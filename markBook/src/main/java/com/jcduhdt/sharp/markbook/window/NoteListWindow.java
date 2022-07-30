package com.jcduhdt.sharp.markbook.window;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageDialogBuilder;
import com.intellij.openapi.ui.OkCancelDialogBuilder;
import com.intellij.openapi.ui.messages.MessageDialog;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.jcduhdt.sharp.markbook.data.DataCenter;
import com.jcduhdt.sharp.markbook.processor.DefaultSourceNoteData;
import com.jcduhdt.sharp.markbook.processor.MDFreeMarkerProcessor;
import com.jcduhdt.sharp.markbook.processor.Processor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class NoteListWindow {
    private JTextField tfTopic;
    private JTable tbContent;
    private JButton btnGenerate;
    private JButton btnClean;
    private JButton btnClose;
    private JPanel contentPanel;

    private void init() {
        tbContent.setModel(DataCenter.TABLE_MODEL);
        tbContent.setEnabled(true);
    }

    public NoteListWindow(Project project, ToolWindow toolWindow) {
        init();
        btnGenerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String topic = tfTopic.getText();
                String fileName = topic + ".md";
                if (topic == null || "".equals(topic)) {
                     MessageDialogBuilder.yesNo("Operation Result", "Doc Title is empty").show();
                    return;
                }
                VirtualFile virtualFile = FileChooser.chooseFile(FileChooserDescriptorFactory.createSingleFolderDescriptor(), project, project.getBaseDir());
                if (virtualFile != null) {
                    String path = virtualFile.getPath();
                    String fileFullPath = path + File.separator + fileName;

                    Processor processor = new MDFreeMarkerProcessor();
                    try {
                        processor.process(new DefaultSourceNoteData(fileFullPath, topic, DataCenter.NOTE_LIST));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        return;
                    }
                    MessageDialogBuilder.yesNo("Operation Result", "success").show();
                }
            }
        });

        btnClean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataCenter.reset();
            }
        });


        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolWindow.hide(null);
            }
        });
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}
