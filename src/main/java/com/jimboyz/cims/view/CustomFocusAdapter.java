package com.jimboyz.cims.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CustomFocusAdapter extends FocusAdapter {

    private final JTextField textField;
    private final String placeHolder;
    private final Font font;

    public CustomFocusAdapter(JTextField textField, String placeHolder) {
        font = textField.getFont();
        this.textField = textField;
        this.placeHolder = placeHolder;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(textField.getText().equals(placeHolder)) {
            textField.setText("");
            textField.setFont(new Font(textField.getFont().getName(), Font.PLAIN, textField.getFont().getSize()));
            textField.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(textField.getText().isEmpty()) {
            textField.setText(placeHolder);
            textField.setFont(font);
            textField.setForeground(Color.GRAY);
        }
    }
}
