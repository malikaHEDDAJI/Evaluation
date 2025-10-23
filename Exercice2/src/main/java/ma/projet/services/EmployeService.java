package ma.projet.services;

import ma.projet.classes.*;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class EmployeService implements IDao<Employe> {

    @Override
    public boolean create(Employe o) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            session.save(o);
            t.commit();
            return true;
        }
    }

    @Override
    public boolean update(Employe o) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            session.update(o);
            t.commit();
            return true;
        }
    }

    @Override
    public boolean delete(Employe o) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            session.delete(o);
            t.commit();
            return true;
        }
    }

    @Override
    public Employe getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Employe.class, id);
        }
    }

    @Override
    public List<Employe> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Employe", Employe.class).list();
        }
    }

    // 🔸 Liste des tâches réalisées par un employé
    public List<Tache> getTachesRealisees(int employeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT et.tache FROM EmployeTache et WHERE et.employe.id = :id", Tache.class)
                    .setParameter("id", employeId)
                    .list();
        }
    }

    // 🔸 Liste des projets gérés par un employé
    public List<Projet> getProjetsGerés(int employeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM Projet p WHERE p.chefProjet.id = :id", Projet.class)
                    .setParameter("id", employeId)
                    .list();
        }
    }

    public List<EmployeTache> getTachesByEmploye(int employeId) {
        Transaction transaction = null;
        List<EmployeTache> taches = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            taches = session.createQuery(
                            "from EmployeTache et where et.employe.id = :empId", EmployeTache.class)
                    .setParameter("empId", employeId)
                    .getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

        return taches;
    }

    public List<Projet> getProjetsByEmploye(int employeId) {
        Transaction transaction = null;
        List<Projet> projets = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // HQL pour récupérer les projets où l'employé est chef de projet
            projets = session.createQuery(
                            "from Projet p where p.chefProjet.id = :empId", Projet.class)
                    .setParameter("empId", employeId)
                    .getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

        return projets;
    }

}
