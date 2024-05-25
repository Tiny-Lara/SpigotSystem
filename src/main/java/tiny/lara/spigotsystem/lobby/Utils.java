package tiny.lara.spigotsystem.lobby;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import tiny.lara.spigotsystem.Main;

public class Utils {
	public static void reloadConfig() {
		Main.cfg = new YamlConfiguration().loadConfiguration(Main.file);
		Main.setSettingsFromConfig();
	}
	public static void reloadConfigCMD(Player p) {
		p.sendMessage("�6Reloading config...");
		Main.cfg = new YamlConfiguration().loadConfiguration(Main.file);
		Main.setSettingsFromConfig();
	}
	public static void reloadConfigConsole() {
		Bukkit.getServer().getConsoleSender().sendMessage(Settings.prefix + "�6Reloading config...");
		Main.cfg = new YamlConfiguration().loadConfiguration(Main.file);
		Main.setSettingsFromConfig();
	}
}