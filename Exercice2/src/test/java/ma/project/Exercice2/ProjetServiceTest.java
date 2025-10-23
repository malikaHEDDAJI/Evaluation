package ma.project.Exercice2;

import ma.project.Exercice2.classes.Projet;
import ma.project.Exercice2.classes.Tache;
import ma.project.Exercice2.services.ProjetService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjetServiceTest {

    @Autowired
    private ProjetService projetService;

    private static Projet projetTest;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @BeforeAll
    public static void init(@Autowired ProjetService projetService) throws Exception {
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
    public static void cleanup(@Autowired ProjetService projetService) {
        projetService.delete(projetTest.getId());
    }
}
