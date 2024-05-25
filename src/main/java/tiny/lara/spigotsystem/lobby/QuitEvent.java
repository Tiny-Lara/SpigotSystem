package tiny.lara.spigotsystem.lobby;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		if(!Settings.isleaveMessage || Settings.leaveMessage == null) {
			return;
		}
		e.setQuitMessage(Settings.leaveMessage.replace("%player%", e.getPlayer().getName()));
	}
}