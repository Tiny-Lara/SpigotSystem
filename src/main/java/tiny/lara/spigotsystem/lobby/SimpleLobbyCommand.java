package tiny.lara.spigotsystem.lobby;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tiny.lara.spigotsystem.Main;

public class SimpleLobbyCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			/*If player has permsissions*/if (p.hasPermission(Main.name.toLowerCase() + ".admin") || p.hasPermission(Main.prefixWithoutColor.toLowerCase() + ".cmd")) {
			if(args.length == 0) {
            sendCMDListAdmin(p);
			} else if (args.length == 1) {
				switch(args[0])
				{
				case "setspawn":
					Settings.Spawn = p.getLocation();
					Settings.lobbyWorldName = p.getLocation().getWorld().getName();
					Locations.setLocation("Spawn", p.getLocation());
					Locations.saveLocations();
					Utils.reloadConfig();
					p.sendMessage("§aLobby spawnpoint set!");	
					break;
				case "reload":
					Utils.reloadConfigCMD(p);
					break;
				case "s":
					teleportToSpawn(p);
					break;
				default:
		            sendCMDListAdmin(p);
		            break;
				}
			} else {
	            sendCMDListAdmin(p);
			}

			} else {
				p.sendMessage(Settings.prefix+"§cNo permission!");
			}
		} else {
			if (args.length == 1) {
			switch(args[0])
			{
			case "reload":
				Utils.reloadConfigConsole();
			break;
			default:
				org.bukkit.Bukkit.getServer().getConsoleSender().sendMessage(Settings.prefix + "§cCommand not found ore you cant use it because you are not a player!");
			break;
			}
			}
		}
		return false;
	}
    void sendCMDList(Player player) {
    	player.sendMessage(Main.prefix + "§f----Commands----");
		player.sendMessage(Main.prefix + "§6/" + Main.nameK.toLowerCase() + " spawn");
		player.sendMessage(Main.prefix + "§f----------------");
    }
    void sendCMDListAdmin(Player player) {
    	player.sendMessage(Main.prefix + "§f----Commands----");
		player.sendMessage(Main.prefix + "§6/" + Main.nameK.toLowerCase() + " spawn");
		player.sendMessage(Main.prefix + "§6/" + Main.nameK.toLowerCase() + " reload");
		player.sendMessage(Main.prefix + "§6/" + Main.nameK.toLowerCase() + " setspawn");
		player.sendMessage(Main.prefix + "§f----------------");
    }
    String booleanToString(boolean bool) {
    	if(bool) {
    		return " = §aEnabled ✔";
    	} else {
    		return " = §cDisabled ✘";
    	}
    }
    public void teleportToSpawn(Player player) {
    	if(Settings.Spawn == null) {
    		if (player.hasPermission(Main.prefixWithoutColor.toLowerCase() + ".admin") || player.hasPermission(Main.prefixWithoutColor.toLowerCase() + ".cmd")) {
        		player.sendMessage("§cType /sl setspawn to set the spawn point!");
    		 } else {
        		player.sendMessage("§cNo spawn is set!");
    	     }
           return;
    	}
    	player.teleport(Settings.Spawn);
		player.sendMessage(Settings.teleportToSpawnMessage);
    }
    public static void saveSettingsInConfig() {
    	//TODO save settings
    }
    public static void resetSettings() {
    	//TODO set settings to the default values
    	saveSettingsInConfig();
    }
}
