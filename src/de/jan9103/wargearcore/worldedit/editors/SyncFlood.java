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
import org.bukkit.block.Block;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.craftbukkit.v1_19_R3.CraftWorld;

import de.jan9103.wargearcore.coordSys.BigArea;
import net.minecraft.core.BlockPosition;

public class SyncFlood {
	public static void b(World w,BigArea a){
		final CraftWorld cw=(CraftWorld)w;

		for(int x=a.low.x; x<=a.high.x; x++)
			for(int y=a.low.y; y<=a.high.y; y++)
				for(int z=a.low.z; z<=a.high.z; z++){
					final Block i=w.getBlockAt(x,y,z);
					if(i.getType()==Material.AIR){
						w.getBlockAt(x,y,z).setType(Material.WATER,false); continue;
					}
					if(!i.getType().isSolid()){
						// cw.getHandle().removeTileEntity(new BlockPosition(x,y,z));
						w.getBlockAt(x,y,z).setType(Material.WATER,false); continue;
					}
					if(i.getBlockData() instanceof Waterlogged){
						final Waterlogged m=(Waterlogged)i.getBlockData();
						m.setWaterlogged(true);
						i.setBlockData(m,false);
					}
				}
	}
}
