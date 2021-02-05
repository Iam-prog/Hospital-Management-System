
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

public class SurgaeryInformationController implements Initializable {

    @FXML
    private TextField textId;

    @FXML
    private TableColumn<Surgery, String> Department;

    @FXML
    private TextField textName;

    @FXML
    private TextField textDepartment;

    @FXML
    private TableView<Test> tableView;

    @FXML
    private TableColumn<Surgery, String> id;

    @FXML
    private TextField textCost;

    @FXML
    private TableColumn<Surgery, Double> Cost;

    @FXML
    private TableColumn<Surgery, String> Name;

    ObservableList<Test> test = FXCollections.observableArrayList();

    public void changeId(TableColumn.CellEditEvent edittedCell) {
        Test infoSelected = tableView.getSelectionModel().getSelectedItem();
        infoSelected.setId(edittedCell.getNewValue().toString());

    }

    public void changeName(TableColumn.CellEditEvent edittedCell) {
        Test infoSelected = tableView.getSelectionModel().getSelectedItem();
        infoSelected.setName(edittedCell.getNewValue().toString());

    }

    public void changeDepartment(TableColumn.CellEditEvent edittedCell) {
        Test infoSelected = tableView.getSelectionModel().getSelectedItem();
        infoSelected.setDepartment(edittedCell.getNewValue().toString());

    }

    public void changeCost(TableColumn.CellEditEvent edittedCell) {
        Test infoSelected = tableView.getSelectionModel().getSelectedItem();
        infoSelected.setCost(Double.parseDouble(edittedCell.getNewValue().toString()));

    }

    @FXML
    void add(ActionEvent event) throws SQLException, ClassNotFoundException {
        Surgery newtest = new Surgery(textId.getText(), textName.getText(), textDepartment.getText(), Double.valueOf(textCost.getText()));
        tableView.getItems().add(newtest);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String db = "jdbc:mysql://localhost:3306/Hospital?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String password = "";
        try (Connection conn = DriverManager.getConnection(db, user, password)) {
            Statement stm = (Statement) conn.createStatement();
            String query = "INSERT INTO surgeryinformation VALUES(" + Integer.parseInt(textId.getText()) + ",'" + textName.getText() + "','" + textDepartment.getText() + "','" + textCost.getText() + "')";
            stm.executeUpdate(query);
        }
        reftable();
    }

    void reftable() throws ClassNotFoundException, SQLException {
        test.clear();
        Class.forName("com.mysql.cj.jdbc.Driver");
        String db = "jdbc:mysql://localhost:3306/Hospital?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String password = "";
        try (Connection conn = DriverManager.getConnection(db, user, password)) {
            String query = "SELECT * FROM surgeryinformation";
            try (PreparedStatement stm = (PreparedStatement) conn.prepareStatement(query);
                    ResultSet rs = (ResultSet) stm.executeQuery()) {
                while (rs.next()) {

                    test.add(new Surgery(
                            rs.getString("Id"),
                            rs.getString("Name"),
                            rs.getString("Department"),
                            Double.valueOf(rs.getString("Cost"))
                    ));
                    tableView.setItems(test);
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
    void delete(ActionEvent event) {
        ObservableList<Test> selectedRows, allinfo;
        allinfo = tableView.getItems();
        selectedRows = tableView.getSelectionModel().getSelectedItems();
        for (Test dia : selectedRows) {
            allinfo.remove(dia);
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
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Department.setCellValueFactory(new PropertyValueFactory<>("Department"));
        Cost.setCellValueFactory(new PropertyValueFactory<>("Cost"));
        // tableView.setItems(getInfo());    
        tableView.setEditable(true);
        id.setCellFactory(TextFieldTableCell.forTableColumn());
        Name.setCellFactory(TextFieldTableCell.forTableColumn());
        Department.setCellFactory(TextFieldTableCell.forTableColumn());
        Cost.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    /* 
   dummy data   
    public ObservableList<Test> getInfo() {
        //   File file = new File("Medicine.dat");
        //ObservableList<Medicine> info = FXCollections.observableArrayList();
        test.add(new Surgery("AA", "Dd", "CC", 2000));
        test.add(new Surgery("AAa", "DdD", "CCc", 3000));
        test.add(new Surgery("AAaa", "DdDD", "CCcc", 4000));
         try {
            FileOutputStream output = new FileOutputStream(file);
            ObjectOutputStream obout = new ObjectOutputStream(output);
            for (Medicine m : info) {
                obout.writeObject(m);
            }
            obout.close();
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MedicineStoreController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MedicineStoreController.class.getName()).log(Level.SEVERE, null, ex);
        }         
        File file = new File("Medicine.dat");
        FileInputStream output = new FileInputStream(file);
        try (ObjectInputStream obout = new ObjectInputStream(output);) {
            ArrayList< Medicine> med = new ArrayList<>();
            for (Medicine m1 : med) {
                m1.add(obout.readObject());
            }
            //Medicine m = (Medicine) obout.readObject();
            /*System.out.println("name" + m.getBrandName());           
               System.out.println(med.get(2).getBrandName());       
        for (Medicine m1 : med) {
            info.add(new Medicine(m1.getId(), m1.getBrandName(), m1.getContains(), m1.getDosageform(), m1.getManufacturer(), m1.getPrice()));
        }
    }     
        return test;
    }     
     */
}

