package dzsivokado.elsonormalpluginom.commands;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import dzsivokado.elsonormalpluginom.Wtroll;
import dzsivokado.elsonormalpluginom.apis.DiscordWebhook;
import org.bukkit.*;
import org.bukkit.command.*;
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

import static dzsivokado.elsonormalpluginom.Functions.sendersendmsg;

public class WadminCMD implements CommandExecutor, TabExecutor {
   private final Wtroll plugin;
   private String webhookURL;

   public WadminCMD(Wtroll plugin) {
      this.plugin = plugin;
   }
   
   public  String pname;


   public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
      ProtocolManager pm = ProtocolLibrary.getProtocolManager();
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
      String demomsg = this.plugin.getConfig().getString("demo_msg");
      String endgmsg = this.plugin.getConfig().getString("endgame_msg");
      String noescmsg = this.plugin.getConfig().getString("noescape_msg");
      String lookdownmsg = this.plugin.getConfig().getString("lookdown_msg");
      String lookupmsg = this.plugin.getConfig().getString("lookup_msg");
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
      String webhookdemo = this.plugin.getConfig().getString("webhook_demo");
      String webhookendg = this.plugin.getConfig().getString("webhook_endgame");
      String webhooknoesc = this.plugin.getConfig().getString("webhook_noescape");
      String webhooklookdown = this.plugin.getConfig().getString("webhook_lookdown");
      String webhooklookup = this.plugin.getConfig().getString("webhook_lookup");

      if (sender instanceof Player){
         Player player = (Player) sender;

         pname = player.getPlayer().getDisplayName();
      } else if (sender instanceof ConsoleCommandSender) {

         pname = "Console";
      }




      DiscordWebhook webhook = new DiscordWebhook(this.webhookURL);



         if (args.length == 0) {
            sendersendmsg(sender, "§6/wtroll creeper <player> <amount>");
            sendersendmsg(sender, "§6/wtroll tnt <player> <amount>");
            sendersendmsg(sender, "§6/wtroll clear <player>");
            sendersendmsg(sender, "§6/wtroll kill <player>");
            sendersendmsg(sender, "§6/wtroll explode <player>");
            sendersendmsg(sender, "§6/wtroll air <player>");
            sendersendmsg(sender, "§6/wtroll slow <player> ");
            sendersendmsg(sender, "§6/wtroll demo <player> ");
            sendersendmsg(sender, "§6/wtroll endgame <player> ");
            sendersendmsg(sender, "§6/wtroll noescape <player> ");
            sendersendmsg(sender, "§6/wtroll lookdown <player>");
            sendersendmsg(sender, "§6/wtroll lookup <player>");
         } else if (args.length >= 1) {




            Integer szam;
            if (args[0].equals("creeper")) {
               if (sender.hasPermission("wtroll.creeper")) {
                  if (args.length == 1) {
                     sendersendmsg(sender, nonSpecifydPlayer);
                  } else if (args.length == 2) {
                     String playerName = args[1];
                     Player target = Bukkit.getServer().getPlayerExact(playerName);
                     if (target == null) {
                        sendersendmsg(sender, playerNotFound);
                     } else {
                        World w = target.getWorld();
                        Location l = target.getLocation();

                        creepermsg = creepermsg.replace("%target%", target.getPlayer().getDisplayName());
                        creepermsg = creepermsg.replace("%amount%", defacreeper.toString());
                        webhookcreeper = webhookcreeper.replace("%amount%", defacreeper.toString());
                        webhookcreeper = webhookcreeper.replace("%target%", target.getPlayer().getDisplayName());
                        webhookcreeper = webhookcreeper.replace("%executor%", pname);

                        webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhookcreeper).setTitle("/wtroll creeper").setColor(Color.RED));

                        for (int i = 0; i < defacreeper; ++i) {
                           w.spawnEntity(l, EntityType.CREEPER);
                        }

                        try {
                           webhook.execute();
                        } catch (IOException var7) {
                           System.out.println("Webhook not connected");
                        }

                        sendersendmsg(sender, creepermsg);
                     }
                  } else if (args.length == 3) {
                     String playerName = args[1];
                     Player target = Bukkit.getServer().getPlayerExact(playerName);
                     if (target == null) {
                        sendersendmsg(sender, playerNotFound);
                     } else {
                        World w = target.getWorld();
                        Location l = target.getLocation();
                        szam = Integer.valueOf(args[2]);
                        creepermsg = creepermsg.replace("%target%", target.getPlayer().getDisplayName());
                        creepermsg = creepermsg.replace("%amount%", szam.toString());
                        webhookcreeper = webhookcreeper.replace("%amount%", szam.toString());
                        webhookcreeper = webhookcreeper.replace("%target%", target.getPlayer().getDisplayName());
                        webhookcreeper = webhookcreeper.replace("%executor%", pname);

                        webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhookcreeper).setTitle("/wtroll creeper").setColor(Color.RED));

                        for (int i = 0; i < szam; ++i) {
                           w.spawnEntity(l, EntityType.CREEPER);
                        }

                        try {
                           webhook.execute();
                        } catch (IOException var7) {
                           System.out.println("Webhook not connected");
                        }

                        sendersendmsg(sender, creepermsg);
                     }
                  }
               }
            } else if (args[0].equals("tnt")) {
               if (sender.hasPermission("wtroll.tnt")) {
                  if (args.length == 1) {
                     sendersendmsg(sender, nonSpecifydPlayer);
                  } else if (args.length == 2) {
                     String playerName = args[1];
                     Player target = Bukkit.getServer().getPlayerExact(playerName);
                     if (target == null) {
                        sendersendmsg(sender, playerNotFound);
                     } else {
                        World w = target.getWorld();
                        Location l = target.getLocation();
                        tntmsg = tntmsg.replace("%target%", target.getPlayer().getDisplayName());
                        tntmsg = tntmsg.replace("%amount%", defatnt.toString());
                        webhooktnt = webhooktnt.replace("%target%", target.getPlayer().getDisplayName());
                        webhooktnt = webhooktnt.replace("%executor%", pname);
                        webhooktnt = webhooktnt.replace("%amount%", defatnt.toString());

                        webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhooktnt).setTitle("/wtroll tnt").setColor(Color.RED));

                        for (int i = 0; i < defatnt; ++i) {
                           w.spawnEntity(l, EntityType.PRIMED_TNT);
                        }

                        try {
                           webhook.execute();
                        } catch (IOException var7) {
                           System.out.println("Webhook not connected");
                        }
                        sendersendmsg(sender, tntmsg);
                     }
                  } else if (args.length == 3) {
                     String playerName = args[1];
                     Player target = Bukkit.getServer().getPlayerExact(playerName);
                     if (target == null) {
                        sendersendmsg(sender, playerNotFound);
                     } else {
                        World w = target.getWorld();
                        Location l = target.getLocation();
                        szam = Integer.valueOf(args[2]);
                        tntmsg = tntmsg.replace("%target%", target.getPlayer().getDisplayName());
                        tntmsg = tntmsg.replace("%amount%", szam.toString());
                        webhooktnt = webhooktnt.replace("%target%", target.getPlayer().getDisplayName());
                        webhooktnt = webhooktnt.replace("%executor%", pname);
                        webhooktnt = webhooktnt.replace("%amount%", szam.toString());

                        webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhooktnt).setTitle("/wtroll tnt").setColor(Color.RED));

                        for (int i = 0; i < szam; ++i) {
                           w.spawnEntity(l, EntityType.PRIMED_TNT);
                        }

                        try {
                           webhook.execute();
                        } catch (IOException var7) {
                           System.out.println("Webhook not connected");
                        }
                        sendersendmsg(sender, tntmsg);
                     }
                  }
               }
            } else {
               if (args[0].equals("clear")) {
                  if (sender.hasPermission("wtroll.clear")) {
                     if (args.length == 1) {
                        sendersendmsg(sender, nonSpecifydPlayer);
                     } else if (args.length == 2) {
                        String playerName = args[1];
                        Player target = Bukkit.getServer().getPlayerExact(playerName);
                        if (target == null) {
                           sendersendmsg(sender, playerNotFound);
                        } else {
                           Inventory inv = target.getInventory();
                           inv = target.getInventory();
                           ItemStack dirt = new ItemStack(Material.DIRT, 2304);
                           clearmsg = clearmsg.replace("%target%", target.getPlayer().getDisplayName());
                           webhookclear = webhookclear.replace("%target%", target.getPlayer().getDisplayName());
                           webhookclear = webhookclear.replace("%executor%", pname);

                           webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhookclear).setTitle("/wtroll clear").setColor(Color.RED));

                           inv.clear();
                           inv.addItem( dirt);

                           try {
                              webhook.execute();
                           } catch (IOException var7) {
                              System.out.println("Webhook not connected");
                           }
                           sendersendmsg(sender, clearmsg);

                        }
                     }
                  }
               } else if (args[0].equals("kill")) {
                  if (sender.hasPermission("wtroll.kill")) {
                     if (args.length == 1) {
                        sendersendmsg(sender, nonSpecifydPlayer);
                     } else if (args.length == 2) {
                        String playerName = args[1];
                        Player target = Bukkit.getServer().getPlayerExact(playerName);
                        if (target == null) {
                           sendersendmsg(sender, playerNotFound);
                        } else {
                           Inventory inv = target.getInventory();
                           killmsg = killmsg.replace("%target%", target.getPlayer().getDisplayName());
                           webhookkill = webhookkill.replace("%target%", target.getPlayer().getDisplayName());
                           webhookkill = webhookkill.replace("%executor%", pname);

                           webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhookkill).setTitle("/wtroll kill").setColor(Color.RED));

                           inv.clear();
                           target.setHealth(0.0D);

                           try {
                              webhook.execute();
                           } catch (IOException var7) {
                              System.out.println("Webhook not connected");
                           }
                           sendersendmsg(sender, killmsg);
                        }
                     }
                  }
               } else {

                  if (args[0].equals("explode")) {
                     if (sender.hasPermission("wtroll.explode")) {
                        if (args.length == 1) {
                           sendersendmsg(sender, nonSpecifydPlayer);
                        } else if (args.length == 2) {
                           String playerName = args[1];
                           Player target = Bukkit.getServer().getPlayerExact(playerName);
                           if (target == null) {
                              sendersendmsg(sender, playerNotFound);
                           } else {
                              Location l = target.getLocation();
                              World w = l.getWorld();
                              explodemsg = explodemsg.replace("%target%", target.getPlayer().getDisplayName());
                              webhookexplode = webhookexplode.replace("%target%", target.getPlayer().getDisplayName());
                              webhookexplode = webhookexplode.replace("%executor%", pname);

                              webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhookexplode).setTitle("/wtroll explode").setColor(Color.RED));

                              l.createExplosion(5.0F, false);
                              target.setHealth(0.0D);
                              target.playSound(l, Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
                              target.getWorld().strikeLightningEffect(l);
                              target.playEffect(l, Effect.SMOKE, 0);

                              try {
                                 webhook.execute();
                              } catch (IOException var7) {
                                 System.out.println("Webhook not connected");
                              }

                              sendersendmsg(sender, explodemsg);
                           }
                        }
                     }
                  } else if (args[0].equals("air")) {
                     if (sender.hasPermission("wtroll.air")) {
                        if (args.length == 1) {
                           sendersendmsg(sender, nonSpecifydPlayer);
                        } else if (args.length == 2) {
                           String playerName = args[1];
                           Player target = Bukkit.getServer().getPlayerExact(playerName);
                           if (target == null) {
                              sendersendmsg(sender, playerNotFound);
                           } else {
                              Location l = target.getLocation();
                              World w = l.getWorld();
                              Location playerLocation = target.getLocation();
                              Inventory inventory = target.getInventory();
                              ItemStack viz = new ItemStack(Material.WATER_BUCKET, 1);
                              airmsg = airmsg.replace("%target%", target.getPlayer().getDisplayName());
                              webhookair = webhookair.replace("%target%", target.getPlayer().getDisplayName());
                              webhookair = webhookair.replace("%executor%", pname);

                              webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhookair).setTitle("/wtroll air").setColor(Color.RED));

                              playerLocation.setY(playerLocation.getY() + 300.0D);
                              target.teleport(playerLocation);

                              try {
                                 webhook.execute();
                              } catch (IOException var7) {
                                 System.out.println("Webhook not connected");
                              }
                              sendersendmsg(sender, airmsg);
                              inventory.setItem(4, viz);
                              target.setGameMode(GameMode.SURVIVAL);
                           }
                        }
                     }
                  } else if (args[0].equals("slow")) {
                     if (sender.hasPermission("wtroll.slow")) {
                        if (args.length == 1) {
                           sendersendmsg(sender, nonSpecifydPlayer);
                        } else if (args.length == 2) {
                           String playerName = args[1];
                           Player target = Bukkit.getServer().getPlayerExact(playerName);
                           if (target == null) {
                              sendersendmsg(sender, playerNotFound);
                           } else {

                              slowmsgon = slowmsgon.replace("%target%", target.getPlayer().getDisplayName());
                              slowmsgoff = slowmsgoff.replace("%target%", target.getPlayer().getDisplayName());
                              webhookslowon = webhookslowon.replace("%target%", target.getPlayer().getDisplayName());
                              webhookslowon = webhookslowon.replace("%executor%", pname);
                              webhookslowoff = webhookslowoff.replace("%target%", target.getPlayer().getDisplayName());
                              webhookslowoff = webhookslowoff.replace("%executor%", pname);




                              if (target.getWalkSpeed() == 0.009f) {
                                 target.setWalkSpeed(0.19f);
                                 sendersendmsg(sender, slowmsgoff);
                                 webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhookslowoff).setTitle("/wtroll slow").setColor(Color.RED));
                              } else {
                                 target.setWalkSpeed(0.009f);
                                 target.setFoodLevel(-1);
                                 sendersendmsg(sender, slowmsgon);
                                 webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhookslowon).setTitle("/wtroll slow").setColor(Color.RED));
                              }

                              try {
                                 webhook.execute();
                              } catch (IOException var7) {
                                 System.out.println("Webhook not connected");
                              }

                           }
                        }
                     }
                  }else if (args[0].equals("demo")) {
                     if (sender.hasPermission("wtroll.demo")) {
                        if (args.length == 1) {
                           sendersendmsg(sender, nonSpecifydPlayer);
                        } else if (args.length == 2) {
                           String playerName = args[1];
                           Player target = Bukkit.getServer().getPlayerExact(playerName);
                           if (target == null) {
                              sendersendmsg(sender, playerNotFound);
                           } else {


                              demomsg = demomsg.replace("%target%", target.getPlayer().getDisplayName());
                              webhookdemo = webhookdemo.replace("%target%", target.getPlayer().getDisplayName());
                              webhookdemo = webhookdemo.replace("%executor%", pname);
                              webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhookdemo).setTitle("/wtroll demo").setColor(Color.RED));


                               PacketContainer packet = new PacketContainer(PacketType.Play.Server.GAME_STATE_CHANGE);
                               packet.getGameStateIDs().write(0, 5);
                               packet.getFloat().write(0, 0f);


                               pm.sendServerPacket(target, packet);
                               sendersendmsg(sender, demomsg);


                               try {
                                 webhook.execute();
                              } catch (IOException var7) {
                                 System.out.println("Webhook not connected");
                              }

                           }
                        }
                     }
                  }else if (args[0].equals("endgame")) {
                     if (sender.hasPermission("wtroll.endgame")) {
                        if (args.length == 1) {
                           sendersendmsg(sender, nonSpecifydPlayer);
                        } else if (args.length == 2) {
                           String playerName = args[1];
                           Player target = Bukkit.getServer().getPlayerExact(playerName);
                           if (target == null) {
                              sendersendmsg(sender, playerNotFound);
                           } else {


                              endgmsg = endgmsg.replace("%target%", target.getPlayer().getDisplayName());
                              webhookendg = webhookendg.replace("%target%", target.getPlayer().getDisplayName());
                              webhookendg = webhookendg.replace("%executor%", pname);
                              webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhookendg).setTitle("/wtroll endgame").setColor(Color.RED));


                              PacketContainer packet = new PacketContainer(PacketType.Play.Server.GAME_STATE_CHANGE);
                              packet.getGameStateIDs().write(0, 4);
                              packet.getFloat().write(0, 1f);


                              pm.sendServerPacket(target, packet);
                              sendersendmsg(sender, endgmsg);


                              try {
                                 webhook.execute();
                              } catch (IOException var7) {
                                 System.out.println("Webhook not connected");
                              }

                           }
                        }
                     }
                  }else if (args[0].equals("demo")) {
                     if (sender.hasPermission("wtroll.demo")) {
                        if (args.length == 1) {
                           sendersendmsg(sender, nonSpecifydPlayer);
                        } else if (args.length == 2) {
                           String playerName = args[1];
                           Player target = Bukkit.getServer().getPlayerExact(playerName);
                           if (target == null) {
                              sendersendmsg(sender, playerNotFound);
                           } else {


                              demomsg = demomsg.replace("%target%", target.getPlayer().getDisplayName());
                              webhookdemo = webhookdemo.replace("%target%", target.getPlayer().getDisplayName());
                              webhookdemo = webhookdemo.replace("%executor%", pname);
                              webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhookdemo).setTitle("/wtroll demo").setColor(Color.RED));


                              PacketContainer packet = new PacketContainer(PacketType.Play.Server.GAME_STATE_CHANGE);
                              packet.getGameStateIDs().write(0, 5);
                              packet.getFloat().write(0, 0f);


                              pm.sendServerPacket(target, packet);
                              sendersendmsg(sender, demomsg);


                              try {
                                 webhook.execute();
                              } catch (IOException var7) {
                                 System.out.println("Webhook not connected");
                              }

                           }
                        }
                     }
                  }else if (args[0].equals("noescape")) {
                     if (sender.hasPermission("wtroll.noescape")) {
                        if (args.length == 1) {
                           sendersendmsg(sender, nonSpecifydPlayer);
                        } else if (args.length == 2) {
                           String playerName = args[1];
                           Player target = Bukkit.getServer().getPlayerExact(playerName);
                           if (target == null) {
                              sendersendmsg(sender, playerNotFound);
                           } else {


                              noescmsg = noescmsg.replace("%target%", target.getPlayer().getDisplayName());
                              webhooknoesc = webhooknoesc.replace("%target%", target.getPlayer().getDisplayName());
                              webhooknoesc = webhooknoesc.replace("%executor%", pname);
                              webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhooknoesc).setTitle("/wtroll noescape").setColor(Color.RED));


                              PacketContainer packet = new PacketContainer(PacketType.Play.Server.GAME_STATE_CHANGE);
                              packet.getGameStateIDs().write(0, 4);
                              packet.getFloat().write(0, 0f);


                              pm.sendServerPacket(target, packet);
                              sendersendmsg(sender, noescmsg);


                              try {
                                 webhook.execute();
                              } catch (IOException var7) {
                                 System.out.println("Webhook not connected");
                              }

                           }
                        }
                     }
                  }else if (args[0].equals("lookdown")) {
                     if (sender.hasPermission("wtroll.lookdown")) {
                        if (args.length == 1) {
                           sendersendmsg(sender, nonSpecifydPlayer);
                        } else if (args.length == 2) {
                           String playerName = args[1];
                           Player target = Bukkit.getServer().getPlayerExact(playerName);
                           if (target == null) {
                              sendersendmsg(sender, playerNotFound);
                           } else {


                              lookdownmsg = lookdownmsg.replace("%target%", target.getPlayer().getDisplayName());
                              webhooklookdown = webhooklookdown.replace("%target%", target.getPlayer().getDisplayName());
                              webhooklookdown = webhooklookdown.replace("%executor%", pname);
                              webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhooklookdown).setTitle("/wtroll lookdown").setColor(Color.RED));


                              World world = target.getWorld();
                              Location loc = target.getLocation();
                              Double x = loc.getX();
                              Double y = loc.getY();
                              Double z = loc.getZ();
                              float yaw = loc.getYaw() - 180;
                              float pitch = 180;




                              Location goal = new Location(world, x, y, z, yaw, pitch);
                              target.teleport(goal);
                              sendersendmsg(sender, lookdownmsg);

                              try {
                                 webhook.execute();
                              } catch (IOException var7) {
                                 System.out.println("Webhook not connected");
                              }

                           }
                        }
                     }
                  }else if (args[0].equals("lookup")) {
                     if (sender.hasPermission("wtroll.lookup")) {
                        if (args.length == 1) {
                           sendersendmsg(sender, nonSpecifydPlayer);
                        } else if (args.length == 2) {
                           String playerName = args[1];
                           Player target = Bukkit.getServer().getPlayerExact(playerName);
                           if (target == null) {
                              sendersendmsg(sender, playerNotFound);
                           } else {


                              lookupmsg = lookupmsg.replace("%target%", target.getPlayer().getDisplayName());
                              webhooklookup = webhooklookup.replace("%target%", target.getPlayer().getDisplayName());
                              webhooklookup = webhooklookup.replace("%executor%", pname);
                              webhook.addEmbed((new DiscordWebhook.EmbedObject()).setDescription(webhooklookup).setTitle("/wtroll lookup").setColor(Color.RED));


                              World world = target.getWorld();
                              Location loc = target.getLocation();
                              Double x = loc.getX();
                              Double y = loc.getY();
                              Double z = loc.getZ();
                              float yaw = loc.getYaw() - 180;
                              float pitch = -180;




                              Location goal = new Location(world, x, y, z, yaw, pitch);
                              target.teleport(goal);
                              sendersendmsg(sender, lookupmsg);

                              try {
                                 webhook.execute();
                              } catch (IOException var7) {
                                 System.out.println("Webhook not connected");
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
         return Arrays.asList("creeper", "tnt", "clear", "kill", "explode", "air", "slow", "demo", "endgame", "noescape", "lookdown", "lookup" );
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