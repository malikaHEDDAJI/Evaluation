package ma.projet.services;

import ma.projet.classes.EmployeTache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class EmployeTacheService implements IDao<EmployeTache> {
    @Override
    public boolean create(EmployeTache o) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            session.save(o);
            t.commit();
            return true;
        }
    }

    @Override
    public boolean update(EmployeTache o) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            session.update(o);
            t.commit();
            return true;
        }
    }

    @Override
    public boolean delete(EmployeTache o) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            session.delete(o);
            t.commit();
            return true;
        }
    }

    @Override
    public EmployeTache getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(EmployeTache.class, id);
        }
    }

    @Override
    public List<EmployeTache> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM EmployeTache", EmployeTache.class).list();
        }
    }

}
