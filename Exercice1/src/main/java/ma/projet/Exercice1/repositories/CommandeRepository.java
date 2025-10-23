package ma.projet.Exercice1.repositories;

import ma.projet.Exercice1.classes.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {}
