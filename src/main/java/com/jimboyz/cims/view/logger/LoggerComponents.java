package com.jimboyz.cims.view.logger;

import javax.swing.*;
import java.awt.*;

public class LoggerComponents {//implements RowSelectionListener

    public JTextArea errorMessage(String message) {
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Dialog", Font.PLAIN, 12));
        textArea.setEditable(false);
        textArea.setText(message);
        return textArea;
    }

}
