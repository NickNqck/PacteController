package fr.nicknqck.addon.pacte;

import fr.nicknqck.Main;
import fr.nicknqck.roles.desc.AllDesc;
import fr.nicknqck.utils.rank.ChatRank;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PacteCommand implements CommandExecutor {

    public PacteCommand() {
        PacteController.getInstance().getCommand("pactecontroller").setTabCompleter(new PacteCompletor());
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!ChatRank.isHost(commandSender)) {
            commandSender.sendMessage("§cSeul un administrateur peut faire ceci !");
            return true;
        }
        if (strings.length >= 1) {
            if (strings[0].equalsIgnoreCase("status")) {
                commandSender.sendMessage(new String[]{
                        AllDesc.bar,
                        "§fVoici l'état actuel des pactes de§e Jigoro V2",
                        "",
                        "§8 -§f Pacte§e Solo§f: "+(PacteController.getInstance().isPacteSolo() ? "§aActiver" : "§cDésactiver"),
                        "§8 -§f Pacte avec§c Kaigaku§f: "+(PacteController.getInstance().isPacteKaigaku() ? "§aActiver" : "§cDésactiver"),
                        "§8 -§f Pacte avec§a Zen'Itsu§f: "+(PacteController.getInstance().isPacteZenItsu() ? "§aActiver" : "§cDésactiver"),
                        "",
                        AllDesc.bar
                });
                return true;
            }
        }
        if (commandSender instanceof Player) {
            if (PacteController.getInstance().getConfigurator() == null) {
                Main.getInstance().sendMessageToHosts("§c"+commandSender.getName()+"§7 commence à configurer les pactes");
                PacteController.getInstance().setConfigurator(((Player) commandSender).getUniqueId());
                new PacteInventory().open((Player) commandSender);
            } else {
                commandSender.sendMessage("§cImpossible, quelqu'un configure déjà les pactes.");
            }
            return true;
        }
        return false;
    }
}