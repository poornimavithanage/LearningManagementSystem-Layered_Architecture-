package controller;

import business.BOFactory;
import business.BOType;
import business.custom.StudentBO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entity.CustomEntity;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import util.AnnouncementTM;
import util.CourseTM;

import java.io.IOException;
import java.util.List;

import static controller.LoginFormController.loginId;

public class StudentCoursesFormController {
    public AnchorPane root;
    public JFXComboBox<CourseTM> cmbCourses;
    public Label lblType;
    public Label lblTitle;
    public VBox vBoxAnnouncements;
    public Label lblStudentId;
    private StudentBO studentBO = BOFactory.getInstance().getBO(BOType.STUDENT);

    public void setStudentId(String id){
        lblStudentId.setText(id);
        loadAllCourses();

    }

    public void initialize(){
//        System.out.println(loginId);
//        lblStudentId.setText("S001");
        System.out.println(LoginFormController.loginId);
        loadAllCourses();
        cmbCourses.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CourseTM>() {
            @Override
            public void changed(ObservableValue<? extends CourseTM> observable, CourseTM oldValue, CourseTM newValue) {
                if (newValue == null) {
                    lblTitle.setText("");
                    lblType.setText("");
                    return;
                }
                try {
                    System.out.println(lblStudentId.getText());
                    CourseTM courseDetails = studentBO.getCourseDetails(LoginFormController.loginId, newValue.getId());
                    lblTitle.setText(courseDetails.getTitle());
                    lblType.setText(courseDetails.getType());

                    vBoxAnnouncements.getChildren().clear();
                    List<AnnouncementTM> announcements = studentBO.getAnnouncements(newValue.getId());
                    for (AnnouncementTM announcement : announcements) {
                        Label label = new Label(announcement.toString());
                        vBoxAnnouncements.getChildren().add(label);
                        label.setPrefWidth(806);
                        label.setPrefHeight(52);
                        label.setFont(new Font(18));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void btnCourses_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/StudentCoursesForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnModules_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/StudentModuleForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnAccount_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/StudentAccountForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }
    private void loadAllCourses() {
        try {
            cmbCourses.getItems().clear();
            cmbCourses.setItems(FXCollections.observableArrayList(studentBO.getStudentCourses(LoginFormController.loginId)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
