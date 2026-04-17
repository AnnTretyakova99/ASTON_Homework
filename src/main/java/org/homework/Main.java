package org.homework;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        StudentManagement management = new StudentManagement();
        List<Student> studentList = new ArrayList<>();

        studentList.add(new Student("Коля", "А-1", 1, Arrays.asList(4, 5, 4)));
        studentList.add(new Student("Женя", "Б-2", 2, Arrays.asList(3, 3, 2)));
        studentList.add(new Student("Маргарита", "В-3", 3, Arrays.asList(4, 5, 5)));
        studentList.add(new Student("Сергей", "Г-4", 3, Arrays.asList(3, 3, 4)));


        management.removeLowGradeStudents(studentList);
        management.promoteStudents(studentList);
        Set<Student> studentSet = new HashSet<>(studentList);
        management.printStudents(studentSet, 4);
    }
}