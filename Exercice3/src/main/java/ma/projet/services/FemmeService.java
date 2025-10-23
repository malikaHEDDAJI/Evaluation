package ma.projet.services;

import ma.projet.beans.Femme;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class FemmeService implements IDao<Femme> {

    @Override
    public void create(Femme o) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.save(o);
            t.commit();
        }
    }

    @Override
    public Femme findById(int id) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(Femme.class, id);
        }
    }

    @Override
    public List<Femme> findAll() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from Femme", Femme.class).list();
        }
    }

    @Override
    public void update(Femme o) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.update(o);
            t.commit();
        }
    }

    @Override
    public void delete(Femme o) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.delete(o);
            t.commit();
        }
    }

    public Long nombreEnfantsEntreDates(int idFemme, Date d1, Date d2) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Object res = s.createNativeQuery("Femme.nombreEnfantsEntreDates")
                    .setParameter("idFemme", idFemme)
                    .setParameter("d1", d1)
                    .setParameter("d2", d2)
                    .getSingleResult();
            return res == null ? 0L : ((Number) res).longValue();
        }
    }

    public List<Femme> femmesMarieesDeuxFoisOuPlus() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createNamedQuery("Femme.marieesDeuxFois", Femme.class).list();
        }
    }

}
