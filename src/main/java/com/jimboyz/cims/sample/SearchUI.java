package com.jimboyz.cims.sample;

import com.jimboyz.cims.err.Message;
import com.jimboyz.cims.model.dao.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchUI extends JFrame {
    private final HibernateSearchPagination searchPagination;
    private final JTextField searchField1;
    private final JTextField searchField2;
    private final JTextField searchField3;
    private final JTextArea resultArea;
    private final JLabel paginationLabel;
    private final JButton nextButton, prevButton, firstButton, lastButton;

    public SearchUI() {
        searchPagination = new HibernateSearchPagination();

        setTitle("Hibernate Search Pagination");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel(new GridLayout(2, 4));
        searchField1 = new JTextField("sarona");
        searchField2 = new JTextField("jim");
        searchField3 = new JTextField("male");
        JButton searchButton = new JButton("Search");

        searchPanel.add(new JLabel("FirstName:"));
        searchPanel.add(searchField1);
        searchPanel.add(new JLabel("LastName:"));
        searchPanel.add(searchField2);
        searchPanel.add(new JLabel("Gender:"));
        searchPanel.add(searchField3);
        searchPanel.add(searchButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        JPanel navigationPanel = new JPanel();
        firstButton = new JButton("First");
        prevButton = new JButton("Previous");
        nextButton = new JButton("Next");
        lastButton = new JButton("Last");
        paginationLabel = new JLabel("Page 0 of 0 (Total Records: 0)");

        navigationPanel.add(firstButton);
        navigationPanel.add(prevButton);
        navigationPanel.add(nextButton);
        navigationPanel.add(lastButton);
        navigationPanel.add(paginationLabel);

        add(searchPanel, BorderLayout.NORTH);
        add(navigationPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> performSearch());
        nextButton.addActionListener(e -> goToNextPage());
        prevButton.addActionListener(e -> goToPreviousPage());
        firstButton.addActionListener(e -> goToFirstPage());
        lastButton.addActionListener(e -> goToLastPage());

        updateNavigationState();
    }

    private void performSearch() {
        List<Student> results = searchPagination.searchWithPagination(
                searchField1.getText(),
                searchField2.getText(),
                searchField3.getText()
        );

        updateResults(results);
    }

    private void goToNextPage() {
        searchPagination.goToNextPage();
        performSearch();
    }

    private void goToPreviousPage() {
        searchPagination.goToPreviousPage();
        performSearch();
    }

    private void goToFirstPage() {
        searchPagination.goToFirstPage();
        performSearch();
    }

    private void goToLastPage() {
        searchPagination.goToLastPage();
        performSearch();
    }

    private void updateResults(List<Student> results) {
        resultArea.setText("hi ");
        for (Student entity : results) {
            resultArea.append(entity.toString() + "\n");
            System.out.println(entity);
//            Message.displayMessage(entity.toString());
        }
        updateNavigationState();
    }

    private void updateNavigationState() {
        paginationLabel.setText(String.format("Page %d of %d (Total Records: %d)",
                searchPagination.getCurrentPage(),
                searchPagination.getTotalPages(),
                searchPagination.getTotalRecords()));

        prevButton.setEnabled(searchPagination.getCurrentPage() > 1);
        nextButton.setEnabled(searchPagination.getCurrentPage() < searchPagination.getTotalPages());
        firstButton.setEnabled(searchPagination.getCurrentPage() > 1);
        lastButton.setEnabled(searchPagination.getCurrentPage() < searchPagination.getTotalPages());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SearchUI().setVisible(true));
    }
}
