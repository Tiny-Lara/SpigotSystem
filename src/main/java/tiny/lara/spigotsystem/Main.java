package tiny.lara.spigotsystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import tiny.lara.spigotsystem.lobby.CMD_SetSpawn;

import java.util.Objects;

public class Main extends JavaPlugin {
    private static Plugin instance;
    @Override
    public void onEnable() {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new JoinListener(), this);
        System.out.println("§2Fly An!");
        //lobby
        Bukkit.getMessenger().registerOutgoingPluginChannel((Plugin)this, "BungeeCord");
        instance = this;
        loadConfig();
        loadCommands();
        Bukkit.getMessenger().registerOutgoingPluginChannel((Plugin)this, "BungeeCord");
        Bukkit.getConsoleSender().sendMessage(String.valueOf(getPrefix()) + "§eLoad...Events");
        Bukkit.getConsoleSender().sendMessage(String.valueOf(getPrefix()));
    }
    public void onDisable() {
        System.out.println("§4Fly Aus!");
    }
    public void loadConfig() {
        FileConfiguration cfg = getConfig();
        cfg.options().copyDefaults(true);
        saveConfig();
        getConfig().options().header("%pr% = Lobby Prefix \n %p% = Spielername  \n DER DISPLAY IST DER NICK CHAT NUR OHNE : UND %MESSAGE%!!!! \n");
        getConfig().addDefault("Lobby.Prefix", "&7[&6Lobby&7]");
        getConfig().addDefault("Lobby.NoPerm", "%pr% &cDu darfst das nicht!");
        getConfig().addDefault("Lobby.Command.Warp_Message", Boolean.TRUE);
        getConfig().addDefault("Lobby.Command.Warp", "%pr% &7Du bist nun bei &e%WARP%");
        getConfig().addDefault("Lobby.Command.GM_Use", "%pr% &cSyntax: &7/gm <0-3>");
        getConfig().addDefault("Lobby.Command.GM_0", "%pr% &7Dein Gamemode wurde auf &2Survival &7gesetzt!");
        getConfig().addDefault("Lobby.Command.GM_1", "%pr% &7Dein Gamemode wurde auf &2Kreativ &7gesetzt!");
        getConfig().addDefault("Lobby.Command.GM_2", "%pr% &7Dein Gamemode wurde auf &2Adventure &7gesetzt!");
        getConfig().addDefault("Lobby.Command.GM_3", "%pr% &7Dein Gamemode wurde auf &2Spectator &7gesetzt!");
        getConfig().addDefault("Lobby.Command.Lobby", "%pr% &7Lobby was coded by &bMiningMaurice");
        getConfig().addDefault("Lobby.Command.Lobby_reload", "%pr% &aDie Config wurde Reloaded");
        getConfig().addDefault("Lobby.Command.ChatClear", "%pr% &7Der Chat wurde von &c%p% &7geleert!");
        getConfig().addDefault("Lobby.Command.Build_On", "%pr% &aDu bist nun im Build-Mode");
        getConfig().addDefault("Lobby.Command.Build_Off", "%pr% &cDu bist nun nicht mehr im Build-Mode");
        getConfig().addDefault("Lobby.Command.YouTube", "%pr% &aDen &5YT &aRang vergeben wir, wenn du 25000 &5Abonnenten &ahast!");
        getConfig().addDefault("Lobby.Command.Info", "%pr% &eNeu! &bLobbySystem");
        getConfig().addDefault("Lobby.Command.AFK_On", "%pr% Der Spieler &a%p% &7ist nun AFK");
        getConfig().addDefault("Lobby.Command.AFK_Off", "%pr% Der Spieler &c%p% &7ist nun nicht mehr AFK");
        getConfig().addDefault("Lobby.Command.Spawn_wurde_nicht_gesetzt", "%pr% &cDer Spawn wurde noch nicht gesetzt!");
        FileConfiguration fileConfiguration = getConfig();
        reloadConfig();
    }
    public void loadCommands() {
        Objects.requireNonNull(getCommand("setspawn")).setExecutor((CommandExecutor)new CMD_SetSpawn(this));
    }
    public static Main getInstance() {
        return (Main) instance;
    }
    public static Main getPlugin() {
        Main plugin = null;
        return plugin;
    }
    public static String getPrefix() {
        String pr = getPlugin().getConfig().getString("Lobby.Prefix");
        pr = pr.replaceAll("&", "§");
        return pr;
    }
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("fly")) {
                Player player = (Player) sender;
                if (args.length == 0)
                    if (player.hasPermission("Tiny.Fly")) {
                        if (!player.getAllowFlight()) {
                            player.sendMessage(ChatColor.DARK_GREEN + "Fly An!");
                            player.setAllowFlight(true);
                        } else {
                            sender.sendMessage(ChatColor.DARK_RED + "Fly Aus!");
                            player.setAllowFlight(false);
                        }
                    } else {
                        sender.sendMessage(ChatColor.BLUE + "Keine Rechte!");
                    }
                if (args.length == 1)
                    if (player.hasPermission("Tiny.FlySet")) {
                        Player player2 = Bukkit.getPlayer(args[0]);
                        if (player2 == null) {
                            sender.sendMessage(ChatColor.RED + "Player offline");
                            return true;
                        }
                        if (!player2.getAllowFlight()) {
                            sender.sendMessage(ChatColor.DARK_GREEN + "Fly An für " + player2.getName());
                            player2.setAllowFlight(true);
                            player2.sendMessage(ChatColor.DARK_GREEN + "Fly An von " + player.getName());
                        } else {
                            sender.sendMessage(ChatColor.DARK_RED + "Fly Aus für " + player2.getName());
                            player2.setAllowFlight(false);
                            player2.sendMessage(ChatColor.DARK_RED + "Fly Aus von " + player.getName());
                        }
                    } else {
                        sender.sendMessage(ChatColor.BLUE + "Keine Rechte!");
                    }
            }
            if (cmd.getName().equalsIgnoreCase("flyall") &&
                    sender.hasPermission("Tiny.FlySet")) {
                Player[] allplayers = Bukkit.getServer().getOnlinePlayers().toArray(new Player[0]);
                byte b;
                int i;
                Player[] arrayOfPlayer1;
                for (i = (arrayOfPlayer1 = allplayers).length, b = 0; b < i; ) {
                    Player allserver = arrayOfPlayer1[b];
                    allserver.setAllowFlight(true);
                    allserver.sendMessage(ChatColor.AQUA + "Fly An für alle auf dem Server!");
                    b++;
                }
            }
            if (cmd.getName().equalsIgnoreCase("disableflyall") &&
                    sender.hasPermission("Tiny.FlySet")) {
                Player[] allplayers = Bukkit.getServer().getOnlinePlayers().toArray(new Player[0]);
                byte b;
                int i;
                Player[] arrayOfPlayer1;
                for (i = (arrayOfPlayer1 = allplayers).length, b = 0; b < i; ) {
                }
                Player allserver = arrayOfPlayer1[b];
                allserver.setAllowFlight(false);
                allserver.sendMessage(ChatColor.DARK_PURPLE + "Fly Aus für alle auf dem Server!");
            }
        }
        return true;
    }
}