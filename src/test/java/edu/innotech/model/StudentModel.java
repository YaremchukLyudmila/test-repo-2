package edu.innotech.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class StudentModel {
    private Long id;
    private String name;
    private List<Integer> marks;

    public StudentModel() {
    }

    public StudentModel(String name, List<Integer> marks) {
        this.name = name;
        this.marks = marks;
    }
}
