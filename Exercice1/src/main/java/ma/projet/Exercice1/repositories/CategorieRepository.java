package ma.projet.Exercice1.repositories;

import ma.projet.Exercice1.classes.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {}
