package com.jimboyz.cims.view;

import com.jimboyz.cims.AppProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddMedicalDataPage extends JDialog {

    private JButton btnSave;
    private JButton btnClear;
    private final MedicalDataPage medicalDataPage;
    private final AppProperties appProperties = new AppProperties();

    public AddMedicalDataPage(Frame frame) {

        super(frame, "Add Medical Data", true);

        medicalDataPage = new MedicalDataPage();

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width - 490, Toolkit.getDefaultToolkit().getScreenSize().height - 150);
        this.setLocationRelativeTo(frame);
        this.add(new JScrollPane(medicalDataPage), BorderLayout.CENTER);
        this.add(btnComponents(), BorderLayout.SOUTH);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(appProperties.clearFieldUponClosingDialog) {
                    clearField();
                }
            }
        });

    }

    private JPanel btnComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        btnSave = SwingUtil.button("Save", "save-medical-data", "save-med-merge", new Font("Dialog", Font.PLAIN, 15), Color.BLACK, Cursor.HAND_CURSOR);
        btnClear = SwingUtil.button("Clear", "clear-medical-data", "clear-med", btnSave.getFont(), Color.BLACK, Cursor.HAND_CURSOR);

        btnSave.setMnemonic('s');
        btnClear.setMnemonic('c');

        panel.add(btnSave);
        panel.add(btnClear);

        return panel;
    }

    public void saveMedicalDataListener(ActionListener listener) {
        btnSave.addActionListener(listener);
    }

    public void clearMedicalDataListener(ActionListener listener) {
        btnClear.addActionListener(listener);
    }

    public void clearField() {
        medicalDataPage.clear();
    }

    public MedicalDataPage getMedicalData() {
        return medicalDataPage;
    }

    public JButton getBtnSave() {
        return btnSave;
    }
}
