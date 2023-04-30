package app;


import curent.Curent;
import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static app.Common.createService;
import static org.junit.jupiter.api.Assertions.*;

class IncrementalIntegration {
    @Test
    void testAddStudent(){
        var service = createService();
        var student = new Student("1", "George", 100, "george@gmail.com");
        service.addStudent(student);
    }

    @Test
    void testIntegrateAddTema(){
        var service = createService();
        service.addTema(new Tema("1", "Desc", 2, 1));
    }

    @Test
    void testIntegrateAddNota(){
        var service = createService();
        service.addStudent(new Student("101", "George", 100, "george@gmail.com"));
        service.addTema(new Tema("101", "Tema", 1, 1));
        service.addNota(new Nota("101", "101", "101", 10, LocalDate.of(2023, 4, 15)), "feedback");
        assertNotNull(service.findNota("101"));
    }
}
