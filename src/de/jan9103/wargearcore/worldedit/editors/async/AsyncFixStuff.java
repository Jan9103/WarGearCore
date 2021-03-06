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
package de.jan9103.wargearcore.worldedit.editors.async;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Fence;
import org.bukkit.block.data.type.GlassPane;

import de.jan9103.wargearcore.coordSys.BigArea;
import de.jan9103.wargearcore.worldedit.editors.WeSender;

public class AsyncFixStuff extends AsyncEditor3 {
	public AsyncFixStuff(World we,BigArea ar,boolean upd,WeSender se){
		super(we,ar,upd,se);
	}

	@Override public void exe(int xl,int xh,int yl,int yh,int zl,int zh){
		for(int x=xl; x<=xh; x++)
			for(int y=yl; y<=yh; y++)
				for(int z=zl; z<=zh; z++){
					Block     b =w.getBlockAt(x,y,z);
					BlockData bd=b.getBlockData();
					if(bd==null) continue;
					if(bd instanceof Fence){
						Fence f=(Fence)bd;
						if(!b.getRelative(BlockFace.NORTH).isPassable())
							f.setFace(BlockFace.NORTH,true);
						if(!b.getRelative(BlockFace.SOUTH).isPassable())
							f.setFace(BlockFace.SOUTH,true);
						if(!b.getRelative(BlockFace.EAST).isPassable())
							f.setFace(BlockFace.EAST,true);
						if(!b.getRelative(BlockFace.WEST).isPassable())
							f.setFace(BlockFace.WEST,true);
						b.setBlockData(f);
						continue;
					}
					if(bd instanceof GlassPane){
						GlassPane f=(GlassPane)bd;
						if(!b.getRelative(BlockFace.NORTH).isPassable())
							f.setFace(BlockFace.NORTH,true);
						if(!b.getRelative(BlockFace.SOUTH).isPassable())
							f.setFace(BlockFace.SOUTH,true);
						if(!b.getRelative(BlockFace.EAST).isPassable())
							f.setFace(BlockFace.EAST,true);
						if(!b.getRelative(BlockFace.WEST).isPassable())
							f.setFace(BlockFace.WEST,true);
						b.setBlockData(f);
						continue;
					}
					// https://hub.spigotmc.org/javadocs/spigot/org/bukkit/block/data/type/Wall.html
					// https://hub.spigotmc.org/javadocs/spigot/org/bukkit/block/data/type/Stairs.html
				}
	}

	//TODO custom undo
}
