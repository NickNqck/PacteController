package fr.nicknqck.addon.pacte;

import fr.nicknqck.Main;
import fr.nicknqck.items.GUIItems;
import fr.nicknqck.utils.fastinv.FastInv;
import fr.nicknqck.utils.itembuilder.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class PacteInventory extends FastInv {

    public PacteInventory() {
        super(27, "§fConfiguration des pactes");
        setItems(getBorders(), new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability(7).setName(" ").toItemStack());

        setItem(11, new ItemBuilder(GUIItems.getJigoroPacte1()).setName("§fPacte§e Solitaire").setLore("§fCe pacte est actuellement: "+(PacteController.getInstance().isPacteSolo() ? "§aActivé" : "§cDésactiver")).toItemStack(), event -> {
            event.setCancelled(true);
            if (PacteController.getInstance().getConfigurator() == null) {
                event.getWhoClicked().sendMessage("§cVous ne configurez déjà plus les pactes !");
                event.getWhoClicked().closeInventory();
                return;
            }
            if (!PacteController.getInstance().getConfigurator().equals(event.getWhoClicked().getUniqueId())) {
                event.getWhoClicked().sendMessage("§cVous ne configurez pas les pactes !");
                event.getWhoClicked().closeInventory();
                return;
            }
            if (PacteController.getInstance().isPacteSolo()) {
                Main.getInstance().sendMessageToHosts("§c"+event.getWhoClicked().getName()+"§7 a définie le "+event.getCurrentItem().getItemMeta().getDisplayName()+"§7 sur§c désactiver§7.");
                PacteController.getInstance().setPacteSolo(false);
            } else {
                Main.getInstance().sendMessageToHosts("§c"+event.getWhoClicked().getName()+"§7 a définie le "+event.getCurrentItem().getItemMeta().getDisplayName()+"§7 sur§a activer§7.");
                PacteController.getInstance().setPacteSolo(true);
            }
            event.getWhoClicked().setMetadata("notdef", new FixedMetadataValue(PacteController.getInstance(), "rien"));
            new PacteInventory().open((Player) event.getWhoClicked());
        });

        setItem(13, new ItemBuilder(GUIItems.getJigoroPacte2()).setName("§fPacte avec§c Kaigaku").setLore("§fCe pacte est actuellement: "+(PacteController.getInstance().isPacteKaigaku() ? "§aActivé" : "§cDésactiver")).toItemStack(), event -> {
            event.setCancelled(true);
            if (PacteController.getInstance().getConfigurator() == null) {
                event.getWhoClicked().sendMessage("§cVous ne configurez déjà plus les pactes !");
                event.getWhoClicked().closeInventory();
                return;
            }
            if (!PacteController.getInstance().getConfigurator().equals(event.getWhoClicked().getUniqueId())) {
                event.getWhoClicked().sendMessage("§cVous ne configurez pas les pactes !");
                event.getWhoClicked().closeInventory();
                return;
            }
            if (PacteController.getInstance().isPacteKaigaku()) {
                Main.getInstance().sendMessageToHosts("§c"+event.getWhoClicked().getName()+"§7 a définie le "+event.getCurrentItem().getItemMeta().getDisplayName()+"§7 sur§c désactiver§7.");
                PacteController.getInstance().setPacteKaigaku(false);
            } else {
                Main.getInstance().sendMessageToHosts("§c"+event.getWhoClicked().getName()+"§7 a définie le "+event.getCurrentItem().getItemMeta().getDisplayName()+"§7 sur§a activer§7.");
                PacteController.getInstance().setPacteKaigaku(true);
            }
            event.getWhoClicked().setMetadata("notdef", new FixedMetadataValue(PacteController.getInstance(), "rien"));
            new PacteInventory().open((Player) event.getWhoClicked());
        });

        setItem(15, new ItemBuilder(GUIItems.getJigoroPacte3()).setName("§fPacte avec§a Zen'Itsu").setLore("§fCe pacte est actuellement: "+(PacteController.getInstance().isPacteZenItsu() ? "§aActivé" : "§cDésactiver")).toItemStack(), event -> {
            event.setCancelled(true);
            if (PacteController.getInstance().getConfigurator() == null) {
                event.getWhoClicked().sendMessage("§cVous ne configurez déjà plus les pactes !");
                event.getWhoClicked().closeInventory();
                return;
            }
            if (!PacteController.getInstance().getConfigurator().equals(event.getWhoClicked().getUniqueId())) {
                event.getWhoClicked().sendMessage("§cVous ne configurez pas les pactes !");
                event.getWhoClicked().closeInventory();
                return;
            }
            if (PacteController.getInstance().isPacteZenItsu()) {
                Main.getInstance().sendMessageToHosts("§c"+event.getWhoClicked().getName()+"§7 a définie le "+event.getCurrentItem().getItemMeta().getDisplayName()+"§7 sur§c désactiver§7.");
                PacteController.getInstance().setPacteZenItsu(false);
            } else {
                Main.getInstance().sendMessageToHosts("§c"+event.getWhoClicked().getName()+"§7 a définie le "+event.getCurrentItem().getItemMeta().getDisplayName()+"§7 sur§a activer§7.");
                PacteController.getInstance().setPacteZenItsu(true);
            }
            event.getWhoClicked().setMetadata("notdef", new FixedMetadataValue(PacteController.getInstance(), "rien"));
            new PacteInventory().open((Player) event.getWhoClicked());
        });
    }

    @Override
    protected void onClose(InventoryCloseEvent event) {
        super.onClose(event);
        if (PacteController.getInstance().getConfigurator() != null) {
            if (event.getPlayer().getUniqueId().equals(PacteController.getInstance().getConfigurator())) {
                if (event.getPlayer().hasMetadata("notdef")) {
                    return;
                }
                Main.getInstance().sendMessageToHosts("§c"+event.getPlayer().getName()+"§7 a arrêter de configurer les pactes.");
                PacteController.getInstance().setConfigurator(null);
            }
        }
    }

    @Override
    protected void onOpen(InventoryOpenEvent event) {
        super.onOpen(event);
        if (event.getPlayer().hasMetadata("notdef")) {
            event.getPlayer().removeMetadata("notdef", PacteController.getInstance());
        }
    }
}
