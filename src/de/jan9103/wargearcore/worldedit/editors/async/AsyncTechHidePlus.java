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
import org.bukkit.block.BlockFace;
import org.bukkit.block.TileState;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;

import de.jan9103.wargearcore.coordSys.BigArea;
import de.jan9103.wargearcore.util.TPS;
import de.jan9103.wargearcore.worldedit.editors.WeSender;
import net.minecraft.server.v1_15_R1.BlockPosition;

public class AsyncTechHidePlus extends AsyncEditor3 {
	private final CraftWorld cw;
	private final Material[] exclude;
	public AsyncTechHidePlus(World we,BigArea ar,boolean upd,WeSender se,Material[] excl){
		super(we,ar,upd,se); cw=(CraftWorld)we; exclude=excl;
	}

	@Override public boolean b(){
		TPS.ign(); return true;
	}

	@Override public void exe(int xl,int xh,int yl,int yh,int zl,int zh){
		if(yl<1) yl=1;
		if(yh>=w.getMaxHeight()) yh=w.getMaxHeight()-2;
		for(int x=xl; x<=xh; x++)
			for(int y=yl; y<=yh; y++)
				zloop : for(int z=zl; z<=zh; z++){
					final Block b=w.getBlockAt(x,y,z);
					Material    m=b.getType();
					for(Material i:exclude)
						if(i==m) continue zloop;
					switch(m){
					// CAN BE IN GANG
					// -- can be on wall / sealing
					case LEVER:
					case OAK_BUTTON:
					case DARK_OAK_BUTTON:
					case ACACIA_BUTTON:
					case JUNGLE_BUTTON:
					case BIRCH_BUTTON:
					case STONE_BUTTON:
					case REDSTONE_WALL_TORCH:
					case TRIPWIRE_HOOK:
						if(b.getRelative(BlockFace.DOWN).isPassable()){
							b.setType(Material.AIR);
							continue;
						}

					// -- has to be on ground
					case OAK_PRESSURE_PLATE:
					case BIRCH_PRESSURE_PLATE:
					case DARK_OAK_PRESSURE_PLATE:
					case ACACIA_PRESSURE_PLATE:
					case JUNGLE_PRESSURE_PLATE:
					case HEAVY_WEIGHTED_PRESSURE_PLATE:
					case LIGHT_WEIGHTED_PRESSURE_PLATE:
					case REDSTONE_TORCH:
					case COMPARATOR:
					case REPEATER:
					case REDSTONE_WIRE: {
						Block up=b.getRelative(BlockFace.UP);
						b.setType(isGang(up)?Material.AIR:(up.getBlockData()==null?up.getType():Material.END_STONE),u);
						continue;
					}

					case STRING: b.setType(Material.AIR); continue;

					case NOTE_BLOCK:
					case JUKEBOX:
					case HOPPER:
					case DROPPER:
					case DISPENSER:
					case STICKY_PISTON:
					case PISTON:
					case PISTON_HEAD:
					case LECTERN:
					case SLIME_BLOCK:
					case HONEY_BLOCK:
					case FURNACE:
					case WATER:
						b.setType(Material.END_STONE); continue;

					case CHEST:
					case TRAPPED_CHEST:
					case OAK_SIGN:
					case OAK_WALL_SIGN:
					case SPRUCE_SIGN:
					case SPRUCE_WALL_SIGN:
					case BIRCH_SIGN:
					case BIRCH_WALL_SIGN:
					case JUNGLE_SIGN:
					case JUNGLE_WALL_SIGN:
					case ACACIA_SIGN:
					case ACACIA_WALL_SIGN: {
						if(b.getState() instanceof TileState) cw.getHandle().removeTileEntity(new BlockPosition(x,y,z));
						continue;
					}

					default:
					}
				}
	}

	private boolean isGang(Block b){
		switch(b.getType()){
		case RED_CARPET:
		case BLUE_CARPET:
		case LIME_CARPET:
		case ORANGE_CARPET:
		case PINK_CARPET:
		case MAGENTA_CARPET:
		case GREEN_CARPET:
		case YELLOW_CARPET:
		case WHITE_CARPET:
		case BLACK_CARPET:
		case GRAY_CARPET:
		case BROWN_CARPET:
		case CYAN_CARPET:
		case LIGHT_BLUE_CARPET:
		case LIGHT_GRAY_CARPET:
			return true;

		default: return b.isPassable();
		}
	}
}