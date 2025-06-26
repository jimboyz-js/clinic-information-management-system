package com.jimboyz.cims.view;

import com.jimboyz.cims.AppProperties;
import com.jimboyz.cims.err.Message;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminPage extends JPanel {

    private final JTable table;
    private final AppProperties appProperties;
    private final JLabel lblKey;
    private final JLabel lblUpdate;
    private final JLabel lblDelete;
    private final JLabel lblSuspend;
    private final JLabel lblDisable;
    private final JLabel lblRefresh;

    public AdminPage() {

        this.setLayout(new BorderLayout());

        appProperties = new AppProperties();

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
        tableHeader.setDefaultRenderer(new AdminPage.CustomTableHeader());
        table.setDefaultRenderer(Object.class, new AdminPage.CustomCellRenderer());
        ((DefaultTableCellRenderer) tableHeader.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        String[] columnName = {"ID","Firstname", "Lastname", "Username", "Terminal", "Mac Address",  "Admin", "Disable", "Suspended"};
        tableModel.setColumnIdentifiers(columnName);

        tableHeader.setFont(new Font(appProperties.tableFont, appProperties.tableFontFontStyle, appProperties.tableFontSize));
        table.setFont(new Font(appProperties.tableFont, appProperties.tableFontFontStyle, appProperties.tableFontSize));
        table.setBackground(appProperties.tableFontBackground);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 0));
        panel.setPreferredSize(new Dimension(300, 45));

        lblKey = SwingUtil.label("Key", new Font("Times New Roman", Font.PLAIN, 14), Color.BLACK, SwingConstants.CENTER);
        lblKey.setPreferredSize(new Dimension(100, 43));
        lblKey.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblKey.setIcon(SwingUtil.newImageIcon("/com/jimboyz/cims/images/key_icon.png"));

        lblUpdate = SwingUtil.label("Update", lblKey.getFont(), lblKey.getForeground(), SwingConstants.CENTER);
        lblUpdate.setPreferredSize(new Dimension(100, 43));
        lblUpdate.setCursor(lblKey.getCursor());
        lblUpdate.setIcon(SwingUtil.newImageIcon("/com/jimboyz/cims/images/edit.png"));

        lblDelete = SwingUtil.label("Delete", lblKey.getFont(), lblKey.getForeground(), SwingConstants.CENTER);
        lblDelete.setPreferredSize(new Dimension(100, 43));
        lblDelete.setCursor(lblKey.getCursor());
        lblDelete.setIcon(SwingUtil.newImageIcon("/com/jimboyz/cims/images/delete3.png"));

        lblSuspend = SwingUtil.label("Suspend", lblKey.getFont(), lblKey.getForeground(), SwingConstants.CENTER);
        lblSuspend.setPreferredSize(new Dimension(100, 43));
        lblSuspend.setCursor(lblKey.getCursor());
        lblSuspend.setIcon(SwingUtil.newImageIcon("/com/jimboyz/cims/images/suspend_icon.png"));

        lblDisable = SwingUtil.label("Disable", lblKey.getFont(), lblKey.getForeground(), SwingConstants.CENTER);
        lblDisable.setPreferredSize(new Dimension(100, 43));
        lblDisable.setCursor(lblKey.getCursor());
        lblDisable.setToolTipText("Disable Account");
        lblDisable.setIcon(SwingUtil.newImageIcon("/com/jimboyz/cims/images/suspend_icon.png"));

        lblRefresh = SwingUtil.label("Refresh", lblKey.getFont(), lblKey.getForeground(), SwingConstants.CENTER);
        lblRefresh.setPreferredSize(new Dimension(100, 43));
        lblRefresh.setCursor(lblKey.getCursor());
        lblRefresh.setIcon(SwingUtil.newImageIcon("/com/jimboyz/cims/images/refresh_nav.gif"));

        panel.add(lblKey);
        panel.add(lblUpdate);
        panel.add(lblDelete);
        panel.add(lblSuspend);
        panel.add(lblDisable);
        panel.add(lblRefresh);

        this.add(panel, BorderLayout.NORTH);
        this.add(new JScrollPane(table), BorderLayout.CENTER);

    }

//    private void tableFormat() {
//        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
//        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//
//        table.getColumnModel().getColumn(0).setMinWidth(0);
//        table.getColumnModel().getColumn(0).setMaxWidth(0);
//        table.getColumnModel().getColumn(0).setPreferredWidth(0);
//        table.getColumnModel().getColumn(0).setResizable(false);
//        table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
//        table.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);
//        table.getColumnModel().getColumn(6).setPreferredWidth(50);
//        table.getColumnModel().getColumn(7).setPreferredWidth(50);
//        table.getColumnModel().getColumn(8).setCellRenderer(cellRenderer);
//    }

    public void keyMouseAdapter(MouseAdapter e) {
        lblKey.addMouseListener(e);
    }

    public void updateMouseAdapter(MouseAdapter e) {
        lblUpdate.addMouseListener(e);
    }

    public void deleteMouseAdapter(MouseAdapter e) {
        lblDelete.addMouseListener(e);
    }

    public void suspendMouseAdapter(MouseAdapter e) {
        lblSuspend.addMouseListener(e);
    }

    public void disableAcctMouseAdapter(MouseAdapter e) {
        lblDisable.addMouseListener(e);
    }

    public void refreshMouseAdapter(MyAdapter e) {
        lblRefresh.addMouseListener(e);
    }

    public JTable getTable() {
        return table;
    }

    public JLabel getLblKey() {
        return lblKey;
    }

    public JLabel getLblUpdate() {
        return lblUpdate;
    }

    public JLabel getLblDelete() {
        return lblDelete;
    }

    public JLabel getLblSuspend() {
        return lblSuspend;
    }

    public JLabel getLblDisable() {
        return lblDisable;
    }

    public JLabel getLblRefresh() {
        return lblRefresh;
    }

    private class CustomTableHeader extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            component.setBackground(new Color(238, 238, 238));
            component.setForeground(appProperties.tableHeaderFontColor);
            component.setPreferredSize(new Dimension(27, 29));

            return component;
        }
    }

    private static class CustomCellRenderer extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if(!table.isRowSelected(row)) {
                component.setBackground(row % 2 == 0 ? new Color(236, 240, 243) : new Color(252, 252, 252));
            } else {
                component.setBackground(Color.BLUE);
            }

            this.setHorizontalAlignment(SwingConstants.CENTER);

            table.getColumnModel().getColumn(0).setMinWidth(0);
            table.getColumnModel().getColumn(0).setMaxWidth(0);
            table.getColumnModel().getColumn(0).setPreferredWidth(0);
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(6).setPreferredWidth(50);
            table.getColumnModel().getColumn(7).setPreferredWidth(50);

            return component;
        }
    }

    public static class MyAdapter extends MouseAdapter {
        private final JLabel label;

        public MyAdapter(JLabel label) {
            this.label = label;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            label.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            label.setBorder(null);
        }

    }
}

