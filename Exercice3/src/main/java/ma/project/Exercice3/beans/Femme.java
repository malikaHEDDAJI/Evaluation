package ma.project.Exercice3.beans;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Femme extends Personne {

    @OneToMany(mappedBy = "femme", fetch = FetchType.LAZY)
    private List<ma.project.Exercice3.beans.Mariage> mariages;

    public Femme() {}

    public Femme(String nom, String prenom, String telephone, String adresse, Date dateNaissance) {
        super(nom, prenom, telephone, adresse, dateNaissance);
    }

    public List<ma.project.Exercice3.beans.Mariage> getMariages() { return mariages; }
    public void setMariages(List<ma.project.Exercice3.beans.Mariage> mariages) { this.mariages = mariages; }
}
