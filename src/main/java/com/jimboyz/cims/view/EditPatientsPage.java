package com.jimboyz.cims.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/*
 * Created by Jimboy M. Sarona on Thu. March. 20 03:55 PM PST 2025
 */

/**
 * @author jimboy Ni ChOy!!!
 */

public class EditPatientsPage extends JPanel {

    private final JPanel panelInfoPage;
    private final JButton btnUpdate;

    public EditPatientsPage() {

        this.setLayout(new BorderLayout());

        panelInfoPage = new SetPatientsInfoPage();

        btnUpdate = ((SetPatientsInfoPage) panelInfoPage).getBtnSave();
        btnUpdate.setText("Update");
        btnUpdate.setName("update");
        btnUpdate.setActionCommand("update");

        this.add(panelInfoPage, BorderLayout.CENTER);
    }

    public SetPatientsInfoPage getPanelInfoPage() {
        return (SetPatientsInfoPage) panelInfoPage;
    }

    public void updateDataListener(ActionListener listener) {
        btnUpdate.addActionListener(listener);
    }

    //    private void initComponents() {
//
//        JPanel btnComponentPanel = new JPanel();
//
//        lblPhoto = SwingUtil.label("Photo", new Font("Montserrat", Font.BOLD, 19), Color.BLACK);
//        JLabel lblPersonalInfo = SwingUtil.label("Personal Information", new Font("Times New Roman", Font.PLAIN, 25), Color.BLACK);
//        JLabel lblContactInfo = SwingUtil.label("Contact Information", lblPersonalInfo.getFont(), lblPersonalInfo.getForeground());
//
//        JLabel lblFirstname = SwingUtil.label("Firstname:", new Font("Times New Roman", Font.PLAIN, 19), Color.BLACK);
//        txtFirstname = SwingUtil.textField("firstname", new Font("Times New Roman", Font.PLAIN, 19), Color.BLACK);
//        JLabel lblGender = SwingUtil.label("Gender:", lblFirstname.getFont(), lblFirstname.getForeground());
//        cmbGender = SwingUtil.comboBox(new String[]{"Male", "Female"}, txtFirstname.getFont(), true);
//
//        JLabel lblBirthdate = SwingUtil.label("Birthdate:", lblFirstname.getFont(), lblFirstname.getForeground());
//        txtBirthdate = SwingUtil.dateChooser(new Date(), new Font(appProperties.dateChooserFont, appProperties.dateChooserFontStyle, appProperties.dateChooserFontSize), appProperties.dateFormatString);
//        JLabel lblLastname = SwingUtil.label("Lastname:", lblFirstname.getFont(), lblFirstname.getForeground());
//        txtLastname = SwingUtil.textField("lastname", txtFirstname.getFont(), Color.BLACK);
//        JLabel lblCivilStatus = SwingUtil.label("Civil Status:", lblFirstname.getFont(), lblFirstname.getForeground());
//        cmbStatus = SwingUtil.comboBox(new String[]{"Single", "Married", "Windowed", "Divorced"}, txtFirstname.getFont(), false);
//        JLabel lblAge = SwingUtil.label("Age:", lblFirstname.getFont(), lblFirstname.getForeground());
//        txtAge = SwingUtil.textField("age", txtFirstname.getFont(), Color.BLACK);
//        JLabel lblCourse = SwingUtil.label("Course:", lblFirstname.getFont(), lblFirstname.getForeground());
//        cmbCourse = SwingUtil.comboBox(new String[]{"", "ACT", "BSIT", "BSBA", "BSCRIM", "DHMT", "HRM"}, txtFirstname.getFont(), true);
//        JLabel lblYearLevel = SwingUtil.label("Year Level:", lblFirstname.getFont(), lblFirstname.getForeground());
//        cmbYearLevel = SwingUtil.comboBox(new String[]{"", "1st Year", "2nd Year", "3rd Year", "4th Year", "Grade 7", "Grade 8", "Grade 9", "Grade 10", "Grade 11", "Grade 12", "Other"}, txtFirstname.getFont(), true);
//        JLabel lblMobileNo = SwingUtil.label("Mobile No.:", lblFirstname.getFont(), lblFirstname.getForeground());
//        txtMobileNo = SwingUtil.textField("mobileno", txtFirstname.getFont(), Color.BLACK);
//        JLabel lblEmailAdd = SwingUtil.label("Email Add.:", lblFirstname.getFont(), lblFirstname.getForeground());
//        txtEmailAdd = SwingUtil.textField("email-add", txtFirstname.getFont(), Color.BLACK);
//        JLabel lblIdno = SwingUtil.label("ID. No.:", lblFirstname.getFont(), lblFirstname.getForeground());
//        txtIdno = SwingUtil.textField("id-no", txtFirstname.getFont(), Color.BLACK);
//        JLabel lblAddress = SwingUtil.label("Address:", lblFirstname.getFont(), lblFirstname.getForeground());
//        txtBrgy = SwingUtil.textField("brgy", new Font(lblFirstname.getFont().getFontName(), Font.ITALIC, lblFirstname.getFont().getSize()), Color.GRAY);
//        txtMun = SwingUtil.textField("mun", txtBrgy.getFont(), txtBrgy.getForeground());
//        txtProv = SwingUtil.textField("prov", txtBrgy.getFont(), txtBrgy.getForeground());
//        txtBrgy.setText("Barangay");
//        txtMun.setText("City/Municipality");
//        txtProv.setText("Province");
//
//        txtBrgy.addFocusListener(new CustomFocusAdapter(txtBrgy, "Barangay"));
//        txtMun.addFocusListener(new CustomFocusAdapter(txtMun, "City/Municipality"));
//        txtProv.addFocusListener(new CustomFocusAdapter(txtProv, "Province"));
//
//        btnCamera = SwingUtil.button("Camera", "camera", "btn-camera", new Font("Dialog", Font.BOLD, 12), Color.BLACK, new Cursor(Cursor.HAND_CURSOR));
//        btnBrowse = SwingUtil.button("Browse", "browse", "btn-browse", btnCamera.getFont(), btnCamera.getForeground(), btnCamera.getCursor());
//        btnUpdate = SwingUtil.button("Update", "update", "btn-update", btnCamera.getFont(), btnCamera.getForeground(), btnCamera.getCursor());
//        btnClear = SwingUtil.button("Clear", "clear", "btn-clear", btnCamera.getFont(), btnCamera.getForeground(), btnCamera.getCursor());
//
//        //======== this ========
//        setLayout(new MigLayout(
//                "hidemode 3",
//                                // columns
//                                "[fill]" +
//                                "[fill]" +
//                                "[fill]" +
//                                "[fill]" +
//                                "[fill]" +
//                                "[fill]" +
//                                "[fill]" +
//                                "[fill]" +
//                                "[fill]" +
//                                "[fill]" +
//                                "[fill]",
//                                // rows
//                                "[]" +
//                                "[]" +
//                                "[]" +
//                                "[]" +
//                                "[]" +
//                                "[]" +
//                                "[]" +
//                                "[]" +
//                                "[]" +
//                                "[]" +
//                                "[]" +
//                                "[]"));
//
//        //---- JPanel ----
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
//        panel.setBackground(this.getBackground());
//        add(panel, "cell 0 5, width 150:150:150");
//
//        //---- lblPhoto ----
//        lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
//        lblPhoto.setToolTipText("Photo");
//        lblPhoto.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
//        add(lblPhoto, "cell 0 0 1 5, width 150:150:150,height 150:150:150");
//
//        //---- lblPersonalInfo ----
//        add(lblPersonalInfo, "cell 1 0 8 1");
//
//        //---- lblFirstname ----
//        add(lblFirstname, "cell 1 1");
//        add(txtFirstname, "cell 2 1 5 1, growx,  pushx");
//
//        //---- lblGender ----
//        add(lblGender, "cell 7 1");
//        add(cmbGender, "cell 8 1, pushx");
//
//        //---- lblBirthdate ----
//        add(lblBirthdate, "cell 9 1");
//        add(txtBirthdate, "cell 10 1, pushx, width 100");
//
//        //---- lblLastname ----
//        add(lblLastname, "cell 1 2");
//        add(txtLastname, "cell 2 2 5 1");
//
//        //---- lblCivilStatus ----
//        add(lblCivilStatus, "cell 7 2");
//        add(cmbStatus, "cell 8 2");
//
//        //---- lblAge ----
//        add(lblAge, "cell 9 2");
//        add(txtAge, "cell 10 2");
//
//        //---- lblCourse ----
//        add(lblCourse, "cell 1 3");
//        add(cmbCourse, "cell 2 3 5 1");
//
//        //---- lblYearLevel ----
//        add(lblYearLevel, "cell 7 3");
//        add(cmbYearLevel, "cell 8 3");
//
//        //---- lblContactInfo ----
//        add(lblContactInfo, "cell 1 5 8 1");
//
//        //---- btnCamera ----
//        btnCamera.setToolTipText("Camera");
//        panel.add(btnCamera);
//
//        //---- btnBrowse ----
//        btnBrowse.setToolTipText("Browse");
//        panel.add(btnBrowse);
//
//        //---- lblMobileNo ----
//        add(lblMobileNo, "cell 1 6");
//        add(txtMobileNo, "cell 2 6 5 1");
//
//        //---- lblEmailAdd ----
//        add(lblEmailAdd, "cell 7 6");
//        add(txtEmailAdd, "cell 8 6");
//
//        //---- lblIdno ----
//        add(lblIdno, "cell 9 6");
//        add(txtIdno, "cell 10 6");
//
//        //---- lblAddress ----
//        add(lblAddress, "cell 1 7");
//        add(txtBrgy, "cell 2 7 5 1");
//        add(txtMun, "cell 7 7 2 1");
//        add(txtProv, "cell 9 7 2 1");
//
//        //======== btnComponentPanel ========
//        {
//            btnComponentPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
//
//            //---- btnSave ----
//            btnComponentPanel.add(btnUpdate);
//
//            //---- btnClear ----
//            btnComponentPanel.add(btnClear);
//        }
//        add(btnComponentPanel, "cell 0 9 11 2");
//    }

//    public void toClearField() {
//        txtFirstname.setText("");
//        txtLastname.setText("");
//        cmbCourse.setSelectedIndex(0);
//        cmbGender.setSelectedIndex(0);
//        cmbStatus.setSelectedIndex(0);
//        cmbYearLevel.setSelectedIndex(0);
//        txtBirthdate.setDate(new Date());
//        txtAge.setText("");
//        txtMobileNo.setText("");
//        txtEmailAdd.setText("");
//        txtIdno.setText("");
//        txtBrgy.setText("Barangay");
//        txtMun.setText("City/Municipality");
//        txtProv.setText("Province");
//
//        txtBrgy.setForeground(Color.GRAY);
//        txtMun.setForeground(Color.GRAY);
//        txtProv.setForeground(Color.GRAY);
//        txtBrgy.setFont(new Font("Times New Roman", Font.ITALIC, 19));
//        txtMun.setFont(new Font("Times New Roman", Font.ITALIC, 19));
//        txtProv.setFont(new Font("Times New Roman", Font.ITALIC, 19));
//
//        lblPhoto.setIcon(null);
//        lblPhoto.setText("Photo");
//    }

}