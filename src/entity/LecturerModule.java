package entity;

public class LecturerModule implements SuperEntity {
    private LecturerModulePK lecturerModulePK;

    public LecturerModule() {
    }

    public LecturerModule(String moduleId, String lectureId) {
        this.lecturerModulePK = new LecturerModulePK(moduleId,lectureId);
    }

    public LecturerModule(LecturerModulePK lecturerModulePK) {
        this.lecturerModulePK = lecturerModulePK;
    }

    public LecturerModulePK getLecturerModulePK() {
        return lecturerModulePK;
    }

    public void setLecturerModulePK(LecturerModulePK lecturerModulePK) {
        this.lecturerModulePK = lecturerModulePK;
    }
}
