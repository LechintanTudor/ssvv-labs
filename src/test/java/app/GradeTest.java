package app;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.Test;
import validation.ValidationException;

import java.time.LocalDate;

import static app.Common.createService;
import static org.junit.jupiter.api.Assertions.*;

public class GradeTest {
    @Test
    void testAddStudent() {
        var service = createService();
        service.addStudent(new Student("100", "George", 100, "george@gmail.com"));
        assertNotNull(service.findStudent("100"));
        assertThrows(ValidationException.class, () -> service.addStudent(new Student(null, null, 100,  null)));
    }

    @Test
    void testAddAssignment() {
        var service = createService();
        service.addTema(new Tema("100", "Tema", 10, 1));
        assertNotNull(service.findTema("100"));
        assertThrows(ValidationException.class, () -> service.addTema(new Tema(null, null, 0, 0)));
    }

    @Test
    void testAddGrade() {
        var service = createService();
        service.addStudent(new Student("101", "George", 100, "george@gmail.com"));
        service.addTema(new Tema("101", "Tema", 1, 1));
        service.addNota(new Nota("101", "101", "101", 10, LocalDate.of(2023, 4, 15)), "feedback");
        assertNotNull(service.findNota("101"));
    }

    @Test
    void testAll() {
        var service = createService();

        service.addStudent(new Student("101", "George", 100, "george@gmail.com"));
        assertNotNull(service.findStudent("101"));

        service.addTema(new Tema("101", "Tema", 1, 1));
        assertNotNull(service.findTema("101"));

        service.addNota(new Nota("101", "101", "101", 10, LocalDate.of(2023, 4, 15)), "feedback");
        assertNotNull(service.findNota("101"));
    }
}
