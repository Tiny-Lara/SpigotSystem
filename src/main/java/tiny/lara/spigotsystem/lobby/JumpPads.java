package tiny.lara.spigotsystem.lobby;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class JumpPads implements Listener {
    @EventHandler
    public void onPlayer(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (p.getLocation().getBlock().getType() == Material.HEAVY_WEIGHTED_PRESSURE_PLATE &&
                p.hasPermission("lobby.jump")) {
            Vector v = p.getLocation().getDirection().multiply(2.0D).setY(1);
            p.setVelocity(v);
            p.setFallDistance(-500.0F);
            p.playSound(p.getLocation(), Sound.WEATHER_RAIN_ABOVE, 1.0F, 1.0F);
        }
    }
}