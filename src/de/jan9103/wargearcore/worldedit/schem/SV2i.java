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

import static de.jan9103.wargearcore.util.BlockIds.iTM;

import java.io.*;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.data.type.Ladder;
import org.bukkit.block.data.type.Leaves;
import org.bukkit.block.data.type.Observer;
import org.bukkit.block.data.type.Piston;
import org.bukkit.block.data.type.RedstoneWallTorch;
import org.bukkit.block.data.type.RedstoneWire;
import org.bukkit.block.data.type.Repeater;
import org.bukkit.block.data.type.Slab;
import org.bukkit.block.data.type.Stairs;

import de.jan9103.wargearcore.LogHandler;
import de.jan9103.wargearcore.area.ATheme;
import de.jan9103.wargearcore.coordSys.BigCoord;
import de.jan9103.wargearcore.util.BlockIds;
import de.jan9103.wargearcore.worldedit.clip.ChestClipBlock;
import de.jan9103.wargearcore.worldedit.clip.ClipBlockND;
import de.jan9103.wargearcore.worldedit.clip.ClipBlockWD;
import de.jan9103.wargearcore.worldedit.clip.Clipboard;
import de.jan9103.wargearcore.worldedit.clip.ClipboardBlock;
import de.jan9103.wargearcore.worldedit.clip.SignClipBlock;
import de.jan9103.wargearcore.worldedit.clip.TCB;
import de.jan9103.wargearcore.worldedit.clip.WaterlogBlock;

/**
 * SV2 aber mit ids statt material-strings und paar extras:
 *
 * -126 130 MASK
 * -125 131 Cb-Nd (small materialname)
 * -124 132 Cb-Wd (big data)
 * -123 133 Waterlog (big data)
 * -122 134 Sign (big data) (big txt(\n fuer zeilen))
 * -121 135 repeat once
 * -120 136 repeat (byte) times
 * -119 137 repeat (int) times
 * -118 138 endstone
 * -117 139 air
 * -118 149 tcb
 *
 * -  1 ERROR
 *    0 NULL (Mask)
 *
 *  1 : STAIRS (byte: stairdata) (stair-mat-id)
 *  2 : SLAB ^^
 *  3 : LADDER (byte: ladderdata)
 *  4 : REPEATER (byte repdata)
 *  5 : r-Wire (byte) (byte)
 *  6 : r-wall-torch (byte)
 *  7 : LEAVES (byte) (leave-mat)
 *  8 : observer
 * TODO 9 : noteblock https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/block/data/type/NoteBlock.html
 * 10 : piston
 * TODO 11 : piston head: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/block/data/type/PistonHead.html
 * TODO 12 : switch https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/block/data/type/Switch.html
 * TODO 13 : tnt https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/block/data/type/TNT.html
 * TODO 14 : trapdoor https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/block/data/type/TrapDoor.html
 * TODO 15 : PANE
 * TODO 16 : WALL
 *
 */
public class SV2i extends SchemFormat {
	@Override public Clipboard load(File f){
		if(!f.exists()) return null;

		FileInputStream fis=null;

		try{
			fis=new FileInputStream(f);
			return decompress(fis);
		}catch(final IOException e){if(fis!=null) try{fis.close();}catch(final IOException e1){}LogHandler.handleException(e); return null;}
	}

	public static Clipboard decompress(InputStream fis)throws IOException {
		final int       xs  =readInt(fis);
		final int       ys  =readInt(fis);
		final int       zs  =readInt(fis);
		final Clipboard c   =new Clipboard(xs,ys,zs);
		ClipboardBlock  zz  =null;
		int             reps=0;

		c.cv=readBigC(fis);
		c.originalCopyPoint=readBigC(fis);
		c.thm1=ATheme.thm((byte)fis.read());
		c.thm2=ATheme.thm((byte)fis.read());
		for(int x=0; x<xs; x++)
			for(int y=0; y<ys; y++)
				for(int z=0; z<zs; z++){
					if(reps!=0){
						reps--; c.clip[x][y][z]=zz; continue;
					}
					switch(fis.read()){
					case 130: zz=null; continue;

					case 131: c.clip[x][y][z]=zz=new ClipBlockND(iTM(readInt(fis))); continue;

					case 132: c.clip[x][y][z]=zz=new ClipBlockWD(Bukkit.createBlockData(new String(readBig(fis)))); continue;

					case 133: c.clip[x][y][z]=zz=new WaterlogBlock(Bukkit.createBlockData(new String(readBig(fis)))); continue;

					case 134: c.clip[x][y][z]=zz=new SignClipBlock(Bukkit.createBlockData(new String(readBig(fis))),new String(readBig(fis)).split("\n")); continue;

					case 135: c.clip[x][y][z]=zz; continue;

					case 136: c.clip[x][y][z]=zz; reps=fis.read(); continue;

					case 137: c.clip[x][y][z]=zz; reps=readInt(fis); continue;

					case 138: c.clip[x][y][z]=zz=new ClipBlockND(Material.END_STONE); continue;

					case 139: c.clip[x][y][z]=zz=new ClipBlockND(Material.AIR); continue;

					case 140: c.clip[x][y][z]=zz=new TCB((byte)fis.read()); continue;

					case 1: c.clip[x][y][z]=zz=new WaterlogBlock(SchemUtil.stair((byte)fis.read(),BlockIds.bTstair(fis.read()))); continue;

					case 2: c.clip[x][y][z]=zz=new WaterlogBlock(SchemUtil.slab((byte)fis.read(),BlockIds.bTslab(fis.read()))); continue;

					case 3: c.clip[x][y][z]=zz=new WaterlogBlock(SchemUtil.ladder((byte)fis.read())); continue;

					case 4: c.clip[x][y][z]=zz=new ClipBlockWD(SchemUtil.repeater((byte)fis.read())); continue;

					case 5: c.clip[x][y][z]=zz=new ClipBlockWD(SchemUtil.wire((byte)fis.read(),(byte)fis.read())); continue;

					case 6: c.clip[x][y][z]=zz=new ClipBlockWD(SchemUtil.wallTorch((byte)fis.read())); continue;

					case 7: c.clip[x][y][z]=zz=new ClipBlockWD(SchemUtil.leaves((byte)fis.read(),BlockIds.bTleave(fis.read()))); continue;

					case 8: c.clip[x][y][z]=zz=new ClipBlockWD(SchemUtil.observer((byte)fis.read())); continue;

					case 10: c.clip[x][y][z]=zz=new ClipBlockWD(SchemUtil.piston((byte)fis.read())); continue;

					case -1: fis.close(); return c;

					default:
					}
				}
		fis.close();
		return c;
	}

	@Override public boolean save(File f,Clipboard c){
		FileOutputStream fos=null;

		try{
			if(c==null){
				Bukkit.getLogger().warning("Tried to save NULL clipboard");
				return false;
			}
			if(c.clip==null){
				Bukkit.getLogger().warning("Tried to save NULL clipboard array");
				return false;
			}
			if(!f.exists()) try{
					f.getParentFile().mkdirs();
					f.createNewFile();
				}catch(final IOException e){}
			if(!f.exists()){
				Bukkit.getLogger().warning("Failed to create schem file (SV2i-save)");
				return false;
			}
			fos=new FileOutputStream(f);
			boolean ret=compress(fos,c);
			f.setLastModified(System.currentTimeMillis());
			return ret;
		}catch(final IOException e){if(fos!=null) try{fos.close();}catch(final IOException e1){}LogHandler.handleException(e); return false;}
	}

	public static boolean compress(OutputStream fos,Clipboard c)throws IOException {
		ClipboardBlock zz  =null;
		int            reps=0;

		writeInt(c.xSize(),fos);
		writeInt(c.ySize(),fos);
		writeInt(c.zSize(),fos);
		writeBigC(c.cv,fos);
		writeBigC(c.originalCopyPoint,fos);
		fos.write(ATheme.thm(c.thm1));
		fos.write(ATheme.thm(c.thm2));
		for(final ClipboardBlock[][] a:c.clip)
			for(final ClipboardBlock[] b:a)
				for(final ClipboardBlock i:b){
					if(i==null){
						if(zz==null){
							reps++; continue;
						}
						if(reps!=0){
							if(reps==1) fos.write((byte)-121);
							else if(reps<128) fos.write(new byte[] {(byte)-120,(byte)(reps-1)});
							else{
								fos.write((byte)-119);
								writeInt(reps-1,fos);
							}
							reps=0;
						}
						zz=null;
						fos.write((byte)-126);
						continue;
					}
					if(zz==null||!zz.compare(i)){
						if(reps!=0){
							if(reps==1) fos.write((byte)-121);
							else if(reps<128) fos.write(new byte[] {(byte)-120,(byte)(reps-1)});
							else{
								fos.write((byte)-119);
								writeInt(reps-1,fos);
							}
							reps=0;
						}
					}
					else{
						reps++; continue;
					}
					zz=i;
					if(i instanceof ClipBlockND)
						switch(i.getM()){
						case END_STONE: fos.write((byte)-118); continue;

						case AIR: fos.write((byte)-117); continue;

						default: fos.write((byte)-125); writeInt(BlockIds.mTI(i.getM()),fos); continue;
						}
					if(i instanceof SignClipBlock){
						fos.write((byte)-122);
						saveBig(i.getD().getAsString().getBytes(),fos);
						String s="";
						for(final String e:((SignClipBlock)i).getTxt()) s+=e+"\n";
						saveBig(s.substring(0,s.length()-1).getBytes(),fos);
						continue;
					}
					if(i instanceof WaterlogBlock||i instanceof ChestClipBlock){
						if(i.getD() instanceof Stairs){
							fos.write(new byte[] {1,SchemUtil.stair((Stairs)i.getD()),BlockIds.stairTB(i.getM())}); continue;
						}
						if(i.getD() instanceof Slab){
							fos.write(new byte[] {2,SchemUtil.slab((Slab)i.getD()),BlockIds.slabTB(i.getM())}); continue;
						}
						if(i.getD() instanceof Ladder){
							fos.write(new byte[] {3,SchemUtil.ladder((Ladder)i.getD())}); continue;
						}
						fos.write((byte)-123);
						saveBig(i.getD().getAsString().getBytes(),fos);
						continue;
					}
					if(i instanceof ClipBlockWD){
						if(i.getD() instanceof Repeater){
							fos.write(new byte[] {4,SchemUtil.repeater((Repeater)i.getD())}); continue;
						}
						if(i.getD() instanceof RedstoneWire){
							fos.write(5); fos.write(SchemUtil.wire((RedstoneWire)i.getD())); continue;
						}
						if(i.getD() instanceof RedstoneWallTorch){
							fos.write(new byte[] {6,SchemUtil.wallTorch((RedstoneWallTorch)i.getD())}); continue;
						}
						if(i.getD() instanceof Leaves){
							fos.write(new byte[] {7,SchemUtil.leaves((Leaves)i.getD()),BlockIds.leaveTB(i.getM())}); continue;
						}
						if(i.getD() instanceof Observer){
							fos.write(new byte[] {8,SchemUtil.observer((Observer)i.getD())}); continue;
						}
						if(i.getD() instanceof Piston){
							fos.write(new byte[] {10,SchemUtil.piston((Piston)i.getD())}); continue;
						}
						fos.write((byte)-124);
						saveBig(i.getD().getAsString().getBytes(),fos);
						continue;
					}
					if(i instanceof TCB){
						fos.write((byte)-118);
						fos.write(((TCB)i).compress());
						continue;
					}
					fos.write((byte)0);
				}
		if(reps!=0){
			if(reps==1) fos.write((byte)-121);
			else if(reps<128) fos.write(new byte[] {(byte)-120,(byte)(reps-1)});
			else{
				fos.write((byte)-119);
				writeInt(reps-1,fos);
			}
		}
		fos.flush();
		fos.close();
		return true;
	}

	//private void saveBool(boolean b,FileOutputStream fos)throws IOException{fos.write(b?(byte)1:(byte)0);}
	//private void saveSmall(byte[]a,FileOutputStream fos)throws IOException{fos.write((byte)a.length);fos.write(a);}
	private static void saveBig(byte[] a,OutputStream fos)throws IOException {
		writeInt(a.length,fos); fos.write(a);
	}

	private static void writeInt(int a,OutputStream fos)throws IOException {
		fos.write(intTob4(a));
	}

	private static void writeBigC(BigCoord a,OutputStream fos)throws IOException {
		fos.write(intTob4(a.x)); fos.write(intTob4(a.y)); fos.write(intTob4(a.z));
	}

	//private boolean readBool(FileInputStream fis)throws IOException{return fis.read()==1;}
	private static int readInt(InputStream fis)throws IOException {
		return b4ToInt(fis.read(),fis.read(),fis.read(),fis.read());
	}

	//private byte[]readSmall(FileInputStream fis)throws IOException{
	//	final int s=fis.read();
	//	byte[]b=new byte[s];
	//	for(int i=0;i<s;i++)b[i]=(byte)fis.read();
	//	return b;
	//}

	private static byte[] readBig(InputStream fis)throws IOException {
		final int    s=readInt(fis);
		final byte[] b=new byte[s];

		for(int i=0; i<s; i++) b[i]=(byte)fis.read();
		return b;
	}

	private static BigCoord readBigC(InputStream fis)throws IOException {
		return new BigCoord(readInt(fis),readInt(fis),readInt(fis));
	}

	private static int b4ToInt(int a,int b,int c,int d){
		return (a&0xFF)<<24|(b&0xFF)<<16|(c&0xFF)<<8|d&0xFF;
	}

	private static byte[] intTob4(int i){
		return new byte[] {(byte)(i>>24),(byte)(i>>16),(byte)(i>>8),(byte)i};
	}
}
