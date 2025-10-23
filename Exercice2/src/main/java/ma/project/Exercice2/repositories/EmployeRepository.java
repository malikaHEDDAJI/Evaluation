package ma.project.Exercice2.repositories;

import ma.project.Exercice2.classes.Employe;
import ma.project.Exercice2.classes.Projet;
import ma.project.Exercice2.classes.Tache;
import ma.project.Exercice2.classes.EmployeTache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeRepository extends JpaRepository<Employe, Integer> {

    @Query("SELECT et.tache FROM EmployeTache et WHERE et.employe.id = :id")
    List<Tache> findTachesByEmploye(@Param("id") int employeId);

    @Query("FROM Projet p WHERE p.chefProjet.id = :id")
    List<Projet> findProjetsByEmploye(@Param("id") int employeId);
}
