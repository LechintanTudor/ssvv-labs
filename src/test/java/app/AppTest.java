package app;

import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.Test;
import service.Service;
import validation.ValidationException;

import static app.Common.createService;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test
    void addValidStudent() {
        var service = createService();
        var student = new Student("1", "George", 100, "george@gmail.com");
        service.addStudent(student);
    }

    @Test
    void tryToAddStudentWithInvalidId() {
        var service = createService();
        assertInvalidStudentCannotBeAdded(service, new Student("", "George", 100, "george@gmail.com"));
        assertInvalidStudentCannotBeAdded(service, new Student(null, "George", 100, "george@gmail.com"));
    }

    @Test
    void tryToAddStudentWithInvalidName() {
        var service = createService();
        assertInvalidStudentCannotBeAdded(service, new Student("1", "", 100, "george@gmail.com"));
        assertInvalidStudentCannotBeAdded(service, new Student("1", null, 100, "george@gmail.com"));
    }

    @Test
    void tryToAddStudentWithInvalidGroup() {
        var service = createService();
        assertInvalidStudentCannotBeAdded(service, new Student("1", "George", -1, "george@gmail.com"));
    }

    @Test
    void tryToAddStudentWithInvalidEmail() {
        var service = createService();
        assertInvalidStudentCannotBeAdded(service, new Student("1", "George", 1, ""));
        assertInvalidStudentCannotBeAdded(service, new Student("1", "George", 1, null));
    }

    @Test
    void addValidTema() {
        var service = createService();
        service.addTema(new Tema("1", "Desc", 1, 1));
    }

    @Test
    void tryToAddTemaWithInvalidId() {
        var service = createService();
        assertInvalidTemaCannotBeAdded(service, new Tema(null, "Desc", 1, 1));
        assertInvalidTemaCannotBeAdded(service, new Tema("", "Desc", 1, 1));
    }

    @Test
    void tryToAddTemaWithInvalidDescription() {
        var service = createService();
        assertInvalidTemaCannotBeAdded(service, new Tema("1", "", 1, 1));
    }

    @Test
    void tryToAddTemaWithInvalidDeadline() {
        var service = createService();
        assertInvalidTemaCannotBeAdded(service, new Tema("1", "Desc", 0, 1));
        assertInvalidTemaCannotBeAdded(service, new Tema("1", "Desc", 15, 1));
    }

    void assertInvalidStudentCannotBeAdded(Service service, Student student) {
        assertThrows(ValidationException.class, () -> service.addStudent(student));
    }

    void assertInvalidTemaCannotBeAdded(Service service, Tema tema) {
        assertThrows(ValidationException.class, () -> service.addTema(tema));
    }
}
