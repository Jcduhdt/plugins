package com.jcduhdt.sharp.codeposition.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.jcduhdt.sharp.codeposition.data.Data;
import com.jcduhdt.sharp.codeposition.dialog.NoticeDialog;
import com.jcduhdt.sharp.codeposition.util.RegexUtil;
import com.jcduhdt.sharp.codeposition.util.SystemUtil;

import java.util.Set;

public class CodePosition extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {

        Project project = e.getProject();
        String projectName = project.getName();

        String content = SystemUtil.getTextFromClipBoard();

        if (content.equals("")) {
            new NoticeDialog("clipboard is empty").show();
            return;
        }

        Data data;
        try {
            data = RegexUtil.fileDataRegex(projectName, content);
        } catch (Exception ex) {
            new NoticeDialog(ex.getMessage() + "\ncontent: " + content).show();
            return;
        }


        Set<VirtualFile> vfSet = (Set<VirtualFile>) FilenameIndex.getVirtualFilesByName(data.fileName, GlobalSearchScope.projectScope(project));

        Boolean notFind = true;
        for (VirtualFile vf : vfSet) {

            String vfPath = vf.getPath();
            if (!vfPath.contains(data.filePath)) {
                continue;
            }

            // 光标移动到指定位置
            new OpenFileDescriptor(project, vf, data.row, data.col).navigate(true);
            notFind = false;
            break;
        }

        if (notFind) {
            new NoticeDialog("not find: " + data).show();
        }
    }
}
