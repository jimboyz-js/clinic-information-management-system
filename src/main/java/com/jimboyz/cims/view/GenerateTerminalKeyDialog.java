package com.jimboyz.cims.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GenerateTerminalKeyDialog extends JDialog {

    private JPanel panel;
    private final JTextPane textPane;
    private JButton saveButton;
    private JButton generateButton;
    private JButton exitButton;
    private final CardLayout cl;
    private final JPanel panelContainer;

    private JCheckBox chkAllowAddStudent;
    private JCheckBox chkDontAllowAddStudent;
    private JCheckBox chkAllowAddMedicalRec;
    private JCheckBox chkDontAllowAddMedicalRec;
    private JCheckBox chkAllowAddUser;
    private JCheckBox chkDontAllowAddUser;
    private JCheckBox chkAllowAdmin;
    private JCheckBox chkDontAllowAdmin;
    private JCheckBox chkAllowUpdateStudent;
    private JCheckBox chkDontAllowUpdateStudent;
    private JCheckBox chkAllowUpdateMedicalRec;
    private JCheckBox chkDontAllowUpdateMedicalRec;
    private JCheckBox chkAllowUpdateUser;
    private JCheckBox chkDontAllowUpdateUser;
    private JCheckBox chkAllowDeleteStudent;
    private JCheckBox chkDontAllowDeleteStudent;
    private JCheckBox chkAllowDeleteMedicalRec;
    private JCheckBox chkDontAllowDeleteMedicalRec;
    private JCheckBox chkAllowDeleteUser;
    private JCheckBox chkDontAllowDeleteUser;

    public GenerateTerminalKeyDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.setSize(new Dimension(550,450));
        this.setLocationRelativeTo(owner);

        cl = new CardLayout();
        panelContainer = new JPanel(cl);

        JRootPane rootPane = this.getRootPane();
        panel = new JPanel();
        rootPane.setContentPane(panel);

        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(238, 238, 238));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        textPane = new JTextPane();
        textPane.setFocusable(true);
        textPane.setFont(new Font("Montserrat", Font.BOLD, 20));
        textPane.setForeground(Color.BLACK);
        textPane.setCaretPosition(0);
        textPane.setPreferredSize(new Dimension(50, 50));

        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();

        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        panel.add(new JScrollPane(textPane), BorderLayout.NORTH);
        panel.add(listPanel(), BorderLayout.CENTER);
        panel.add(btnComponents(), BorderLayout.SOUTH);

    }

    private JPanel listPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(17, 1, 0, 1));

        panelContainer.setBorder(new TitledBorder(BorderFactory.createEmptyBorder(), "Manage Restriction", TitledBorder.LEFT, TitledBorder.TOP, new Font("Montserrat", Font.PLAIN, 13), new Color(0, 0, 0)));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true);
        splitPane.setDividerLocation(177);
        splitPane.setBorder(UIManager.getBorder("DesktopIcon.border"));

        DefaultListModel<IconListItem> listModel = new DefaultListModel<>();
        listModel.addElement(new IconListItem("Add Data", SwingUtil.newImageIcon("/com/jimboyz/cims/images/folder-open.png")));
        listModel.addElement(new IconListItem("Update Data", SwingUtil.newImageIcon("/com/jimboyz/cims/images/folder-open.png")));
        listModel.addElement(new IconListItem("Delete Data", SwingUtil.newImageIcon("/com/jimboyz/cims/images/folder-open.png")));
//        listModel.addElement(new IconListItem("Allowance", SwingUtil.newImageIcon("/com/jimboyz/cims/images/folder-open.png")));
//        listModel.addElement(new IconListItem("Deduction", SwingUtil.newImageIcon("/com/jimboyz/cims/images/folder-open.png")));
//        listModel.addElement(new IconListItem("Update Salary", SwingUtil.newImageIcon("/com/jimboyz/cims/images/folder-open.png")));
//        listModel.addElement(new IconListItem("Attendance", SwingUtil.newImageIcon("/com/jimboyz/cims/images/folder-open.png")));
//        listModel.addElement(new IconListItem("Update Attendance", SwingUtil.newImageIcon("/com/jimboyz/cims/images/folder-open.png")));
//        listModel.addElement(new IconListItem("Payment", SwingUtil.newImageIcon("/com/jimboyz/cims/images/folder-open.png")));
        listModel.addElement(new IconListItem("Other", UIManager.getIcon("FileView.computerIcon")));

        final JList<IconListItem> list = getIconListItemJList(listModel);

        splitPane.add(new JScrollPane(list));

        JScrollPane spAddData = scrollPane(addDataPanel());
        JScrollPane spUpdateData = scrollPane(updateDataPanel());
        JScrollPane spDeleteData = scrollPane(deleteDataPanel());

        panelContainer.add(spAddData, "add-data-panel");
        panelContainer.add(spUpdateData, "update-data-panel");
        panelContainer.add(spDeleteData, "delete-data-panel");
        panelContainer.add(otherPanel(), "other-panel");

        splitPane.add(panelContainer);
        panel.add(splitPane, BorderLayout.CENTER);

        return panel;
    }

    private JScrollPane scrollPane(JPanel panelCon) {
        JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setViewportView(panelCon);
        scrollPane.setBorder(null);

        return scrollPane;
    }

    private JPanel addDataPanel() {
        JPanel panel = new JPanel();
        panel.setMinimumSize(new Dimension(183, 150));
        panel.setLayout(new MigLayout(
                "hidemode 3",
                                // columns
                                "[grow]",
                                // rows
                                "[]" +
                                "[]" +
                                "[]"));

        ButtonGroup bgAddStudent = new ButtonGroup();

        JPanel addStudentPanel = getPrivActionPanel("Add Student");

        chkAllowAddStudent = getChk("Allow");
        chkDontAllowAddStudent = getChk("Don't Allow");

        bgAddStudent.add(chkAllowAddStudent);
        bgAddStudent.add(chkDontAllowAddStudent);

        addStudentPanel.add(chkAllowAddStudent, "cell 0 0");
        addStudentPanel.add(chkDontAllowAddStudent, "cell 1 0");

        JPanel addMedicalRecordPanel = getPrivActionPanel("Add Medical Data");

        ButtonGroup bgAddMedRec = new ButtonGroup();

        chkAllowAddMedicalRec = getChk("Allow");
        chkDontAllowAddMedicalRec = getChk("Don't Allow");

        bgAddMedRec.add(chkAllowAddMedicalRec);
        bgAddMedRec.add(chkDontAllowAddMedicalRec);

        addMedicalRecordPanel.add(chkAllowAddMedicalRec, "cell 0 0");
        addMedicalRecordPanel.add(chkDontAllowAddMedicalRec, "cell 1 0");

        JPanel addUserPanel = getPrivActionPanel("Add User");

        ButtonGroup bgAddUser = new ButtonGroup();

        chkAllowAddUser = getChk("Allow");
        chkDontAllowAddUser = getChk("Don't Allow");

        bgAddUser.add(chkAllowAddUser);
        bgAddUser.add(chkDontAllowAddUser);

        addUserPanel.add(chkAllowAddUser, "cell 0 0");
        addUserPanel.add(chkDontAllowAddUser, "cell 1 0");

        panel.add(addStudentPanel, "cell 0 0, grow");
        panel.add(addMedicalRecordPanel, "cell 0 1, grow");
        panel.add(addUserPanel, "cell 0 2, grow");

        return panel;
    }

    private JPanel updateDataPanel() {
        JPanel panel = new JPanel();
        panel.setMinimumSize(new Dimension(183, 150));
        panel.setLayout(new MigLayout(
                "hidemode 3",
                                // columns
                                "[grow]",
                                // rows
                                "[]" +
                                "[]" +
                                "[]"));

        ButtonGroup bgUpdateStudent = new ButtonGroup();

        JPanel updateStudentPanel = getPrivActionPanel("Update Student");

        chkAllowUpdateStudent = getChk("Allow");
        chkDontAllowUpdateStudent = getChk("Don't Allow");

        bgUpdateStudent.add(chkAllowUpdateStudent);
        bgUpdateStudent.add(chkDontAllowUpdateStudent);

        updateStudentPanel.add(chkAllowUpdateStudent, "cell 0 0");
        updateStudentPanel.add(chkDontAllowUpdateStudent, "cell 1 0");

        JPanel updateMedicalRecordPanel = getPrivActionPanel("Update Medical Data");

        ButtonGroup bgUpdateMedRec = new ButtonGroup();

        chkAllowUpdateMedicalRec = getChk("Allow");
        chkDontAllowUpdateMedicalRec = getChk("Don't Allow");

        bgUpdateMedRec.add(chkAllowUpdateMedicalRec);
        bgUpdateMedRec.add(chkDontAllowUpdateMedicalRec);

        updateMedicalRecordPanel.add(chkAllowUpdateMedicalRec, "cell 0 0");
        updateMedicalRecordPanel.add(chkDontAllowUpdateMedicalRec, "cell 1 0");

        JPanel updateUserPanel = getPrivActionPanel("Update User");

        ButtonGroup bgUpdateUser = new ButtonGroup();

        chkAllowUpdateUser = getChk("Allow");
        chkDontAllowUpdateUser = getChk("Don't Allow");

        bgUpdateUser.add(chkAllowUpdateUser);
        bgUpdateUser.add(chkDontAllowUpdateUser);

        updateUserPanel.add(chkAllowUpdateUser, "cell 0 0");
        updateUserPanel.add(chkDontAllowUpdateUser, "cell 1 0");

        panel.add(updateStudentPanel, "cell 0 0, grow");
        panel.add(updateMedicalRecordPanel, "cell 0 1, grow");
        panel.add(updateUserPanel, "cell 0 2, grow");

        return panel;
    }

    private JPanel deleteDataPanel() {
        JPanel panel = new JPanel();
        panel.setMinimumSize(new Dimension(183, 150));
        panel.setLayout(new MigLayout(
                "hidemode 3",
                                // columns
                                "[grow]",
                                // rows
                                "[]" +
                                "[]" +
                                "[]"));

        ButtonGroup bgDeleteStudent = new ButtonGroup();

        JPanel deleteStudentPanel = getPrivActionPanel("Delete Student");

        chkAllowDeleteStudent = getChk("Allow");
        chkDontAllowDeleteStudent = getChk("Don't Allow");

        bgDeleteStudent.add(chkAllowDeleteStudent);
        bgDeleteStudent.add(chkDontAllowDeleteStudent);

        deleteStudentPanel.add(chkAllowDeleteStudent, "cell 0 0");
        deleteStudentPanel.add(chkDontAllowDeleteStudent, "cell 1 0");

        JPanel deleteMedicalRecordPanel = getPrivActionPanel("Delete Medical Data");

        ButtonGroup bgDeleteMedRec = new ButtonGroup();

        chkAllowDeleteMedicalRec = getChk("Allow");
        chkDontAllowDeleteMedicalRec = getChk("Don't Allow");

        bgDeleteMedRec.add(chkAllowDeleteMedicalRec);
        bgDeleteMedRec.add(chkDontAllowDeleteMedicalRec);

        deleteMedicalRecordPanel.add(chkAllowDeleteMedicalRec, "cell 0 0");
        deleteMedicalRecordPanel.add(chkDontAllowDeleteMedicalRec, "cell 1 0");

        JPanel deleteUserPanel = getPrivActionPanel("Delete User");

        ButtonGroup bgDeleteUser = new ButtonGroup();

        chkAllowDeleteUser = getChk("Allow");
        chkDontAllowDeleteUser = getChk("Don't Allow");

        bgDeleteUser.add(chkAllowDeleteUser);
        bgDeleteUser.add(chkDontAllowDeleteUser);

        deleteUserPanel.add(chkAllowDeleteUser, "cell 0 0");
        deleteUserPanel.add(chkDontAllowDeleteUser, "cell 1 0");

        panel.add(deleteStudentPanel, "cell 0 0, grow");
        panel.add(deleteMedicalRecordPanel, "cell 0 1, grow");
        panel.add(deleteUserPanel, "cell 0 2, grow");

        return panel;
    }

    private static JCheckBox getChk(String text) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkBox.setFocusable(false);

        return checkBox;
    }

    private static JPanel getPrivActionPanel(String titleBorder) {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, titleBorder, TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
        panel.setMinimumSize(new Dimension(167, 50));
        panel.setLayout(new MigLayout(
                "hidemode 3",
                        // columns
                        "[fill]" +
                        "[fill]",
                        // rows
                        "[]" +
                        "[]" +
                        "[]"));
        return panel;
    }

    private JPanel otherPanel() {
        JPanel panel = new JPanel();
        panel.setMinimumSize(new Dimension(183, 150));
        panel.setLayout(new MigLayout(
                "hidemode 3",
                                // columns
                                "[grow]",
                                // rows
                                "[]" +
                                "[]" +
                                "[]"));

        ButtonGroup bg = new ButtonGroup();

        JPanel administratorPanel = getPrivActionPanel("Administration");

        chkAllowAdmin = getChk("Yes");
        chkDontAllowAdmin = getChk("No");

        bg.add(chkAllowAdmin);
        bg.add(chkDontAllowAdmin);

        administratorPanel.add(chkAllowAdmin, "cell 0 0");
        administratorPanel.add(chkDontAllowAdmin, "cell 0 1");

        panel.add(administratorPanel, "cell 0 0, grow");

        return panel;
    }

    private JList<IconListItem> getIconListItemJList(DefaultListModel<IconListItem> listModel) {
        JList<IconListItem> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(e -> {
            if(e.getValueIsAdjusting()) {
                exListAction(list);
            }
        });
        list.setCellRenderer(new IconListRenderer());

        list.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                exListAction(list);
            }
        });
        return list;
    }

    private void exListAction(JList<IconListItem> list) {
        String value = list.getSelectedValue().toString();
        EventQueue.invokeLater(()->{
            switch (value) {
                case "Add Data" -> cl.show(panelContainer, "add-data-panel");
                case "Update Data" -> cl.show(panelContainer, "update-data-panel");
                case "Delete Data" -> cl.show(panelContainer, "delete-data-panel");
                case "Other" -> cl.show(panelContainer, "other-panel");
            }
        });
    }

    public void defaultCheckBoxSelected() {
        chkAllowAddStudent.setSelected(true);
        chkAllowAddMedicalRec.setSelected(true);
        chkDontAllowAddUser.setSelected(true);
        chkAllowUpdateStudent.setSelected(true);
        chkAllowUpdateMedicalRec.setSelected(true);
        chkDontAllowUpdateUser.setSelected(true);
        chkAllowDeleteStudent.setSelected(true);
        chkAllowDeleteMedicalRec.setSelected(true);
        chkDontAllowDeleteUser.setSelected(true);
        chkDontAllowAdmin.setSelected(true);
    }

    public void setAdminPrivileges() {
        chkAllowAddStudent.setSelected(true);
        chkAllowAddMedicalRec.setSelected(true);
        chkAllowAddUser.setSelected(true);
        chkAllowUpdateStudent.setSelected(true);
        chkAllowUpdateMedicalRec.setSelected(true);
        chkAllowUpdateUser.setSelected(true);
        chkAllowDeleteStudent.setSelected(true);
        chkAllowDeleteMedicalRec.setSelected(true);
        chkAllowDeleteUser.setSelected(true);
        chkAllowAdmin.setSelected(true);

        enableDisableChk(false);
    }

    public void enableDisableChk(boolean enable) {
        chkAllowAddStudent.setEnabled(enable);
        chkAllowAddMedicalRec.setEnabled(enable);
        chkAllowAddUser.setEnabled(enable);
        chkAllowUpdateStudent.setEnabled(enable);
        chkAllowUpdateMedicalRec.setEnabled(enable);
        chkAllowUpdateUser.setEnabled(enable);
        chkAllowDeleteStudent.setEnabled(enable);
        chkAllowDeleteMedicalRec.setEnabled(enable);
        chkAllowDeleteUser.setEnabled(enable);
        chkDontAllowAddStudent.setEnabled(enable);
        chkDontAllowAddMedicalRec.setEnabled(enable);
        chkDontAllowAddUser.setEnabled(enable);
        chkDontAllowUpdateStudent.setEnabled(enable);
        chkDontAllowUpdateMedicalRec.setEnabled(enable);
        chkDontAllowUpdateUser.setEnabled(enable);
        chkDontAllowDeleteStudent.setEnabled(enable);
        chkDontAllowDeleteMedicalRec.setEnabled(enable);
        chkDontAllowDeleteUser.setEnabled(enable);
    }

    private JPanel btnComponents() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(7, 0, 0, 0));
        saveButton = SwingUtil.button("Save", "save", "save", new Font("Dialog", Font.BOLD, 12), Color.BLACK, Cursor.HAND_CURSOR);
        generateButton = SwingUtil.button("Re-generate", "regenerate", "generate", new Font("Dialog", Font.BOLD, 12), Color.BLACK, Cursor.HAND_CURSOR);
        exitButton = SwingUtil.button("Exit", "exit", "exit", new Font("Dialog", Font.BOLD, 12), Color.BLACK, Cursor.HAND_CURSOR);

        panel.add(saveButton);
        panel.add(generateButton);
        panel.add(exitButton);

        return panel;
    }

    public JTextPane getTextPane() {
        return textPane;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getGenerateButton() {
        return generateButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public void setChkAllowAddStudentListener(ActionListener listener) {
        this.chkAllowAddStudent.addActionListener(listener);
    }

    public void setChkDontAllowAddStudentListener(ActionListener listener) {
        this.chkDontAllowAddStudent.addActionListener(listener);
    }

    public void setChkAllowAddMedicalRecListener(ActionListener listener) {
        this.chkAllowAddMedicalRec.addActionListener(listener);
    }

    public void setChkDontAllowAddMedicalRecListener(ActionListener listener) {
        this.chkDontAllowAddMedicalRec.addActionListener(listener);
    }

    public void setChkAllowAddUserListener(ActionListener listener) {
        this.chkAllowAddUser.addActionListener(listener);
    }

    public void setChkDontAllowAddUserListener(ActionListener listener) {
        this.chkDontAllowAddUser.addActionListener(listener);
    }

    public void setChkAllowAdminListener(ActionListener listener) {
        this.chkAllowAdmin.addActionListener(listener);
    }

    public void setChkDontAllowAdminListener(ActionListener listener) {
        this.chkDontAllowAdmin.addActionListener(listener);
    }

    public void setChkAllowUpdateStudentListener(ActionListener listener) {
        this.chkAllowUpdateStudent.addActionListener(listener);
    }

    public void setChkDontAllowUpdateStudentListener(ActionListener listener) {
        this.chkDontAllowUpdateStudent.addActionListener(listener);
    }

    public void setChkAllowUpdateMedicalRecListener(ActionListener listener) {
        this.chkAllowUpdateMedicalRec.addActionListener(listener);
    }

    public void setChkDontAllowUpdateMedicalRecListener(ActionListener listener) {
        this.chkDontAllowUpdateMedicalRec.addActionListener(listener);
    }

    public void setChkAllowUpdateUserListener(ActionListener listener) {
        this.chkAllowUpdateUser.addActionListener(listener);
    }

    public void setChkDontAllowUpdateUserListener(ActionListener listener) {
        this.chkDontAllowUpdateUser.addActionListener(listener);
    }

    public void setChkAllowDeleteStudentListener(ActionListener listener) {
        this.chkAllowDeleteStudent.addActionListener(listener);
    }

    public void setChkDontAllowDeleteStudentListener(ActionListener listener) {
        this.chkDontAllowDeleteStudent.addActionListener(listener);
    }

    public void setChkAllowDeleteMedicalRecListener(ActionListener listener) {
        this.chkAllowDeleteMedicalRec.addActionListener(listener);
    }

    public void setChkDontAllowDeleteMedicalRecListener(ActionListener listener) {
        this.chkDontAllowDeleteMedicalRec.addActionListener(listener);
    }

    public void setChkAllowDeleteUserListener(ActionListener listener) {
        this.chkAllowDeleteUser.addActionListener(listener);
    }

    public void setChkDontAllowDeleteUserListener(ActionListener listener) {
        this.chkDontAllowDeleteUser.addActionListener(listener);
    }

    public JCheckBox getChkAllowAddStudent() {
        return chkAllowAddStudent;
    }

    public JCheckBox getChkDontAllowAddStudent() {
        return chkDontAllowAddStudent;
    }

    public JCheckBox getChkAllowAddMedicalRec() {
        return chkAllowAddMedicalRec;
    }

    public JCheckBox getChkDontAllowAddMedicalRec() {
        return chkDontAllowAddMedicalRec;
    }

    public JCheckBox getChkAllowAddUser() {
        return chkAllowAddUser;
    }

    public JCheckBox getChkDontAllowAddUser() {
        return chkDontAllowAddUser;
    }

    public JCheckBox getChkAllowAdmin() {
        return chkAllowAdmin;
    }

    public JCheckBox getChkDontAllowAdmin() {
        return chkDontAllowAdmin;
    }

    public JCheckBox getChkAllowUpdateStudent() {
        return chkAllowUpdateStudent;
    }

    public JCheckBox getChkDontAllowUpdateStudent() {
        return chkDontAllowUpdateStudent;
    }

    public JCheckBox getChkAllowUpdateMedicalRec() {
        return chkAllowUpdateMedicalRec;
    }

    public JCheckBox getChkDontAllowUpdateMedicalRec() {
        return chkDontAllowUpdateMedicalRec;
    }

    public JCheckBox getChkAllowUpdateUser() {
        return chkAllowUpdateUser;
    }

    public JCheckBox getChkDontAllowUpdateUser() {
        return chkDontAllowUpdateUser;
    }

    public JCheckBox getChkAllowDeleteStudent() {
        return chkAllowDeleteStudent;
    }

    public JCheckBox getChkDontAllowDeleteStudent() {
        return chkDontAllowDeleteStudent;
    }

    public JCheckBox getChkAllowDeleteMedicalRec() {
        return chkAllowDeleteMedicalRec;
    }

    public JCheckBox getChkDontAllowDeleteMedicalRec() {
        return chkDontAllowDeleteMedicalRec;
    }

    public JCheckBox getChkAllowDeleteUser() {
        return chkAllowDeleteUser;
    }

    public JCheckBox getChkDontAllowDeleteUser() {
        return chkDontAllowDeleteUser;
    }
}
