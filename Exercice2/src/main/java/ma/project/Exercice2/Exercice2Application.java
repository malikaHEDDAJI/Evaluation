package ma.project.Exercice2;

import ma.project.Exercice2.classes.Employe;
import ma.project.Exercice2.classes.EmployeTache;
import ma.project.Exercice2.classes.Projet;
import ma.project.Exercice2.classes.Tache;
import ma.project.Exercice2.services.EmployeService;
import ma.project.Exercice2.services.EmployeTacheService;
import ma.project.Exercice2.services.ProjetService;
import ma.project.Exercice2.services.TacheService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootApplication
public class Exercice2Application {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(Exercice2Application.class, args);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		EmployeService es = context.getBean(EmployeService.class);
		ProjetService ps = context.getBean(ProjetService.class);
		TacheService ts = context.getBean(TacheService.class);
		EmployeTacheService ets = context.getBean(EmployeTacheService.class);

		Employe e1 = new Employe("Heddaji", "Malika", "8000");
		es.create(e1);

		Projet p1 = new Projet("Gestion de stock", sdf.parse("14/01/2013"), e1);
		ps.create(p1);

		Tache t1 = new Tache("Analyse", 1200, p1);
		t1.setDateDebutReelle(sdf.parse("10/02/2013"));
		t1.setDateFinReelle(sdf.parse("20/02/2013"));
		ts.create(t1);

		Tache t2 = new Tache("Conception", 1500, p1);
		t2.setDateDebutReelle(sdf.parse("10/03/2013"));
		t2.setDateFinReelle(sdf.parse("15/03/2013"));
		ts.create(t2);

		Tache t3 = new Tache("Développement", 2000, p1);
		t3.setDateDebutReelle(sdf.parse("10/04/2013"));
		t3.setDateFinReelle(sdf.parse("25/04/2013"));
		ts.create(t3);

		ets.create(new EmployeTache(e1, t1, t1.getDateDebutReelle(), t1.getDateFinReelle()));
		ets.create(new EmployeTache(e1, t2, t2.getDateDebutReelle(), t2.getDateFinReelle()));
		ets.create(new EmployeTache(e1, t3, t3.getDateDebutReelle(), t3.getDateFinReelle()));

		System.out.println("== Projets gérés par l’employé ==");
		List<Projet> projets = es.getProjetsByEmploye(e1.getId());
		for (Projet p : projets) {
			System.out.println("Projet : " + p.getNom() + " | Date début : " + sdf.format(p.getDateDebut()));
		}

		System.out.println("\n== Tâches du projet 'Gestion de stock' ==");
		List<Tache> tachesRealisees = ps.getTachesByProjet(p1.getId());
		for (Tache t : tachesRealisees) {
			System.out.println("Tâche : " + t.getNom()
					+ " | Début : " + sdf.format(t.getDateDebutReelle())
					+ " | Fin : " + sdf.format(t.getDateFinReelle()));
		}

		System.out.println("\n== Tâches coût > 1000 DH ==");
		List<Tache> tachesChere = ts.getTachesPrixSup1000();
		for (Tache t : tachesChere) {
			System.out.println(t.getNom() + " : " + t.getPrix() + " DH");
		}

		System.out.println("\n== Tâches réalisées par " + e1.getPrenom() + " ==");
		List<EmployeTache> employeTaches = ets.getByEmploye(e1.getId());
		for (EmployeTache et : employeTaches) {
			System.out.println("Tâche : " + et.getTache().getNom()
					+ " | Début réelle : " + sdf.format(et.getDateDebutReelle())
					+ " | Fin réelle : " + sdf.format(et.getDateFinReelle()));
		}
	}
}
