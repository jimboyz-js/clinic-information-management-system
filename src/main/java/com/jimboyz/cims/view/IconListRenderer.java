package com.jimboyz.cims.view;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class IconListRenderer extends DefaultListCellRenderer {

    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof IconListItem item) {
            label.setText(item.getText());
            label.setIcon(item.getIcon());
        } else {
            label.setText(value.toString());
        }

        return label;
    }
}

class IconListItem {
    private final String text;
    private final Icon icon;

    public IconListItem(String text, Icon icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public Icon getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return text;
    }
}

