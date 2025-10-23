package ma.project.Exercice3.services;

import ma.project.Exercice3.beans.Homme;
import ma.project.Exercice3.repositories.HommeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class HommeService {
    private final HommeRepository repo;

    public HommeService(HommeRepository repo) {
        this.repo = repo;
    }

    public Homme create(Homme h) { return repo.save(h); }
    public Homme findById(int id) { return repo.findById(id).orElse(null); }
    public List<Homme> findAll() { return repo.findAll(); }
    public Homme update(Homme h) { return repo.save(h); }
    public void delete(Homme h) { repo.delete(h); }

    public List<Homme> hommesMarieAQuatreFemmesEntreDates(Date debut, Date fin) {
        return repo.hommesMarieAQuatreFemmesEntreDates(debut, fin);
    }
}
