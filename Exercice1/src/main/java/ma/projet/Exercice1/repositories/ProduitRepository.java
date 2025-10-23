package ma.projet.Exercice1.repositories;

import ma.projet.Exercice1.classes.Categorie;
import ma.projet.Exercice1.classes.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {
    List<Produit> findByCategorie(Categorie c);
    List<Produit> findByPrixGreaterThan(double prix);
}
