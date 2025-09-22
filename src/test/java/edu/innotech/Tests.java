package edu.innotech;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

public class Tests {
    @Test
    public void testRating(){
        Student stud = new Student("vasia");
        stud.addGrade(4);
        StudentRepo repo=Mockito.mock(StudentRepo.class);
//        stud.setRepo(repo);
//
//        Assertions.assertEquals(stud.raiting(),10);

    }

    @RepeatedTest(value = 4,name ="корректные оценки добавляются в список")
    public void gradesInRange(RepetitionInfo repetitionInfo) {
        Student stud = new Student("vasia");
        int num=repetitionInfo.getCurrentRepetition()+1;
        stud.addGrade(num);
        Assertions.assertEquals(stud.getGrades().get(0),num);
    }

    @ParameterizedTest(name ="добавление некорректных оценок кидает исключение")
    @MethodSource("edu.innotech.MarksGenerator#ints")
    public void gradesNotInRange(int x) {
        Student stud = new Student("vasia");
        Assertions.assertThrows(IllegalArgumentException.class, () -> stud.addGrade(x));
    }
}




