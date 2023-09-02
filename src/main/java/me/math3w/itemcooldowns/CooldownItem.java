package me.math3w.itemcooldowns;

import me.math3w.itemcooldowns.utils.Utils;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

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

    public Material getItemType() {
        return itemType;
    }

    public Duration getCooldown() {
        return cooldown;
    }

    public void sendCooldownMessage(Player player, Instant lastUse) {
        Duration remainingDuration = cooldown.minus(Duration.between(lastUse, Instant.now()));
        player.sendMessage(Utils.colorize(cooldownMessage.replaceAll("%cooldown%", String.valueOf(remainingDuration.toSeconds() + 1))));
    }

    public void sendReadyMessage(Player player) {
        player.sendMessage(Utils.colorize(readyMessage));
    }
}
