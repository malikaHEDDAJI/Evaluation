package ma.project.Exercice2;

import ma.project.Exercice2.classes.Employe;
import ma.project.Exercice2.classes.EmployeTache;
import ma.project.Exercice2.classes.Tache;
import ma.project.Exercice2.services.EmployeService;
import ma.project.Exercice2.services.EmployeTacheService;
import ma.project.Exercice2.services.TacheService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeServiceTest {

    @Autowired
    private EmployeService employeService;

    @Autowired
    private EmployeTacheService employeTacheService;

    @Autowired
    private TacheService tacheService;

    private static Employe employeTest;
    private static Tache tache1, tache2;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @BeforeAll
    public static void setup(@Autowired EmployeService employeService,
                             @Autowired EmployeTacheService employeTacheService,
                             @Autowired TacheService tacheService) throws Exception {

        employeTest = new Employe("Heddaji", "Malika");
        employeService.create(employeTest);

        tache1 = new Tache("Analyse", 1500, sdf.parse("10/02/2013"), sdf.parse("20/02/2013"), null);
        tache2 = new Tache("Conception", 1200, sdf.parse("10/03/2013"), sdf.parse("15/03/2013"), null);
        tacheService.create(tache1);
        tacheService.create(tache2);

        employeTacheService.create(new EmployeTache(employeTest, tache1, sdf.parse("11/02/2013"), sdf.parse("20/02/2013")));
        employeTacheService.create(new EmployeTache(employeTest, tache2, sdf.parse("11/03/2013"), sdf.parse("14/03/2013")));
    }

    @Test
    @Order(1)
    public void testGetTachesByEmploye() {
        List<EmployeTache> taches = employeTacheService.getByEmploye(employeTest.getId());
        assertThat(taches).hasSize(2);
    }

}
