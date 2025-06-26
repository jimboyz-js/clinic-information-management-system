package com.jimboyz.cims.view;

import com.jimboyz.cims.err.ErrorDialog;

import javax.swing.*;

public class UtilitiesClass {

    public static String required() {

        return "<span color=red> *</span>";
    }

    public static void setLaf() {

        try {
            for(UIManager.LookAndFeelInfo lafInfo : UIManager.getInstalledLookAndFeels()) {
                if("Windows".equals(lafInfo.getName())) {

                    UIManager.setLookAndFeel(lafInfo.getClassName());
                    break;
                }

            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                 | UnsupportedLookAndFeelException e) {

            ErrorDialog.show(e);
        }

    }
}