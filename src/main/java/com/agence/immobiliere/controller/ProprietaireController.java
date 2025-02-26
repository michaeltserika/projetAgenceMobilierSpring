package com.agence.immobiliere.controller;

import com.agence.immobiliere.model.Proprietaire;
import com.agence.immobiliere.service.ProprietaireService;
import com.agence.immobiliere.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class ProprietaireController {
    @Autowired
    private ProprietaireService proprietaireService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("/proprietaires")
    public String listProprietaires(Model model) {
        model.addAttribute("proprietaires", proprietaireService.findAll());
        return "proprietaires/list";
    }

    @GetMapping("/proprietaire/new")
    public String showAddForm(Model model) {
        model.addAttribute("proprietaire", new Proprietaire());
        model.addAttribute("utilisateurs", utilisateurService.findAll());
        return "proprietaires/form";
    }

    @PostMapping("/proprietaire/save")
    public String saveProprietaire(@ModelAttribute("proprietaire") Proprietaire proprietaire,
                                   @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        if (!imageFile.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());
            imageFile.transferTo(filePath);
            proprietaire.setImagePath("/uploads/" + fileName);
        } else if (proprietaire.getImagePath() == null) {
            proprietaire.setImagePath("/uploads/default_image.jpg");
        }
        proprietaireService.save(proprietaire);
        return "redirect:/proprietaires";
    }

    @GetMapping("/proprietaire/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Proprietaire proprietaire = proprietaireService.findById(id);
        if (proprietaire == null) return "redirect:/proprietaires";
        model.addAttribute("proprietaire", proprietaire);
        model.addAttribute("utilisateurs", utilisateurService.findAll());
        return "proprietaires/form";
    }

    @GetMapping("/proprietaire/delete/{id}")
    public String deleteProprietaire(@PathVariable("id") Integer id) {
        Proprietaire proprietaire = proprietaireService.findById(id);
        if (proprietaire != null) {
            if (proprietaire.getImagePath() != null && !proprietaire.getImagePath().equals("/uploads/default_image.jpg")) {
                try {
                    Files.deleteIfExists(Paths.get("." + proprietaire.getImagePath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            proprietaireService.delete(proprietaire);
        }
        return "redirect:/proprietaires";
    }
}