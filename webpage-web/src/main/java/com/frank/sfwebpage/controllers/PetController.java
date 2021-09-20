package com.frank.sfwebpage.controllers;

import com.frank.sfwebpage.model.Owner;
import com.frank.sfwebpage.model.Pet;
import com.frank.sfwebpage.model.PetType;
import com.frank.sfwebpage.services.OwnerService;
import com.frank.sfwebpage.services.PetService;
import com.frank.sfwebpage.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private final PetService petService;
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;

    public PetController(PetService petService, PetTypeService petTypeService, OwnerService ownerService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
    }

    @ModelAttribute("types")
    public Set<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String initCreationForm(@ModelAttribute Owner owner, Model model) {
        Pet pet = new Pet();
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/new")
    public String processCreationForm(@ModelAttribute Owner owner, Pet pet, BindingResult bindingResult, Model model) {
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
            bindingResult.rejectValue("name", "duplicate", "already exists");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("pet", pet);
            return "pets/createOrUpdatePetForm";
        }

        pet.setOwner(owner);
        petService.save(pet);

        return "redirect:/owners/%d".formatted(owner.getId());
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));
        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(Pet pet, BindingResult bindingResult, Owner owner, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pet", pet);
            return "pets/createOrUpdatePetForm";
        }

        pet.setOwner(owner);
        petService.save(pet);

        return "redirect:/owners/%d".formatted(owner.getId());
    }
}
