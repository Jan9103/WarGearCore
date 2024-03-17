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

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.craftbukkit.v1_19_R3.CraftWorld;

import de.jan9103.wargearcore.coordSys.BigArea;
import de.jan9103.wargearcore.worldedit.editors.WeSender;
import net.minecraft.core.BlockPosition;

/**
 * fill(arg.air) & rep(arg.rest)
 * @author Jan9103
 * @since Jul 11, 2020
 * Lizence: CC BY-NC-ND 4.0 ( https://creativecommons.org/licenses/by-nc-nd/4.0/ )
 */
public class AsyncInvOaO extends AsyncEditor3 {
	private final Material a,r;
	private final CraftWorld cw;
	public AsyncInvOaO(World world,BigArea area,Material air,Material rest,boolean upd,WeSender s){
		super(world,area,upd,s); cw=(CraftWorld)w; a=air; r=rest;
	}

	@Override
	public void exe(int xl,int xh,int yl,int yh,int zl,int zh){
		for(int x=xl; x<=xh; x++)
			for(int y=yl; y<=yh; y++)
				for(int z=zl; z<=zh; z++){
					final Block b=w.getBlockAt(x,y,z);
					if(b.getType()==Material.AIR){
						b.setType(a,u); continue;
					}
					// if(b.getState() instanceof TileState) cw.getHandle().removeTileEntity(new BlockPosition(x,y,z));
					b.setType(r,u);
				}
	}
}
