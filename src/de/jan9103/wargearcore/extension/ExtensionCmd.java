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
package de.jan9103.wargearcore.extension;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import de.jan9103.wargearcore.User;
import de.jan9103.wargearcore.UserManager;
import de.jan9103.wargearcore.WGC;
import de.jan9103.wargearcore.chat.Msg;

public class ExtensionCmd implements CommandExecutor,TabCompleter {
	@Override public boolean onCommand(CommandSender s,Command a1,String a2,String[] a){
		boolean    root=(s instanceof ConsoleCommandSender)||(s instanceof RemoteConsoleCommandSender);
		final User u   =(s instanceof Player)?UserManager.getUser((Player)s):null;

		if(u!=null)
			if(u.root) root=true;
		if(a.length<1){
			if(u!=null){
				new Msg(u).a("WarGearCore "+WGC.wgc.getDescription().getVersion()+" by Jan9103")
				.d().aC("Official Discord: https://discord.gg/ddB2MDK",Msg.URL,"https://discord.gg/ddB2MDK","Open URL").a((Player)s);
				return true;
			}
			else return msg(u,s,"Missing command argument..",true);
		}
		switch(a[0].toLowerCase()){
//			case"reload":
//				WGC.wgc.extm.unload(a[1]);
//				WGC.wgc.extm.load(a[1]);
		case "list": {
			StringBuilder o=new StringBuilder("Loaded Extensions:");
			for(Extension i:WGC.wgc.extm.loaded.values()){
				o.append("\n + "+i.name);
				for(String c:i.commands)
					o.append("\n |  /"+c);
			}
			return msg(u,s,o.toString(),false);
		}

		case "unload": {
			if(!root) return msg(u,s,"Missing WGC-root Permission.",true);

			if(a.length<2) return msg(u,s,"Missing Argument: Extension Name.",true);

			String wn=null;
			for(String i:WGC.wgc.extm.loaded.keySet()) if(i.equalsIgnoreCase(a[1])){
					wn=i; break;
				}
			if(wn==null) return msg(u,s,"Unknown Extension.. \"/extension list\"",true);

			WGC.wgc.extm.unload(wn);
			return msg(u,s,"Extension should've been unloaded.",false);
		}

		case "reloadall":
			try{
				if(!root) return msg(u,s,"Missing WGC-root Permission.",true);

				for(String i:WGC.wgc.extm.loaded.keySet()) WGC.wgc.extm.unload(i);
				WGC.wgc.extm.loadAll();
				return msg(u,s,"Should've reloaded all extensions..",false);
			}catch(Exception e){e.printStackTrace();         //TODO remove debug
					    return msg(u,s,"Reloadall failed: "+e.getClass().getName()+": "+e.getMessage(),true);}
		}
		return true;
	}

	private boolean msg(User u,CommandSender s,String m,boolean error){
		if(u!=null){
			if(error) new Msg(u).b(m).a((Player)s);
			else new Msg(u).a(m).a((Player)s);
		}
		else s.sendMessage("[WGC] "+m);
		return true;
	}

	@Override public List<String>onTabComplete(CommandSender s,Command arg1,String arg2,String[] args){
		// TODO Auto-generated method stub
		return null;
	}
}
