package tiny.lara.spigotsystem.lobby;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tiny.lara.spigotsystem.Main;

public class Join implements Listener {
    Main plugin;
    public Join(Main main) {
        this.plugin = main;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (this.plugin.getConfig().getBoolean("Join.TeleportToSpawn")) {
            Location loc = p.getLocation();
            loc.setX(CMD_SetSpawn.cfg.getDouble("spawn.spawn.X"));
            loc.setY(CMD_SetSpawn.cfg.getDouble("spawn.spawn.Y"));
            loc.setZ(CMD_SetSpawn.cfg.getDouble("spawn.spawn.Z"));
            double yaw = CMD_SetSpawn.cfg.getDouble("spawn.spawn.Yaw");
            double pitch = CMD_SetSpawn.cfg.getDouble("spawn.spawn.Pitch");
            loc.setYaw((float)yaw);
            loc.setPitch((float)pitch);
            World welt = Bukkit.getWorld(CMD_SetSpawn.cfg.getString("spawn.spawn.World"));
            loc.setWorld(welt);
            p.teleport(loc);
        }
        p.setHealth(20.0D);
        p.setFoodLevel(20);
        p.setGameMode(GameMode.ADVENTURE);
    }
}