package entity;

import java.io.Serializable;

public class FacultyContactPK implements SuperEntity {

    private String id;
    private String contact;

    public FacultyContactPK() {
    }

    public FacultyContactPK(String id, String contact) {
        this.id = id;
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "FacultyContactPK{" +
                "id='" + id + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
