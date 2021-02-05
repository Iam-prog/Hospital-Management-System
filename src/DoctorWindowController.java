
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DoctorWindowController implements Initializable {

    @FXML
    public void handleButtonActionGoBack(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Home Page");
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void handleButtonActionReg(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("DocReg.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Doctor Registration");
        window.setScene(scene);
        window.show();
    }
    @FXML
    private PasswordField pass;

    @FXML
    private TextField userName;

    @FXML
    private Label logMessLabel;

    public static int id = 0;

    @FXML
    public void handleButtonActionLog(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        String s = userName.getText();
        String ss = pass.getText();
        if (userName.getText().length() > 0 && pass.getText().length() > 0) {
            boolean found = false;
            Class.forName("com.mysql.cj.jdbc.Driver");
            String db = "jdbc:mysql://localhost:3306/Hospital?zeroDateTimeBehavior=convertToNull";
            String user = "root";
            String password = "";
            try (Connection conn = DriverManager.getConnection(db, user, password)) {
                String query = "SELECT * FROM `doctors` WHERE `UserName` =? AND `Password` =?";
                PreparedStatement stm;
                stm = conn.prepareStatement(query);
                stm.setString(1, s);
                stm.setString(2, ss);
                try (ResultSet rs = stm.executeQuery()) {
                    while (rs.next()) {
                        found = true;
                        id = rs.getInt(1);
                    }
                }
                stm.close();
            }
            if (found) {
                Parent parent = FXMLLoader.load(getClass().getResource("DoctorPersonalViewWindow.fxml"));
                Scene scene = new Scene(parent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setTitle("Doctor Personal View Window");
                window.setScene(scene);
                window.show();
            } else {
                logMessLabel.setText("UserName or Password Wrong");
                //System.out.println("Not Found");
            }
        } else {
            logMessLabel.setText("Field(s) Not Filled Properly");
        }

        /*
            for (int i = 0; i < doc.size(); i++) {
                if (doc.get(i).userName.equals(userName.getText()) && doc.get(i).pass.equals(pass.getText())) {
                    index = i;
                    System.out.println(doc.get(i).firstName);
                    found = true;
                }
            }
            File file = new File("Doctor.dat");
            FileInputStream output = new FileInputStream(file);
            ObjectInputStream obout = new ObjectInputStream(output);
            ArrayList<Doctor> doc = (ArrayList<Doctor>) obout.readObject();
            obout.close();
            Iterator<Doctor> doc1 = doc.iterator();
            System.out.println(doc.size());
            System.out.println(doc.get(0).userName);
            for (int i = 0; i < doc.size(); i++) {
                if (doc.get(i).userName.equals(userName.getText()) && doc.get(i).pass.equals(pass.getText())) {
                    index = i;
                    System.out.println(doc.get(i).firstName);
                    found = true;
                }
            }
            while (doc1.hasNext()) {
                Doctor d = doc1.next();
                if (d.userName.equals(userName.getText()) && d.pass.equals(pass.getText())) {
                    System.out.println(d.firstName);
                    found = true;
                }
            }
         */
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}

