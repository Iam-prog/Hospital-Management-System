
public class Admission {

    private int addid;
    private int patid;
    private int docid;
    private int appoid;

    public Admission() {
    }

    public Admission(int addid, int patid, int docid, int appoid) {
        this.addid = addid;
        this.patid = patid;
        this.docid = docid;
        this.appoid = appoid;
    }

    public int getAddid() {
        return addid;
    }

    public void setAddid(int addid) {
        this.addid = addid;
    }

    public int getPatid() {
        return patid;
    }

    public void setPatid(int patid) {
        this.patid = patid;
    }

    public int getDocid() {
        return docid;
    }

    public void setDocid(int docid) {
        this.docid = docid;
    }

    public int getAppoid() {
        return appoid;
    }

    public void setAppoid(int appoid) {
        this.appoid = appoid;
    }

}

