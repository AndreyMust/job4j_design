package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TableEditor implements AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(TableEditor.class.getName());
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("jdbc.driver"));
        String url = properties.getProperty("jdbc.url");
        String login = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        connection = DriverManager.getConnection(url, login, password);
        LOG.info("initConnection: ok");
    }

    private void runSQL(String querySQL, String tableName) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(querySQL);
        } catch (SQLException throwables) {
            LOG.error(throwables);
        }
    }

    public void createTable(String tableName) {
        runSQL(String.format("CREATE TABLE if not exists %s();", tableName), tableName);
        LOG.info("createTable: ok");
    }

    public void dropTable(String tableName) {
        runSQL(String.format("DROP TABLE if exists %s;", tableName), tableName);
        LOG.info("dropTable: ok");
    }

    public void addColumn(String tableName, String columnName, String type) {
        runSQL(String.format("ALTER TABLE %s ADD column if not exists %s %s;",
                tableName, columnName, type), tableName);
        LOG.info("addColumn: ok");
    }

    public void dropColumn(String tableName, String columnName) {
        runSQL(String.format("ALTER TABLE %s DROP column %s;",
                tableName, columnName), tableName);
        LOG.info("dropColumn: ok");
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        runSQL(String.format("ALTER TABLE %s RENAME column %s to %s;",
                tableName, columnName, newColumnName), tableName);
        LOG.info("renameColumn: ok");
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

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        String tableName = "user_table";
        String columnName = "column_one";
        String dataType = "varchar(50)";
        String newColumnName = "column_two";

        Properties properties = new Properties();
        try (InputStream is = TableEditor.class.getResourceAsStream("/app.properties")) {
            properties.load(is);

            try (TableEditor table = new TableEditor(properties)) {
                table.createTable(tableName);
                table.addColumn(tableName, columnName, dataType);
                table.renameColumn(tableName, columnName, newColumnName);
                table.dropColumn(tableName, newColumnName);
                table.dropTable(tableName);
            }
        }
    }
}
