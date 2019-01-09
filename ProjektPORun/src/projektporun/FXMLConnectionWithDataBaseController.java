package projektporun;

import java.io.IOException;
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import projektporun.DataBaseApplication;

public class FXMLConnectionWithDataBaseController implements Initializable {

    private static final String URL = "jdbc:mysql://127.0.0.1/projekt";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    private static Connection connection;

    @FXML
    public Button connectionButton;
    public TextField textFieldLogin;
    public PasswordField textFieldPassword;
    public Text wrongAcces;
    private AnchorPane rootPane;
    private Stage primaryStage;

    @FXML
    public void ConnnectButton(ActionEvent event) throws IOException {
        try {
            String login = textFieldLogin.getText();
            String password = textFieldPassword.getText();
            if (!(login.equals(USER) && password.equals(PASSWORD))) {
                wrongAcces.setVisible(true);
            } else if (login.equals(USER) && password.equals(PASSWORD)) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Parent dataBaseMenu = FXMLLoader.load(getClass().getResource("DataBaseMenu.fxml"));
                Scene dataBaseMenu_scene = new Scene(dataBaseMenu);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(dataBaseMenu_scene);
                app_stage.setTitle("Baza danych - Dominik Warzocha");
                app_stage.show();
            }
        } catch (SQLException ex) {
            System.out.println("nie polaczono");
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wrongAcces.setVisible(false);

    }

}
