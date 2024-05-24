package tiny.lara.spigotsystem.lobby;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDamage implements Listener {
    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if (p.getGameMode() == GameMode.CREATIVE) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }
}