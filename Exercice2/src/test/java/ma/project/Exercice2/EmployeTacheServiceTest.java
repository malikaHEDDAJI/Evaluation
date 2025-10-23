package ma.project.Exercice2;

import ma.project.Exercice2.classes.Employe;
import ma.project.Exercice2.classes.EmployeTache;
import ma.project.Exercice2.classes.Projet;
import ma.project.Exercice2.classes.Tache;
import ma.project.Exercice2.services.EmployeService;
import ma.project.Exercice2.services.EmployeTacheService;
import ma.project.Exercice2.services.ProjetService;
import ma.project.Exercice2.services.TacheService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeTacheServiceTest {

    @Autowired
    private EmployeTacheService employeTacheService;

    @Autowired
    private EmployeService employeService;

    @Autowired
    private TacheService tacheService;

    @Autowired
    private ProjetService projetService;

    private static Employe employe;
    private static Projet projet;
    private static Tache tache;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @BeforeAll
    public static void init(@Autowired EmployeTacheService employeTacheService,
                            @Autowired EmployeService employeService,
                            @Autowired TacheService tacheService,
                            @Autowired ProjetService projetService) throws Exception {

        employe = new Employe("Heddaji", "Malika");
        employeService.create(employe);

        projet = new Projet("Test Projet ET", sdf.parse("01/02/2024"));
        projetService.create(projet);

        tache = new Tache("Développement", 2000, sdf.parse("10/02/2024"), sdf.parse("20/02/2024"), projet);
        tacheService.create(tache);
    }

    @Test
    @Order(1)
    public void testAffectationTacheEmploye() throws Exception {
        EmployeTache et = new EmployeTache(employe, tache, sdf.parse("10/02/2024"), sdf.parse("20/02/2024"));
        employeTacheService.create(et);
        assertThat(et.getId()).isNotNull();
    }

}
