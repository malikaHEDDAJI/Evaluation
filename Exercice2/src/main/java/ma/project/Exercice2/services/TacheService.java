package ma.project.Exercice2.services;

import ma.project.Exercice2.classes.Tache;
import ma.project.Exercice2.repositories.TacheRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TacheService {

    private final TacheRepository tacheRepository;

    public TacheService(TacheRepository tacheRepository) {
        this.tacheRepository = tacheRepository;
    }

    public Tache create(Tache t) {
        return tacheRepository.save(t);
    }

    public Tache update(Tache t) {
        return tacheRepository.save(t);
    }

    public void delete(int id) {
        tacheRepository.deleteById(id);
    }

    public Tache getById(int id) {
        return tacheRepository.findById(id).orElse(null);
    }

    public List<Tache> getAll() {
        return tacheRepository.findAll();
    }

    public List<Tache> getByPrixSuperieur(double prixMin) {
        return tacheRepository.findByPrixSuperieur(prixMin);
    }

    public List<Tache> getTachesPrixSup1000() {
        return tacheRepository.findByPrixSuperieur(1000.0);
    }

    public List<Tache> getTachesEntreDates(Date d1, Date d2) {
        return tacheRepository.findByDateDebutReelleBetween(d1, d2);
    }
}
