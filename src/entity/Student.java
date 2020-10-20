package entity;

public class Student implements SuperEntity{
    private String id;
    private String facultyId;
    private String name;
    private String address;
    private String contact;
    private String nic;
    private String email;
    private String userId;

    public Student() {
    }

    public Student(String id, String facultyId, String name, String address, String contact, String nic, String email, String userId) {
        this.id = id;
        this.facultyId = facultyId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.nic = nic;
        this.email = email;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "StudentTM{" +
                "id='" + id + '\'' +
                ", facultyId='" + facultyId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", nic='" + nic + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


}
