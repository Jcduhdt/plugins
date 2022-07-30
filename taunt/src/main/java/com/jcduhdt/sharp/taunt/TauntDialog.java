package com.jcduhdt.sharp.taunt;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class TauntDialog extends DialogWrapper {

    private JLabel label;

    protected TauntDialog() {
        super(true);
        setTitle("每天一碗毒鸡汤");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel panel = new JPanel();
        label = new JLabel(ContentUtil.getContent());
        panel.add(label);
        return panel;
    }


    @Override
    protected JComponent createSouthPanel() {
        JPanel panel = new JPanel();
        JButton button = new JButton("再干一碗");
        button.addActionListener(e -> {
            label.setText(ContentUtil.getContent());
        });
        panel.add(button);
        return panel;
    }

}
