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
package de.jan9103.wargearcore.util.portcannon;

import org.bukkit.entity.Player;

import de.jan9103.wargearcore.User;
import de.jan9103.wargearcore.cmds.UserOnlyCmd;
import de.jan9103.wargearcore.util.Parser;

public class PortableCannonCmd extends UserOnlyCmd {
	@Override public void exe(String[] a,User u,Player p){
		if(a.length<1){
			u.portc.shoot(p); return;
		}
		switch(a[0].toLowerCase()){
		case "shotgun":
		case "sg": {
			switch(a.length){
			case 1: p.sendMessage("/pc sg <tnt> [delay (gameticks)] [speed] [spread (1 hits an entire T1 at once)]"); return;

			case 2: u.portc=new PCShotgun(Parser.integer(a[1]),5,20); return;

			case 3: u.portc=new PCShotgun(Parser.integer(a[1]),5,Parser.integer(a[2])); return;

			case 4: u.portc=new PCShotgun(Parser.integer(a[1]),Parser.integer(a[3]),Parser.integer(a[2])); return;

			default: {
				try {
					u.portc=new PCShotgun(Parser.integer(a[1]),Parser.integer(a[3]),Parser.integer(a[2]),Float.parseFloat(a[4]));
				}catch(final NumberFormatException e){p.sendMessage(a[4]+" is not a valid number..");}
				return;
			}
			}
		}

		case "static":
		case "stc":
			switch(a.length){
			case 3:
			case 1: p.sendMessage("/pc stc <tnt> [min delay (gameticks)] [delay max random] [speed] [spread (1 hits an entire T1 at once)]"); return;

			case 2: u.portc=new PCStatic(Parser.integer(a[1]),5,20,30); return;

			case 4: u.portc=new PCStatic(Parser.integer(a[1]),5,Parser.integer(a[2]),Parser.integer(a[3])); return;

			case 5: u.portc=new PCStatic(Parser.integer(a[1]),Parser.integer(a[4]),Parser.integer(a[2]),Parser.integer(a[3])); return;

			default: {
				try {
					u.portc=new PCStatic(Parser.integer(a[1]),Parser.integer(a[4]),Parser.integer(a[2]),Parser.integer(a[3]),Float.parseFloat(a[5]));
				}catch(final NumberFormatException e){p.sendMessage(a[4]+" is not a valid number..");}
				return;
			}
			}

		case "stich":
		case "s":
			switch(a.length){
			case 1: p.sendMessage("/pc s <tnt> [delay (gameticks)] [speed]\nInstant-Side-Stab: /pc s 100 0 100"); return;

			case 2: u.portc=new PCStich(Parser.integer(a[1]),60,0); return;

			case 3: u.portc=new PCStich(Parser.integer(a[1]),60,Parser.integer(a[2])); return;

			default: u.portc=new PCStich(Parser.integer(a[1]),Parser.integer(a[3]),Parser.integer(a[2])); return;
			}

		default:
			p.sendMessage("unknown Syntax, use /help pc");
		}
	}
}
