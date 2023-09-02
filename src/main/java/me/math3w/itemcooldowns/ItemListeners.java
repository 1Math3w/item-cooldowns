package me.math3w.itemcooldowns;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ItemListeners implements Listener {
    private final ItemCooldowns itemCooldowns;
    private final Map<UUID, Map<Material, Instant>> itemLastUses = new HashMap<>();

    public ItemListeners(ItemCooldowns itemCooldowns) {
        this.itemCooldowns = itemCooldowns;
    }

    @EventHandler
    public void onItemInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null) return;

        CooldownItem cooldownItem = itemCooldowns.getItemsConfig().getCooldownItem(item.getType());
        if (cooldownItem == null) return;

        UUID uniqueId = event.getPlayer().getUniqueId();

        itemLastUses.putIfAbsent(uniqueId, new HashMap<>());

        Map<Material, Instant> playerItemLastUses = itemLastUses.get(uniqueId);
        Instant itemLastUse = playerItemLastUses.get(item.getType());

        if (itemLastUse != null && Instant.now().minus(cooldownItem.getCooldown()).isBefore(itemLastUse)) {
            cooldownItem.sendCooldownMessage(event.getPlayer(), itemLastUse);
            event.setCancelled(true);
            return;
        }

        playerItemLastUses.put(item.getType(), Instant.now());
        itemLastUses.put(uniqueId, playerItemLastUses);
        Bukkit.getScheduler().runTaskLater(itemCooldowns, () -> cooldownItem.sendReadyMessage(event.getPlayer()), cooldownItem.getCooldown().toMillis() / 50);
    }
}
