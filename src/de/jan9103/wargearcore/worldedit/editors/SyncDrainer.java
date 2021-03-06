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
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Waterlogged;

import de.jan9103.wargearcore.coordSys.BigCoord;

public class SyncDrainer extends WorldEditor {
	private int m;
	public SyncDrainer(World w,BigCoord s,int max){
		m=max; sub(s.getLocation(w).getBlock());
	}

	public SyncDrainer(Block b,int max){
		m=max; sub(b);
	}

	private void sub(Block b){
		if(b.getType()==Material.AIR) return;

		if(b.isLiquid())
			b.setType(Material.AIR,b.getRelative(BlockFace.DOWN).getType()==Material.WATER);
		else{
			final BlockData bd=b.getBlockData();
			if(!(bd instanceof Waterlogged)) return;

			final Waterlogged wl=(Waterlogged)bd;
			if(wl.isWaterlogged()){
				wl.setWaterlogged(false);
				b.setBlockData(wl,b.getRelative(BlockFace.DOWN).getType()==Material.WATER);
			}
		}
		if(m--<=0) return;

		sub(b.getRelative(BlockFace.UP));
		sub(b.getRelative(BlockFace.SOUTH));
		sub(b.getRelative(BlockFace.WEST));
		sub(b.getRelative(BlockFace.EAST));
		sub(b.getRelative(BlockFace.NORTH));
	}
}
