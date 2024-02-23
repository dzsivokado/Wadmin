package dzsivokado.elsonormalpluginom.commands;

import dzsivokado.elsonormalpluginom.Wadmin;
import dzsivokado.elsonormalpluginom.apis.DiscordWebhook;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class TesztCommand implements CommandExecutor {

   private final Wadmin plugin;

   public TesztCommand(Wadmin plugin) {
      this.plugin = plugin;
   }
   private String webhookURL;

   public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
      if (sender instanceof Player) {
         this.webhookURL = this.plugin.getConfig().getString("webhook");
         
         DiscordWebhook webhook = new DiscordWebhook(this.webhookURL);
         webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription("lol"));

         try {
            webhook.execute();
         } catch (IOException var7) {
            System.out.println("stfu");
         }

      }

      return true;
   }
}
