package ma.project.Exercice3;

import ma.project.Exercice3.beans.*;
import ma.project.Exercice3.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Exercice3Application {

	public static void main(String[] args) {
		SpringApplication.run(Exercice3Application.class, args);
	}

	@Bean
	CommandLineRunner run(@Autowired HommeService hommeService,
						  @Autowired FemmeService femmeService,
						  @Autowired MariageService mariageService) {
		return args -> {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			List<Mariage> mariagesH1 = new ArrayList<>();
			Homme h1 = new Homme("SAFI", "SAID", "0600000000", "Rabat", sdf.parse("01/01/1960"), mariagesH1);
			hommeService.create(h1);

			Femme f1 = new Femme("RAMI", "SALIMA", "0611111111", "Casablanca", sdf.parse("02/02/1965"));
			Femme f2 = new Femme("ALI", "AMAL", "0622222222", "Marrakech", sdf.parse("05/05/1970"));
			Femme f3 = new Femme("ALAOUI", "WAFA", "0633333333", "Fès", sdf.parse("01/03/1975"));
			Femme f4 = new Femme("ALAMI", "KARIMA", "0644444444", "Tanger", sdf.parse("10/10/1980"));
			femmeService.create(f1);
			femmeService.create(f2);
			femmeService.create(f3);
			femmeService.create(f4);

			Mariage m1 = new Mariage(h1, f1, sdf.parse("03/09/1990"), null, 4);
			Mariage m2 = new Mariage(h1, f2, sdf.parse("03/09/1995"), null, 2);
			Mariage m3 = new Mariage(h1, f3, sdf.parse("04/11/2000"), null, 3);
			Mariage m4 = new Mariage(h1, f4, sdf.parse("03/09/1989"), sdf.parse("03/09/1990"), 0);

			mariageService.create(m1);
			mariageService.create(m2);
			mariageService.create(m3);
			mariageService.create(m4);

			mariagesH1.add(m1);
			mariagesH1.add(m2);
			mariagesH1.add(m3);
			mariagesH1.add(m4);

			mariageService.afficherDetailsMariagesHomme(h1.getId());

			System.out.println("\nFemmes mariées deux fois ou plus :");
			femmeService.femmesMarieesDeuxFoisOuPlus().forEach(
					f -> System.out.println(f.getNom() + " " + f.getPrenom())
			);
		};
	}
}
