package com.jimboyz.cims.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class DefaultTextField extends FocusAdapter {

    private final JTextField textField;

    public DefaultTextField(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void focusLost(FocusEvent e) {
        textField.setBackground(new Color(254, 255, 255));

    }

    @Override
    public void focusGained(FocusEvent e) {
        textField.setBackground(new Color(254, 255, 208));

    }
}

