package ma.projet.Exercice1.classes;

import jakarta.persistence.*;

@Entity
@NamedQuery(name="Produit.findByPrixGreaterThan",
        query="SELECT p FROM Produit p WHERE p.prix > :prix")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String reference;
    private double prix;
    private int quantite;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    public Produit() {}
    public Produit(String reference, double prix, int quantite, Categorie categorie) {
        this.reference = reference;
        this.prix = prix;
        this.quantite = quantite;
        this.categorie = categorie;
    }

    public int getId() { return id; }
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }
    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    public Categorie getCategorie() { return categorie; }
    public void setCategorie(Categorie categorie) { this.categorie = categorie; }
}