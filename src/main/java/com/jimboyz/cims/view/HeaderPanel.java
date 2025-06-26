package com.jimboyz.cims.view;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {

    public HeaderPanel() {
        this.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 50));
        this.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        JLabel lblLogo = new JLabel(SwingUtil.newImageIcon(SwingUtil.getImage("/com/jimboyz/cims/images/js-software-itsupport.png"), 250, 50, Image.SCALE_SMOOTH));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblLogo);
    }
}
