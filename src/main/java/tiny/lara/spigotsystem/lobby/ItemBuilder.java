package tiny.lara.spigotsystem.lobby;

import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {
    Material mati;
    ItemStack i;
    ItemMeta meta;
    public ItemBuilder(Material mat) {
        this.mati = mat;
        this.i = new ItemStack(this.mati);
        this.meta = this.i.getItemMeta();
    }
    public ItemBuilder(ItemStack item) {
        this.i = item;
        this.meta = this.i.getItemMeta();
    }
    public ItemBuilder setDisplay(String name) {
        this.meta.setDisplayName(name);
        return this;
    }
    public ItemBuilder setLore(String... lore) {
        this.meta.setLore(Arrays.asList(lore));
        return this;
    }
    public ItemBuilder setBackID(int a) {
        this.i.setDurability((short)a);
        return this;
    }
    public ItemBuilder setAmount(int amount) {
        this.i.setAmount(amount);
        return this;
    }
    public ItemBuilder setEnchantment(Enchantment e, int staerke) {
        this.meta.addEnchant(e, staerke, true);
        return this;
    }
    public ItemBuilder setAttributs(ItemFlag flag) {
        this.meta.addItemFlags(new ItemFlag[] { flag });
        return this;
    }
    public ItemStack build() {
        this.i.setItemMeta(this.meta);
        return this.i;
    }
}