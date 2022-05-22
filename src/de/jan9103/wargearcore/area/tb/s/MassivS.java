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
import org.bukkit.block.BlockFace;

import de.jan9103.wargearcore.area.ATheme;
import de.jan9103.wargearcore.area.ThemeMat;
import de.jan9103.wargearcore.area.WgArea;
import de.jan9103.wargearcore.coordSys.BigArea;
import de.jan9103.wargearcore.worldedit.editors.AsyncMultiEditor;
import de.jan9103.wargearcore.worldedit.editors.WeSender;
import de.jan9103.wargearcore.worldedit.editors.async.AsyncFillA;
import de.jan9103.wargearcore.worldedit.editors.async.AsyncSetO;

public class MassivS extends Shield {
	private final BlockFace b;
	public MassivS(BlockFace dir){
		b=dir;
	}

	@Override public void aLater(AsyncMultiEditor c,WgArea a){
		Material[] mat=new Material[] {ATheme.get(a.thm,ThemeMat.OWN_DARK,true),ATheme.get(a.thm,ThemeMat.OWN_LIGHT,true)};
		switch(b){
		case UP:
		case DOWN:         //ds
			c.a(new AsyncSetO(a.w,new BigArea(a.redWg.low.x+1,a.redWg.high.y,a.redWg.low.z+1,a.redWg.high.x-1,a.redWg.high.y+a.ausfahrm-1,a.redWg.high.z-1),Material.END_STONE,false,c));
			c.a(new AsyncFillA(a.w,new BigArea(a.redWg.low.x+1,a.redWg.high.y+a.ausfahrm,a.redWg.low.z+1,a.redWg.high.x-1,a.redWg.high.y+a.ausfahrm,a.redWg.high.z-1),mat,false,c));
			break;

		case EAST:         //higer x
			c.a(new AsyncSetO(a.w,new BigArea(a.redWg.high.x,a.redWg.low.y,a.redWg.low.z+1,a.redWg.high.x+a.ausfahrm-1,a.redWg.high.y-1,a.redWg.high.z-1),Material.END_STONE,false,c));
			c.a(new AsyncFillA(a.w,new BigArea(a.redWg.high.x+a.ausfahrm,a.redWg.low.y,a.redWg.low.z+1,a.redWg.high.x+a.ausfahrm,a.redWg.high.y-1,a.redWg.high.z-1),mat,false,c));
			break;

		case NORTH:         //bs
			c.a(new AsyncSetO(a.w,new BigArea(a.redWg.low.x+1,a.redWg.low.y,a.redWg.high.z,a.redWg.high.x-1,a.redWg.high.y-1,a.redWg.high.z+a.ausfahrm-1),Material.END_STONE,false,c));
			c.a(new AsyncFillA(a.w,new BigArea(a.redWg.low.x+1,a.redWg.low.y,a.redWg.high.z+a.ausfahrm,a.redWg.high.x-1,a.redWg.high.y-1,a.redWg.high.z+a.ausfahrm),mat,false,c));
			break;

		case WEST:         //lower x
			c.a(new AsyncSetO(a.w,new BigArea(a.redWg.low.x,a.redWg.low.y,a.redWg.low.z+1,a.redWg.low.x-a.ausfahrm+1,a.redWg.high.y-1,a.redWg.high.z-1),Material.END_STONE,false,c));
			c.a(new AsyncFillA(a.w,new BigArea(a.redWg.low.x-a.ausfahrm,a.redWg.low.y,a.redWg.low.z+1,a.redWg.low.x-a.ausfahrm,a.redWg.high.y-1,a.redWg.high.z-1),mat,false,c));
			break;

		default:         //front
			c.a(new AsyncSetO(a.w,new BigArea(a.redWg.low.x+1,a.redWg.low.y,a.redWg.low.z,a.redWg.high.x-1,a.redWg.high.y-1,a.redWg.low.z-a.ausfm_front+1),Material.END_STONE,false,c));
			c.a(new AsyncFillA(a.w,new BigArea(a.redWg.low.x+1,a.redWg.low.y,a.redWg.low.z-a.ausfm_front,a.redWg.high.x-1,a.redWg.high.y-1,a.redWg.low.z-a.ausfm_front),mat,false,c));
		}
	}

	@Override public void b(WgArea a,WeSender s){
		final AsyncMultiEditor c=new AsyncMultiEditor(s);

		switch(b){
		case UP:
		case DOWN:         //ds
			c.a(new AsyncSetO(a.w,new BigArea(a.redWg.low.x+1,a.redWg.high.y,a.redWg.low.z+1,a.redWg.high.x-1,a.redWg.high.y+a.ausfahrm,a.redWg.high.z-1),Material.END_STONE,false,c));
			break;

		case EAST:         //higer x
			c.a(new AsyncSetO(a.w,new BigArea(a.redWg.high.x,a.redWg.low.y,a.redWg.low.z+1,a.redWg.high.x+a.ausfahrm,a.redWg.high.y-1,a.redWg.high.z-1),Material.END_STONE,false,c));
			break;

		case NORTH:         //bs
			c.a(new AsyncSetO(a.w,new BigArea(a.redWg.low.x+1,a.redWg.low.y,a.redWg.high.z,a.redWg.high.x-1,a.redWg.high.y-1,a.redWg.high.z+a.ausfahrm),Material.END_STONE,false,c));
			break;

		case WEST:         //lower x
			c.a(new AsyncSetO(a.w,new BigArea(a.redWg.low.x,a.redWg.low.y,a.redWg.low.z+1,a.redWg.low.x-a.ausfahrm,a.redWg.high.y-1,a.redWg.high.z-1),Material.END_STONE,false,c));
			break;

		default:         //front
			c.a(new AsyncSetO(a.w,new BigArea(a.redWg.low.x+1,a.redWg.low.y,a.redWg.low.z,a.redWg.high.x-1,a.redWg.high.y-1,a.redWg.low.z-a.ausfahrm),Material.END_STONE,false,c));
		}
		c.exe();
	}

	@Override public String toString(){
		return "m "+b.name();
	}
}
