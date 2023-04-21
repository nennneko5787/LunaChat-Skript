package ml.nennneko5787.minecraft.LunaChatSkript.utils.adapter;

import ch.njol.skript.log.HandlerList;
import ch.njol.util.Kleenean;
import ml.nennneko5787.minecraft.LunaChatSkript.Main;
import org.bukkit.event.Event;

/**
 * Thanks to anarchick for the versioning check!
 * @author ItsTheSky / Anarchick
 */
public interface SkriptAdapter {

    static SkriptAdapter getInstance() {
        return Main.getSkriptAdapter();
    }

    void setHasDelayedBefore(Kleenean value);

    Kleenean getHasDelayedBefore();

    HandlerList getHandlers();

    Class<? extends Event>[] getCurrentEvents();

    boolean isCurrentEvents(Class<? extends Event>... events);

    void setCurrentEvent(String name, Class<? extends Event>... events);

}
