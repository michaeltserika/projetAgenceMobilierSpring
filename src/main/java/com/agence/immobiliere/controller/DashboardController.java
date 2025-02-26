package com.agence.immobiliere.controller;

import com.agence.immobiliere.model.Paiement;
import com.agence.immobiliere.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @Autowired private BienService bienService;
    @Autowired private ProprietaireService proprietaireService;
    @Autowired private ClientService clientService;
    @Autowired private ContratService contratService;
    @Autowired private PaiementService paiementService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("totalBiens", bienService.findAll().size());
        model.addAttribute("totalProprietaires", proprietaireService.findAll().size());
        model.addAttribute("totalClients", clientService.findAll().size());
        model.addAttribute("totalContrats", contratService.findAll().size());
        model.addAttribute("totalPaiements", paiementService.findAll().stream()
                .filter(p -> p.getStatut() == Paiement.StatutPaiement.Effectué)
                .mapToDouble(p -> p.getMontant().doubleValue()).sum());
        return "dashboard"; // Plus de "layout"
    }
}