package com.jcduhdt.sharp.markbook.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.MessageDialogBuilder;
import com.intellij.psi.util.StringEntry;
import com.intellij.ui.EditorTextField;
import com.jcduhdt.sharp.markbook.data.DataCenter;
import com.jcduhdt.sharp.markbook.data.DataConvert;
import com.jcduhdt.sharp.markbook.data.NoteData;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class AddNoteDialog extends DialogWrapper {

    private EditorTextField tfTitle;
    private EditorTextField tfMark;

    public AddNoteDialog() {
        super(true);
        setTitle("Add Note");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        tfTitle = new EditorTextField("Note Title");
        tfMark = new EditorTextField("Note Content");

        tfMark.setPreferredSize(new Dimension(200,100));

        panel.add(tfTitle, BorderLayout.NORTH);
        panel.add(tfMark, BorderLayout.CENTER);
        return panel;
    }

    @Override
    protected JComponent createSouthPanel() {
        JPanel panel = new JPanel();
        JButton button = new JButton("Add Note to List");
        button.addActionListener(e -> {
            String title = tfTitle.getText();
            String mark = tfMark.getText();

            String fileType = DataCenter.FINAL_NAME.substring(DataCenter.FINAL_NAME.lastIndexOf(".") + 1);
            NoteData noteData = new NoteData(title, mark, DataCenter.SELECT_TEXT, DataCenter.FINAL_NAME, fileType);

            DataCenter.NOTE_LIST.add(noteData);
            DataCenter.TABLE_MODEL.addRow(DataConvert.convert(noteData));
            this.close(0);
        });
        panel.add(button);
        return panel;
    }
}
