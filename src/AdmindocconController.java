
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

public class AdmindocconController implements Initializable {

    @FXML
    private TextField did;

    @FXML
    private TextField sid;

    @FXML
    private TextField sal1;

    @FXML
    private TableColumn<Test, String> Name11;

    @FXML
    private TableView<Doctor> tableView;

    @FXML
    private TextField diaid;

    @FXML
    private TableColumn<Test, String> id11;

    @FXML
    private TableView<Test> tableView1;

    @FXML
    private TextField assas1;

    @FXML
    private TableColumn<Doctor, String> Name;

    @FXML
    private TableView<Test> tableView2;

    @FXML
    private TableColumn<Test, String> id1;

    @FXML
    private TableColumn<Doctor, Integer> id;

    @FXML
    private Label regMessLabel;

    @FXML
    private Label regMessLabel1;

    @FXML
    private TableColumn<Test, String> Name1;

    @FXML
    private TextField did1;

    @FXML
    private TextField assas;

    @FXML
    private TextField sal;

    ObservableList<Doctor> doc = FXCollections.observableArrayList();
    ObservableList<Test> sur = FXCollections.observableArrayList();
    ObservableList<Test> diag = FXCollections.observableArrayList();

    @FXML
    void loadd(ActionEvent event) throws ClassNotFoundException, SQLException {
        reftable();
    }

    @FXML
    void loads(ActionEvent event) throws ClassNotFoundException, SQLException {
        reftable1();
    }

    @FXML
    void loaddia(ActionEvent event) throws ClassNotFoundException, SQLException {
        reftable2();
    }

    void reftable() throws ClassNotFoundException, SQLException {
        doc.clear();

        Class.forName("com.mysql.cj.jdbc.Driver");
        String db = "jdbc:mysql://localhost:3306/Hospital?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String password = "";
        try (Connection conn = DriverManager.getConnection(db, user, password)) {
            String query = "SELECT `Id`, `Name` FROM doctors";
            try (PreparedStatement stm = (PreparedStatement) conn.prepareStatement(query);
                    ResultSet rs = (ResultSet) stm.executeQuery()) {
                while (rs.next()) {
                    doc.add(new Doctor(
                            rs.getInt(1),
                            rs.getString(2)
                    ));

                    tableView.setItems(doc);

                }
            }
            conn.close();
        }

    }

    void reftable1() throws ClassNotFoundException, SQLException {
        sur.clear();

        Class.forName("com.mysql.cj.jdbc.Driver");
        String db = "jdbc:mysql://localhost:3306/Hospital?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String password = "";
        try (Connection conn = DriverManager.getConnection(db, user, password)) {
            String query = "SELECT `Id`, `Name` FROM surgeryinformation";
            try (PreparedStatement stm1 = (PreparedStatement) conn.prepareStatement(query);
                    ResultSet rs1 = (ResultSet) stm1.executeQuery()) {
                while (rs1.next()) {
                    sur.add(new Surgery(
                            String.valueOf(rs1.getInt(1)),
                            rs1.getString(2)
                    ));

                    tableView1.setItems(sur);

                }
            }
            conn.close();
        }

    }

    void reftable2() throws ClassNotFoundException, SQLException {
        diag.clear();

        Class.forName("com.mysql.cj.jdbc.Driver");
        String db = "jdbc:mysql://localhost:3306/Hospital?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String password = "";
        try (Connection conn = DriverManager.getConnection(db, user, password)) {
            String query = "SELECT `Id`, `Name` FROM diagnosisinformation";
            try (PreparedStatement stm2 = (PreparedStatement) conn.prepareStatement(query);
                    ResultSet rs2 = (ResultSet) stm2.executeQuery()) {
                while (rs2.next()) {

                    diag.add(new Diagonesis(
                            String.valueOf(rs2.getInt(1)),
                            rs2.getString(2)
                    ));

                    tableView2.setItems(diag);

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
        boolean found = false;
        boolean found1 = false;
        boolean found2 = false;
        Connection conn = DriverManager.getConnection(db, user, password);
        String query1 = "SELECT * FROM `assigndoctorforsurgaery` WHERE `DoctorId`=" + Integer.parseInt(did.getText()) + " AND `SurgaeryId`=" + Integer.parseInt(sid.getText()) + "";
        PreparedStatement stm2;
        stm2 = conn.prepareStatement(query1);
        try (ResultSet rs = stm2.executeQuery()) {
            if (rs.next()) {
                found = true;
            }
        }
        stm2.close();
        if (found == false) {

            String query = "SELECT * FROM `doctors` WHERE `Id`=" + Integer.parseInt(did.getText()) + "";
            PreparedStatement stm1;
            stm1 = conn.prepareStatement(query);
            try (ResultSet rs = stm1.executeQuery()) {
                if (rs.next()) {
                    found1 = true;
                }
            }
            stm1.close();
            if (found1) {
                String query2 = "SELECT * FROM `surgeryinformation` WHERE `Id`=" + Integer.parseInt(sid.getText()) + "";
                PreparedStatement stm5;
                stm5 = conn.prepareStatement(query2);
                try (ResultSet rs = stm5.executeQuery()) {
                    if (rs.next()) {
                        found2 = true;
                    }
                }
                if (found2) {
                    stm2.close();

                    Statement stm4 = (Statement) conn.createStatement();
                    String query3 = "INSERT INTO assigndoctorforsurgaery VALUES(" + Integer.parseInt(did.getText()) + ",'" + assas.getText() + "'," + Integer.parseInt(sid.getText()) + ",'" + sal.getText() + "')";
                    stm4.executeUpdate(query3);
                } else {
                    regMessLabel.setText("This Surgery Id Does Not Exist.Try different Id.");
                }
            } else {
                regMessLabel.setText("This Doctor Id Does Not Exist.Try different Id.");
            }
        } else {
            regMessLabel.setText("This Doctor already Assigned For this Surgery.");
        }

    }

    @FXML
    void add1(ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String db = "jdbc:mysql://localhost:3306/Hospital?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String password = "";
        boolean found = false;
        boolean found1 = false;
        boolean found2 = false;
        Connection conn = DriverManager.getConnection(db, user, password);
        String query1 = "SELECT * FROM `assigndoctorfordiagnosis` WHERE `DoctorId`=" + Integer.parseInt(did1.getText()) + " AND `DiagnosisId`=" + Integer.parseInt(diaid.getText()) + "";
        PreparedStatement stm2;
        stm2 = conn.prepareStatement(query1);
        try (ResultSet rs = stm2.executeQuery()) {
            if (rs.next()) {
                found = true;
            }
        }
        stm2.close();
        if (found == false) {

            String query = "SELECT * FROM `doctors` WHERE `Id`=" + Integer.parseInt(did.getText()) + "";
            PreparedStatement stm1;
            stm1 = conn.prepareStatement(query);
            try (ResultSet rs = stm1.executeQuery()) {
                if (rs.next()) {
                    found1 = true;
                }
            }
            stm1.close();
            if (found1) {
                String query2 = "SELECT * FROM `diagnosisinformation` WHERE `Id`=" + Integer.parseInt(diaid.getText()) + "";
                PreparedStatement stm5;
                stm5 = conn.prepareStatement(query2);
                try (ResultSet rs = stm5.executeQuery()) {
                    if (rs.next()) {
                        found2 = true;
                    }
                }
                if (found2) {
                    stm2.close();

                    Statement stm4 = (Statement) conn.createStatement();
                    String query3 = "INSERT INTO assigndoctorfordiagnosis VALUES(" + Integer.parseInt(did1.getText()) + ",'" + assas1.getText() + "'," + Integer.parseInt(diaid.getText()) + ",'" + sal1.getText() + "')";
                    stm4.executeUpdate(query3);
                } else {
                    regMessLabel1.setText("This Diagnosis Id Does Not Exist.Try different Id.");
                }
            } else {
                regMessLabel1.setText("This Doctor Id Does Not Exist.Try different Id.");
            }
        } else {
            regMessLabel1.setText("This Doctor already Assigned For this Diagnosis Test.");
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
        Parent parent = FXMLLoader.load(getClass().getResource("Admindoc.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Admin Doctor Controller Window");
        window.setScene(scene);
        window.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tableView.setEditable(true);
        id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        Name.setCellFactory(TextFieldTableCell.forTableColumn());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        id1.setCellValueFactory(new PropertyValueFactory<>("id"));
        Name1.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView1.setEditable(true);
        id1.setCellFactory(TextFieldTableCell.forTableColumn());
        Name1.setCellFactory(TextFieldTableCell.forTableColumn());
        tableView1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        id11.setCellValueFactory(new PropertyValueFactory<>("id"));
        Name11.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView2.setEditable(true);
        id11.setCellFactory(TextFieldTableCell.forTableColumn());
        Name11.setCellFactory(TextFieldTableCell.forTableColumn());
        tableView2.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

}

