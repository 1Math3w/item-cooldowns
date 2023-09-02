package me.math3w.itemcooldowns;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.time.Duration;
import java.time.Instant;

public class CooldownItem {
    private final Material itemType;
    private final Duration cooldown;
    private final String cooldownMessage;
    private final String readyMessage;

    public CooldownItem(Material itemType, Duration cooldown, String cooldownMessage, String readyMessage) {
        this.itemType = itemType;
        this.cooldown = cooldown;
        this.cooldownMessage = cooldownMessage;
        this.readyMessage = readyMessage;
    }

    public CooldownItem(Material itemType, ConfigurationSection config) {
        this(itemType, Duration.ofSeconds(config.getInt("cooldown")), config.getString("cooldown-message"), config.getString("ready-message"));
    }
}
