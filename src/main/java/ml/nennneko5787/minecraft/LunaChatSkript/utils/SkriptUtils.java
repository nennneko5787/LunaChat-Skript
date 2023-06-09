package ml.nennneko5787.minecraft.LunaChatSkript.utils;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.SyntaxElement;
import ch.njol.skript.lang.SyntaxElementInfo;
import ch.njol.skript.lang.util.SimpleLiteral;
import ch.njol.skript.log.ParseLogHandler;
import ch.njol.skript.log.SkriptLogger;
import ch.njol.skript.variables.Variables;
import ch.njol.util.NonNullPair;
import ch.njol.util.StringUtils;
import ml.nennneko5787.minecraft.LunaChatSkript.utils.adapter.SkriptAdapter;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SkriptUtils {

    private static final Pattern varPattern = Pattern.compile("%[\\w-_:*]+%");

    public static Expression<?> parseExpression(
            String expr,
            @Nullable String defaultError,
            Event event) {
        return parseExpression(expr, Skript.getExpressions(), defaultError, event);
    }

    /**
     * Original code from SkriptLang team, I just changed how the SkriptParser work with Expression.
     * @author SkriptLang team
     */
    @Nullable
    @SuppressWarnings("unchecked")
    public static <T extends SyntaxElement> T parseExpression(
            String expr,
            Iterator<? extends SyntaxElementInfo<? extends T>> source,
            @Nullable String defaultError,
            Event event) {
        expr = "" + expr.trim();
        if (expr.isEmpty()) {
            Skript.error(defaultError);
            return null;
        } else {
            ParseLogHandler log = SkriptLogger.startParseLogHandler();

            final Matcher varMatcher = varPattern.matcher(expr);
            final boolean onlyVariable = expr.startsWith("\"%") && expr.endsWith("%\"");

            final T var5;
            try {

                String mainContent = null;
                while (varMatcher.find()) {
                    final String varName = varMatcher.group(0).replaceAll("%", "");
                    final Object value = Variables.getVariable(varName.replaceFirst("_", ""),
                            event, varName.startsWith("_"));
                    String content;
                    try {
                        content = StringUtils.join(((Map<?, ?>) value).values(), ", ");
                    } catch (ClassCastException ex) {
                        content = value.toString();
                    } catch (NullPointerException ex) {
                        content = "<none>";
                    }
                    mainContent = content;
                    expr = replaceGroup(varPattern.toString(),
                            expr, 0, content);
                }

                if (onlyVariable)
                    return mainContent == null ? null : (T) new SimpleLiteral<>(mainContent, false);

                final SkriptParser parser = new SkriptParser(expr);
                final T e;
                final NonNullPair<String, Class<? extends Event>[]> previous = new NonNullPair<>(ScriptLoader.getCurrentEventName(), SkriptAdapter.getInstance().getCurrentEvents());
                try {

                    SkriptAdapter.getInstance().setCurrentEvent(event.getEventName(), event.getClass());
                    // The parse method is private
                    final Method method = parser
                            .getClass()
                            .getDeclaredMethod("parse", Iterator.class);
                    method.setAccessible(true);
                    e = (T) method.invoke(parser, source);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                } finally {
                    SkriptAdapter.getInstance().setCurrentEvent(previous.getFirst(), previous.getSecond());
                }
                if (e == null) {
                    log.printError(defaultError);
                    var5 = null;
                    return var5;
                }

                log.printLog();
                var5 = e;
            } finally {
                log.stop();
            }

            return var5;
        }
    }

    public static String replaceGroup(String regex, String source, int groupToReplace, String replacement) {
        return replaceGroup(regex, source, groupToReplace, 1, replacement);
    }

    public static String replaceGroup(String regex, String source, int groupToReplace, int groupOccurrence, String replacement) {
        Matcher m = Pattern.compile(regex).matcher(source);
        for (int i = 0; i < groupOccurrence; i++)
            if (!m.find()) return source; // pattern not met, may also throw an exception here
        return new StringBuilder(source).replace(m.start(groupToReplace), m.end(groupToReplace), replacement).toString();
    }
}
