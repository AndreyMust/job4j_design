package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class StatementDemo {

    private static Properties loadProperties() throws IOException {
            InputStream is = StatementDemo.class.getResourceAsStream("/app.properties");
            Properties props = new Properties();
            props.load(is);
            return props;
    }

    private static Connection getConnection() throws Exception {
        Properties props = loadProperties();
        Class.forName("org.postgresql.Driver");
        String url = props.getProperty("jdbc.url");
        String login = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        return DriverManager.getConnection(url, login, password);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public void load() {
        /* try (InputStream is = this.getClass().getResourceAsStream("/app.properties")) { */
        StatementDemo.class.getResourceAsStream("/app.properties\"");
        try (InputStream is = this.getClass().getResourceAsStream("/app.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            String serverPort = properties.getProperty("jdbc.username");
            System.out.println(serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void load2() {
        try (InputStream is = StatementDemo.class.getResourceAsStream("/app.properties")) {
            Properties props = new Properties();
            props.load(is);
            String serverPort = props.getProperty("jdbc.username");
            System.out.println(serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "create table if not exists demo_table(%s, %s);",
                        "id serial primary key",
                        "name text"
                );
                statement.execute(sql);
                System.out.println(getTableScheme(connection, "demo_table"));
            }
        }
    }
}
