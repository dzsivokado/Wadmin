package dzsivokado.elsonormalpluginom.commands;

import dzsivokado.elsonormalpluginom.Wtroll;
import dzsivokado.elsonormalpluginom.apis.DiscordWebhook;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class WadminCMD implements CommandExecutor, TabExecutor {
   private final Wtroll plugin;
   private String webhookURL;

   public WadminCMD(Wtroll plugin) {
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
      String slowmsgon = this.plugin.getConfig().getString("slow_msg_on");
      String slowmsgoff = this.plugin.getConfig().getString("slow_msg_off");
      Integer defatnt = this.plugin.getConfig().getInt("default_tnt");
      Integer defacreeper = this.plugin.getConfig().getInt("default_creeper");
      this.webhookURL = this.plugin.getConfig().getString("webhook");
      String webhookcreeper = this.plugin.getConfig().getString("webhook_creeper");
      String webhooktnt = this.plugin.getConfig().getString("webhook_tnt");
      String webhookclear = this.plugin.getConfig().getString("webhook_clear");
      String webhookkill = this.plugin.getConfig().getString("webhook_kill");
      String webhookair = this.plugin.getConfig().getString("webhook_air");
      String webhookexplode = this.plugin.getConfig().getString("webhook_explode");
      String webhookslowon = this.plugin.getConfig().getString("webhook_slow_on");
      String webhookslowoff = this.plugin.getConfig().getString("webhook_slow_off");

      DiscordWebhook webhook = new DiscordWebhook(this.webhookURL);

      if (sender instanceof Player) {
         Player p = (Player)sender;
         if (args.length == 0) {
            p.sendMessage("§6/wtroll creeper <player> <amount>");
            p.sendMessage("§6/wtroll tnt <player> <amount>");
            p.sendMessage("§6/wtroll clear <player>");
            p.sendMessage("§6/wtroll kill <player>");
            p.sendMessage("§6/wtroll explode <player>");
            p.sendMessage("§6/wtroll air <player>");
            p.sendMessage("§6/wtroll slow <player> ");
         } else if (args.length >= 1) {


            Integer szam;
            if (args[0].equals("creeper")) {
               if (p.hasPermission("wtroll.creeper")) {
                  if (args.length == 1) {
                     p.sendMessage(nonSpecifydPlayer);
                  } else if (args.length == 2) {
                     String playerName = args[1];
                     Player target = Bukkit.getServer().getPlayerExact(playerName);
                     if (target == null) {
                        p.sendMessage(playerNotFound);
                     } else {
                        World w = target.getWorld();
                        Location l = target.getLocation();

                        creepermsg = creepermsg.replace("%target%", target.getPlayer().getDisplayName());
                        creepermsg = creepermsg.replace("%amount%", defacreeper.toString());
                        webhookcreeper = webhookcreeper.replace("%amount%", defacreeper.toString());
                        webhookcreeper = webhookcreeper.replace("%target%", target.getPlayer().getDisplayName());
                        webhookcreeper = webhookcreeper.replace("%executor%", p.getPlayer().getDisplayName());

                        webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhookcreeper).setTitle("/wtroll creeper").setColor(Color.RED));

                        for (int i = 0; i < defacreeper; ++i) {
                           w.spawnEntity(l, EntityType.CREEPER);
                        }

                        try {
                           webhook.execute();
                        } catch (IOException var7) {
                           System.out.println("stfu");
                        }

                        p.sendMessage(creepermsg);
                     }
                  } else if (args.length == 3) {
                     String playerName = args[1];
                     Player target = Bukkit.getServer().getPlayerExact(playerName);
                     if (target == null) {
                        p.sendMessage(playerNotFound);
                     } else {
                        World w = target.getWorld();
                        Location l = target.getLocation();
                        szam = Integer.valueOf(args[2]);
                        creepermsg = creepermsg.replace("%target%", target.getPlayer().getDisplayName());
                        creepermsg = creepermsg.replace("%amount%", szam.toString());
                        webhookcreeper = webhookcreeper.replace("%amount%", szam.toString());
                        webhookcreeper = webhookcreeper.replace("%target%", target.getPlayer().getDisplayName());
                        webhookcreeper = webhookcreeper.replace("%executor%", p.getPlayer().getDisplayName());

                        webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhookcreeper).setTitle("/wtroll creeper").setColor(Color.RED));

                        for (int i = 0; i < szam; ++i) {
                           w.spawnEntity(l, EntityType.CREEPER);
                        }

                        try {
                           webhook.execute();
                        } catch (IOException var7) {
                           System.out.println("stfu");
                        }

                        p.sendMessage(creepermsg);
                     }
                  }
               }
            } else if (args[0].equals("tnt")) {
               if (p.hasPermission("wtroll.tnt")) {
                  if (args.length == 1) {
                     p.sendMessage(nonSpecifydPlayer);
                  } else if (args.length == 2) {
                     String playerName = args[1];
                     Player target = Bukkit.getServer().getPlayerExact(playerName);
                     if (target == null) {
                        p.sendMessage(playerNotFound);
                     } else {
                        World w = target.getWorld();
                        Location l = target.getLocation();
                        tntmsg = tntmsg.replace("%target%", target.getPlayer().getDisplayName());
                        tntmsg = tntmsg.replace("%amount%", defatnt.toString());
                        webhooktnt = webhooktnt.replace("%target%", target.getPlayer().getDisplayName());
                        webhooktnt = webhooktnt.replace("%executor%", p.getPlayer().getDisplayName());
                        webhooktnt = webhooktnt.replace("%amount%", defatnt.toString());

                        webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhooktnt).setTitle("/wtroll tnt").setColor(Color.RED));

                        for (int i = 0; i < defatnt; ++i) {
                           w.spawnEntity(l, EntityType.PRIMED_TNT);
                        }

                        try {
                           webhook.execute();
                        } catch (IOException var7) {
                           System.out.println("stfu");
                        }
                        p.sendMessage(tntmsg);
                     }
                  } else if (args.length == 3) {
                     String playerName = args[1];
                     Player target = Bukkit.getServer().getPlayerExact(playerName);
                     if (target == null) {
                        p.sendMessage(playerNotFound);
                     } else {
                        World w = target.getWorld();
                        Location l = target.getLocation();
                        szam = Integer.valueOf(args[2]);
                        tntmsg = tntmsg.replace("%target%", target.getPlayer().getDisplayName());
                        tntmsg = tntmsg.replace("%amount%", szam.toString());
                        webhooktnt = webhooktnt.replace("%target%", target.getPlayer().getDisplayName());
                        webhooktnt = webhooktnt.replace("%executor%", p.getPlayer().getDisplayName());
                        webhooktnt = webhooktnt.replace("%amount%", szam.toString());

                        webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhooktnt).setTitle("/wtroll tnt").setColor(Color.RED));

                        for (int i = 0; i < szam; ++i) {
                           w.spawnEntity(l, EntityType.PRIMED_TNT);
                        }

                        try {
                           webhook.execute();
                        } catch (IOException var7) {
                           System.out.println("stfu");
                        }
                        p.sendMessage(tntmsg);
                     }
                  }
               }
            } else {
               if (args[0].equals("clear")) {
                  if (p.hasPermission("wtroll.clear")) {
                     if (args.length == 1) {
                        p.sendMessage(nonSpecifydPlayer);
                     } else if (args.length == 2) {
                        String playerName = args[1];
                        Player target = Bukkit.getServer().getPlayerExact(playerName);
                        if (target == null) {
                           p.sendMessage(playerNotFound);
                        } else {
                           Inventory inv = target.getInventory();
                           inv = target.getInventory();
                           ItemStack dirt = new ItemStack(Material.DIRT, 2304);
                           clearmsg = clearmsg.replace("%target%", target.getPlayer().getDisplayName());
                           webhookclear = webhookclear.replace("%target%", target.getPlayer().getDisplayName());
                           webhookclear = webhookclear.replace("%executor%", p.getPlayer().getDisplayName());

                           webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhookclear).setTitle("/wtroll clear").setColor(Color.RED));

                           inv.clear();
                           inv.setItem(0, dirt);

                           try {
                              webhook.execute();
                           } catch (IOException var7) {
                              System.out.println("stfu");
                           }
                           p.sendMessage(clearmsg);

                        }
                     }
                  }
               } else if (args[0].equals("kill")) {
                  if (p.hasPermission("wtroll.kill")) {
                     if (args.length == 1) {
                        p.sendMessage(nonSpecifydPlayer);
                     } else if (args.length == 2) {
                        String playerName = args[1];
                        Player target = Bukkit.getServer().getPlayerExact(playerName);
                        if (target == null) {
                           p.sendMessage(playerNotFound);
                        } else {
                           Inventory inv = target.getInventory();
                           killmsg = killmsg.replace("%target%", target.getPlayer().getDisplayName());
                           webhookkill = webhookkill.replace("%target%", target.getPlayer().getDisplayName());
                           webhookkill = webhookkill.replace("%executor%", p.getPlayer().getDisplayName());

                           webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhookkill).setTitle("/wtroll kill").setColor(Color.RED));

                           inv.clear();
                           target.setHealth(0.0D);

                           try {
                              webhook.execute();
                           } catch (IOException var7) {
                              System.out.println("stfu");
                           }
                           p.sendMessage(killmsg);
                        }
                     }
                  }
               } else {

                  if (args[0].equals("explode")) {
                     if (p.hasPermission("wtroll.explode")) {
                        if (args.length == 1) {
                           p.sendMessage(nonSpecifydPlayer);
                        } else if (args.length == 2) {
                           String playerName = args[1];
                           Player target = Bukkit.getServer().getPlayerExact(playerName);
                           if (target == null) {
                              p.sendMessage(playerNotFound);
                           } else {
                              Location l = target.getLocation();
                              World w = l.getWorld();
                              explodemsg = explodemsg.replace("%target%", target.getPlayer().getDisplayName());
                              webhookexplode = webhookexplode.replace("%target%", target.getPlayer().getDisplayName());
                              webhookexplode = webhookexplode.replace("%executor%", p.getPlayer().getDisplayName());

                              webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhookexplode).setTitle("/wtroll explode").setColor(Color.RED));

                              l.createExplosion(5.0F, false);
                              target.setHealth(0.0D);
                              target.playSound(l, Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
                              target.getWorld().strikeLightningEffect(l);
                              target.playEffect(l, Effect.SMOKE, 0);

                              try {
                                 webhook.execute();
                              } catch (IOException var7) {
                                 System.out.println("stfu");
                              }

                              p.sendMessage(explodemsg);
                           }
                        }
                     }
                  } else if (args[0].equals("air")) {
                     if (p.hasPermission("wtroll.air")) {
                        if (args.length == 1) {
                           p.sendMessage(nonSpecifydPlayer);
                        } else if (args.length == 2) {
                           String playerName = args[1];
                           Player target = Bukkit.getServer().getPlayerExact(playerName);
                           if (target == null) {
                              p.sendMessage(playerNotFound);
                           } else {
                              Location l = target.getLocation();
                              World w = l.getWorld();
                              Location playerLocation = target.getLocation();
                              Inventory inventory = target.getInventory();
                              ItemStack viz = new ItemStack(Material.WATER_BUCKET, 1);
                              airmsg = airmsg.replace("%target%", target.getPlayer().getDisplayName());
                              webhookair = webhookair.replace("%target%", target.getPlayer().getDisplayName());
                              webhookair = webhookair.replace("%executor%", p.getPlayer().getDisplayName());

                              webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhookair).setTitle("/wtroll air").setColor(Color.RED));

                              playerLocation.setY(playerLocation.getY() + 300.0D);
                              target.teleport(playerLocation);

                              try {
                                 webhook.execute();
                              } catch (IOException var7) {
                                 System.out.println("stfu");
                              }
                              p.sendMessage(airmsg);
                              inventory.setItem(4, viz);
                              p.setGameMode(GameMode.SURVIVAL);
                           }
                        }
                     }
                  } else if (args[0].equals("slow")){
                     if (p.hasPermission("wtroll.slow")) {
                        if (args.length == 1) {
                           p.sendMessage(nonSpecifydPlayer);
                        } else if (args.length == 2) {
                           String playerName = args[1];
                           Player target = Bukkit.getServer().getPlayerExact(playerName);
                           if (target == null) {
                              p.sendMessage(playerNotFound);
                           } else {

                              slowmsgon = slowmsgon.replace("%target%", target.getPlayer().getDisplayName());
                              slowmsgoff = slowmsgoff.replace("%target%", target.getPlayer().getDisplayName());
                              webhookslowon = webhookslowon.replace("%target%", target.getPlayer().getDisplayName());
                              webhookslowon = webhookslowon.replace("%executor%", p.getPlayer().getDisplayName());
                              webhookslowoff = webhookslowoff.replace("%target%", target.getPlayer().getDisplayName());
                              webhookslowoff = webhookslowoff.replace("%executor%", p.getPlayer().getDisplayName());

                              webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhookair).setTitle("/wtroll slow").setColor(Color.RED));


                                 if (target.getWalkSpeed() == 0.009f) {
                                    target.setWalkSpeed(0.19f);
                                    p.sendMessage(slowmsgoff);
                                 }else {
                                    target.setWalkSpeed(0.009f);
                                    target.setFoodLevel(-1);
                                    p.sendMessage(slowmsgon);
                                 }

                              try {
                                 webhook.execute();
                              } catch (IOException var7) {
                                 System.out.println("stfu");
                              }

                           }
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
         return Arrays.asList("creeper", "tnt", "clear", "kill", "explode", "air", "slow");
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
