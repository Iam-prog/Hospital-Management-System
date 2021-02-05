
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

public class FXMLDocumentController implements Initializable {

    @FXML
    public void handleButtonActionDoctor(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("DoctorWindow.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Doctor Login Window");
        window.setScene(scene);
        window.show();
        //window.initModality(Modality.WINDOW_MODAL);
        //window.initOwner(window);

    }

    @FXML
    void handleButtonActionPatient(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("PatientWindow.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Patient Login Window");
        window.setScene(scene);
        window.show();

    }

    @FXML
    void handleButtonActionEmployee(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("EmployeeWindow.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Employee Login Window");
        window.setScene(scene);
        window.show();
    }

    @FXML
    void medicineStore(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("MedicineStore.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Medicine Store Window");
        window.setScene(scene);
        window.show();

    }

    @FXML
    void roomInfo(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("RoomInformation.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Room Information Window");
        window.setScene(scene);
        window.show();

    }

    @FXML
    void AvailableTest(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("AvailableTest.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Available Test Window");
        window.setScene(scene);
        window.show();

    }

    @FXML
    void admission(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("Admission.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Admission Window");
        window.setScene(scene);
        window.show();

    }

    @FXML
    void admin(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Admin Window");
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}

