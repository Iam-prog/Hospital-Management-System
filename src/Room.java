
import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;

public class Room implements Serializable {

    private SimpleStringProperty roomNo, roomType;
    private int buildingNo, floorNo, wardNo;
    private double price;

    public Room() {
    }

    public Room(int buildingNo, String roomNo) {
        this.roomNo = new SimpleStringProperty(roomNo);;
        this.buildingNo = buildingNo;
    }

    public Room(String roomNo, int buildingNo, String roomType, int floorNo, int wardNo, double price) {
        this.roomNo = new SimpleStringProperty(roomNo);
        this.roomType = new SimpleStringProperty(roomType);
        this.buildingNo = buildingNo;
        this.floorNo = floorNo;
        this.wardNo = wardNo;
        this.price = price;
    }

    public String getRoomNo() {
        return roomNo.get();
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = new SimpleStringProperty(roomNo);
    }

    public String getRoomType() {
        return roomType.get();
    }

    public void setRoomType(String roomType) {
        this.roomType = new SimpleStringProperty(roomType);
    }

    public int getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(int buildingNo) {
        this.buildingNo = buildingNo;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public int getWardNo() {
        return wardNo;
    }

    public void setWardNo(int wardNo) {
        this.wardNo = wardNo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Room" + "roomNo=" + roomNo + ", roomType=" + roomType + ", buildingNo=" + buildingNo + ", floorNo=" + floorNo + ", wardNo=" + wardNo + ", price=" + price;
    }

}

