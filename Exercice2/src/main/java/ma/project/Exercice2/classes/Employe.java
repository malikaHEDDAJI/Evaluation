package ma.project.Exercice2.classes;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String telephone;

    @OneToMany(mappedBy = "employe")
    private List<ma.project.Exercice2.classes.EmployeTache> employeTaches;

    @OneToMany(mappedBy = "chefProjet")
    private List<ma.project.Exercice2.classes.Projet> projets;

    public Employe() {}
    public Employe(String nom, String prenom, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
    }

    public Employe(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSalaire() {
        return telephone;
    }

    public void setSalaire(String salaire) {
        this.telephone = salaire;
    }

    public List<ma.project.Exercice2.classes.EmployeTache> getEmployeTaches() {
        return employeTaches;
    }


    public String toString() {
        return "Employe{" + id + " - " + nom + " " + prenom + "}";
    }
}
