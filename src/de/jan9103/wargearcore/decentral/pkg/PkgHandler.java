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
package de.jan9103.wargearcore.decentral.pkg;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.jan9103.wargearcore.LogHandler;
import de.jan9103.wargearcore.User;
import de.jan9103.wargearcore.UserManager;
import de.jan9103.wargearcore.WGC;
import de.jan9103.wargearcore.chat.Msg;
import de.jan9103.wargearcore.chat.UChatMsgV3;
import de.jan9103.wargearcore.decentral.DecentralClientConn;
import de.jan9103.wargearcore.decentral.DecentralConn;
import de.jan9103.wargearcore.decentral.DecentralMgr;
import de.jan9103.wargearcore.decentral.PkgBuilder;

public class PkgHandler {
	private final ByteArrayReader r;
	public PkgHandler(byte[] a,DecentralConn source){
		r=new ByteArrayReader(a);
		switch(r.by()){
		case 1:         // CHAT
			if(DecentralMgr.server!=null)
				DecentralMgr.server.amplify(a,source);
			try{String server=r.str(),player=r.str(),msg=r.str();
			    new UChatMsgV3(player,null,server,null,msg,false,false,source.name,"Player on Decentral WGC Server").a();}catch(Exception e){if(!(e instanceof IOException)) LogHandler.handleException(e); }
			return;

		case 2:         // MSG
			try{
				String server=r.str(),target=r.str(),sender=r.str(),msg=r.str();
				Player p=Bukkit.getPlayer(target);
				if(p!=null){
					User u=UserManager.getUser(target);
					new Msg(u).c("from "+u.getName()+": ","sent from "+server+" ("+source.name+")").a(msg).a(p);
				}
				else{
					if(sender==DecentralMgr.name) return;        // prevent infinite loops

					if(DecentralMgr.server!=null){
						for(DecentralClientConn i:DecentralMgr.server.clients)
							for(String e:i.players)
								if(e.equalsIgnoreCase(target)){
									i.send(a); return;
								}
						source.send(PkgBuilder.msgPkg(sender,DecentralMgr.name,"Msg target not found.."));
					}
				}
			}catch(IOException e){LogHandler.handleException(e);}
			return;

		default:
			WGC.log("FAILED TO READ DECENTRAL PKG TYPE: "+a[0]);
		}
	}
}
