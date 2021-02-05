
public abstract class Test {

    protected String id, name, department;
    protected double cost;

    public Test() {
    }

    public Test(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Test(String id, String name, String department, double cost) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getCost() {
        return cost;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Test" + "id=" + id + ", name=" + name + ", department=" + department + ", cost=" + cost;
    }

}

