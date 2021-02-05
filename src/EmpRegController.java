
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EmpRegController implements Initializable {

    @FXML
    private ChoiceBox division;

    @FXML
    private TextField firstName;

    @FXML
    private TextField thana;

    @FXML
    private TextField district;

    @FXML
    private ChoiceBox bloodGroup;

    @FXML
    private TextField lastName;

    @FXML
    private DatePicker dateOfBirthPicker;

    @FXML
    private PasswordField pass;

    @FXML
    private PasswordField conpass;

    @FXML
    private TextField userName;

    @FXML
    private TextField email;

    @FXML
    private TextField id;

    @FXML
    private TextField age;

    @FXML
    private TextField designation;

    @FXML
    private Label patImLabel;

    @FXML
    private Label regMessLabel;

    private File filePath;
    Image img;
    public static ArrayList<Employee> employee = new ArrayList<>();

    public static ArrayList emp() {
        return employee;
    }

    @FXML
    public void showage() {
        Calendar now = Calendar.getInstance();
        int y = now.get(Calendar.YEAR);
        int dateOfBirth = (dateOfBirthPicker.getValue().getYear());
        int age1 = y - dateOfBirth;
        age.setText(Integer.toString(age1) + " Years");
    }

    public int age() {
        Calendar now = Calendar.getInstance();
        int y = now.get(Calendar.YEAR);
        int dateOfBirth = (dateOfBirthPicker.getValue().getYear());
        int age1 = y - dateOfBirth;
        return age1;

    }

    @FXML
    void handleButtonActionRegister(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        boolean found1 = false;
        if (id.getText().length() > 0 && firstName.getText().length() > 0 && lastName.getText().length() > 0 && userName.getText().length() > 0 && district.getText().length() > 0 && thana.getText().length() > 0 && pass.getText().length() > 0 && conpass.getText().length() > 0 && email.getText().length() > 0 && designation.getText().length() > 0 && !division.getSelectionModel().isEmpty() && !bloodGroup.getSelectionModel().isEmpty() && dateOfBirthPicker.getValue() != null) {
            if (pass.getText().trim().equals(conpass.getText().trim())) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(DoctorPersonalViewWindowController.class.getName()).log(Level.SEVERE, null, ex);
                }
                String db = "jdbc:mysql://localhost:3306/Hospital?zeroDateTimeBehavior=convertToNull";
                String user = "root";
                String password = "";
                try (Connection conn = DriverManager.getConnection(db, user, password)) {
                    String query = "SELECT * FROM `employees` WHERE `Id`=" + Integer.parseInt(id.getText()) + "";
                    PreparedStatement stm1;
                    stm1 = conn.prepareStatement(query);

                    try (ResultSet rs = stm1.executeQuery()) {
                        if (rs.next()) {
                            found1 = true;
                        }

                    }
                    stm1.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DoctorPersonalViewWindowController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (found1 == false) {
                    employee.add(new Employee(Integer.parseInt(id.getText()), firstName.getText(), lastName.getText(), division.getValue().toString(), district.getText(), thana.getText(), bloodGroup.getValue().toString(), userName.getText(), pass.getText(), email.getText(), dateOfBirthPicker.getValue(), designation.getText(), img));
                    try (Connection conn = DriverManager.getConnection(db, user, password)) {
                        Statement stm = (Statement) conn.createStatement();
                        String query = "INSERT INTO employees VALUES(" + Integer.parseInt(id.getText()) + ",'" + firstName.getText() + " " + lastName.getText() + "','" + email.getText() + "','" + thana.getText() + "," + district.getText() + "," + division.getValue().toString() + "','" + bloodGroup.getValue().toString() + "','" + dateOfBirthPicker.getValue().format(DateTimeFormatter.ofPattern("dd - LLLL - yyyy")) + "','" + userName.getText() + "','" + pass.getText() + "','" + designation.getText() + "'," + age() + ")";
                        stm.executeUpdate(query);
                    }
                    Parent parent = FXMLLoader.load(getClass().getResource("CompRegWindow1.fxml"));
                    Scene scene = new Scene(parent);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setTitle("Message Window");
                    window.setScene(scene);
                    window.show();
                } else {
                    regMessLabel.setText("This Id already Exist.Try different Id.");
                }
            } else {
                regMessLabel.setText("Password Doesn't Match");
            }
        } else {
            regMessLabel.setText("Field(s) Not Filled Properly");
        }

    }

    @FXML
    void handleButtonActionUploadImage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Uplode Image");
        ArrayList<String> imgExtension = new ArrayList<>();
        imgExtension.addAll(Arrays.asList("*.jpg", "*jpeg", "*.png", "*.bmp"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", imgExtension));
        this.filePath = fc.showOpenDialog(null);
        String url = this.filePath.getAbsolutePath();
        try {
            img = new Image(new FileInputStream(new File(url)));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EmpRegController.class.getName()).log(Level.SEVERE, null, ex);
        }
        patImLabel.setText(this.filePath.getName());
    }

    @FXML
    public void handleButtonActionGoBack(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("EmployeeWindow.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Employee Login Window");
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        bloodGroup.getItems().addAll("A+", "B+", "AB+", "O+", "A-", "B-", "AB-", "O-");
        bloodGroup.setValue("A+");
        division.getItems().addAll("Dhaka", "Khulna", "Chittagong", "Rajshahi ", "Sylhet", "Barisal", "Rangpur", "Mymensingh");
        division.setValue("Dhaka");
    }

}

