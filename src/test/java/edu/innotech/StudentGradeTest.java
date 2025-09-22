package edu.innotech;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class StudentGradeTest {

    @Test
    public void gradeValidation() {
        CheckGradeService checkGradeService = Mockito.mock(CheckGradeService.class);
        Mockito.when(checkGradeService.checkGrade(Mockito.anyInt()))
                .then(args -> {
                    Integer grade = args.getArgument(0);
                    return grade >= 2 && grade <= 5;
                });
        Student student = new Student("Name");
        student.setCheckGradeService(checkGradeService);
        try {
            student.addGrade(1);
            Assertions.fail();
        } catch (Exception e) {}
        student.addGrade(2);
        student.addGrade(3);
        student.addGrade(4);
        student.addGrade(5);
        try {
            student.addGrade(6);
            Assertions.fail();
        } catch (Exception e) {}
        Assertions.assertEquals(List.of(2, 3, 4, 5), student.getGrades());
    }

}
