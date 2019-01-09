package projektporun;

import projektporun.*;
import domain.*;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.dao.HibernateUtil;

public class DataBaseApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("FXMLConnectionWithDataBase.fxml"));
        Pane stackPane = loader.load();
        FXMLConnectionWithDataBaseController controller = loader.getController();

        Scene scene = new Scene(stackPane);

        primaryStage.setTitle("Łączenie z bazą danych");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        HibernateUtil.OpenConnection();
        launch(args);
        HibernateUtil.CloseConnection();
    }

}
