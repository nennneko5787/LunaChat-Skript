package ml.nennneko5787.minecraft.LunaChatSkript.skript.type.statements;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ml.nennneko5787.minecraft.LunaChatSkript.utils.SkriptUtils;
import ml.nennneko5787.minecraft.LunaChatSkript.utils.parser.ParserFactory;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExecuteStatement implements Statement {

    private Effect effect;

    @Override
    public @Nullable ParsingResult init(String code, Event event, @NotNull ParserFactory parser, @Nullable String preCodeBetween) {
        if (!code.startsWith("execute "))
            return null;
        final String rawEffect = code.substring(8);

        effect = SkriptUtils.parseExpression(rawEffect, Skript.getEffects().iterator(), null, event);
        if (effect == null)
            return new ParsingResult(null, "Could not parse effect: " + rawEffect);
        return new ParsingResult();
    }

    @Override
    public @Nullable String parse(@NotNull Event event, @Nullable String codeBetween) {
        effect.run(event);
        return "";
    }
}
