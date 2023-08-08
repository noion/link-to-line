package com.github.noion.linktoline.psi

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project


class EditorIllustrationAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        // Get all the required data from data keys
        // Get all the required data from data keys
        val editor: Editor = event.getRequiredData(CommonDataKeys.EDITOR)
        val project: Project = event.getRequiredData(CommonDataKeys.PROJECT)
        val document = editor.document

        // Work off of the primary caret to get the selection info

        // Work off of the primary caret to get the selection info
        val primaryCaret = editor.caretModel.primaryCaret
        val start = primaryCaret.selectionStart
        val end = primaryCaret.selectionEnd

        // Replace the selection with a fixed string.
        // Must do this document change in a write action context.

        // Replace the selection with a fixed string.
        // Must do this document change in a write action context.
        WriteCommandAction.runWriteCommandAction(
            project
        ) { document.replaceString(start, end, "editor_basics") }

        // De-select the text range that was just replaced

        // De-select the text range that was just replaced
        primaryCaret.removeSelection()
    }

    override fun update(event: AnActionEvent) {
        // Get required data keys
        // Get required data keys
        val project = event.project
        val editor = event.getData(CommonDataKeys.EDITOR)

        // Set visibility only in the case of
        // existing project editor, and selection

        // Set visibility only in the case of
        // existing project editor, and selection
        event.getPresentation().setEnabledAndVisible(project != null && editor != null && editor.selectionModel.hasSelection())
    }
}