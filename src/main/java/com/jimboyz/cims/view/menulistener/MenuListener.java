package com.jimboyz.cims.view.menulistener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuListener {

    private ActionListener actionListener;

    public Action menuListener() {
        return new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                actionListener.actionPerformed(e);
            }
        };
    }

    public Action exit() {
        return new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

//    public void setGenerateMenuListener(ActionListener actionListener) {
//        this.actionListener = actionListener;
//    }
}
