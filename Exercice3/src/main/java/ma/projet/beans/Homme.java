package ma.projet.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Homme extends Personne {

    @OneToMany(mappedBy = "homme", cascade = CascadeType.ALL)
    private List<Mariage> mariages;

    public Homme(String nom, String prenom, String telephone, String adresse, Date dateNaissance, List<Mariage> mariages) {
        super(nom, prenom, telephone, adresse, dateNaissance);
        this.mariages = mariages;
    }

    public Homme() {

    }

    public List<Mariage> getMariages() {
        return mariages;
    }

    public void setMariages(List<Mariage> mariages) {
        this.mariages = mariages;
    }

    @Override
    public String toString() {
        return "Homme{" + nom + " " + prenom + "}";
    }
}
