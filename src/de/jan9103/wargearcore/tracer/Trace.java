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
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.TNTPrimed;

import de.jan9103.wargearcore.WGC;
import de.jan9103.wargearcore.area.WgArea;

public class Trace {
	private int shedule=0;
	private boolean on =false;
	protected final HashSet<TraceTnt>active=new HashSet<>();
	protected final HashSet<TracedTnt>done =new HashSet<>();
	public final World w;
	public final WgArea a;
	public ShowMode tm=new SandOvMode(this,2500,40);
	public boolean on(){
		return on;
	}

	public int saved(){
		return done.size();
	}

	public void clear(){
		active.clear();
		done.clear();
		tm.traceCleared();
	}

	public void shutdown(){
		stop();
		hide();
		active.clear();
		done.clear();
	}

	public Trace(World world,WgArea area){
		w=world; a=area;
	}

	public void prime(TNTPrimed tnt){
		active.add(new TraceTnt(tnt));
	}

	public void start(){
		if(on) return;

		shedule=Bukkit.getScheduler().scheduleSyncRepeatingTask(WGC.wgc,()->{
			final Iterator<TraceTnt>i=active.iterator();
			while(i.hasNext()){
				TraceTnt t=i.next();
				if(t.i>79) i.remove(); t.tick();
			}
		},0,1);
		on=true;
	}

	public void stop(){
		if(!on) return;

		Bukkit.getScheduler().cancelTask(shedule);
		on=false;
	}

	public void show(){
		tm.start();
	}

	public void hide(){
		tm.stop();
	}

	public void boom(TNTPrimed t,Location l){
		for(TraceTnt i:active) if(i.tnt==t){
				i.explosion(this,l); active.remove(i); return;
			}
	}
}