package ma.projet.Exercice1.service;

import ma.projet.Exercice1.classes.Categorie;

import ma.projet.Exercice1.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {
    @Autowired
    private CategorieRepository categorieRepository;
    public Categorie save(Categorie c) { return categorieRepository.save(c); }
    public List<Categorie> findAll() { return categorieRepository.findAll(); }
}

