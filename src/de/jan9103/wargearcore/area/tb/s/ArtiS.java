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
package de.jan9103.wargearcore.area.tb.s;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Gate;

import de.jan9103.wargearcore.area.ATheme;
import de.jan9103.wargearcore.area.ThemeMat;
import de.jan9103.wargearcore.area.WgArea;
import de.jan9103.wargearcore.coordSys.BigArea;
import de.jan9103.wargearcore.worldedit.editors.AsyncMultiEditor;
import de.jan9103.wargearcore.worldedit.editors.WeSender;
import de.jan9103.wargearcore.worldedit.editors.async.AsyncAir;
import de.jan9103.wargearcore.worldedit.editors.async.AsyncFillA;
import de.jan9103.wargearcore.worldedit.editors.async.AsyncFillO;
import de.jan9103.wargearcore.worldedit.editors.async.AsyncFillOD;
import de.jan9103.wargearcore.worldedit.editors.async.AsyncSetO;

public class ArtiS extends Shield {
	private final boolean fs;
	private final byte d;
	public ArtiS(String[] a)throws NumberFormatException {
		d =a.length>2?Byte.parseByte(a[2]):2;
		fs=a.length>3?a[3].equalsIgnoreCase("on"):true;
	}

	public ArtiS(int dist,boolean fall){
		fs=fall;
		d =(byte)dist;
	}

	@Override public void aLater(AsyncMultiEditor e,WgArea a){
		final Gate fg=(Gate)Bukkit.createBlockData(ATheme.get(a.thm,ThemeMat.OWN_GATE,true));

		fg.setFacing(BlockFace.EAST);
		e.a(new AsyncFillOD(a.w,new BigArea(a.redWg.low.x,a.redWg.high.y+a.ausfahrm,a.redWg.low.z+d,a.redWg.high.x,a.redWg.high.y+a.ausfahrm,a.redWg.low.z+d),fg,false,e))
		.a(new AsyncFillO(a.w,new BigArea(a.redWg.low.x,a.redWg.high.y+a.ausfahrm-1,a.redWg.low.z+d,a.redWg.high.x,a.redWg.high.y,a.redWg.low.z+d),ATheme.get(a.thm,ThemeMat.OWN_THIN_SHIELD,true),false,e));
		if(d<0) //vorgesetzt
			e.a(new AsyncFillA(a.w,new BigArea(a.redWg.low.x,a.redWg.high.y-1,a.redWg.low.z+d,a.redWg.high.x,a.redWg.high.y,a.redWg.low.z),new Material[] {Material.SLIME_BLOCK,Material.END_STONE,Material.PISTON,ATheme.get(a.thm,ThemeMat.OWN_LIGHT,true),ATheme.get(a.thm,ThemeMat.OWN_DARK,true)},false,e));
		else if(fs)
			e.a(new AsyncSetO(a.w,new BigArea(a.redWg.low.x,a.redWg.high.y-a.ausfahrm+1,a.redWg.low.z+d-1,a.redWg.high.x,a.redWg.high.y,a.redWg.low.z+d-1),Material.AIR,false,e))
			.a(new AsyncSetO(a.w,new BigArea(a.redWg.low.x,a.redWg.high.y-a.ausfahrm,a.redWg.low.z+d-1,a.redWg.high.x,a.redWg.high.y-a.ausfahrm,a.redWg.low.z+d-1),ATheme.get(a.thm,ThemeMat.OWN_SLAB,true),false,e));
	}

	@Override public void b(WgArea a,WeSender s){
		final AsyncMultiEditor e=new AsyncMultiEditor(s);

		e.a(new AsyncAir(a.w,new BigArea(a.redWg.low.x,a.redWg.high.y+1,a.redWg.low.z+d,a.redWg.high.x,a.redWg.high.y+a.ausfahrm,a.redWg.low.z+d),false,e));
		if(d<0) //vorgesetzt
			e.a(new AsyncAir(a.w,new BigArea(a.redWg.low.x,a.redWg.high.y-1,a.redWg.low.z+d,a.redWg.high.x,a.redWg.high.y,a.redWg.low.z),false,e));
		else if(fs)
			e.a(new AsyncAir(a.w,new BigArea(a.redWg.low.x,a.redWg.high.y-a.ausfahrm,a.redWg.low.z+d-1,a.redWg.high.x,a.redWg.high.y-1,a.redWg.low.z+d-1),false,e))
			.a(new AsyncAir(a.w,new BigArea(a.redWg.low.x,a.redWg.high.y,a.redWg.low.z+d-1,a.redWg.high.x,a.redWg.high.y,a.redWg.low.z+d-1),false,e));
		e.exe();
	}

	@Override public String toString(){
		return "as "+d+" "+(fs?"on":"off");
	}
}
