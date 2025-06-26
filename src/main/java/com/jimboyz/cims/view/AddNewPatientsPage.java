package com.jimboyz.cims.view;

import javax.swing.*;
import java.awt.*;

/*
 * Created by Jimboy M. Sarona on Thu. Feb. 20 12:22 PM PST 2025
 * Modified and backup by Jimboy M. Sarona on Thu. March 20, 2025 5:33 PM
 */
/**
 * @author jimboy Ni ChOy!!!
 */

public class AddNewPatientsPage extends JPanel {

    private final JPanel panelInfoPage;

    
    public AddNewPatientsPage() {

        this.setLayout(new BorderLayout());
        panelInfoPage = new SetPatientsInfoPage();

        this.add(panelInfoPage, BorderLayout.CENTER);


    }

    public SetPatientsInfoPage getPanelInfoPage() {
        return (SetPatientsInfoPage) panelInfoPage;
    }
}