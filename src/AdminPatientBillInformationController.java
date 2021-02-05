
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

public class AdminPatientBillInformationController implements Initializable {

    @FXML
    private TextField assroomn;

    @FXML
    private TextField pid;

    @FXML
    private Label regMessLabel;

    @FXML
    private TextField did;

    @FXML
    private TextField sid;

    @FXML
    private TableColumn<Patient, String> name;

    @FXML
    private TableView<Patient> tableView;

    @FXML
    private TableColumn<Patient, Integer> id;

    ObservableList<Patient> pat = FXCollections.observableArrayList();

    @FXML
    void load(ActionEvent event) throws ClassNotFoundException, SQLException {

        reftable();
    }

    void reftable() throws ClassNotFoundException, SQLException {
        pat.clear();

        Class.forName("com.mysql.cj.jdbc.Driver");
        String db = "jdbc:mysql://localhost:3306/Hospital?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String password = "";
        try (Connection conn = DriverManager.getConnection(db, user, password)) {
            String query = "SELECT `Id`, `Name` FROM patients";
            try (PreparedStatement stm = (PreparedStatement) conn.prepareStatement(query);
                    ResultSet rs = (ResultSet) stm.executeQuery()) {
                while (rs.next()) {
                    pat.add(new Patient(
                            rs.getInt(1),
                            rs.getString(2)
                    ));

                    tableView.setItems(pat);

                }
            }
            conn.close();
        }

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
        boolean found5 = false;
        if (sid.getText().length() > 0 && did.getText().length() > 0) {
            Connection conn = DriverManager.getConnection(db, user, password);
            String query1 = "SELECT * FROM `patients` WHERE `Id`=" + Integer.parseInt(pid.getText()) + "";
            PreparedStatement stm2;
            stm2 = conn.prepareStatement(query1);
            try (ResultSet rs = stm2.executeQuery()) {
                if (rs.next()) {
                    found2 = true;
                }
            }
            stm2.close();
            if (found2) {
                //   try (Connection conn = DriverManager.getConnection(db, user, password)) {
                String query2 = "SELECT * FROM `surgeryinformation` WHERE `Id`=" + Integer.parseInt(sid.getText()) + "";
                PreparedStatement stm3;
                stm3 = conn.prepareStatement(query2);
                try (ResultSet rs = stm3.executeQuery()) {
                    if (rs.next()) {
                        found3 = true;
                    }
                }
                stm3.close();
                if (found3) {
                    String query4 = "SELECT * FROM `diagnosisinformation` WHERE `Id`=" + Integer.parseInt(did.getText()) + "";
                    PreparedStatement stm5;
                    stm5 = conn.prepareStatement(query4);
                    try (ResultSet rs = stm5.executeQuery()) {
                        if (rs.next()) {
                            found4 = true;
                        }
                    }
                    stm5.close();
                    if (found4) {
                        String query5 = "SELECT * FROM `roominformation` WHERE `RoomNo`=" + Integer.parseInt(assroomn.getText()) + "";
                        PreparedStatement stm6;
                        stm6 = conn.prepareStatement(query5);
                        try (ResultSet rs = stm6.executeQuery()) {
                            if (rs.next()) {
                                found5 = true;
                            }
                        }
                        stm6.close();

                        if (found5) {
                            Statement stm4;
                            stm4 = (Statement) conn.createStatement();
                            String query3 = "INSERT INTO patientbillinformation VALUES(" + Integer.parseInt(pid.getText()) + "," + Integer.parseInt(assroomn.getText()) + "," + Integer.parseInt(sid.getText()) + "," + Integer.parseInt(did.getText()) + ")";
                            stm4.executeUpdate(query3);
                        } else {
                            regMessLabel.setText("This Room Id does Not Exist.Try different Id.");
                        }
                    } else {
                        regMessLabel.setText("This Diagnosis Id does Not Exist.Try different Id.");
                    }
                } else {
                    regMessLabel.setText("This Surgery Id does not Exist.Try different Id.");
                }
            } else {
                regMessLabel.setText("This Patient Id does not Exist.Try different Id.");
            }

        } else if (sid.getText().length() > 0 && did.getText().length() == 0) {
            Connection conn = DriverManager.getConnection(db, user, password);
            String query1 = "SELECT * FROM `patients` WHERE `Id`=" + Integer.parseInt(pid.getText()) + "";
            PreparedStatement stm2;
            stm2 = conn.prepareStatement(query1);
            try (ResultSet rs = stm2.executeQuery()) {
                if (rs.next()) {
                    found2 = true;
                }
            }
            stm2.close();
            if (found2) {
                //   try (Connection conn = DriverManager.getConnection(db, user, password)) {
                String query2 = "SELECT * FROM `surgeryinformation` WHERE `Id`=" + Integer.parseInt(sid.getText()) + "";
                PreparedStatement stm3;
                stm3 = conn.prepareStatement(query2);
                try (ResultSet rs = stm3.executeQuery()) {
                    if (rs.next()) {
                        found3 = true;
                    }
                }
                stm3.close();
                if (found3) {
                    String query5 = "SELECT * FROM `roominformation` WHERE `RoomNo`=" + Integer.parseInt(assroomn.getText()) + "";
                    PreparedStatement stm6;
                    stm6 = conn.prepareStatement(query5);
                    try (ResultSet rs = stm6.executeQuery()) {
                        if (rs.next()) {
                            found5 = true;
                        }
                    }
                    stm6.close();
                    if (found5) {
                        Statement stm4;
                        stm4 = (Statement) conn.createStatement();
                        String query3 = "INSERT INTO patientbillinformation1 VALUES(" + Integer.parseInt(pid.getText()) + "," + Integer.parseInt(assroomn.getText()) + "," + Integer.parseInt(sid.getText()) + ")";
                        stm4.executeUpdate(query3);
                    } else {
                        regMessLabel.setText("This Room Id does Not Exist.Try different Id.");
                    }
                } else {
                    regMessLabel.setText("This Surgery Id does not Exist.Try different Id.");
                }
            } else {
                regMessLabel.setText("This Patient Id does not Exist.Try different Id.");
            }
        } else if (sid.getText().length() == 0 && did.getText().length() > 0) {
            Connection conn = DriverManager.getConnection(db, user, password);
            String query1 = "SELECT * FROM `patients` WHERE `Id`=" + Integer.parseInt(pid.getText()) + "";
            PreparedStatement stm2;
            stm2 = conn.prepareStatement(query1);
            try (ResultSet rs = stm2.executeQuery()) {
                if (rs.next()) {
                    found2 = true;
                }
            }
            stm2.close();
            if (found2) {
                String query4 = "SELECT * FROM `diagnosisinformation` WHERE `Id`=" + Integer.parseInt(did.getText()) + "";
                PreparedStatement stm5;
                stm5 = conn.prepareStatement(query4);
                try (ResultSet rs = stm5.executeQuery()) {
                    if (rs.next()) {
                        found4 = true;
                    }
                }
                stm5.close();
                if (found4) {
                    String query5 = "SELECT * FROM `roominformation` WHERE `RoomNo`=" + Integer.parseInt(assroomn.getText()) + "";
                    PreparedStatement stm6;
                    stm6 = conn.prepareStatement(query5);
                    try (ResultSet rs = stm6.executeQuery()) {
                        if (rs.next()) {
                            found5 = true;
                        }
                    }
                    stm6.close();
                    if (found5) {
                        Statement stm4;
                        stm4 = (Statement) conn.createStatement();
                        String query3 = "INSERT INTO patientbillinformation2 VALUES(" + Integer.parseInt(pid.getText()) + "," + Integer.parseInt(assroomn.getText()) + "," + Integer.parseInt(did.getText()) + ")";
                        stm4.executeUpdate(query3);
                    } else {
                        regMessLabel.setText("This Room Id does Not Exist.Try different Id.");
                    }
                } else {
                    regMessLabel.setText("This Diagnosis Id does Not Exist.Try different Id.");
                }
            } else {
                regMessLabel.setText("This Patient Id does not Exist.Try different Id.");
            }
        } else {
            regMessLabel.setText("Field(s) Not Filled Properly.");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView.setEditable(true);
        id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

}

