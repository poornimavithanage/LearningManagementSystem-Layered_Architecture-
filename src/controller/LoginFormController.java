package controller;

import business.BOFactory;
import business.BOType;
import business.custom.AdminBO;
import business.custom.LecturerBO;
import business.custom.StudentBO;
import business.custom.UserBO;
import business.custom.impl.AdminBOImpl;
import business.custom.impl.LecturerBOImpl;
import business.custom.impl.StudentBOImpl;
import business.custom.impl.UserBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.UserTM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginFormController {
    public JFXPasswordField txtPassword;
    public JFXTextField txtUsername;
    public JFXButton btnLogin;
    public JFXButton btnCancel;
    public AnchorPane root;
    public static String loginId;
//    public static String studentId;

    private UserBO userBO = BOFactory.getInstance().getBO(BOType.USER);
    private StudentBO studentBO= BOFactory.getInstance().getBO(BOType.STUDENT);

    private LecturerBO lecturerBO = new LecturerBOImpl();
    private AdminBO adminBO= new AdminBOImpl();

    public void initialize(){

    }

    public void btnLogin_OnAction(ActionEvent actionEvent) throws Exception {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        List<UserTM> allUsers = userBO.getAllUsers();

        for (UserTM user : allUsers) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                if (user.getUserRole().equals("Student")) {
//                    loadView("/view/StudentCoursesForm.fxml");
                    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/StudentCoursesForm.fxml"));
                    Parent root = fxmlLoader.load();
                    StudentCoursesFormController controller = fxmlLoader.getController();
                    Scene mainScene =  new Scene(root);
                    Stage mainStage = (Stage)this.root.getScene().getWindow();
                    mainStage.setScene(mainScene);
                    mainStage.centerOnScreen();
                    loginId = studentBO.getStudentId(user.getId());
                    controller.setStudentId(loginId);
//
                }
                else if(user.getUserRole().equals("Lecturer")){
                    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/LecturerCoursesForm.fxml"));
                    Parent root = fxmlLoader.load();
                    LecturerCoursesFormController controller = fxmlLoader.getController();
                    Scene mainScene =  new Scene(root);
                    Stage mainStage = (Stage)this.root.getScene().getWindow();
                    mainStage.setScene(mainScene);
                    mainStage.centerOnScreen();
                    loginId = lecturerBO.getLecturerId(user.getId());
                    System.out.println(loginId);
                    controller.setLecturerId(loginId);
                }
                else if(user.getUserRole().equals("Admin")){
                    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/AdminDashboard.fxml"));
                    Parent root = fxmlLoader.load();
                    AdminDashboardController controller = fxmlLoader.getController();
                    Scene mainScene =  new Scene(root);
                    Stage mainStage = (Stage)this.root.getScene().getWindow();
                    mainStage.setScene(mainScene);
                    mainStage.centerOnScreen();
                    loginId = adminBO.getAdminId(user.getId());
//                    controller.setStudentId(loginId);
                }
                else{
                    if (user.getPassword()!=txtPassword .getText()|| user.getUsername()!=txtUsername.getText()) {
                        //TODO: ADD ALERT
                        new Alert(Alert.AlertType.ERROR,"Incorrect username or password. Please try again!", ButtonType.OK).show();
                    }
                }
            }
        }
    }

    public void btnCancel_OnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void loadView(String location) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource(location));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

}
