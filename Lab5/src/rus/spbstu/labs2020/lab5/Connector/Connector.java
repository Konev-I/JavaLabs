package rus.spbstu.labs2020.lab5.Connector;

import java.sql.*;
import java.util.Scanner;

public class Connector {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static void setTable() {
        try(Connection connection = DriverManager.getConnection(URL, USER, PASS);
            Statement statement = connection.createStatement())
        {
            System.out.println("Введите начальное количество товаров:");
            Scanner scanner = new Scanner(System.in);
            int num = scanner.nextInt();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS products (id INT NOT NULL AUTO_INCREMENT, prodid INT NOT NULL, title VARCHAR(30) NOT NULL, cost INT NOT NULL, PRIMARY KEY (id, prodid, title))");
            statement.executeUpdate("TRUNCATE TABLE products");
            for(int i = 1; i <= num; i++)
            {
                statement.executeUpdate("insert into products (prodid, title, cost) " +
                        "values('" + i + "', 'Product" + i + "', '" + i * 10 + "')");
            }

        }
        catch (SQLException e)
        {
            System.out.println("Something went wrong...");
        }
    }
}