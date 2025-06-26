package com.jimboyz.cims.view;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class SwingUtil {

    public static Image getImage(String icoPath) {
        return Toolkit.getDefaultToolkit().getImage(SwingUtil.class.getResource(icoPath));
    }

    public static ImageIcon newImageIcon(String iconPath) {
        return new ImageIcon(getImage(iconPath));
    }

    public static ImageIcon newImageIcon(Image img_, int width, int height, int scale) {
        ImageIcon imageIcon = new ImageIcon(img_);
        Image image = imageIcon.getImage();
        Image img = image.getScaledInstance(width, height, scale);
        return new ImageIcon(img);
    }

    public static ImageIcon newImageIcon(byte[] img_, int width, int height, int scale) {
        ImageIcon imageIcon = new ImageIcon(img_);
        Image image = imageIcon.getImage();
        Image img = image.getScaledInstance(width, height, scale);
        return new ImageIcon(img);
    }

    public static ImageIcon newImageIcon(String img_, int width, int height, int scale) {
        ImageIcon imageIcon = new ImageIcon(img_);
        Image image = imageIcon.getImage();
        Image img = image.getScaledInstance(width, height, scale);
        return new ImageIcon(img);
    }

    public static Action newAction(String actionName, boolean isEnabled, final ActionListener actionListener) {

        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionListener.actionPerformed(e);
            }
        };
        action.putValue(Action.NAME, actionName);
        action.setEnabled(isEnabled);

        return action;
    }

    public static Action newAction(String actionName, ImageIcon icon, boolean isEnabled, final ActionListener actionListener) {
        Action action = newAction(actionName, isEnabled, actionListener);
        action.putValue(Action.SMALL_ICON, icon);
        return action;
    }

    public static Action newAction(String actionName, ImageIcon icon, boolean isEnabled, String description, final ActionListener actionListener) {
        Action action = newAction(actionName, icon, isEnabled, actionListener);
        action.putValue(Action.SHORT_DESCRIPTION, description);
        return action;
    }

    @Deprecated
    public static JLabel label(String name, Font font, Color fontColor, int x, int y, int width, int height) {
        JLabel label = new JLabel();
        label.setText(name);
        label.setBounds(x, y, width, height);
        label.setFont(font);
        label.setForeground(fontColor);

        return label;
    }

    public static JLabel label(String name, Font font, Color fontColor) {
        JLabel label = new JLabel();
        label.setText(name);
        label.setFont(font);
        label.setForeground(fontColor);

        return label;
    }

    public static JLabel label(String name, Font font, Color fontColor, int textOrientation) {
        JLabel label = label(name, font, fontColor);
        label.setHorizontalAlignment(textOrientation);

        return label;
    }

    public static JSplitPane splitpane(int orientation, int dividerLocation, boolean oneTouchExpandable) {
        JSplitPane splitpane = new JSplitPane(orientation);
        splitpane.setDividerLocation(dividerLocation);
        splitpane.setOneTouchExpandable(oneTouchExpandable);
        return splitpane;
    }

    @Deprecated
    public static JTextField textField(String actionCommand, Font font, Color fontColor, int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setActionCommand(actionCommand);
        textField.setFont(font);
        textField.setForeground(fontColor);

        textField.addFocusListener(new DefaultTextField(textField));

        return textField;
    }

    public static JTextField textField(String actionCommand, Font font, Color fontColor) {
        JTextField textField = new JTextField();
        textField.setActionCommand(actionCommand);
        textField.setFont(font);
        textField.setForeground(fontColor);

        textField.addFocusListener(new DefaultTextField(textField));

        return textField;
    }

    public static JPasswordField passwordField(String name, String actionCommand, Font font, Color fontColor) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setActionCommand(actionCommand);
        passwordField.setFont(font);
        passwordField.setName(name);
        passwordField.setForeground(fontColor);
        passwordField.addFocusListener(new DefaultTextField(passwordField));
        return passwordField;
    }

    public static JButton button(String title, String name, String actionCommand, Font font, Color fontColor, Cursor cursor) {
        JButton button = new JButton(title);
        button.setActionCommand(actionCommand);
        button.setFont(font);
        button.setName(name);
        button.setForeground(fontColor);
        button.setCursor(cursor);

        return button;
    }

    public static JButton button(String title, String name, String actionCommand, Font font, Color fontColor, int cursorType) {
        return button(title, name, actionCommand, font, fontColor, new Cursor(cursorType));
    }

    public static JComboBox<String> comboBox(String[] item, Font font, boolean editable){
        JComboBox<String> comboBox = new JComboBox<>(item);
        comboBox.setFont(font);
        comboBox.setEditable(editable);
        comboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return comboBox;
    }

//    public static JCheckBox checkBox(String name, Cursor cursor) {
//        JCheckBox checkBox = new JCheckBox();
//        checkBox.setText(name);
//        checkBox.setCursor(cursor);
//
//        return checkBox;
//    }

    public static JDateChooser dateChooser(Date date, Font font, String dateFormatString) {
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDate(date);
        dateChooser.setFont(font);
        dateChooser.setDateFormatString(dateFormatString);
        dateChooser.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return dateChooser;
    }
}
