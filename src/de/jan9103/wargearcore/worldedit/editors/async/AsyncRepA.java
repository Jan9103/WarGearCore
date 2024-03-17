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

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.craftbukkit.v1_19_R3.CraftWorld;

import de.jan9103.wargearcore.coordSys.BigArea;
import de.jan9103.wargearcore.worldedit.editors.Undo;
import de.jan9103.wargearcore.worldedit.editors.WeSender;
import net.minecraft.core.BlockPosition;

public class AsyncRepA extends AsyncEditor3 {
	public final Material[] to;
	public final Random r;
	private final CraftWorld cw;
	public AsyncRepA(World world,BigArea area,Material[] t,boolean u,WeSender s){
		super(world,area,u,s); to=t; r=new Random(); cw=(CraftWorld)w;
	}

	@Override
	public void exe(int xl,int xh,int yl,int yh,int zl,int zh){
		for(int x=xl; x<=xh; x++)
			for(int y=yl; y<=yh; y++)
				for(int z=zl; z<=zh; z++){
					final Block b=w.getBlockAt(x,y,z);
					if(b.getType()==Material.AIR) continue;
					// if(b.getState() instanceof TileState) cw.getHandle().removeTileEntity(new BlockPosition(x,y,z));
					b.setType(to[r.nextInt(to.length)],u);
				}
	}

	@Override public void prepUndo(Undo i){
		for(int x=xl; x<=xh; x++)
			for(int y=yl; y<=yh; y++)
				for(int z=zl; z<=zh; z++)
					if(i.unset(x,y,z)){
						final Block b=w.getBlockAt(x,y,z);
						if(b.getType()!=Material.AIR) i.sB(b);
					}
	}
}
