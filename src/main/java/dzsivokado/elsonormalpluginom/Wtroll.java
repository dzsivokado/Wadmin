package dzsivokado.elsonormalpluginom;

import dzsivokado.elsonormalpluginom.commands.TesztCommand;
import dzsivokado.elsonormalpluginom.commands.WadminCMD;
import dzsivokado.elsonormalpluginom.listeners.JoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Wtroll extends JavaPlugin {
   public void onEnable() {
      this.saveDefaultConfig();
      this.getServer().getConsoleSender().sendMessage("§6=========================");
      this.getServer().getConsoleSender().sendMessage(" ");
      this.getServer().getConsoleSender().sendMessage("§1the Wtroll plugin started");
      this.getServer().getConsoleSender().sendMessage(" ");
      this.getServer().getConsoleSender().sendMessage("§6=========================");
      this.getCommand("wtroll").setExecutor(new WadminCMD(this));
      this.getCommand("teszt").setExecutor(new TesztCommand(this));
      //
      getServer().getPluginManager().registerEvents(new JoinListener(this), this);
      //
      new UpdateChecker(this, 117171).getVersion(version -> {
         if (this.getDescription().getVersion().equals(version)) {
            getLogger().info("There is not a new update available.");
         } else {
            getLogger().info("There is a new update available. Download here: https://www.spigotmc.org/resources/wtroll.117171/");
         }
      });

   }

   public void onDisable() {
      this.getServer().getConsoleSender().sendMessage("§6=======================");
      this.getServer().getConsoleSender().sendMessage(" ");
      this.getServer().getConsoleSender().sendMessage("§1A Wtroll plugin stopped");
      this.getServer().getConsoleSender().sendMessage(" ");
      this.getServer().getConsoleSender().sendMessage("§6=======================");
   }


}