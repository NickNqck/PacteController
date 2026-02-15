package fr.nicknqck.addon.pacte;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public final class PacteController extends JavaPlugin {

    @Getter
    private static PacteController instance;
    @Getter
    @Setter
    private boolean pacteKaigaku = true, pacteZenItsu = true, pacteSolo = true;
    @Getter
    @Setter
    private UUID configurator = null;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("pactecontroller").setExecutor(new PacteCommand());
        getServer().getPluginManager().registerEvents(new PacteListener(), this);
        new PacteCkecker(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
