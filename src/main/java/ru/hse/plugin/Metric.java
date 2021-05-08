package ru.hse.plugin;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Metric {

    void update(char charTyped, @NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file);

    void clear();

    String getInfo();

    @Override
    String toString(); // TODO хз как явно говорить что это обязательно надо заовеерайдить

    @NotNull
    String getName(); // Формат: 'ИмяКласс(поля, какого, то, конструктора)'

    @Nullable
    static Metric fromString(@Nullable String metric) {
        if (metric == null) {
            return null;
        }

        String[] parts = metric.split(" ");

        if (parts[0].equals(PluginConstants.WORD_COUNTER)) {
            if (parts.length != 3) throw new RuntimeException("Parse error, could not parse \"" + metric + "\"");
            return new WordCounter(parts[1], Integer.parseInt(parts[2]));
        } else if (parts[0].equals(PluginConstants.CHAR_COUNTER)) {
            if (parts.length != 3) throw new RuntimeException("Parse error, could not parse \"" + metric + "\"");
            return new CharCounter((char)Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
        } else {
            throw new RuntimeException("Parse error, could not parse \"" + metric + "\"");
        }
    }
}
