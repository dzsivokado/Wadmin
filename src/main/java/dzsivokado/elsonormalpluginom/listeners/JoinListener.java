package dzsivokado.elsonormalpluginom.listeners;

import dzsivokado.elsonormalpluginom.Wtroll;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class JoinListener implements Listener {

    private final Wtroll plugin;



    public JoinListener(Wtroll plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(org.bukkit.event.player.PlayerJoinEvent e){

        Player p = e.getPlayer();
        String JoinMSG = this.plugin.getConfig().getString("join_msg");

        p.sendMessage(JoinMSG);
    }
}
