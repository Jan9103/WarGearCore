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
package de.jan9103.wargearcore.api.fight;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;

import de.jan9103.wargearcore.WGC;
import de.jan9103.wargearcore.fight.Fight;
import de.jan9103.wargearcore.fight.Fighter;

/**
 * Fight Mod.
 * ---
 * REQUIRED CONSTRUCOTR: (Fight fight,String[]arguments)
 * ---
 * If your constructor has an issue throw an FightModConstructorException with a nice message for the player
 * (NumberFormatExceptions are already taken care of)
 * ---
 * You have to register this class at some point (ex: JavaPlugin.onLoad() ) at Fight.allMods(name, FightMod)
 * ---
 * one object of each active fightMod exists per area
 */
public abstract class FightMod {
	/**
	 * register you mod
	 * @param name: The name of your mod
	 * @param fightModClass: The class of your mod (Example: RisingTides.class)
	 */
	public static void register(String name,Class<? extends FightMod>fightModClass){
		Fight.allMods.put(name,fightModClass);
	}

	public final Fight f;
	private HashSet<Integer>shedules=new HashSet<>();
	public FightMod(Fight fyte){
		f=fyte;
	}

	/**
	 * register a temporary (during fight) EventListener. Can be "this"
	 */
	public void registerTmpListener(Listener l){
		f.tmpListener.add(l); Bukkit.getPluginManager().registerEvents(l,WGC.wgc);
	}

	/**
	 * Called on "/fight mod add .."
	 */
	public void load(){
	}

	/**
	 * Called on "/fight mod remove .."
	 * default: stops all registered bukkit shedules
	 */
	public void unload(){
		stopAllShedules();
	}

	/**
	 * Called on "/fight start"
	 */
	public void countdownStart(){
	}

	/**
	 * Called on Countdown End
	 */
	public void fightStart(){
	}

	/**
	 * Called when someone dies
	 * @param fighter whoever died (can be a User or Bot)
	 */
	public void playerDeath(Fighter p){
	}

	/**
	 * Called when the fight ends
	 */
	public void fightEnd(){
	}

	/**
	 * A short description shown at Fight start
	 * Example: "Rising Tides with dirt"
	 */
	public abstract String desc();

	/**
	 * Generates a String which can reproduce this mod (command arguments).
	 * "/fight mod add [this string]" should readd it.
	 * used to save mods in configs
	 * example: "bomber 10"
	 */
	public abstract String cfg();

	/**
	 * Called whenever the waterremover detects a leak.
	 * @param block is the leak source
	 * @return cancel the draining? (return false if you dont want to intervene)
	 */
	public boolean preWaterRemoval(Block block){
		return false;
	}

	/**
	 * register a bukkit shedule.
	 * @param shed the Bukkit thread shedule
	 * @return shed
	 */
	public int addShedule(int shed){
		shedules.add(shed); return shed;
	}

	/**
	 * unregister and cancel a bukkit shedule.
	 * @param shed the Bukkit thread shedule id
	 */
	public void stopShedule(int shed){
		Bukkit.getScheduler().cancelTask(shed);
		shedules.remove(shed);
	}

	/**
	 * unregister and cancel all registered bukkit shedules
	 */
	public void stopAllShedules(){
		for(int i:shedules) Bukkit.getScheduler().cancelTask(i);
		shedules.clear();
	}
}
