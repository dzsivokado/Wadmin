package dzsivokado.elsonormalpluginom;

import dzsivokado.elsonormalpluginom.commands.WadminCMD;
import dzsivokado.elsonormalpluginom.listeners.JoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Wadmin extends JavaPlugin {
   public void onEnable() {
      this.saveDefaultConfig();
      this.getServer().getConsoleSender().sendMessage("§6=========================");
      this.getServer().getConsoleSender().sendMessage(" ");
      this.getServer().getConsoleSender().sendMessage("§1the Wtroll plugin started");
      this.getServer().getConsoleSender().sendMessage(" ");
      this.getServer().getConsoleSender().sendMessage("§6=========================");
      this.getCommand("wtroll").setExecutor(new WadminCMD(this));
      //this.getCommand("teszt").setExecutor(new TesztCommand());
      //
      getServer().getPluginManager().registerEvents(new JoinListener(this), this);
      //


   }

   public void onDisable() {
      this.getServer().getConsoleSender().sendMessage("§6=======================");
      this.getServer().getConsoleSender().sendMessage(" ");
      this.getServer().getConsoleSender().sendMessage("§1A Wtroll plugin stopped");
      this.getServer().getConsoleSender().sendMessage(" ");
      this.getServer().getConsoleSender().sendMessage("§6=======================");
   }


}
