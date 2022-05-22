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

import java.util.ArrayList;
import java.util.Map.Entry;

import de.jan9103.wargearcore.WGC;
import de.jan9103.wargearcore.extension.vars.CMarker;
import de.jan9103.wargearcore.extension.vars.CPP;
import de.jan9103.wargearcore.extension.vars.SCPPArr;

public class CPPArrBuilder extends CPP {
	public final ArrayList<CPP>code=new ArrayList<>();
	@Override public String debug(){
		return "CPPArrB";
	}

	public SCPPArr build(ScriptLoader sl,Integer[] addr){
		/**cleaned code*/
		ArrayList<CPP>fc=new ArrayList<>();
		/**used to keep track to which line it will be in the final script*/
		int line=0;

		for(CPP i:code){
			if(i instanceof CMarker){
				for(Entry<String,CMarker>a:sl.jumps.entrySet())
					if(a.getValue()==i){
						sl.buildJump.put(a.getKey(),append(addr,line)); break;
					}
			}
			else{
				fc.add(i); line++;
			}
		}
		CPP[] cpp=new CPP[fc.size()];
		if(WGC.d){
			System.out.println("CPPA Content: -----");
			for(line=0; line<cpp.length; line++) System.out.println((cpp[line]=fc.get(line)).debug());
			System.out.println("---------------------");
		}
		return new SCPPArr(cpp);
	}

	public Integer[] append(Integer[] a,int i){
		Integer[] o=new Integer[a.length+1];
		for(int k=0; k<a.length; k++) o[k]=a[k];
		o[a.length]=i;
		return o;
	}
}
