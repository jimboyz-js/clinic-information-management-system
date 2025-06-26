package com.jimboyz.cims.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.*;

import net.miginfocom.swing.*;

/*
 * Created by jimBoYz Ni ChOy on Mon Feb 17 13:16:12 PST 2025
 */

/**
 * @author jimboy Ni ChOy!!!
 */

public class LoginPage extends JPanel {

	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnLogin;

	public LoginPage() {
		initComponents();
	}

	private void initComponents() {

        JPanel panelContainer = new JPanel();
		JLabel lblLoginDesc = SwingUtil.label("LOGIN", new Font("Segoe UI", Font.PLAIN, 27), Color.BLACK);
		JLabel lblUsername = SwingUtil.label("<html>Username:" + UtilitiesClass.required() + "</html>", new Font(Font.DIALOG, Font.PLAIN, 12), Color.BLACK);
		JLabel lblPassword = SwingUtil.label("<html>Password:" + UtilitiesClass.required() + "</html>", new Font(Font.DIALOG, Font.PLAIN, 12), Color.BLACK);
		txtUsername = SwingUtil.textField("username", lblUsername.getFont(), Color.BLACK);
		txtPassword = SwingUtil.passwordField("pass","password", lblUsername.getFont(), Color.BLACK);
		btnLogin = SwingUtil.button("Login", "login", "login", lblUsername.getFont(), lblUsername.getForeground(), new Cursor(Cursor.HAND_CURSOR));

		//======== this ========
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(238, 238, 238));

		//======== panelContainer ========
		{
			panelContainer.setLayout(new MigLayout(
				"hidemode 3",
				// columns
				"[fill]" +
				"[fill]" +
				"[fill]" +
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
				"[]"));

			//---- lblLoginDesc ----
			panelContainer.add(lblLoginDesc, "cell 1 1,gapx 70,gapy 50");

			//---- infoDesc ----
			JLabel infoDesc = new JLabel("<html>Please fill in the information below. Create account if you do not have one.</html>");
			infoDesc.setHorizontalAlignment(SwingConstants.CENTER);
			panelContainer.add(infoDesc, "cell 1 3 4 1,gapx 70, gapy 17 15");

			//---- lblUsername ----
			panelContainer.add(lblUsername, "cell 1 5,gapx 70");
			panelContainer.add(txtUsername, "cell 2 5 3 1");

			//---- lblPassword ----
			panelContainer.add(lblPassword, "cell 1 6,gapx 70");
			panelContainer.add(txtPassword, "cell 2 6 3 1");

			//---- btnLogin ----
			panelContainer.add(btnLogin, "cell 4 7,wmax 63,gapx 70");

			txtUsername.setText("jimboyz");
			txtPassword.setText("sarona");
		}
		add(panelContainer, BorderLayout.CENTER);

		//======== panelContLogo ========
		add(new HeaderPanel(), BorderLayout.NORTH);
	}

	public void signIn(ActionListener e) {
		btnLogin.addActionListener(e);
	}

	public void signIn(KeyListener e) {
		txtUsername.addKeyListener(e);
		txtPassword.addKeyListener(e);
	}

	public String getUsername() {
		return txtUsername.getText();
	}

	public String getPassword() {
		return new String(txtPassword.getPassword());
	}
}
