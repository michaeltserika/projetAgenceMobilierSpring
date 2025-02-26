package com.agence.immobiliere.controller;

import com.agence.immobiliere.model.Utilisateur;
import com.agence.immobiliere.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/utilisateurs")
    public String listUtilisateurs(Model model) {
        model.addAttribute("utilisateurs", utilisateurService.findAll());
        return "utilisateurs/list"; // Plus de "layout"
    }

    @GetMapping("/utilisateur/new")
    public String showAddForm(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "utilisateurs/form"; // Plus de "layout"
    }

    @PostMapping("/utilisateur/save")
    public String saveUtilisateur(@ModelAttribute("utilisateur") Utilisateur utilisateur) {
        utilisateurService.save(utilisateur);
        return "redirect:/utilisateurs";
    }

    @GetMapping("/utilisateur/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Utilisateur utilisateur = utilisateurService.findById(id);
        if (utilisateur == null) return "redirect:/utilisateurs";
        model.addAttribute("utilisateur", utilisateur);
        return "utilisateurs/form"; // Plus de "layout"
    }

    @GetMapping("/utilisateur/delete/{id}")
    public String deleteUtilisateur(@PathVariable("id") Integer id) {
        Utilisateur utilisateur = utilisateurService.findById(id);
        if (utilisateur != null) utilisateurService.delete(utilisateur);
        return "redirect:/utilisateurs";
    }
}