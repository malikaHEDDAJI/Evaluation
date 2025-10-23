package ma.projet.services;

import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

public class HommeService implements IDao<Homme> {

    @Override
    public void create(Homme o) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.save(o);
            t.commit();
        }
    }

    @Override
    public Homme findById(int id) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(Homme.class, id);
        }
    }

    @Override
    public List<Homme> findAll() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from Homme", Homme.class).list();
        }
    }

    @Override
    public void update(Homme o) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.update(o);
            t.commit();
        }
    }

    @Override
    public void delete(Homme o) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.delete(o);
            t.commit();
        }
    }

    public List<Homme> hommesMarieAQuatreFemmesEntreDates(Date debut, Date fin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Homme> cq = cb.createQuery(Homme.class);
            Root<Mariage> mariageRoot = cq.from(Mariage.class);
            Join<Mariage, Homme> hommeJoin = mariageRoot.join("homme");

            Predicate conditionDate = cb.between(mariageRoot.get("dateDebut"), debut, fin);

            cq.select(hommeJoin)
                    .where(conditionDate)
                    .groupBy(hommeJoin.get("id"))
                    .having(cb.equal(cb.count(mariageRoot.get("femme")), 4));

            return session.createQuery(cq).getResultList();
        }
    }
}
