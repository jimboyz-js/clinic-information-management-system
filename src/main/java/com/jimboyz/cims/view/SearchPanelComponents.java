package com.jimboyz.cims.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Objects;

public class SearchPanelComponents extends JPanel {

    private JTextField txtFirstname;
    private JTextField txtLastname;
    private JComboBox<String> cmbYearLevel;
    private JComboBox<String> cmbGender;
    private JTextField txtIdNum;
    private JButton btnSearch;

    public SearchPanelComponents() {
        this.setLayout(new BorderLayout(5, 5));
        this.setBackground(new Color(232, 232, 228));
        this.add(searchPanel(), BorderLayout.NORTH);
    }

    private JPanel searchPanel() {
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(this.getBackground());
        searchPanel.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 50));

        JLabel label1 = SwingUtil.label("Search", new Font("Montserrat", Font.BOLD, 12), Color.WHITE);
        label1.setOpaque(true);
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setBackground(Color.DARK_GRAY);

        txtFirstname = SwingUtil.textField("fname", new Font("Montserrat", Font.PLAIN, 13), Color.BLACK);
        txtLastname = SwingUtil.textField("lname", txtFirstname.getFont(), txtFirstname.getForeground());
        cmbYearLevel = SwingUtil.comboBox(new String[]{"", "1st Year", "2nd Year", "3rd Year", "4th Year"}, txtFirstname.getFont(), true);
        cmbGender = SwingUtil.comboBox(new String[]{"Male", "Female"}, txtFirstname.getFont(), true);
        txtIdNum = SwingUtil.textField("idno", txtFirstname.getFont(), txtFirstname.getForeground());
        btnSearch = SwingUtil.button("Search", "search", "btn-search", new Font("Montserrat", Font.PLAIN, 12), Color.BLACK, new Cursor(Cursor.HAND_CURSOR));

        //======== this ========

        //---- lblName ----
        JLabel lblName = SwingUtil.label("Name:", new Font("Montserrat", Font.PLAIN, 13), Color.BLACK);

        //---- lblGender ----
        JLabel lblGender = SwingUtil.label("Gender:", lblName.getFont(), lblName.getForeground());

        //---- lblYearLevel ----
        JLabel lblYearLevel = SwingUtil.label("Year Level:", lblName.getFont(), lblName.getForeground());

        //---- lblIdNum ----
        JLabel lblIdNum = SwingUtil.label("ID. No:", lblName.getFont(), lblName.getForeground());

        //---- btnSearch ----
        btnSearch.setText("Search");

        GroupLayout layout = new GroupLayout(searchPanel);
        searchPanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(lblName)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFirstname, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtLastname, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(lblGender, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbGender, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(lblYearLevel, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbYearLevel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(lblIdNum, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdNum, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(btnSearch)
                                .addContainerGap(47, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 1100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(txtLastname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblName)
                                        .addComponent(txtFirstname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblGender)
                                        .addComponent(cmbGender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblYearLevel)
                                        .addComponent(lblIdNum)
                                        .addComponent(txtIdNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbYearLevel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        );

        return searchPanel;
    }

    public void setBtnSearch(ActionListener listener) {
        btnSearch.addActionListener(listener);
    }

    public void setFirstnameListener(KeyListener listener) {
        txtFirstname.addKeyListener(listener);
    }

    public void setLastnameListener(KeyListener listener) {
        txtLastname.addKeyListener(listener);
    }

    public void setGenderListener(KeyListener listener) {
        cmbGender.getEditor().getEditorComponent().addKeyListener(listener);
    }

    public void setIdNumberListener(KeyListener listener) {
        txtIdNum.addKeyListener(listener);
    }

    public void setYearLevelListener(KeyListener listener) {
        cmbYearLevel.getEditor().getEditorComponent().addKeyListener(listener);
    }

    public String getTxtFirstname() {
        return txtFirstname.getText();
    }

    public String getTxtLastname() {
        return txtLastname.getText();
    }

    public String getCmbYearLevelItem() {
        return Objects.requireNonNull(cmbYearLevel.getSelectedItem()).toString();
    }

    public String getCmbGenderItem() {
        return Objects.requireNonNull(cmbGender.getSelectedItem()).toString();
    }

    public String getTxtIdNum() {
        return txtIdNum.getText();
    }
}