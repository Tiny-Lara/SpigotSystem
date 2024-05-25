package tiny.lara.spigotsystem.lobby;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakEvent implements Listener {
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Player p = event.getPlayer();
		if(p.getGameMode().equals(GameMode.CREATIVE)) {
			return;
		}
		if(Settings.lobbyWorldName != null && Settings.isBreakEvent) {
			if(p.getLocation().getWorld().getName().equals(Settings.lobbyWorldName)) {
				event.setCancelled(true);
				p.sendMessage(Settings.prefix+Settings.breakMessage.replace("%player%", p.getName()));
			}
		}
	}
}