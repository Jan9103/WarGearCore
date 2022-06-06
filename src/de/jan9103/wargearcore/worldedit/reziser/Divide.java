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
package de.jan9103.wargearcore.worldedit.reziser;

import org.bukkit.Material;

import de.jan9103.wargearcore.worldedit.clip.Clipboard;
import de.jan9103.wargearcore.worldedit.clip.ClipboardBlock;
import de.jan9103.wargearcore.worldedit.util.ReformatBlocks;

public class Divide extends Reziser {
	private final int xa,ya,za;
	public Divide(Clipboard cb,int xamount,int yamount,int zamount){
		super(cb);
		xa=Integer.max(1,xamount);
		ya=Integer.max(1,yamount);
		za=Integer.max(1,zamount);
	}

	@Override public Clipboard run(){
		if(xa+ya+za==3) return in;

		final int xz=(int)Math.ceil(in.xSize()/xa);
		final int yz=(int)Math.ceil(in.ySize()/ya);
		final int zz=(int)Math.ceil(in.zSize()/za);

		out=new Clipboard(xz,yz,zz);
		for(int x=0; x<xz; x++)
			for(int y=0; y<yz; y++)
				for(int z=0; z<zz; z++){
					final ClipboardBlock[] b=a(x*xa,y*ya,z*za);
					out.set(x,y,z,a(b));
				}
		return out;
	}

	private ClipboardBlock[] a(int x,int y,int z){
		final ClipboardBlock[] o=new ClipboardBlock[xa*ya*za];

		for(int xx=0; xx<xa; xx++)
			for(int yx=0; yx<ya; yx++)
				for(int zx=0; zx<za; zx++){
					final int p=xx+yx*xx+zx*yx*xx;
					if(p>o.length) return o;

					o[p]=in.get(xx+x,yx+y,zx+z);
				}
		return o;
	}

	private ClipboardBlock a(ClipboardBlock[] c){
		if(c==null) return null;

		ClipboardBlock o =c[0];
		int            am=0;
		final int      m =c.length/2;

		for(final ClipboardBlock b : c){
			if(b==null) continue;
			final Material ma=b.getM();
			if(ma==Material.END_STONE) continue;
			if(ReformatBlocks.isTech(ma)) continue;
			final int a=a(c,ma);
			if(am<a){
				if(a<m) return b;

				am=a;
				o =b;
			}
		}
		return o;
	}

	private int a(ClipboardBlock[] b,Material m){
		int out=0;

		for(final ClipboardBlock a:b){
			if(a==null){
				if(m==Material.AIR) out++; continue;
			}
			if(a.getM()==m) out++;
		}
		return out;
	}
}