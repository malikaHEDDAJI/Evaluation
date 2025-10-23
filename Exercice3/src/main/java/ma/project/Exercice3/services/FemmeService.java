package ma.project.Exercice3.services;

import ma.project.Exercice3.beans.Femme;
import ma.project.Exercice3.repositories.FemmeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FemmeService {

    private final FemmeRepository repo;

    public FemmeService(FemmeRepository repo) {
        this.repo = repo;
    }

    public Femme create(Femme f) { return repo.save(f); }
    public Femme findById(int id) { return repo.findById(id).orElse(null); }
    public List<Femme> findAll() { return repo.findAll(); }
    public Femme update(Femme f) { return repo.save(f); }
    public void delete(Femme f) { repo.delete(f); }

    public Long nombreEnfantsEntreDates(int idFemme, Date d1, Date d2) {
        Long res = repo.nombreEnfantsEntreDates(idFemme, d1, d2);
        return res == null ? 0L : res;
    }

    public List<Femme> femmesMarieesDeuxFoisOuPlus() {
        return repo.femmesMarieesDeuxFois();
    }
}
