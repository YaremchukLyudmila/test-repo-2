package edu.innotech;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class StudentTest {

    @Test
    public void encapsulationTest() {
        StudentOld student = new StudentOld("Людмила");
        student.addGrade(4);
        student.addGrade(5);
        Assertions.assertEquals(student.getGrades(), List.of(4 ,5));
        student.getGrades().add(3);
        Assertions.assertEquals(student.getGrades(), List.of(4 ,5));
    }

    @Test
    public void name() {
        StudentOld student = new StudentOld("Людмила");
        Assertions.assertEquals(student.getName(), "Людмила");
        student.setName("Галина");
        Assertions.assertEquals(student.getName(), "Галина");
    }

    @Test
    public void eqAndHashCode() {
        StudentOld student1 = new StudentOld("Людмила");
        StudentOld student2 = new StudentOld("Людмила");
        student1.addGrade(4);
        student2.addGrade(4);
        Assertions.assertEquals(student1, student2);
        Assertions.assertEquals(student1.hashCode(), student2.hashCode());
        Assertions.assertEquals(student1.toString(), student2.toString());
        student2.addGrade(5);
        Assertions.assertNotEquals(student1, student2);
        Assertions.assertNotEquals(student1.hashCode(), student2.hashCode());
        Assertions.assertNotEquals(student1.toString(), student2.toString());

        student1.addGrade(5);
        Assertions.assertEquals(student1, student2);
        Assertions.assertEquals(student1.hashCode(), student2.hashCode());
        Assertions.assertEquals(student1.toString(), student2.toString());
        student1.setName("qwer");
        Assertions.assertNotEquals(student1, student2);
        Assertions.assertNotEquals(student1.hashCode(), student2.hashCode());
        Assertions.assertNotEquals(student1.toString(), student2.toString());
    }

    @Test
    public void gradesValidation() {
        StudentOld student1 = new StudentOld("Людмила");
        try {
            student1.addGrade(6);
            Assertions.fail();
        } catch (Exception e) {
        }
        try {
            student1.addGrade(1);
            Assertions.fail();
        } catch (Exception e) {
        }
    }
}
