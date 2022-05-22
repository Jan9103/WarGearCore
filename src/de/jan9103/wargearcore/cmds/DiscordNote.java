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
package de.jan9103.wargearcore.cmds;

import org.bukkit.entity.Player;

import de.jan9103.wargearcore.User;
import de.jan9103.wargearcore.chat.Msg;
import de.jan9103.wargearcore.util.DWHS;

public class DiscordNote extends AsyncUserCmd {
	private final String[] a; private final User u; private final Player p;
	public DiscordNote(String[] a1,User u1,Player p1){
		a=a1; u=u1; p=p1;
	}

	@Override public Runnable get(String[] a,User u,Player p){
		return new DiscordNote(a,u,p);
	}

	public DiscordNote(){
		a=null; u=null; p=null;
	}

	private static String url="";
	public static void setUrl(String u){
		if(u!=null) url=u;
	}

	@Override public void run(){
		if(url==""){
			new Msg(u).e("The admin hasnt yet set a WebHook in the config.").a(p); return;
		}
		if(a==null){
			new Msg(u).b("Sorry, but you cant send empty messages.").a(p); return;
		}
		if(a.length<1){
			new Msg(u).b("Sorry, but you cant send empty messages.").a(p); return;
		}
		String out="";

		for(final String element:a)
			out+=element+" ";
		try{new DWHS(url,u.getName(),u.profilePic,out); new Msg(u).a("Message sent.").a(p);}catch(Throwable t){new Msg(u).b("Failed to send message.").d().b("maybe your profile-picture is invalid?").a(p);}
	}
}
