package ma.project.Exercice2.services;

import ma.project.Exercice2.classes.Employe;
import ma.project.Exercice2.classes.Projet;
import ma.project.Exercice2.classes.Tache;
import ma.project.Exercice2.repositories.EmployeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeService {

    private final EmployeRepository employeRepository;

    public EmployeService(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    public Employe create(Employe e) {
        return employeRepository.save(e);
    }

    public Employe update(Employe e) {
        return employeRepository.save(e);
    }

    public void delete(int id) {
        employeRepository.deleteById(id);
    }

    public Employe getById(int id) {
        return employeRepository.findById(id).orElse(null);
    }

    public List<Employe> getAll() {
        return employeRepository.findAll();
    }

    public List<Tache> getTachesByEmploye(int id) {
        return employeRepository.findTachesByEmploye(id);
    }

    public List<Projet> getProjetsByEmploye(int id) {
        return employeRepository.findProjetsByEmploye(id);
    }
}
