package tiny.lara.spigotsystem;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TestScoreboard extends ScoreboardBuilder {
    public TestScoreboard(Player player) {
        super(player, ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Gamessucht.com");
    }
    @Override
    public void createScoreboard() {
        setScore(ChatColor.DARK_GRAY.toString(), 3);
        setScore(ChatColor.GRAY + "Rang", 2);
        if(player.hasPermission("Tiny.Owner")) {
            setScore(ChatColor.DARK_AQUA.toString() + "Owner", 1);
        } else if (player.hasPermission("Tiny.Admin")) {
            setScore(ChatColor.DARK_RED.toString() + "Admin", 1);
        } else if (player.hasPermission("Tiny.Dev")) {
            setScore(ChatColor.BLUE.toString() + "Developer", 1);
        } else if (player.hasPermission("Tiny.Mod2")) {
            setScore(ChatColor.RED.toString() + "SrModerator", 1);
        } else if (player.hasPermission("Tiny.Mod1")) {
            setScore(ChatColor.GREEN.toString() + "Moderator", 1);
        } else if (player.hasPermission("Tiny.Sup2")) {
            setScore(ChatColor.BLUE.toString() + "SrSupporter", 1);
        } else if (player.hasPermission("Tiny.Sup1")) {
            setScore(ChatColor.DARK_BLUE.toString() + "Supproter", 1);
        } else if (player.hasPermission("Tiny.Creator")) {
            setScore(ChatColor.DARK_PURPLE.toString() + "Creator", 1);
        } else if (player.hasPermission("Tiny.Builder")) {
            setScore(ChatColor.YELLOW.toString() + "Builder", 1);
        } else if (player.hasPermission("Tiny.Platin")) {
            setScore(ChatColor.AQUA.toString() + "Platin", 1);
        } else if (player.hasPermission("Tiny.Master")) {
            setScore(ChatColor.LIGHT_PURPLE.toString() + "Master", 1);
        } else if (player.hasPermission("Tiny.Gold")) {
            setScore(ChatColor.GOLD.toString() + "Gold", 1);
        } else if (player.hasPermission("Tiny.Spieler")) {
            setScore(ChatColor.DARK_GRAY.toString() + "Spieler", 1);
        }
        setScore(ChatColor.GRAY.toString(), 0);
    }
    @Override
    public void update() {
            player.kickPlayer("&3Reconnect!");
    }
}