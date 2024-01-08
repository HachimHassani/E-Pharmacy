package com.grp10.e_pharmacy.controller;

import com.grp10.e_pharmacy.model.MedicamentDTO;
import com.grp10.e_pharmacy.service.MedicamentService;
import com.grp10.e_pharmacy.util.WebUtils;
import jakarta.validation.Valid;
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
@RequestMapping("/medicaments")
public class MedicamentController {

    private final MedicamentService medicamentService;

    public MedicamentController(final MedicamentService medicamentService) {
        this.medicamentService = medicamentService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("medicaments", medicamentService.findAll());
        return "medicament/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("medicament") final MedicamentDTO medicamentDTO) {
        return "medicament/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("medicament") @Valid final MedicamentDTO medicamentDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("nom") && medicamentService.nomExists(medicamentDTO.getNom())) {
            bindingResult.rejectValue("nom", "Exists.medicament.nom");
        }
        if (bindingResult.hasErrors()) {
            return "medicament/add";
        }
        medicamentService.create(medicamentDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("medicament.create.success"));
        return "redirect:/medicaments";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("medicament", medicamentService.get(id));
        return "medicament/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("medicament") @Valid final MedicamentDTO medicamentDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        final MedicamentDTO currentMedicamentDTO = medicamentService.get(id);
        if (!bindingResult.hasFieldErrors("nom") &&
                !medicamentDTO.getNom().equalsIgnoreCase(currentMedicamentDTO.getNom()) &&
                medicamentService.nomExists(medicamentDTO.getNom())) {
            bindingResult.rejectValue("nom", "Exists.medicament.nom");
        }
        if (bindingResult.hasErrors()) {
            return "medicament/edit";
        }
        medicamentService.update(id, medicamentDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("medicament.update.success"));
        return "redirect:/medicaments";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        final String referencedWarning = medicamentService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            medicamentService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("medicament.delete.success"));
        }
        return "redirect:/medicaments";
    }

}
