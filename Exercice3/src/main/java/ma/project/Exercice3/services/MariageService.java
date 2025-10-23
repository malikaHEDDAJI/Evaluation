package ma.project.Exercice3.services;

import ma.project.Exercice3.beans.Mariage;
import ma.project.Exercice3.repositories.MariageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Transactional
public class MariageService {
    private final MariageRepository repo;

    public MariageService(MariageRepository repo) {
        this.repo = repo;
    }

    public Mariage create(Mariage m) { return repo.save(m); }
    public Mariage findById(int id) { return repo.findById(id).orElse(null); }
    public List<Mariage> findAll() { return repo.findAll(); }
    public Mariage update(Mariage m) { return repo.save(m); }
    public void delete(Mariage m) { repo.delete(m); }

    public void afficherDetailsMariagesHomme(int hommeId) {
        var maybe = repo.findByHommeIdOrderByDateDebut(hommeId);
        if (maybe == null || maybe.isEmpty()) {
            System.out.println("Aucun mariage trouvé pour l'homme id " + hommeId);
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String nomHomme = maybe.get(0).getHomme().getNom() + " " + maybe.get(0).getHomme().getPrenom();
        System.out.println("Nom : " + nomHomme);

        var mariages = maybe;
        var mariagesEnCours = mariages.stream().filter(m -> m.getDateFin() == null).toList();
        var mariagesEchoues = mariages.stream().filter(m -> m.getDateFin() != null).toList();

        if (!mariagesEnCours.isEmpty()) {
            System.out.println("Mariages En Cours :");
            for (int i = 0; i < mariagesEnCours.size(); i++) {
                var m = mariagesEnCours.get(i);
                System.out.printf("%d. Femme : %s %s   Date Début : %s    Nbr Enfants : %d%n",
                        i + 1,
                        m.getFemme().getNom(),
                        m.getFemme().getPrenom(),
                        sdf.format(m.getDateDebut()),
                        m.getNbrEnfant());
            }
        }

        if (!mariagesEchoues.isEmpty()) {
            System.out.println("\nMariages échoués :");
            for (int i = 0; i < mariagesEchoues.size(); i++) {
                var m = mariagesEchoues.get(i);
                System.out.printf("%d. Femme : %s %s  Date Début : %s    Date Fin : %s    Nbr Enfants : %d%n",
                        i + 1,
                        m.getFemme().getNom(),
                        m.getFemme().getPrenom(),
                        sdf.format(m.getDateDebut()),
                        sdf.format(m.getDateFin()),
                        m.getNbrEnfant());
            }
        }
    }
}
