package ma.projet.beans;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "Femme.marieesDeuxFois",
                query = "SELECT f FROM Femme f WHERE (SELECT COUNT(m) FROM Mariage m WHERE m.femme = f) >= 2"
        )
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Femme.nombreEnfantsEntreDates",
                query = "SELECT SUM(m.nbrEnfant) AS totalEnfants FROM mariage m " +
                        "WHERE m.femme_id = :idFemme AND m.dateDebut BETWEEN :d1 AND :d2",
                resultSetMapping = "Mapping.LongResult"
        )
})
@SqlResultSetMapping(
        name = "Mapping.LongResult",
        columns = { @ColumnResult(name = "totalEnfants") }
)

public class Femme extends Personne {

    @OneToMany(mappedBy = "femme", fetch = FetchType.LAZY)
    private List<Mariage> mariages;

    public Femme() {}

    public Femme(String nom, String prenom, String telephone, String adresse, Date dateNaissance) {
        super(nom, prenom, telephone, adresse, dateNaissance);
    }

    public List<Mariage> getMariages() {
        return mariages;
    }

    public void setMariages(List<Mariage> mariages) {
        this.mariages = mariages;
    }
}
