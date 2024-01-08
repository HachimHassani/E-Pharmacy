package com.grp10.e_pharmacy.controller;

import com.grp10.e_pharmacy.model.PharmacieDTO;
import com.grp10.e_pharmacy.service.PharmacieService;
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
@RequestMapping("/pharmacies")
public class PharmacieController {

    private final PharmacieService pharmacieService;

    public PharmacieController(final PharmacieService pharmacieService) {
        this.pharmacieService = pharmacieService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("pharmacies", pharmacieService.findAll());
        return "pharmacie/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("pharmacie") final PharmacieDTO pharmacieDTO) {
        return "pharmacie/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("pharmacie") @Valid final PharmacieDTO pharmacieDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "pharmacie/add";
        }
        pharmacieService.create(pharmacieDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("pharmacie.create.success"));
        return "redirect:/pharmacies";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("pharmacie", pharmacieService.get(id));
        return "pharmacie/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("pharmacie") @Valid final PharmacieDTO pharmacieDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "pharmacie/edit";
        }
        pharmacieService.update(id, pharmacieDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("pharmacie.update.success"));
        return "redirect:/pharmacies";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        final String referencedWarning = pharmacieService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            pharmacieService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("pharmacie.delete.success"));
        }
        return "redirect:/pharmacies";
    }

}
