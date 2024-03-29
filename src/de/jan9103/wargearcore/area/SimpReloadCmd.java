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
package de.jan9103.wargearcore.area;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import de.jan9103.wargearcore.User;
import de.jan9103.wargearcore.WGC;
import de.jan9103.wargearcore.chat.Msg;
import de.jan9103.wargearcore.cmds.UserOnlyCmd;
import de.jan9103.wargearcore.worldedit.clip.Clipboard;
import de.jan9103.wargearcore.worldedit.editors.async.AsyncPaste4b;

public class SimpReloadCmd extends UserOnlyCmd {
	@Override public void exe(String[] a,User u,Player p){
		if(a.length<1){
			new Msg(u,WGC._PREFIX_2_Edit).b("Missing Argument.").d().b("/sr save - copies tnt in WGE-Selection").d().b("/sr paste - paste").d().b("   or Left clicking (without sneaking) with a shear.").a(p); return;
		}
		switch(a[0].toLowerCase()){
		case "copy":
		case "save": {
			if(!u.getWE().isset()){
				new Msg(u,WGC._PREFIX_2_Edit).b("Select an area first.").a(p);
				return;
			}
			u.simpReload.copy(u.getWE(),p.getLocation().getBlock());
			final Clipboard cb=new Clipboard(u.getWE(),p.getLocation().getBlock());
			Bukkit.getScheduler().runTaskAsynchronously(WGC.wgc,()->{
					cb.inverseMask(Material.TNT);
					u.simpReload.set(cb);
					new Msg(u,WGC._PREFIX_2_Edit).a("Tnt copied").a(p);
				});
			return;
		}

		case "paste":
			Bukkit.getScheduler().runTaskAsynchronously(WGC.wgc,()->{
				Clipboard cb=u.simpReload.getAndWait();
				if(cb==null){
					new Msg(u,WGC._PREFIX_2_Edit).e("Unable to load Reload-Clipboard.\nPlease (re-)create one.").a(p); return;
				}
				AsyncPaste4b ap=new AsyncPaste4b(p.getWorld(),cb.originalCopyPoint,u,cb);
				if(u.saveUndo)
					Bukkit.getScheduler().runTask(WGC.wgc,()->{
						u.saveUndo(ap.getUndo(),"simple-reload");
						ap.aFromSync();
					});
				else ap.a();
			});
			return;

		default: new Msg(u,WGC._PREFIX_2_Edit).b("Unknown SubCommand").a(p);
		}
	}
}
