package fr.nicknqck.addon.pacte;

import fr.nicknqck.utils.UpdateChecker;
import org.bukkit.plugin.Plugin;

public class PacteCkecker extends UpdateChecker {

    public PacteCkecker(Plugin plugin, String repo) {
        super(plugin, repo);
    }
}