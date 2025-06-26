package com.jimboyz.cims.view.toolbar;

import com.jimboyz.cims.view.SwingUtil;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class XToolbarBottomComponents extends JToolBar {

	private String mode;

    public XToolbarBottomComponents() {

		this.setOrientation(JToolBar.HORIZONTAL);
		this.setFloatable(false);
		this.setRollover(false);
		this.setPreferredSize(new Dimension(22, 25));
		this.setMargin(new Insets(0, 20, 0, 20));
		this.setBorder(UIManager.getBorder("DesktopIcon.border"));
		this.add(new JToolBar.Separator());
	}

//	private String getUsername() {/////////////////////
//		return System.getProperty("system.username") != null ? System.getProperty("system.username") : "user";
//	}

	public void setToolbarComponents(String username, String terminal, String mode) {
		this.add(component("USER: ", username.toUpperCase() ,"/com/jimboyz/cims/images/user.png", 300, 400));//getUsername().toUpperCase(),
		this.add(component("TERMINAL: ", terminal.toUpperCase(),"/com/jimboyz/cims/images/terminal_id.png", 400, 777));
		this.add(component("MODE: ", mode.toUpperCase(),"/com/jimboyz/cims/images/offline_mode.png", 500, 0));//
	}

	private JComponent component(String value, String name, String iconPath, int prefWidth, int maxWidth) {
		JComponent panel = new JComponent() {
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(prefWidth, XToolbarBottomComponents.this.getPreferredSize().height);
			}
		};

		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		if(maxWidth > 0) panel.setMaximumSize(new Dimension(maxWidth, this.getPreferredSize().height));

		panel.setBorder(UIManager.getBorder("TitledBorder.border"));

        JLabel lblIco = new JLabel(value);
		lblIco.setIcon(SwingUtil.newImageIcon(iconPath));
		lblIco.setFont(new Font("Times New Roman",Font.BOLD, 12));
		lblIco.setHorizontalAlignment(SwingConstants.LEFT);
		lblIco.setIconTextGap(3);
		panel.add(lblIco);

		JLabel lblName = new JLabel(name);
		lblName.setFont(new Font("Times New Roman",Font.PLAIN, 12));
		panel.add(lblName);

		return panel;
	}

	
	private ImageIcon ico(String path) {
		
		Properties properties = new Properties();
//		try(FileInputStream fis = new FileInputStream(GUIProperties.MAIN_FOLDER+GUIProperties.FILE_SEPARATOR+GUIProperties.APP_DETAILS_VERSION+GUIProperties.FILE_SEPARATOR+GUIProperties.DB_NAME_PROPERTIES)){
		try(FileInputStream fis = new FileInputStream("")){
			properties.load(fis);
			String host = properties.getProperty("host");
			if(host.toLowerCase().contains("localhost") || host.toLowerCase().contains("127.0.0.1")) {
				setMode("Offline");
				return SwingUtil.newImageIcon(path);
			}
			
		}catch(IOException ignored) {
			
		}
		setMode("Online");
		return SwingUtil.newImageIcon("/com/jimboyz/cims/images/online_mode.png");
		
	}
	
	private void setMode(String mode) {
		this.mode = mode;
	}
	
	private String getMode() {
		return mode;
	}
}
