package org.homework;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        StudentManagement management = new StudentManagement();
        List<Student> studentList = new ArrayList<>();

        studentList.add(new Student("Коля", "А-1", 1, Arrays.asList(4, 5, 4)));
        studentList.add(new Student("Женя", "Б-2", 2, Arrays.asList(3, 3, 2)));
        studentList.add(new Student("Маргарита", "В-3", 3, Arrays.asList(4, 5, 5)));
        studentList.add(new Student("Сергей", "Г-4", 3, Arrays.asList(3, 3, 4)));


        management.processStudents(studentList);
        Set<Student> studentSet = new HashSet<>(studentList);
        management.printStudents(studentSet, 2);
    }
}