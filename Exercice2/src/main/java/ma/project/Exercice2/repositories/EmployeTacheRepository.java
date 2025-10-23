package ma.project.Exercice2.repositories;

import ma.project.Exercice2.classes.EmployeTache;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeTacheRepository extends JpaRepository<EmployeTache, Integer> {

    List<EmployeTache> findByEmployeId(int employeId);
}
