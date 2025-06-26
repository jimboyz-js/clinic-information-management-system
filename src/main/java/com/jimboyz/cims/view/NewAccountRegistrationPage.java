package com.jimboyz.cims.view;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

/*
 * Created by Jimboy M. Sarona on Thu. Feb. 20 04:13 PM PST 2025
 * @author jimboy Ni ChOy!!!
 */

public class NewAccountRegistrationPage extends JPanel {

	private JTextField txtFirstname;
	private JTextField txtLastname;
	private JTextField txtUsername;
	private JTextField txtTerminalKey;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmPass;

	private JButton btnBack;
	private JButton btnCreate;

    public NewAccountRegistrationPage() {
		initComponents();
	}

	private void initComponents() {

        JLabel lblNewAccountReg = SwingUtil.label("New Account Registration", new Font("Segoe UI", Font.PLAIN, 27), Color.BLACK);
        JLabel lblAssistance = SwingUtil.label("<html>Please fill in the information below. Contact helpdesk for assistance.</html>", new Font("Segoe UI", Font.PLAIN, 12), Color.BLACK);
        JLabel lblFirstname = SwingUtil.label("<html>Firstname:" + UtilitiesClass.required() + "</html>", new Font("Segoe UI", Font.PLAIN, 12), Color.BLACK);
		txtFirstname = SwingUtil.textField("fname", lblFirstname.getFont(), lblFirstname.getForeground());
        JLabel lblLastname = SwingUtil.label("<html>Lastname:" + UtilitiesClass.required() + "</html>", lblFirstname.getFont(), lblFirstname.getForeground());
		txtLastname = SwingUtil.textField("lname", lblFirstname.getFont(), lblFirstname.getForeground());
        JLabel lblUsername = SwingUtil.label("<html>Username:" + UtilitiesClass.required() + "</html>", lblFirstname.getFont(), lblFirstname.getForeground());
		txtUsername = SwingUtil.textField("uname", lblFirstname.getFont(), lblFirstname.getForeground());
        JLabel lblTerminalKey = SwingUtil.label("<html>Terminal Key:" + UtilitiesClass.required() + "</html>", lblFirstname.getFont(), lblFirstname.getForeground());
		txtTerminalKey = SwingUtil.textField("terminal-key", lblFirstname.getFont(), lblFirstname.getForeground());
        JLabel lblPassword = SwingUtil.label("<html>Password:" + UtilitiesClass.required() + "</html>", lblFirstname.getFont(), lblFirstname.getForeground());
		txtPassword = SwingUtil.passwordField("password", "password", lblFirstname.getFont(), lblFirstname.getForeground());
        JLabel lblConfPass = SwingUtil.label("<html>Confirm Pass:" + UtilitiesClass.required() + "</html>", lblFirstname.getFont(), lblFirstname.getForeground());
		txtConfirmPass = SwingUtil.passwordField("confirm-password", "confirm-password", lblFirstname.getFont(), lblFirstname.getForeground());

		//======== this ========
		this.setLayout(new BorderLayout());

		//======== panel ========
        JPanel panel = new JPanel();
		panel.setLayout(new MigLayout(
			"hidemode 3",
							// columns
							"[fill]" +
							"[fill]",
							// rows
							"[]" +
							"[]" +
							"[]" +
							"[]" +
							"[]" +
							"[]" +
							"[]" +
							"[]" +
							"[]"));

		//---- lblNewAccountReg ----
		panel.add(lblNewAccountReg, "cell 0 0 2 1,gapx 70,gapy 70 30");

		//---- lblAssistance ----
		panel.add(lblAssistance, "cell 0 1 2 1, gapx 70, gapy null 20");

		//---- lblFirstname ----
		panel.add(lblFirstname, "cell 0 2, gapx 70");
		panel.add(txtFirstname, "cell 1 2, height 25");

		//---- lblLastname ----
		panel.add(lblLastname, "cell 0 3, gapx 70");
		panel.add(txtLastname, "cell 1 3, height 25");

		//---- lblUsername ----
		panel.add(lblUsername, "cell 0 4, gapx 70");
		panel.add(txtUsername, "cell 1 4, height 25");

		//---- lblTerminalKey ----
		panel.add(lblTerminalKey, "cell 0 5, gapx 70");
		panel.add(txtTerminalKey, "cell 1 5, height 25");

		//---- lblPassword ----
		panel.add(lblPassword, "cell 0 6, gapx 70");
		panel.add(txtPassword, "cell 1 6, height 25");

		//---- lblConfPass ----
		panel.add(lblConfPass, "cell 0 7, gapx 70");
		panel.add(txtConfirmPass, "cell 1 7, height 25");

		panel.add(panelButtonComponent(), "cell 0 8 8 1, gapx 70, height 50");

		//======== panelContLogo ========
		this.add(new HeaderPanel(), BorderLayout.NORTH);

		this.add(panel, BorderLayout.CENTER);
	}

	private JPanel panelButtonComponent() {
		JPanel panelButtonComponent = new JPanel();
		panelButtonComponent.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 10));

		btnCreate = SwingUtil.button("Create", "create", "create-acct", new Font("Tahoma", Font.PLAIN, 11), Color.BLACK, new Cursor(Cursor.HAND_CURSOR));
		btnBack = SwingUtil.button("<Back", "back", "back", btnCreate.getFont(), btnCreate.getForeground(), btnCreate.getCursor());

		btnCreate.setMnemonic('c');
		btnBack.setMnemonic('b');

		panelButtonComponent.add(btnBack);
		panelButtonComponent.add(btnCreate);

		return panelButtonComponent;

	}

	public void createAccountListener(ActionListener listener) {
		btnCreate.addActionListener(listener);
	}

	public void createAccountListener(KeyAdapter keyAdapter) {
		txtFirstname.addKeyListener(keyAdapter);
		txtLastname.addKeyListener(keyAdapter);
		txtUsername.addKeyListener(keyAdapter);
		txtTerminalKey.addKeyListener(keyAdapter);
		txtPassword.addKeyListener(keyAdapter);
		txtConfirmPass.addKeyListener(keyAdapter);
	}

	public void backListener(ActionListener listener) {
		btnBack.addActionListener(listener);
	}

	public String getFirstname() {
		return txtFirstname.getText();
	}

	public String getLastname() {
		return txtLastname.getText();
	}

	public String getUsername() {
		return txtUsername.getText();
	}

	public String getPassword() {
		return new String(txtPassword.getPassword());
	}

	public String getConfirmPass() {
		return new String(txtConfirmPass.getPassword());
	}

	public String getTerminalKey() {
		return txtTerminalKey.getText();
	}

	public void clearField() {
		txtFirstname.setText("");
		txtLastname.setText("");
		txtUsername.setText("");
		txtPassword.setText("");
		txtConfirmPass.setText("");
		txtTerminalKey.setText("");
	}
}
