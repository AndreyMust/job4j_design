package ru.job4j.ood.srp;

import java.util.Calendar;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.List;

public class ReportForHR implements Report {

    private Store store;

    public ReportForHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;").append(System.lineSeparator());
        List<Employee>  employees = store.findBy(filter)
                                    .stream()
                                    .sorted(Comparator.comparing(Employee::getSalary))
                                    .collect(Collectors.toList());

        employees.forEach(e -> text.append(e.getName()).append("; ")
                                    .append(e.getSalary()).append(";")
                                    .append(System.lineSeparator())
                            );
        return text.toString();
    }

    public static void main(String[] args) {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportForHR(store);
        System.out.println(engine.generate(em -> true));
    }
}
