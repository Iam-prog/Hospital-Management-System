
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class AdminPatientInformationController implements Initializable {

    @FXML
    private TableColumn<Patient, String> address;

    @FXML
    private TableColumn<Patient, String> des;

    @FXML
    private TableColumn<Patient, String> bg;

    @FXML
    private TableColumn<Patient, String> dob;

    @FXML
    private TableColumn<Patient, String> name;

    @FXML
    private TableView<Patient> tableView;

    @FXML
    private TableColumn<Patient, String> un;

    @FXML
    private TableColumn<Patient, Integer> id;

    @FXML
    private TableColumn<Patient, String> email;

    @FXML
    private TableColumn<Patient, Integer> age;

    ObservableList<Patient> pat = FXCollections.observableArrayList();

    void reftable() throws ClassNotFoundException, SQLException {
        pat.clear();

        Class.forName("com.mysql.cj.jdbc.Driver");
        String db = "jdbc:mysql://localhost:3306/Hospital?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String password = "";
        try (Connection conn = DriverManager.getConnection(db, user, password)) {
            String query = "SELECT * FROM patients";
            try (PreparedStatement stm = (PreparedStatement) conn.prepareStatement(query);
                    ResultSet rs = (ResultSet) stm.executeQuery()) {
                while (rs.next()) {

                    pat.add(new Patient(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            Integer.valueOf(rs.getString(10)),
                            rs.getString(7),
                            rs.getString(9)
                    ));

                    tableView.setItems(pat);

                }
            }
            conn.close();
        }

    }

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

    @FXML
    void goback(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("Adminpat.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Admin Patient Controller Window");
        window.setScene(scene);
        window.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        bg.setCellValueFactory(new PropertyValueFactory<>("bloodg"));
        dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        un.setCellValueFactory(new PropertyValueFactory<>("userName"));
        des.setCellValueFactory(new PropertyValueFactory<>("desig"));

        tableView.setEditable(true);
        id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        email.setCellFactory(TextFieldTableCell.forTableColumn());
        address.setCellFactory(TextFieldTableCell.forTableColumn());
        bg.setCellFactory(TextFieldTableCell.forTableColumn());
        dob.setCellFactory(TextFieldTableCell.forTableColumn());
        age.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        un.setCellFactory(TextFieldTableCell.forTableColumn());
        des.setCellFactory(TextFieldTableCell.forTableColumn());

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

}

