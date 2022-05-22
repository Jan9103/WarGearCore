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
package de.jan9103.wargearcore.cmds;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.jan9103.wargearcore.User;
import de.jan9103.wargearcore.chat.Msg;

public class RenameCommand extends UserOnlyCmd {
	@Override public void exe(final String[] args,User u,Player p){
		if(args.length<1){
			new Msg(u).b("Missing argument.").a(p); return;
		}
		final ItemStack item=p.getInventory().getItemInMainHand();

		if(item==null||item.getType()==Material.AIR){
			new Msg(u).b("Please select a valid Item in your Inventory").a(u); return;
		}
		final ItemMeta meta=item.getItemMeta();

		meta.setDisplayName(String.join(" ",args).replace("$$","\n").replace('$','§').replace("\n","$"));
		item.setItemMeta(meta);
		p.getInventory().setItemInMainHand(item);
		//p.playSound(p.getLocation(),Sound.BLOCK_ANVIL_USE,Global.itemSound,1);
		new Msg(u).a("new Item-Lore: §r"+String.join(" ",args).replace("$$","\n").replace('$','§').replace("\n","$")).a(u);
	}
}
