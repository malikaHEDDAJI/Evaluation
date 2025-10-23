package ma.projet.classes;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @ManyToOne
    @JoinColumn(name = "chef_id")
    private Employe chefProjet;

    @OneToMany(mappedBy = "projet")
    private List<Tache> taches;

    public Projet() {}
    public Projet(String nom, Date dateDebut, Employe chefProjet) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.chefProjet = chefProjet;
    }

    public Projet(String nom, Date dateDebut) {
        this.nom = nom;
        this.dateDebut = dateDebut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Employe getChefProjet() {
        return chefProjet;
    }

    public void setChefProjet(Employe chefProjet) {
        this.chefProjet = chefProjet;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }
}
