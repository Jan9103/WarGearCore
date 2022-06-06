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
package de.jan9103.wargearcore.area.tb;

import java.util.HashSet;
import java.util.List;

import org.bukkit.Material;

import de.jan9103.wargearcore.C;
import de.jan9103.wargearcore.User;
import de.jan9103.wargearcore.WGC;
import de.jan9103.wargearcore.area.WgArea;
import de.jan9103.wargearcore.area.tb.s.Shield;
import de.jan9103.wargearcore.chat.Msg;
import de.jan9103.wargearcore.worldedit.editors.AsyncMultiEditor;
import de.jan9103.wargearcore.worldedit.editors.WeSender;

public abstract class TB {
	public void p(WgArea a,WeSender s){
		pLater(a,s).exe();
	}

	public abstract AsyncMultiEditor pLater(WgArea a,WeSender s);

	protected void addShields(WgArea a,AsyncMultiEditor e){
		for(Shield i:s) i.aLater(e,a);
	}

	public abstract void s(C c);

	public HashSet<Shield>s=new HashSet<>();
	public static TB l(C c){
		switch(c.gi("tbt",0)){
		case 1:         //deprecated
		case 2: return new TB2(c);

		case 3: return new TbAim(c);

		default: return new TB2();
		}
	}

	public void aS(Shield i){
		if(s!=null) s.add(i);
	}

	public void rAS(){
		s.clear();
	}

	public static TB a(String[] a,User u){
		try{
			switch(a[1]){
			case "2":
			case "simple": return new TB2();

			case "3":
			case "schem":
				if(u.curAr==null){
					WGC.logHandler.add("WGC: ERROR: Tb.a.3.a"); u.rawMsg("ERROR: Tb.a.3.a"); return null;
				}
				if(a.length<3){
					new Msg(u,WGC._PREFIX_2_Area).b("Missing argument: schemtype").a(u); return null;
				}
				return new SchemTb(a[2],u.curAr);

			case "4":
			case "aimtrainer": try{
					int size=5,amount=1;
					if(a.length>2) size=Integer.parseInt(a[2]);
					if(a.length>3) amount=Integer.parseInt(a[3]);
					return new TbAim(size,amount);
			}catch(NumberFormatException e){new Msg(u,WGC._PREFIX_2_Area).b("Invalid Number").a(u); return null;}

			default:
				u.rawMsg("UNKNOWN TESTBLOCK FORMAT");
				return null;
			}
		}catch(final Exception e){return null;}
	}

	public Material[] a(List<Material>l){
		final Material[] a=new Material[l.size()];

		for(int i=l.size()-1; i>=0; i--) a[i]=l.get(i);
		return a;
	}

	public void a(TB b){
		//TODO clone shields, ect
	}
}