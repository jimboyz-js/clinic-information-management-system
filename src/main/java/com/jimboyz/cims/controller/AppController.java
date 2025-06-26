package com.jimboyz.cims.controller;

import com.jimboyz.cims.GetMacAddr;
import com.jimboyz.cims.MySimpleDateFormat;
import com.jimboyz.cims.err.ErrorDialog;
import com.jimboyz.cims.err.Message;
import com.jimboyz.cims.model.DataModel;
import com.jimboyz.cims.model.DateValidator;
import com.jimboyz.cims.model.LoginModel;
import com.jimboyz.cims.model.StudentImageFile;
import com.jimboyz.cims.model.dao.user.User;
import com.jimboyz.cims.view.*;
import com.jimboyz.cims.view.webcam.CameraView;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.File;
import java.io.Serial;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class AppController {

    private final MainFrame frame;
    private final DashboardPage dashboardPage;
    private final LoginPage loginPage;
    private final LoginModel loginModel;
    private final DataModel dataModel;
    private final NewAccountRegistrationPage newAccountRegistration;
    private AddMedicalDataPage addMedicalDataPage;
    private final ChangePasswordPage changePassword;
    private final HomePage homePage;
    private final JTable table;
    private final JTable userTable;
    private final ConstraintsList constraintsList;
    private boolean flag = false; // This boolean value can be checked if true then expected that the user want to update student data. It changed the actionCommand of a  camera button-ok.
    private SuspendedUserView suspendUserView;

    //======== using this field when selecting an image in JFileChooser in AddNewPatients ========
//    private File browseFile;

    public AppController() {

        frame = new MainFrame();
        constraintsList = ConstraintsList.getInstance();

        dashboardPage = new DashboardPage();
        loginPage = new LoginPage();
        loginModel = new LoginModel();//
        dataModel = new DataModel();//
        homePage = new HomePage();
        table = dashboardPage.getDataComponents().getTable();
        userTable = dashboardPage.getAdminPage().getTable();
        newAccountRegistration = new NewAccountRegistrationPage();
        changePassword = new ChangePasswordPage();
//        addMedicalDataPage = new AddMedicalDataPage(frame);

        constraintsList.addConstraintsNameToList("login");
        setFrameTitleName();

        frame.getFrameContainer().add(loginPage, constraintsList.getConstraintsNameByLastIndex());
        loginController();
        frame.getFrameContainer().add(homePage, "home");
        frame.getFrameContainer().add(dashboardPage, "medicalDashboard");
        frame.getFrameContainer().add(newAccountRegistration, "newAccountRegistration");
        frame.getFrameContainer().add(changePassword, "changePassword");

        treeController();
        homeController();
        tableController();
        toolbarTopController();
        toolbarBottomController();
        menuController();
//        generateMenuController();
        createAccountRegistrationController();
        changePasswordController();
        dataController(1);
//        addMedicalDataController();
        addNewPatientsController();
        updateStudentRecordController();
//        addMedicalDataController();
        setWindowListener();
        paginatedSearchController();

        frame.setVisible(true);
    }

    private void loginController() {
        loginPage.signIn(e-> {
//            dataModel = new DataModel();
//            loginModel = new LoginModel();
            loginSetup();
        });

        loginPage.signIn(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginSetup();
                }
            }
        });
    }

    private void loginSetup() {

        if(loginModel.isAuthenticated(loginPage.getUsername(), loginPage.getPassword())) {

            // check if user-account is suspended
            Date fromDate = loginModel.getUser().getFromDateSuspended();
            Date toDate = loginModel.getUser().getToDateSuspended();

            if(fromDate != null && toDate != null) {
                if(DateValidator.isTodayWithinRange(MySimpleDateFormat.getInstance().format(fromDate), MySimpleDateFormat.getInstance().format(toDate))) {
                    Message.displayMessage("User: " + loginModel.getUser().getUsername() + " with terminal key: " + loginModel.getUserTerminalKey() + " is suspended until " + MySimpleDateFormat.getInstance().format(loginModel.getUser().getToDateSuspended()));
                    return;
                }
            }

            // Check if account is disabled
            if(loginModel.getUser().isDisable()) {
                Message.displayMessage("Account " + loginModel.getUser().getUsername() + " is disabled by the admin.");
                return;
            }

            if(loginModel.isAdminLogin()) {
                changePassword.setDescription("<html>Please change your password for better security. Contact helpdesk for assistance.</html>");
                frame.showContentPane("changePassword");
                frame.setEditTitle("Change Password");
                return;
            }

            dashboardPage.getToolbarBottom().setToolbarComponents(loginPage.getUsername(), loginModel.getUserTerminalKey(), "online");
            constraintsList.addConstraintsNameToList("home");
            frame.showContentPane(constraintsList.getConstraintsNameByLastIndex());

            // Enable Action Menu
            // Move to homeController
//            actionMenu(true);
//
//            frame.getSystemActionMenu().setEnabled(!frame.getContainerConstraints().equals("home"));

            setFrameTitleName();
//            dataController();
            System.setProperty("system.username", loginPage.getUsername());

            // Enable Menu
            frame.getJMenuBar().getMenu(2).setEnabled(true);

        } else {
            Message.displaySuccessMessage("Wrong username or password.");
        }
    }

    private void actionMenu(boolean enable) {
        frame.getChangePasswordAction().setEnabled(enable);
        frame.getHomeMenuAction().setEnabled(enable);//////////

        frame.getLogoutMenuAction().setEnabled(enable);
        frame.getSystemActionMenu().setEnabled(enable);
    }

    private void treeController() {
        dashboardPage.setSelectedView(dashboardPage.getSelectedTreePath());
        dashboardPage.setTreeSelectionListener(e -> {
            dashboardPage.setSelectedView(dashboardPage.getSelectedTreePath());
            dataController(1);
        });
    }

    private void homeController() {
        homePage.medicalSelected(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                constraintsList.addConstraintsNameToList("medicalDashboard");
//                frame.getHomeMenuAction().setEnabled(true);

//                frame.getSystemActionMenu().setEnabled(!frame.getContainerConstraints().equals("home"));

                actionMenu(true);
                frame.showContentPane(constraintsList.getConstraintsNameByLastIndex());
//                frame.setEditTitle("Dashboard");
                setFrameTitleName();
            }
        });
    }
    
    
    private void dataController(int page) {
//        dataModel.fetchData(dashboardPage.getDataComponents().getTable());
//        String depCourse = dashboardPage.getSelectedTreePath();
//        String depCourse = switch (dashboardPage.getSelectedTreePath()) {
//            case "College", "High School", "TRS" -> "department";
//            case "ACT", "BSIT", "BSBA", "BSCRIM" -> "course";
//            default -> "All Records";
//        };


        dataModel.fetchData(dashboardPage.getDataComponents().getTable(),
                            dashboardPage.getTxtFirstname(),
                            dashboardPage.getTxtLastname(),
                            dashboardPage.getCmbGenderItem(),
                            dashboardPage.getCmbYearLevelItem(),
                            dashboardPage.getTxtIdNum(),
                            dashboardPage.getSelectedTreePath(), page, dataModel.getPageSize());
        dashboardPage.getDataComponents().getBasicInfoComponents().clearField();
        updateButtonState();
    }

    private void tableController() {

        dashboardPage.listSelectionListener(e -> {
            if(!e.getValueIsAdjusting()) {

//                dashboardPage.getDataComponents().getBasicInfoComponents().clearField();
//                dashboardPage.getDataComponents().getBasicInfoComponents().revalidate();

                dataModel.tableData(dashboardPage.getDataComponents().getTable());
                dashboardPage.setTxtName(dataModel.getStudentInfoModel().getName());
                dashboardPage.setTxtGender(dataModel.getStudentInfoModel().getGender());
                dashboardPage.setTxtStatus(dataModel.getStudentInfoModel().getStatus());
                dashboardPage.setTxtBirthdate(dataModel.getStudentInfoModel().getBirthdate());
                dashboardPage.setTxtAge(String.valueOf(dataModel.getStudentInfoModel().getAge()));
                dashboardPage.setTxtAddress(dataModel.getStudentInfoModel().getAddress());
                dashboardPage.setTxtEmailAdd(dataModel.getStudentInfoModel().getEmailAdd());
                dashboardPage.setTxtCourse(dataModel.getStudentInfoModel().getCourse());
                dashboardPage.setTxtYearLevel(dataModel.getStudentInfoModel().getYearLevel());
                dashboardPage.setTxtBloodType(dataModel.getStudentInfoModel().getBloodType());
                dashboardPage.setTxtPulseRate(dataModel.getStudentInfoModel().getPulseRate());
                dashboardPage.setTxtHeight(dataModel.getStudentInfoModel().getHeight());
                dashboardPage.setTxtWeight(dataModel.getStudentInfoModel().getWeight());
                dashboardPage.setTxtBp(dataModel.getStudentInfoModel().getBp());
                dashboardPage.setStudentImage(dataModel.getStudentInfoModel().getStudentImage());

                dashboardPage.getToolbarTop().tableSelectionChanged(table);

            }
        });

        // User Table
        showUser();
        adminController();
    }

    // Admin Page
    // User Table
    private void showUser() {
        ((DefaultTableModel) userTable.getModel()).setRowCount(0);
        List<User> user = dataModel.fetchingAllUserData();
        for(User users : user) {
            Date dateFrom = users.getFromDateSuspended();
            Date dateTo = users.getToDateSuspended();
            String from = dateFrom == null ? "" : MySimpleDateFormat.getInstance().format(dateFrom);
            String to = dateTo == null ? "" : MySimpleDateFormat.getInstance().format(dateTo);
            String s = from.isEmpty() && to.isEmpty() ? "" : " ~ ";
            String acctCat = users.isAdmin() ? "Admin" : "User";

            if(dateFrom != null || dateTo != null) {
                // Display in JTable with empty string if not valid
                if(!DateValidator.isTodayWithinRange(MySimpleDateFormat.getInstance().format(dateFrom), MySimpleDateFormat.getInstance().format(dateTo))) {
                    from = "";
                    to = "";
                    s = "";
                }
            }
            ((DefaultTableModel) userTable.getModel()).addRow(new Object[] {users.getId(), users.getFirstname(), users.getLastname(), users.getUsername(), users.getTerminal_key(), users.getMac_add(), acctCat, users.isDisable(), from + s + to});
        }
    }

    // === Admin Controller ===
    private void adminController() {
        dashboardPage.getAdminPage().keyMouseAdapter(new AdminPage.MyAdapter(dashboardPage.getAdminPage().getLblKey()) {
            @Override
            public void mouseClicked(MouseEvent e) {
                generateMenuController();
            }
        });

        dashboardPage.getAdminPage().updateMouseAdapter(new AdminPage.MyAdapter(dashboardPage.getAdminPage().getLblUpdate()) {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateUserPrivileges();
            }
        });

        dashboardPage.getAdminPage().deleteMouseAdapter(new AdminPage.MyAdapter(dashboardPage.getAdminPage().getLblDelete()) {
            @Override
            public void mouseClicked(MouseEvent e) {
                String s = userTable.getValueAt(userTable.getSelectedRow(), 0).toString();
                if(JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete?", "Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    if(dataModel.deleteUser(Long.parseLong(s))) {
                        ((DefaultTableModel) userTable.getModel()).removeRow(userTable.getSelectedRow());
                        Message.displaySuccessMessage("You've deleted user successfully.");
                    } else {
                        Message.showErr("Failed to delete user.");
                    }
                }
            }
        });

        dashboardPage.getAdminPage().suspendMouseAdapter(new AdminPage.MyAdapter(dashboardPage.getAdminPage().getLblSuspend()) {
            @Override
            public void mouseClicked(MouseEvent e) {

                String admin = userTable.getValueAt(userTable.getSelectedRow(), 4).toString();

                if(admin.equals("T01")) {
                    return;
                }

                if(userTable.getSelectedRow() == -1) {
                    Message.displayMessage("No user selected.");
                    return;
                }

                suspendUserView = new SuspendedUserView(frame);
                suspendUserController();
                suspendUserView.setVisible(true);
            }
        });

        dashboardPage.getAdminPage().disableAcctMouseAdapter(new AdminPage.MyAdapter(dashboardPage.getAdminPage().getLblDisable()) {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(userTable.getSelectedRow() == -1) {
                    Message.displayMessage("No user selected.");
                    return;
                }

                disableUserAcctController();
            }
        });

        dashboardPage.getAdminPage().refreshMouseAdapter(new AdminPage.MyAdapter(dashboardPage.getAdminPage().getLblRefresh()) {
            @Override
            public void mouseClicked(MouseEvent e) {
                showUser();
            }
        });
    }

    private void toolbarTopController() {
        dashboardPage.getToolbarTop().showAddPatientsPage(e-> {
            dashboardPage.showContentPane(DashboardPage.ADD_NEW_PATIENTS);
            dashboardPage.getToolbarTop().setToolComponentsEnabled(false);
            flag = true; // Set to true then the camera button-ok (btn-ok) is expected that it is in the AddPatientsPage.
        });

        dashboardPage.getToolbarTop().showDashboardPage(e-> {
            dashboardPage.showContentPane(DashboardPage.DATA_COMPONENTS);
            dashboardPage.getToolbarTop().tableSelectionChanged(table);
        });

        dashboardPage.getToolbarTop().showEditPatientsPage(e -> {

            try {
                flag = false; // Expected that the btn-ok is in the EditPatientsPage (AddPatientsPage and EditPatientsPage is the same interface but different functionality).
                dataModel.show();
                showDataToBeEdit();

            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }

            dashboardPage.showContentPane(DashboardPage.EDIT_PATIENTS);
            dashboardPage.getToolbarTop().setToolComponentsEnabled(false);
        });

        //======== Refresh Table ========
        dashboardPage.getToolbarTop().refreshTable(e -> dataController(1));

        //======== Delete Record ========
        dashboardPage.getToolbarTop().deletePatients(e -> {
            if(loginModel.getUser().isAdmin() || loginModel.getUser().isAllowDeleteStudent()) {
                if(JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete?", "Delete", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if(dataModel.deleteData()) {
                        ((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
                        dashboardPage.getDataComponents().getBasicInfoComponents().clearField();
                        Message.displaySuccessMessage("Deleted Successfully");
                    } else {
                        Message.showErr("Failed to delete data.");
                    }
                }
            } else {
                Message.displayMessage("Permission denied.");
            }
        });

        //======== Show Report ========
        dashboardPage.getToolbarTop().showViewPatientsPage(e -> openReport());

        table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "openReport");
        table.getActionMap().put("openReport", new AbstractAction() {
            @Serial
            private static final long serialVersionUID = 1L;
            @Override
            public void actionPerformed(ActionEvent e) {
                openReport();
            }
        });

        //======== Show Add Medical Data ========
        dashboardPage.getToolbarTop().showEditMedicalDataPage(e -> {
            addMedicalDataPage = new AddMedicalDataPage(frame);
            addMedicalDataPage.setTitle(addMedicalDataPage.getTitle().concat(" to " + table.getValueAt(table.getSelectedRow(), 1) + " " + table.getValueAt(table.getSelectedRow(), 2)));
            JButton btnSave = addMedicalDataPage.getBtnSave();
            btnSave.setActionCommand("save-med");
            btnSave.addActionListener(e1 -> {
                 if(e1.getActionCommand().equals("save-med")) {
                    dataModel.addMedicalRecordOfExistingPatient(
                            addMedicalDataPage.getMedicalData().isHypertension(),
                            addMedicalDataPage.getMedicalData().isDiabetes(),
                            addMedicalDataPage.getMedicalData().isBronchial(),
                            addMedicalDataPage.getMedicalData().isHeartDisease(),
                            addMedicalDataPage.getMedicalData().isThyroid(),
                            addMedicalDataPage.getMedicalData().isCopd(),
                            addMedicalDataPage.getMedicalData().isPud(),
                            addMedicalDataPage.getMedicalData().getTxtHeight(),
                            addMedicalDataPage.getMedicalData().getTxtWeight(),
                            addMedicalDataPage.getMedicalData().getTxtPulseRate(),
                            addMedicalDataPage.getMedicalData().getTxtBp(),
                            addMedicalDataPage.getMedicalData().getTxtBloodType(),
                            addMedicalDataPage.getMedicalData().getTaRemarks(),
                            addMedicalDataPage.getMedicalData().getTaFamilyHistory(),
                            addMedicalDataPage.getMedicalData().getTxtDevNormal(),
                            addMedicalDataPage.getMedicalData().getTxtPositiveDev(),
                            addMedicalDataPage.getMedicalData().getTxtRemarksDev(),
                            addMedicalDataPage.getMedicalData().getTxtGeneralAppearanceNormal(),
                            addMedicalDataPage.getMedicalData().getTxtPositiveGeneralAppearance(),
                            addMedicalDataPage.getMedicalData().getTxtRemarksGeneralAppearance(),
                            addMedicalDataPage.getMedicalData().getTxtEarNormal(),
                            addMedicalDataPage.getMedicalData().getTxtPositiveEar(),
                            addMedicalDataPage.getMedicalData().getTxtRemarksEar(),
                            addMedicalDataPage.getMedicalData().getTxtHeadNormal(),
                            addMedicalDataPage.getMedicalData().getTxtPositiveHead(),
                            addMedicalDataPage.getMedicalData().getTxtRemarksHead(),
                            addMedicalDataPage.getMedicalData().getTxtNormalChest(),
                            addMedicalDataPage.getMedicalData().getTxtPositiveChest(),
                            addMedicalDataPage.getMedicalData().getTxtRemarksChest(),
                            addMedicalDataPage.getMedicalData().getTxtNormalHeart(),
                            addMedicalDataPage.getMedicalData().getTxtPositiveHeart(),
                            addMedicalDataPage.getMedicalData().getTxtRemarksHeart(),
                            addMedicalDataPage.getMedicalData().getTxtNormalAbdomen(),
                            addMedicalDataPage.getMedicalData().getTxtPositiveAbdomen(),
                            addMedicalDataPage.getMedicalData().getTxtRemarksAbdomen(),
                            addMedicalDataPage.getMedicalData().getTxtNormalGenitals(),
                            addMedicalDataPage.getMedicalData().getTxtPositiveGenitals(),
                            addMedicalDataPage.getMedicalData().getTxtRemarksGenitals(),
                            addMedicalDataPage.getMedicalData().getTxtNormalExtremities(),
                            addMedicalDataPage.getMedicalData().getTxtPositiveExtremities(),
                            addMedicalDataPage.getMedicalData().getTxtRemarksExtremities(),
                            addMedicalDataPage.getMedicalData().getTxtNormalNeuralgic(),
                            addMedicalDataPage.getMedicalData().getTxtPositiveNeuralgic(),
                            addMedicalDataPage.getMedicalData().getTxtRemarksNeuralgic(),
                            addMedicalDataPage.getMedicalData().getTaDiagnosis(),
                            addMedicalDataPage.getMedicalData().getTaRecommendation(),
                            addMedicalDataPage.getMedicalData().getTxtPhysician(),
                            addMedicalDataPage.getMedicalData().getTxtLicense(),
                            addMedicalDataPage.getMedicalData().getTxtMedicalExaminer(),
                            addMedicalDataPage.getMedicalData().getDateByString(),
                            addMedicalDataPage.getMedicalData().getTxtTime());

                    if(Boolean.parseBoolean(System.getProperty("app.medical.data.save"))) {
                        Message.displaySuccessMessage("New record successfully added.");
                        addMedicalDataPage.clearField();
                        addMedicalDataPage.dispose();
                        System.clearProperty("app.medical.data.save");
                    }
                }
            });
            addMedicalDataPage.setVisible(true);
        });
    }

    private void openReport() {
        ReportDialog reportDialog = new ReportDialog("Medical Report", frame);
        long id = Long.parseLong(table.getValueAt(table.getSelectedRow(), 0).toString());
        JRBeanCollectionDataSource dataSource = dataModel.initReport(id);
        if(dataSource == null) {
            return;
        }
        reportDialog.viewReport(dataSource);
        reportDialog.setVisible(true);
    }

    private void toolbarBottomController() {
//        frame.getToolbarBottom().setUsername(loginPage.getUsername());
    }

    private void menuController() {

        frame.getMenuListener().setActionListener(e->{
            if(e.getActionCommand().equals("Create Account")) {
                frame.showContentPane("newAccountRegistration");
                frame.setEditTitle("New Account Registration");
            } else if(e.getActionCommand().equals("Change Password")) {
                frame.showContentPane("changePassword");
                frame.setEditTitle("Change Password");
            } else if (e.getActionCommand().equals("Home")) {
                frame.showContentPane("home");
                constraintsList.addConstraintsNameToList("home");
                frame.getHomeMenuAction().setEnabled(false);
//                frame.setEditTitle("Home");
                setFrameTitleName();
            } else if (e.getActionCommand().equals("Logout")) {
                logout();
            } else if (e.getActionCommand().equals("Generate Key")) {
                // CheckList
                if(loginModel.getUser().isAdmin() || loginModel.getUser().isAllowAddUser()) {
                    generateMenuController();
                } else {
                    Message.displayMessage("Permission denied.");
                }
            } else if (e.getActionCommand().equals("Administrator")) {
                if(loginModel.getUser().isAdmin()) {
                    dashboardPage.showContentPane(DashboardPage.SHOW_ADMIN_PAGES);
                    dashboardPage.getToolbarTop().setToolComponentsEnabled(false);
                } else {
                    Message.displayMessage("Permission denied.\nPlease login as admin.");
                }
            }
        });
    }

    private void logout() {
        // Disable Menu
//        frame.getLogoutMenuAction().setEnabled(false);
        frame.getHomeMenuAction().setEnabled(false);
//        frame.getChangePasswordAction().setEnabled(false);
//        frame.getSystemActionMenu().setEnabled(false);
        actionMenu(false);

        constraintsList.removeAll(constraintsList.getAllConstrainstList());
        constraintsList.addConstraintsNameToList("login");
        dashboardPage.getToolbarBottom().removeAll();
        frame.showContentPane("login");
//      frame.setEditTitle("Login");
        loginModel.logoutUser();

        // Disable Action Menu
        frame.getJMenuBar().getMenu(2).setEnabled(false);

        setFrameTitleName();
    }

    private void generateMenuController() {
        Map<String, Boolean> map = new HashMap<>();
        GenerateTerminalKeyDialog terminalKey = new GenerateTerminalKeyDialog(frame, "Generate Key", true);
        terminalKey.getTextPane().setText(dataModel.generateTerminalKey());

        // ******** CheckBox Controller ********
        // Default
        terminalKey.defaultCheckBoxSelected();
        map.put("allowAddStudent", true);
        map.put("allowAddMedRec", true);
        map.put("allowAddUser", false);
        map.put("allowUpdateStudent", true);
        map.put("allowUpdateMedRec", true);
        map.put("allowUpdateUser", false);
        map.put("allowDeleteStudent", true);
        map.put("allowDeleteMedRec", true);
        map.put("allowDeleteUser", false);
        map.put("admin", false);

        // Add Student
        terminalKey.setChkAllowAddStudentListener(e -> map.put("allowAddStudent", terminalKey.getChkAllowAddStudent().isSelected()));
        terminalKey.setChkDontAllowAddStudentListener(e-> map.put("allowAddStudent", false));

        // Add Medical Record
        terminalKey.setChkAllowAddMedicalRecListener(e -> map.put("allowAddMedRec", terminalKey.getChkAllowAddMedicalRec().isSelected()));
        terminalKey.setChkDontAllowAddMedicalRecListener(e-> map.put("allowAddMedRec", false));

        // Add User
        terminalKey.setChkAllowAddUserListener(e -> map.put("allowAddUser", terminalKey.getChkAllowAddUser().isSelected()));
        terminalKey.setChkDontAllowAddUserListener(e -> map.put("allowAddUser", false));

        // Update Student
        terminalKey.setChkAllowUpdateStudentListener(e -> map.put("allowUpdateStudent", true));
        terminalKey.setChkDontAllowUpdateStudentListener(e-> map.put("allowUpdateStudent", false));

        // Update Medical Record
        terminalKey.setChkAllowUpdateMedicalRecListener(e -> map.put("allowUpdateMedRec", true));
        terminalKey.setChkDontAllowUpdateMedicalRecListener(e-> map.put("allowUpdateMedRec", false));

        // Update User
        terminalKey.setChkAllowUpdateUserListener(e -> map.put("allowUpdateUser", true));
        terminalKey.setChkDontAllowUpdateUserListener(e-> map.put("allowUpdateUser", false));

        // Delete Student
        terminalKey.setChkAllowDeleteStudentListener(e -> map.put("allowDeleteStudent", true));
        terminalKey.setChkDontAllowDeleteStudentListener(e-> map.put("allowDeleteStudent", false));

        // Delete Medical Record
        terminalKey.setChkAllowDeleteMedicalRecListener(e -> map.put("allowDeleteMedRec", true));
        terminalKey.setChkDontAllowDeleteMedicalRecListener(e-> map.put("allowDeleteMedRec", false));

        // Delete User
        terminalKey.setChkAllowDeleteUserListener(e -> map.put("allowDeleteUser", true));
        terminalKey.setChkDontAllowDeleteUserListener(e-> map.put("allowDeleteUser", false));

        // Admin
        terminalKey.setChkAllowAdminListener(e -> {
            map.put("allowAddStudent", true);
            map.put("allowAddMedRec", true);
            map.put("allowAddUser", true);
            map.put("allowUpdateStudent", true);
            map.put("allowUpdateMedRec", true);
            map.put("allowUpdateUser", true);
            map.put("allowDeleteStudent", true);
            map.put("allowDeleteMedRec", true);
            map.put("allowDeleteUser", true);
            map.put("admin", true);

            terminalKey.setAdminPrivileges();

        });
        terminalKey.setChkDontAllowAdminListener(e-> {
            terminalKey.defaultCheckBoxSelected();
            map.put("allowAddStudent", true);
            map.put("allowAddMedRec", true);
            map.put("allowAddUser", false);
            map.put("allowUpdateStudent", true);
            map.put("allowUpdateMedRec", true);
            map.put("allowUpdateUser", false);
            map.put("allowDeleteStudent", true);
            map.put("allowDeleteMedRec", true);
            map.put("allowDeleteUser", false);
            map.put("admin", false);
            terminalKey.enableDisableChk(true);

        });

        // ******** Generate Key ********
        terminalKey.getGenerateButton().addActionListener(e -> terminalKey.getTextPane().setText(dataModel.generateTerminalKey()));
        // Exit Dialog
        terminalKey.getExitButton().addActionListener(e -> terminalKey.dispose());
        // Save Terminal Key
        terminalKey.getSaveButton().addActionListener(e -> {
            if(dataModel.createTerminalKey(terminalKey.getTextPane().getText(), map.get("admin"),
                                            map.get("allowAddUser"), map.get("allowAddStudent"),
                                            map.get("allowAddMedRec"), map.get("allowUpdateStudent"),
                                            map.get("allowUpdateMedRec"), map.get("allowUpdateUser"),
                                            map.get("allowDeleteStudent"), map.get("allowDeleteMedRec"),
                                            map.get("allowDeleteUser"))) {
                Message.displaySuccessMessage("New terminal key created.");
                terminalKey.dispose();
            } else {
                Message.showErr("Saving terminal key failed.");
            }

        });

        terminalKey.setVisible(true);
    }

    private void updateUserPrivileges() {
        Map<String, Boolean> map = new HashMap<>();
        String s = userTable.getValueAt(userTable.getSelectedRow(), 0).toString();
        GenerateTerminalKeyDialog terminalKey = new GenerateTerminalKeyDialog(frame, "Update", true);
        terminalKey.getTextPane().setText(dataModel.getUserTerminalKey(Integer.parseInt(s)));
        terminalKey.getTextPane().setEnabled(false);

        // ******** CheckBox Controller ********
        // Default
//        terminalKey.defaultCheckBoxSelected();

//        chkAllowAddStudent.setSelected(true);
//        chkAllowAddMedicalRec.setSelected(true);
//        chkDontAllowAddUser.setSelected(true);
//        chkAllowUpdateStudent.setSelected(true);
//        chkAllowUpdateMedicalRec.setSelected(true);
//        chkDontAllowUpdateUser.setSelected(true);
//        chkAllowDeleteStudent.setSelected(true);
//        chkAllowDeleteMedicalRec.setSelected(true);
//        chkDontAllowDeleteUser.setSelected(true);
//        chkDontAllowAdmin.setSelected(true);

//        terminalKey.getChkAllowAddStudent().setSelected(dataModel.getUserIsAllowAddStudent());
//        terminalKey.getChkAllowAddMedicalRec().setSelected(dataModel.getUserIsAllowAddMedRec());
//        terminalKey.getChkAllowAddUser().setSelected(dataModel.getUserIsAllowAddUser());
//        terminalKey.getChkAllowUpdateStudent().setSelected(dataModel.getUserIsAllowUpdateStudent());
//        terminalKey.getChkAllowUpdateMedicalRec().setSelected(dataModel.getUserIsAllowUpdateMedRec());
//        terminalKey.getChkAllowUpdateUser().setSelected(dataModel.getUserIsAllowUpdateUser());
//        terminalKey.getChkAllowDeleteStudent().setSelected(dataModel.getUserIsAllowDeleteStudent());
//        terminalKey.getChkAllowDeleteMedicalRec().setSelected(dataModel.getUserIsAllowDeleteMedRec());
//        terminalKey.getChkAllowDeleteUser().setSelected(dataModel.getUserIsAllowDeleteUser());
//        terminalKey.getChkAllowAdmin().setSelected(dataModel.getUserIsAdmin());

        if(dataModel.getUserIsAllowAddStudent()) {
            terminalKey.getChkAllowAddStudent().setSelected(true);
        } else {
            terminalKey.getChkDontAllowAddStudent().setSelected(true);
        }

        if(dataModel.getUserIsAllowAddMedRec()) {
            terminalKey.getChkAllowAddMedicalRec().setSelected(true);
        } else {
            terminalKey.getChkDontAllowAddMedicalRec().setSelected(true);
        }

        if(dataModel.getUserIsAllowAddUser()) {
            terminalKey.getChkAllowAddUser().setSelected(true);
        } else {
            terminalKey.getChkDontAllowAddUser().setSelected(true);
        }

        if(dataModel.getUserIsAllowUpdateStudent()) {
            terminalKey.getChkAllowUpdateStudent().setSelected(true);
        } else {
            terminalKey.getChkDontAllowUpdateStudent().setSelected(true);
        }

        if(dataModel.getUserIsAllowUpdateMedRec()) {
            terminalKey.getChkAllowUpdateMedicalRec().setSelected(true);
        } else {
            terminalKey.getChkDontAllowUpdateMedicalRec().setSelected(true);
        }

        if(dataModel.getUserIsAllowUpdateUser()) {
            terminalKey.getChkAllowUpdateUser().setSelected(true);
        } else {
            terminalKey.getChkDontAllowUpdateUser().setSelected(true);
        }

        if(dataModel.getUserIsAllowDeleteStudent()) {
            terminalKey.getChkAllowDeleteStudent().setSelected(true);
        } else {
            terminalKey.getChkDontAllowDeleteStudent().setSelected(true);
        }

        if(dataModel.getUserIsAllowDeleteMedRec()) {
            terminalKey.getChkAllowDeleteMedicalRec().setSelected(true);
        } else {
            terminalKey.getChkDontAllowDeleteMedicalRec().setSelected(true);
        }

        if(dataModel.getUserIsAllowDeleteUser()) {
            terminalKey.getChkAllowDeleteUser().setSelected(true);
        } else {
            terminalKey.getChkDontAllowDeleteUser().setSelected(true);
        }

        if(dataModel.getUserIsAdmin()) {
            terminalKey.getChkAllowAdmin().setSelected(true);
        } else {
            terminalKey.getChkDontAllowAdmin().setSelected(true);
        }

        map.put("allowAddStudent", dataModel.getUserIsAllowAddStudent());
        map.put("allowAddMedRec", dataModel.getUserIsAllowAddMedRec());
        map.put("allowAddUser", dataModel.getUserIsAllowAddUser());
        map.put("allowUpdateStudent", dataModel.getUserIsAllowUpdateStudent());
        map.put("allowUpdateMedRec", dataModel.getUserIsAllowUpdateMedRec());
        map.put("allowUpdateUser", dataModel.getUserIsAllowUpdateUser());
        map.put("allowDeleteStudent", dataModel.getUserIsAllowDeleteStudent());
        map.put("allowDeleteMedRec", dataModel.getUserIsAllowDeleteMedRec());
        map.put("allowDeleteUser", dataModel.getUserIsAllowDeleteUser());
        map.put("admin", dataModel.getUserIsAdmin());


        if(dataModel.getUserIsAdmin()) {
            terminalKey.setAdminPrivileges();
        }

        // Add Student
        terminalKey.setChkAllowAddStudentListener(e -> map.put("allowAddStudent", terminalKey.getChkAllowAddStudent().isSelected()));
        terminalKey.setChkDontAllowAddStudentListener(e-> map.put("allowAddStudent", false));

        // Add Medical Record
        terminalKey.setChkAllowAddMedicalRecListener(e -> map.put("allowAddMedRec", terminalKey.getChkAllowAddMedicalRec().isSelected()));
        terminalKey.setChkDontAllowAddMedicalRecListener(e-> map.put("allowAddMedRec", false));

        // Add User
        terminalKey.setChkAllowAddUserListener(e -> map.put("allowAddUser", terminalKey.getChkAllowAddUser().isSelected()));
        terminalKey.setChkDontAllowAddUserListener(e -> map.put("allowAddUser", false));

        // Update Student
        terminalKey.setChkAllowUpdateStudentListener(e -> map.put("allowUpdateStudent", terminalKey.getChkAllowUpdateStudent().isSelected()));
        terminalKey.setChkDontAllowUpdateStudentListener(e-> map.put("allowUpdateStudent", false));

        // Update Medical Record
        terminalKey.setChkAllowUpdateMedicalRecListener(e -> map.put("allowUpdateMedRec", true));
        terminalKey.setChkDontAllowUpdateMedicalRecListener(e-> map.put("allowUpdateMedRec", false));

        // Update User
        terminalKey.setChkAllowUpdateUserListener(e -> map.put("allowUpdateUser", true));
        terminalKey.setChkDontAllowUpdateUserListener(e-> map.put("allowUpdateUser", false));

        // Delete Student
        terminalKey.setChkAllowDeleteStudentListener(e -> map.put("allowDeleteStudent", true));
        terminalKey.setChkDontAllowDeleteStudentListener(e-> map.put("allowDeleteStudent", false));

        // Delete Medical Record
        terminalKey.setChkAllowDeleteMedicalRecListener(e -> map.put("allowDeleteMedRec", true));
        terminalKey.setChkDontAllowDeleteMedicalRecListener(e-> map.put("allowDeleteMedRec", false));

        // Delete User
        terminalKey.setChkAllowDeleteUserListener(e -> map.put("allowDeleteUser", true));
        terminalKey.setChkDontAllowDeleteUserListener(e-> map.put("allowDeleteUser", false));

        // Admin
        terminalKey.setChkAllowAdminListener(e -> {
            map.put("allowAddStudent", true);
            map.put("allowAddMedRec", true);
            map.put("allowAddUser", true);
            map.put("allowUpdateStudent", true);
            map.put("allowUpdateMedRec", true);
            map.put("allowUpdateUser", true);
            map.put("allowDeleteStudent", true);
            map.put("allowDeleteMedRec", true);
            map.put("allowDeleteUser", true);
            map.put("admin", true);

            terminalKey.setAdminPrivileges();

        });
        terminalKey.setChkDontAllowAdminListener(e-> {
//            terminalKey.defaultCheckBoxSelected();
//            map.put("allowAddStudent", true);
//            map.put("allowAddMedRec", true);
//            map.put("allowAddUser", false);
//            map.put("allowUpdateStudent", true);
//            map.put("allowUpdateMedRec", true);
//            map.put("allowUpdateUser", false);
//            map.put("allowDeleteStudent", true);
//            map.put("allowDeleteMedRec", true);
//            map.put("allowDeleteUser", false);
            map.put("admin", false);
            terminalKey.enableDisableChk(true);

        });

        // ******** Copy Generated Key ********
        terminalKey.getGenerateButton().setText("Copy");
        terminalKey.getGenerateButton().setToolTipText("Copy Terminal Key");
        terminalKey.getGenerateButton().addActionListener(e -> {
            StringSelection selection = new StringSelection(terminalKey.getTextPane().getText());
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
        });
        // Exit Dialog
        terminalKey.getExitButton().addActionListener(e -> terminalKey.dispose());
        // Update Terminal Key
        terminalKey.getSaveButton().setText("Update");
        terminalKey.getSaveButton().addActionListener(e -> {

            if(JOptionPane.showConfirmDialog(frame, "Are you sure you want to update?", "Update", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if(dataModel.updateUser(map.get("admin"), map.get("allowAddUser"), map.get("allowAddStudent"),
                        map.get("allowAddMedRec"), map.get("allowUpdateStudent"),
                        map.get("allowUpdateMedRec"), map.get("allowUpdateUser"),
                        map.get("allowDeleteStudent"), map.get("allowDeleteMedRec"),
                        map.get("allowDeleteUser"))) {
                    Message.displaySuccessMessage("User updated...");
                } else {
                    Message.showErr("Failed to update user.");
                }
            }

        });

        terminalKey.setVisible(true);
    }

    // Suspend User Controller
    private void suspendUserController() {

        try {
            suspendUserView.setSuspendUserListener(e -> {
                String s = userTable.getValueAt(userTable.getSelectedRow(), 0).toString();

                if(suspendUserView.getFromSuspendedDate().getDate() > suspendUserView.getToSuspendedDate().getDate()) {
                    Message.displayMessage("Starting date is greater than ending date.");
                    return;
                }
                if(dataModel.suspendUser(Integer.parseInt(s), suspendUserView.getFromSuspendedDate(), suspendUserView.getToSuspendedDate())) {
                    Message.displaySuccessMessage("User suspended successfully...");
                } else {
                    Message.showErr("Failed to suspend user.");
                }
            });
        } catch (Exception e) {
            ErrorDialog.show(e);
        }

        // Close/Exit the dialog
        suspendUserView.closeDialog(e -> suspendUserView.dispose());
    }

    // Disable User Controller
    private void disableUserAcctController() {
        int disableUser = JOptionPane.showConfirmDialog(frame, "Are you sure you want to disable this account?", "Disable", JOptionPane.YES_NO_OPTION);
        String s = userTable.getValueAt(userTable.getSelectedRow(), 0).toString();
        int id = Integer.parseInt(s);
        if(disableUser == JOptionPane.YES_OPTION) {
            if(dataModel.disableUser(id, true)) {
                Message.displaySuccessMessage("Account disabled...");
            } else {
                Message.showErr("Failed to disable user account.");
            }
        }
    }

    private void createAccountRegistrationController() {

        newAccountRegistration.createAccountListener(e -> createAccount());

        newAccountRegistration.createAccountListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    createAccount();
                }
            }
        });

        newAccountRegistration.backListener(e->{
            frame.showContentPane(constraintsList.getConstraintsNameByLastIndex());
            setFrameTitleName();
            newAccountRegistration.clearField();
        });
    }

    private void createAccount() {
        if(newAccountRegistration.getFirstname().isEmpty() || newAccountRegistration.getLastname().isEmpty() || newAccountRegistration.getUsername().isEmpty() ||
                newAccountRegistration.getTerminalKey().isEmpty() || newAccountRegistration.getPassword().isEmpty() || newAccountRegistration.getConfirmPass().isEmpty()) {
            Message.displayMessage("Please complete the field.");
            return;
        }

        if(newAccountRegistration.getConfirmPass().equals(newAccountRegistration.getPassword())) {
            String saltedText = BCrypt.gensalt(12);
            if(dataModel.createAccount(newAccountRegistration.getFirstname(), newAccountRegistration.getLastname(),
                    newAccountRegistration.getUsername(), GetMacAddr.getMac(), newAccountRegistration.getTerminalKey(), BCrypt.hashpw(newAccountRegistration.getPassword(), saltedText), saltedText)) {
                Message.displaySuccessMessage("New account created.");
                frame.showContentPane(constraintsList.getConstraintsNameByLastIndex());
                setFrameTitleName();
                newAccountRegistration.clearField();
            } else {
                Message.showErr("New account creation failed.");
            }
        } else {
            Message.displayMessage("Password doesn't match...");
        }
    }

    public void createAccountForAdmin() {
        try {
            String saltedText = BCrypt.gensalt(10);
            dataModel.createAccount("JSSoftware", "IT Support", "admin", GetMacAddr.getMac() != null ? GetMacAddr.getMac() : null, "T01", BCrypt.hashpw("admin777", saltedText), saltedText);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void changePasswordController() {
        changePassword.backListener(e->{
            frame.showContentPane(constraintsList.getConstraintsNameByLastIndex());
//            frame.setEditTitle(constraintsList.getConstraintsNameByLastIndex());
            setFrameTitleName();
            changePassword.clearField();
            loginModel.logoutUser();
        });

        changePassword.changePasswordListener(e -> {
            changePassword();
        });

        changePassword.changePasswordListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    changePassword();
                }
            }
        });
    }

    private void changePassword() {
        String hashedOldPassword = BCrypt.hashpw(changePassword.getOldPassword(), loginModel.getUser().getSalt());
        String saltedText = BCrypt.gensalt(12);

        if(changePassword.getOldPassword().isEmpty() || changePassword.getNewPassword().isEmpty() || changePassword.getConfirmPassword().isEmpty()) {
            Message.displayMessage("Please complete the field.");
            return;
        }

        if(!changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
            Message.displayMessage("Password doesn't match...");
            return;
        }

        if(!hashedOldPassword.equals(loginModel.getUser().getPassword())) {
            Message.showErr("Invalid old password.");
            return;
        }

        if(dataModel.changePassword(loginModel.getUser().getUsername() != null ? loginModel.getUser().getUsername() : System.getProperty("system.username"), hashedOldPassword, BCrypt.hashpw(changePassword.getNewPassword(), saltedText), saltedText)) {
            if(loginModel.isAdminLogin()) {
                Message.displaySuccessMessage("Password has been changed successfully. Please do not forget your login credentials.");
            } else {
                Message.displaySuccessMessage("You’ve successfully changed your password.");
            }
            logout();
            changePassword.clearField();
        } else {
            Message.showErr("Failed to change password.");
        }
    }

    private void setFrameTitleName() {
        String str = constraintsList.getConstraintsNameByLastIndex();
        str = switch (str) {
            case "medicalDashboard", "dentalDashboard" -> "Dashboard";
            case "login" -> "Login";
            case "home" -> "Home";
            default -> str;
        };
        frame.setEditTitle(str);
    }

    private void addNewPatientsController() {
        dashboardPage.getAddNewPatients().getPanelInfoPage().saveDataListener(e -> {
            try {
                dataModel.saveData(dashboardPage.getAddNewPatients().getPanelInfoPage().getTxtFirstname().getText(),
                        dashboardPage.getAddNewPatients().getPanelInfoPage().getTxtLastname().getText(),
                        dashboardPage.getAddNewPatients().getPanelInfoPage().getTxtAge().getText(),
                        Objects.requireNonNull(dashboardPage.getAddNewPatients().getPanelInfoPage().getCmbGender().getSelectedItem()).toString(),
                        Objects.requireNonNull(dashboardPage.getAddNewPatients().getPanelInfoPage().getCmbCourse().getSelectedItem()).toString(),
                        Objects.requireNonNull(dashboardPage.getAddNewPatients().getPanelInfoPage().getCmbStatus().getSelectedItem()).toString(),
                        Objects.requireNonNull(dashboardPage.getAddNewPatients().getPanelInfoPage().getCmbYearLevel().getSelectedItem()).toString(),
                        MySimpleDateFormat.getInstance().format(dashboardPage.getAddNewPatients().getPanelInfoPage().getTxtBirthdate().getDate()),
                        dashboardPage.getAddNewPatients().getPanelInfoPage().getTxtMobileNo().getText(),
                        dashboardPage.getAddNewPatients().getPanelInfoPage().getTxtEmailAdd().getText(),
                        dashboardPage.getAddNewPatients().getPanelInfoPage().getTxtIdno().getText(),
                        dashboardPage.getAddNewPatients().getPanelInfoPage().getTxtBrgy().getText(),
                        dashboardPage.getAddNewPatients().getPanelInfoPage().getTxtMun().getText(),
                        dashboardPage.getAddNewPatients().getPanelInfoPage().getTxtProv().getText(),
                        dataModel.getWebcamModel().getImageByte(getQualifiedFile()));
                StudentImageFile.getInstance().removeImageFile();

                if(Boolean.parseBoolean(System.getProperty("app.data.save"))) {
//                    Message.displayMessage("Save record successfully.");
                        dashboardPage.getAddNewPatients().getPanelInfoPage().toClearField();
                    int confirm = JOptionPane.showOptionDialog(frame, "Data saved successfully.\nDo you want to add medical record?", "Save", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon(SwingUtil.getImage("/com/jimboyz/cims/images/ico-check.png")), new String[]{"Add", "Cancel"}, 1);
                    if(confirm == 0) {
//                        addMedicalDataPage = new AddMedicalDataPage(frame);
                        addMedicalDataController();
                    }
                    System.clearProperty("app.data.save");

                } else {
                    Message.showErr("Save record failed.");
                }
            } catch (Exception ex) {
                ErrorDialog.error(ex);
            }
        });

        dashboardPage.getAddNewPatients().getPanelInfoPage().doBrowseListener(e -> SwingUtilities.invokeLater(() -> dashboardPage.getAddNewPatients().getPanelInfoPage().doBrowseCommand()));
        dashboardPage.getAddNewPatients().getPanelInfoPage().openCamListener(e -> SwingUtilities.invokeLater(this::cameraController));

        dashboardPage.getAddNewPatients().getPanelInfoPage().clearDataListener(e -> {
            if(JOptionPane.showConfirmDialog(frame, "Are you sure you want to clear?", "Clear", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                dashboardPage.getAddNewPatients().getPanelInfoPage().toClearField();
                StudentImageFile.getInstance().removeImageFile();
            }
        });
    }

    public void updateStudentRecordController() {// change the update message.... in StundentDaoImpl
        dashboardPage.getEditPatients().updateDataListener(e -> {
            if(JOptionPane.showConfirmDialog(frame, "Are you sure you want to update?", "Update", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                dataModel.updateData(
                        dashboardPage.getEditPatients().getPanelInfoPage().getTxtFirstname().getText(),
                        dashboardPage.getEditPatients().getPanelInfoPage().getTxtLastname().getText(),
                        dashboardPage.getEditPatients().getPanelInfoPage().getTxtAge().getText(),
                        Objects.requireNonNull(dashboardPage.getEditPatients().getPanelInfoPage().getCmbGender().getSelectedItem()).toString(),
                        Objects.requireNonNull(dashboardPage.getEditPatients().getPanelInfoPage().getCmbCourse().getSelectedItem()).toString(),
                        Objects.requireNonNull(dashboardPage.getEditPatients().getPanelInfoPage().getCmbStatus().getSelectedItem()).toString(),
                        Objects.requireNonNull(dashboardPage.getEditPatients().getPanelInfoPage().getCmbYearLevel().getSelectedItem()).toString(),
                        MySimpleDateFormat.getInstance().format(dashboardPage.getEditPatients().getPanelInfoPage().getTxtBirthdate().getDate()),
                        dashboardPage.getEditPatients().getPanelInfoPage().getTxtMobileNo().getText(),
                        dashboardPage.getEditPatients().getPanelInfoPage().getTxtEmailAdd().getText(),
                        dashboardPage.getEditPatients().getPanelInfoPage().getTxtIdno().getText(),
                        dashboardPage.getEditPatients().getPanelInfoPage().getTxtBrgy().getText(),
                        dashboardPage.getEditPatients().getPanelInfoPage().getTxtMun().getText(),
                        dashboardPage.getEditPatients().getPanelInfoPage().getTxtProv().getText(),
                        dataModel.getWebcamModel().getImageByte(getQualifiedFile()));

                if(Boolean.parseBoolean(System.getProperty("app.data.update"))) {
                    dashboardPage.getEditPatients().getPanelInfoPage().toClearField();
                    Message.displaySuccessMessage("Updated Successfully");
                    System.clearProperty("app.data.update");
                } else {
                    Message.showErr("Update failed.");
                }
            }
        });

        dashboardPage.getEditPatients().getPanelInfoPage().doBrowseListener(e -> SwingUtilities.invokeLater(() -> dashboardPage.getEditPatients().getPanelInfoPage().doBrowseCommand()));
        dashboardPage.getEditPatients().getPanelInfoPage().openCamListener(e -> SwingUtilities.invokeLater(this::cameraController));

        dashboardPage.getEditPatients().getPanelInfoPage().clearDataListener(e -> {
            if(JOptionPane.showConfirmDialog(frame, "Are you sure you want to clear?", "Clear", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                dashboardPage.getAddNewPatients().getPanelInfoPage().toClearField();
                StudentImageFile.getInstance().removeImageFile();
            }
        });
    }

    private void addMedicalDataController() {

        // Add data
        addMedicalDataPage = new AddMedicalDataPage(frame);
        addMedicalDataPage.saveMedicalDataListener(e -> {
            if(e.getActionCommand().equals("save-med-merge")) {
                dataModel.addMedicalRecord(
                        addMedicalDataPage.getMedicalData().isHypertension(),
                        addMedicalDataPage.getMedicalData().isDiabetes(),
                        addMedicalDataPage.getMedicalData().isBronchial(),
                        addMedicalDataPage.getMedicalData().isHeartDisease(),
                        addMedicalDataPage.getMedicalData().isThyroid(),
                        addMedicalDataPage.getMedicalData().isCopd(),
                        addMedicalDataPage.getMedicalData().isPud(),
                        addMedicalDataPage.getMedicalData().getTxtHeight(),
                        addMedicalDataPage.getMedicalData().getTxtWeight(),
                        addMedicalDataPage.getMedicalData().getTxtPulseRate(),
                        addMedicalDataPage.getMedicalData().getTxtBp(),
                        addMedicalDataPage.getMedicalData().getTxtBloodType(),
                        addMedicalDataPage.getMedicalData().getTaRemarks(),
                        addMedicalDataPage.getMedicalData().getTaFamilyHistory(),
                        addMedicalDataPage.getMedicalData().getTxtDevNormal(),
                        addMedicalDataPage.getMedicalData().getTxtPositiveDev(),
                        addMedicalDataPage.getMedicalData().getTxtRemarksDev(),
                        addMedicalDataPage.getMedicalData().getTxtGeneralAppearanceNormal(),
                        addMedicalDataPage.getMedicalData().getTxtPositiveGeneralAppearance(),
                        addMedicalDataPage.getMedicalData().getTxtRemarksGeneralAppearance(),
                        addMedicalDataPage.getMedicalData().getTxtEarNormal(),
                        addMedicalDataPage.getMedicalData().getTxtPositiveEar(),
                        addMedicalDataPage.getMedicalData().getTxtRemarksEar(),
                        addMedicalDataPage.getMedicalData().getTxtHeadNormal(),
                        addMedicalDataPage.getMedicalData().getTxtPositiveHead(),
                        addMedicalDataPage.getMedicalData().getTxtRemarksHead(),
                        addMedicalDataPage.getMedicalData().getTxtNormalChest(),
                        addMedicalDataPage.getMedicalData().getTxtPositiveChest(),
                        addMedicalDataPage.getMedicalData().getTxtRemarksChest(),
                        addMedicalDataPage.getMedicalData().getTxtNormalHeart(),
                        addMedicalDataPage.getMedicalData().getTxtPositiveHeart(),
                        addMedicalDataPage.getMedicalData().getTxtRemarksHeart(),
                        addMedicalDataPage.getMedicalData().getTxtNormalAbdomen(),
                        addMedicalDataPage.getMedicalData().getTxtPositiveAbdomen(),
                        addMedicalDataPage.getMedicalData().getTxtRemarksAbdomen(),
                        addMedicalDataPage.getMedicalData().getTxtNormalGenitals(),
                        addMedicalDataPage.getMedicalData().getTxtPositiveGenitals(),
                        addMedicalDataPage.getMedicalData().getTxtRemarksGenitals(),
                        addMedicalDataPage.getMedicalData().getTxtNormalExtremities(),
                        addMedicalDataPage.getMedicalData().getTxtPositiveExtremities(),
                        addMedicalDataPage.getMedicalData().getTxtRemarksExtremities(),
                        addMedicalDataPage.getMedicalData().getTxtNormalNeuralgic(),
                        addMedicalDataPage.getMedicalData().getTxtPositiveNeuralgic(),
                        addMedicalDataPage.getMedicalData().getTxtRemarksNeuralgic(),
                        addMedicalDataPage.getMedicalData().getTaDiagnosis(),
                        addMedicalDataPage.getMedicalData().getTaRecommendation(),
                        addMedicalDataPage.getMedicalData().getTxtPhysician(),
                        addMedicalDataPage.getMedicalData().getTxtLicense(),
                        addMedicalDataPage.getMedicalData().getTxtMedicalExaminer(),
                        MySimpleDateFormat.getInstance().format(addMedicalDataPage.getMedicalData().getDate()),
                        addMedicalDataPage.getMedicalData().getTxtTime()
                );
            }

            if(Boolean.parseBoolean(System.getProperty("app.medical.data.save"))) {
                Message.displaySuccessMessage("New record successfully added.");
                addMedicalDataPage.clearField();
                addMedicalDataPage.dispose();
                System.clearProperty("app.medical.data.save");
            }
        });

        addMedicalDataPage.setVisible(true);

        // Clear Field
        addMedicalDataPage.clearMedicalDataListener(e -> {
            if(JOptionPane.showConfirmDialog(frame, "Are you sure you want to clear?", "Clear", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                addMedicalDataPage.clearField();
            }
        });
    }

    private void cameraController() {
        CameraView cameraView = new CameraView(frame);
        cameraView.setCaptureImageListener(e -> {
            if(e.getActionCommand().equals("btn-capture")) {
                dataModel.getWebcamModel().setCapturedImage(cameraView.getWebcam());
                dataModel.getWebcamModel().setPreviewImage(cameraView.getBoardPanel(), cameraView.getWebcam(), cameraView.getLabelPreview());
                cameraView.getBtnCapture().setEnabled(false);
            }
        });

        cameraView.setRetakeImageListener(e -> {
            if(e.getActionCommand().equals("btn-retake")) {
                dataModel.getWebcamModel().retakeImage(cameraView.getBoardPanel());
                cameraView.getBtnCapture().setEnabled(true);
            }
        });

        cameraView.setOkImageListener(e -> {
            if(flag) {
                dataModel.getWebcamModel().setImage(dashboardPage.getAddNewPatients().getPanelInfoPage().getLblPhoto(), cameraView);
            } else {
                dataModel.getWebcamModel().setImage(dashboardPage.getEditPatients().getPanelInfoPage().getLblPhoto(), cameraView);
            }
        });

        cameraView.setVisible(true);
    }

    private void showDataToBeEdit() throws ParseException {

        SimpleDateFormat formatter = MySimpleDateFormat.getInstance().formatter();
        Date date = formatter.parse(dataModel.getEditPatientsModel().getBirthdate());

        dashboardPage.getEditPatients().getPanelInfoPage().getTxtFirstname().setText(dataModel.getEditPatientsModel().getFirstname());
        dashboardPage.getEditPatients().getPanelInfoPage().getTxtLastname().setText(dataModel.getEditPatientsModel().getLastname());
        dashboardPage.getEditPatients().getPanelInfoPage().getCmbCourse().setSelectedItem(dataModel.getEditPatientsModel().getCourse());
        dashboardPage.getEditPatients().getPanelInfoPage().getCmbGender().setSelectedItem(dataModel.getEditPatientsModel().getGender());
        dashboardPage.getEditPatients().getPanelInfoPage().getCmbStatus().setSelectedItem(dataModel.getEditPatientsModel().getStatus());
        dashboardPage.getEditPatients().getPanelInfoPage().getCmbYearLevel().setSelectedItem(dataModel.getEditPatientsModel().getYearLevel());//dataModel.getEditPatientsModel().getBirthdate()
        dashboardPage.getEditPatients().getPanelInfoPage().getTxtBirthdate().setDate(date);
        dashboardPage.getEditPatients().getPanelInfoPage().getTxtAge().setText(String.valueOf(dataModel.getEditPatientsModel().getAge()));
        dashboardPage.getEditPatients().getPanelInfoPage().getTxtMobileNo().setText(dataModel.getEditPatientsModel().getPhone());
        dashboardPage.getEditPatients().getPanelInfoPage().getTxtEmailAdd().setText(dataModel.getEditPatientsModel().getEmailAdd());
        dashboardPage.getEditPatients().getPanelInfoPage().getTxtIdno().setText(dataModel.getEditPatientsModel().getIdNum());

        JTextField txtBrgy = dashboardPage.getEditPatients().getPanelInfoPage().getTxtBrgy();
        JTextField txtMun = dashboardPage.getEditPatients().getPanelInfoPage().getTxtMun();
        JTextField txtProv = dashboardPage.getEditPatients().getPanelInfoPage().getTxtProv();

        String brgy = dataModel.getEditPatientsModel().getBarangay() != null ? dataModel.getEditPatientsModel().getBarangay() : "Barangay";
        String mun = dataModel.getEditPatientsModel().getMunicipality() != null ? dataModel.getEditPatientsModel().getMunicipality() : "City/Municipality";
        String prov = dataModel.getEditPatientsModel().getProvince() != null ? dataModel.getEditPatientsModel().getProvince() : "Province";

        if(!brgy.equals("Barangay") || !mun.equals("City/Municipality") || !prov.equals("Province")) {
            txtBrgy.setText(dataModel.getEditPatientsModel().getBarangay());
            txtMun.setText(dataModel.getEditPatientsModel().getMunicipality());
            txtProv.setText(dataModel.getEditPatientsModel().getProvince());

            txtBrgy.setFont(new Font("Times New Roman", Font.PLAIN, 19));
            txtMun.setFont(new Font("Times New Roman", Font.PLAIN, 19));
            txtProv.setFont(new Font("Times New Roman", Font.PLAIN, 19));

            txtBrgy.setForeground(Color.BLACK);
            txtMun.setForeground(Color.BLACK);
            txtProv.setForeground(Color.BLACK);
        } else {
            txtBrgy.setText("Barangay");
            txtMun.setText("City/Municipality");
            txtProv.setText("Province");

            txtBrgy.setFont(new Font("Times New Roman", Font.ITALIC, 19));
            txtMun.setFont(new Font("Times New Roman", Font.ITALIC, 19));
            txtProv.setFont(new Font("Times New Roman", Font.ITALIC, 19));

            txtBrgy.setForeground(Color.GRAY);
            txtMun.setForeground(Color.GRAY);
            txtProv.setForeground(Color.GRAY);
        }

        setIconController();

    }

    private void setIconController() {
        JLabel lbl = dashboardPage.getEditPatients().getPanelInfoPage().getLblPhoto();
        byte[] img = dataModel.getEditPatientsModel().getStudentImage();
        if(img != null) {
            lbl.setText("");
            lbl.setIcon(SwingUtil.newImageIcon(img, lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH));
        } else {
            lbl.setText("Photo");
            lbl.setIcon(null);
        }
    }

    //======== Set Image Controller ========
    private File getQualifiedFile() {

        StudentImageFile sif = StudentImageFile.getInstance();
        if(sif.getImageFile() != null) {
            return  sif.getImageFile();
        }

        return null;
    }

    private void setWindowListener() {
        frame.windowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
//                LoadProperties.deleteConfigFile();
            }
        });
    }

    //======== Paginated Search ========
    public void paginatedSearchController() {
        dashboardPage.getDataComponents().getBtnFirst().addActionListener(e -> dataController(1));
        dashboardPage.getDataComponents().getBtnLast().addActionListener(e -> dataController((int) Math.ceil((double) dataModel.getTotalResults() / dataModel.getPageSize())));
        dashboardPage.getDataComponents().getBtnNext().addActionListener(e -> dataController(dataModel.getCurrentPage() + 1));
        dashboardPage.getDataComponents().getBtnPrev().addActionListener(e -> dataController(dataModel.getCurrentPage() - 1));
        
        dashboardPage.setBtnSearch(e -> {
            if(dashboardPage.getDataComponents().getSearchComponents().getTxtFirstname().isEmpty()
                    && dashboardPage.getTxtLastname().isEmpty()
                    && dashboardPage.getCmbGenderItem().isEmpty()
                    && dashboardPage.getCmbYearLevelItem().isEmpty()
                    && dashboardPage.getTxtIdNum().isEmpty()) {
                return;
            }
//            dataController((int)dataModel.getTotalResults());
            dataController(1);
        });

        searchKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(dashboardPage.getDataComponents().getSearchComponents().getTxtFirstname().isEmpty()
                            && dashboardPage.getTxtLastname().isEmpty()
                            && dashboardPage.getCmbGenderItem().isEmpty()
                            && dashboardPage.getCmbYearLevelItem().isEmpty()
                            && dashboardPage.getTxtIdNum().isEmpty()) {
                        return;
                    }
                    dataController(1);
                }
            }
        });
    }

    public void searchKeyListener(KeyListener listener) {
        dashboardPage.setFirstnameListener(listener);
        dashboardPage.setLastnameListener(listener);
        dashboardPage.setGenderListener(listener);
        dashboardPage.setYearLevelListener(listener);
        dashboardPage.setIdNumberListener(listener);
    }

    ///======== Paginated Search Button Controller ========
    private void updateButtonState() {

        dashboardPage.getDataComponents().getLblRecords().setText(String.format("%d Record(s)", dataModel.getTotalResults()));
        dashboardPage.getDataComponents().getLblPages().setText(String.format("Page %d of %d", dataModel.getCurrentPage(), (int)Math.ceil((double) dataModel.getTotalResults() / dataModel.getPageSize())));
        dashboardPage.getDataComponents().getBtnFirst().setEnabled(dataModel.getCurrentPage() > 1);
        dashboardPage.getDataComponents().getBtnPrev().setEnabled(dataModel.getCurrentPage() > 1);
        dashboardPage.getDataComponents().getBtnNext().setEnabled(dataModel.getCurrentPage() < (int) Math.ceil((double) dataModel.getTotalResults() / dataModel.getPageSize()));
        dashboardPage.getDataComponents().getBtnLast().setEnabled(dataModel.getCurrentPage() < (int) Math.ceil((double) dataModel.getTotalResults() / dataModel.getPageSize()));
    }
}
