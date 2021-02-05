
import java.time.LocalDate;
import java.util.Calendar;
import javafx.scene.image.Image;

public class Patient {

    protected String firstName;
    protected String lastName;
    protected String name;
    protected String div;
    protected String dis;
    protected String tha;
    protected String address;
    protected String bloodg;
    protected String userName;
    protected String pass;
    protected String email;
    protected LocalDate dOB;
    protected String dob;
    protected String desig;
    protected Image image;
    protected int id;
    protected int age;

    public Patient() {
    }

    public Patient(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Patient(int id, String name, String email, String address, String bloodg, String dob, int age, String userName, String desig) {
        this.name = name;
        this.bloodg = bloodg;
        this.userName = userName;
        this.email = email;
        this.desig = desig;
        this.id = id;
        this.address = address;
        this.dob = dob;
        this.age = age;
    }

    public Patient(int id, String firstName, String lastName, String div, String dis, String tha, String bloodg, String userName, String pass, String email, LocalDate dOB, String desig, Image image) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.div = div;
        this.dis = dis;
        this.tha = tha;
        this.bloodg = bloodg;
        this.userName = userName;
        this.pass = pass;
        this.email = email;
        this.dOB = dOB;
        this.desig = desig;
        this.image = image;
    }

    public Patient(int id, String firstName, String lastName, String div, String dis, String tha, String bloodg, String userName, String pass, String email, LocalDate dOB, String desig) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.div = div;
        this.dis = dis;
        this.tha = tha;
        this.bloodg = bloodg;
        this.userName = userName;
        this.pass = pass;
        this.email = email;
        this.dOB = dOB;
        this.desig = desig;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDiv() {
        return div;
    }

    public String getDis() {
        return dis;
    }

    public String getTha() {
        return tha;
    }

    public String getBloodg() {
        return bloodg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getdOB() {
        return dOB;
    }

    public String getDesig() {
        return desig;
    }

    public Image getImage() {
        return image;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDiv(String div) {
        this.div = div;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public void setTha(String tha) {
        this.tha = tha;
    }

    public void setBloodg(String bloodg) {
        this.bloodg = bloodg;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setdOB(LocalDate dOB) {
        this.dOB = dOB;
    }

    public void setDesig(String desig) {
        this.desig = desig;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int showage() {
        Calendar now = Calendar.getInstance();
        int y = now.get(Calendar.YEAR);
        int dateOfBirth = (this.dOB.getYear());
        int age = y - dateOfBirth;
        return age;
    }

}


