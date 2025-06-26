package com.jimboyz.cims.view;

import javax.swing.*;
import java.awt.event.MouseAdapter;

public class HoverOption extends MouseAdapter {

    private final JComponent component;

    public HoverOption(JComponent component) {
        this.component = component;
    }

    public JComponent getComponent() {
        return component;
    }

    public void setMouseAdapter(MouseAdapter mouseAdapter) {
        component.addMouseListener(mouseAdapter);
    }

    public void removeMouseAdapter() {
        component.removeMouseListener(this);
    }
}
