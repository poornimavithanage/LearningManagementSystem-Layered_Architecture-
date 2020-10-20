package controller;

import business.BOFactory;
import business.BOType;
import business.custom.CourseBO;
import business.custom.FacultyBO;
import business.custom.LecturerBO;
import business.custom.StudentBO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardController {
    public AnchorPane root;
    public Label lblNoofStudents;
    public Label lblNoofLecturer;
    public Label lblNoofFaculties;
    public Label lblNoofCourses;

    private StudentBO studentBO = BOFactory.getInstance().getBO(BOType.STUDENT);
    private LecturerBO lecturerBO = BOFactory.getInstance().getBO(BOType.LECTURER);
    private FacultyBO facultyBO = BOFactory.getInstance().getBO(BOType.FACULTY);
    private CourseBO courseBO = BOFactory.getInstance().getBO(BOType.COURSE);

    public void initialize(){
        try {
            String studentCount = studentBO.getStudentCount();
            lblNoofStudents.setText(studentCount);
            String lecturerCount = lecturerBO.getLecturerCount();
            lblNoofLecturer.setText(lecturerCount);
            String facultyCount = facultyBO.getFacultyCount();
            lblNoofFaculties.setText(facultyCount);
            String courseCount = courseBO.getCoursesCount();
            lblNoofCourses.setText(courseCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnDashboard_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/AdminDashboard.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnCourses_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/AdminCoursesForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnModules_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/AdminModuleForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnAccount_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/AdminAccountForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnStudent_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/AdminStudentForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnLecturer_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/LecturerAccountForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }
}
