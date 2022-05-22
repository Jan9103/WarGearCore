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

import org.bukkit.entity.Player;

import de.jan9103.wargearcore.User;
import de.jan9103.wargearcore.WGC;
import de.jan9103.wargearcore.chat.Msg;
import de.jan9103.wargearcore.coordSys.BigCoord;
import de.jan9103.wargearcore.worldedit.editors.async.AsyncPaste4;

public class PasteCmd extends WorldEditCmd {
	@Override public void exec(String[] a,User u,Player p){
		boolean o=false;

		if(a.length>=1)
			for(final String s:a)
				switch(s.toLowerCase()){
				case "-o": o=true; continue;
				}
		AsyncPaste4 ap4=new AsyncPaste4(p.getWorld(),new BigCoord(p.getLocation()),u,u.cbv2,o);

		if(!ap4.b()){
			new Msg(u,WGC._PREFIX_2_Edit).b("Unable to paste. (Missing Clipboard?)").a(p); return;
		}
		if(u.saveUndo) u.saveUndo(ap4.getUndo(),"paste"+(o?"-o":""));
		ap4.aFromSync();
	}
}
