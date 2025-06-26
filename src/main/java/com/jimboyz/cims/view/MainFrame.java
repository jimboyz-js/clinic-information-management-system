package com.jimboyz.cims.view;

import com.jimboyz.cims.err.Message;
import com.jimboyz.cims.view.menulistener.MenuListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

//  File Menu
    private Action createAccount;
    private Action changePassword;
    private Action home;
    private Action suspend;
    private Action logout;

//  System Menu
    private Action systemActionMenu;

//  Generate Menu
    private Action generateKey;

    private int menuMask;

    private JPanel container;
    private CardLayout cl;
    private String constraints;
    private MenuListener menuListener;

    public static String FRAME_TITLE = "Clinic Information System v2.0 - ";

    public MainFrame() {
        initComponent();
    }

    private void initComponent() {
        //======== this ========
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(new Dimension((Toolkit.getDefaultToolkit().getScreenSize().width - 17), Toolkit.getDefaultToolkit().getScreenSize().height - 50));
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(new BorderLayout());
        this.setResizable(true);
        this.setTitle(FRAME_TITLE);

        cl = new CardLayout();
        container = new JPanel();
        container.setBackground(this.getBackground());
        container.setLayout(cl);
        Message.containerWindow(container);
        menuMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();

        menuListener = new MenuListener();

        JMenuBar menuBar = new JMenuBar();
        menuBar.setOpaque(false);
        menuBar.add(this.fileMenu());
        menuBar.add(this.systemMenu());
        menuBar.add(this.generateMenu()).setEnabled(false);

        this.setJMenuBar(menuBar);
        this.add(container, BorderLayout.CENTER);
    }

    private JMenu fileMenu() {

        createAccount = SwingUtil.newAction("Create Account", SwingUtil.newImageIcon("/com/jimboyz/cims/images/create-account.png"), true, "Create Account", menuListener.menuListener());//showAcct()showCreateAccountPage()
        changePassword = SwingUtil.newAction("Change Password", SwingUtil.newImageIcon("/com/jimboyz/cims/images/change-password.png"), false, "Change Password", menuListener.menuListener());//showChangePass()showChangePasswordPage()
        home = SwingUtil.newAction("Home", null, false, menuListener.menuListener());//xmenu.setHomeMenuAction(this)
        suspend = SwingUtil.newAction("Suspend", SwingUtil.newImageIcon("/com/jimboyz/cims/images/suspend_icon.png"), false, menuListener.menuListener());
        logout = SwingUtil.newAction("Logout", SwingUtil.newImageIcon("/com/jimboyz/cims/images/logout.png"), false, menuListener.menuListener());//xmenu.setLogoutMenu(this)
        Action exit = SwingUtil.newAction("Exit", SwingUtil.newImageIcon("/com/jimboyz/cims/images/delete_icon.gif"), true, menuListener.exit());//

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fileMenu.add(createAccount).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, menuMask));
        fileMenu.addSeparator();
        fileMenu.add(changePassword).setMnemonic('C');
        fileMenu.addSeparator();
        fileMenu.add(home).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, menuMask));
        fileMenu.addSeparator();
        fileMenu.add(suspend).setAccelerator(KeyStroke.getKeyStroke("control shift J"));
        fileMenu.addSeparator();
        fileMenu.add(logout).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, menuMask));
        fileMenu.add(exit).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));

        return fileMenu;
    }

    public JMenu systemMenu() {
        systemActionMenu = SwingUtil.newAction("Administrator", SwingUtil.newImageIcon("/com/jimboyz/cims/images/create-account.png"), false, null, menuListener.menuListener());
        JMenu systemMenu = new JMenu("System");
        systemMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        systemMenu.add(systemActionMenu).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, menuMask));

        return systemMenu;
    }

    public JMenu generateMenu() {
        generateKey = SwingUtil.newAction("Generate Key", SwingUtil.newImageIcon("/com/jimboyz/cims/images/create-account.png"), true, "Generate Terminal Key", menuListener.menuListener());
        JMenu generateMenu = new JMenu("Generate");
        generateMenu.setMnemonic(KeyEvent.VK_G);
        generateMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        generateMenu.add(generateKey).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, menuMask));

//        generateMenu.addSeparator();

        return generateMenu;
    }

    public void windowListener(WindowListener e) {
        this.addWindowListener(e);
    }

    public JPanel getFrameContainer() {
        return container;
    }

    public void showContentPane(String constraints) {
        this.constraints = constraints;
        cl.show(container, constraints);
    }

//    public void setContainerConstraints(String constraints) {
//        this.constraints = constraints;
//    }

    public String getContainerConstraints() {
        return constraints;
    }

    public Action getChangePasswordAction() {
        return changePassword;
    }

    public MenuListener getMenuListener() {
        return menuListener;
    }

    public Action getHomeMenuAction() {
        return home;
    }

    public Action getSuspendMenuAction() {
        return suspend;
    }

    public Action getLogoutMenuAction() {
        return logout;
    }

    public Action getSystemActionMenu() {
        return systemActionMenu;
    }

    public void setEditTitle(String title) {
        super.setTitle(this.getTitle().replaceFirst("-.*", "").concat("- ") + title);
    }
}
