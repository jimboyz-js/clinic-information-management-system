package com.jimboyz.cims.view;

import com.jimboyz.cims.view.logger.LoggerComponents;
import com.jimboyz.cims.view.toolbar.XToolbarBottomComponents;
import com.jimboyz.cims.view.toolbar.XToolbarTopComponents;
import com.jimboyz.cims.view.tree.TreeComponents;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class DashboardPage extends JPanel {

    private final DataComponents dataComponents;
    private final TreeComponents tree;
    private final LoggerComponents loggerComponentView;
    private final ShowBasicInfo showBasicInfo;
    private final SearchPanelComponents searchComponents;
    private final AddNewPatientsPage addNewPatients;
    private final EditPatientsPage editPatients;
    private final AdminPage adminPage;
    private JTextField selectedView;
    private double divider_size = 0;
    private final JPanel panelContainer;
    private final CardLayout cl;
    private final XToolbarTopComponents toolbarTop;
    private final XToolbarBottomComponents toolbarBottom;

    public static final String DATA_COMPONENTS = "dataComponents";
    public static final String ADD_NEW_PATIENTS = "addNewPatients";
    public static final String EDIT_PATIENTS = "editPatients";
    public static final String SHOW_ADMIN_PAGES = "showAdminPages";

    public DashboardPage() {

        //======== this ========
        this.setLayout(new BorderLayout());

        cl = new CardLayout();

        toolbarTop = new XToolbarTopComponents();
        toolbarBottom = new XToolbarBottomComponents();

        panelContainer = new JPanel();
        panelContainer.setLayout(cl);
        panelContainer.setBackground(this.getBackground());

        dataComponents = new DataComponents();
        addNewPatients = new AddNewPatientsPage();
        adminPage = new AdminPage();
        editPatients = new EditPatientsPage();
        tree = new TreeComponents();
        loggerComponentView = new LoggerComponents();
        showBasicInfo = this.dataComponents.getBasicInfoComponents();
        searchComponents = this.dataComponents.getSearchComponents();

        panelContainer.add(dataComponents, DATA_COMPONENTS);
        panelContainer.add(new JScrollPane(addNewPatients), ADD_NEW_PATIENTS);
        panelContainer.add(new JScrollPane(editPatients), EDIT_PATIENTS);
        panelContainer.add(new JScrollPane(adminPage), SHOW_ADMIN_PAGES);

        this.add(toolbarTop, BorderLayout.NORTH);
        this.add(toolbarBottom, BorderLayout.SOUTH);

        init();
    }

    private void init() {

        //======== Main Container ========
        JSplitPane splitPane = SwingUtil.splitpane(JSplitPane.HORIZONTAL_SPLIT, 300, true);
        splitPane.setResizeWeight(0.5);
        splitPane.setContinuousLayout(true);

        //======== Tree View ========
        JPanel panel = new JPanel();
        panel.setBackground(this.getBackground());
        panel.setLayout(new BorderLayout());

        JSplitPane xSplitPane = SwingUtil.splitpane(JSplitPane.VERTICAL_SPLIT, (Toolkit.getDefaultToolkit().getScreenSize().height), true);
        xSplitPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, new Color(64, 0, 64)));
        xSplitPane.setContinuousLayout(true);
        xSplitPane.setResizeWeight(0.5);
        splitPane.add(panel, JSplitPane.LEFT);
        panel.add(xSplitPane, BorderLayout.CENTER);
        panel.add(textViewComponents(), BorderLayout.SOUTH);
        xSplitPane.add(new JScrollPane(tree), JSplitPane.TOP);

        panel = new JPanel();
        panel.setBackground(this.getBackground());
        panel.setLayout(new BorderLayout());

        xSplitPane.add(panel, JSplitPane.BOTTOM);
        panel.add(expand(xSplitPane), BorderLayout.NORTH);
        panel.add(new JScrollPane(loggerComponentView.errorMessage("sarona")), BorderLayout.CENTER);

        //======== Data Components Container ========
        splitPane.add(panelContainer, JSplitPane.RIGHT);

        this.add(splitPane, BorderLayout.CENTER);
    }

    private JPanel textViewComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel con = new JPanel();
        con.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        con.add(new JLabel(SwingUtil.newImageIcon("/com/jimboyz/cims/images/separator.png")));

        JLabel icon = new JLabel();
        icon.setHorizontalAlignment(SwingConstants.CENTER);
        icon.setIcon(SwingUtil.newImageIcon("/com/jimboyz/cims/images/diamond_view.png"));
        con.add(icon);

        con.add(new JLabel(SwingUtil.newImageIcon("/com/jimboyz/cims/images/separator.png")));
        panel.add(con, BorderLayout.WEST);

        selectedView = SwingUtil.textField("selected-view", new Font("Montserrat", Font.PLAIN, 12), Color.BLACK);
        selectedView.setEditable(false);
        selectedView.setBorder(UIManager.getBorder("DesktopIcon.border"));
        selectedView.setBackground(new Color(254, 255, 208));

        panel.add(selectedView, BorderLayout.CENTER);

        return panel;
    }

    public JPanel expand(JSplitPane splitpane) {

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        panel.setPreferredSize(new Dimension(15, 15));

        JLabel collapseAll = new JLabel(SwingUtil.newImageIcon("/com/jimboyz/cims/images/tree_collapseall.png"));
        collapseAll.setCursor(new Cursor(Cursor.HAND_CURSOR));
        collapseAll.setToolTipText("Collapse");
        collapseAll.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                divider_size = splitpane.getDividerLocation();

                if(divider_size < 500) {
                    splitpane.setDividerLocation(500);
                }else {
                    splitpane.setDividerLocation(Toolkit.getDefaultToolkit().getScreenSize().height);
                }

            }
        });

        JLabel expandAll = new JLabel(SwingUtil.newImageIcon("/com/jimboyz/cims/images/tree_expandall.png"));
        expandAll.setCursor(new Cursor(Cursor.HAND_CURSOR));
        expandAll.setToolTipText("Expand");
        expandAll.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                divider_size = splitpane.getDividerLocation();
                if(divider_size >= 500)
                    splitpane.setDividerLocation(300);
                else
                    splitpane.setDividerLocation(0);
            }
        });

        panel.add(expandAll);
        panel.add(collapseAll);

        return panel;
    }

    public void setSelectedView(String txt) {
        selectedView.setText(txt);
    }

    public String getSelectedView() {
        return selectedView.getText();
    }

    public void setTreeSelectionListener(TreeSelectionListener l) {
        tree.addTreeSelectionListener(l);
    }

    public String getSelectedTreePath() {
        return Objects.requireNonNull(tree.getSelectionPath()).getLastPathComponent().toString();
    }

    public void listSelectionListener(ListSelectionListener listener) {
        dataComponents.listSelectionListener(listener);
    }

    public void setTxtName(String txtName) {
        showBasicInfo.setTxtName(txtName);
    }

    public void setTxtCourse(String txtCourse) {
        this.showBasicInfo.setTxtCourse(txtCourse);
    }

    public void setTxtGender(String txtGender) {
        this.showBasicInfo.setTxtGender(txtGender);
    }

    public void setTxtYearLevel(String txtYearLevel) {
        this.showBasicInfo.setTxtYearLevel(txtYearLevel);
    }

    public void setTxtStatus(String txtStatus) {
        this.showBasicInfo.setTxtStatus(txtStatus);
    }

    public void setTxtBloodType(String txtBloodType) {
        this.showBasicInfo.setTxtBloodType(txtBloodType);
    }

    public void setTxtBirthdate(String txtBirthdate) {
        this.showBasicInfo.setTxtBirthdate(txtBirthdate);
    }

    public void setTxtHeight(String txtHeight) {
        this.showBasicInfo.setTxtHeight(txtHeight);
    }

    public void setTxtAge(String txtAge) {
        this.showBasicInfo.setTxtAge(txtAge);
    }

    public void setTxtWeight(String txtWeight) {
        this.showBasicInfo.setTxtWeight(txtWeight);
    }

    public void setTxtAddress(String txtAddress) {
        this.showBasicInfo.setTxtAddress(txtAddress);
    }

    public void setTxtPulseRate(String txtPulseRate) {
        this.showBasicInfo.setTxtPulseRate(txtPulseRate);
    }

    public void setTxtEmailAdd(String txtEmailAdd) {
        this.showBasicInfo.setTxtEmailAdd(txtEmailAdd);
    }

    public void setTxtBp(String txtBp) {
        this.showBasicInfo.setTxtBp(txtBp);
    }

    public void setStudentImage(byte[] img) {
        this.showBasicInfo.setStudentPhoto(img);
    }

    public void setBtnSearch(ActionListener listener) {
        searchComponents.setBtnSearch(listener);
    }

    public void setFirstnameListener(KeyListener listener) {
        searchComponents.setFirstnameListener(listener);
    }

    public void setLastnameListener(KeyListener listener) {
        searchComponents.setLastnameListener(listener);
    }

    public void setGenderListener(KeyListener listener) {
        searchComponents.setGenderListener(listener);
    }

    public void setIdNumberListener(KeyListener listener) {
        searchComponents.setIdNumberListener(listener);
    }

    public void setYearLevelListener(KeyListener listener) {
        searchComponents.setYearLevelListener(listener);
    }

    public String getTxtFirstname() {
        return searchComponents.getTxtFirstname();
    }

    public String getTxtLastname() {
        return searchComponents.getTxtLastname();
    }

    public String getCmbYearLevelItem() {
        return searchComponents.getCmbYearLevelItem();
    }

    public String getCmbGenderItem() {
        return searchComponents.getCmbGenderItem();
    }

    public String getTxtIdNum() {
        return searchComponents.getTxtIdNum();
    }

    public DataComponents getDataComponents() {
        return dataComponents;
    }

    public JPanel getPanelContainer() {
        return panelContainer;
    }

    public void showContentPane(String constraints) {
        cl.show(panelContainer, constraints);
    }

    public AddNewPatientsPage getAddNewPatients() {
        return addNewPatients;
    }

    public EditPatientsPage getEditPatients() {
        return editPatients;
    }

    public XToolbarTopComponents getToolbarTop() {
        return toolbarTop;
    }

    public XToolbarBottomComponents getToolbarBottom() {
        return toolbarBottom;
    }

    public AdminPage getAdminPage() {
        return adminPage;
    }
}
