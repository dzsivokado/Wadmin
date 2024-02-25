package dzsivokado.elsonormalpluginom;

import dzsivokado.elsonormalpluginom.commands.WadminCMD;
import org.bukkit.plugin.java.JavaPlugin;

public final class Wadmin extends JavaPlugin {
   public void onEnable() {
      this.saveDefaultConfig();
      this.getServer().getConsoleSender().sendMessage("§6=========================");
      this.getServer().getConsoleSender().sendMessage(" ");
      this.getServer().getConsoleSender().sendMessage("§1the Wadmin plugin started");
      this.getServer().getConsoleSender().sendMessage(" ");
      this.getServer().getConsoleSender().sendMessage("§6=========================");
      this.getCommand("wadmin").setExecutor(new WadminCMD(this));
      //this.getCommand("teszt").setExecutor(new TesztCommand(this));
   }

   public void onDisable() {
      this.getServer().getConsoleSender().sendMessage("§6=======================");
      this.getServer().getConsoleSender().sendMessage(" ");
      this.getServer().getConsoleSender().sendMessage("§1A Wadmin plugin stopped");
      this.getServer().getConsoleSender().sendMessage(" ");
      this.getServer().getConsoleSender().sendMessage("§6=======================");
   }
}
