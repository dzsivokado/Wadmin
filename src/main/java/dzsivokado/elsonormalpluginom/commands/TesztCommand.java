package dzsivokado.elsonormalpluginom.commands;

import dzsivokado.elsonormalpluginom.Wadmin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class TesztCommand implements CommandExecutor, Listener {

   private final Wadmin plugin;

   public TesztCommand(Wadmin plugin) {
      this.plugin = plugin;
   }
   private String webhookURL;

   public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
      if (sender instanceof Player) {
         Player player = (Player) sender;

         if (player.getWalkSpeed() == 0.009f) {
             player.setWalkSpeed(0.19f);
             player.sendMessage("ki");
         }else {
            player.setWalkSpeed(0.009f);
            player.setFoodLevel(-1);
            player.sendMessage("be");
         }
         return true;
      }

      return true;
   }





}
