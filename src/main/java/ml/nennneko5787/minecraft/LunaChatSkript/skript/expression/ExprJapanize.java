package ml.nennneko5787.minecraft.LunaChatSkript.skript.expression;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ml.nennneko5787.minecraft.LunaChatSkript.Main;
import org.bukkit.event.Event;

import com.github.ucchyocean.lc3.LunaChatAPI;
import com.github.ucchyocean.lc3.japanize.JapanizeType;
import com.github.ucchyocean.lc3.LunaChatBukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ExprJapanize extends SimpleExpression<String> {
	private static LunaChatAPI lunachatapi;

	static {
		Skript.registerExpression(ExprJapanize.class, String.class, ExpressionType.COMBINED, "[lunachat] japanize[d] %string% with [japanize][ ]type %string%");
	}

	private Expression<String> text;
	private Expression<String> japanizetypetype;

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
		japanizetypetype = (Expression<String>) exprs[1];
		return true;
	}

	@Override
	public String toString(Event event, boolean debug) {
		return "" + get(event);
	}

	@Override
	protected String[] get(Event event) {
		//LunaChat API Get Instance
		if (Main.serverInstance.getPluginManager().isPluginEnabled("LunaChat")) {
			this.lunachatapi = ((LunaChatBukkit) Main.serverInstance.getPluginManager().getPlugin("LunaChat")).getLunaChatAPI();
		}
		String japanizetypetypeObj = japanizetypetype.getSingle(event);
		String textObj = text.getSingle(event);
		String result = this.lunachatapi.japanize(textObj,JapanizeType.fromID(japanizetypetypeObj,JapanizeType.NONE));
		if (result == null) {
			result = "";
		}
		return new String[]{result};
	}

}
