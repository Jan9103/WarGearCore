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

import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.jan9103.wargearcore.util.LimitedMap;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BetterKillTracker implements Listener {
	private final LimitedMap<Block,Player>updates=new LimitedMap<>(100);
	private final LimitedMap<TNTPrimed,Player>tnt=new LimitedMap<>(50);
	private final LimitedMap<Player,Player>player=new LimitedMap<>(5);
	@EventHandler(priority=EventPriority.MONITOR) public void a(BlockPhysicsEvent e){
		Player p=updates.get(e.getSourceBlock());

		if(p!=null)
			updates.put(e.getBlock(),p);
	}

	@EventHandler(priority=EventPriority.MONITOR) public void a(PlayerInteractEvent e){
		updates.put(e.getClickedBlock(),e.getPlayer());
	}

	@EventHandler(priority=EventPriority.MONITOR) public void a(EntitySpawnEvent e){
		if(e.getEntityType()!=EntityType.PRIMED_TNT) return;

		if(!e.getEntity().hasGravity()) return;

		Player p=updates.get(e.getLocation().getBlock());

		if(p!=null) tnt.put((TNTPrimed)e.getEntity(),p);
	}

	@EventHandler(priority=EventPriority.MONITOR) public void a(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player){
			Player p=tnt.get(e.getDamager());
			player.put((Player)e.getEntity(),p);
		}
	}

	@EventHandler public void a(PlayerDeathEvent e){
		Player s=player.get(e.getEntity());

		if(s!=null) e.setDeathMessage(e.getEntity().getDisplayName()+" was shot by "+s.getName()+".");
	}

	@EventHandler(priority=EventPriority.MONITOR) public void a(PlayerQuitEvent e){
		Player p=e.getPlayer();

		updates.removeAllWithValue(p);
		tnt.removeAllWithValue(p);
		player.remove(p);
		player.removeAllWithValue(p);
	}
}