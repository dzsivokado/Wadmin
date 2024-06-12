package dzsivokado.elsonormalpluginom.commands;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TesztCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (sender instanceof Player) {

            Player p = (Player) sender;

            ProtocolManager pm = ProtocolLibrary.getProtocolManager();

            PacketContainer packet = new PacketContainer(PacketType.Play.Server.GAME_STATE_CHANGE);
            packet.getGameStateIDs().write(0, 5);
            packet.getFloat().write(0, 0f);


                pm.sendServerPacket(p, packet);
                




        }


        return true;
    }

}


