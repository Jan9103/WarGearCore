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

import org.bukkit.Material;

import de.jan9103.wargearcore.area.ATheme;
import de.jan9103.wargearcore.area.ThemeMat;
import de.jan9103.wargearcore.area.WgArea;
import de.jan9103.wargearcore.coordSys.BigArea;
import de.jan9103.wargearcore.worldedit.editors.AsyncMultiEditor;
import de.jan9103.wargearcore.worldedit.editors.WeSender;
import de.jan9103.wargearcore.worldedit.editors.async.AsyncFillA;
import de.jan9103.wargearcore.worldedit.editors.async.AsyncFillO;
import de.jan9103.wargearcore.worldedit.editors.async.AsyncSetO;

public class KiesS extends Shield {
	@Override public void aLater(AsyncMultiEditor e,WgArea a){
		final Material[] ms=new Material[] {ATheme.get(a.thm,ThemeMat.OWN_SAND,true),ATheme.get(a.thm,ThemeMat.OWN_POWDER_DARK,true),ATheme.get(a.thm,ThemeMat.OWN_POWDER_LIGHT,true)};

		e.a(new AsyncFillO(a.w,new BigArea(a.redWg.low.x,a.redWg.high.y-1,a.redWg.low.z,a.redWg.high.x,a.redWg.high.y-1,a.redWg.low.z-a.ausfm_front+1),Material.END_STONE,false,e))
		.a(new AsyncFillO(a.w,new BigArea(a.redWg.low.x,a.redWg.high.y,a.redWg.low.z,a.redWg.high.x,a.redWg.high.y,a.redWg.low.z-a.ausfm_front),ms[0],false,e))
		.a(new AsyncFillA(a.w,new BigArea(a.redWg.low.x,a.redWg.low.y,a.redWg.low.z-a.ausfm_front,a.redWg.high.x,a.redWg.high.y-1,a.redWg.low.z-a.ausfm_front),ms,false,e));
	}

	@Override public void b(WgArea a,WeSender s){
		final BigArea ba=new BigArea(a.redWg.low.x,a.redWg.low.y,a.redWg.low.z-1,a.redWg.high.x,a.redWg.high.y,a.redWg.low.z-a.ausfm_front);

		new AsyncSetO(a.w,ba,Material.AIR,false,s).a();
	}

	@Override public String toString(){
		return "k";
	}
}
