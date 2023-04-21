package ml.nennneko5787.minecraft.LunaChatSkript.skript.type.statements;

import ml.nennneko5787.minecraft.LunaChatSkript.utils.parser.ParserFactory;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Statement {

    @Nullable ParsingResult init(String code, Event event, @NotNull ParserFactory parser, @Nullable String preCodeBetween);

    @Nullable String parse(@NotNull Event event, @Nullable String codeBetween);

    default @Nullable String getDefaultEndSectionName() {
        return null;
    }

}
