package com.jimboyz.cims.view.toolbar;

import javax.swing.*;
import java.awt.*;

public class ToolButton extends JButton {

    public ToolButton(XToolbarTopComponents toolbar, Icon icon, String actionCommand) {
        super(icon);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setMargin(new Insets(3, 3, 3, 3));
        this.setPreferredSize(new Dimension(25, 25));
        this.setActionCommand(actionCommand);
        this.setBorderPainted(true);
        this.setFocusable(false);
        this.setFocusPainted(true);
    }
}
