package com.frank.sfwebpage.controllers;

import com.frank.sfwebpage.model.Vet;
import com.frank.sfwebpage.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
@RequestMapping("/vets")
public class VetController {

    private final VetService vetService;

    @Autowired
    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping({"", "/", "/index", "/index.html"})
    public String listVets(Model model) {

        model.addAttribute("vets", vetService.findAll());

        return "vets/vetList";
    }

    @GetMapping({"/find", "/find.html"})
    public String findVet(Model model) {
        model.addAttribute("message", "Not implemented yet!!!");
        return "error";
    }

    @GetMapping("/api")
    public @ResponseBody Set<Vet> listVetsJson() {
        return vetService.findAll();
    }
}
