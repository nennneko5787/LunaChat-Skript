package ml.nennneko5787.minecraft.LunaChatSkript;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ml.nennneko5787.minecraft.LunaChatSkript.skript.PluginUpdater;
import ml.nennneko5787.minecraft.LunaChatSkript.utils.ReflectionUtils;
import ml.nennneko5787.minecraft.LunaChatSkript.utils.adapter.SkriptAdapter;
import ml.nennneko5787.minecraft.LunaChatSkript.utils.adapter.SkriptV2_3;
import ml.nennneko5787.minecraft.LunaChatSkript.utils.adapter.SkriptV2_6;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.github.ucchyocean.lc3.LunaChatAPI;
import com.github.ucchyocean.lc3.LunaChatBukkit;

import java.io.IOException;

public final class Main extends JavaPlugin {

	private SkriptAddon addon;
	private static SkriptAdapter skriptAdapter;
	public static boolean use26;
	public static LunaChatAPI lunachatapi;
	private static Main instance;

	@Override
	public void onEnable() {
		getLogger().info("The cat is trying to wake up...");
		addon = Skript.registerAddon(this);
		instance = this;
		try {
			addon.loadClasses("ml.nennneko5787.minecraft.LunaChatSkript", "skript");
		} catch (IOException e) {
			e.printStackTrace();
			this.onDisable();
		}

		final PluginUpdater updater = PluginUpdater.create(this, "nennneko5787", "LunaChat-Skript");
		final PluginUpdater.UpdateState state = updater.check();
		switch (state) {
			case LOWER:
				getLogger().warning("You are using an outdated LunaChat-Skript version!");
				getLogger().warning("Latest is " + updater.getLatest() + ", but are are on " + getDescription().getVersion() + "!");
				getLogger().warning("Update it now: https://github.com/nennneko5787/LunaChat-Skript/releases/latest" + updater.getLatest());
				break;
			case EQUAL:
				getLogger().info("You are on the latest LunaChat-Skript version! Well done!");
				break;
			case GREATER:
				getLogger().warning("Detected a test version of LunaChat-Skript. Please report bugs on our GitHub.");
				break;
		}
		
		/* This creates config.yml and all other websk folders
		this.saveDefaultConfig();

		try {
			Path path = Paths.get("plugins/WebSK/files/");
			Files.createDirectories(path);
		} catch (IOException e) {
			e.printStackTrace();
			this.onDisable();
		}
		*/

		// This class is from 2.6-alpha1 and +
		final boolean use26 = ReflectionUtils.classExist("ch.njol.skript.conditions.CondIsPluginEnabled");
		skriptAdapter = use26 ? new SkriptV2_6() : new SkriptV2_3();
		Main.use26 = use26;

		//LunaChat API Get Instance
		LunaChatAPI lunachatapi;
		if (getServer().getPluginManager().isPluginEnabled("LunaChat")) {
			lunachatapi = ((LunaChatBukkit) getServer().getPluginManager().getPlugin("LunaChat")).getLunaChatAPI();
			Main.lunachatapi = lunachatapi;
		}
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public static SkriptAdapter getSkriptAdapter() {
		return skriptAdapter;
	}

	@Override
	public void onDisable() {
		getLogger().info("The cat sleeps again.");
	}
}
