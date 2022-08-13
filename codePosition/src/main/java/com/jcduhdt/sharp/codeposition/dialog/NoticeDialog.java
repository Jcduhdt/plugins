package com.jcduhdt.sharp.codeposition.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBScrollPane;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class NoticeDialog extends DialogWrapper {

    private String FailReason;

    public NoticeDialog(String failReason) {
        super(true);
        setTitle("Search Failed");
        this.FailReason = failReason;
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JTextArea jta = new JTextArea(FailReason);
        jta.setLineWrap(true);
        jta.setEditable(false);
        jta.setSize(500, 300);

        JBScrollPane scrollPane = new JBScrollPane(jta,JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        return scrollPane;
    }

    @Override
    protected @Nullable JComponent createSouthPanel() {

        JPanel panel = new JPanel();
        JButton button = new JButton("ok");
        button.setForeground(new Color(51,153,255));
        button.addActionListener(e -> {
            this.close(OK_EXIT_CODE);
        });
        panel.add(button);
        return panel;
    }
}
