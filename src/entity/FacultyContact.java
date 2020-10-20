package entity;

import java.io.Serializable;

public class FacultyContact implements SuperEntity {

    private FacultyContactPK facultyContactPK;

    public FacultyContact(String string, String rstString) {
    }

    public FacultyContact() {
        this.facultyContactPK = facultyContactPK;
    }

    public FacultyContactPK getFacultyContactPK() {
        return facultyContactPK;
    }

    public void setFacultyContactPK(FacultyContactPK facultyContactPK) {
        this.facultyContactPK = facultyContactPK;
    }

    @Override
    public String toString() {
        return "FacultyContact{" +
                "facultyContactPK=" + facultyContactPK +
                '}';
    }
}
