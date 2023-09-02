package me.math3w.itemcooldowns.configurations;

import me.math3w.itemcooldowns.CooldownItem;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public class ItemsConfig extends CustomConfig {

    public ItemsConfig(JavaPlugin plugin) {
        super(plugin, "items");
    }

    @Override
    protected void addDefaults() {
        addDefault("ender_pearl.cooldown", 10);
        addDefault("ender_pearl.cooldown-message", "&7You have to wait %cooldown%s to use ender pearl again");
        addDefault("ender_pearl.ready-message", "&aYou can use ender pearl again");
    }

    public CooldownItem getCooldownItem(Material itemType) {
        ConfigurationSection itemSection = getConfig().getConfigurationSection(itemType.name().toLowerCase());
        if (itemSection == null) return null;

        return new CooldownItem(itemType, itemSection);
    }
}
