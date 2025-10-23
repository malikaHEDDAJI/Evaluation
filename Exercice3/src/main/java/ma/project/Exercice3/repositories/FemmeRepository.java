package ma.project.Exercice3.repositories;

import ma.project.Exercice3.beans.Femme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FemmeRepository extends JpaRepository<Femme, Integer> {

    @Query("SELECT f FROM Femme f WHERE (SELECT COUNT(m) FROM Mariage m WHERE m.femme = f) >= 2")
    List<Femme> femmesMarieesDeuxFois();

    @Query(value = "SELECT COALESCE(SUM(m.nbr_enfant),0) FROM mariage m WHERE m.femme_id = :idFemme AND m.date_debut BETWEEN :d1 AND :d2", nativeQuery = true)
    Long nombreEnfantsEntreDates(@Param("idFemme") int idFemme, @Param("d1") Date d1, @Param("d2") Date d2);
}
