package dzsivokado.elsonormalpluginom.commands;

import dzsivokado.elsonormalpluginom.apis.DiscordWebhook;
import java.io.IOException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TesztCommand implements CommandExecutor {
   private String webhookURL = "https://discord.com/api/webhooks/1209199723768975370/N7GRFu8JKl3oRXd08GODAXQXeWD4dfxJXsWdhqMOoUmXwx-QCdwhB7KiSVQXo6nO4oiy";

   public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
      if (sender instanceof Player) {
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
