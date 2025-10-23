package ma.projet.Exercice1.service;

import ma.projet.Exercice1.classes.LigneCommande;
import ma.projet.Exercice1.dao.IDao;
import ma.projet.Exercice1.repositories.LigneCommandeRepository;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LigneCommandeService {
    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;
    public LigneCommande save(LigneCommande lc) { return ligneCommandeRepository.save(lc); }
    public List<LigneCommande> findAll() { return ligneCommandeRepository.findAll(); }
}
