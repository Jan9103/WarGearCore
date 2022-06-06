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
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.jan9103.wargearcore.area.WgArea;
import de.jan9103.wargearcore.chat.ChatColor;
import de.jan9103.wargearcore.chat.ChatTheme;
import de.jan9103.wargearcore.chat.Msg;
import de.jan9103.wargearcore.coordSys.BigCoord;
import de.jan9103.wargearcore.fight.FightTeam;
import de.jan9103.wargearcore.fight.Fighter;
import de.jan9103.wargearcore.jsvn.ScriptBinary;
import de.jan9103.wargearcore.util.Parser;
import de.jan9103.wargearcore.util.WGCAuth;
import de.jan9103.wargearcore.util.portcannon.PCShotgun;
import de.jan9103.wargearcore.util.portcannon.PortableCannon;
import de.jan9103.wargearcore.worldedit.WEArea;
import de.jan9103.wargearcore.worldedit.clip.CBv2;
import de.jan9103.wargearcore.worldedit.editors.Undo;
import de.jan9103.wargearcore.worldedit.editors.WeSender;

/**
 * An extension for the Player object.
 * This stores everything WGC needs to know about a User/Player
 * ---
 * implements WeSender: WGE has some weird features ;)
 *  here it is used to send completion/ cancel/ etc messages
 * ---
 * implements Fighter: in order to make the implementation of
 *  bots easier i seperated User and Fighting Something
 */
public class User implements WeSender,Fighter {
	/** Minecraft UUID */
	public final UUID uuid;
	/** The WarGearEdit Clipboard */
	public final CBv2 cbv2;
	/** SimpleReload Clipboard */
	public final CBv2 simpReload;
	/** URL to the profile picture (for discord integration) */
	public String profilePic;
	/** remote fire item-name to block mapping */
	public final HashMap<String,Block>fernz=new HashMap<>();
	/** cached book-scripts */
	public final HashMap<ItemStack,ScriptBinary>books=new HashMap<>();
	/** used for WGE undo */
	public int undoId=0;
	/** sound volume */
	private byte[] volume;
	/** The WGE hoe selection area */
	private WEArea wearea;
	public ChatTheme ct=ChatTheme.OLD;
	/** @Deprecated */
	private boolean isOnline;
	/** forgot.. TODO */
	public boolean rtlr=true;
	/** online with a pre 1.16 mc version (used for chat json and colors) */
	public boolean pre16=true;
	/** if false WGE is faster, but has no undo */
	public boolean saveUndo=true;
	/** CHAT: make unicode emojis out of ascii emojis */
	public boolean emoji=true;
	/** show chat images send by other players */
	public boolean chatImages=true;
	/** root permissio */
	public boolean root=false;
	/** use regex for search, etc */
	public boolean regex=false;
	/** unsure TODO */
	public boolean special_blocks=false;
	/** used for "/r" */
	public User currentMsgTarget;
	private String name="#NoNamePlayer";
	private FightTeam arenateam;
	private boolean canUseWE;
	/** used for "/back" */
	public Location back;
	/** config for the portable cannon */
	public PortableCannon portc=new PCShotgun(1,5,40);
	/** current WGArea (cached) */
	public WgArea curAr         =null;
	public boolean inFight      =false;
	public boolean noArgUndoList=true;
	public boolean advancedTab  =false;
	public boolean elytraPlus   =false;
	//TODO advanced tab cfg & setting
	/** remote fire target (with not renamed shears) */
	public Block fernzNull;

	/**
	 * The joinPanel design
	 * TODO: document id meanings here
	 */
	public byte joinPanel=2;

	/**
	 * how long beforehand does the player want to be
	 * informed about todos
	 */
	public byte joinTodoInfoDays=3;

	/**
	 * WGCDefence auth mode.
	 * TODO: check if i remembered the id meanings correctly
	 * ask for the password:
	 * 0: never
	 * 1: once per ip
	 * 2: always
	 */
	public byte authPwMode=1;

	/**
	 * a relic of the old integrated pex system.
	 * might revive it someday, but well
	 * @Deprecated
	 * @return null
	 */
	public String getColor(){
		return null;
	}                                     //TODO

	/**
	 * called when someone else leaves (for clearing "/r" targets, etc)
	 */
	public void onLeave(User u){
		if(currentMsgTarget==u) currentMsgTarget=null;
	}

	/** forgot.. TODO */
	public Long cda=0L;
	/** inform about cancelled WGE operation */
	@Override public void onCancel(){
		new Msg(this,WGC._PREFIX_2_Edit).a("Cancelled operation.").a(this);
	}

	/** get the player location */
	public BigCoord loc(){
		final Player p=getPlayer();

		return p==null?null:new BigCoord(p.getLocation());
	}

	/** get the player location */
	public Block getBlock(){
		final Player p=getPlayer();

		if(p==null) return null;

		return p.getLocation().getBlock();
	}

	/** get the eye location */
	public Location eyeLoc(){
		final Player p=getPlayer(); if(p==null) return null; return p.getEyeLocation();
	}

	public void heal(){
		final Player p=getPlayer(); if(p==null) return; p.setHealth(20); p.setSaturation(20);
	}

	/** NORTH/ SOUTH/ EAST/ WEST/ UP/ DOWN */
	public BlockFace facing(){
		final Player p=getPlayer();

		return p==null?BlockFace.SELF:Parser.smallDir(p.getEyeLocation().getDirection());
	}

	/** from the fight system */
	public void setLevel(int level){
		final Player p=getPlayer();

		if(p!=null) p.setLevel(level);
	}

	/** get the WGE selection */
	public WEArea getWE(){
		return wearea;
	}

	public User(UUID id,boolean authme){
		uuid=id; setup(authme); cbv2=new CBv2(id,"main"); simpReload=new CBv2(id,"sr");
	}

	@Deprecated public User(Player p){
		uuid      =p.getUniqueId();
		cbv2      =new CBv2(uuid,"main");
		simpReload=new CBv2(uuid,"sr");
		setup(false);
	}

	/**
	 * Historic feature for fixing common bugs.
	 */
	public void autoBugFix(){
		if(wearea==null) wearea=new WEArea();
	}

	/**
	 * loads the config, etc.
	 * not sure why it isn't in the constructor
	 */
	private void setup(boolean authme){
		canUseWE=true;
		wearea  =new WEArea();
		final File f=new File(WGC.dataFolder+"/user/"+uuid.toString()+".usr");

		if(!f.exists()) try{f.createNewFile();}catch(final IOException e){WGC.log("Unable to create new UserConfigFile..");}
		final C c=new C(f);

		volume          =c.gba("volume",new byte[] {100,100,100,100,100,100});
		rtlr            =c.gb("respond",true);
		ct              =ChatColor.fromInt(c.gi("chattheme",2));
		pre16           =c.gb("pre16",true);
		saveUndo        =c.gb("undo",true);
		name            =c.gs("name","#NoNamePlayer");
		emoji           =c.gb("emoji",true);
		noArgUndoList   =c.gb("undoNoArg",true);
		advancedTab     =c.gb("advancedtab",false);
		chatImages      =c.gb("chatimages",true);
		elytraPlus      =c.gb("elytraplus",false);
		root            =c.gb("root",false);
		joinPanel       =(byte)c.gi("joinPanel",2);
		joinTodoInfoDays=(byte)c.gi("joinToDoInfoDays",3);
		authPwMode      =c.gby("authmode",(byte)1);
		profilePic      =c.gs("pictureUrl","https://crafatar.com/avatars/"+uuid.toString());
		regex           =c.gb("regex",false);
		special_blocks  =c.gb("special_blocks",false);
		List<String>ips=c.gjsl("ips");
		String      pw =c.gs("authpw","§");

		// ------ add here -----------------------------------
		try{c.save();}catch(final IOException e){WGC.log("Unable to save UserConfigFile..");}
		if(authme) WGCAuth.check(getPlayer(),this,ips,pw,authPwMode);
		if(WGC.april_fools()) ct=ChatTheme.APRIL_FOOLS;
	}

	public void saveCfg(){
		final File f=new File(WGC.dataFolder+"/user/"+uuid.toString()+".usr");

		if(!f.exists()) try{f.createNewFile();}catch(final IOException e){WGC.log("Unable to create new UserConfigFile");}
		final C c=new C(f);

		c.sba("volume",volume);
		c.s("respond",rtlr);
		c.s("chattheme",ChatColor.toInt(ct));
		c.s("pre16",pre16);
		c.s("undo",saveUndo);
		c.s("name",name);
		c.s("emoji",emoji);
		c.s("undoNoArg",noArgUndoList);
		c.s("advancedtab",advancedTab);
		c.s("chatimages",chatImages);
		c.s("elytraplus",elytraPlus);
		c.s("joinPanel",joinPanel);
		c.s("joinToDoInfoDays",joinTodoInfoDays);
		c.s("authmode",authPwMode);
		c.s("pictureUrl",profilePic);
		c.s("regex",regex);
		c.s("special_blocks",special_blocks);
		// ------ add here ------------------------------------
		try{c.save();}catch(final IOException e){WGC.log("Unable to save UserConfigFile");}
	}

	public void playSound(SoundKategory k,Sound s,float pitch){
		final Player p=getPlayer();

		if(p==null) return;

		p.playSound(p.getLocation(),s,getSoundVolume(k)/100f,pitch);
	}

	public void setSoundVolume(SoundKategory k,byte vol){
		switch(k){
		case AREA_STATE_CHANGE: volume[1]=vol; return;

		case CHATPING: volume[5]=vol; return;

		case COMMANDS: volume[0]=vol; return;

		case FIGHT_BASE: volume[3]=vol; return;

		case FIGHT_EVENT: volume[2]=vol; return;

		case TIMER: volume[4]=vol; return;

		default:
		}
	}

	public byte getSoundVolume(SoundKategory k){
		switch(k){
		case TIMER: return volume[4];

		case FIGHT_BASE: return volume[3];

		case FIGHT_EVENT: return volume[2];

		case AREA_STATE_CHANGE: return volume[1];

		case COMMANDS: return volume[0];

		case CHATPING: return volume[5];

		default: return 0;
		}
	}

	public byte cycleVolume(SoundKategory k,byte am,boolean up){
		int cat;

		switch(k){
		case AREA_STATE_CHANGE: cat=1; break;

		case CHATPING: cat=5; break;

		case FIGHT_BASE: cat=3; break;

		case FIGHT_EVENT: cat=2; break;

		case TIMER: cat=4; break;

		default: cat=0;       //COMMANDS
		}
		if(up){
			byte i=(byte)(volume[cat]+am);
			if(i>100) i=0;
			return volume[cat]=i;
		}
		byte i=(byte)(volume[cat]-am);

		if(i<0) i=100;
		return volume[cat]=i;
	}

	/**
	 * Historic..
	 * was used to differentiate between a join and just loading the config
	 */
	public void join(){
		final Player p=getPlayer();

		if(p==null) return;

		//if(!p.getName().equalsIgnoreCase(name)&&name!=null&&name!="#NoNamePlayer"){
		//if(WGC.autoBugReportLog)WGC.dcBug("Name "+p.getName()+" != "+name);
		//		UserManager.bc(new BcMsg().c(name).a(" has a new name: ").c(p.getName()).a("."));
		//}
		name=p.getName();
		WGC.msbo.getScore("Online: ").setScore(Bukkit.getOnlinePlayers().size());
		isOnline=true;
	}

	public void leave(){
		cbv2.unload();
		isOnline=false;
		WGC.msbo.getScore("Online: ").setScore(Bukkit.getOnlinePlayers().size());
		//TODO kick out of arean & co
	}

	public Player getPlayer(){
		return Bukkit.getServer().getPlayer(uuid);
	}

	public void gm(GameMode m){
		final Player p=getPlayer(); if(p==null) return; p.setGameMode(m);
	}

	public void tp(Location l){
		final Player p=getPlayer(); if(p==null) return; p.teleport(l);
	}

	@Deprecated public void rawMsg(String s){
		final Player p=getPlayer(); if(p!=null) p.sendMessage(s);
	}

	/**
	 * Historic..
	 */
	@Deprecated public boolean isOnline(){
		if(!getPlayer().isOnline()) return false; return isOnline;
	}

	/** Historic: nick system */
	public String getName(){
		return name;
	}

	/** Historic: nick system */
	public void setName(String name){
		this.name=name;
	}

	/** Historic: was needed for the old god mode */
	public boolean isGod(){
		try{return getPlayer().isInvulnerable();}catch(final NullPointerException e){return false;}
	}

	public void setGod(boolean god){
		try{getPlayer().setInvulnerable(god);}catch(final NullPointerException e){}
	}

	public FightTeam getArenateam(){
		return arenateam;
	}

	public void setArenateam(FightTeam arenateam){
		this.arenateam=arenateam;
	}

	/** was used to prevent command spam */
	public boolean canUseWE(){
		return canUseWE;
	}

	/** was used to prevent command spam */
	public void setCanUseWE(boolean canUseWE){
		this.canUseWE=canUseWE;
	}

	/** called when a WGE operation is finished */
	@Override public void onEnd(){
		new Msg(this,WGC._PREFIX_2_Edit).a("Done").a(this);
	}

	/** used for the fight-code in order to allow bots */
	@Override public String fightGetName(){
		return name;
	}

	/** used for the fight-code in order to allow bots */
	@Override public float fightGetHealth(){
		final Player p=getPlayer();

		return p==null?0:(int)p.getHealth();
	}

	/** used for the fight-code in order to allow bots */
	@Override public void fightReady(){
		gm(GameMode.SURVIVAL);
		setGod(true);
		heal();
	}

	/** used for the fight-code in order to allow bots */
	@Override public void fightStart(){
		setGod(false); heal();
	}

	/** used for the fight-code in order to allow bots */
	@Override public void fightSetXp(final int lvl){
		final Player p=getPlayer(); if(p!=null) p.setLevel(lvl);
	}

	/** used for the fight-code in order to allow bots */
	@Override public void fightTp(Location l){
		tp(l);
	}

	/** WGE */
	public int saveUndo(Undo u,String name){
		if(++undoId>=30) undoId=0;
		String pre="u"+undoId+"_";

		for(File i:new File(WGC.dataFolder+"/user/clipboard/"+uuid.toString()).listFiles()) if(i.getName().startsWith(pre)) i.delete();
		File f=new File(WGC.dataFolder+"/user/clipboard/"+uuid.toString()+"/"+pre+name+".jsv2i");

		u.s(f);
		return undoId;
	}
}