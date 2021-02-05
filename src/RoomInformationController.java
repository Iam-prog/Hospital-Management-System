
import java.io.FileNotFoundException;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class RoomInformationController implements Initializable {

    @FXML
    private TableColumn<Room, Integer> wardNo;

    @FXML
    private TextField textWardNo;

    @FXML
    private TableColumn<Room, String> roomNo;

    @FXML
    private TextField textFloorNo;

    @FXML
    private TableView<Room> tableView;

    @FXML
    private TextField textRoomType;

    @FXML
    private TextField textRoomNo;

    @FXML
    private TextField textPrice;

    @FXML
    private TableColumn<Room, Integer> buildingNo;

    @FXML
    private TextField textbuildingNo;

    @FXML
    private TableColumn<Room, Double> price;

    @FXML
    private TableColumn<Room, Integer> floorNo;

    @FXML
    private TableColumn<Room, String> roomType;

    ObservableList<Room> room = FXCollections.observableArrayList();

    public void changeRoomNo(TableColumn.CellEditEvent edittedCell) {
        Room infoSelected = tableView.getSelectionModel().getSelectedItem();
        infoSelected.setRoomNo(edittedCell.getNewValue().toString());

    }

    public void changeBuildingNo(TableColumn.CellEditEvent edittedCell) {
        Room infoSelected = tableView.getSelectionModel().getSelectedItem();
        infoSelected.setBuildingNo(Integer.parseInt(edittedCell.getNewValue().toString()));

    }

    public void changeRoomType(TableColumn.CellEditEvent edittedCell) {
        Room infoSelected = tableView.getSelectionModel().getSelectedItem();
        infoSelected.setRoomType(edittedCell.getNewValue().toString());

    }

    public void changeFloorNo(TableColumn.CellEditEvent edittedCell) {
        Room infoSelected = tableView.getSelectionModel().getSelectedItem();
        infoSelected.setFloorNo(Integer.parseInt(edittedCell.getNewValue().toString()));

    }

    public void changeWardNo(TableColumn.CellEditEvent edittedCell) {
        Room infoSelected = tableView.getSelectionModel().getSelectedItem();
        infoSelected.setWardNo(Integer.parseInt(edittedCell.getNewValue().toString()));

    }

    public void changePrice(TableColumn.CellEditEvent edittedCell) {
        Room infoSelected = tableView.getSelectionModel().getSelectedItem();
        infoSelected.setPrice(Double.parseDouble(edittedCell.getNewValue().toString()));

    }

    @FXML
    void add(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
        Room r = new Room(textRoomNo.getText(), Integer.valueOf(textbuildingNo.getText()), textRoomType.getText(), Integer.valueOf(textFloorNo.getText()), Integer.valueOf(textWardNo.getText()), Double.valueOf(textPrice.getText()));
        tableView.getItems().add(r);

        Class.forName("com.mysql.cj.jdbc.Driver");
        String db = "jdbc:mysql://localhost:3306/Hospital?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String password = "";
        try (Connection conn = DriverManager.getConnection(db, user, password)) {
            Statement stm = (Statement) conn.createStatement();

            String query = "INSERT INTO roominformation VALUES('" + Integer.parseInt(textRoomNo.getText()) + "'," + Integer.parseInt(textbuildingNo.getText()) + ",'" + textRoomType.getText() + "'," + Integer.parseInt(textFloorNo.getText()) + "," + Integer.parseInt(textWardNo.getText()) + ",'" + textPrice.getText() + "')";
            stm.executeUpdate(query);
        }
        reftable();
    }

    void reftable() throws ClassNotFoundException, SQLException {
        room.clear();
        Class.forName("com.mysql.cj.jdbc.Driver");
        String db = "jdbc:mysql://localhost:3306/Hospital?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String password = "";
        try (Connection conn = DriverManager.getConnection(db, user, password)) {
            String query = "SELECT * FROM roominformation";
            try (PreparedStatement stm = (PreparedStatement) conn.prepareStatement(query);
                    ResultSet rs = (ResultSet) stm.executeQuery()) {
                while (rs.next()) {

                    room.add(new Room(
                            rs.getString("RoomNo"),
                            rs.getInt("BuildingNo"),
                            rs.getString("RoomType"),
                            rs.getInt("FloorNo"),
                            rs.getInt("WardNo"),
                            Double.valueOf(rs.getString("Price"))
                    ));

                    tableView.setItems(room);

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
    void delete(ActionEvent event
    ) {
        ObservableList<Room> selectedRows, allinfo;
        allinfo = tableView.getItems();

        selectedRows = tableView.getSelectionModel().getSelectedItems();

        for (Room r : selectedRows) {
            allinfo.remove(r);
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        roomNo.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        buildingNo.setCellValueFactory(new PropertyValueFactory<>("buildingNo"));
        roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        floorNo.setCellValueFactory(new PropertyValueFactory<>("floorNo"));
        wardNo.setCellValueFactory(new PropertyValueFactory<>("wardNo"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableView.setEditable(true);
        roomNo.setCellFactory(TextFieldTableCell.forTableColumn());
        buildingNo.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        roomType.setCellFactory(TextFieldTableCell.forTableColumn());
        floorNo.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        wardNo.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        price.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }
    /* dummy data
    public ObservableList<Room> getInfo() {

        room.add(new Room("A1", 1, "Single Ac Room", 2, 2, 600.00));
        room.add(new Room("A2", 2, "Single Ac Room", 3, 3, 600.00));
        room.add(new Room("A3", 3, "Single Non Ac Room", 4, 4, 300.00));
        return room;

    }

     */
}

