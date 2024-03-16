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
package de.jan9103.wargearcore.listener;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;
import org.bukkit.event.player.PlayerJoinEvent;

import de.jan9103.wargearcore.LogHandler;
import de.jan9103.wargearcore.User;
import de.jan9103.wargearcore.UserManager;
import de.jan9103.wargearcore.WGC;
import de.jan9103.wargearcore.area.WgArea;
import de.jan9103.wargearcore.chat.JoinMsgPanel;
import de.jan9103.wargearcore.chat.Msg;
import de.jan9103.wargearcore.util.TPS;
import de.jan9103.wargearcore.util.Tab;
import de.jan9103.wargearcore.util.ToDo;

public class JoinListener implements Listener {
//	@EventHandler public void a(PlayerRegisterChannelEvent e){
//		if(e.getChannel().equalsIgnoreCase("lunarclient:pm")){
//			//USES LUNAR CLIENT
//			System.out.println(e.getPlayer().getName()+" uses LunarClient.");
//		}
//	}
	@EventHandler public void a(AsyncPlayerPreLoginEvent e){
		final File f=new File(WGC.dataFolder+"/user/"+e.getUniqueId().toString()+".usr");

		if(!f.exists())
			try{f.createNewFile();
			    UserManager.applyDefaultConfig(f);}catch(final IOException e1){LogHandler.handleException(e1); e.disallow(Result.KICK_OTHER,"We're currently not able to create your user-profile, please try again later or report this Bug."); return;}
		final YamlConfiguration c=YamlConfiguration.loadConfiguration(f);
	}

	@EventHandler public void a(PlayerJoinEvent e){
		try{
			Player p=e.getPlayer();
			TPS.ign(10000);
			final User u=UserManager.onJoin(p.getUniqueId());
			//		e.setJoinMessage(null);
			WGC.dcLog("[+] "+p.getName());
			senJoinInfo(p,u);
			if(WGC.customTab) Tab.auto(e.getPlayer(),u);
		}catch(Exception ex){LogHandler.handleException(ex);}
	}

	public static void senJoinInfo(Player p,User u){
		switch(u.joinPanel){
		case 1: {       // SHORT TEXT WALL
			final Msg m=new Msg(u,null).d("\n======== ").a("Loaded Areas").d(" ========");
			for(WgArea i:WgArea.areas) m.d().aC(i.name,Msg.RUN,"/a tp "+i.name,"tp");
			m.d("\n======== ").a("Online").d(" ========");
			for(final User i:UserManager.loadedUsers()) m.d().a(i.getName()+(i.curAr==null?"":" ("+i.curAr.name+")"));
			if(!u.saveUndo) m.aC("\nWARNING: WGE-undo-history is deactivated!",Msg.RUN,"/undo on","re-activate");
			m.a(p);
			break;
		}

		case 2: {        // ICON
			new JoinMsgPanel(u).send(u,p);
			break;
		}

		default:
		}
		if(u.joinTodoInfoDays>0) ToDo.soon(u,System.currentTimeMillis()+(u.joinTodoInfoDays*86400000l),true);
	}
}
