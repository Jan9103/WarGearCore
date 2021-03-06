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

import org.bukkit.Bukkit;

import de.jan9103.wargearcore.User;
import de.jan9103.wargearcore.WGC;
import de.jan9103.wargearcore.chat.Msg;
import de.jan9103.wargearcore.worldedit.clip.Clipboard;
import de.jan9103.wargearcore.worldedit.util.LCMcalc;

public class AsyncAutoResizer implements Runnable {
	private final User user;
	private LCMcalc xRef,yRef,zRef;
	private final int toX,toY,toZ;
	public AsyncAutoResizer(User u,int xTarget,int yTarget,int zTarget){
		u.setCanUseWE(false);
		toX =xTarget;
		toY =yTarget;
		toZ =zTarget;
		user=u;
	}

	@Override public void run(){
		final Clipboard in=user.cbv2.getAndWait();

		if(in==null){
			new Msg(user,"Edit").b("Copy somthing first..").a(user);
			user.setCanUseWE(true);
			return;
		}
		xRef=new LCMcalc(in.xSize(),toX);
		yRef=new LCMcalc(in.ySize(),toY);
		zRef=new LCMcalc(in.zSize(),toZ);
		if(xRef.aMulti<1||yRef.aMulti<1||zRef.aMulti<1){
			new Msg(user,"Edit").b("Unable to resize to the selected size..").a(user);
			user.setCanUseWE(true);
			return;
		}
		if(in.xSize()*xRef.aMulti+in.ySize()*yRef.aMulti+in.zSize()*zRef.aMulti>2147483646){
			new Msg(user,"Edit").b("Unable to resize to the selected size without overflowing the RAM..").a(user);
			user.setCanUseWE(true);
			return;
		}
		Clipboard a=new Multiplier(in,xRef.aMulti,1,1).run();

		a=new Divide(a,xRef.bMulti,1,1).run();
		a=new Multiplier(a,1,yRef.aMulti,1).run();
		a=new Divide(a,1,yRef.bMulti,1).run();
		a=new Multiplier(a,1,1,zRef.aMulti).run();
		final Clipboard b=new Divide(a,1,1,zRef.bMulti).run();

		b.setOriginalCopyPoint(in.getOriginalCopyPoint());
		b.setCv(in.cv);
		Bukkit.getScheduler().runTask(WGC.wgc,(Runnable)()->{
			user.cbv2.set(b);
			user.setCanUseWE(true);
			new Msg(user,"Edit").a("Resized").a(user);
		});
	}
}
