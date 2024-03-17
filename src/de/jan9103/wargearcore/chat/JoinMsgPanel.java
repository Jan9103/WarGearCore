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
package de.jan9103.wargearcore.chat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_19_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import de.jan9103.wargearcore.User;
import de.jan9103.wargearcore.WGC;
import de.jan9103.wargearcore.area.WgArea;
import de.jan9103.wargearcore.util.Flag;
import de.jan9103.wargearcore.util.TPS;

public class JoinMsgPanel {
	//TODO continue
	//TODO link

	private String motd="";
	private Flag flag  =null;
	public JoinMsgPanel(User u){
		final LocalDate ld   =LocalDate.now();
		int             i    =(int)(System.currentTimeMillis()%4);
		int             event=0;

		switch(ld.getMonthValue()){
		case 1:        //JAN
			switch(ld.getDayOfMonth()){
			case 1: motd="Happy new Year!"; break;
			}
			break;

		case 2:        //FEB
			switch(ld.getDayOfMonth()){
			case 23: event=16; break;
			case 26: motd="WargearWorld was founded "+(ld.getYear()-2020)+" year ago."; break;
			}
			break;

		case 4:        //APR
			switch(ld.getDayOfMonth()){
			case 1:
				switch(i){
				case 0: motd="Happy Birthday!"; break;

				case 1: motd="Mojang moved the crosshair there -> §r\u22B9"; break;

				case 2: motd="Half-Life 3 has been announced!"; break;

				case 3: motd="MCIV is coming back!"; event=17; break;
				}

			case 6: event=8; break;

			case 16: motd="MCIV v1 is now "+(ld.getYear()-2012)+" years old."; event=17; break;

			case 26: event=6; break;
			}
			break;

		case 5:        //MAY
			switch(ld.getDayOfMonth()){
			case 6: motd=(ld.getYear()-2014)+"th Birthday of MPP"; break;
			case 17:
				switch(i){
				case 0: motd=(ld.getYear()-2019)+"th birthday of steamwar.de"; break;
				case 1: event=2; break;
				case 2: event=3; break;
				case 3: event=1; break;
				}
			case 19: event=7; break;
			case 24: event=4; break;
			case 27: motd=(ld.getYear()-2021)+" years ago WGC Closed Beta v1 was released."; break;
			case 29: motd="Gear Forge opened "+(ld.getYear()-2015)+" years ago."; break;
			}
			break;

		case 7:        //JUL
			switch(ld.getDayOfMonth()){
			case 14: event=5; break;
			case 16: event=3; break;
			}
			break;

		case 9:        //SEP
			switch(ld.getDayOfMonth()){
			case 21: event=18; break;
			case 23: event=2; break;
			}
			break;

		case 10:        //OCT
			switch(ld.getDayOfMonth()){
			case 8: event=6; break;

			case 11: event=1; break;

			case 26: event=12; break;
			}
			break;

		case 11:        //NOV
			switch(ld.getDayOfMonth()){
			case 20: event=3; break;
			}
			break;

		case 12:        //DEC
		}
		if(motd=="")
			if(ld.getDayOfWeek()==DayOfWeek.FRIDAY){
				if(ld.getDayOfMonth()==13) motd="Friday the 13th ._.";
				else motd="Weekend!";
			}
		if(motd=="")
			switch(ld.getMonthValue()){
			case 6: if(i==0) event=1; break;

			case 8: if(i==0) motd="\u2600"; break; //SUN

			case 12: if(i==0) motd="\u2603"; break; //SNOWMAN
			}
		if(WGC.flagtext=="WGC")
			flag=new Flag(event,u.ct,u.pre16);
		else
			flag=new Flag(WGC.flagtext,event,u.ct,u.pre16);
	}

	public void send(User u,Player p){
		ArrayList<Player>ps=new ArrayList<>(Bukkit.getOnlinePlayers());
		final Msg        m =new Msg(u,null).a("WGC v"+WGC.VERSION+" (made by Jan9103)")
				     .a("\nPing: ").b(p.getPing()+"")
				     .a("\nTPS: ").b(TPS.b())
				     .a("\nloaded Areas: ").b(WgArea.areas.size()+"")
				     .a("\n\n\n");

		//TODO artwork / info combo
		m.insertJson(flag.a(0)).b("  "+WGC.servername);
		m.insertJson(flag.a(1)).a(" Online: ").b(ps.size()+"");
		m.insertJson(flag.a(2)); if(ps.size()>0) m.a("  - "+ps.get(0).getName());
		m.insertJson(flag.a(3)); if(ps.size()>1) m.a("  - "+ps.get(1).getName());
		m.insertJson(flag.a(4)); if(ps.size()>2) m.a("  - "+ps.get(2).getName());
		m.insertJson(flag.a(5)); if(ps.size()>3) m.a("  - "+ps.get(3).getName());
		m.insertJson(flag.a(6)); if(ps.size()>4) m.a("  - "+ps.get(4).getName());
		m.insertJson(flag.a(7)); if(ps.size()>5) m.a("  - "+ps.get(5).getName());
		m.insertJson(flag.a(8)); if(ps.size()>6) m.a("  - "+ps.get(6).getName());
		m.insertJson(flag.a(9)); if(ps.size()>8) m.a("  - ..."); else if(ps.size()>7) m.a("  - "+ps.get(7).getName());
		m.insertJson(flag.a(10)).a(motd);
		m.a(p);
	}
}
