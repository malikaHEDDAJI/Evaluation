package ma.projet.Exercice1.repositories;

import ma.projet.Exercice1.classes.Commande;
import ma.projet.Exercice1.classes.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Integer> {
    List<LigneCommande> findByCommande(Commande c);
    List<LigneCommande> findByCommande_DateCommandeBetween(LocalDate start, LocalDate end);
}