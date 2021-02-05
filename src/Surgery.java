
public class Surgery extends Test {

    public Surgery() {
        super();
    }

    public Surgery(String id, String name, String department, double cost) {
        super(id, name, department, cost);
    }

    public Surgery(String id, String name) {
        super(id, name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

}


