package com.jimboyz.cims.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/*
 * Created by Jimboy M. Sarona on TUE. Feb 18, 2025 03:07 PM 2025
 */

/**
 * @author jimboy Ni ChOy!!!
 */

public class ShowBasicInfo extends JPanel {

    private JTextField txtName;
    private JTextField txtCourse;
    private JTextField txtGender;
    private JTextField txtYearLevel;
    private JTextField txtStatus;
    private JTextField txtBloodType;
    private JTextField txtBirthdate;
    private JTextField txtHeight;
    private JTextField txtAge;
    private JTextField txtWeight;
    private JTextField txtAddress;
    private JTextField txtPulseRate;
    private JTextField txtEmailAdd;
    private JTextField txtBp;
    private JLabel lblPhoto;

    public ShowBasicInfo() {
        initComponents();
    }

    private void initComponents() {

        JLabel lblName = SwingUtil.label("Name:", new Font("Times New Roman", Font.PLAIN, 17), Color.BLACK);
        lblPhoto = new JLabel();
        JLabel lblCourse = SwingUtil.label("Course:", new Font("Times New Roman", Font.PLAIN, 17), Color.BLACK);
        JLabel lblGender = SwingUtil.label("Gender:", new Font("Times New Roman", Font.PLAIN, 17), Color.BLACK);
        JLabel lblYearLevel = SwingUtil.label("Year Level:", new Font("Times New Roman", Font.PLAIN, 17), Color.BLACK);
        JLabel lblStatus = SwingUtil.label("Civil Status:", new Font("Times New Roman", Font.PLAIN, 17), Color.BLACK);
        JLabel lblBloodType = SwingUtil.label("Blood Type:", new Font("Times New Roman", Font.PLAIN, 17), Color.BLACK);
        JLabel lblBirthdate = SwingUtil.label("Birthdate:", new Font("Times New Roman", Font.PLAIN, 17), Color.BLACK);
        JLabel lblHeight = SwingUtil.label("Height:", new Font("Times New Roman", Font.PLAIN, 17), Color.BLACK);
        JLabel lblAge = SwingUtil.label("Age:", new Font("Times New Roman", Font.PLAIN, 17), Color.BLACK);
        JLabel lblWeight = SwingUtil.label("Weight:", new Font("Times New Roman", Font.PLAIN, 17), Color.BLACK);
        JLabel lblAddress = SwingUtil.label("Address:", new Font("Times New Roman", Font.PLAIN, 17), Color.BLACK);
        JLabel lblPulseRate = SwingUtil.label("Pulse Rate:", new Font("Times New Roman", Font.PLAIN, 17), Color.BLACK);
        JLabel lblEmailAdd = SwingUtil.label("Email Add.:", new Font("Times New Roman", Font.PLAIN, 17), Color.BLACK);
        JLabel lblBp = SwingUtil.label("BP.:", new Font("Times New Roman", Font.PLAIN, 17), Color.BLACK);

        txtName = SwingUtil.textField(null, lblName.getFont(), lblName.getForeground());
        txtCourse = SwingUtil.textField(null, lblName.getFont(), lblName.getForeground());
        txtGender = SwingUtil.textField(null, lblName.getFont(), lblName.getForeground());
        txtYearLevel = SwingUtil.textField(null, lblName.getFont(), lblName.getForeground());
        txtStatus = SwingUtil.textField(null, lblName.getFont(), lblName.getForeground());
        txtBloodType = SwingUtil.textField(null, lblName.getFont(), lblName.getForeground());
        txtBirthdate = SwingUtil.textField(null, lblName.getFont(), lblName.getForeground());
        txtHeight = SwingUtil.textField(null, lblName.getFont(), lblName.getForeground());
        txtAge = SwingUtil.textField(null, lblName.getFont(), lblName.getForeground());
        txtWeight = SwingUtil.textField(null, lblName.getFont(), lblName.getForeground());
        txtAddress = SwingUtil.textField(null, lblName.getFont(), lblName.getForeground());
        txtPulseRate = SwingUtil.textField(null, lblName.getFont(), lblName.getForeground());
        txtEmailAdd = SwingUtil.textField(null, lblName.getFont(), lblName.getForeground());
        txtBp = SwingUtil.textField(null, lblName.getFont(), lblName.getForeground());

        //======== this ========
        setBackground(new Color(232, 232, 228));
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        setLayout(new MigLayout(
                "hidemode 3",
                                // columns
                                "[fill]" +
                                "[fill]" +
                                "[fill]" +
                                "[fill]" +
                                "[fill]",
                                // rows
                                "[]" +
                                "[]" +
                                "[]" +
                                "[]" +
                                "[]" +
                                "[]" +
                                "[]"));

        //---- lblPhoto ----
        lblPhoto.setText("Photo");
        lblPhoto.setToolTipText("Photo");
        lblPhoto.setFont(new Font("Montserrat", Font.BOLD, 19));
        lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
        lblPhoto.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
        add(lblPhoto, "cell 0 0 0 6,width 150:150:150,height 150:150:150");

        //---- lblName ----
        add(lblName, "cell 1 1");

        //---- txtName ----
        txtName.setEditable(false);
        txtName.setFocusable(false);
        add(txtName, "cell 2 1,width 150:700:700");

        //---- label2 ----
        add(lblCourse, "cell 3 1");

        //---- textField1 ----
        txtCourse.setEditable(false);
        txtCourse.setFocusable(false);
        add(txtCourse, "cell 4 1,width 150:700:700");

        //---- lblGender ----
        add(lblGender, "cell 1 2");

        //---- txtGender ----
        txtGender.setEditable(false);
        txtGender.setFocusable(false);
        add(txtGender, "cell 2 2,width 150:700:700");

        //---- label3 ----
        add(lblYearLevel, "cell 3 2");

        //---- textField2 ----
        txtYearLevel.setEditable(false);
        txtYearLevel.setFocusable(false);
        add(txtYearLevel, "cell 4 2,width 150:700:700");

        //---- lblStatus ----
        add(lblStatus, "cell 1 3");

        //---- txtStatus ----
        txtStatus.setEditable(false);
        txtStatus.setFocusable(false);
        add(txtStatus, "cell 2 3,width 150:700:700");

        //---- label5 ----
        add(lblBloodType, "cell 3 3");

        //---- textField4 ----
        txtBloodType.setEditable(false);
        txtBloodType.setFocusable(false);
        add(txtBloodType, "cell 4 3,width 150:700:700");

        //---- lblBirthdate ----
        add(lblBirthdate, "cell 1 4");

        //---- txtBirthdate ----
        txtBirthdate.setEditable(false);
        txtBirthdate.setFocusable(false);
        add(txtBirthdate, "cell 2 4,width 150:700:700");

        //---- label4 ----
        add(lblHeight, "cell 3 5");
        //---- textField3 ----
        txtHeight.setEditable(false);
        txtHeight.setFocusable(false);
        add(txtHeight, "cell 4 5,width 150:700:700");

        //---- lblAge ----
        add(lblAge, "cell 1 5");

        //---- txtAge ----
        txtAge.setEditable(false);
        txtAge.setFocusable(false);
        add(txtAge, "cell 2 5,width 150:700:700");

        //---- label7 ----
        add(lblWeight, "cell 3 6");

        //---- textField6 ----
        txtWeight.setEditable(false);
        txtWeight.setFocusable(false);
        add(txtWeight, "cell 4 6,width 150:700:700");

        //---- lblAddress ----
        add(lblAddress, "cell 1 6");

        //---- txtAddress ----
        txtAddress.setEditable(false);
        txtAddress.setFocusable(false);
        add(txtAddress, "cell 2 6,width 150:700:700");

        //---- label6 ----
        add(lblPulseRate, "cell 3 4");

        //---- textField5 ----
        txtPulseRate.setEditable(false);
        txtPulseRate.setFocusable(false);
        add(txtPulseRate, "cell 4 4,width 150:700:700");

//		//---- lblEmailAdd ----
        add(lblEmailAdd, "cell 1 7");

        //---- txtEmailAdd ----
        txtEmailAdd.setEditable(false);
        txtEmailAdd.setFocusable(false);
        add(txtEmailAdd, "cell 2 7,width 150:700:700");

        //---- label8 ----
        add(lblBp, "cell 3 7");

        //---- textField7 ----
        txtBp.setEditable(false);
        txtBp.setFocusable(false);
        add(txtBp, "cell 4 7,width 150:700:700");
    }

    public void setTxtName(String txtName) {
        this.txtName.setText(txtName);
    }

    public void setTxtCourse(String txtCourse) {
        this.txtCourse.setText(txtCourse);
    }

    public void setTxtGender(String txtGender) {
        this.txtGender.setText(txtGender);
    }

    public void setTxtYearLevel(String txtYearLevel) {
        this.txtYearLevel.setText(txtYearLevel);
    }

    public void setTxtStatus(String txtStatus) {
        this.txtStatus.setText(txtStatus);
    }

    public void setTxtBloodType(String txtBloodType) {
        this.txtBloodType.setText(txtBloodType);
    }

    public void setTxtBirthdate(String txtBirthdate) {
        this.txtBirthdate.setText(txtBirthdate);
    }

    public void setTxtHeight(String txtHeight) {
        this.txtHeight.setText(txtHeight);
    }

    public void setTxtAge(String txtAge) {
        this.txtAge.setText(txtAge);
    }

    public void setTxtWeight(String txtWeight) {
        this.txtWeight.setText(txtWeight);
    }

    public void setTxtAddress(String txtAddress) {
        this.txtAddress.setText(txtAddress);
    }

    public void setTxtPulseRate(String txtPulseRate) {
        this.txtPulseRate.setText(txtPulseRate);
    }

    public void setTxtEmailAdd(String txtEmailAdd) {
        this.txtEmailAdd.setText(txtEmailAdd);
    }

    public void setTxtBp(String txtBp) {
        this.txtBp.setText(txtBp);
    }

    public void setStudentPhoto(byte[] img) {
        if(img != null) {
            this.lblPhoto.setText("");
        } else {
            this.lblPhoto.setText("Photo");
        }
        this.lblPhoto.setIcon(img != null ? SwingUtil.newImageIcon(img, lblPhoto.getWidth(), lblPhoto.getHeight(), Image.SCALE_SMOOTH) : null);
    }

    public void clearField() {
        txtName.setText("");
        txtCourse.setText("");
        txtGender.setText("");
        txtYearLevel.setText("");
        txtBirthdate.setText("");
        txtHeight.setText("");
        txtAge.setText("");
        txtWeight.setText("");
        txtAddress.setText("");
        txtPulseRate.setText("");
        txtEmailAdd.setText("");
        txtBp.setText("");
        txtBloodType.setText("");
        txtStatus.setText("");
        lblPhoto.setText("Photo");
        lblPhoto.setIcon(null);
    }
}