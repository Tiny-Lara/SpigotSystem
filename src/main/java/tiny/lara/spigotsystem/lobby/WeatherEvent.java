package tiny.lara.spigotsystem.lobby;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherEvent implements Listener {
    @EventHandler
    public void onWeather(WeatherChangeEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void onFood(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }
}