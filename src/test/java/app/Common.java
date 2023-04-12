package app;

import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

public class Common {
    public static Service createService() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();

        String filenameStudent = "fisiere-test/Studenti.xml";
        String filenameTema = "fisiere-test/Teme.xml";
        String filenameNota = "fisiere-test/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);

        return new Service(
            studentXMLRepository,
            studentValidator,
            temaXMLRepository,
            temaValidator,
            notaXMLRepository,
            notaValidator
        );
    }
}
