package com.grp10.e_pharmacy.controller;

import com.grp10.e_pharmacy.domain.Ordonance;
import com.grp10.e_pharmacy.domain.Pharmacie;
import com.grp10.e_pharmacy.domain.User;
import com.grp10.e_pharmacy.model.CommandeDTO;
import com.grp10.e_pharmacy.model.Statue;
import com.grp10.e_pharmacy.repos.OrdonanceRepository;
import com.grp10.e_pharmacy.repos.PharmacieRepository;
import com.grp10.e_pharmacy.repos.UserRepository;
import com.grp10.e_pharmacy.service.CommandeService;
import com.grp10.e_pharmacy.util.CustomCollectors;
import com.grp10.e_pharmacy.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/commandes")
public class CommandeController {

    private final CommandeService commandeService;
    private final PharmacieRepository pharmacieRepository;
    private final OrdonanceRepository ordonanceRepository;
    private final UserRepository userRepository;

    public CommandeController(final CommandeService commandeService,
            final PharmacieRepository pharmacieRepository,
            final OrdonanceRepository ordonanceRepository, final UserRepository userRepository) {
        this.commandeService = commandeService;
        this.pharmacieRepository = pharmacieRepository;
        this.ordonanceRepository = ordonanceRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("statueValues", Statue.values());
        model.addAttribute("pharmacieValues", pharmacieRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Pharmacie::getId, Pharmacie::getNom)));
        model.addAttribute("ordonanceValues", ordonanceRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Ordonance::getId, Ordonance::getStatue)));
        model.addAttribute("patientValues", userRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(User::getId, User::getNom)));
        model.addAttribute("livreurValues", userRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(User::getId, User::getNom)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("commandes", commandeService.findAll());
        return "commande/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("commande") final CommandeDTO commandeDTO) {
        return "commande/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("commande") @Valid final CommandeDTO commandeDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("ordonance") && commandeDTO.getOrdonance() != null && commandeService.ordonanceExists(commandeDTO.getOrdonance())) {
            bindingResult.rejectValue("ordonance", "Exists.commande.ordonance");
        }
        if (bindingResult.hasErrors()) {
            return "commande/add";
        }
        commandeService.create(commandeDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("commande.create.success"));
        return "redirect:/commandes";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("commande", commandeService.get(id));
        return "commande/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("commande") @Valid final CommandeDTO commandeDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        final CommandeDTO currentCommandeDTO = commandeService.get(id);
        if (!bindingResult.hasFieldErrors("ordonance") && commandeDTO.getOrdonance() != null &&
                !commandeDTO.getOrdonance().equals(currentCommandeDTO.getOrdonance()) &&
                commandeService.ordonanceExists(commandeDTO.getOrdonance())) {
            bindingResult.rejectValue("ordonance", "Exists.commande.ordonance");
        }
        if (bindingResult.hasErrors()) {
            return "commande/edit";
        }
        commandeService.update(id, commandeDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("commande.update.success"));
        return "redirect:/commandes";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        commandeService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("commande.delete.success"));
        return "redirect:/commandes";
    }

}
