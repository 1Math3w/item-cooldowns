package me.math3w.itemcooldowns;

import me.math3w.itemcooldowns.configurations.ItemsConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemCooldowns extends JavaPlugin {
    private ItemsConfig itemsConfig;

    @Override
    public void onEnable() {
        itemsConfig = new ItemsConfig(this);

        Bukkit.getPluginManager().registerEvents(new ItemListeners(this), this);
    }

    public ItemsConfig getItemsConfig() {
        return itemsConfig;
    }
}
