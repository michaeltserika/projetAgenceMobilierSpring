package com.agence.immobiliere.controller;

import com.agence.immobiliere.model.Contrat;
import com.agence.immobiliere.service.BienService;
import com.agence.immobiliere.service.ClientService;
import com.agence.immobiliere.service.ContratService;
import com.agence.immobiliere.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContratController {
    @Autowired
    private ContratService contratService;

    @Autowired
    private BienService bienService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/contrats")
    public String listContrats(Model model) {
        model.addAttribute("contrats", contratService.findAll());
        return "contrats/list";
    }

    @GetMapping("/contrat/new")
    public String showAddForm(Model model) {
        model.addAttribute("contrat", new Contrat());
        model.addAttribute("biens", bienService.findAll());
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("utilisateurs", utilisateurService.findAll());
        return "contrats/form";
    }

    @PostMapping("/contrat/save")
    public String saveContrat(@ModelAttribute("contrat") Contrat contrat) {
        contratService.save(contrat);
        return "redirect:/contrats";
    }

    @GetMapping("/contrat/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Contrat contrat = contratService.findById(id);
        if (contrat == null) return "redirect:/contrats";
        model.addAttribute("contrat", contrat);
        model.addAttribute("biens", bienService.findAll());
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("utilisateurs", utilisateurService.findAll());
        return "contrats/form";
    }

    @GetMapping("/contrat/delete/{id}")
    public String deleteContrat(@PathVariable("id") Integer id) {
        Contrat contrat = contratService.findById(id);
        if (contrat != null) contratService.delete(contrat);
        return "redirect:/contrats";
    }
}