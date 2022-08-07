package com.jcduhdt.sharp.taunt;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;
import org.jetbrains.annotations.NotNull;


public class MyApplicationComponent implements ProjectManagerListener {

    @Override
    public void projectOpened(@NotNull Project project) {

        // Ensure this isn't part of testing
        if (ApplicationManager.getApplication().isUnitTestMode()) {
            return;
        }

        TauntDialog tauntDialog = new TauntDialog();
        tauntDialog.show();
    }
}