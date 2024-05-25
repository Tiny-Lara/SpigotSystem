package tiny.lara.spigotsystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import tiny.lara.spigotsystem.lobby.*;
import java.io.File;

public class Main extends JavaPlugin {
    public final static String COLOR = "§b";
    public final static String name = "System";
    public final static String nameK = "ss";
    private static Main instance;
    public static Plugin plugin;
    public final static String prefix = COLOR + "["+name+"] ";
    public final static String prefixWithoutColor = "["+name+"] ";
    public static File file = new File("plugins/"+name, "config.yml");
    public static YamlConfiguration cfg = new YamlConfiguration().loadConfiguration(file);
    @Override
    public void onEnable() {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new JoinListener(), this);
        System.out.println("§2Fly An!");
        //lobby
        Config.setupConfig();
        Locations.setupFiles();
        instance = this;
        plugin = this;
        registerEvents();
        registerCommands();
        setSettingsFromConfig();
    }
    public static Main getInstance() {
        return (Main) instance;
    }
    private void registerCommands() {
        //Main command
        this.getCommand("Spigotlobby").setExecutor(new SimpleLobbyCommand());
        this.getCommand("ss").setExecutor(new SimpleLobbyCommand());
        //Spawn command
        this.getCommand("spawn").setExecutor(new SpawnCommand());
        this.getCommand("stuck").setExecutor(new SpawnCommand());
        System.out.println(prefixWithoutColor + "loaded commands!");
    }
    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new BreakEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlaceEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PVPEvent(), this);
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new QuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new DamageEvent(), this);
        Bukkit.getPluginManager().registerEvents(new RespawnEvent(), this);
        Bukkit.getPluginManager().registerEvents(new HungerEvent(), this);
        Bukkit.getPluginManager().registerEvents(new WeatherEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ChangeWorldEvent(), this);
        System.out.println(prefixWithoutColor + "loaded events!");
    }
    public static void setSettingsFromConfig() {
        //BOOLEANS
        Settings.isTeleportOnJoin = cfg.getBoolean("Config.TeleportOnJoin");
        Settings.isBreakEvent = cfg.getBoolean("Config.DisableBlockBreak");
        Settings.isPlaceEvent = cfg.getBoolean("Config.DisableBlockPlace");
        Settings.isDamageEvent = cfg.getBoolean("Config.DisableDamage");
        Settings.isHitEvent = cfg.getBoolean("Config.DisablePVP");
        Settings.isWelcomeMessage = cfg.getBoolean("Config.WelcomeMessageB");
        Settings.isJoinMessage = cfg.getBoolean("Config.JoinMessageB");
        Settings.isleaveMessage = cfg.getBoolean("Config.LeaveMessageB");
        Settings.isFireWorkOnJoin = cfg.getBoolean("Config.FireworkOnJoin");
        Settings.isTeleportOnRespawn = cfg.getBoolean("Config.TeleportOnRespawn");
        Settings.breakMessage = cfg.getString("Messages.BreakBlocksMessage").replace("&", "§");
        Settings.placeMessage = cfg.getString("Messages.PlaceBlocksMessage").replace("&", "§");
        Settings.hitMessage = cfg.getString("Messages.PlayerHitMessage").replace("&", "§");
        Settings.prefix = cfg.getString("Messages.Prefix").replace("&", "§");
        Settings.leaveMessage = cfg.getString("Messages.LeaveMessage").replace("&", "§");
        Settings.joinMessage = cfg.getString("Messages.JoinMessage").replace("&", "§");
        Settings.welcomeMessage = cfg.getString("Messages.WelcomeMessage").replace("&", "§");
        Settings.teleportToSpawnMessage = cfg.getString("Messages.SpawnTeleportMessage").replace("&", "§");
        try {
            Settings.Spawn = Locations.getLocation("Spawn");
            Settings.lobbyWorldName = Locations.getLocation("Spawn").getWorld().getName();
        } catch (Exception e) {
            System.out.println(prefixWithoutColor + "Failed to init spawn location and world name because spawn is not set!");
            System.out.println(prefixWithoutColor + "Type ingame /" + nameK + " setspawn");
        }

    }
    public void onDisable() {
        System.out.println("§4Fly Aus!");
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