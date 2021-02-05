
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

public class AdminempconController implements Initializable {

    @FXML
    private TextField eid;

    @FXML
    private TextField docid;

    @FXML
    private TextField sal1;

    @FXML
    private TableColumn<Room, String> Name11;

    @FXML
    private TableView<Doctor> tableView;

    @FXML
    private TextField roomn;

    @FXML
    private TableColumn<Room, Integer> id11;

    @FXML
    private TableView<Employee> tableView1;

    @FXML
    private TextField assas1;

    @FXML
    private TableColumn<Doctor, String> Name;

    @FXML
    private TableView<Room> tableView2;

    @FXML
    private TableColumn<Employee, Integer> id1;

    @FXML
    private TableColumn<Doctor, Integer> id;

    @FXML
    private Label regMessLabel;

    @FXML
    private Label regMessLabel1;

    @FXML
    private TableColumn<Employee, String> Name1;

    @FXML
    private TextField eid1;

    @FXML
    private TextField assas;

    @FXML
    private TextField sal;

    ObservableList<Doctor> doc = FXCollections.observableArrayList();
    ObservableList<Employee> emp = FXCollections.observableArrayList();
    ObservableList<Room> room = FXCollections.observableArrayList();

    @FXML
    void loadd(ActionEvent event) throws ClassNotFoundException, SQLException {
        reftable();
    }

    @FXML
    void loade(ActionEvent event) throws ClassNotFoundException, SQLException {
        reftable1();
    }

    @FXML
    void loadr(ActionEvent event) throws ClassNotFoundException, SQLException {
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
        emp.clear();

        Class.forName("com.mysql.cj.jdbc.Driver");
        String db = "jdbc:mysql://localhost:3306/Hospital?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String password = "";
        try (Connection conn = DriverManager.getConnection(db, user, password)) {
            String query = "SELECT `Id`, `Name` FROM employees";
            try (PreparedStatement stm1 = (PreparedStatement) conn.prepareStatement(query);
                    ResultSet rs1 = (ResultSet) stm1.executeQuery()) {
                while (rs1.next()) {
                    emp.add(new Employee(
                            rs1.getInt(1),
                            rs1.getString(2)
                    ));

                    tableView1.setItems(emp);

                }
            }
            conn.close();
        }

    }

    void reftable2() throws ClassNotFoundException, SQLException {
        room.clear();

        Class.forName("com.mysql.cj.jdbc.Driver");
        String db = "jdbc:mysql://localhost:3306/Hospital?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String password = "";
        try (Connection conn = DriverManager.getConnection(db, user, password)) {
            String query = "SELECT `RoomNo`, `BuildingNo` FROM roominformation";
            try (PreparedStatement stm2 = (PreparedStatement) conn.prepareStatement(query);
                    ResultSet rs2 = (ResultSet) stm2.executeQuery()) {
                while (rs2.next()) {

                    room.add(new Room(
                            rs2.getInt(2),
                            String.valueOf(rs2.getInt(1))
                    ));

                    tableView2.setItems(room);

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
        String query1 = "SELECT * FROM `assignemployeetodoctor` WHERE `EmployeeId`=" + Integer.parseInt(eid.getText()) + "";
        PreparedStatement stm2;
        stm2 = conn.prepareStatement(query1);
        try (ResultSet rs = stm2.executeQuery()) {
            if (rs.next()) {
                found = true;
            }
        }
        stm2.close();
        if (found == false) {

            String query = "SELECT * FROM `employees` WHERE `Id`=" + Integer.parseInt(eid.getText()) + "";
            PreparedStatement stm1;
            stm1 = conn.prepareStatement(query);
            try (ResultSet rs = stm1.executeQuery()) {
                if (rs.next()) {
                    found1 = true;
                }
            }
            stm1.close();
            if (found1) {
                String query2 = "SELECT * FROM `doctors` WHERE `Id`=" + Integer.parseInt(docid.getText()) + "";
                PreparedStatement stm5;
                stm5 = conn.prepareStatement(query2);
                try (ResultSet rs = stm5.executeQuery()) {
                    if (rs.next()) {
                        found2 = true;
                    }
                }
                stm2.close();
                if (found2) {
                    Statement stm4 = (Statement) conn.createStatement();
                    String query3 = "INSERT INTO assignemployeetodoctor VALUES(" + Integer.parseInt(eid.getText()) + ",'" + assas.getText() + "'," + Integer.parseInt(docid.getText()) + ",'" + sal.getText() + "')";
                    stm4.executeUpdate(query3);

                } else {
                    regMessLabel.setText("This Doctor Id Does Not Exist.Try different Id.");
                }
            } else {
                regMessLabel.setText("This Employee Id Does Not Exist.Try different Id.");
            }
        } else {
            regMessLabel.setText("This Employee already Assigned.");
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
        String query1 = "SELECT * FROM `assignemployeeaposition` WHERE `EmployeeId`=" + Integer.parseInt(eid1.getText()) + "";
        PreparedStatement stm2;
        stm2 = conn.prepareStatement(query1);
        try (ResultSet rs = stm2.executeQuery()) {
            if (rs.next()) {
                found = true;
            }
        }
        stm2.close();
        if (found == false) {
            String query = "SELECT * FROM `employees` WHERE `Id`=" + Integer.parseInt(eid1.getText()) + "";
            PreparedStatement stm1;
            stm1 = conn.prepareStatement(query);
            try (ResultSet rs = stm1.executeQuery()) {
                if (rs.next()) {
                    found1 = true;
                }
            }
            stm1.close();
            if (found1) {
                String query2 = "SELECT * FROM `roominformation` WHERE `RoomNo`=" + Integer.parseInt(roomn.getText()) + "";
                PreparedStatement stm5;
                stm5 = conn.prepareStatement(query2);
                try (ResultSet rs = stm5.executeQuery()) {
                    if (rs.next()) {
                        found2 = true;
                    }
                }
                stm2.close();
                if (found2) {
                    Statement stm4 = (Statement) conn.createStatement();
                    String query3 = "INSERT INTO assignemployeetodoctor VALUES(" + Integer.parseInt(eid1.getText()) + "," + assas1.getText() + "," + Integer.parseInt(roomn.getText()) + ",'" + sal1.getText() + "')";
                    stm4.executeUpdate(query3);

                } else {
                    regMessLabel1.setText("This Room Number Does Not Exist.Try different Room Number.");
                }
            } else {
                regMessLabel1.setText("This Employee Id Does Not Exist.Try different Id.");
            }
        } else {
            regMessLabel1.setText("This Employee already Assigned.");
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
        Parent parent = FXMLLoader.load(getClass().getResource("Adminemp.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Admin Employee Controller Window");
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
        id1.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        Name1.setCellFactory(TextFieldTableCell.forTableColumn());
        tableView1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        id11.setCellValueFactory(new PropertyValueFactory<>("buildingNo"));
        Name11.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        tableView2.setEditable(true);
        id11.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        Name11.setCellFactory(TextFieldTableCell.forTableColumn());
        tableView2.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

}

