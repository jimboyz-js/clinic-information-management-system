package com.jimboyz.cims.err;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
//
//import com.jimboyz.cps.maingui.view.logs.LoggerController;
//import com.jimboyz.cps.payroll.SwingUtil;

public class ErrorDialog {

    private final Throwable throwable;

    private final Component component;

    public static void error(String message) {
        show(new Exception(message));
        System.err.println(new Exception());
        new Exception().printStackTrace();
//        LoggerController.logger(Level.SEVERE, message, new Exception(message));
    }

    public static void error(Throwable throwable) {
        show(throwable, null);
        System.err.println(throwable);
        throwable.printStackTrace();
//        LoggerController.logger(Level.SEVERE, "Exception", throwable);
    }

    public static void show(String message) {
        show(new Exception(message));
//        LoggerController.logger(Level.SEVERE, message, new Exception(message));
    }

    public static void show(Throwable throwable) {
        show(throwable, null);
//        LoggerController.logger(Level.SEVERE, "Exception", new Exception(throwable));

    }

    public static void show(Throwable throwable, Component component) {
        ErrorDialog ed = new ErrorDialog(throwable, component);
        ed.show0();
    }

    public ErrorDialog(Throwable throwable, Component component) {
        this.throwable = throwable;
        this.component = component;
    }

    private void show0() {
        Window win = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow();
        CustomDialog cd = null;
        if (win instanceof Dialog) {
            cd = new CustomDialog((Dialog)win);
        } else if (win instanceof Frame) {
            cd = new CustomDialog((Frame)win);
        } else {
            cd = new CustomDialog((Frame)null);
        }
        String msg = getMessage(this.throwable);
        cd.setMessage((msg != null) ? msg : "");
        cd.setTitle("Error");
        cd.setResizable(true);
        cd.pack();
        if (this.component != null)
            cd.setLocationRelativeTo(this.component);
        centerWindow(cd);
        cd.setVisible(true);
    }

    private void centerWindow(Window win) {
        win.setLocationRelativeTo(null);

    }

    private String getMessage(Throwable t) {
        if (t == null)
            return null;
        String msg = t.getMessage();
        Throwable cause = t.getCause();
        while (cause != null) {
            String s = cause.getMessage();
            if (s != null)
                msg = s;
            cause = cause.getCause();
        }
        return msg;
    }

    private class CustomDialog extends JDialog {

        private static final long serialVersionUID = 1L;

        JPanel container;

        JLabel lblIcon;

        JLabel lblMessage;

        ThrowableView view;

        int maxWidth = 700;

        CustomDialog(Dialog owner) {
            super(owner, true);
            initComponents();
        }

        CustomDialog(Frame owner) {
            super(owner, true);
            initComponents();
        }

        void setMessage(String message) {
            if (message != null) {
                StringBuffer sb = new StringBuffer();
                sb.append("<html>"+ message.replaceAll("\\n", "<br>") + "</html>");
                this.lblMessage.setFont(new Font("Dialog", Font.PLAIN, 12));
                this.lblMessage.setText(sb.toString());
                Dimension dim = this.lblMessage.getPreferredSize();
                if (dim.width > this.maxWidth) {
                    int parts = dim.width / this.maxWidth;
                    if (dim.width % this.maxWidth > 0)
                        parts++;
                    int height = dim.height * parts;
                    this.lblMessage.setPreferredSize(new Dimension(this.maxWidth, height));
                } else if (dim.width < 500) {
                    this.lblMessage.setPreferredSize(new Dimension(500, dim.height));
                }
            } else {
                this.lblMessage.setText("");
            }
        }

        private void initComponents() {
            this.container = new JPanel(new BorderLayout());
            this.view = new ThrowableView();
            JPanel pnlView = new JPanel(new BorderLayout());
            pnlView.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
            pnlView.setPreferredSize(new Dimension(100, 200));
            pnlView.add(this.view);
            pnlView.setVisible(false);
            this.lblIcon = new JLabel();
            this.lblIcon.setIcon(UIManager.getIcon("OptionPane.errorIcon"));
            this.lblIcon.setVerticalAlignment(1);
            this.lblMessage = new JLabel("Error Message");
            this.lblMessage.setVerticalAlignment(1);
            this.lblMessage.setBorder(BorderFactory.createEmptyBorder(3, 15, 0, 20));
            JPanel pnlIcon = new JPanel(new BorderLayout());
            pnlIcon.add(this.lblIcon, "North");
            JPanel boxTop = new JPanel(new BorderLayout());
            this.container.add(boxTop, "North");
            boxTop.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
            boxTop.add(pnlIcon, "West");
            boxTop.add(this.lblMessage);
            JPanel pnlSouth = new JPanel(new BorderLayout());
            this.container.add(pnlSouth, "Center");
            Box boxButtons = new Box(2);
            pnlSouth.add(boxButtons, "North");
            pnlSouth.add(pnlView, "Center");
            boxButtons.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 20));
            boxButtons.add(createButton("Details >>", "showDetails", 'd', true));
            boxButtons.add(createButton("Details <<", "hideDetails", 'd', false));
            boxButtons.add(Box.createHorizontalGlue());
            JButton defaultButton = createButton("OK", "OK", 'o', true);
            defaultButton.setFocusable(false);
            defaultButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            getRootPane().setDefaultButton(defaultButton);
            boxButtons.add(defaultButton);
            Dimension btnDim = defaultButton.getPreferredSize();
            defaultButton.setPreferredSize(new Dimension(70, btnDim.height));
            setContentPane(this.container);
            addWindowListener(new WindowAdapter() {
                public void windowActivated(WindowEvent e) {
                    JButton btn = CustomDialog.this.getRootPane().getDefaultButton();
                    if (btn == null)
                        return;
                    btn.grabFocus();
                }
            });
            CancelWindowAction cwa = new CancelWindowAction(this);
            this.container.registerKeyboardAction(cwa, cwa.getKeyStroke(), 2);
        }

        private JButton createButton(String text, String actionCommand, char mnemonic, boolean visible) {
            JButton btn = new JButton(text);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setActionCommand(actionCommand);
            btn.setVisible(visible);
            btn.setFocusable(false);
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    CustomDialog.this.perform(e);
                }
            });
            if (mnemonic != '\000')
                btn.setMnemonic(mnemonic);
            this.container.putClientProperty("Button." + actionCommand, btn);
            return btn;
        }

        private void perform(ActionEvent e) {
            String cmd = e.getActionCommand();
            JButton btn = (JButton)e.getSource();
            if ("OK".equals(cmd)) {
                dispose();
            } else if ("showDetails".equals(cmd)) {
                this.view.getParent().setVisible(true);
                this.view.print(ErrorDialog.this.throwable);
                pack();
                btn.setVisible(false);
                ((JComponent)this.container.getClientProperty("Button.hideDetails")).setVisible(true);
                Dimension size = this.getSize();
                this.setSize(size.width, size.height);
            } else if ("hideDetails".equals(cmd)) {
                this.view.getParent().setVisible(false);
                this.pack();
                btn.setVisible(false);
                ((JComponent)this.container.getClientProperty("Button.showDetails")).setVisible(true);
                Dimension size = getSize();
                setSize(size.width, size.height);
            }
        }
    }

    private class ThrowableView extends JScrollPane {

        private static final long serialVersionUID = 1L;

        JTextArea field;

        ThrowableView() {
            this.field = new JTextArea();
            this.field.setBorder(BorderFactory.createEmptyBorder(3, 3, 0, 0));
            this.field.setEditable(false);
            this.field.setForeground(Color.red);
            //Font font = this.field.getFont();
            //this.field.setFont(new Font(font.getFontName(), 0, 13));
            //System.out.println("Font : " + font.getFontName());
            this.field.setFont(new Font("Consolas", Font.PLAIN, 13));
            setViewportView(this.field);
        }

        void print(Throwable throwable) {
            CustomWriter writer = new CustomWriter();
            if (throwable != null)
                throwable.printStackTrace(new PrintWriter(writer));
            this.field.setText(writer.getText());
        }
    }

    private class CustomWriter extends Writer {
        StringBuffer buffer = new StringBuffer();

        public void write(char[] cbuf, int off, int len) throws IOException {
            this.buffer.append(cbuf, off, len);
        }

        public String getText() {
            return this.buffer.toString();
        }

        public void flush() throws IOException {}

        public void close() throws IOException {}

        private CustomWriter() {}
    }

    private class CancelWindowAction implements ActionListener {
        JDialog dialog;

        KeyStroke keyStroke;

        CancelWindowAction(JDialog dialog) {
            this.dialog = dialog;
            this.keyStroke = KeyStroke.getKeyStroke(27, 0, false);
        }

        public KeyStroke getKeyStroke() {
            return this.keyStroke;
        }

        public void actionPerformed(ActionEvent e) {
            this.dialog.dispose();
        }
    }
}


