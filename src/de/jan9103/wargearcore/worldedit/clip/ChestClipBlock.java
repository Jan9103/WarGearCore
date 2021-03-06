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
package de.jan9103.wargearcore.worldedit.clip;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;

public class ChestClipBlock extends ClipboardBlock {
	private static final long serialVersionUID=-7053224780605658135L;
	private final BlockData d;
	private final ItemStack[] itms;
	public ChestClipBlock(BlockData data){
		d=data; itms=new ItemStack[0];
	}

	public ChestClipBlock(Chest c){
		d   =c.getBlockData();
		itms=c.getBlockInventory().getContents();
	}

	public ChestClipBlock(BlockData d,ItemStack[] it){
		itms  =it;
		this.d=d;
	}

	@Override public void blockPaste(Block b){
		b.setBlockData(d,false);
		((Chest)b.getState()).getBlockInventory().setContents(itms);
	}

	@Override public boolean waterlogged(){
		return false;
	}

	@Override public ClipboardBlock copy(){
		return new ChestClipBlock(d,itms);
	}

	@Override public Material getM(){
		return d.getMaterial();
	}

	@Override public BlockData getD(){
		return d;
	}

	public ItemStack[] getInv(){
		return itms;
	}

	@Override public boolean compare(ClipboardBlock b){
		return false;
	}
}
