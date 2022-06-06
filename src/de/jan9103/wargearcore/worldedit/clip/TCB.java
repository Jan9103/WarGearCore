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

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

import de.jan9103.wargearcore.area.ATheme;
import de.jan9103.wargearcore.area.ThemeMat;
import de.jan9103.wargearcore.worldedit.schem.SchemUtil;

public class TCB extends ClipboardBlock {
	private static final long serialVersionUID=4816018746773757049L;
	public final ThemeMat tm;
	public final boolean arena;
	public TCB(ThemeMat thm,boolean a){
		tm=thm; arena=a;
	}

	public TCB(byte b){
		arena=SchemUtil.gBit(b,7);
		tm   =SchemUtil.tm(SchemUtil.sBit(b,7,false));
	}

	public byte compress(){
		return SchemUtil.sBit(SchemUtil.tm(tm),7,arena);
	}

	public void blockPaste(Block b,ATheme.THM ta,ATheme.THM ts,boolean red){
		b.setType(ATheme.get(arena?ta:ts,tm,red));
	}

	public Material getM(ATheme.THM ta,ATheme.THM ts,boolean red){
		return ATheme.get(arena?ta:ts,tm,red);
	}

	@Override public Material getM(){
		return ATheme.get(ATheme.THM.GRAY,tm,true);
	}

	@Override public BlockData getD(){
		return Bukkit.createBlockData(getM());
	}

	@Override public void blockPaste(Block b){
		b.setType(getM(),false);
	}

	@Override public ClipboardBlock copy(){
		return new TCB(tm,arena);
	}

	@Override public boolean compare(ClipboardBlock b){
		if(!(b instanceof TCB)) return false;

		return tm==((TCB)b).tm;
	}
}