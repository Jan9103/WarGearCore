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
import de.jan9103.wargearcore.UserManager;
import de.jan9103.wargearcore.chat.Msg;

public class TellCmd extends UserOnlyCmd {
	@Override public void exe(String[] a,User u,Player p){
		if(a.length<3){
			Msg.missArg(u,"Core",p);
			return;
		}
		final User t=UserManager.parseUser(a[1]);

		if(t==null){
			new Msg(u).b("Unknown target.").a(p);
			return;
		}
		if(t==u){
			new Msg(u).c("Note: ").a(joinOn(a,1)).a(p);
			u.currentMsgTarget=u;
		}
		else{
			new Msg(t).c("from "+u.getName()+": ").a(joinOn(a,1)).a(t);
			new Msg(u).c("to "+t.getName()+": ").a(joinOn(a,1)).a(p);
			if(t.rtlr) t.currentMsgTarget=u;
			if(!u.rtlr) u.currentMsgTarget=t;
		}
	}

	private String joinOn(String[] s,int i){
		if(s.length<i) return "";

		String out="";

		for(int k=i; k<s.length; k++) out+=" "+s[k];
		return out;
	}
}
