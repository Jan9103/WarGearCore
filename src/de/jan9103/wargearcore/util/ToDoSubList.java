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
package de.jan9103.wargearcore.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.ItemStack;

import de.jan9103.wargearcore.User;
import de.jan9103.wargearcore.WGC;
import de.jan9103.wargearcore.chat.Co;
import de.jan9103.wargearcore.util.gui.InvClickMenu;

public final class ToDoSubList {
	/**
	 * (timestamp target (or just a space) (content)
	 */
	public ArrayList<String>a=new ArrayList<>();
	public ArrayList<String>soon(Long til){
		ArrayList<String>o=new ArrayList<>();

		for(String i:a){
			int z=i.indexOf(' ');
			if(z>0)
				try{if(Long.parseLong(i.substring(0,z))<til) o.add(i); }catch(NumberFormatException e){}
		}
		return o;
	}

	public void add(String s){
		int z=s.indexOf(' ');

		if(z>0) s=s.substring(0,z);
		rem(s);
		a.add(s);
		Collections.sort(a);
	}

	public void rem(String s){
		if(!a.remove(s)){
			int z=s.indexOf('\n');
			if(z>0) s=s.substring(0,z);
			System.out.println("DEBUG-1: \""+s+"\"");
			for(String i:a){
				z=i.indexOf(' ');
				if(z>0){
					System.out.println("DEBUG-2: \""+i.substring(z)+"\"");
					if(i.substring(z)==s) a.remove(i);
				}
			}
		}
	}

	/**
	 * (content)\n(date stuff)
	 */
	public void open(final User u,String name){
		InvClickMenu.InvClickMenuBuilder b=new InvClickMenu.InvClickMenuBuilder(u)
						    .setTitle(Co.NORM,name);
		for(String i:a){
			int z=i.indexOf(' ');
			if(z>0)
				try{i+="\n"+WGC.sdfHour.format(new Date(Long.parseLong(i.substring(0,z))));}catch(NumberFormatException e){}
				i=i.substring(0,z);
			b.addOption(Material.PAPER,i);
		}
		b.build((e,us)->{
			e.setCancelled(true);
			if(e.getSlotType()!=SlotType.CONTAINER) return;

			final ItemStack i=e.getCurrentItem();
			if(i==null){
				e.getWhoClicked().closeInventory(); return;
			}
			switch(i.getType()){
			case GRAY_STAINED_GLASS_PANE: return;

			case PAPER:
				String nm=i.getItemMeta().getDisplayName();
				if(e.isShiftClick()&&e.isRightClick()){
					rem(nm);
					i.setType(Material.GRAY_STAINED_GLASS_PANE);
				}

			default:
			}
		});
	}

	public void save(File f)throws IOException {
		if(!f.exists()) f.createNewFile();
		final OutputStreamWriter w=new OutputStreamWriter(new FileOutputStream(f),StandardCharsets.UTF_8);

		for(String i:a) w.write(i);
		w.flush();
		w.close();
	}

	public ToDoSubList(){
	}

	public ToDoSubList(File f)throws IOException {
		final BufferedReader r=new BufferedReader(new InputStreamReader(new FileInputStream(f),StandardCharsets.UTF_8));
		String tmp;

		while((tmp=r.readLine())!=null) a.add(tmp);
		Collections.sort(a);
		try{r.close();}catch(IOException e){}
	}
}
