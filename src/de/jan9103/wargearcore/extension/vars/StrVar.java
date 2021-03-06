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

public class StrVar extends Var {
	public String s;
	public StrVar(){
		s="";
	}

	public StrVar(String str){
		s=str;
	}

	@Override public CVar add(CVar v){
		return new StrVar(s+v.toString());
	}

	@Override public CVar div(CVar v){
		ArrVar a=new ArrVar();

		for(String i:s.split(v.toString())) a.add(new StrVar(i));
		return a;
	}

	@Override public CVar mod(CVar v){
		// TODO Auto-generated method stub
		return new StrVar();
	}

	@Override public String toString(){
		return s;
	}

	@Override public CVar addEq(CVar v){
		s+=v.toString(); return this;
	}

	@Override public CVar subEq(CVar v){
		if(v instanceof ArrVar){
			for(CVar i:((ArrVar)v).a) subEq(i);
			return this;
		}
		s=s.replace(v.toString(),""); return this;
	}

	@Override public CVar mulEq(CVar v){
		StringBuilder b=new StringBuilder();

		for(int i=v.toInt(); i>0; i--) b.append(s);
		s=b.toString(); return this;
	}

	@Override public CVar divEq(CVar v){
		return this;
	}                                                //TODO reassign as array (split)

	@Override public CVar modEq(CVar v){
		return this;
	}                                                //TODO

	@Override public boolean equals(CVar v){
		return s==v.toString();
	}

	@Override public int toInt(){
		try{return Integer.parseInt(s);}catch(NumberFormatException e){return 0;}
	}

	@Override public CVar clone(){
		return new StrVar(s);
	}

	@Override public String debug(){
		return "StrVar: "+s;
	}

	@Override public CVar run(String cmd,ArrVar a){
		return this;
	}
}
