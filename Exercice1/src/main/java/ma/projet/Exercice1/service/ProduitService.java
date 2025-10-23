package ma.projet.Exercice1.service;


import ma.projet.Exercice1.classes.Categorie;
import ma.projet.Exercice1.classes.Commande;
import ma.projet.Exercice1.classes.LigneCommande;
import ma.projet.Exercice1.classes.Produit;
import ma.projet.Exercice1.repositories.LigneCommandeRepository;
import ma.projet.Exercice1.repositories.ProduitRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    public Produit save(Produit p) { return produitRepository.save(p); }

    public List<Produit> findByCategorie(Categorie c) {
        return produitRepository.findByCategorie(c);
    }

    public List<Produit> findByPrixSup100() {
        return produitRepository.findByPrixGreaterThan(100);
    }

    public List<Produit> produitsCommandesEntreDeuxDates(LocalDate start, LocalDate end) {
        List<LigneCommande> lignes = ligneCommandeRepository.findByCommande_DateCommandeBetween(start, end);
        return lignes.stream().map(LigneCommande::getProduit).distinct().toList();
    }

    public List<Produit> produitsCommande(Commande c) {
        List<LigneCommande> lignes = ligneCommandeRepository.findByCommande(c);
        return lignes.stream().map(LigneCommande::getProduit).toList();
    }

}

