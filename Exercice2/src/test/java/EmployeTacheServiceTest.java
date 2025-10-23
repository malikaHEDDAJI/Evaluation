import ma.projet.classes.*;
import ma.projet.services.*;
import org.junit.jupiter.api.*;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeTacheServiceTest {

    private static EmployeTacheService employeTacheService;
    private static EmployeService employeService;
    private static TacheService tacheService;
    private static ProjetService projetService;

    private static Employe employe;
    private static Projet projet;
    private static Tache tache;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @BeforeAll
    public static void init() throws Exception {
        employeTacheService = new EmployeTacheService();
        employeService = new EmployeService();
        projetService = new ProjetService();
        tacheService = new TacheService();

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

    @AfterAll
    public static void cleanup() {
        tacheService.delete(tache);
        projetService.delete(projet);
        employeService.delete(employe);
    }
}
