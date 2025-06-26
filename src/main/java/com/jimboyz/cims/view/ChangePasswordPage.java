package com.jimboyz.cims.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class ChangePasswordPage extends JPanel {

    private final JPasswordField oldPassword;
    private final JPasswordField newPassword;
    private final JPasswordField confirmPassword;
    private JButton changePasswordButton;
    private JButton backButton;
    private final JLabel description;

    public ChangePasswordPage() {

        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("hidemode 3", "[fill][fill]", "[][][][][][]"));

        oldPassword = SwingUtil.passwordField("old password", "old-password", new Font("Segoe UI", Font.PLAIN, 12), Color.BLACK);
        newPassword = SwingUtil.passwordField("new password", "new-password", oldPassword.getFont(), oldPassword.getForeground());
        confirmPassword = SwingUtil.passwordField("confirm password", "confirm-password", newPassword.getFont(), newPassword.getForeground());
        description = SwingUtil.label("<html>Please fill in the information below. Contact helpdesk for assistance.</html>", oldPassword.getFont(), oldPassword.getForeground());

        panel.add(SwingUtil.label("<html>Change Password</html>", new Font("Segoe UI", Font.PLAIN, 27), Color.BLACK), "cell 0 0 2 1, gapx 70, gapy 70 30");
        panel.add(description, "cell 0 1 2 1, gapx 70, gapy null 20");
        panel.add(SwingUtil.label("<html>Old Password:" + UtilitiesClass.required() + "</html>", oldPassword.getFont(), oldPassword.getForeground()), "cell 0 2, gapx 70");
        panel.add(SwingUtil.label("<html>New Password:" + UtilitiesClass.required() + "</html>", oldPassword.getFont(), oldPassword.getForeground()), "cell 0 3, gapx 70");
        panel.add(SwingUtil.label("<html>Confirm Password:" + UtilitiesClass.required() + "</html>", oldPassword.getFont(), oldPassword.getForeground()), "cell 0 4, gapx 70");
        panel.add(oldPassword, "cell 1 2, height 25");
        panel.add(newPassword, "cell 1 3, height 25");
        panel.add(confirmPassword, "cell 1 4, height 25");
        panel.add(panelButtonComponent(), "cell 0 5 5 1, gapx 70, height 50");

        this.add(new HeaderPanel(), BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);
    }

    private JPanel panelButtonComponent() {
        JPanel panelButtonComponent = new JPanel();
        panelButtonComponent.setBackground(this.getBackground());
        panelButtonComponent.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 10));

        changePasswordButton = SwingUtil.button("Change", "change", "change-pass", new Font("Tahoma", Font.PLAIN, 11), Color.BLACK, new Cursor(Cursor.HAND_CURSOR));
        backButton = SwingUtil.button("<Back", "back", "back", changePasswordButton.getFont(), changePasswordButton.getForeground(), changePasswordButton.getCursor());

        changePasswordButton.setMnemonic('c');
        backButton.setMnemonic('b');

        panelButtonComponent.add(backButton);
        panelButtonComponent.add(changePasswordButton);

        return panelButtonComponent;

    }

    public String getOldPassword() {
        return new String(oldPassword.getPassword());
    }

    public String getNewPassword() {
        return new String(newPassword.getPassword());
    }

    public String getConfirmPassword() {
        return new String(confirmPassword.getPassword());
    }

    public void backListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public void changePasswordListener(ActionListener listener) {
        changePasswordButton.addActionListener(listener);
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void changePasswordListener(KeyAdapter keyAdapter) {
        oldPassword.addKeyListener(keyAdapter);
        newPassword.addKeyListener(keyAdapter);
        confirmPassword.addKeyListener(keyAdapter);
    }

    public void clearField() {
        oldPassword.setText("");
        newPassword.setText("");
        confirmPassword.setText("");
    }
}
