package fr.nicknqck.addon.pacte;

import fr.nicknqck.events.custom.roles.ds.JigoroV2ChoosePacteEvent;
import lombok.NonNull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PacteListener implements Listener {

    @EventHandler
    private void onPacte(@NonNull final JigoroV2ChoosePacteEvent event) {
        if (event.getPacte().equals(JigoroV2ChoosePacteEvent.Pacte.SOLO) && !PacteController.getInstance().isPacteSolo()) {
            event.setCancelled(true);
        }
        if (event.getPacte().equals(JigoroV2ChoosePacteEvent.Pacte.KAIGAKU) && !PacteController.getInstance().isPacteKaigaku()) {
            event.setCancelled(true);
        }
        if (event.getPacte().equals(JigoroV2ChoosePacteEvent.Pacte.ZENITSU) && !PacteController.getInstance().isPacteZenItsu()) {
            event.setCancelled(true);
        }
    }

}