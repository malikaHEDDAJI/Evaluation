
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.services.ProjetService;
import org.junit.jupiter.api.*;
import java.text.SimpleDateFormat;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjetServiceTest {

    private static ProjetService projetService;
    private static Projet projetTest;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @BeforeAll
    public static void init() throws Exception {
        projetService = new ProjetService();
        projetTest = new Projet("Test Projet", sdf.parse("01/01/2020"));
        projetService.create(projetTest);
    }

    @Test
    @Order(1)
    public void testCreateProjet() {
        assertThat(projetTest.getId()).isNotNull();
    }

    @Test
    @Order(2)
    public void testGetTachesPlanifiees() {
        List<Tache> taches = projetService.getTachesPlanifiees(projetTest.getId());
        assertThat(taches).isNotNull();
    }

    @Test
    @Order(3)
    public void testGetTachesRealisees() {
        List<Tache> taches = projetService.getTachesRealisees(projetTest.getId());
        assertThat(taches).isNotNull();
    }

    @AfterAll
    public static void cleanup() {
        projetService.delete(projetTest);
    }
}
