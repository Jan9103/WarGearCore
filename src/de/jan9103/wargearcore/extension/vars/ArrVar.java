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
package de.jan9103.wargearcore.extension.vars;

import java.util.ArrayList;

public class ArrVar extends Var {
	public final ArrayList<CVar>a;
	public ArrVar(){
		a=new ArrayList<>();
	}

	public ArrVar(CVar b){
		a=new ArrayList<>(); a.add(b);
	}

	public ArrVar(ArrayList<CVar>l){
		a=l;
	}

	public int size(){
		return a.size();
	}

	@Override public Var add(CVar v){
		ArrayList<CVar>al=new ArrayList<>();

		for(CVar i:a) if(!i.equals(v)) al.add(i);
		return new ArrVar(al);
	}

	@Override public CVar sub(CVar v){
		ArrayList<CVar>al=new ArrayList<>();

		al.addAll(a); al.add(v);
		return new ArrVar(al);
	}

	@Override public CVar mul(CVar v){
		// TODO
		return new IntVar();
	}

	@Override public CVar div(CVar v){
		// TODO
		return new IntVar();
	}

	@Override public CVar mod(CVar v){
		// TODO
		return new IntVar();
	}

	@Override public CVar addEq(CVar v){
		a.add(v); return this;
	}

	/**remove all instances of v*/
	@Override public CVar subEq(CVar v){
		for(CVar i:a) if(i.equals(v)) a.remove(i); return this;
	}

	@Override public CVar mulEq(CVar v){
		// TODO
		return this;
	}

	@Override public CVar divEq(CVar v){
		// TODO
		return this;
	}

	@Override public CVar modEq(CVar v){
		// TODO
		return this;
	}

	@Override public String toString(){
		return null;
	}

	@Override public boolean equals(CVar v){
		if(!(v instanceof ArrVar)) return false;

		for(CVar i:((ArrVar)v).a) if(!contains(i)) return false;

		return true;
	}

	public boolean contains(CVar v){
		for(CVar i:a) if(i.equals(v)) return true;

		return false;
	}

	/**sum*/
	@Override public int toInt(){
		int o=0; for(CVar i:a) o+=i.toInt(); return o;
	}

	@Override public Var clone(){
		ArrayList<CVar>al=new ArrayList<>(); al.addAll(a); return new ArrVar(al);
	}

	@Override public String debug(){
		return "ArrVar";
	}

	@Override public CVar run(String cmd,ArrVar arg){
		switch(cmd){
		case "join": {
			StringBuilder o=new StringBuilder();
			int           i=0; String sep=" ";
			switch(arg.size()){
			case 2:
				i  =Integer.max(arg.a.get(0).toInt(),0);
				sep=arg.a.get(1).toString();
				break;

			case 1:
				sep=arg.a.get(0).toString();
				break;
			}
			if(i<a.size()) o.append(a.get(i).toString());
			while(++i<a.size()) o.append(sep+a.get(i).toString());
			return new StrVar(o.toString());
		}
		}
		return this;
	}
}
