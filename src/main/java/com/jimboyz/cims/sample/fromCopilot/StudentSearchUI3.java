package com.jimboyz.cims.sample.fromCopilot;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.jimboyz.cims.model.dao.Student;
import org.hibernate.Session;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;

import java.util.List;

public class StudentSearchUI3 extends JFrame {
    private JTextField txtFirstname, txtLastname, txtGender, txtId;
    private JButton btnGotoFirst, btnNext, btnPrevious, btnGotoLast, btnSearch;
    private JTable table;
    private DefaultTableModel tableModel;
    private int currentPage = 1;
    private final int pageSize = 10;
    private long totalResults = 0;
    private final StudentDaox studentDaox;

    public StudentSearchUI3(Session session) {
        this.studentDaox = new StudentDaox(session);

        setTitle("Student Search");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel searchPanel = new JPanel();
        txtFirstname = new JTextField(10);
        txtLastname = new JTextField(10);
        txtGender = new JTextField(10);
        txtId = new JTextField(10);

        searchPanel.add(new JLabel("First Name:"));
        searchPanel.add(txtFirstname);
        searchPanel.add(new JLabel("Last Name:"));
        searchPanel.add(txtLastname);
        searchPanel.add(new JLabel("Gender:"));
        searchPanel.add(txtGender);
        searchPanel.add(new JLabel("ID:"));
        searchPanel.add(txtId);

        btnGotoFirst = new JButton("|<<");
        btnPrevious = new JButton("<<");
        btnNext = new JButton(">>");
        btnGotoLast = new JButton(">>|");
        btnSearch = new JButton("Search");

        tableModel = new DefaultTableModel(new String[]{"ID", "First Name", "Last Name", "Gender"}, 0);
        table = new JTable(tableModel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnGotoFirst);
        buttonPanel.add(btnPrevious);
        buttonPanel.add(btnNext);
        buttonPanel.add(btnGotoLast);
        buttonPanel.add(btnSearch);

        add(searchPanel, "North");
        add(new JScrollPane(table), "Center");
        add(buttonPanel, "South");

        setupListeners();
        loadPage(1);

        setVisible(true);
    }

    private void setupListeners() {
        btnGotoFirst.addActionListener(e -> loadPage(1));
        btnPrevious.addActionListener(e -> loadPage(currentPage - 1));
        btnNext.addActionListener(e -> loadPage(currentPage + 1));
        btnGotoLast.addActionListener(e -> loadPage((int) Math.ceil((double) totalResults / pageSize)));
        btnSearch.addActionListener(e -> loadPage(1));
    }

    private void loadPage(int page) {
        String firstname = txtFirstname.getText();
        String lastname = txtLastname.getText();
        String gender = txtGender.getText();
//        Long id = null;
        String id = null;
        if (!txtId.getText().isEmpty()) {
            try {
//                id = Long.parseLong(txtId.getText());
                id = txtId.getText();
            } catch (NumberFormatException ignored) {}
        }

        var result = studentDaox.searchEmployees(firstname, lastname, gender, id, page, pageSize);
        totalResults = result.total().hitCount();
        List<Student> employees = result.hits();

        tableModel.setRowCount(0);
        for (Student emp : employees) {
            System.out.println(emp);
            tableModel.addRow(new Object[]{emp.getIdNumber(), emp.getFirstname(), emp.getLastname(), emp.getGender()});
        }

        currentPage = page;
        updateButtonState();
    }

    private void updateButtonState() {
        btnGotoFirst.setEnabled(currentPage > 1);
        btnPrevious.setEnabled(currentPage > 1);
        btnNext.setEnabled(currentPage < (int) Math.ceil((double) totalResults / pageSize));
        btnGotoLast.setEnabled(currentPage < (int) Math.ceil((double) totalResults / pageSize));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Session session = HibernateUtil2.getSessionFactory().openSession();

            // Fixed
            // Add this code...
            // Working
            SearchSession searchSession = Search.session(session);
            try {
                searchSession.massIndexer().startAndWait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            new StudentSearchUI3(session);
        });
    }
}

