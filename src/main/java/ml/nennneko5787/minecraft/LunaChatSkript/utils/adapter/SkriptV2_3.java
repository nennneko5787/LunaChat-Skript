package ml.nennneko5787.minecraft.LunaChatSkript.utils.adapter;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.log.HandlerList;
import ch.njol.skript.log.SkriptLogger;
import ch.njol.util.Kleenean;
import ml.nennneko5787.minecraft.LunaChatSkript.utils.ReflectionUtils;
import org.bukkit.event.Event;

public class SkriptV2_3 implements SkriptAdapter {

    @Override
    public void setHasDelayedBefore(Kleenean value) {

        ReflectionUtils.setField(
                ScriptLoader.class,
                null,
                "hasDelayBefore",
                value
        );

    }

    @Override
    public Kleenean getHasDelayedBefore() {
        return ReflectionUtils.getFieldValue(
                ScriptLoader.class,
                "hasDelayBefore"
        );
    }

    @Override
    public HandlerList getHandlers() {
        return ReflectionUtils.getFieldValue(SkriptLogger.class, "handlers");
    }

    @Override
    public Class<? extends Event>[] getCurrentEvents() {
        return ScriptLoader.getCurrentEvents();
    }

    @SafeVarargs
    @Override
    public final boolean isCurrentEvents(Class<? extends Event>... events) {
        return ScriptLoader.isCurrentEvent(events);
    }

    @Override
    public void setCurrentEvent(String name, Class<? extends Event>... events) {
        ScriptLoader.setCurrentEvent(name, events);
    }

}
