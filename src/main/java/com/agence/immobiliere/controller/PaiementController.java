package com.agence.immobiliere.controller;

import com.agence.immobiliere.model.Paiement;
import com.agence.immobiliere.service.ContratService;
import com.agence.immobiliere.service.PaiementService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

@Controller
public class PaiementController {
    @Autowired
    private PaiementService paiementService;

    @Autowired
    private ContratService contratService;

    @GetMapping("/paiements")
    public String listPaiements(Model model) {
        model.addAttribute("paiements", paiementService.findAll());
        return "paiements/list";
    }

    @GetMapping("/paiement/new")
    public String showAddForm(Model model) {
        model.addAttribute("paiement", new Paiement());
        model.addAttribute("contrats", contratService.findAll());
        return "paiements/form";
    }

    @PostMapping("/paiement/save")
    public String savePaiement(@ModelAttribute("paiement") Paiement paiement) {
        paiementService.save(paiement);
        return "redirect:/paiements";
    }

    @GetMapping("/paiement/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Paiement paiement = paiementService.findById(id);
        if (paiement == null) return "redirect:/paiements";
        model.addAttribute("paiement", paiement);
        model.addAttribute("contrats", contratService.findAll());
        return "paiements/form";
    }

    @GetMapping("/paiement/delete/{id}")
    public String deletePaiement(@PathVariable("id") Integer id) {
        Paiement paiement = paiementService.findById(id);
        if (paiement != null) paiementService.delete(paiement);
        return "redirect:/paiements";
    }

    @GetMapping("/paiement/facture/{id}")
    public ResponseEntity<byte[]> generateFacture(@PathVariable("id") Integer id) throws Exception {
        Paiement paiement = paiementService.findById(id);
        if (paiement == null) return ResponseEntity.notFound().build();

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);
        document.open();
        document.add(new Paragraph("Facture - Paiement #" + paiement.getId()));
        document.add(new Paragraph("Contrat: " + paiement.getContrat().getId()));
        document.add(new Paragraph("Client: " + paiement.getContrat().getClient().getNom() + " " + paiement.getContrat().getClient().getPrenom()));
        document.add(new Paragraph("Bien: " + paiement.getContrat().getBien().getTitre()));
        document.add(new Paragraph("Montant: " + paiement.getMontant() + " €"));
        document.add(new Paragraph("Date: " + paiement.getDatePaiement()));
        document.add(new Paragraph("Mode: " + paiement.getModePaiement()));
        document.add(new Paragraph("Statut: " + paiement.getStatut()));
        document.close();

        byte[] pdfBytes = out.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "facture_" + id + ".pdf");
        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }
}