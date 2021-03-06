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
package de.jan9103.wargearcore.worldedit.clip;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.block.data.Waterlogged;

import de.jan9103.wargearcore.User;
import de.jan9103.wargearcore.WGC;
import de.jan9103.wargearcore.area.ATheme;
import de.jan9103.wargearcore.coordSys.BigArea;
import de.jan9103.wargearcore.coordSys.BigCoord;
import de.jan9103.wargearcore.worldedit.WEArea;
import de.jan9103.wargearcore.worldedit.editors.WeSender;
import de.jan9103.wargearcore.worldedit.editors.async.AsyncPaste4b;

public class Clipboard {
	public ClipboardBlock[][][] clip;
	public ATheme.THM thm1=ATheme.THM.GRAY,thm2=ATheme.THM.ARENA;
	public ATheme.THM thm1(ATheme.THM a){
		return thm1==ATheme.THM.ARENA?a:thm1;
	}

	public ATheme.THM thm2(ATheme.THM a){
		return thm2==ATheme.THM.ARENA?a:thm2;
	}

	public BigCoord cv; // copy verschiebung
	public BigCoord getCv(){
		return cv;
	}

	public void setCv(BigCoord cv){
		this.cv=cv;
	}

	public BigCoord getOriginalCopyPoint(){
		return originalCopyPoint;
	}

	public void setOriginalCopyPoint(BigCoord originalCopyPoint){
		this.originalCopyPoint=originalCopyPoint;
	}

	public BigCoord originalCopyPoint;
	public boolean isValid(){
		if(clip==null) return false;

		if(cv==null) return false;

		return true;
	}

	public Clipboard(int xSize,int ySize,int zSize){
		if(xSize<1||ySize<1||zSize<1) return;

		clip=new ClipboardBlock[xSize][ySize][zSize];
		for(int x=0; x<xSize; x++){
			clip[x]=new ClipboardBlock[ySize][zSize];
			for(int y=0; y<ySize; y++){
				clip[x][y]=new ClipboardBlock[zSize];
				for(int z=0; z<zSize; z++) clip[x][y][z]=null;
			}
		}
		cv=new BigCoord(0,0,0);
	}

	public boolean unset(int x,int y,int z){
		try{return clip[x][y][z]==null;}catch(Exception e){return false;}
	}

	public Clipboard(ClipboardBlock[][][] c,BigCoord pCv,BigCoord o){
		cv=pCv; clip=c; originalCopyPoint=o;
	}

	public void autoPaste(Block pos,WeSender wes){
		Bukkit.getScheduler().runTaskAsynchronously(WGC.wgc,()->{new AsyncPaste4b(pos.getWorld(),new BigCoord(pos),wes,this).a();});
	}

	public void autoPaste(World w,WeSender wes){
		Bukkit.getScheduler().runTaskAsynchronously(WGC.wgc,()->{new AsyncPaste4b(w,originalCopyPoint,wes,this).a();});
	}

	public void autoPasteUndo(World w,User u){
		AsyncPaste4b a=new AsyncPaste4b(w,originalCopyPoint,u,this);

		if(u.saveUndo) u.saveUndo(a.getUndo(),"paste-undo");
		a.aFromSync();
	}

	public void set(int x,int y,int z,Block b){
		switch(b.getType()){
		case CHEST:
			clip[x][y][z]=new ChestClipBlock((Chest)b.getState());
			return;

		default:
			if(b.getBlockData() instanceof Waterlogged){
				clip[x][y][z]=new WaterlogBlock(b.getBlockData()); return;
			}
			if(b.getBlockData() instanceof Sign){
				clip[x][y][z]=new SignClipBlock(b); return;
			}
			if(b.getBlockData().getAsString().indexOf('[')==-1)
				clip[x][y][z]=new ClipBlockND(b.getType());
			else
				clip[x][y][z]=new ClipBlockWD(b.getBlockData().clone());
		}
	}

	public void set(int x,int y,int z,ClipboardBlock b){
		if(clip==null) return;

		if(x>clip.length||x<0) return;

		if(y>clip[x].length||y<0) return;

		if(z>clip[x][y].length||y<0) return;

		clip[x][y][z]=b;
	}

	public Material unsafeM(int x,int y,int z){
		return clip[x][y][z].getM();
	}

	public ClipboardBlock get(int x,int y,int z){
		if(clip==null) return null;

		if(x>clip.length||x<0) return null;

		if(y>clip[x].length||y<0) return null;

		if(z>clip[x][y].length||y<0) return null;

		return clip[x][y][z];
	}

	public Clipboard(WEArea a,Block l){
		final BigArea ba=a.toBigArea();

		originalCopyPoint=new BigCoord(l);
		cv  =ba.low.offset(originalCopyPoint);
		clip=new ClipboardBlock[ba.xSize()][ba.ySize()][ba.zSize()];
		final int xm=clip.length;
		final int ym=clip[0].length;
		final int zm=clip[0][0].length;

		for(int x=0; x<xm; x++)
			for(int y=0; y<ym; y++)
				for(int z=0; z<zm; z++)
					set(x,y,z,a.w.getBlockAt(x+ba.low.x,y+ba.low.y,z+ba.low.z));
	}

	public Clipboard(BigArea ba,World w,BigCoord cp){
		originalCopyPoint=cp;
		cv  =ba.low.offset(cp);
		clip=new ClipboardBlock[ba.xSize()][ba.ySize()][ba.zSize()];
		final int xm=clip.length;
		final int ym=clip[0].length;
		final int zm=clip[0][0].length;

		for(int x=0; x<xm; x++)
			for(int y=0; y<ym; y++)
				for(int z=0; z<zm; z++)
					set(x,y,z,w.getBlockAt(x+ba.low.x,y+ba.low.y,z+ba.low.z));
	}

	public void clone(WEArea a,Block l){
		final BigArea ba=a.toBigArea();

		originalCopyPoint=new BigCoord(l);
		cv  =ba.low.offset(originalCopyPoint);
		clip=new ClipboardBlock[ba.xSize()][ba.ySize()][ba.zSize()];
		final int xm=clip.length;
		final int ym=clip[0].length;
		final int zm=clip[0][0].length;

		for(int x=0; x<xm; x++)
			for(int y=0; y<ym; y++)
				for(int z=0; z<zm; z++)
					set(x,y,z,a.w.getBlockAt(x+ba.low.x,y+ba.low.y,z+ba.low.z));
	}

	/**
	 * cut away everything except m
	 */
	public void inverseMask(Material m){
		final int xm=clip.length;
		final int ym=clip[0].length;
		final int zm=clip[0][0].length;

		for(int x=0; x<xm; x++)
			for(int y=0; y<ym; y++)
				for(int z=0; z<zm; z++){
					ClipboardBlock cb=clip[x][y][z];
					if(cb==null) continue;
					if(cb.getM()!=m) clip[x][y][z]=null;
				}
	}

	public void inverseMask(Material[] m){
		final int xm=clip.length;
		final int ym=clip[0].length;
		final int zm=clip[0][0].length;

		for(int x=0; x<xm; x++)
			for(int y=0; y<ym; y++)
				loopi : for(int z=0; z<zm; z++){
					ClipboardBlock cb=clip[x][y][z];
					if(cb==null) continue;
					Material ma=cb.getM();
					for(Material i:m)
						if(ma==i) continue loopi;
					clip[x][y][z]=null;
				}
	}

	public void mask(Material m){
		final int xm=clip.length;
		final int ym=clip[0].length;
		final int zm=clip[0][0].length;

		for(int x=0; x<xm; x++)
			for(int y=0; y<ym; y++)
				for(int z=0; z<zm; z++){
					ClipboardBlock cb=clip[x][y][z];
					if(cb==null) continue;
					if(cb.getM()==m) clip[x][y][z]=null;
				}
	}

	public void mask(Material[] m){
		final int xm=clip.length;
		final int ym=clip[0].length;
		final int zm=clip[0][0].length;

		for(int x=0; x<xm; x++)
			for(int y=0; y<ym; y++)
				for(int z=0; z<zm; z++){
					ClipboardBlock cb=clip[x][y][z];
					if(cb==null) continue;
					Material ma=cb.getM();
					for(Material i:m)
						if(ma==i){
							clip[x][y][z]=null; break;
						}
				}
	}

	public void unMask(Material m){
		final int   xm=clip.length;
		final int   ym=clip[0].length;
		final int   zm=clip[0][0].length;
		ClipBlockND cb=new ClipBlockND(m);

		for(int x=0; x<xm; x++)
			for(int y=0; y<ym; y++)
				for(int z=0; z<zm; z++)
					if(clip[x][y][z]==null) clip[x][y][z]=cb;
	}

	public int xSize(){
		if(clip==null) return 0; return clip.length;
	}

	public int ySize(){
		if(clip==null) return 0; return clip[0].length;
	}

	public int zSize(){
		if(clip==null) return 0; return clip[0][0].length;
	}

	public Clipboard subClip(int xS,int xZ,int yS,int yZ,int zS,int zZ){
		xZ=Integer.min(xZ,xSize()-xS);
		yZ=Integer.min(yZ,ySize()-yS);
		zZ=Integer.min(zZ,zSize()-zS);
		if(xZ<1||yZ<1||zZ<1||xS+xZ>xSize()||yS+yZ>ySize()||zS+zZ>zSize()) return null;

		final Clipboard out=new Clipboard(xZ,yZ,zZ);

		out.setCv(cv.offset(new BigCoord(xS,yS,zS)));
		out.setOriginalCopyPoint(originalCopyPoint);
		for(int x=0; x<xZ; x++)
			for(int y=0; y<yZ; y++)
				for(int z=0; z<zZ; z++)
					out.set(x,y,z,get(xS+x,yS+y,zS+z));
		return out;
	}

	public Clipboard dupe(){
		return new Clipboard(clip,cv,originalCopyPoint);
	}

	public long hash(){
		final int xm =clip.length;
		final int ym =clip[0].length;
		final int zm =clip[0][0].length;
		int       a  =0;
		long      out=0;

		for(int x=0; x<xm; x++)
			for(int y=0; y<ym; y++)
				for(int z=0; z<zm; z++){
					if(++a>32) a=0;
					out^=(clip[x][y][z].hashCode()+0L)<<a;
				}
		return out;
	}
}
