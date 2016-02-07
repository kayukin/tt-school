package net.thumbtack.jdbc;

/**
 * Created by Konstantin on 07.02.2016.
 */
public class JdbcMain {
    public static void main(String[] args) {
        Jdbc jdbc = new Jdbc("jdbc:mysql://localhost/bookstore", "root", "root");
        jdbc.PrintQueryResult("SELECT * FROM books");
        jdbc.PrintQueryResult("SELECT * FROM books LIMIT 5;");
    }
}
