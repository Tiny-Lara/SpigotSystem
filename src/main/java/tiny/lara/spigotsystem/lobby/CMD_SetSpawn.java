package tiny.lara.spigotsystem.lobby;

import java.io.File;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import tiny.lara.spigotsystem.Main;

public class CMD_SetSpawn implements CommandExecutor {
    Main plugin;
    public CMD_SetSpawn(Main main) {
        this.plugin = main;
    }
    public static File file = new File("plugins/System", "location.yml");
    public static FileConfiguration cfg = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;
        if (p.hasPermission("lobby.setspawn")) {
            if (args.length == 0) {
                p.sendMessage(String.valueOf(this.plugin.getConfig().getString("Prefix").replace("&", "§")) + "§7Verwendung§8: §8/§csetspawn §7<§fName§7>");
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("spawn")) {
                    Location loc = p.getLocation();
                    double x = loc.getX();
                    double y = loc.getY();
                    double z = loc.getZ();
                    float yaw = loc.getYaw();
                    float pitch = loc.getPitch();
                    String w = loc.getWorld().getName();
                    cfg.set("spawn.spawn.X", Double.valueOf(x));
                    cfg.set("spawn.spawn.Y", Double.valueOf(y));
                    cfg.set("spawn.spawn.Z", Double.valueOf(z));
                    cfg.set("spawn.spawn.Yaw", Float.valueOf(yaw));
                    cfg.set("spawn.spawn.Pitch", Float.valueOf(pitch));
                    cfg.set("spawn.spawn.World", w);
                    p.sendMessage(String.valueOf(this.plugin.getConfig().getString("Prefix").replace("&", "§")) + "§7Du hast §aSPAWN §7gesetzt");
                    try {
                        cfg.save(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            p.sendMessage(String.valueOf(this.plugin.getConfig().getString("Prefix").replace("&", "§")) + "§7Du hast §ckeine §7Berechtigung für diesen Befehl");
        }
        return false;
    }
}