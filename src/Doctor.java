
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import javafx.scene.image.Image;

public class Doctor implements Serializable {

    protected int id;
    protected String firstName;
    protected String lastName;
    protected String Name;
    protected String div;
    protected String dis;
    protected String tha;
    protected String add;
    protected String bloodg;
    protected String userName;
    protected String pass;
    protected String email;
    protected transient LocalDate dOB;
    protected String dob;
    protected String desig;
    protected String impath;
    protected transient Image image;
    public static int count = 0;
    protected int age;

    public Doctor() {
        count++;
    }

    public Doctor(int id, String Name, String email, String add, String bloodg, String dob, int age, String userName, String desig) {
        this.id = id;
        this.Name = Name;
        this.email = email;
        this.add = add;
        this.bloodg = bloodg;
        this.dob = dob;
        this.age = age;
        this.userName = userName;
        this.desig = desig;

    }

    public Doctor(int id, String Name) {
        this.id = id;
        this.Name = Name;
    }

    public Doctor(int id, String Name, String email, String add, String bloodg, String dob, String userName, String pass, String desig, String impath) {
        this.id = id;
        this.Name = Name;
        this.add = add;
        this.bloodg = bloodg;
        this.userName = userName;
        this.pass = pass;
        this.email = email;
        this.dob = dob;
        this.desig = desig;
        this.impath = impath;
    }

    public Doctor(int id, String firstName, String lastName, String div, String dis, String tha, String bloodg, String userName, String pass, String email, String desig) {
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
        this.desig = desig;
    }

    public Doctor(int id, String firstName, String lastName, String div, String dis, String tha, String bloodg, String userName, String pass, String email, LocalDate dOB, String desig, Image image) {
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

        count++;
    }

    Doctor(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getImpath() {
        return impath;
    }

    public void setImpath(String impath) {
        this.impath = impath;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Doctor.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Doctor{" + "firstName=" + firstName + ", lastName=" + lastName + ", div=" + div + ", dis=" + dis + ", tha=" + tha + ", bloodg=" + bloodg + ", userName=" + userName + ", pass=" + pass + ", email=" + email + ", desig=" + desig + '}';
    }

}

