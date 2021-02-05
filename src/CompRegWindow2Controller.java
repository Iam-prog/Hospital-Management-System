
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CompRegWindow2Controller implements Initializable {

    @FXML
    void home(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Home Page");
        window.setScene(scene);
        window.show();

    }

    @FXML
    void logIn(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("PatientWindow.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Patient Login Window");
        window.setScene(scene);
        window.show();

    }

    @FXML
    void regWindow(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("PatReg.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Patient Registration");
        window.setScene(scene);
        window.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}

