package tiny.lara.spigotsystem.lobby;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {
    public static ItemStack createItem(Material material, int subid, String displayname) {
        ItemStack item = new ItemStack(material, 1, (short)subid);
        ItemMeta mitem = item.getItemMeta();
        mitem.setDisplayName(displayname);
        item.setItemMeta(mitem);
        return item;
    }
}