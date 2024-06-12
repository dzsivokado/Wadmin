package dzsivokado.elsonormalpluginom.commands;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TesztCommand implements CommandExecutor {






    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {

            Player p = (Player)sender;

            World world = p.getWorld();
            Location loc = p.getLocation();
            Double x = loc.getX();
            Double y = loc.getY();
            Double z = loc.getZ();
            float yaw = loc.getYaw() - 180;
            float pitch = 180;

// change data here


                Location goal = new Location(world, x, y, z, yaw, pitch);
                p.teleport(goal);

        }
        return true;
    }



}