package ma.project.Exercice2.services;

import ma.project.Exercice2.classes.EmployeTache;
import ma.project.Exercice2.repositories.EmployeTacheRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeTacheService {

    private final EmployeTacheRepository employeTacheRepository;

    public EmployeTacheService(EmployeTacheRepository employeTacheRepository) {
        this.employeTacheRepository = employeTacheRepository;
    }

    public EmployeTache create(EmployeTache et) {
        return employeTacheRepository.save(et);
    }

    public EmployeTache update(EmployeTache et) {
        return employeTacheRepository.save(et);
    }

    public void delete(int id) {
        employeTacheRepository.deleteById(id);
    }

    public EmployeTache getById(int id) {
        return employeTacheRepository.findById(id).orElse(null);
    }

    public List<EmployeTache> getAll() {
        return employeTacheRepository.findAll();
    }

    public List<EmployeTache> getByEmploye(int employeId) {
        return employeTacheRepository.findByEmployeId(employeId);
    }
}
