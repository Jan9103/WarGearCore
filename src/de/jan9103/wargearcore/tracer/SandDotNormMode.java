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
package de.jan9103.wargearcore.tracer;

import java.util.HashSet;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;

import de.jan9103.wargearcore.User;
import de.jan9103.wargearcore.UserManager;
import de.jan9103.wargearcore.coordSys.FinalFloatCoord;

public class SandDotNormMode extends SandMode {
	public SandDotNormMode(Trace t,int d,int u){
		super(t,d,u);
	}

	@Override protected void updateActive(){
		for(final FallingBlock s:active) s.remove();
		active.clear();
		if(dist<1){
			if(allWay.size()+allExp.size()+allUpd.size()>MAX_SAND){
				gtrMaxSand(); return;
			}
			for(final Location i:allWay) a(i,Material.WHITE_STAINED_GLASS);
			for(final Location i:allExp) a(i,Material.RED_STAINED_GLASS);
			for(final Location i:allUpd) a(i,Material.BLUE_STAINED_GLASS);
		}
		else{
			HashSet<Location>    way=new HashSet<>(),exp=new HashSet<>(),upd=new HashSet<>();
			final HashSet<Player>ps=new HashSet<>();
			for(final User i:UserManager.loadedUsers()) if(i.curAr==t.a) ps.add(i.getPlayer());
			for(final Location i:allWay)
				for(final Player p:ps)
					if(p.getLocation().distanceSquared(i)<dist){
						way.add(i); break;
					}
			for(final Location i:allExp)
				for(final Player p:ps)
					if(p.getLocation().distanceSquared(i)<dist){
						exp.add(i); break;
					}
			for(final Location i:allUpd)
				for(final Player p:ps)
					if(p.getLocation().distanceSquared(i)<dist){
						upd.add(i); break;
					}
			if(way.size()+exp.size()+upd.size()>MAX_SAND){
				gtrMaxSand(); return;
			}
			for(final Location i:way) a(i,Material.WHITE_STAINED_GLASS);
			for(final Location i:exp) a(i,Material.RED_STAINED_GLASS);
			for(final Location i:upd) a(i,Material.BLUE_STAINED_GLASS);
		}
	}

	@Override public int size(){
		return allUpd.size()+allWay.size()+allExp.size();
	}

	@Override public void traceAdded(TracedTnt t,Trace tr){
		if(t.a[t.a.length-1]==null) return;

		for(final FinalFloatCoord c:t.a) allWay.add(c.l(tr.w));
		float xt=0,zt=0;

		//FinalFloatCoord v=t.a[0];
		for(final FinalFloatCoord i:t.a){
			if(i==null) continue;
			if(xt==0){
				xt=i.x; zt=i.z;
			}
			if(Math.abs(xt-i.x)>Math.abs(zt-i.z)){
				if(Math.pow(xt-i.x,2)>=0.01) allUpd.add(new Location(tr.w,i.x,i.y,zt));
			}
			else if(Math.pow(zt-i.z,2)>=0.01) allUpd.add(new Location(tr.w,xt,i.y,i.z));
			xt=i.x; zt=i.z;
			//if(i.similar(v,1))continue;
			//v=i;
		}
		allExp.add(t.e.l(tr.w));
	}
}
