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
package de.jan9103.wargearcore.worldedit.editors;

import org.bukkit.Material;
import org.bukkit.World;

import de.jan9103.wargearcore.coordSys.BigArea;

public class SyncFill {
	public static void b(World w,BigArea a,Material to,boolean upd){
		for(int x=a.low.x; x<=a.high.x; x++)
			for(int y=a.low.y; y<=a.high.y; y++)
				for(int z=a.low.z; z<=a.high.z; z++)
					if(w.getBlockAt(x,y,z).getType()==Material.AIR) w.getBlockAt(x,y,z).setType(to,upd);
	}

	public static void b(World w,int x1,int x2,int y1,int y2,int z1,int z2,Material to,boolean upd){
		for(int x=x1; x<=x2; x++)
			for(int y=y1; y<=y2; y++)
				for(int z=z1; z<=z2; z++)
					if(w.getBlockAt(x,y,z).getType()==Material.AIR) w.getBlockAt(x,y,z).setType(to,upd);
	}
}
