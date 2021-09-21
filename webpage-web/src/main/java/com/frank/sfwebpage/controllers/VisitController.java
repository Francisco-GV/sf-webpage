package com.frank.sfwebpage.controllers;

import com.frank.sfwebpage.model.Pet;
import com.frank.sfwebpage.model.Visit;
import com.frank.sfwebpage.services.PetService;
import com.frank.sfwebpage.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners/*/pets/{petId}/visits")
@SuppressWarnings("MVCPathVariableInspection")
public class VisitController {
    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("pet")
    public Pet findPet(@PathVariable("petId") Long petId) {
        return petService.findById(petId);
    }

    @GetMapping("/new")
    public String initNewVisitForm(@ModelAttribute Pet pet, Model model) {
        Visit visit = new Visit();
        visit.setPet(pet);
        model.addAttribute("visit", visit);
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/new")
    public String processNewVisitForm(@ModelAttribute Pet pet, @ModelAttribute Visit visit, BindingResult result, Model model) {
        visit.setPet(pet);

        if (result.hasErrors()) {
            model.addAttribute("visit", visit);
            return "pets/createOrUpdateVisitForm";
        }

        visitService.save(visit);

        return "redirect:/owners/%d".formatted(pet.getOwner().getId());
    }
}