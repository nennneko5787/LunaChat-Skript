package ml.nennneko5787.minecraft.LunaChatSkript.skript.type.statements;

import ml.nennneko5787.minecraft.LunaChatSkript.utils.parser.ParserFactory;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CommentStatement implements Statement {
    @Override
    public @Nullable ParsingResult init(String code, Event event, @NotNull ParserFactory parser, @Nullable String preCodeBetween) {
        return code.startsWith("#") ? new ParsingResult() : null;
    }

    @Override
    public @Nullable String parse(@NotNull Event event, @Nullable String codeBetween) {
        return "";
    }
}
