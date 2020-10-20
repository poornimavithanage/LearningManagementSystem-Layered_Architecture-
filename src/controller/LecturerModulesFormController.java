package controller;

import business.BOFactory;
import business.BOType;
import business.custom.LecturerBO;
import business.custom.ModuleBO;
import business.custom.impl.LecturerBOImpl;
import business.custom.impl.ModuleBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.CourseTM;
import util.FacultyTM;
import util.ModuleTM;

import java.io.IOException;
import java.util.List;

public class LecturerModulesFormController {
    public AnchorPane root;
    public JFXButton btnDashboard;
    public JFXComboBox<CourseTM> cmbCourses;
    public JFXComboBox<FacultyTM> cmbFaculty;
    public ListView<ModuleTM> lstModules;
    public static String lecturerId;

    private LecturerBO lecturerBO = BOFactory.getInstance().getBO(BOType.LECTURER);
    private ModuleBO moduleBO= BOFactory.getInstance().getBO(BOType.MODULE);

    public void initialize() throws Exception {
//        lecturerId = "L001";
        loadAllFacultiesOfLecturer(LoginFormController.loginId);
//        loadAllCoursesOfLecturerInFaculty("L001","F001");
//        loadAllCourseModules("C001");
        cmbCourses.setVisible(false);

        cmbFaculty.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<FacultyTM>() {
            @Override
            public void changed(ObservableValue<? extends FacultyTM> observable, FacultyTM oldValue, FacultyTM selectedFaculty) {
                if (selectedFaculty==null) {
                    return;
                }
                cmbCourses.setVisible(true);
                try {
                    loadAllCoursesOfLecturerInFaculty(LoginFormController.loginId,selectedFaculty.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        cmbCourses.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CourseTM>() {
            @Override
            public void changed(ObservableValue<? extends CourseTM> observable, CourseTM oldValue, CourseTM selectedCourse) {
                if(selectedCourse==null){
                    return;
                }
                try {
                    loadAllCourseModules(selectedCourse.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        lstModules.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ModuleTM>() {
            @Override
            public void changed(ObservableValue<? extends ModuleTM> observable, ModuleTM oldValue, ModuleTM selectedModule) {
                if(selectedModule==null){
                    return;
                }
                try {
                    loadModuleContent();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void btnCourses_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/LecturerCoursesForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnModules_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/LecturerModulesForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnAccount_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/LecturerAccountForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void loadAllFacultiesOfLecturer(String lecturerId) throws Exception {
        cmbFaculty.getItems().clear();
        List<FacultyTM> faculties = lecturerBO.getLecturerFaculties(lecturerId);
        ObservableList<FacultyTM> facultyTMS = FXCollections.observableArrayList(faculties);
        cmbFaculty.setItems(facultyTMS);
    }

    public void loadAllCoursesOfLecturerInFaculty(String lecturerId, String facultyId) throws Exception {
        cmbCourses.getItems().clear();
        List<CourseTM> lecturerFacultyCourses = lecturerBO.getLecturerFacultyCourses(lecturerId, facultyId);
        ObservableList<CourseTM> courseTMS = FXCollections.observableArrayList(lecturerFacultyCourses);
        cmbCourses.setItems(courseTMS);
    }

    public void loadAllCourseModules(String courseId) throws Exception {
        lstModules.getItems().clear();
        List<ModuleTM> courseModules = moduleBO.getCourseModules(courseId);
        ObservableList<ModuleTM> moduleTMS = FXCollections.observableArrayList(courseModules);
        lstModules.setItems(moduleTMS);
    }

    public void loadModuleContent() throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/LecturerModuleContent.fxml"));
        Scene mainScene = new Scene(root);
        Stage mainStage = (Stage) this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

}
