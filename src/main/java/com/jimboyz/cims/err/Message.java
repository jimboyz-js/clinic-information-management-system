package com.jimboyz.cims.err;

import javax.swing.*;
import java.awt.*;

public class Message {

    private static Container container;

    public static void displaySuccessMessage(String msg) {

        JOptionPane.showMessageDialog(container != null ? container.getParent() : null, msg);
    }

    public static void displayMessage(String msg) {
        JOptionPane.showMessageDialog(container != null ? container.getParent() : null, msg);
    }

    public static void showErr(String msg) {
        JOptionPane.showMessageDialog(container != null ? container.getParent() : null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void containerWindow(Container container) {
        Message.container = container;
    }

}
