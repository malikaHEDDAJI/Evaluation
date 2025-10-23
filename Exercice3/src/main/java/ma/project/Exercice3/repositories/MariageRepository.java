package ma.project.Exercice3.repositories;

import ma.project.Exercice3.beans.Mariage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MariageRepository extends JpaRepository<Mariage, Integer> {
    List<Mariage> findByHommeIdOrderByDateDebut(int hommeId);
}
