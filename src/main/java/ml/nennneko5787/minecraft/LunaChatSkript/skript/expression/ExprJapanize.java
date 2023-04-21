package ml.nennneko5787.minecraft.LunaChatSkript.skript.expression;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ml.nennneko5787.minecraft.LunaChatSkript.Main;
import ml.nennneko5787.minecraft.LunaChatSkript.skript.type.JapanizeTypeType;
import org.bukkit.event.Event;

import com.github.ucchyocean.lc3.LunaChatAPI;
import com.github.ucchyocean.lc3.japanize.JapanizeType;

public class ExprJapanize extends SimpleExpression<String> {

	static {
		Skript.registerExpression(ExprJapanize.class, String.class, ExpressionType.COMBINED, "japanize[d] %string% with type %japanizetype%");
	}

	private Expression<String> text;
	private Expression<JapanizeTypeType> japanizetypetype;

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
		text = (Expression<String>) exprs[0];
		japanizetypetype = (Expression<JapanizeTypeType>) exprs[1];
		return true;
	}

	@Override
	public String toString(Event event, boolean debug) {
		return "" + get(event);
	}

	@Override
	protected String[] get(Event event) {
		Main maininstance = Main.getInstance();
		LunaChatAPI instance = maininstance.lunachatapi;
		JapanizeTypeType japanizetypetypeObj = japanizetypetype.getSingle(event);
		String textObj = text.getSingle(event);
		String result = instance.japanize(textObj,JapanizeType.fromID(japanizetypetypeObj.toString(), JapanizeType.NONE));
		return new String[]{result};
	}

}
