package com.jimboyz.cims.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HomePage extends JPanel {

    private JLabel lblMedical;
    private JLabel lblDental;

    private final JPanel panelContainer;

    public HomePage() {

        this.setLayout(new BorderLayout());
        this.setBackground(new Color(238, 238, 238));

        panelContainer = new JPanel();
        panelContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 80, 110));
        panelContainer.setBackground(this.getBackground());

        JPanel panelLogo = new JPanel();
        panelLogo.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelLogo.setBackground(this.getBackground());
        panelLogo.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 50));

        JLabel lblLogo = new JLabel(SwingUtil.newImageIcon(SwingUtil.getImage("/com/jimboyz/cims/images/js-software-itsupport.png"), 250, 50, Image.SCALE_SMOOTH));
        panelLogo.add(lblLogo);

        this.add(panelLogo, BorderLayout.NORTH);
        this.add(panelContainer, BorderLayout.CENTER);

        medical();
        dental();
    }

    private void medical() {
        JPanel medicalPanel = setPanelMenuContainer();
        lblMedical = new JLabel(SwingUtil.newImageIcon(SwingUtil.getImage("/com/jimboyz/cims/images/folder_04.png"), 100, 100, Image.SCALE_SMOOTH));
        lblMedical.setOpaque(true);
        final HoverOption ho = getHoverOption(lblMedical);
        medicalPanel.addMouseListener(ho);
        medicalPanel.add(lblMedical, BorderLayout.CENTER);
        medicalPanel.add(SwingUtil.label("Medical", new Font("Consolas", Font.PLAIN, 17), Color.BLACK, SwingConstants.CENTER), BorderLayout.SOUTH);
        panelContainer.add(medicalPanel);
    }

    private static HoverOption getHoverOption(JLabel lblIcon) {
        HoverOption ho = new HoverOption(lblIcon);
        ho.setMouseAdapter(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ((JLabel) ho.getComponent()).setIcon(SwingUtil.newImageIcon(SwingUtil.getImage("/com/jimboyz/cims/images/folder_04.png"), 110, 110, Image.SCALE_SMOOTH));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ((JLabel) ho.getComponent()).setIcon(SwingUtil.newImageIcon(SwingUtil.getImage("/com/jimboyz/cims/images/folder_04.png"), 100, 100, Image.SCALE_SMOOTH));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                e.consume();
            }
        });
        return ho;
    }

    private void dental() {
        JPanel dentalPanel = setPanelMenuContainer();
        lblDental = new JLabel(SwingUtil.newImageIcon(SwingUtil.getImage("/com/jimboyz/cims/images/folder_04.png"), 100, 100, Image.SCALE_SMOOTH));
        HoverOption ho = getHoverOption(lblDental);
        lblDental.addMouseListener(ho);
        dentalPanel.add(lblDental, BorderLayout.CENTER);
        dentalPanel.add(SwingUtil.label("Dental", new Font("Consolas", Font.PLAIN, 17), Color.BLACK, SwingConstants.CENTER), BorderLayout.SOUTH);
        panelContainer.add(dentalPanel);
    }

    private JPanel setPanelMenuContainer() {
        JPanel xpanel = new JPanel();
        xpanel.setLayout(new BorderLayout());
        xpanel.setBackground(this.getBackground());
        xpanel.setBorder(BorderFactory.createBevelBorder(30));
        xpanel.setPreferredSize(new Dimension(110, 110));
        xpanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return xpanel;
    }

    public void medicalSelected(MouseListener e) {
        lblMedical.addMouseListener(e);
    }

    public void dentalSelected(MouseListener e) {
        lblDental.addMouseListener(e);
    }
}
