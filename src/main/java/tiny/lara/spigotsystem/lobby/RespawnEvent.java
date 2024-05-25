package tiny.lara.spigotsystem.lobby;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import tiny.lara.spigotsystem.Main;

public class RespawnEvent implements Listener {
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		if(!Settings.isTeleportOnRespawn || Settings.Spawn == null) {
			return;
		}
		Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
			@Override
			public void run() {
	            event.getPlayer().teleport(Settings.Spawn);
			}
		}, 2);	
	}
}