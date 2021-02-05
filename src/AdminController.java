
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

public class AdminController implements Initializable {

    @FXML
    void doc(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("Admindoc.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Admin Doctor Controller Window");
        window.setScene(scene);
        window.show();
    }

    @FXML
    void emp(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("Adminemp.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Admin Employee Controller Window");
        window.setScene(scene);
        window.show();
    }

    @FXML
    void pat(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("Adminpat.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Admin Patient Controller Window");
        window.setScene(scene);
        window.show();

    }

    @FXML
    void home(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Home Page");
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}

