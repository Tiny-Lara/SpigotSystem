package tiny.lara.spigotsystem.lobby;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import tiny.lara.spigotsystem.Main;

public class HungerEvent  implements Listener {

	@EventHandler
	public void onFood(FoodLevelChangeEvent e) {
		if(Main.cfg.getBoolean("Config.DisableHunger")) {
		e.setCancelled(true);
		}
	}
}