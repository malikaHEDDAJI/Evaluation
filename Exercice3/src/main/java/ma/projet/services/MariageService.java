package ma.projet.services;

import ma.projet.beans.Mariage;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.SimpleDateFormat;
import java.util.List;

public class MariageService implements IDao<Mariage> {

    @Override
    public void create(Mariage o) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.save(o);
            t.commit();
        }
    }

    @Override
    public Mariage findById(int id) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(Mariage.class, id);
        }
    }

    @Override
    public List<Mariage> findAll() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from Mariage", Mariage.class).list();
        }
    }

    @Override
    public void update(Mariage o) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.update(o);
            t.commit();
        }
    }

    @Override
    public void delete(Mariage o) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.delete(o);
            t.commit();
        }
    }

    public void afficherDetailsMariagesHomme(int hommeId) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            String nomHomme = s.createQuery(
                            "SELECT h.nom || ' ' || h.prenom FROM Homme h WHERE h.id = :hid", String.class)
                    .setParameter("hid", hommeId)
                    .uniqueResult();

            if (nomHomme == null) {
                System.out.println("Aucun homme trouvé avec l'ID : " + hommeId);
                return;
            }

            List<Mariage> mariages = s.createQuery(
                            "FROM Mariage m WHERE m.homme.id = :hid ORDER BY m.dateDebut",
                            Mariage.class)
                    .setParameter("hid", hommeId)
                    .list();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            System.out.println("Nom : " + nomHomme);

            List<Mariage> mariagesEnCours = mariages.stream()
                    .filter(m -> m.getDateFin() == null)
                    .toList();

            if (!mariagesEnCours.isEmpty()) {
                System.out.println("Mariages En Cours :");
                for (int i = 0; i < mariagesEnCours.size(); i++) {
                    Mariage m = mariagesEnCours.get(i);
                    System.out.printf("%d. Femme : %s %s   Date Début : %s    Nbr Enfants : %d%n",
                            i + 1,
                            m.getFemme().getNom(),
                            m.getFemme().getPrenom(),
                            sdf.format(m.getDateDebut()),
                            m.getNbrEnfant());
                }
            }

            List<Mariage> mariagesEchoues = mariages.stream()
                    .filter(m -> m.getDateFin() != null)
                    .toList();

            if (!mariagesEchoues.isEmpty()) {
                System.out.println("\nMariages échoués :");
                for (int i = 0; i < mariagesEchoues.size(); i++) {
                    Mariage m = mariagesEchoues.get(i);
                    System.out.printf("%d. Femme : %s %s  Date Début : %s    Date Fin : %s    Nbr Enfants : %d%n",
                            i + 1,
                            m.getFemme().getNom(),
                            m.getFemme().getPrenom(),
                            sdf.format(m.getDateDebut()),
                            sdf.format(m.getDateFin()),
                            m.getNbrEnfant());
                }
            }

            if (mariagesEnCours.isEmpty() && mariagesEchoues.isEmpty()) {
                System.out.println("Aucun mariage trouvé.");
            }
        }
    }
}
