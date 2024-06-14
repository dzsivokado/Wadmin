package dzsivokado.elsonormalpluginom;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Functions {

    public static void sendersendmsg(CommandSender sender, String msg){
        try {
            if (sender instanceof Player){
                sender.sendMessage(msg);
            }else{
                Bukkit.getServer().getLogger().info(msg);
            }
        }catch (Exception e){
            Bukkit.getServer().getLogger().info("addsasdasasdasdasasd");
        }
    }


}
