package tiny.lara.spigotsystem.lobby;

import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;

public class LanguageManager {
    public File language = new File("plugins/System/Language/German");

    public YamlConfiguration cfg = YamlConfiguration.loadConfiguration(this.language);
}