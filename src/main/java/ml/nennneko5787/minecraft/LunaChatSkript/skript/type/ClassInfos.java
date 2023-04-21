package ml.nennneko5787.minecraft.LunaChatSkript.skript.type;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.classes.Serializer;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.yggdrasil.Fields;
import org.jetbrains.annotations.NotNull;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;

public class ClassInfos {

    static {
        Classes.registerClass(new ClassInfo<>(JapanizeTypeType.class, "japanizetype")
                .user("japanizetypes?")
                .name("japanizetype")
                .description("Japanize Type")
                .parser(new Parser<JapanizeTypeType>() {
                    @Override
                    public JapanizeTypeType parse(@NotNull String input, @NotNull ParseContext context) {
                        return null;

                    }

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return false;
                    }

                    @Override
                    public String toVariableNameString(JapanizeTypeType type) {
                        return String.valueOf(type);
                    }

                    public @NotNull String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    public @NotNull String toString(@NotNull JapanizeTypeType type, int flags) {
                        return toVariableNameString(type);
                    }
                }));
    }

}
