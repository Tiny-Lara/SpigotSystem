package tiny.lara.spigotsystem.lobby;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class DamageEvent implements Listener {
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if(Settings.lobbyWorldName == null && !Settings.isDamageEvent) {
		  return;	
		}	
			if(event.getEntity().getLocation().getWorld().getName().equals(Settings.lobbyWorldName)) {
				if(event.getCause() == DamageCause.FALL) {
					event.setCancelled(true);
				} else if(event.getCause() == DamageCause.LAVA) {
					event.setCancelled(true);
				} else if(event.getCause() == DamageCause.FIRE) {
					event.setCancelled(true);
				} else if(event.getCause() == DamageCause.FIRE_TICK) {
					event.setCancelled(true);
				} else if(event.getCause() == DamageCause.FALLING_BLOCK) {
					event.setCancelled(true);
				} else if(event.getCause() == DamageCause.BLOCK_EXPLOSION) {
					event.setCancelled(true);
				} else if(event.getCause() == DamageCause.ENTITY_EXPLOSION) {
					event.setCancelled(true);
				}
		   }
	 }
}