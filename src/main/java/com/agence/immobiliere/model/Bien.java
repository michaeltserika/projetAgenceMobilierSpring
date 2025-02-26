package com.agence.immobiliere.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "biens")
public class Bien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal prix;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeBien type = TypeBien.Maison;

    @Column(nullable = false)
    private Float surface;

    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false)
    private String ville;

    @Column(name = "code_postal", nullable = false, length = 10)
    private String codePostal;

    @Column(name = "image_path", nullable = false)
    private String imagePath;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutBien statut = StatutBien.Disponible;

    @Column(name = "agent_id", nullable = false)
    private Integer agentId;

    @Column(name = "proprietaire_id", nullable = false)
    private Integer proprietaireId;

    @Column(name = "date_ajout")
    private LocalDateTime dateAjout = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "agent_id", insertable = false, updatable = false)
    private Utilisateur agent;

    @ManyToOne
    @JoinColumn(name = "proprietaire_id", insertable = false, updatable = false)
    private Proprietaire proprietaire;

    public enum TypeBien { Appartement, Maison, Terrain, Bureau, Local_Commercial }
    public enum StatutBien { Disponible, Vendu, En_attente }

    public Bien() {}
    public Bien(String titre, String description, BigDecimal prix, TypeBien type, Float surface, String adresse, String ville, String codePostal, String imagePath, StatutBien statut, Integer agentId, Integer proprietaireId) {
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.type = type;
        this.surface = surface;
        this.adresse = adresse;
        this.ville = ville;
        this.codePostal = codePostal;
        this.imagePath = imagePath;
        this.statut = statut;
        this.agentId = agentId;
        this.proprietaireId = proprietaireId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getPrix() { return prix; }
    public void setPrix(BigDecimal prix) { this.prix = prix; }
    public TypeBien getType() { return type; }
    public void setType(TypeBien type) { this.type = type; }
    public Float getSurface() { return surface; }
    public void setSurface(Float surface) { this.surface = surface; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }
    public String getCodePostal() { return codePostal; }
    public void setCodePostal(String codePostal) { this.codePostal = codePostal; }
    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public StatutBien getStatut() { return statut; }
    public void setStatut(StatutBien statut) { this.statut = statut; }
    public Integer getAgentId() { return agentId; }
    public void setAgentId(Integer agentId) { this.agentId = agentId; }
    public Integer getProprietaireId() { return proprietaireId; }
    public void setProprietaireId(Integer proprietaireId) { this.proprietaireId = proprietaireId; }
    public LocalDateTime getDateAjout() { return dateAjout; }
    public void setDateAjout(LocalDateTime dateAjout) { this.dateAjout = dateAjout; }
    public Utilisateur getAgent() { return agent; }
    public void setAgent(Utilisateur agent) { this.agent = agent; }
    public Proprietaire getProprietaire() { return proprietaire; }
    public void setProprietaire(Proprietaire proprietaire) { this.proprietaire = proprietaire; }
}