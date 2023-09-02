package me.math3w.itemcooldowns;

import me.math3w.itemcooldowns.configurations.ItemsConfig;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemCooldowns extends JavaPlugin {
    private ItemsConfig itemsConfig;

    @Override
    public void onEnable() {
        itemsConfig = new ItemsConfig(this);
    }

    public ItemsConfig getItemsConfig() {
        return itemsConfig;
    }
}
