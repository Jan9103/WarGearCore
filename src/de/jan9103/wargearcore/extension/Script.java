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

import java.util.HashMap;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.jan9103.wargearcore.extension.vars.ArrVar;
import de.jan9103.wargearcore.extension.vars.MPlayer;
import de.jan9103.wargearcore.extension.vars.SCPPArr;
import de.jan9103.wargearcore.extension.vars.StrVar;

public class Script {
	public final SCPPArr scppa;
	private final HashMap<String,Integer[]>jumps;
	private final Integer[] cmdJumpInPoint;
	/**used for debug messages*/
	public final String name;
	public Script(HashMap<String,Integer[]>jump,SCPPArr a,String nm){
		scppa=a; jumps=jump; cmdJumpInPoint=new Integer[] {0}; name=nm;
	}

	public Script(HashMap<String,Integer[]>jump,SCPPArr a,String nm,String cmdJmp){
		scppa=a; jumps=jump; cmdJumpInPoint=jumps.getOrDefault(cmdJmp,new Integer[] {0}); name=nm;
	}

	public void runCommand(CommandSender s,String[] args,Extension e){
		if(!(s instanceof Player)){
			s.sendMessage("Player only Command"); return;
		}
		ScriptRunner sr=new ScriptRunner(this,e);

		sr.jump(cmdJumpInPoint);
		sr.vars.set("sender",new MPlayer(s.getName()));
		ArrVar aa=new ArrVar();

		for(String i:args)
			aa.a.add(new StrVar(i));
		sr.vars.set("args",aa);
		try{sr.run();}catch(Exception e1){e1.printStackTrace();}
	}

	@Override public String toString(){
		return "Script: "+name;
	}
}