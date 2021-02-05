
import java.io.IOException;
import java.io.Serializable;
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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

public class MedicineStoreController implements Initializable, Serializable {

    @FXML
    private TableColumn<Medicine, String> brandName;

    @FXML
    private TextField textManufacturer;

    @FXML
    private TableColumn<Medicine, String> dosageForm;

    @FXML
    private TableView<Medicine> tableView;

    @FXML
    private TableColumn<Medicine, Double> price;

    @FXML
    private TextField textId;

    @FXML
    private TableColumn<Medicine, String> contains;

    @FXML
    private TableColumn<Medicine, String> manufacturer;

    @FXML
    private TextField textDosageForm;

    @FXML
    private TextField textPrice;

    @FXML
    private TextField textBrandName;

    @FXML
    private TableColumn<Medicine, String> id;

    @FXML
    private TextField textContains;

    ObservableList<Medicine> info = FXCollections.observableArrayList();

    //public void file() throws FileNotFoundException, IOException {
    //  File file = new File("Medicine.dat");
    //   FileOutputStream output = new FileOutputStream(file);
    //   ObjectOutputStream obout = new ObjectOutputStream(output);
    //   for (Medicine m : info) {
    //    obout.writeObject(m);
    //   }
    //  obout.close();
    //  output.close();
    // }
    @FXML
    void add(ActionEvent event) throws ClassNotFoundException, SQLException {
        Medicine newmed = new Medicine(textId.getText(), textBrandName.getText(), textContains.getText(), textDosageForm.getText(), textManufacturer.getText(), Double.valueOf(textPrice.getText()));
        tableView.getItems().add(newmed);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String db = "jdbc:mysql://localhost:3306/Hospital?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String password = "";
        try (Connection conn = DriverManager.getConnection(db, user, password)) {
            Statement stm = (Statement) conn.createStatement();
            String query = "INSERT INTO medicinestore VALUES(" + Integer.parseInt(textId.getText()) + ",'" + textBrandName.getText() + "','" + textContains.getText() + "','" + textDosageForm.getText() + "','" + textManufacturer.getText() + "','" + textPrice.getText() + "')";
            stm.executeUpdate(query);
        }
        reftable();
    }

    @FXML
    void delete(ActionEvent event) {
        ObservableList<Medicine> selectedRows, allinfo;
        allinfo = tableView.getItems();
        selectedRows = tableView.getSelectionModel().getSelectedItems();
        for (Medicine med : selectedRows) {
            allinfo.remove(med);
        }
    }

    void reftable() throws ClassNotFoundException, SQLException {
        info.clear();
        Class.forName("com.mysql.cj.jdbc.Driver");
        String db = "jdbc:mysql://localhost:3306/Hospital?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String password = "";
        try (Connection conn = DriverManager.getConnection(db, user, password)) {
            String query = "SELECT * FROM medicinestore";
            try (PreparedStatement stm = (PreparedStatement) conn.prepareStatement(query);
                    ResultSet rs = (ResultSet) stm.executeQuery()) {
                while (rs.next()) {

                    info.add(new Medicine(
                            rs.getString("Id"),
                            rs.getString("BrandName"),
                            rs.getString("Contains"),
                            rs.getString("DosageForm"),
                            rs.getString("Manufacturer"),
                            Double.valueOf(rs.getString("Price"))
                    ));
                    tableView.setItems(info);
                }
            }
            conn.close();
        }
    }

    @FXML
    void load(ActionEvent event) throws ClassNotFoundException, SQLException {
        reftable();
    }

    public void changeId(CellEditEvent edittedCell) {
        Medicine infoSelected = tableView.getSelectionModel().getSelectedItem();
        infoSelected.setId(edittedCell.getNewValue().toString());
    }

    public void changeBrandName(CellEditEvent edittedCell) {
        Medicine infoSelected = tableView.getSelectionModel().getSelectedItem();
        infoSelected.setBrandName(edittedCell.getNewValue().toString());
    }

    public void changeContains(CellEditEvent edittedCell) {
        Medicine infoSelected = tableView.getSelectionModel().getSelectedItem();
        infoSelected.setContains(edittedCell.getNewValue().toString());
    }

    public void changeDosageForm(CellEditEvent edittedCell) {
        Medicine infoSelected = tableView.getSelectionModel().getSelectedItem();
        infoSelected.setDosageform(edittedCell.getNewValue().toString());
    }

    public void changeManufacturer(CellEditEvent edittedCell) {
        Medicine infoSelected = tableView.getSelectionModel().getSelectedItem();
        infoSelected.setManufacturer(edittedCell.getNewValue().toString());
    }

    public void changePrice(CellEditEvent edittedCell) {
        Medicine infoSelected = tableView.getSelectionModel().getSelectedItem();
        infoSelected.setPrice(Double.parseDouble(edittedCell.getNewValue().toString()));
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
        brandName.setCellValueFactory(new PropertyValueFactory<>("brandName"));
        contains.setCellValueFactory(new PropertyValueFactory<>("contains"));
        dosageForm.setCellValueFactory(new PropertyValueFactory<>("dosageform"));
        manufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableView.setEditable(true);

        id.setCellFactory(TextFieldTableCell.forTableColumn());
        brandName.setCellFactory(TextFieldTableCell.forTableColumn());
        contains.setCellFactory(TextFieldTableCell.forTableColumn());
        dosageForm.setCellFactory(TextFieldTableCell.forTableColumn());
        manufacturer.setCellFactory(TextFieldTableCell.forTableColumn());
        price.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }
    /*
    public ObservableList<Medicine> getInfo() {
        //   File file = new File("Medicine.dat");
        //ObservableList<Medicine> info = FXCollections.observableArrayList();
        info.add(new Medicine("1", "ACE Plus", "Paracetamol BP 500mg + Caffeine USP 65mg/tablet ", "Tablet (film-coated)", "Square Pharmaceuticals Ltd.", 300.00));
        info.add(new Medicine("2", "NAPA EXTRA", "Paracetamol BP 500mg + Caffeine USP 65mg/tablet ", "Tablet (film-coated)", "Beximco Pharmaceuticals Ltd.", 150.00));
        info.add(new Medicine("3", "PAINIL Plus", "Paracetamol BP 500mg + Caffeine USP 65mg/tablet ", "Tablet (film-coated)", "Kumudini Pharma Ltd.", 300.00));

        /*   try {
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
            /*           System.out.println("name" + m.getBrandName());
            
            System.out.println(med.get(2).getBrandName());
         
         
        for (Medicine m1 : med) {
            info.add(new Medicine(m1.getId(), m1.getBrandName(), m1.getContains(), m1.getDosageform(), m1.getManufacturer(), m1.getPrice()));
        }
    }
        
        return info;
    } */
}

