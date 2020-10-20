package util;

public class AdminTM {
    private String id;
    private String name;
    private String contact;
    private String userId;

    public AdminTM() {
    }

    public AdminTM(String id, String name, String contact, String userId) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.userId = userId;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AdminTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
