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
package de.jan9103.wargearcore.worldedit.cmds;

import de.jan9103.wargearcore.User;
import de.jan9103.wargearcore.WGC;
import de.jan9103.wargearcore.chat.Msg;
import de.jan9103.wargearcore.worldedit.clip.Clipboard;
import de.jan9103.wargearcore.worldedit.editors.ClipboardDiff;
import de.jan9103.wargearcore.worldedit.schem.SchemFileFinder;
import de.jan9103.wargearcore.worldedit.schem.SchemFormat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;

public class DiffCmd extends WorldEditCmd {
	@Override public void exec(String[] args,User u,Player p){
		if(args.length!=1){
			new Msg(u).b("Syntax: /(diff (schematic)").a(p); return;
		}
		Bukkit.getScheduler().runTaskAsynchronously(WGC.wgc,()->{
			File schemFile=SchemFileFinder.userMatch(u,args[0]); // TODO
			if(schemFile==null) new Msg(u).b("Unable to match \""+args[0]+"\" to a single schematic.");
			Clipboard schem=SchemFormat.parseAndLoad(schemFile);
			if(schem==null){
				new Msg(u).b("Schem not found..").a(p); return;
			}
			Clipboard userCB=u.cbv2.getAndWait();
			if(userCB==null){
				new Msg(u).b("Copy something first").a(p); return;
			}
			if(!userCB.isValid()){
				new Msg(u).b("Copy something first").a(p); return;
			}
			ClipboardDiff cd=new ClipboardDiff(userCB,schem);
			cd.run();
			u.cbv2.set(cd.out);
			new Msg(u).a("Diff saved to your clipboard.");
		});
	}
}
