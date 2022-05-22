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
package de.jan9103.wargearcore.worldedit.schem;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected.Half;
import org.bukkit.block.data.type.Ladder;
import org.bukkit.block.data.type.Leaves;
import org.bukkit.block.data.type.Observer;
import org.bukkit.block.data.type.Piston;
import org.bukkit.block.data.type.RedstoneWallTorch;
import org.bukkit.block.data.type.RedstoneWire;
import org.bukkit.block.data.type.Repeater;
import org.bukkit.block.data.type.Slab;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.block.data.type.Stairs.Shape;

import de.jan9103.wargearcore.area.ThemeMat;

public class SchemUtil {
	public static byte tm(ThemeMat i){
		switch(i){
		case OWN_LIGHT: return 1;

		case OWN_MARKER: return 2;

		case OWN_SAND: return 3;

		case OWN_SLAB: return 5;

		case OWN_SLAB_HIGH: return 6;

		case OWN_GATE: return 7;

		case OWN_THIN_SHIELD: return 8;

		case OWN_BANNER_DARK: return 9;

		case OWN_BANNER_LIGHT: return 10;

		case OWN_CARPET_LIGHT: return 11;

		case OWN_CARPET_DARK: return 12;

		case OWN_CONCRETE_LIGHT: return 13;

		case OWN_CONCRETE_DARK: return 14;

		case OWN_GLASS_LIGHT: return 15;

		case OWN_GLASS_DARK: return 16;

		case OWN_PANE_LIGHT: return 17;

		case OWN_PANE_DARK: return 18;

		case OWN_POWDER_LIGHT: return 19;

		case OWN_POWDER_DARK: return 20;

		case OWN_SHULKER_LIGHT: return 21;

		case OWN_SHULKER_DARK: return 22;

		case OWN_GLAZED_LIGHT: return 23;

		case OWN_GLAZED_DARK: return 24;

		case OWN_TERRA_LIGHT: return 25;

		case OWN_TERRA_DARK: return 26;

		case OWN_ARENA: return 27;

		case ENEMY_DARK: return 28;

		case ENEMY_LIGHT: return 29;

		case ENEMY_MARKER: return 30;

		case ENEMY_SAND: return 31;

		case ENEMY_SLAB: return 33;

		case ENEMY_SLAB_HIGH: return 34;

		case ENEMY_GATE: return 35;

		case ENEMY_THIN_SHIELD: return 36;

		case ENEMY_BANNER_DARK: return 37;

		case ENEMY_BANNER_LIGHT: return 38;

		case ENEMY_CARPET_LIGHT: return 39;

		case ENEMY_CARPET_DARK: return 40;

		case ENEMY_CONCRETE_LIGHT: return 41;

		case ENEMY_CONCRETE_DARK: return 42;

		case ENEMY_GLASS_LIGHT: return 43;

		case ENEMY_GLASS_DARK: return 44;

		case ENEMY_PANE_LIGHT: return 45;

		case ENEMY_PANE_DARK: return 46;

		case ENEMY_POWDER_LIGHT: return 47;

		case ENEMY_POWDER_DARK: return 48;

		case ENEMY_SHULKER_LIGHT: return 49;

		case ENEMY_SHULKER_DARK: return 50;

		case ENEMY_GLAZED_LIGHT: return 51;

		case ENEMY_GLAZED_DARK: return 52;

		case ENEMY_TERRA_LIGHT: return 53;

		case ENEMY_TERRA_DARK: return 54;

		case ENEMY_ARENA: return 55;

		case ARENA_HIGHLIGHT: return 56;

		default: return 0;
		}
	}

	public static ThemeMat tm(byte i){
		switch(i){
		case 1: return ThemeMat.OWN_LIGHT;

		case 2: return ThemeMat.OWN_MARKER;

		case 3:
		case 4: return ThemeMat.OWN_SAND;

		case 5: return ThemeMat.OWN_SLAB;

		case 6: return ThemeMat.OWN_SLAB_HIGH;

		case 7: return ThemeMat.OWN_GATE;

		case 8: return ThemeMat.OWN_THIN_SHIELD;

		case 9: return ThemeMat.OWN_BANNER_DARK;

		case 10: return ThemeMat.OWN_BANNER_LIGHT;

		case 11: return ThemeMat.OWN_CARPET_LIGHT;

		case 12: return ThemeMat.OWN_CARPET_DARK;

		case 13: return ThemeMat.OWN_CONCRETE_LIGHT;

		case 14: return ThemeMat.OWN_CONCRETE_DARK;

		case 15: return ThemeMat.OWN_GLASS_LIGHT;

		case 16: return ThemeMat.OWN_GLASS_DARK;

		case 17: return ThemeMat.OWN_PANE_LIGHT;

		case 18: return ThemeMat.OWN_PANE_DARK;

		case 19: return ThemeMat.OWN_POWDER_LIGHT;

		case 20: return ThemeMat.OWN_POWDER_DARK;

		case 21: return ThemeMat.OWN_SHULKER_LIGHT;

		case 22: return ThemeMat.OWN_SHULKER_DARK;

		case 23: return ThemeMat.OWN_GLAZED_LIGHT;

		case 24: return ThemeMat.OWN_GLAZED_DARK;

		case 25: return ThemeMat.OWN_TERRA_LIGHT;

		case 26: return ThemeMat.OWN_TERRA_DARK;

		case 27: return ThemeMat.OWN_ARENA;

		case 28: return ThemeMat.ENEMY_DARK;

		case 29: return ThemeMat.ENEMY_LIGHT;

		case 30: return ThemeMat.ENEMY_MARKER;

		case 31:
		case 32: return ThemeMat.ENEMY_SAND;

		case 33: return ThemeMat.ENEMY_SLAB;

		case 34: return ThemeMat.ENEMY_SLAB_HIGH;

		case 35: return ThemeMat.ENEMY_GATE;

		case 36: return ThemeMat.ENEMY_THIN_SHIELD;

		case 37: return ThemeMat.ENEMY_BANNER_DARK;

		case 38: return ThemeMat.ENEMY_BANNER_LIGHT;

		case 39: return ThemeMat.ENEMY_CARPET_LIGHT;

		case 40: return ThemeMat.ENEMY_CARPET_DARK;

		case 41: return ThemeMat.ENEMY_CONCRETE_LIGHT;

		case 42: return ThemeMat.ENEMY_CONCRETE_DARK;

		case 43: return ThemeMat.ENEMY_GLASS_LIGHT;

		case 44: return ThemeMat.ENEMY_GLASS_DARK;

		case 45: return ThemeMat.ENEMY_PANE_LIGHT;

		case 46: return ThemeMat.ENEMY_PANE_DARK;

		case 47: return ThemeMat.ENEMY_POWDER_LIGHT;

		case 48: return ThemeMat.ENEMY_POWDER_DARK;

		case 49: return ThemeMat.ENEMY_SHULKER_LIGHT;

		case 50: return ThemeMat.ENEMY_SHULKER_DARK;

		case 51: return ThemeMat.ENEMY_GLAZED_LIGHT;

		case 52: return ThemeMat.ENEMY_GLAZED_DARK;

		case 53: return ThemeMat.ENEMY_TERRA_LIGHT;

		case 54: return ThemeMat.ENEMY_TERRA_DARK;

		case 55: return ThemeMat.ENEMY_ARENA;

		case 56: return ThemeMat.ARENA_HIGHLIGHT;

		default: return ThemeMat.OWN_DARK;
		}
	}

	public static byte piston(Piston p){
		switch(p.getFacing()){
		case DOWN: return sBit(sBit((byte)1,7,p.isExtended()),6,p.getMaterial()==Material.STICKY_PISTON);

		case EAST: return sBit(sBit((byte)2,7,p.isExtended()),6,p.getMaterial()==Material.STICKY_PISTON);

		case NORTH: return sBit(sBit((byte)3,7,p.isExtended()),6,p.getMaterial()==Material.STICKY_PISTON);

		case SOUTH: return sBit(sBit((byte)4,7,p.isExtended()),6,p.getMaterial()==Material.STICKY_PISTON);

		case UP: return sBit(sBit((byte)5,7,p.isExtended()),6,p.getMaterial()==Material.STICKY_PISTON);

		default: return sBit(sBit((byte)6,7,p.isExtended()),6,p.getMaterial()==Material.STICKY_PISTON);
		}
	}

	public static Piston piston(byte b){
		final Piston o=(Piston)Bukkit.createBlockData(gBit(b,6)?Material.STICKY_PISTON:Material.PISTON);

		o.setExtended(gBit(b,7));
		switch(sBit(sBit(b,7,false),6,false)){
		case 1: o.setFacing(BlockFace.DOWN); return o;

		case 2: o.setFacing(BlockFace.EAST); return o;

		case 3: o.setFacing(BlockFace.NORTH); return o;

		case 4: o.setFacing(BlockFace.SOUTH); return o;

		case 5: o.setFacing(BlockFace.UP); return o;

		default: o.setFacing(BlockFace.WEST); return o;
		}
	}

	public static byte observer(Observer t){
		switch(t.getFacing()){
		case DOWN: return sBit((byte)1,7,t.isPowered());

		case EAST: return sBit((byte)2,7,t.isPowered());

		case NORTH: return sBit((byte)3,7,t.isPowered());

		case SOUTH: return sBit((byte)4,7,t.isPowered());

		case UP: return sBit((byte)5,7,t.isPowered());

		default: return sBit((byte)6,7,t.isPowered());       //WEST
		}
	}

	public static Observer observer(byte b){
		final Observer o=(Observer)Bukkit.createBlockData(Material.OBSERVER);

		o.setPowered(gBit(b,7));
		switch(sBit(b,7,false)){
		case 1: o.setFacing(BlockFace.DOWN); return o;

		case 2: o.setFacing(BlockFace.EAST); return o;

		case 3: o.setFacing(BlockFace.NORTH); return o;

		case 4: o.setFacing(BlockFace.SOUTH); return o;

		case 5: o.setFacing(BlockFace.UP); return o;

		default: o.setFacing(BlockFace.WEST); return o;
		}
	}

	public static byte leaves(Leaves l){
		return sBit((byte)l.getDistance(),7,l.isPersistent());
	}

	public static Leaves leaves(byte b,Material m){
		final Leaves o=(Leaves)Bukkit.createBlockData(m);

		o.setPersistent(gBit(b,7));
		o.setDistance(sBit(b,7,false));
		return o;
	}

	public static byte wallTorch(RedstoneWallTorch t){
		switch(t.getFacing()){
		case EAST: return sBit(sBit(sBit(1,t.isLit()),5,false),4,false);

		case NORTH: return sBit(sBit(sBit(1,t.isLit()),5,false),4,true);

		case SOUTH: return sBit(sBit(sBit(1,t.isLit()),5,true),4,false);

		default: return sBit(sBit(sBit(1,t.isLit()),5,true),4,true);
		}
	}

	public static RedstoneWallTorch wallTorch(byte b){
		final RedstoneWallTorch o=(RedstoneWallTorch)Bukkit.createBlockData(Material.REDSTONE_WALL_TORCH);

		o.setLit(gBit(b,1));
		if(gBit(b,5)) o.setFacing(gBit(b,4)?BlockFace.WEST:BlockFace.SOUTH);
		else o.setFacing(gBit(b,4)?BlockFace.NORTH:BlockFace.EAST);
		return o;
	}

	public static byte[] wire(RedstoneWire w){
		byte o=0;

		switch(w.getFace(BlockFace.SOUTH)){
		case NONE: break;

		case SIDE: o=sBit(o,0,true); break;

		default: o=sBit(o,1,true);
		}
		switch(w.getFace(BlockFace.EAST)){
		case NONE: break;

		case SIDE: o=sBit(o,2,true); break;

		default: o=sBit(o,3,true);
		}
		switch(w.getFace(BlockFace.WEST)){
		case NONE: break;

		case SIDE: o=sBit(o,4,true); break;

		default: o=sBit(o,5,true);
		}
		switch(w.getFace(BlockFace.NORTH)){
		case NONE: break;

		case SIDE: o=sBit(o,6,true); break;

		default: o=sBit(o,7,true);
		}
		return new byte[] {o,(byte)w.getPower()};
	}

	public static RedstoneWire wire(byte b,byte p){
		final RedstoneWire o=(RedstoneWire)Bukkit.createBlockData(Material.REDSTONE_WIRE);

		o.setPower(p);
		if(gBit(b,0)) o.setFace(BlockFace.SOUTH,RedstoneWire.Connection.SIDE);
		else o.setFace(BlockFace.SOUTH,gBit(b,1)?RedstoneWire.Connection.UP:RedstoneWire.Connection.NONE);
		if(gBit(b,2)) o.setFace(BlockFace.EAST,RedstoneWire.Connection.SIDE);
		else o.setFace(BlockFace.EAST,gBit(b,3)?RedstoneWire.Connection.UP:RedstoneWire.Connection.NONE);
		if(gBit(b,4)) o.setFace(BlockFace.WEST,RedstoneWire.Connection.SIDE);
		else o.setFace(BlockFace.WEST,gBit(b,5)?RedstoneWire.Connection.UP:RedstoneWire.Connection.NONE);
		if(gBit(b,6)) o.setFace(BlockFace.NORTH,RedstoneWire.Connection.SIDE);
		else o.setFace(BlockFace.NORTH,gBit(b,7)?RedstoneWire.Connection.UP:RedstoneWire.Connection.NONE);
		return o;
	}

	public static byte repeater(Repeater r){
		byte o=sBit(sBit(1,r.isPowered()),2,r.isLocked()); //geht

		switch(r.getFacing()){                             // irgendiwe ist immer 3==true && 4==true
		case EAST: o=sBit(sBit(o,3,false),4,false); break;

		case NORTH: o=sBit(sBit(o,3,false),4,true); break;

		case SOUTH: o=sBit(sBit(o,3,true),4,false); break;

		default: o=sBit(sBit(o,3,true),4,true);
		}
		switch(r.getDelay()-r.getMinimumDelay()){
		case 0: return sBit(sBit(o,5,false),6,false);

		case 1: return sBit(sBit(o,5,false),6,true);

		case 2: return sBit(sBit(o,5,true),6,true);

		default: return sBit(sBit(o,5,true),6,false);
		}
	}

	public static Repeater repeater(byte b){
		final Repeater o=(Repeater)Bukkit.createBlockData(Material.REPEATER);

		o.setPowered(gBit(b,1));
		o.setLocked(gBit(b,2));
		if(gBit(b,3)) o.setFacing(gBit(b,4)?BlockFace.WEST:BlockFace.SOUTH);
		else o.setFacing(gBit(b,4)?BlockFace.NORTH:BlockFace.EAST);
		if(gBit(b,5)) o.setDelay((gBit(b,6)?2:3)+o.getMinimumDelay());
		else o.setDelay((gBit(b,6)?1:0)+o.getMinimumDelay());
		return o;
	}

	public static byte ladder(Ladder l){
		switch(l.getFacing()){
		case EAST: return sBit(sBit(sBit(1,l.isWaterlogged()),5,false),4,false);

		case NORTH: return sBit(sBit(sBit(1,l.isWaterlogged()),5,false),4,true);

		case SOUTH: return sBit(sBit(sBit(1,l.isWaterlogged()),5,true),4,false);

		default: return sBit(sBit(sBit(1,l.isWaterlogged()),5,true),4,true);
		}
	}

	public static Ladder ladder(byte b){
		final Ladder o=(Ladder)Bukkit.createBlockData(Material.LADDER);

		o.setWaterlogged(gBit(b,1));
		if(gBit(b,5)) o.setFacing(gBit(b,4)?BlockFace.WEST:BlockFace.SOUTH);
		else o.setFacing(gBit(b,4)?BlockFace.NORTH:BlockFace.EAST);
		return o;
	}

	public static byte slab(Slab s){
		switch(s.getType()){
		case BOTTOM: return sBit(sBit(sBit(1,s.isWaterlogged()),5,false),4,false);

		case DOUBLE: return sBit(sBit(sBit(1,s.isWaterlogged()),5,true),4,false);

		default: return sBit(sBit(sBit(1,s.isWaterlogged()),5,true),4,true);
		}
	}

	public static Slab slab(byte b,Material m){
		final Slab o=(Slab)Bukkit.createBlockData(m);

		o.setWaterlogged(gBit(b,1));
		if(gBit(b,5)) o.setType(gBit(b,4)?Slab.Type.TOP:Slab.Type.DOUBLE);
		else o.setType(Slab.Type.BOTTOM);
		return o;
	}

	public static byte stair(Stairs s){
		byte o=sBit(sBit(6,s.getHalf()==Half.TOP),7,s.isWaterlogged());

		switch(s.getFacing()){
		case EAST:
			o=sBit(sBit(o,5,false),4,false); break;

		case NORTH:
			o=sBit(sBit(o,5,false),4,true); break;

		case SOUTH:
			o=sBit(sBit(o,5,true),4,false); break;

		default:        //WEST
			o=sBit(sBit(o,5,true),4,true);
		}
		switch(s.getShape()){
		case INNER_LEFT:
			o=sBit(sBit(sBit(o,3,false),2,false),1,false); break;

		case INNER_RIGHT:
			o=sBit(sBit(sBit(o,3,false),2,false),1,true); break;

		case OUTER_LEFT:
			o=sBit(sBit(sBit(o,3,false),2,true),1,false); break;

		case OUTER_RIGHT:
			o=sBit(sBit(sBit(o,3,false),2,true),1,true); break;

		default:        //STRAIGHT
			o=sBit(sBit(sBit(o,3,true),2,false),1,false);
		}
		return o;
	}

	public static Stairs stair(byte b,Material m){
		final Stairs o=(Stairs)Bukkit.createBlockData(m);

		o.setWaterlogged(gBit(b,7));
		o.setHalf(gBit(b,6)?Half.TOP:Half.BOTTOM);
		if(gBit(b,5))
			o.setFacing(gBit(b,4)?BlockFace.WEST:BlockFace.SOUTH);
		else
			o.setFacing(gBit(b,4)?BlockFace.NORTH:BlockFace.EAST);
		if(gBit(b,3))
			o.setShape(Shape.STRAIGHT);
		else{
			if(gBit(b,2))
				o.setShape(gBit(b,1)?Shape.OUTER_RIGHT:Shape.OUTER_LEFT);
			else
				o.setShape(gBit(b,1)?Shape.INNER_RIGHT:Shape.INNER_LEFT);
		}
		return o;
	}

	public static byte sBit(int pos,boolean v){
		if(v) return (byte)(1<<pos);

		return (byte)0;
	}

	public static byte sBit(byte b,int pos,boolean v){
		if(v) return (byte)(b|1<<pos);

		return (byte)(b&~(1<<pos));
	}

	public static boolean gBit(byte b,int pos){
		return (b&1<<pos)!=0;
	}
}
