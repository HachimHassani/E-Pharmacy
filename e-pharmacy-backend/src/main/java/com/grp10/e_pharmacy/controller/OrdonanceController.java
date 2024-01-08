package com.grp10.e_pharmacy.controller;

import com.grp10.e_pharmacy.domain.Medicament;
import com.grp10.e_pharmacy.domain.User;
import com.grp10.e_pharmacy.model.OrdonanceDTO;
import com.grp10.e_pharmacy.repos.MedicamentRepository;
import com.grp10.e_pharmacy.repos.UserRepository;
import com.grp10.e_pharmacy.service.OrdonanceService;
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
@RequestMapping("/ordonances")
public class OrdonanceController {

    private final OrdonanceService ordonanceService;
    private final MedicamentRepository medicamentRepository;
    private final UserRepository userRepository;

    public OrdonanceController(final OrdonanceService ordonanceService,
            final MedicamentRepository medicamentRepository, final UserRepository userRepository) {
        this.ordonanceService = ordonanceService;
        this.medicamentRepository = medicamentRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("medicamentsValues", medicamentRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Medicament::getId, Medicament::getNom)));
        model.addAttribute("doctorValues", userRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(User::getId, User::getNom)));
        model.addAttribute("patientValues", userRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(User::getId, User::getNom)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("ordonances", ordonanceService.findAll());
        return "ordonance/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("ordonance") final OrdonanceDTO ordonanceDTO) {
        return "ordonance/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("ordonance") @Valid final OrdonanceDTO ordonanceDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "ordonance/add";
        }
        ordonanceService.create(ordonanceDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("ordonance.create.success"));
        return "redirect:/ordonances";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("ordonance", ordonanceService.get(id));
        return "ordonance/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("ordonance") @Valid final OrdonanceDTO ordonanceDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "ordonance/edit";
        }
        ordonanceService.update(id, ordonanceDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("ordonance.update.success"));
        return "redirect:/ordonances";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        final String referencedWarning = ordonanceService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            ordonanceService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("ordonance.delete.success"));
        }
        return "redirect:/ordonances";
    }

}
