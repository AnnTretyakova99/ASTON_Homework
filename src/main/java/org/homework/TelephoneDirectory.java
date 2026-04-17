package org.homework;
import java.util.*;

public class TelephoneDirectory {
    private Map<String, List<String>> directory = new HashMap<>();

    public void add(String surname, String phone) {
        directory.computeIfAbsent(surname, k -> new ArrayList<>()).add(phone);
    }

    public List<String> get(String surname) {
        return directory.getOrDefault(surname, Collections.emptyList());
    }

    public static void main(String[] args) {
        TelephoneDirectory pb = new TelephoneDirectory();
        pb.add("Бобров", "8-902-145-33-41");
        pb.add("Светов", "8-912-557-23-48");
        pb.add("Иванов", "8-915-346-11-30");
        pb.add("Петров", "8-981-432-66-93");

        System.out.println("Номер Боброва: " + pb.get("Бобров"));
        System.out.println("Номер Светова: " + pb.get("Светов"));
        System.out.println("Номер Иванова: " + pb.get("Иванов") );
        System.out.println("Номер Петрова: " + pb.get("Петров"));
    }
}
