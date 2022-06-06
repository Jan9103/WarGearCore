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
package de.jan9103.wargearcore.coordSys;

import org.bukkit.Location;
import org.bukkit.World;

public class FinalFloatCoord {
	public final float x,y,z;
	public FinalFloatCoord(float xt,float y2,float z2){
		x=xt; y=y2; z=z2;
	}

	public FinalFloatCoord(Location l){
		x=(float)l.getX();
		y=(float)l.getY();
		z=(float)l.getZ();
	}

	public Location l(World w){
		return new Location(w,x,y,z);
	}

	public boolean similar(FinalFloatCoord c){
		return Math.abs(x-c.x)+Math.abs(y-c.y)+Math.abs(z-c.z)<0.5;
	}

	public boolean similar(Location l){
		return Math.abs(x-l.getX())+Math.abs(y-l.getY())+Math.abs(z-l.getZ())<0.5;
	}

	public boolean similar(FinalFloatCoord c,float f){
		return Math.abs(x-c.x)+Math.abs(y-c.y)+Math.abs(z-c.z)<f;
	}

	public boolean similar(Location l,float f){
		return Math.abs(x-l.getX())+Math.abs(y-l.getY())+Math.abs(z-l.getZ())<f;
	}

	public int xI(){
		return Math.round(x);
	}

	public int yI(){
		return Math.round(y);
	}

	public int zI(){
		return Math.round(z);
	}

	public FinalFloatCoord[] corners(){
		final FinalFloatCoord[] a=new FinalFloatCoord[8];

		a[0]=this;
		a[1]=new FinalFloatCoord(x+0.98f,y,z);
		a[2]=new FinalFloatCoord(x,y+0.98f,z);
		a[3]=new FinalFloatCoord(x+0.98f,y+0.98f,z);
		a[4]=new FinalFloatCoord(x,y,z+0.98f);
		a[5]=new FinalFloatCoord(x+0.98f,y,z+0.98f);
		a[6]=new FinalFloatCoord(x,y+0.98f,z+0.98f);
		a[7]=new FinalFloatCoord(x+0.98f,y+0.98f,z+0.98f);
		return a;
	}
}