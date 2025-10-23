package ma.project.Exercice3.repositories;

import ma.project.Exercice3.beans.Homme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface HommeRepository extends JpaRepository<Homme, Integer> {

    @Query("SELECT h FROM Homme h JOIN h.mariages m WHERE m.dateDebut BETWEEN :debut AND :fin GROUP BY h HAVING COUNT(DISTINCT m.femme) = 4")
    List<Homme> hommesMarieAQuatreFemmesEntreDates(@Param("debut") Date debut, @Param("fin") Date fin);
}
