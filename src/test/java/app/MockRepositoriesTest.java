package app;

import domain.Student;
import domain.Tema;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class MockRepositoriesTest {

    @Mock
    private StudentXMLRepo studentFileRepository;
    @Mock
    private TemaXMLRepo temaFileRepository;

    private Service service;

    @Before
    public void setup(){
        studentFileRepository = mock(StudentXMLRepo.class);
        temaFileRepository = mock(TemaXMLRepo.class);
        service = new Service(studentFileRepository, new StudentValidator(), temaFileRepository,
                new TemaValidator(), new NotaXMLRepo("fisiere-test/Note.xml"),
                new NotaValidator(studentFileRepository, temaFileRepository));

    }

    @Test
    public void testMockedServices(){
        Student stud1 = new Student("200", "abc", 200, "abc@a.com");
        Tema tema1 = new Tema("200", "tema de test", 2, 1);
        try{
            when(studentFileRepository.save(stud1)).thenReturn(stud1);
            when(temaFileRepository.save(tema1)).thenReturn(tema1);
            when(studentFileRepository.findOne("200")).thenReturn(stud1);
            when(temaFileRepository.findOne("200")).thenReturn(tema1);
            when(studentFileRepository.findAll()).thenReturn(List.of(stud1));
            when(temaFileRepository.findAll()).thenReturn(List.of(tema1));
            when(studentFileRepository.delete("200")).thenReturn(stud1);
            when(temaFileRepository.delete("200")).thenReturn(tema1);
        }catch(Exception e){
            e.printStackTrace();
        }
        service.addStudent(stud1);
        service.addTema(tema1);
        assert (service.findStudent("200").equals(stud1));
        assert (service.findTema("200").equals(tema1));
        List<Student> l1 = (List<Student>) service.getAllStudenti();
        List<Tema> l2 = (List<Tema>) service.getAllTeme();

        assert(l1.size() == 1 && l2.size() == 1);

        assert (service.deleteStudent("200").equals(stud1));
        assert (service.deleteTema("200").equals(tema1));
    }


}
