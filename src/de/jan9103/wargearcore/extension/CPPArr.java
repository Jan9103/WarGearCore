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

import de.jan9103.wargearcore.extension.vars.CPP;
import de.jan9103.wargearcore.extension.vars.SCPPArr;

public class CPPArr extends CPP {
	public final SCPPArr cd;
	public int i=0;
	public CPPArr(SCPPArr scppa){
		cd=scppa;
	}

	public CPP next(){
		if(++i>=cd.code.length) return null;

		return cd.code[i];
	}

	public CPP auto(){
		if(i>=cd.code.length) return null;

		return cd.code[i++];
	}

	public CPP get(){
		if(i>=cd.code.length) return null;

		return cd.code[i];
	}

	public CPP peek(){
		if(i+1>=cd.code.length) return null;

		return cd.code[i+1];
	}

	/**
	 * @return true for exit this cpp
	 */
	public boolean onEnd(VarSet vs){
		return true;
	}

	@Override public String debug(){
		return "CPPArr";
	}
}
