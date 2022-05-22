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
package de.jan9103.wargearcore.api.wge;

import java.io.File;
import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.block.Block;

import de.jan9103.wargearcore.worldedit.clip.Clipboard;
import de.jan9103.wargearcore.worldedit.editors.ClipboardDiff;
import de.jan9103.wargearcore.worldedit.schem.SV2i;
import de.jan9103.wargearcore.worldedit.schem.SV2iz;
import de.jan9103.wargearcore.worldedit.schem.SchemFormat;

public class WGEClipboard {
	private final Clipboard a;
	/** Not meant for you - just a interface for me */
	public WGEClipboard(Clipboard a){
		this.a=a;
	}

	/**
	 * @param schematic: A schematic file (in a WGE-supported format)
	 * @throws IOException: Unable to load the file (invalid format/ file dosn't exist/ etc)
	 */
	public WGEClipboard(File schematic)throws IOException {
		a=SchemFormat.parseAndLoad(schematic);
		if(a==null) throw new IOException();
	}

	/**
	 * ONLY CALL FROM A SYNC PROCESS!
	 * @param location
	 * @param handler: nullable
	 */
	public void paste(Block location,WGEFinishHandler handler){
		a.autoPaste(location,handler);
	}

	public void mask(Material material){
		a.mask(material);
	}

	public void mask(Material[] material){
		a.mask(material);
	}

	public void inverseMask(Material material){
		a.inverseMask(material);
	}

	public void inverseMask(Material[] material){
		a.inverseMask(material);
	}

	public void unMask(Material material){
		a.unMask(material);
	}

	public int xSize(){
		return a.xSize();
	}

	public int ySize(){
		return a.ySize();
	}

	public int zSize(){
		return a.zSize();
	}

	public WGEClipboard diff(Clipboard other){
		ClipboardDiff cd=new ClipboardDiff(a,other); cd.run(); return new WGEClipboard(cd.out);
	}

	/**
	 * @param schematic: a file with the extension ".jsv2i"
	 * @throws IOException: it failed
	 */
	public void save(File schematic)throws IOException {
		if(!new SV2i().save(schematic,a)) throw new IOException();
	}

	/**
	 * @param schematic: a file with the extension ".jsv2iz"
	 * @throws IOException: it failed
	 */
	public void saveCompressed(File schematic)throws IOException {
		if(!new SV2iz().save(schematic,a)) throw new IOException();
	}
}
