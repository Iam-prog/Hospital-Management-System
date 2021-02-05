
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DoctorPersonalViewWindowController implements Initializable {

    @FXML
    private Label bloodGroup;

    @FXML
    private Label address;

    @FXML
    private Label pass;

    @FXML
    private Label dOB;

    @FXML
    private Label name;

    @FXML
    private Label designation;

    @FXML
    private Label userName;

    @FXML
    private Label age;

    @FXML
    private Label email;

    @FXML
    private ImageView ima;

    @FXML
    void logOut(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("DoctorWindow.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Doctor Login Window");
        window.setScene(scene);
        window.show();

    }

    int id = DoctorWindowController.id;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DoctorPersonalViewWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String db = "jdbc:mysql://localhost:3306/Hospital?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String password = "";
        try (Connection conn = DriverManager.getConnection(db, user, password)) {
            String query = "SELECT * FROM `doctors` WHERE `Id`=" + id + "";
            PreparedStatement stm;
            stm = conn.prepareStatement(query);

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    name.setText(rs.getString(2));
                    age.setText(String.valueOf(rs.getInt(10)));
                    email.setText(rs.getString(3));
                    userName.setText(rs.getString(7));
                    designation.setText(rs.getString(9));
                    pass.setText(rs.getString(8));
                    dOB.setText(rs.getString(6));
                    bloodGroup.setText(rs.getString(5));
                    address.setText(rs.getString(4));

                }
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(DoctorPersonalViewWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DoctorPersonalViewWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image img1 = null;
        try {
            img1 = new Image(new FileInputStream(new File(".\\src\\default.jpg")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DoctorPersonalViewWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ima.setImage(img1);
    }
}

