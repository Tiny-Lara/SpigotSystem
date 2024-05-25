package tiny.lara.spigotsystem.lobby;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tiny.lara.spigotsystem.Main;

public class JoinEvent implements Listener {
	@EventHandler
	public void playerJoin (PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(Settings.isWelcomeMessage) {
			p.sendMessage(Settings.prefix + Settings.welcomeMessage.replace("%player%", p.getName()));
		}
		if(Settings.isJoinMessage) {
			e.setJoinMessage(Settings.joinMessage.replace("%player%", p.getName()));
		}
		if(Settings.lobbyWorldName == null) {
		if(p.hasPermission(Main.prefixWithoutColor.toLowerCase() + ".admin") || p.hasPermission(Main.prefixWithoutColor.toLowerCase() + ".cmd")) {
				p.sendMessage(Settings.prefix + "�cType �a/sl setspawn �c to set the lobby spawn point");
			} else {
				p.sendMessage(Settings.prefix + "�cType �6op " + p.getName() + "�c in server console and set the lobby spawn with �a/sl setspawn");
			}
		}
		if(Settings.isTeleportOnJoin && Settings.lobbyWorldName != null) {
			p.teleport(Settings.Spawn);
		}
	}
}