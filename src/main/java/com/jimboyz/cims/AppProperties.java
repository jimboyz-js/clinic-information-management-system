package com.jimboyz.cims;

import java.awt.*;
import java.io.File;

public class AppProperties {

    // JTree
    public boolean isRootHandleShow = false;

    // Toolbar
    public boolean isFloatable = true;
    public boolean isRollOver = false;

    // JTable
    public boolean isReOrderingAllowed = false;
    public boolean isResizable = false;
    public String tableFont = "Times New Roman";
    public int tableFontFontStyle = Font.PLAIN;
    public int tableFontSize = 17;
    public Color tableFontForeground = Color.BLACK;
    public Color tableFontBackground = null;

    // JTableHeader
    public String tableHeaderFont = "Times New Roman";
    public int tableHeaderFontStyle = Font.PLAIN;
    public int tableHeaderFontSize = 17;
    public Color tableHeaderFontColor = Color.BLACK;

    // JDateChooser
    public String dateFormatString = "MM/dd/yyyy";
    public String dateChooserFont = "Dialog";
    public int dateChooserFontStyle = Font.PLAIN;
    public int dateChooserFontSize = 13;

    // Camera Webcam
    public String timeFormatString = "HH:mm:ss";
    public String imageFormat = "JPG";
    public String capturedImageLocation = HOME_DIR + File.separator + "Pictures";

    //File
    public static final String HOME_DIR = System.getProperty("user.home");
    public static final String USER_DIR = System.getProperty("user.dir");
    public static final String CIMS_DIR = HOME_DIR + File.separator + ".cims-v2";
    public static final String CAPTURED_IMG_FILE = "com.jimboyz.webcam.util.properties";

    // Reset table when no record found upon searching
    public boolean resetTable = false;
    // Show message dialog when no record found.
    public boolean isShowMessageDialog = true;

    // Session MassIndexer
    // If set to true then execute this method searchSession.massIndexer().startAndWait();
    public boolean isSessionMassIndexerStartAndWait = false;

    // Add (Medical Page) Properties
    public String addMedicalPageFontFamily = "Times New Roman";
    public int addMedicalPageFontStyle = Font.PLAIN;
    public int addMedicalPageFontSize = 19;
    public Color addMedicalPageFontColor = Color.BLACK;
    public String timeMedFormatString = "hh:mm:ss a";
    public boolean clearFieldUponClosingDialog = true;


}
