package ma.projet.services;

import ma.projet.classes.*;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ProjetService implements IDao<Projet> {

    @Override
    public boolean create(Projet o) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            session.save(o);
            t.commit();
            return true;
        }
    }

    @Override
    public boolean update(Projet o) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            session.update(o);
            t.commit();
            return true;
        }
    }

    @Override
    public boolean delete(Projet o) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            session.delete(o);
            t.commit();
            return true;
        }
    }

    @Override
    public Projet getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Projet.class, id);
        }
    }

    @Override
    public List<Projet> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Projet", Projet.class).list();
        }
    }

    // 🔸 Liste des tâches planifiées d’un projet
    public List<Tache> getTachesPlanifiees(int projetId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM Tache t WHERE t.projet.id = :id", Tache.class)
                    .setParameter("id", projetId)
                    .list();
        }
    }

    // 🔸 Liste des tâches réalisées (avec dates réelles)
    public List<Tache> getTachesRealisees(int projetId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM Tache t WHERE t.projet.id = :id AND t.dateFinReelle IS NOT NULL",
                            Tache.class)
                    .setParameter("id", projetId)
                    .list();
        }
    }
}
