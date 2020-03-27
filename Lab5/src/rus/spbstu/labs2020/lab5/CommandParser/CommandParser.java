package rus.spbstu.labs2020.lab5.CommandParser;

import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import java.sql.*;

public class CommandParser {
  Connection connection;
  Statement statement;

  public CommandParser(String URL, String USER, String PASS) throws SQLException
  {
    this.connection = DriverManager.getConnection(URL, USER, PASS);
    this.statement = connection.createStatement();
  }

  public void fillChoiceBox(ObservableList<String> names) throws SQLException {
    names.clear();
    ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
    while (resultSet.next())
    {
      names.add(resultSet.getString("title") + " ");
    }
  }

  public boolean productCheck(String name) throws SQLException {
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    String check = "SELECT * FROM products WHERE title = ?";
    preparedStatement = connection.prepareStatement(check);
    preparedStatement.setString(1, name);
    resultSet = preparedStatement.executeQuery();
    return resultSet.next();
  }

  public void commandAdd(String prodTitle, int prodPrice) throws SQLException
  {
    ResultSet resultSet = this.statement.executeQuery("SELECT id FROM products");
    int prodid = 0;
    while (resultSet.next())
    {
      prodid = resultSet.getInt("id");
    }
    prodid += 1;

    String sql = "INSERT INTO products (prodid, title, cost) Values (?, ?, ?)";
    PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
    preparedStatement.setInt(1, prodid);
    preparedStatement.setString(2, prodTitle);
    preparedStatement.setInt(3, prodPrice);
    preparedStatement.executeUpdate();
  }

  public void commandDelete(String prodTitle) throws SQLException
  {
    String sql = "DELETE FROM products where title = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, prodTitle);
    int num = preparedStatement.executeUpdate();
    if (num == 0)
    {
      System.out.println("No product found!");
    }
  }

  public boolean commandShow_all(TextFlow output) throws SQLException
  {
    ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
    if (!resultSet.next())
    {
      Text nothing = new Text("Table is empty!\n");
      nothing.setFill(Color.RED);
      output.getChildren().add(nothing);
      return true;
    }
    resultSet.beforeFirst();
    while (resultSet.next())
    {

      Text id = new Text(resultSet.getString("id") + " ");
      id.setFill(Color.WHITE);
      Text prodid = new Text(resultSet.getString("prodid") + " ");
      prodid.setFill(Color.WHITE);
      Text title = new Text(resultSet.getString("title") + " ");
      title.setFill(Color.WHITE);
      Text cost = new Text(resultSet.getString("cost")  + "\n");
      cost.setFill(Color.WHITE);
      output.getChildren().addAll(id, prodid, title, cost);
    }
    return true;
  }

  public void commandPrice(String prodTitle, TextFlow output) throws SQLException {
    String sql = "SELECT * FROM products where title = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, prodTitle);
    ResultSet resultSet = preparedStatement.executeQuery();
    resultSet.next();
    Text price = new Text(resultSet.getString("cost"));
    price.setFill(Color.WHITE);
    output.getChildren().add(price);
  }

  public void commandChange_price(String prodTitle, int prodCost) throws SQLException
  {
    String sql = "UPDATE products SET cost = ? WHERE title = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(2, prodTitle);
    preparedStatement.setInt(1, prodCost);
    preparedStatement.executeUpdate();
  }

  public void commandFilter_by_price(int firstCost, int secondCost, TextFlow output) throws SQLException
  {
    String sql = "SELECT * FROM products WHERE cost BETWEEN ? AND ?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setInt(1, firstCost);
    preparedStatement.setInt(2, secondCost);

    ResultSet resultSet = preparedStatement.executeQuery();
    if (!resultSet.next())
    {
      output.getChildren().add(new Text("No products found!\n\n"));
      return;
    }
    resultSet.beforeFirst();
    while (resultSet.next())
    {
      Text id = new Text (resultSet.getString("id") + " ");
      Text prodid = new Text (resultSet.getString("prodid") + " ");
      Text title = new Text (resultSet.getString("title") + " ");
      Text price = new Text (resultSet.getString("cost") + "\n");
      id.setFill(Color.WHITE);
      prodid.setFill(Color.WHITE);
      title.setFill(Color.WHITE);
      price.setFill(Color.WHITE);
      output.getChildren().addAll(id, prodid, title, price);
    }
  }
}