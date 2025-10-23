package ma.projet.Exercice1.service;

import ma.projet.Exercice1.classes.Commande;
import ma.projet.Exercice1.dao.IDao;
import ma.projet.Exercice1.repositories.CommandeRepository;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {
    @Autowired
    private CommandeRepository commandeRepository;
    public Commande save(Commande c) { return commandeRepository.save(c); }
    public List<Commande> findAll() { return commandeRepository.findAll(); }
}

