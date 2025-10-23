package ma.project.Exercice2.repositories;

import ma.project.Exercice2.classes.Tache;
import ma.project.Exercice2.classes.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Integer> {

    @Query("SELECT t FROM Tache t WHERE t.projet.id = :projetId")
    List<Tache> findTachesByProjet(int projetId);

    @Query("SELECT t FROM Tache t WHERE t.projet.id = :projetId AND t.dateFinReelle IS NOT NULL")
    List<Tache> findTachesRealisees(int projetId);

    @Query("SELECT t FROM Tache t WHERE t.projet.id = :projetId AND t.dateFinReelle IS NULL")
    List<Tache> findTachesPlanifiees(int projetId);
}
