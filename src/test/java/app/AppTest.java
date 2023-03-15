package app;

import domain.Student;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test
    void addValidStudent() {
        var service = createService();
        var student = new Student("1", "George", 100, "george@gmail.com");
        service.addStudent(student);
    }

    @Test
    void tryToAddInvalidStudent() {
        var service = createService();
        var student = new Student("", "George", 100, "george@gmail.com");
        assertThrows(ValidationException.class, () -> service.addStudent(student));
    }

    Service createService() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere-test/Studenti.xml";
        String filenameTema = "fisiere-test/Teme.xml";
        String filenameNota = "fisiere-test/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        return new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }
}
