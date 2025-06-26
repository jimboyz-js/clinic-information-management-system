package com.jimboyz.cims.view;

import com.jimboyz.cims.AppProperties;
import com.jimboyz.cims.MySimpleDateFormat;
import com.toedter.calendar.JDateChooser;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Date;

public class SuspendedUserView extends JDialog {

    private final JDateChooser fromSuspendedDate;
    private final JDateChooser toSuspendedDate;
    private final JButton okButton;
    private final JButton closeButton;

    public SuspendedUserView(Frame frame) {
        super(frame, "Suspend User", true);

        AppProperties appProperties = new AppProperties();

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new MigLayout());
        this.setSize(400, 300);
        this.setLocationRelativeTo(frame);

        fromSuspendedDate = SwingUtil.dateChooser(new Date(), new Font(appProperties.dateChooserFont, appProperties.dateChooserFontStyle, appProperties.dateChooserFontSize), appProperties.dateFormatString);
        toSuspendedDate = SwingUtil.dateChooser(new Date(), new Font(appProperties.dateChooserFont, appProperties.dateChooserFontStyle, appProperties.dateChooserFontSize), appProperties.dateFormatString);
        okButton = SwingUtil.button("Ok", "ok", "ok-btn", new Font("Dialog", Font.PLAIN, 12), Color.BLACK, Cursor.HAND_CURSOR);
        closeButton = SwingUtil.button("Close", "close", "close-btn", new Font("Dialog", Font.PLAIN, 12), Color.BLACK, Cursor.HAND_CURSOR);
        fromSuspendedDate.setPreferredSize(new Dimension(200, 10));
        toSuspendedDate.setPreferredSize(new Dimension(200, 10));

        this.add(SwingUtil.label("From:", new Font("Consolas", Font.PLAIN, 14), Color.BLACK), "cell 0 0, wmax 50");
        this.add(fromSuspendedDate, "cell 1 0, wmax 500, growx, pushx");
        this.add(SwingUtil.label("To:", new Font("Consolas", Font.PLAIN, 14), Color.BLACK), "cell 0 1, wmax 50");
        this.add(toSuspendedDate, "cell 1 1, wmax 500, growx, pushx");
        this.add(okButton, "cell 1 2, align right");
        this.add(closeButton, "cell 1 2, align left");

        this.pack();

    }

    public JDateChooser getDateChooserFromSuspendedDate() {
        return fromSuspendedDate;
    }

    public Date getFromSuspendedDate() {
        return fromSuspendedDate.getDate();
    }

    public Date getToSuspendedDate() {
        return toSuspendedDate.getDate();
    }

    public JDateChooser getDateChooserToSuspendedDate() {
        return toSuspendedDate;
    }

    public void setSuspendUserListener(ActionListener suspendUserListener) {
        okButton.addActionListener(suspendUserListener);
    }

    public void closeDialog(ActionListener closeListener) {
        closeButton.addActionListener(closeListener);
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }
}
