package com.agence.immobiliere.controller;

import com.agence.immobiliere.model.Bien;
import com.agence.immobiliere.service.BienService;
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
public class BienController {
    @Autowired
    private BienService bienService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ProprietaireService proprietaireService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("/biens")
    public String listBiens(Model model) {
        model.addAttribute("biens", bienService.findAll());
        return "biens/list";
    }

    @GetMapping("/bien/new")
    public String showAddForm(Model model) {
        model.addAttribute("bien", new Bien());
        model.addAttribute("utilisateurs", utilisateurService.findAll());
        model.addAttribute("proprietaires", proprietaireService.findAll());
        return "biens/form";
    }

    @PostMapping("/bien/save")
    public String saveBien(@ModelAttribute("bien") Bien bien,
                           @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        if (!imageFile.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());
            imageFile.transferTo(filePath);
            bien.setImagePath("/uploads/" + fileName);
        } else if (bien.getImagePath() == null) {
            bien.setImagePath("/uploads/default_image.jpg");
        }
        bienService.save(bien);
        return "redirect:/biens";
    }

    @GetMapping("/bien/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Bien bien = bienService.findById(id);
        if (bien == null) return "redirect:/biens";
        model.addAttribute("bien", bien);
        model.addAttribute("utilisateurs", utilisateurService.findAll());
        model.addAttribute("proprietaires", proprietaireService.findAll());
        return "biens/form";
    }

    @GetMapping("/bien/delete/{id}")
    public String deleteBien(@PathVariable("id") Integer id) {
        Bien bien = bienService.findById(id);
        if (bien != null) {
            if (bien.getImagePath() != null && !bien.getImagePath().equals("/uploads/default_image.jpg")) {
                try {
                    Files.deleteIfExists(Paths.get("." + bien.getImagePath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            bienService.delete(bien);
        }
        return "redirect:/biens";
    }
}