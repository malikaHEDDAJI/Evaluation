package ma.project.Exercice3.beans;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Homme extends Personne {

    @OneToMany(mappedBy = "homme", cascade = CascadeType.ALL)
    private List<ma.project.Exercice3.beans.Mariage> mariages;

    public Homme() {}

    public Homme(String nom, String prenom, String telephone, String adresse, Date dateNaissance, List<ma.project.Exercice3.beans.Mariage> mariages) {
        super(nom, prenom, telephone, adresse, dateNaissance);
        this.mariages = mariages;
    }

    public List<ma.project.Exercice3.beans.Mariage> getMariages() { return mariages; }
    public void setMariages(List<ma.project.Exercice3.beans.Mariage> mariages) { this.mariages = mariages; }

    @Override
    public String toString() {
        return "Homme{" + nom + " " + prenom + "}";
    }
}
