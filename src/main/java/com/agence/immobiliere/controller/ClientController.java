package com.agence.immobiliere.controller;

import com.agence.immobiliere.model.Client;
import com.agence.immobiliere.service.ClientService;
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
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("/clients")
    public String listClients(Model model) {
        model.addAttribute("clients", clientService.findAll());
        return "clients/list";
    }

    @GetMapping("/client/new")
    public String showAddForm(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("utilisateurs", utilisateurService.findAll());
        return "clients/form";
    }

    @PostMapping("/client/save")
    public String saveClient(@ModelAttribute("client") Client client,
                             @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        if (!imageFile.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());
            imageFile.transferTo(filePath);
            client.setImagePath("/uploads/" + fileName);
        } else if (client.getImagePath() == null) {
            client.setImagePath("/uploads/default_image.jpg");
        }
        clientService.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/client/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Client client = clientService.findById(id);
        if (client == null) return "redirect:/clients";
        model.addAttribute("client", client);
        model.addAttribute("utilisateurs", utilisateurService.findAll());
        return "clients/form";
    }

    @GetMapping("/client/delete/{id}")
    public String deleteClient(@PathVariable("id") Integer id) {
        Client client = clientService.findById(id);
        if (client != null) {
            if (client.getImagePath() != null && !client.getImagePath().equals("/uploads/default_image.jpg")) {
                try {
                    Files.deleteIfExists(Paths.get("." + client.getImagePath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            clientService.delete(client);
        }
        return "redirect:/clients";
    }
}