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

import org.bukkit.Material;
import org.bukkit.entity.Player;

import de.jan9103.wargearcore.User;
import de.jan9103.wargearcore.WGC;
import de.jan9103.wargearcore.chat.Msg;
import de.jan9103.wargearcore.worldedit.WEArea;
import de.jan9103.wargearcore.worldedit.editors.AsyncMultiEditor;
import de.jan9103.wargearcore.worldedit.editors.WallCreator;

public class WallCmd extends PlaceCommand {
	@Override
	public void execu(WEArea a,String[] args,User u,Player p){
		final Material[] to=parseIn(args[0]);

		if(to==null){
			new Msg(u,WGC._PREFIX_2_Edit).b("Unknown material.").a(p); return;
		}
		boolean upd=false;

		if(args.length>1)
			for(int i=1; i<args.length; i++)
				if(args[i]=="+u") upd=true;

		AsyncMultiEditor aso;

		if(to.length<2) aso=WallCreator.e(a.w,a.toBigArea(),to[0],upd,u);
		else aso=WallCreator.e(a.w,a.toBigArea(),to,upd,u);
		if(u.saveUndo) u.saveUndo(aso.getUndo(),"wall-"+to[0]);
		aso.exe();
	}
}
