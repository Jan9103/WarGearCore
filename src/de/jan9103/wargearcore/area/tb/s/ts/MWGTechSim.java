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
package de.jan9103.wargearcore.area.tb.s.ts;

import java.util.Random;

import org.bukkit.Material;

import de.jan9103.wargearcore.area.WgArea;
import de.jan9103.wargearcore.coordSys.BigArea;
import de.jan9103.wargearcore.worldedit.editors.AsyncMultiEditor;
import de.jan9103.wargearcore.worldedit.editors.async.AsyncAir;
import de.jan9103.wargearcore.worldedit.editors.async.AsyncSetA;
import de.jan9103.wargearcore.worldedit.editors.async.AsyncSetO;

public class MWGTechSim extends TechSimGen {
	public MWGTechSim(Material[] m,Material[] t,BigArea w,Random r,AsyncMultiEditor a,WgArea k){
		super(m,t,w,r,a,k);
	}

	@Override public void gen(){
		switch(r.nextInt(2)){ // ----------------------- UNTEN AUSSEN
		case 1:               //(S-)LS
			dualLs(r.nextInt(3)+1,r.nextInt(4)+1,r.nextInt(4)+3,r.nextBoolean()?2:3,4+r.nextInt(4));
			break;

		default:        //PlatformSS
			platformSS(r.nextInt(4)+1,b.low.y);
			break;
		}
		//TODO oben 2x arti/ds/morser(/ts)/smarti (+vorgesetzt)
		//TODO unten vorne 2x lupf/ halbauto/ hochgesetzte lupf/mittelstich/nix
		//TODO mittle unten: sls (ha)
		//TODO 2mehr (?)
	}

	private void dualLs(int x,int z,int zLen,int techw,int height){
		//LEFT
		ame.a(new AsyncAir(a.w,new BigArea(b.low.x+x,b.low.y+1,b.high.z-z,b.low.x+x,b.low.y+height,b.low.z),false,ame))                      //UNTEN (LAUF+KNONE)
		.a(new AsyncAir(a.w,new BigArea(b.low.x+x,b.low.y+height+1,b.high.z-z-zLen,b.low.x+x,b.low.y+10,b.low.z),false,ame))                 //OBEN (LAUF)
		.a(new AsyncSetO(a.w,new BigArea(b.low.x+x,b.low.y+1,b.high.z-z-zLen),Material.BREWING_STAND,false,ame))                             //BS
		.a(new AsyncSetO(a.w,new BigArea(b.low.x+x,b.low.y+1,b.high.z-z),Material.WATER,true,ame))                                           //WATER
		.a(new AsyncSetA(a.w,new BigArea(b.low.x+x+1,b.low.y+1,b.high.z-z,b.low.x+x+techw,b.low.y+height,b.high.z-z-zLen),tech,false,ame))   //TECH
		//RIGHT
		.a(new AsyncAir(a.w,new BigArea(b.high.x-x,b.low.y+1,b.high.z-z,b.high.x-x,b.low.y+height,b.low.z),false,ame))                       //UNTEN (LAUF+KNONE)
		.a(new AsyncAir(a.w,new BigArea(b.high.x-x,b.low.y+height+1,b.high.z-z-zLen,b.high.x-x,b.low.y+10,b.low.z),false,ame))               //OBEN (LAUF)
		.a(new AsyncSetO(a.w,new BigArea(b.high.x-x,b.low.y+1,b.high.z-z-zLen),Material.BREWING_STAND,false,ame))                            //BS
		.a(new AsyncSetA(a.w,new BigArea(b.high.x-x-1,b.low.y+1,b.high.z-z,b.high.x-x-techw,b.low.y+height,b.high.z-z-zLen),tech,false,ame)) //TECH
		.a(new AsyncSetO(a.w,new BigArea(b.high.x-x,b.low.y+1,b.high.z-z),Material.WATER,true,ame));                                         //WATER
	}

	/**
	 *
	 * @param z r.nextInt(4)+1
	 * @param y b.low.y +y ueber boden (boden: 0)
	 */
	private void platformSS(int z,int y){
		// LEFT
		ame.a(new AsyncSetA(a.w,new BigArea(b.low.x-1,y,b.high.z-z,b.low.x-1,y,b.high.z-z-2),mat,false,ame))    // BODEN
		.a(new AsyncSetO(a.w,new BigArea(b.low.x-1,y+1,b.high.z-z-2),Material.BREWING_STAND,false,ame))         // BS
		.a(new AsyncSetA(a.w,new BigArea(b.low.x-2,y+1,b.high.z-z,b.low.x-2,y+4,b.high.z-z-2),mat,false,ame))   // WAND
		.a(new AsyncSetO(a.w,new BigArea(b.low.x-1,y+1,b.high.z-z+1),mat[0],false,ame))                         //wasserhalter hinten
		.a(new AsyncSetO(a.w,new BigArea(b.low.x,y+2,b.high.z-z),Material.WATER,true,ame))                      //Water
		.a(new AsyncAir(a.w,new BigArea(b.low.x,y+3,b.high.z-z,b.low.x+3,y+6,b.high.z-z),false,ame))            //tribkammer
		.a(new AsyncAir(a.w,new BigArea(b.low.x,y+2,b.high.z-z-2,b.low.x+3,y+6,b.high.z-z-2),false,ame))        //projektilkammer
		// RIGHT
		.a(new AsyncSetA(a.w,new BigArea(b.high.x+1,y,b.high.z-z,b.high.x+1,y,b.high.z-z-2),mat,false,ame))     // BODEN
		.a(new AsyncSetO(a.w,new BigArea(b.high.x+1,y+1,b.high.z-z-2),Material.BREWING_STAND,false,ame))        // BS
		.a(new AsyncSetA(a.w,new BigArea(b.high.x+2,y+1,b.high.z-z,b.high.x+2,y+4,b.high.z-z-2),mat,false,ame)) // WAND
		.a(new AsyncSetO(a.w,new BigArea(b.high.x+1,y+1,b.high.z-z+1),mat[0],false,ame))                        //wasserhalter hinten
		.a(new AsyncSetO(a.w,new BigArea(b.high.x,y+2,b.high.z-z),Material.WATER,true,ame))                     //Water
		.a(new AsyncAir(a.w,new BigArea(b.high.x,y+3,b.high.z-z,b.high.x-3,y+6,b.high.z-z),false,ame))          //tribkammer
		.a(new AsyncAir(a.w,new BigArea(b.high.x,y+2,b.high.z-z-2,b.high.x-3,y+6,b.high.z-z-2),false,ame));     //projektilkammer
	}
}
