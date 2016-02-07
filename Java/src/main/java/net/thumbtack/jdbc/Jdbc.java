package net.thumbtack.jdbc;

import java.sql.*;

/**
 * Created by Konstantin on 07.02.2016.
 */
public class Jdbc {
    private Connection connection;

    Jdbc(String url, String username, String password) {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeUpdate(String sql) {
        try (Statement statement = connection.createStatement()) {
            System.out.println(statement.executeUpdate(sql) + " row(s) affected");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void PrintQueryResult(String sql) {
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println(sql);
            int columnsNumber = rsmd.getColumnCount();
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",\t");
                System.out.print(rsmd.getColumnName(i));
            }
            System.out.println("");
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",\t");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue);
                }
                System.out.println("");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
