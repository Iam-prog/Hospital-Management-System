
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class AdmissionController implements Initializable {

    @FXML
    private TextField paiId;

    @FXML
    private TextField docid;

    @FXML
    private Label regMessLabel;

    @FXML
    private TableView<Doctor> tableView;

    @FXML
    private TextField addId;

    @FXML
    private TableColumn<Doctor, Integer> id;

    @FXML
    private TextField appoid;

    @FXML
    private TableColumn<Doctor, String> Name;

    ObservableList<Doctor> test = FXCollections.observableArrayList();

    @FXML
    void load(ActionEvent event) throws ClassNotFoundException, SQLException {
        reftable();
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

    void reftable() throws ClassNotFoundException, SQLException {
        test.clear();

        Class.forName("com.mysql.cj.jdbc.Driver");
        String db = "jdbc:mysql://localhost:3306/Hospital?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String password = "";
        try (Connection conn = DriverManager.getConnection(db, user, password)) {
            String query = "SELECT `Id`, `Name` FROM doctors";
            try (PreparedStatement stm = (PreparedStatement) conn.prepareStatement(query);
                    ResultSet rs = (ResultSet) stm.executeQuery()) {
                while (rs.next()) {
                    test.add(new Doctor(
                            rs.getInt(1),
                            rs.getString(2)
                    ));

                    tableView.setItems(test);

                }
            }
            conn.close();
        }

    }

    @FXML
    void add(ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String db = "jdbc:mysql://localhost:3306/Hospital?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String password = "";
        boolean found1 = false;
        boolean found2 = false;
        boolean found3 = false;
        boolean found4 = false;
        Connection conn = DriverManager.getConnection(db, user, password);
        String query = "SELECT * FROM `admission` WHERE `AdmissionId`=" + Integer.parseInt(addId.getText()) + "";
        PreparedStatement stm1;
        stm1 = conn.prepareStatement(query);
        try (ResultSet rs = stm1.executeQuery()) {
            if (rs.next()) {
                found1 = true;
            }
        }
        stm1.close();
        if (found1 == false) {
            String query1 = "SELECT * FROM `patients` WHERE `Id`=" + Integer.parseInt(paiId.getText()) + "";
            PreparedStatement stm2;
            stm2 = conn.prepareStatement(query1);
            try (ResultSet rs = stm2.executeQuery()) {
                if (rs.next()) {
                    found2 = true;
                }
            }
            stm2.close();
            if (found2 == false) {
                String query2 = "SELECT * FROM `doctors` WHERE `Id`=" + Integer.parseInt(docid.getText()) + "";
                PreparedStatement stm3;
                stm3 = conn.prepareStatement(query2);
                try (ResultSet rs = stm3.executeQuery()) {
                    if (rs.next()) {
                        found3 = true;
                    }
                }
                stm3.close();
                if (found3 == true) {
                    String query4 = "SELECT * FROM `admission` WHERE `AppointmentId`=" + Integer.parseInt(appoid.getText()) + "";
                    PreparedStatement stm5;
                    stm5 = conn.prepareStatement(query4);
                    try (ResultSet rs = stm5.executeQuery()) {
                        if (rs.next()) {
                            found4 = true;
                        }
                    }
                    stm5.close();
                    if (found4 == false) {
                        Admission newadd = new Admission(Integer.parseInt(addId.getText()), Integer.parseInt(paiId.getText()), Integer.parseInt(docid.getText()), Integer.parseInt(appoid.getText()));
                        Statement stm4 = (Statement) conn.createStatement();
                        String query3 = "INSERT INTO admission VALUES(" + Integer.parseInt(addId.getText()) + "," + Integer.parseInt(paiId.getText()) + "," + Integer.parseInt(docid.getText()) + "," + Integer.parseInt(appoid.getText()) + ")";
                        stm4.executeUpdate(query3);
                    } else {
                        regMessLabel.setText("This Appointment Id already Exist.Try different Id.");
                    }
                } else {
                    regMessLabel.setText("This doctor Id does not Exist.Try different Id.");
                }
            } else {
                regMessLabel.setText("This Patients Id already Exist.Try different Id.");
            }
        } else {
            regMessLabel.setText("This Admission Id already Exist.Try different Id.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tableView.setEditable(true);
        id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        Name.setCellFactory(TextFieldTableCell.forTableColumn());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}

