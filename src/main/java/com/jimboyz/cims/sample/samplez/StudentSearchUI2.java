package com.jimboyz.cims.sample.samplez;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import com.jimboyz.cims.err.Message;
import com.jimboyz.cims.model.dao.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.engine.search.query.SearchResult;

public class StudentSearchUI2 extends JFrame {

    private JTextField txtFirstname, txtLastname, txtGender, txtId;
    private JButton btnGotoFirst, btnNext, btnPrevious, btnGotoLast, btnSearch;
    private JTable table;
    private DefaultTableModel tableModel;

    private int currentPage = 1;
    private final int pageSize = 10;
    private long totalResults = 0;
    private Session session;
    private StudentDAOz studentDAOz;

    public StudentSearchUI2(Session session) {
        this.session = session;
        this.studentDAOz = new StudentDAOz(session);

        setTitle("Student Search");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel(new GridLayout(2, 4));
        txtFirstname = new JTextField();
        txtLastname = new JTextField();
        txtGender = new JTextField();
        txtId = new JTextField();

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
            } catch (NumberFormatException ignored) { }
        }

        SearchResult<Student> result = studentDAOz.searchStudents(firstname, lastname, gender, id, page, pageSize);
        totalResults = result.total().hitCount();
        List<Student> student = result.hits();
        Message.displaySuccessMessage(student.toString());
        tableModel.setRowCount(0);
        for (Student students : student) {
            System.out.println(students);
            Message.displaySuccessMessage(students.toString());
            tableModel.addRow(new Object[]{students.getIdNumber(), students.getFirstname(), students.getLastname(), students.getGender()});
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
//            Session session = HibernateUtil.getSessionFactory().openSession();
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            new StudentSearchUI2(session);
        });
    }
}
