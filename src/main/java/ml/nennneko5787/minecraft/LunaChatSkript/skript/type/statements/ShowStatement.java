package ml.nennneko5787.minecraft.LunaChatSkript.skript.type.statements;

import ch.njol.skript.lang.Expression;
import ch.njol.util.StringUtils;
import ml.nennneko5787.minecraft.LunaChatSkript.utils.SkriptUtils;
import ml.nennneko5787.minecraft.LunaChatSkript.utils.parser.ParserFactory;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class ShowStatement implements Statement{

    private Expression<?> expression;

    @Override
    public @Nullable ParsingResult init(String code, Event event, @NotNull ParserFactory parser, @Nullable String preCodeBetween) {
        if (!code.startsWith("show "))
            return null;
        final String rawExpression = code.substring(5);
        expression = SkriptUtils.parseExpression(rawExpression, null, event);
        if (expression == null)
            return new ParsingResult(null, "Can't understand this expression: " + rawExpression);
        return new ParsingResult();
    }

    @Override
    public @Nullable String parse(@NotNull Event event, @Nullable String codeBetween) {
        String value;
        try {
            value = expression.isSingle() ? expression.getSingle(event).toString() :
                    StringUtils.join(expression.getArray(event), ", ");
        } catch (NullPointerException ex) {
            value = "<none>";
        }
        return value;
    }
}
