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
package de.jan9103.wargearcore.fight;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.jan9103.wargearcore.C;
import de.jan9103.wargearcore.LogHandler;
import de.jan9103.wargearcore.WGC;

public class InvSaver {
	private ItemStack[] armor=null,content=null;
	public String name=null;
	public void copy(Player p){
		armor  =p.getInventory().getArmorContents();
		content=p.getInventory().getContents();
	}

	public void save(String name){
		this.name=name;
		File f=new File(WGC.dataFolder+"/inventories/"+C.encodeFileName(name)+".yml");
		YamlConfiguration c=new YamlConfiguration();

		if(armor!=null) c.set("a",armor);
		if(content!=null) c.set("c",content);
		try{c.save(f);}catch(IOException var4){LogHandler.handleException(var4);}
	}

	@SuppressWarnings({
		"unchecked","rawtypes"
	})
	public void load(String name){
		this.name=name;
		File f=new File(WGC.dataFolder+"/inventories/"+C.encodeFileName(name)+".yml");

		if(f.exists()){
			YamlConfiguration c=YamlConfiguration.loadConfiguration(f);
			armor  =c.isSet("a")?(ItemStack[])((List)c.get("a")).toArray(new ItemStack[0]):null;
			content=c.isSet("c")?(ItemStack[])((List)c.get("c")).toArray(new ItemStack[0]):null;
		}
	}

	public void apply(Player p){
		if(p==null) return;

		if(armor!=null) p.getInventory().setArmorContents(armor);
		if(content!=null) p.getInventory().setContents(content);
	}
}
