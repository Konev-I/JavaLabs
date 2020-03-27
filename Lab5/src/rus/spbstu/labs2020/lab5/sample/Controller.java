package rus.spbstu.labs2020.lab5.sample;

import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import rus.spbstu.labs2020.lab5.CommandParser.CommandParser;
import rus.spbstu.labs2020.lab5.CommandParser.IntParser;

public class Controller {
  private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
  private static final String USER = "root";
  private static final String PASS = "root";
  private static CommandParser commandParser;
  private ObservableList<String> products = FXCollections.observableArrayList();

  static {
    try {
      commandParser = new CommandParser(URL, USER, PASS);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @FXML
  private Button b_show_all;

  @FXML
  private Button b_add;

  @FXML
  private TextField f_product_name;

  @FXML
  private TextField f_product_price;

  @FXML
  private TextFlow output_panel;

  @FXML
  private Button b_delete;

  @FXML
  private ChoiceBox<String> product_name_choice;

  @FXML
  private Button b_price;

  @FXML
  private ChoiceBox<String> product_name_choice2;

  @FXML
  private Button b_change_price;

  @FXML
  private ChoiceBox<String> product_name_choice3;

  @FXML
  private Button b_filter;

  @FXML
  private TextField f_from;

  @FXML
  private TextField f_to;

  @FXML
  private TextField f_new_price;

  @FXML
  private Button b_exit;

  private void fillCheckBox(ChoiceBox<String> box)
  {
    try {
      commandParser.fillChoiceBox(products);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    box.setItems(products);
  }

  @FXML
  void initialize() {
    b_show_all.setOnAction(event -> {
      try {
        if (commandParser.commandShow_all(output_panel)){
          output_panel.getChildren().add(new Text("\n"));
        }
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    });

    product_name_choice.setOnShowing(event -> {
      fillCheckBox(product_name_choice);
    });

    b_delete.setOnAction(event -> {
      try {
        if (!commandParser.productCheck(product_name_choice.getValue()))
        {
          Text error = new Text("No product found!\n\n");
          error.setFill(Color.RED);
          output_panel.getChildren().add(error);
          return;
        }
        commandParser.commandDelete(product_name_choice.getValue());
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    });

    product_name_choice2.setOnShowing(event -> {
      fillCheckBox(product_name_choice2);
    });

    b_price.setOnAction(event -> {
      try {
        if (!commandParser.productCheck(product_name_choice2.getValue()))
        {
          Text error = new Text("No product found!\n\n");
          error.setFill(Color.RED);
          output_panel.getChildren().add(error);
          return;
        }
        commandParser.commandPrice(product_name_choice2.getValue(), output_panel);
        output_panel.getChildren().add(new Text("\n\n"));
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    });

    product_name_choice3.setOnShowing(event -> {
      fillCheckBox(product_name_choice3);
    });

    b_change_price.setOnAction(event -> {
      if (!IntParser.tryParseInt(f_new_price.getText()))
      {
        Text error = new Text("You entered wrong price!\n\n");
        error.setFill(Color.RED);
        output_panel.getChildren().add(error);
        return;
      }
      try {
        if (!commandParser.productCheck(product_name_choice3.getValue()))
        {
          Text error = new Text("No product found!\n\n");
          error.setFill(Color.RED);
          output_panel.getChildren().add(error);
          return;
        }
        commandParser.commandChange_price(product_name_choice3.getValue(), Integer.parseInt(f_new_price.getText()));
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    });

    b_filter.setOnAction(event -> {
      if (!IntParser.tryParseInt(f_from.getText()) || !IntParser.tryParseInt(f_to.getText()))
      {
        Text error = new Text("You entered wrong price!\n\n");
        error.setFill(Color.RED);
        output_panel.getChildren().add(error);
        return;
      }
      try {
        commandParser.commandFilter_by_price(Integer.parseInt(f_from.getText()), Integer.parseInt(f_to.getText()),
          output_panel);
        output_panel.getChildren().add(new Text("\n"));
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    });

    b_add.setOnAction(event -> {
      if (!IntParser.tryParseInt(f_product_price.getText()))
      {
        Text error = new Text("You entered wrong price!\n\n");
        error.setFill(Color.RED);
        output_panel.getChildren().add(error);
        return;
      }
      try {
        if (commandParser.productCheck(f_product_name.getText()))
        {
          Text error = new Text("This product already exist!\n\n");
          error.setFill(Color.RED);
          output_panel.getChildren().add(error);
          return;
        }
        if (f_product_name.getText().equals(""))
        {
          Text error = new Text("You didn't enter product name!\n\n");
          error.setFill(Color.RED);
          output_panel.getChildren().add(error);
          return;
        }
        commandParser.commandAdd(f_product_name.getText(),Integer.parseInt(f_product_price.getText()));
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    });

    b_exit.setOnAction(event -> {
      ((Stage) b_exit.getScene().getWindow()).close();
    });
  }
}
