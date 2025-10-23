import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.services.TacheService;
import ma.projet.services.ProjetService;
import org.junit.jupiter.api.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TacheServiceTest {

    private static TacheService tacheService;
    private static ProjetService projetService;
    private static Tache tacheTest;
    private static Projet projet;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @BeforeAll
    public static void init() throws Exception {
        tacheService = new TacheService();
        projetService = new ProjetService();

        projet = new Projet("Projet TacheTest", sdf.parse("01/01/2024"));
        projetService.create(projet);

        tacheTest = new Tache("Analyse", 1500, sdf.parse("10/01/2024"), sdf.parse("15/01/2024"), projet);
        tacheService.create(tacheTest);
    }

    @Test
    @Order(1)
    public void testCreateTache() {
        assertThat(tacheTest.getId()).isNotNull();
    }

    @Test
    @Order(2)
    public void testTachesPrixSup1000() {
        List<Tache> taches = tacheService.getTachesPrixSup1000();
        assertThat(taches).extracting(Tache::getPrix).allMatch(p -> p > 1000);
    }

    @Test
    @Order(3)
    public void testTachesEntreDates() throws Exception {
        Date d1 = sdf.parse("01/01/2024");
        Date d2 = sdf.parse("31/01/2024");
        List<Tache> taches = tacheService.getTachesEntreDates(d1, d2);
        assertThat(taches).isNotEmpty();
    }

    @AfterAll
    public static void cleanup() {
        tacheService.delete(tacheTest);
        projetService.delete(projet);
    }
}
