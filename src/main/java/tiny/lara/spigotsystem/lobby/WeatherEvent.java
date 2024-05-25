package tiny.lara.spigotsystem.lobby;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import tiny.lara.spigotsystem.Main;

public class WeatherEvent implements Listener {
	@EventHandler
	public void onWeather(WeatherChangeEvent e) {
		if(Main.cfg.getBoolean("Config.DisableWeatherChange")) {
		e.setCancelled(true);
		}
	}
}