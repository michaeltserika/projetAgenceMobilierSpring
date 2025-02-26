package com.agence.immobiliere.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contrats")
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bien_id", nullable = false)
    private Integer bienId;

    @Column(name = "client_id", nullable = false)
    private Integer clientId;

    @Column(name = "agent_id", nullable = false)
    private Integer agentId;

    @Column(name = "date_signature", nullable = false)
    private LocalDate dateSignature;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal montant;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String conditions;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutContrat statut = StatutContrat.En_attente;

    @ManyToOne
    @JoinColumn(name = "bien_id", insertable = false, updatable = false)
    private Bien bien;

    @ManyToOne
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "agent_id", insertable = false, updatable = false)
    private Utilisateur agent;

    public enum StatutContrat { Signé, En_attente, Annulé }

    public Contrat() {}
    public Contrat(Integer bienId, Integer clientId, Integer agentId, LocalDate dateSignature, BigDecimal montant, String conditions, StatutContrat statut) {
        this.bienId = bienId;
        this.clientId = clientId;
        this.agentId = agentId;
        this.dateSignature = dateSignature;
        this.montant = montant;
        this.conditions = conditions;
        this.statut = statut;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getBienId() { return bienId; }
    public void setBienId(Integer bienId) { this.bienId = bienId; }
    public Integer getClientId() { return clientId; }
    public void setClientId(Integer clientId) { this.clientId = clientId; }
    public Integer getAgentId() { return agentId; }
    public void setAgentId(Integer agentId) { this.agentId = agentId; }
    public LocalDate getDateSignature() { return dateSignature; }
    public void setDateSignature(LocalDate dateSignature) { this.dateSignature = dateSignature; }
    public BigDecimal getMontant() { return montant; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }
    public String getConditions() { return conditions; }
    public void setConditions(String conditions) { this.conditions = conditions; }
    public StatutContrat getStatut() { return statut; }
    public void setStatut(StatutContrat statut) { this.statut = statut; }
    public Bien getBien() { return bien; }
    public void setBien(Bien bien) { this.bien = bien; }
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    public Utilisateur getAgent() { return agent; }
    public void setAgent(Utilisateur agent) { this.agent = agent; }
}