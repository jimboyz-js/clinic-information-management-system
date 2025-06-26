package com.jimboyz.cims.view.webcam;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.jimboyz.cims.view.SwingUtil;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CameraView extends JDialog implements WindowListener {

    private final JPanel boardPanel;
    private final CardLayout cardLayout;
    private final Webcam webcam;

    private final JButton btnCapture;
    private final JButton btnRetake;
    private final JButton btnOk;
    private final JLabel lblPreview;

    public CameraView() {
        this(null);
    }

    public CameraView(JFrame owner) {
        super(owner, "Webcam", true);

        cardLayout = new CardLayout();

        this.setSize(new Dimension(700, 600));
        this.setLocationRelativeTo(owner);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLACK);
        this.getContentPane().setLayout(new BorderLayout());
        this.addWindowListener(CameraView.this);

        boardPanel = new JPanel();
        boardPanel.setLayout(cardLayout);
        boardPanel.setBackground(Color.BLACK);

        webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());

        WebcamPanel webcamPanel = new WebcamPanel(webcam, true);
        webcamPanel.setFPSDisplayed(true);
        webcamPanel.setImageSizeDisplayed(true);
        webcam.open();

        lblPreview = new JLabel();
        lblPreview.setHorizontalAlignment(SwingConstants.CENTER);
        lblPreview.setSize(new Dimension(this.getWidth(), this.getHeight()));
        boardPanel.add(webcamPanel, "webcam panel");
        boardPanel.add(lblPreview, "preview");

        showDefault();

        this.add(boardPanel, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 7, 10));
        panel.setBackground(Color.BLACK);//Change to frame.getBackground()
        panel.setPreferredSize(new Dimension(this.getWidth(), 50));

        btnCapture = SwingUtil.button("Capture", "capture", "btn-capture", new Font("Dialog", Font.PLAIN, 12), Color.BLACK, new Cursor(Cursor.HAND_CURSOR));
        btnOk = SwingUtil.button("Ok", "ok", "btn-ok", btnCapture.getFont(), btnCapture.getForeground(), btnCapture.getCursor());
        btnRetake = SwingUtil.button("Retake", "retake", "btn-retake", btnCapture.getFont(), btnCapture.getForeground(), btnCapture.getCursor());

        btnCapture.setPreferredSize(new Dimension(90, 25));
        btnOk.setPreferredSize(new Dimension(90, 25));
        btnRetake.setPreferredSize(new Dimension(90, 25));

        panel.add(btnRetake);
        panel.add(btnCapture);
        panel.add(btnOk);
        this.add(panel, BorderLayout.SOUTH);

        this.setVisible(false);
    }

    public void showDefault() {
        cardLayout.show(boardPanel, "webcam panel");
    }

    public void setCaptureImageListener(ActionListener listener) {
        btnCapture.addActionListener(listener);
    }

    public void setRetakeImageListener(ActionListener listener) {
        btnRetake.addActionListener(listener);
    }

    public void setOkImageListener(ActionListener listener) {
        btnOk.addActionListener(listener);
    }

    public Webcam getWebcam() {
        return webcam;
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public JLabel getLabelPreview() {
        return lblPreview;
    }

    public JButton getBtnCapture() {
        return btnCapture;
    }

    public JButton getBtnRetake() {
        return btnRetake;
    }

    public JButton getBtnOk() {
        return btnOk;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        webcam.close();
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
