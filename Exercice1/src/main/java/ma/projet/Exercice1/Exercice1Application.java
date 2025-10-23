package ma.projet.Exercice1;

import ma.projet.Exercice1.classes.*;
import ma.projet.Exercice1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class Exercice1Application implements CommandLineRunner {

	@Autowired
	private CategorieService categorieService;
	@Autowired
	private ProduitService produitService;
	@Autowired
	private CommandeService commandeService;
	@Autowired
	private LigneCommandeService ligneCommandeService;

	public static void main(String[] args) {
		SpringApplication.run(Exercice1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categorie cat1 = new Categorie("Ordinateurs");
		categorieService.save(cat1);

		Produit p1 = new Produit("ES12", 120, 7, cat1);
		Produit p2 = new Produit("ZR85", 100, 14, cat1);
		Produit p3 = new Produit("EE85", 200, 5, cat1);
		produitService.save(p1);
		produitService.save(p2);
		produitService.save(p3);

		Commande cmd = new Commande(LocalDate.of(2013, 3, 14));
		commandeService.save(cmd);

		ligneCommandeService.save(new LigneCommande(p1, cmd, 7));
		ligneCommandeService.save(new LigneCommande(p2, cmd, 14));
		ligneCommandeService.save(new LigneCommande(p3, cmd, 5));

		System.out.println("Commande : " + cmd.getId() + "\tDate : " + cmd.getDateCommande());
		System.out.println("Liste des produits :");
		System.out.println("Référence\tPrix\tQuantité");

		List<Produit> produits = produitService.produitsCommande(cmd);
		produits.forEach(p ->
				System.out.println(p.getReference() + "\t" + p.getPrix() + " DH\t" + p.getQuantite()));
	}
}
