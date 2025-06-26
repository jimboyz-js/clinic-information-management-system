package com.jimboyz.cims.sample.anotherSample;

import com.jimboyz.cims.model.dao.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class StudentSearchUI extends JFrame {

    private final StudentSearchService searchService;
    private JTextField txtFirstName, txtLastName, txtGender, txtId;
    private JButton btnGotoFirst, btnNext, btnPrevious, btnGotoLast, btnSearch;
    private JTable table;
    private DefaultTableModel tableModel;

    private int pageNumber = 0;
    private final int pageSize = 10;
    private long totalRecords = 0;

    public StudentSearchUI() {
        searchService = new StudentSearchService();
        initUI();
    }

    private void initUI() {
        setTitle("Hibernate Search with Pagination");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel(new GridLayout(2, 4));
        txtFirstName = new JTextField();
        txtLastName = new JTextField();
        txtGender = new JTextField();
        txtId = new JTextField();

        searchPanel.add(new JLabel("First Name:"));
        searchPanel.add(txtFirstName);
        searchPanel.add(new JLabel("Last Name:"));
        searchPanel.add(txtLastName);
        searchPanel.add(new JLabel("Gender:"));
        searchPanel.add(txtGender);
        searchPanel.add(new JLabel("ID:"));
        searchPanel.add(txtId);

        btnSearch = new JButton("Search");
        btnGotoFirst = new JButton("<< First");
        btnPrevious = new JButton("< Previous");
        btnNext = new JButton("Next >");
        btnGotoLast = new JButton("Last >>");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnGotoFirst);
        buttonPanel.add(btnPrevious);
        buttonPanel.add(btnNext);
        buttonPanel.add(btnGotoLast);
        buttonPanel.add(btnSearch);

        tableModel = new DefaultTableModel(new String[]{"ID", "First Name", "Last Name", "Gender"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        btnSearch.addActionListener(this::performSearch);
        btnGotoFirst.addActionListener(e -> goToPage(0));
        btnPrevious.addActionListener(e -> goToPage(Math.max(0, pageNumber - 1)));
        btnNext.addActionListener(e -> goToPage(Math.min((int) (totalRecords / pageSize), pageNumber + 1)));
        btnGotoLast.addActionListener(e -> goToPage((int) (totalRecords / pageSize)));

        setVisible(true);
    }

    private void performSearch(ActionEvent event) {
        pageNumber = 0;
        totalRecords = searchService.getTotalRecords(txtFirstName.getText(), txtLastName.getText(), txtGender.getText(), txtId.getText());
        updateTable();
    }

    private void goToPage(int page) {
        pageNumber = page;
        updateTable();
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        List<Student> students = searchService.searchEmployees(txtFirstName.getText(), txtLastName.getText(), txtGender.getText(), txtId.getText(), pageNumber, pageSize);

        for (Student student : students) {
            tableModel.addRow(new Object[]{student.getId(), student.getFirstname(), student.getLastname(), student.getGender()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentSearchUI::new);
    }
}

