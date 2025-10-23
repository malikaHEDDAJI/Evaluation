import ma.projet.classes.Employe;
import ma.projet.classes.EmployeTache;
import ma.projet.classes.Tache;
import ma.projet.services.EmployeService;
import ma.projet.services.EmployeTacheService;
import ma.projet.services.TacheService;
import org.junit.jupiter.api.*;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeServiceTest {

    private static EmployeService employeService;
    private static EmployeTacheService employeTacheService;
    private static TacheService tacheService;

    private static Employe employeTest;
    private static Tache tache1, tache2;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @BeforeAll
    public static void setup() throws Exception {
        employeService = new EmployeService();
        employeTacheService = new EmployeTacheService();
        tacheService = new TacheService();

        employeTest = new Employe("Heddaji", "Malika");
        employeService.create(employeTest);

        tache1 = new Tache("Analyse", 1500, sdf.parse("10/02/2013"), sdf.parse("20/02/2013"), null);
        tache2 = new Tache("Conception", 1200, sdf.parse("10/03/2013"), sdf.parse("15/03/2013"), null);
        tacheService.create(tache1);
        tacheService.create(tache2);

        employeTacheService.create(new EmployeTache(employeTest, tache1, sdf.parse("11/02/2013"), sdf.parse("20/02/2013")));
        employeTacheService.create(new EmployeTache(employeTest, tache2, sdf.parse("11/03/2013"), sdf.parse("14/03/2013")));
    }


}
