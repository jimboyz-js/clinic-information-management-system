package com.jimboyz.cims.view;

import com.jimboyz.cims.AppProperties;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class DataComponents extends JPanel {

    private final AppProperties appProperties;
    private final ShowBasicInfo basicInfo;
    private final SearchPanelComponents searchComponents;
    private final JTable table;
    private JButton btnFirst;
    private JButton btnLast;
    private JButton btnNext;
    private JButton btnPrev;
    private JLabel lblRecords;
    private JLabel lblPages;

    public DataComponents() {

        appProperties = new AppProperties();
        basicInfo = new ShowBasicInfo();
        searchComponents = new SearchPanelComponents();

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(true);
        table.setCellSelectionEnabled(true);
        table.setFillsViewportHeight(true);
        table.setRowHeight(27);
        table.setShowGrid(false);
        table.setShowHorizontalLines(false);
        table.setModel(new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        table.getTableHeader().setReorderingAllowed(appProperties.isReOrderingAllowed);
        table.getTableHeader().setResizingAllowed(appProperties.isResizable);
        table.setVisible(true);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setDefaultRenderer(new CustomTableHeader());
        table.setDefaultRenderer(Object.class, new CustomCellRenderer());
        ((DefaultTableCellRenderer) tableHeader.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        String[] columnName = {"ID", "Firstname", "Lastname", "Gender", "Birthdate", "Age", "Course", "Year Level"};
        tableModel.setColumnIdentifiers(columnName);

        tableHeader.setFont(new Font(appProperties.tableFont, appProperties.tableFontFontStyle, appProperties.tableFontSize));
        table.setFont(new Font(appProperties.tableFont, appProperties.tableFontFontStyle, appProperties.tableFontSize));
        table.setBackground(appProperties.tableFontBackground);

        JPanel panelBottomComponents = new JPanel();
        panelBottomComponents.setBackground(this.getBackground());
        panelBottomComponents.setLayout(new BorderLayout());
        panelBottomComponents.add(basicInfo, BorderLayout.CENTER);
        panelBottomComponents.add(searchControllerViewPanel(), BorderLayout.SOUTH);

        //======== this ========
        this.setLayout(new BorderLayout());
        this.add(searchComponents, BorderLayout.NORTH);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
        this.add(panelBottomComponents, BorderLayout.SOUTH);
    }

    private JPanel searchControllerViewPanel() {
        JPanel xpanel = new JPanel();
        xpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        xpanel.setBorder(UIManager.getBorder("DesktopIcon.border"));
        xpanel.setBackground(new Color(241, 241, 241));
        xpanel.setPreferredSize(new Dimension(200, 27));

        btnFirst = new JButton();
        btnLast = new JButton();
        btnNext = new JButton();
        btnPrev = new JButton();

        btnFirst.setIcon(SwingUtil.newImageIcon("/com/jimboyz/cims/images/first-icon.png"));
        btnLast.setIcon(SwingUtil.newImageIcon("/com/jimboyz/cims/images/last-icon.png"));
        btnNext.setIcon(SwingUtil.newImageIcon("/com/jimboyz/cims/images/next-icon.png"));
        btnPrev.setIcon(SwingUtil.newImageIcon("/com/jimboyz/cims/images/previous-icon.png"));

        btnFirst.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLast.setCursor(btnFirst.getCursor());
        btnNext.setCursor(btnFirst.getCursor());
        btnPrev.setCursor(btnFirst.getCursor());

        btnFirst.setToolTipText("Go to first");
        btnLast.setToolTipText("Go to last");
        btnNext.setToolTipText("Next");
        btnPrev.setToolTipText("Previous");

        btnFirst.setFocusable(false);
        btnLast.setFocusable(false);
        btnNext.setFocusable(false);
        btnPrev.setFocusable(false);

        JLabel lbl = new JLabel();
        lbl.setPreferredSize(new Dimension(50, 3));

        lblRecords = new JLabel("1 Record(s)");//"1 Record(s)"
        lblRecords.setIconTextGap(5);
        lblRecords.setIcon(SwingUtil.newImageIcon("/com/jimboyz/cims/images/separator.png"));
        lblRecords.setPreferredSize(new Dimension(90, 20));

        lblPages = new JLabel("Page 1 of 1");//"Page 1 of 1"
        lblPages.setIconTextGap(5);
        lblPages.setIcon(SwingUtil.newImageIcon("/com/jimboyz/cims/images/separator.png"));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panel.setBackground(this.getBackground());

        xpanel.add(btnFirst);
        xpanel.add(btnPrev);
        xpanel.add(btnNext);
        xpanel.add(btnLast);

        panel.add(lbl);
        panel.add(lblRecords);
        panel.add(lblPages);

        xpanel.add(panel);

        return xpanel;
    }

    //======== Optional (because other class can do this method ========
    protected void listSelectionListener(ListSelectionListener listener) {
        this.table.getSelectionModel().addListSelectionListener(listener);
    }

    //======== Optional (because other class can do this method ========
    protected void getSelectedRow() {
        this.table.getSelectedRow();
    }

    //======== Required ========
    public JTable getTable() {
        return table;
    }

    public JButton getBtnFirst() {
        return btnFirst;
    }

    public JButton getBtnLast() {
        return btnLast;
    }

    public JButton getBtnNext() {
        return btnNext;
    }

    public JButton getBtnPrev() {
        return btnPrev;
    }

    public ShowBasicInfo getBasicInfoComponents() {
        return basicInfo;
    }

    public SearchPanelComponents getSearchComponents() {
        return searchComponents;
    }

    public JLabel getLblPages() {
        return lblPages;
    }

    public JLabel getLblRecords() {
        return lblRecords;
    }

    private class CustomTableHeader extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            component.setBackground(new Color(238, 238, 238));
            component.setForeground(appProperties.tableHeaderFontColor);
            component.setPreferredSize(new Dimension(27, 29));

            return component;
        }
    }

    private static class CustomCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
           Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
           if(!table.isRowSelected(row)) {
               component.setBackground(row % 2 == 0 ? new Color(236, 240, 243) : new Color(252, 252, 252));
           } else {
               component.setBackground(Color.BLUE);
           }

           return component;
        }
    }
}