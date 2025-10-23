package ma.project.Exercice2.repositories;

import ma.project.Exercice2.classes.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TacheRepository extends JpaRepository<Tache, Integer> {

    @Query("FROM Tache t WHERE t.prix > :prixMin")
    List<Tache> findByPrixSuperieur(@Param("prixMin") double prixMin);

    List<Tache> findByDateDebutReelleBetween(Date d1, Date d2);
}
