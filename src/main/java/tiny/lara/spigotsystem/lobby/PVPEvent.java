package tiny.lara.spigotsystem.lobby;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PVPEvent implements Listener {
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		if(!(e.getEntity() instanceof Player)) return;
		Player player = (Player) e.getEntity();
		if(player.getGameMode().equals(GameMode.CREATIVE) || !Settings.isHitEvent || Settings.lobbyWorldName == null) {
			return;
		}
		if(player.getLocation().getWorld().getName().equals(Settings.lobbyWorldName)) {
			e.setCancelled(true);
		    e.getDamager().sendMessage(Settings.prefix+Settings.hitMessage.replace("%player%", e.getDamager().getName()));
		}
	}
}