package ma.project.Exercice2.services;

import ma.project.Exercice2.classes.Projet;
import ma.project.Exercice2.classes.Tache;
import ma.project.Exercice2.repositories.ProjetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjetService {

    private final ProjetRepository projetRepository;

    public ProjetService(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    public Projet create(Projet p) {
        return projetRepository.save(p);
    }

    public Projet update(Projet p) {
        return projetRepository.save(p);
    }

    public void delete(int id) {
        projetRepository.deleteById(id);
    }

    public Projet getById(int id) {
        return projetRepository.findById(id).orElse(null);
    }

    public List<Projet> getAll() {
        return projetRepository.findAll();
    }

    public List<Tache> getTachesByProjet(int projetId) {
        return projetRepository.findTachesByProjet(projetId);
    }

    public List<Tache> getTachesRealisees(int projetId) {
        // Tâches avec date de fin réelle non nulle
        return projetRepository.findTachesRealisees(projetId);
    }

    public List<Tache> getTachesPlanifiees(int projetId) {
        // Tâches avec date de fin réelle nulle (ou non encore terminées)
        return projetRepository.findTachesPlanifiees(projetId);
    }
}
