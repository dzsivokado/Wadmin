package dzsivokado.elsonormalpluginom.commands;

import dzsivokado.elsonormalpluginom.Wadmin;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.Nullable;

public class WadminCMD implements CommandExecutor, TabExecutor {
   private final Wadmin plugin;

   public WadminCMD(Wadmin plugin) {
      this.plugin = plugin;
   }

   public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
      String nonSpecifydPlayer = this.plugin.getConfig().getString("non_specified_player");
      String playerNotFound = this.plugin.getConfig().getString("player_not_found");
      String tntmsg = this.plugin.getConfig().getString("tnt_msg");
      String clearmsg = this.plugin.getConfig().getString("clear_msg");
      String killmsg = this.plugin.getConfig().getString("kill_msg");
      String creepermsg = this.plugin.getConfig().getString("creeper_msg");
      String airmsg = this.plugin.getConfig().getString("air_msg");
      String explodemsg = this.plugin.getConfig().getString("explode_msg");
      Integer defatnt = this.plugin.getConfig().getInt("default_tnt");
      Integer defacreeper = this.plugin.getConfig().getInt("default_creeper");
      if (sender instanceof Player) {
         Player p = (Player)sender;
         if (args.length == 0) {
            p.sendMessage("§6/wadmin creeper <player> <amount>");
            p.sendMessage("§6/wadmin tnt <player> <amount>");
            p.sendMessage("§6/wadmin clear <player>");
            p.sendMessage("§6/wadmin kill <player>");
            p.sendMessage("§6/wadmin explode <player>");
            p.sendMessage("§6/wadmin air <player>");
         } else if (args.length >= 1) {
            String playerName;
            Player target;
            World w;
            Location l;
            int i;

            Integer szam;
            if (args[0].equals("creeper")) {
               if (args.length == 1) {
                  p.sendMessage(nonSpecifydPlayer);
               } else if (args.length == 2) {
                  playerName = args[1];
                  target = Bukkit.getServer().getPlayerExact(playerName);
                  if (target == null) {
                     p.sendMessage(playerNotFound);
                  } else {
                     w = target.getWorld();
                     l = target.getLocation();
                     creepermsg = creepermsg.replace("%target%", target.getPlayer().getDisplayName());

                     for(i = 0; i < defacreeper; ++i) {
                        w.spawnEntity(l, EntityType.CREEPER);
                     }

                     p.sendMessage(creepermsg);
                  }
               } else if (args.length == 3) {
                  playerName = args[1];
                  target = Bukkit.getServer().getPlayerExact(playerName);
                  if (target == null) {
                     p.sendMessage(playerNotFound);
                  } else {
                     w = target.getWorld();
                     l = target.getLocation();
                     szam = Integer.valueOf(args[2]);
                     creepermsg = creepermsg.replace("%target%", target.getPlayer().getDisplayName());

                     for(i = 0; i < szam; ++i) {
                        w.spawnEntity(l, EntityType.CREEPER);
                     }

                     p.sendMessage(creepermsg);
                  }
               }
            } else if (args[0].equals("tnt")) {
               if (args.length == 1) {
                  p.sendMessage(nonSpecifydPlayer);
               } else if (args.length == 2) {
                  playerName = args[1];
                  target = Bukkit.getServer().getPlayerExact(playerName);
                  if (target == null) {
                     p.sendMessage(playerNotFound);
                  } else {
                     w = target.getWorld();
                     l = target.getLocation();
                     tntmsg = tntmsg.replace("%target%", target.getPlayer().getDisplayName());

                     for(i = 0; i < defatnt; ++i) {
                        w.spawnEntity(l, EntityType.PRIMED_TNT);
                     }

                     p.sendMessage(tntmsg);
                  }
               } else if (args.length == 3) {
                  playerName = args[1];
                  target = Bukkit.getServer().getPlayerExact(playerName);
                  if (target == null) {
                     p.sendMessage(playerNotFound);
                  } else {
                     w = target.getWorld();
                     l = target.getLocation();
                     szam = Integer.valueOf(args[2]);
                     tntmsg = tntmsg.replace("%target%", target.getPlayer().getDisplayName());

                     for(i = 0; i < szam; ++i) {
                        w.spawnEntity(l, EntityType.PRIMED_TNT);
                     }

                     p.sendMessage(tntmsg);
                  }
               }
            } else {
               PlayerInventory inv;
               if (args[0].equals("clear")) {
                  if (args.length == 1) {
                     p.sendMessage(nonSpecifydPlayer);
                  } else if (args.length == 2) {
                     playerName = args[1];
                     target = Bukkit.getServer().getPlayerExact(playerName);
                     if (target == null) {
                        p.sendMessage(playerNotFound);
                     } else {
                        inv = target.getInventory();
                        ItemStack dirt = new ItemStack(Material.DIRT, 2304);
                        clearmsg = clearmsg.replace("%target%", target.getPlayer().getDisplayName());
                        inv.clear();
                        inv.setItem(0, dirt);
                        p.sendMessage(clearmsg);
                     }
                  }
               } else if (args[0].equals("kill")) {
                  if (args.length == 1) {
                     p.sendMessage(nonSpecifydPlayer);
                  } else if (args.length == 2) {
                     playerName = args[1];
                     target = Bukkit.getServer().getPlayerExact(playerName);
                     if (target == null) {
                        p.sendMessage(playerNotFound);
                     } else {
                        inv = target.getInventory();
                        killmsg = killmsg.replace("%target%", target.getPlayer().getDisplayName());
                        inv.clear();
                        target.setHealth(0.0D);
                        p.sendMessage(killmsg);
                     }
                  }
               } else {

                  if (args[0].equals("explode")) {
                     if (args.length == 1) {
                        p.sendMessage(nonSpecifydPlayer);
                     } else if (args.length == 2) {
                        playerName = args[1];
                        target = Bukkit.getServer().getPlayerExact(playerName);
                        if (target == null) {
                           p.sendMessage(playerNotFound);
                        } else {
                           l = target.getLocation();
                           w = l.getWorld();
                           explodemsg = explodemsg.replace("%target%", target.getPlayer().getDisplayName());
                           l.createExplosion(5.0F, false);
                           target.setHealth(0.0D);
                           target.playSound(l, Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
                           target.getWorld().strikeLightningEffect(l);
                           target.playEffect(l, Effect.SMOKE, 0);
                           p.sendMessage(explodemsg);
                        }
                     }
                  } else if (args[0].equals("air")) {
                     if (args.length == 1) {
                        p.sendMessage(nonSpecifydPlayer);
                     } else if (args.length == 2) {
                        playerName = args[1];
                        target = Bukkit.getServer().getPlayerExact(playerName);
                        if (target == null) {
                           p.sendMessage(playerNotFound);
                        } else {
                           l = target.getLocation();
                           w = l.getWorld();
                           Location playerLocation = target.getLocation();
                           Inventory inventory = target.getInventory();
                           ItemStack viz = new ItemStack(Material.WATER_BUCKET, 1);
                           airmsg = airmsg.replace("%target%", target.getPlayer().getDisplayName());
                           playerLocation.setY(playerLocation.getY() + 300.0D);
                           target.teleport(playerLocation);
                           p.sendMessage(airmsg);
                           inventory.setItem(4, viz);
                           p.setGameMode(GameMode.SURVIVAL);
                        }
                     }
                  }
               }
            }
         }
      }

      return true;
   }

   @Nullable
   public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
      if (args.length == 1) {
         return Arrays.asList("creeper", "tnt", "clear", "kill", "explode", "air");
      } else if (args.length != 2) {
         if (args[0].equals("creeper")) {
            return Arrays.asList("1", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70", "75", "80", "85", "90", "95", "100");
         } else {
            return (List)(args[0].equals("tnt") ? Arrays.asList("1", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70", "75", "80", "85", "90", "95", "100") : new ArrayList());
         }
      } else {
         List<String> players = new ArrayList();
         Iterator var6 = Bukkit.getOnlinePlayers().iterator();

         while(var6.hasNext()) {
            Player player = (Player)var6.next();
            players.add(player.getName());
         }

         return players;
      }
   }
}
