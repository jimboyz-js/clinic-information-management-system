package com.jimboyz.cims.view;

import com.jimboyz.cims.err.ErrorDialog;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ReportDialog extends JDialog {

    public ReportDialog(String title, Frame owner) {
        super(owner, title, true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width - 590, Toolkit.getDefaultToolkit().getScreenSize().height - 140);
        this.setLocationRelativeTo(owner);
        this.setResizable(true);
    }

    public void viewReport(JRBeanCollectionDataSource dataSource) {

        try {

//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cims03272025", "root", "");


//            Map<String, Object> map = new HashMap<>();
//            map.put("ids", id);
//            JasperDesign design = JRXmlLoader.load(new File("C:\\Users\\jimboy\\JaspersoftWorkspace\\MyReports\\MedicalReport.jrxml"));
//            JasperDesign design = JRXmlLoader.load(getClass().getClassLoader().getResourceAsStream("MedicalReport.jrxml"));
//            JasperReport report = JasperCompileManager.compileReport(design);
//            String report = JasperCompileManager.compileReportToFile("C:\\Users\\jimboy\\JaspersoftWorkspace\\MyReports\\MedicalReport.jrxml");
            String report = "C:\\Users\\jimboy\\JaspersoftWorkspace\\MyReports\\MedicalReport.jasper";
            JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), dataSource);
            JRViewer viewer = new JRViewer(print);

//            JPanel panel = new JPanel(new BorderLayout());
//            this.add(panel, BorderLayout.CENTER);
//
//            panel.removeAll();
//            panel.repaint();
//            panel.revalidate();

            this.add(viewer, BorderLayout.CENTER);

        } catch (JRException e) {
            ErrorDialog.show(e);
        }
    }
}
