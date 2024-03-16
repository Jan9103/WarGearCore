/*
 * Copyright 2021 Jan9103 (@jan9103:matrix.org Jan9103.wargear@protonmail.com)
 *
 * Permission is hereby granted, free of charge, to any person or organization
 * obtaining a copy of the software and accompanying documentation covered by
 * this license (the "Software") to use, reproduce, display, distribute, execute,
 * and transmit the Software, and to prepare derivative works of the Software,
 * and to permit third-parties to whom the Software is furnished to do so, all
 * subject to the following:
 *
 * The copyright notices in the Software and this entire statement, including
 * the above license grant, this restriction and the following disclaimer, must
 * be included in all copies of the Software, in whole or in part, and all
 * derivative works of the Software, unless such copies or derivative works are
 * solely in the form of machine-executable object code generated by a source
 * language processor.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, TITLE AND NON-INFRINGEMENT. IN NO EVENT
 * SHALL THE COPYRIGHT HOLDERS OR ANYONE DISTRIBUTING THE SOFTWARE BE LIABLE FOR
 * ANY DAMAGES OR OTHER LIABILITY, WHETHER IN CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */
package de.jan9103.wargearcore;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import de.jan9103.wargearcore.worldedit.cmds.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

import de.jan9103.wargearcore.area.AreaCmd;
import de.jan9103.wargearcore.area.FreezeCmd;
import de.jan9103.wargearcore.area.SimpReloadCmd;
import de.jan9103.wargearcore.area.TbCmd;
import de.jan9103.wargearcore.area.TntCmd;
import de.jan9103.wargearcore.area.WgArea;
import de.jan9103.wargearcore.chat.BcMsg;
import de.jan9103.wargearcore.chat.ChatL;
import de.jan9103.wargearcore.cmds.BackCmd;
import de.jan9103.wargearcore.cmds.BookColor;
import de.jan9103.wargearcore.cmds.DiscordNote;
import de.jan9103.wargearcore.cmds.EcCmd;
import de.jan9103.wargearcore.cmds.EnchCommand;
import de.jan9103.wargearcore.cmds.FlyCmd;
import de.jan9103.wargearcore.cmds.FlyspeedCmd;
import de.jan9103.wargearcore.cmds.GamemodeCmds;
import de.jan9103.wargearcore.cmds.HealCmd;
import de.jan9103.wargearcore.cmds.HelmetCmd;
import de.jan9103.wargearcore.cmds.InfTntCmd;
import de.jan9103.wargearcore.cmds.InfiniFood;
import de.jan9103.wargearcore.cmds.LagAllCmd;
import de.jan9103.wargearcore.cmds.LagCmd;
import de.jan9103.wargearcore.cmds.LoreCommand;
import de.jan9103.wargearcore.cmds.NightVision;
import de.jan9103.wargearcore.cmds.PingCmd;
import de.jan9103.wargearcore.cmds.RenameCommand;
import de.jan9103.wargearcore.cmds.RespondCmd;
import de.jan9103.wargearcore.cmds.SkullCmd;
import de.jan9103.wargearcore.cmds.TellCmd;
import de.jan9103.wargearcore.cmds.TimerCmd;
import de.jan9103.wargearcore.cmds.TpsCmd;
import de.jan9103.wargearcore.cmds.UnenchantCommand;
import de.jan9103.wargearcore.cmds.UnsignBook;
import de.jan9103.wargearcore.cmds.UserSettingCmd;
import de.jan9103.wargearcore.cmds.WGCAuthCmd;
import de.jan9103.wargearcore.cmds.WalkSpeedCmd;
import de.jan9103.wargearcore.cmds.WgcCommand;
import de.jan9103.wargearcore.cmds.WhoIsCmd;
import de.jan9103.wargearcore.cmds.WorldFreezeCmd;
import de.jan9103.wargearcore.cmds.WorldTntCmd;
import de.jan9103.wargearcore.decentral.DecentralServer;
import de.jan9103.wargearcore.fight.Fight;
import de.jan9103.wargearcore.fight.FightCmd;
import de.jan9103.wargearcore.fight.listener.NoTeamPvp;
import de.jan9103.wargearcore.listener.BetterKillTracker;
import de.jan9103.wargearcore.listener.BlockPlaceListener;
import de.jan9103.wargearcore.listener.DeathListener;
import de.jan9103.wargearcore.listener.Disconnect;
import de.jan9103.wargearcore.listener.ExplosionListener;
import de.jan9103.wargearcore.listener.FreezeListener;
import de.jan9103.wargearcore.listener.InventoryListener;
import de.jan9103.wargearcore.listener.ItemListener;
import de.jan9103.wargearcore.listener.JoinListener;
import de.jan9103.wargearcore.listener.PrimeListener;
import de.jan9103.wargearcore.listener.SignChangeListener;
import de.jan9103.wargearcore.listener.SnakListener;
import de.jan9103.wargearcore.listener.TeleportListener;
import de.jan9103.wargearcore.tracer.SandMode;
import de.jan9103.wargearcore.tracer.TraceCmd;
import de.jan9103.wargearcore.tracer.TraceTnt;
import de.jan9103.wargearcore.util.ColorConverter;
import de.jan9103.wargearcore.util.DWHS;
import de.jan9103.wargearcore.util.FileWriter;
import de.jan9103.wargearcore.util.ServerListPlus;
import de.jan9103.wargearcore.util.TPS;
import de.jan9103.wargearcore.util.Tab;
import de.jan9103.wargearcore.util.ToDo;
import de.jan9103.wargearcore.util.WGCAuth;
import de.jan9103.wargearcore.util.portcannon.PortableCannonCmd;
import de.jan9103.wargearcore.worldedit.AxeListener;

/**
 * a few static variables and init
 */
public class WGC extends JavaPlugin {
	public static WGC wgc;
	public static final Vector nullVector=new Vector(0,0,0);
	/** /.../plugins/WarGearCore */
	public static String dataFolder=null;
	/** The global scoreboard */
	public static Scoreboard sb;
	/** obnective for the global scoreboard */
	public static Objective msbo; //main-scoreboard-objective
	/** Dateformat: dd.MM.yyyy HH:mm:ss*/
	public static final SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	/** Dateformat: dd.MM.yyyy HH*/
	public static final SimpleDateFormat sdfHour=new SimpleDateFormat("dd.MM.yyyy HH");
	/** Dateformat: dd.MM.yyyy HH:mm:ss.SSS */
	public static final SimpleDateFormat sdfMS=new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS");
	/** Dateformat: dd.MM.yyyy */
	public static final SimpleDateFormat sdfDay=new SimpleDateFormat("dd.MM.yyyy");
	/** WGC version number (used for auto-updates, etc) */
	public static final byte VERSION=15;
	/** discord log webhook url */
	private static String dcLogUrl="";
	public static LogHandler logHandler=new LogHandler();
	public static boolean noDefSb=false,d=false,autoBugReportLog=false,advancedScoreboard=false,customTab=true,discoverytracker=false,activeBugReportBugSearch=false,wgcDefence_auth=true;
	public static String servername="Homeserver",flagtext="WGC";
	public static String _PREFIX_1="WG",_PREFIX_2_Core="Core",_PREFIX_2_Defence="Defence",_PREFIX_2_Edit="Edit",_PREFIX_2_Area="Area",_PREFIX_2_Fight="Fight",_PREFIX_2_Script="Script";
	private static boolean betterTntTracker=false,reload=false,customChat=true,dropManager=true,ads=false,serverListPlus=false;
	private static String bugReportDWH = "";
	public static void dcLog(String s){
		if(dcLogUrl!="") try{new DWHS(dcLogUrl,servername,"https://static.wikia.nocookie.net/minecraft_gamepedia/images/d/d2/Pufferfish_Artwork.png",s);}catch(Throwable t){}
	}

	public static boolean dcBug(String s){
		try{new DWHS(bugReportDWH,servername,"https://static.wikia.nocookie.net/minecraft_gamepedia/images/d/d2/Pufferfish_Artwork.png",s); return false;}catch(Throwable e){return true;}
	}

//	@Deprecated public static void log(Exception e){
//		String o=e.getClass().getSimpleName()+": "+e.getMessage();
//		for(StackTraceElement i:e.getStackTrace())o+="\n"+i.getClassName()+":"+i.getLineNumber();
//		Bukkit.getLogger().warning(o);
//	}
	public static void log(String s){
		logHandler.add(s);
	}

	@Override public void onLoad(){
		if(wgc!=null) Bukkit.getScheduler().cancelTasks(wgc);
		dataFolder=getDataFolder().getAbsolutePath().replaceFirst("/++$",""); // remove all trailing "/"
		wgc=this;
		loadCfg();
		new File(dataFolder+"/schem/P").mkdirs();
		new File(dataFolder+"/schem/Tb").mkdir();
		new File(dataFolder+"/user/clipboard").mkdirs();
		new File(dataFolder+"/areas").mkdir();
		new File(dataFolder+"/backup").mkdir();
		new File(dataFolder+"/servericons").mkdir();
		new File(dataFolder+"/inventories").mkdir();
		new File(dataFolder+"/fightpresets").mkdir();
		ToDo.load();
		ColorConverter.init();
		Fight.init();
	}

	@Override public void onEnable(){
		if(!getServer().getVersion().contains("1.20")){
			Bukkit.getLogger().log(Level.WARNING,"This Version of WGC is only ment for Bukkit-1.20 - nothing newer or older -- please use another version of the plugin or server. Server Version: "+getServer().getVersion()); return;
		}
		if(reload){
			TPS.a(5);
			Bukkit.getLogger().log(Level.WARNING,"§e[WGC BETA] Reloads are not recommended, since in the current state WGC dosnt clean up the ram 100% and it therefore causes lag.");
			Bukkit.broadcastMessage("§e[WGC BETA] Reloads are not recommended, since in the current state WGC dosnt clean up the ram 100% and it therefore causes lag.");
		}
		//if(autoBugReportLog){
		Bukkit.getLogger().addHandler(logHandler);
		logHandler.setLevel(Level.ALL);
		Bukkit.getLogger().setFilter(logHandler);
		getServer().getLogger().setFilter(logHandler);
		//}
		dcLog("**__Starting..__**");
		sb  =Bukkit.getScoreboardManager().getNewScoreboard();
		msbo=sb.registerNewObjective("MainScoreboard","trigger",servername);
		msbo.setDisplaySlot(DisplaySlot.SIDEBAR);
		msbo.getScore("Online: ").setScore(0);
		new UserManager();
		TPS.d();
		getCommand("wgc").setExecutor(new WgcCommand());
		getCommand("wgcbug").setExecutor(logHandler);
		getCommand("settings").setExecutor(new UserSettingCmd());
		getCommand("gamemode").setExecutor(new GamemodeCmds());
		getCommand("lag").setExecutor(new LagCmd());
		getCommand("lagall").setExecutor(new LagAllCmd());
		getCommand("back").setExecutor(new BackCmd());
		getCommand("ctps").setExecutor(new TpsCmd());
		getCommand("timer").setExecutor(new TimerCmd());
		getCommand("skull").setExecutor(new SkullCmd());
		getCommand("whois").setExecutor(new WhoIsCmd());
		getCommand("dcn").setExecutor(new DiscordNote());
		getCommand("fly").setExecutor(new FlyCmd());
		getCommand("todo").setExecutor(new ToDo());
		getCommand("helmet").setExecutor(new HelmetCmd());
		getCommand("nv").setExecutor(new NightVision());
		getCommand("flyspeed").setExecutor(new FlyspeedCmd());
		getCommand("walkspeed").setExecutor(new WalkSpeedCmd());
		getCommand("heal").setExecutor(new HealCmd());
		getCommand("worldfreeze").setExecutor(new WorldFreezeCmd());
		getCommand("colorbook").setExecutor(new BookColor());
		getCommand("unsign").setExecutor(new UnsignBook());
		getCommand("worldtnt").setExecutor(new WorldTntCmd());
		getCommand("enderchest").setExecutor(new EcCmd());
		getCommand("ping").setExecutor(new PingCmd());
		getCommand("ench").setExecutor(new EnchCommand());
		getCommand("unenchant").setExecutor(new UnenchantCommand());
		getCommand("rename").setExecutor(new RenameCommand());
		getCommand("lore").setExecutor(new LoreCommand());
		getCommand("inftnt").setExecutor(new InfTntCmd());
		getCommand("portablecannon").setExecutor(new PortableCannonCmd());
		getCommand("tell").setExecutor(new TellCmd());
		getCommand("r").setExecutor(new RespondCmd());
		getCommand("fight").setExecutor(new FightCmd());
		getCommand("tnt").setExecutor(new TntCmd());
		getCommand("freeze").setExecutor(new FreezeCmd());
		getCommand("area").setExecutor(new AreaCmd());
		getCommand("trace").setExecutor(new TraceCmd());
		getCommand("testblock").setExecutor(new TbCmd());
		getCommand("simplereload").setExecutor(new SimpReloadCmd());
		getCommand("wgcauth").setExecutor(new WGCAuthCmd());
		getCommand("infinitefood").setExecutor(new InfiniFood());
		final ReplaceCmd rc=new ReplaceCmd();

		getCommand("(set").setExecutor(new SetCmd());
		getCommand("(fill").setExecutor(new FillCmd());
		getCommand("(wall").setExecutor(new WallCmd());
		getCommand("(testblock").setExecutor(new WETestblockCmd());
		getCommand("(rep").setExecutor(rc);
		getCommand("(repnear").setExecutor(new RepnearCmd());
		getCommand("(replace").setExecutor(rc);
		getCommand("(slab").setExecutor(new SlabPlaceCmd());
		getCommand("(xray").setExecutor(new XRayCmd());
		getCommand("(air").setExecutor(new AirCommand());
		getCommand("(fix").setExecutor(new WeFixCmd());
		getCommand("(techhide").setExecutor(new TechHideCmd());
		final ClipboardCmd cc=new ClipboardCmd();

		getCommand("(schem").setExecutor(new SchemCmd());
		getCommand("(copy").setExecutor(new CopyCmd());
		getCommand("(cut").setExecutor(new CutCmd());
		getCommand("(paste").setExecutor(new PasteCmd());
		getCommand("(resize").setExecutor(new ResizeCommand());
		getCommand("(cb").setExecutor(cc);
		getCommand("(clipboard").setExecutor(cc);
		getCommand("(posa").setExecutor(new PosCmd(true));
		getCommand("(posb").setExecutor(new PosCmd(false));
		getCommand("(expand").setExecutor(new ExpandCmd());
		getCommand("(undo").setExecutor(new UndoCmd());
		getCommand("(diff").setExecutor(new DiffCmd());
		final PluginManager pm=getServer().getPluginManager();

		pm.registerEvents(new Disconnect(),this);
		pm.registerEvents(new JoinListener(),this);
		pm.registerEvents(new AxeListener(),this);
		pm.registerEvents(new ExplosionListener(),this);
		pm.registerEvents(new FreezeListener(),this);
		if(customChat) pm.registerEvents(new ChatL(),this);
		if(dropManager) pm.registerEvents(new ItemListener(),this);
		if(betterTntTracker) pm.registerEvents(new BetterKillTracker(),this);
		pm.registerEvents(new DeathListener(),this);
		pm.registerEvents(new TeleportListener(),this);
		pm.registerEvents(new InventoryListener(),this);
		pm.registerEvents(new PrimeListener(),this);
		pm.registerEvents(new SignChangeListener(),this);
		pm.registerEvents(new BlockPlaceListener(),this);
		pm.registerEvents(new WGCAuth(),this);
		pm.registerEvents(new SnakListener(),this);
		pm.registerEvents(new NoTeamPvp(),this);
		if(ads)
			try{Bukkit.getScheduler().scheduleSyncRepeatingTask(this,new adRunnable(),12000,12000);}catch(IOException e){logHandler.add("[WGC] Unable to read Ads file");}
		UserManager.startUpdater();
		reloadsettings();
	}

	@Override public void onDisable(){
		reload=true;
		dcLog("**__Server Shutdown..__**");
		savesetting();
		UserManager.stopUpdater();
		WgArea.disable();
		if(UserManager.loadedUsers().size()>0) for(final User u:UserManager.loadedUsers()) u.saveCfg();
		if(autoBugReportLog) Bukkit.getLogger().removeHandler(logHandler);
		ToDo.save();
	}

	public void loadCfg(){
		final C i=new C("wgc.cfg");

		if(serverListPlus) Bukkit.getPluginManager().registerEvents(new ServerListPlus(i),this);
		DiscordNote.setUrl(i.gs("discord.wh.notes",""));
		dcLogUrl          =i.gs("discord.wh.log","");
		servername        =i.gs("server.name","Homeserver");
		customChat        =i.gb("customchat",true);
		dropManager       =i.gb("itemDropManager",true);
		TraceTnt.noLoss   =i.gb("tracer.noDataLossOnModeswitch",false);
		autoBugReportLog  =i.gb("autoBugReport",false);
		bugReportDWH      =i.gs("BugReportDiscordWebHookUrl","");
		advancedScoreboard=i.gb("advancedScoreboard",true);
		serverListPlus    =i.gb("serverListPlus",false);
		ads=i.gb("ads",false);
		discoverytracker        =i.gb("discoverytracker",false);
		activeBugReportBugSearch=i.gb("command.wgcbug.activeScan",false);
		wgcDefence_auth         =i.gb("defence.authme",false);
		flagtext               =i.gs("join.flag","WGC");
		_PREFIX_1              =i.gs("msg.prefix.1","WG");
		_PREFIX_2_Core         =i.gs("msg.prefix.2.core","Core");
		_PREFIX_2_Area         =i.gs("msg.prefix.2.area","Area");
		_PREFIX_2_Defence      =i.gs("msg.prefix.2.defence","Defence");
		_PREFIX_2_Fight        =i.gs("msg.prefix.2.fight","Fight");
		_PREFIX_2_Edit         =i.gs("msg.prefix.2.edit","Edit");
		_PREFIX_2_Script       =i.gs("msg.prefix.2.script","Script");
		noDefSb                =i.gb("noDefaultScoreboard",false);
		betterTntTracker       =i.gb("betterTntKillTracker",false);
		WgArea._sb_bu_status   =i.gs("msg.area.sb.bu.status",WgArea._sb_bu_status);
		WgArea._sb_freeze      =i.gs("msg.area.sb.freeze",WgArea._sb_freeze);
		WgArea._sb_last_bu     =i.gs("msg.area.sb.bu.last",WgArea._sb_last_bu);
		WgArea._sb_off         =i.gs("msg.area.sb.off",WgArea._sb_off);
		WgArea._sb_on          =i.gs("msg.area.sb.on",WgArea._sb_on);
		WgArea._sb_prefix_arena=i.gs("msg.area.sb.prefix.name",WgArea._sb_prefix_arena);
		WgArea._sb_prot        =i.gs("msg.area.sb.protection",WgArea._sb_prot);
		WgArea._sb_tnt         =i.gs("msg.area.sb.tnt",WgArea._sb_tnt);
		WgArea._sb_trace       =i.gs("msg.area.sb.trace.status",WgArea._sb_trace);
		WgArea._sb_wr          =i.gs("msg.area.sb.waterremover",WgArea._sb_wr);
		DecentralServer.port   =i.gi("decentral.server.port",25566);
		DecentralServer.on     =i.gb("decentral.server.host",false);
		SandMode.MAX_SAND      =i.gi("tracer.maxsand",1000);
		Tab.on=i.gb("customtab",true);
		try{i.save();}catch(final IOException e){LogHandler.handleException(e);}
	}

	public void reloadsettings(){
		final C c=new C("cache.yml");

		for(final String i:c.gsl("area")) try{WgArea.load(i);}catch(final Exception e){LogHandler.handleException(e);}
		for(final String i:c.gsl("frz")) FreezeListener.active.add(i);
		for(final String i:c.gsl("expl")) ExplosionListener.tntOn.add(i);
		ServerListPlus.olIps.addAll(c.gll("olips",new ArrayList<Long>()));
	}

	public void savesetting(){
		final C           c  =new C("cache.yml");
		final List<String>frz=new ArrayList<>();
		final List<String>tnt=new ArrayList<>();
		final List<String>ar =new ArrayList<>();

		for(final String i:FreezeListener.active) frz.add(i);
		for(final String i:ExplosionListener.tntOn) tnt.add(i);
		for(final WgArea i:WgArea.areas) ar.add(i.name);
		c.s("area",ar);
		c.s("frz",frz);
		c.s("expl",tnt);
		final List<Long>olips=new ArrayList<>();

		olips.addAll(ServerListPlus.olIps);
		c.sll("olips",olips);
		try{c.save();}catch(final IOException e){LogHandler.handleException(e);}
	}

	/** reload wgc configs without reloading bukkit */
	public static void wgcreload(){
		wgc.loadCfg();
		msbo.setDisplayName(servername);
	}

	/** cheeck if its april first */
	public static boolean april_fools(){
		final LocalDate ld=LocalDate.now(); return ld.getMonthValue()==4&&ld.getDayOfMonth()==1;
	}

	public static int month(){
		return LocalDate.now().getMonthValue();
	}

	/** Generates a String with the current date. */
	public static String sdf(){
		return sdf.format(new Date());
	}

	/** Generates a String with the date. */
	public static String sdf(Long time){
		return sdf.format(new Date(time));
	}

	public static int mins(){
		return Calendar.getInstance().get(Calendar.MINUTE);
	}

	private class adRunnable implements Runnable {
		private final String[] ads;
		private int i=0;
		public adRunnable()throws IOException {
			File f=new File(dataFolder+"/ads.txt");

			if(!f.exists()) throw new IOException("ads.txt file dosn't exist");
			String i=FileWriter.readUTF8(f);

			ads=i.split("\n");
		}

		@Override public void run(){
			UserManager.bc(new BcMsg(servername,"").a(ads[i])); if(++i>=ads.length) i=0;
		}
	}
}
