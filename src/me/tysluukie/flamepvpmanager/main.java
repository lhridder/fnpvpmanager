package me.tysluukie.flamepvpmanager;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.connorlinfoot.titleapi.TitleAPI;

public class main extends JavaPlugin implements Listener {
	public static main plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	public static int currentLine = 0;
	public static int tid = 0;
	public static int running = 1;
	public static long interval = 120;
	
	
//PluginStart
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		getConfig().addDefault("messages","FlamePvpManager/kitpvpmessages.txt");
		getConfig().addDefault("lockdown","false");
		getConfig().addDefault("lockreason","lockdown");
		getConfig().addDefault("kitpvp","0");
		getConfig().addDefault("kingdom","0");
		getConfig().addDefault("hub","0");
		getConfig().addDefault("count","0");
		getConfig().addDefault("spawn.x", "");
		getConfig().addDefault("spawn.y", "");
		getConfig().addDefault("spawn.z", "");
		getConfig().addDefault("spawn.world", "");
		getConfig().options().copyDefaults(true);
		saveConfig();
		tid = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				try {
					broadcastMessage("plugins/" + getConfig().getString("messages"));
				}	catch (IOException e) {
					
				}
			}
		}, 0, interval * 20);
}
//Broadcaster
	public static void broadcastMessage(String fileName) throws IOException {
		FileInputStream fs;
		fs = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(fs));
		for(int i = 0; i < currentLine; ++i)
			br.readLine();
		String line = br.readLine();
		br.close();
		line = line.replaceAll("&f", ChatColor.WHITE + "");
	    line = line.replaceAll("&0", ChatColor.BLACK + "");
	    line = line.replaceAll("&1", ChatColor.DARK_BLUE + "");
	    line = line.replaceAll("&2", ChatColor.DARK_GREEN + "");
	    line = line.replaceAll("&3", ChatColor.DARK_AQUA + "");
	    line = line.replaceAll("&4", ChatColor.DARK_RED + "");
	    line = line.replaceAll("&5", ChatColor.DARK_PURPLE + "");
	    line = line.replaceAll("&6", ChatColor.GOLD + "");
	    line = line.replaceAll("&7", ChatColor.GRAY + "");
	    line = line.replaceAll("&8", ChatColor.DARK_GRAY + "");
	    line = line.replaceAll("&9", ChatColor.BLUE + "");
	    line = line.replaceAll("&a", ChatColor.GREEN + "");
	    line = line.replaceAll("&b", ChatColor.AQUA + "");
	    line = line.replaceAll("&c", ChatColor.RED + "");
	    line = line.replaceAll("&d", ChatColor.LIGHT_PURPLE + "");
	    line = line.replaceAll("&e", ChatColor.YELLOW + "");
	    line = line.replaceAll("&f", ChatColor.WHITE + "");
	    line = line.replaceAll("&k", ChatColor.MAGIC + "");
	    line = line.replaceAll("&l", ChatColor.BOLD + "");
	    line = line.replaceAll("&m", ChatColor.STRIKETHROUGH + "");
	    line = line.replaceAll("&n", ChatColor.UNDERLINE + "");
	    line = line.replaceAll("&o", ChatColor.ITALIC + "");
	    line = line.replaceAll("&r", ChatColor.RESET + "");
	    line = line.replaceAll("$n", "\n" + "");
		Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.WHITE + line);
		LineNumberReader lnr = new LineNumberReader(new FileReader(new File(fileName)));
		lnr.skip(Long.MAX_VALUE);
		int lastLine = lnr.getLineNumber();
	    lnr.close();
		if(currentLine + 1 == lastLine + 1) {
			currentLine = 0;
		} else {
			currentLine++;
		}
	}
	
//JoinEvent	
@EventHandler
public void onJoin(PlayerJoinEvent e) {
			ScoreboardManager sm = Bukkit.getScoreboardManager();
        	Scoreboard onJoin = sm.getNewScoreboard();
        	Objective o = onJoin.registerNewObjective("FreedomCraft", "dummy");
        	o.setDisplaySlot(DisplaySlot.SIDEBAR);
        	o.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "FlameNetwork");
        	Score spacer = null;
        	Score sp = null;
        	Score nieuws = null;
        	Score nieuws1 = null;
        	Score sc = null;
        	Score ip = null;
        	spacer = o.getScore(ChatColor.AQUA + "");
        	spacer.setScore(6);
        	Score players = o.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "Welkom, " + ChatColor.GREEN + "" + ChatColor.BOLD + e.getPlayer().getName());
        	players.setScore(5);
        	sp = o.getScore(ChatColor.RED + "");
        	sp.setScore(4);
        	nieuws = o.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "Rank: ");
        	nieuws.setScore(3);
        	getConfig().addDefault("rank." + e.getPlayer().getName(),"Speler");
        	getConfig().options().copyDefaults(true);
        	saveConfig();
        	nieuws1 = o.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + getConfig().getString("rank." + e.getPlayer().getName()));
        	nieuws1.setScore(2);
        	sc = o.getScore(ChatColor.BLUE + "");
        	sc.setScore(1);
        	ip = o.getScore(ChatColor.DARK_RED + "" + ChatColor.BOLD + "FlameNetwork.g-s.nu         ");
        	ip.setScore(0);
        	e.getPlayer().setScoreboard(onJoin);
        	TitleAPI.sendTitle(e.getPlayer(), 30, 5 * 20, 10, ChatColor.RED + "Welkom!", "op Kitpvp!");
	e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9000000, 1), true);
	if(getConfig().get("rank." + e.getPlayer().getName()).equals("Speler")) {
			if(getConfig().get("lockdown").equals("true")) {
				e.setJoinMessage("");
				String lockreason = getConfig().getString("lockreason");
				e.getPlayer().kickPlayer(lockreason);
			}
			if(getConfig().get("lockdown").equals("false")) {
				e.getPlayer().sendMessage("");
				e.getPlayer().sendMessage("");
				e.getPlayer().sendMessage("");
				e.getPlayer().sendMessage("");
				e.getPlayer().sendMessage("");
				e.getPlayer().sendMessage("");
				e.getPlayer().sendMessage("");
				e.getPlayer().sendMessage("");
				e.getPlayer().sendMessage("");
				e.getPlayer().sendMessage("");
				e.getPlayer().sendMessage("");
				e.getPlayer().sendMessage("");
				e.getPlayer().sendMessage("");
				e.getPlayer().sendMessage("");
				e.getPlayer().sendMessage("");
				e.getPlayer().sendMessage("");
				e.getPlayer().sendMessage(ChatColor.GRAY + "_____________________________________________");
				e.getPlayer().sendMessage("");
				e.getPlayer().sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "                  <FlameNetwork> " + ChatColor.GOLD + "Kitpvp");
				e.getPlayer().sendMessage("");
				e.getPlayer().sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "      Welkom " + e.getPlayer().getName() + "!");
				e.getPlayer().sendMessage("");
				e.getPlayer().sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "      Join de arena door te klikken op:" + ChatColor.GREEN + "" + ChatColor.BOLD + " het Hologram!");
				e.getPlayer().sendMessage("");
				e.getPlayer().sendMessage(ChatColor.GRAY + "_____________________________________________");
				e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.GRAY + e.getPlayer().getName());
			}
		} else {
			e.getPlayer().setGameMode(GameMode.CREATIVE);
			e.getPlayer().performCommand("staff");
			e.setJoinMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage(ChatColor.GRAY + "_____________________________________________");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "                  <FlameNetwork> " + ChatColor.GOLD + "Kitpvp");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "      Welkom " + e.getPlayer().getName() + "!");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "      Je staff-modus is geactiveerd!");
			e.getPlayer().sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "      Je bent momenteel onzichtbaar voor normale spelers!");
			e.getPlayer().sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "      Zet je staffmodus uit via " + ChatColor.GOLD + "/staff!");
			e.getPlayer().sendMessage(ChatColor.GRAY + "_____________________________________________");
			return;
		}
	}

//Quitevent	
@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		e.setQuitMessage("");
	}

//BlockbreakEvent
@EventHandler	
public void onBlockBreak(BlockBreakEvent e){
		if(getConfig().get("rank." + e.getPlayer().getName()).equals("Speler")) {
			e.setCancelled(true);
		}
	}

//BlockplaceEvent
@EventHandler	
public void onBlockPlace(BlockPlaceEvent e){
		if(getConfig().get("rank." + e.getPlayer().getName()).equals("Speler")) {
			e.setCancelled(true);
		}
	}

//Foodlossevent
@EventHandler
public void onFoodLoss(FoodLevelChangeEvent e) {
	e.setCancelled(true);
}

//Commands
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		//help
		if(cmd.getName().equalsIgnoreCase("help")) {
			sender.sendMessage(ChatColor.GRAY + "_____________________________________________");
			sender.sendMessage("");
			sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Dit zijn de commands die je mag gebruiken:");
			sender.sendMessage("");
			sender.sendMessage(ChatColor.GOLD + "/kingdom: " + ChatColor.GREEN + "Join de kingdom server!");
			sender.sendMessage(ChatColor.GOLD + "/hub: " + ChatColor.GREEN + "Ga terug naar de hub!");
			sender.sendMessage("");
			sender.sendMessage(ChatColor.GRAY + "_____________________________________________");
			return true;
		}
		//Setrank
		if(cmd.getName().equalsIgnoreCase("setrank")) {
			if(sender.hasPermission("FlameHubManager.admin")) {
			if(args.length == 0) {
				sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.GREEN + "/setrank (player) (rank)");
				return true;
			}
			if(args.length == 1) {
				sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.GREEN + "/setrank (player) (rank)");
				return true;
			}
			if(args.length == 2) {
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if (target == null) {
					sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.GREEN + "Could not find player " + args[0] + ChatColor.RED + " !");
					return true;
				}
				getConfig().set("rank." + target.getName(), args[1]);
				saveConfig();
				sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.GREEN + "Speler " + args[0] + " heeft nu rank: " + args[1] + " !");
				}
		} else {
			sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.RED + "Je mag dit commando niet uitvoeren!");
		}
		}
		//hub BUNGEE
		if(cmd.getName().equalsIgnoreCase("hub")) {
			sender.sendMessage(ChatColor.GOLD + "Sending you to the hub...");
			Player p = (Player)sender;
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(b);
			try {
				out.writeUTF("Connect");
				out.writeUTF("hub");
				} catch (IOException e) {
					e.printStackTrace();
				}
				p.sendPluginMessage(this, "BungeeCord", b.toByteArray());
		}
		//kingdom BUNGEE
		if(cmd.getName().equalsIgnoreCase("kingdom")) {
			sender.sendMessage(ChatColor.GOLD + "Sending you to kingdom...");
			Player p = (Player)sender;
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(b);
			try {
				out.writeUTF("Connect");
				out.writeUTF("kitpvp");
				} catch (IOException e) {
					e.printStackTrace();
				}
				p.sendPluginMessage(this, "BungeeCord", b.toByteArray());
		}
		if(cmd.getName().equalsIgnoreCase("gm")) {
			if(getConfig().get("rank." + sender.getName()).equals("Speler")) {
				sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.RED + "Je mag dit commando niet uitvoeren!");
			} else {
				if(args.length == 0) {
					sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.GREEN + "/gm (0/1/2/3) [(player)]");
				}
				if(args.length == 1) {
					sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.GREEN + "Your gamemode has been updated!");
					if(args[0].equalsIgnoreCase("0")) {
						Player player = (Player) sender;
						player.setGameMode(GameMode.SURVIVAL);
					}
					if(args[0].equalsIgnoreCase("1")) {
						Player player = (Player) sender;
						player.setGameMode(GameMode.CREATIVE);
					}
					if(args[0].equalsIgnoreCase("2")) {
						Player player = (Player) sender;
						player.setGameMode(GameMode.ADVENTURE);
					}
					if(args[0].equalsIgnoreCase("3")) {
						Player player = (Player) sender;
						player.setGameMode(GameMode.SPECTATOR);
					}
				}
				if(args.length == 2) {
					sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.GREEN + "Gamemode has been updated!");
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if (target == null) {
						sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.GREEN + "Could not find player " + args[0] + ChatColor.RED + " !");
						return true;
					}
					if(args[0].equalsIgnoreCase("0")) {
						Player player = (Player) target;
						player.setGameMode(GameMode.SURVIVAL);
					}
					if(args[0].equalsIgnoreCase("1")) {
						Player player = (Player) target;
						player.setGameMode(GameMode.CREATIVE);
					}
					if(args[0].equalsIgnoreCase("2")) {
						Player player = (Player) target;
						player.setGameMode(GameMode.ADVENTURE);
					}
					if(args[0].equalsIgnoreCase("3")) {
						Player player = (Player) target;
						player.setGameMode(GameMode.SPECTATOR);
					}
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("spawn")) {
			if(getConfig().get("spawn.x").equals("")) {
				sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.RED + "De spawnlocatie is nog niet ingesteld, contacteer een stafflid!");
			} else {
			sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.GREEN + "Teleporting...");
			Player p = (Player) sender;
			double x = getConfig().getDouble("spawn.x");
			double y = getConfig().getDouble("spawn.y");
			double z = getConfig().getDouble("spawn.z");
			World w = Bukkit.getServer().getWorld(getConfig().getString("spawn.world"));
			p.teleport(new Location(w,x,y,z));
			}
		}
		if(cmd.getName().equalsIgnoreCase("setspawn")) {
			if(getConfig().get("rank." + sender.getName()).equals("Speler")) {
				sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.RED + "Je mag dit commando niet uitvoeren!");
			} else {
			Player p = (Player) sender;
			double x = p.getLocation().getX();
			double y = p.getLocation().getY();
			double z = p.getLocation().getZ();
			String world = p.getLocation().getWorld().getName();
			getConfig().set("spawn.x", x);
			getConfig().set("spawn.y", y);
			getConfig().set("spawn.z", z);
			getConfig().set("spawn.world", world);
			saveConfig();
			sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.RED + "Location of spawn set!");
			}
		}
		if(cmd.getName().equalsIgnoreCase("stopbroadcast")) {
			if(getConfig().get("rank." + sender.getName()).equals("Speler")) {
				sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.RED + "Je mag dit commando niet uitvoeren!");
			} else {
			if(running == 1) {
				Bukkit.getServer().getScheduler().cancelTask(tid);
				Player player = (Player) sender;
				player.sendMessage(ChatColor.RED + "[FlameNetwork]" + ChatColor.GREEN + " Broadcast is gestopt!");
				running = 0;
			} else {
				Player player = (Player) sender;
				player.sendMessage(ChatColor.RED + "[FlameNetwork]" + ChatColor.RED + " Broadcast is niet actief!");
			}
			}
		}
			if (commandLabel.equalsIgnoreCase("startbroadcast")) {
				if(getConfig().get("rank." + sender.getName()).equals("Speler")) {
					sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.RED + "Je mag dit commando niet uitvoeren!");
				} else {
				if(running == 1) {
					Player player = (Player) sender;
					player.sendMessage(ChatColor.RED + "[FlameNetwork]" + ChatColor.RED + " Broadcast is al actief!");
				} else {
					Player player = (Player) sender;
					player.sendMessage(ChatColor.RED + "[FlameNetwork]" + ChatColor.GREEN + " Broadcast is gestart!");
					tid = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
						public void run() {
							try {
								broadcastMessage("plugins/FlameHubManager/hubmessages.txt");
							}	catch (IOException e) {
								
							}
						}
					}, 0, interval * 20);
					running = 1;
				}
			}
			}
			if(cmd.getName().equalsIgnoreCase("shutdown")) {
				if(getConfig().get("rank." + sender.getName()).equals("Speler")) {
					sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.RED + "Je mag dit commando niet uitvoeren!");
				} else {
				for(Player player : Bukkit.getServer().getOnlinePlayers()) {
				TitleAPI.sendTitle(player, 30, 5 * 20, 10, ChatColor.RED + "Attentie!", "Server gaat herstarten in 5 seconden!");
			    Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
	                public void run() {
	                    Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "SERVER GAAT STOPPEN!!");
	                	Bukkit.shutdown();
	                    return;
	                }
	            }, 100);
			}
				}
			}
			if(cmd.getName().equalsIgnoreCase("announce")) {
				if(getConfig().get("rank." + sender.getName()).equals("Speler")) {
					sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.RED + "Je mag dit commando niet uitvoeren!");
				} else {
				for(Player player : Bukkit.getServer().getOnlinePlayers()) {
				if(args.length == 0) {
					sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.RED + "Invalid syntax!");
					return true;
				}
				if(args.length == 1) {
					String message = args[0];
					TitleAPI.sendTitle(player, 30, 5 * 20, 10, ChatColor.RED + "Attentie!", message);
					return true;
				}
				if(args.length == 2) {
					String message = args[0] + " " + args[1];
					TitleAPI.sendTitle(player, 30, 5 * 20, 10, ChatColor.RED + "Attentie!", message);
					return true;
				}
				if(args.length == 3) {
					String message = args[0] + " " + args[1] + " " + args[2];
					TitleAPI.sendTitle(player, 30, 5 * 20, 10, ChatColor.RED + "Attentie!", message);
					return true;
				}
				if(args.length == 4) {
					String message = args[0] + " " + args[1] + " " + args[2] + " " + args[3];
					TitleAPI.sendTitle(player, 30, 5 * 20, 10, ChatColor.RED + "Attentie!", message);
					return true;
				}
				if(args.length >= 4) {
					sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.RED + "Je mag maximaal 4 woorden ingeven!");
					return true;
				}
				}
			}
			}
		
		return true;
		
	}
}