package ma.projet.services;

import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Date;
import java.util.List;

public class TacheService implements IDao<Tache> {

    @Override
    public boolean create(Tache o) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            session.save(o);
            t.commit();
            return true;
        }
    }

    @Override
    public boolean update(Tache o) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            session.update(o);
            t.commit();
            return true;
        }
    }

    @Override
    public boolean delete(Tache o) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            session.delete(o);
            t.commit();
            return true;
        }
    }

    @Override
    public Tache getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Tache.class, id);
        }
    }

    @Override
    public List<Tache> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Tache", Tache.class).list();
        }
    }

    // 🔸 Tâches dont le prix > 1000
    public List<Tache> getTachesPrixSup1000() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createNamedQuery("Tache.findPrixSuperieur", Tache.class)
                    .setParameter("prixMin", 1000.0)
                    .list();
        }
    }

    // 🔸 Tâches entre deux dates
    public List<Tache> getTachesEntreDates(Date debut, Date fin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM Tache t WHERE t.dateDebutReelle BETWEEN :d1 AND :d2", Tache.class)
                    .setParameter("d1", debut)
                    .setParameter("d2", fin)
                    .list();
        }
    }
}
