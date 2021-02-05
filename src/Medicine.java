
import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;

public class Medicine implements Serializable {

    private SimpleStringProperty id, brandName, contains, dosageform, manufacturer;
    private double price;

    public Medicine() {
    }

    public Medicine(String id, String brandName, String contains, String dosageform, String manufacturer, double price) {
        this.id = new SimpleStringProperty(id);
        this.brandName = new SimpleStringProperty(brandName);
        this.contains = new SimpleStringProperty(contains);
        this.dosageform = new SimpleStringProperty(dosageform);
        this.price = price;
        this.manufacturer = new SimpleStringProperty(manufacturer);

    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id = new SimpleStringProperty(id);
    }

    public String getBrandName() {
        return brandName.get();
    }

    public void setBrandName(String brandName) {
        this.brandName = new SimpleStringProperty(brandName);
    }

    public String getContains() {
        return contains.get();
    }

    public void setContains(String contains) {
        this.contains = new SimpleStringProperty(contains);
    }

    public String getDosageform() {
        return dosageform.get();
    }

    public void setDosageform(String dosageform) {
        this.dosageform = new SimpleStringProperty(dosageform);
    }

    public String getManufacturer() {
        return manufacturer.get();
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = new SimpleStringProperty(manufacturer);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Medicine " + "id=" + id + ", brandName=" + brandName + ", contains=" + contains + ", dosageform=" + dosageform + ", manufacturer=" + manufacturer + ", price=" + price + '}';
    }

    void add(Object readObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

