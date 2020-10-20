package entity;

public class LecturerModulePK implements SuperEntity{
    private String moduleId;
    private String lecturerId;

    public LecturerModulePK() {
    }

    public LecturerModulePK(String moduleId, String lecturerId) {
        this.moduleId = moduleId;
        this.lecturerId = lecturerId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }
}
