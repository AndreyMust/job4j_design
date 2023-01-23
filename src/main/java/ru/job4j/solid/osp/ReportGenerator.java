package ru.job4j.solid.osp;

import java.util.List;

/*
 * Этот метод нужно модифировть всякий раз, когда добавляются новые типы отчета
 * Это нарущает принцип открытости закрытости
 */
public class ReportGenerator {
    public String generateReport(String type, List<String> data) {
        String report = "";
        if (type.equals("csv")) {
            report = "Name, Age, Salary\n";
            for (String item : data) {
                String[] values = item.split(",");
                report += values[0] + "," + values[1] + "," + values[2] + "\n";
            }
        } else if (type.equals("json")) {
            report = "{\"data\":[";
            for (String item : data) {
                String[] values = item.split(",");
                report += "{\"name\":\"" + values[0] + "\",\"age\":\"" + values[1] + "\",\"salary\":\"" + values[2] + "\"},";
            }
            report = report.substring(0, report.length() - 1);
            report += "]}";
        } else {
            throw new IllegalArgumentException("Invalid type");
        }
        return report;
    }
}
