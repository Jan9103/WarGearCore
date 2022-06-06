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
package de.jan9103.wargearcore.listener;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.data.type.TNT;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import de.jan9103.wargearcore.WGC;
import de.jan9103.wargearcore.area.WgArea;

public class BlockPlaceListener implements Listener {
	public static HashSet<Player>infTnt=new HashSet<>();
	@EventHandler public void a(BlockPlaceEvent e){
		Material material=e.getItemInHand().getType();

		if(material.hasGravity()){
			final WgArea wga=WgArea.inside(e.getBlockPlaced());
			if(wga!=null)
				if(WgArea.inside(e.getBlockPlaced()).isFrozen()){
					e.setBuild(false);
					Bukkit.getScheduler().scheduleSyncDelayedTask(WGC.wgc,()->e.getBlock().setType(e.getItemInHand().getType(),false),1);
				}
			return;
		}
		if(material==Material.INFESTED_CHISELED_STONE_BRICKS)
			e.getBlockPlaced().setType(Material.MOVING_PISTON);
		if(material!=Material.TNT) return;

		if(infTnt.contains(e.getPlayer())) e.getItemInHand().setAmount(64);
		if(e.getItemInHand().getItemMeta().hasDisplayName())
			if(e.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("unstable")){
				final TNT bd=(TNT)Bukkit.createBlockData(Material.TNT);
				bd.setUnstable(true);
				e.getBlockPlaced().setBlockData(bd,false);
			}
	}
}