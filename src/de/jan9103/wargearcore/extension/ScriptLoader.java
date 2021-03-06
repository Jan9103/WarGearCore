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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import de.jan9103.wargearcore.extension.vars.CBasicOp;
import de.jan9103.wargearcore.extension.vars.CEnum;
import de.jan9103.wargearcore.extension.vars.CMarker;
import de.jan9103.wargearcore.extension.vars.CPP;
import de.jan9103.wargearcore.extension.vars.CVarFunction;
import de.jan9103.wargearcore.extension.vars.CVarLink;
import de.jan9103.wargearcore.extension.vars.SCPPArr;
import de.jan9103.wargearcore.extension.vars.SIntVar;
import de.jan9103.wargearcore.extension.vars.SStrVar;

public class ScriptLoader {
	private final InputStreamReader isr;
	private final ExtensionLoader el;
	public final String name,cmdJumpPoint;
	/**unrefined output*/
//	@Deprecated private final ArrayList<CPP>code=new ArrayList<>();
	public final ArrayDeque<CPPArrBuilder>cpp=new ArrayDeque<>();
	/**script-file read buffer*/
	private final ArrayDeque<Integer>buffer=new ArrayDeque<>();
	/**jump points for easier parsing and refining later on*/
	final HashMap<String,CMarker>jumps            =new HashMap<>();
	HashMap<String,Integer[]>buildJump            =new HashMap<>();
	private final HashMap<String,CVarLink>varLinks=new HashMap<>();
	public CVarLink getCVarLink(String s){
		CVarLink i=varLinks.get(s);

		if(i!=null) return i;

		i=new CVarLink(s);
		varLinks.put(s,i);
		return i;
	}

	public ScriptLoader(File f,ExtensionLoader e,String commandJumpPoint)throws FileNotFoundException {
		isr=new InputStreamReader(new FileInputStream(f),StandardCharsets.UTF_8); cmdJumpPoint=commandJumpPoint; el=e; name=f.getName().split(".",2)[0];
	}

	/**pop to next non whitespace*/
	public int popNW() throws IOException {
		int i; while((i=pop())!=-1) if(!isSpace(i)) return i; return -1;
	}

	public void skipW()throws IOException {
		int i; while((i=pop())!=-1) if(!isSpace(i)){
				buffer.push(i); return;
			}
	}

	public int pop()throws IOException {
		if(!buffer.isEmpty()) return buffer.pop(); return isr.read();
	}

	public int peek()throws IOException {
		if(!buffer.isEmpty()) return buffer.peek(); int c=isr.read(); buffer.push(c); return c;
	}

	public void next(int c){
		buffer.push(c);
	}

	public void add(CPP c){
		cpp.peek().code.add(c);
	}

	/**@return true -> a bracket to much*/
	public boolean closeBracket(){
		if(cpp.size()<2) return true;

		cpp.pop(); return false;
	}

	//if(cpp.size()>2)return true;
	//cpp.pop();return false;}
	public void openBracket(){
		CPPArrBuilder b=new CPPArrBuilder(); add(b); cpp.push(b);
	}

	public void parse()throws IOException {
		cpp.add(new CPPArrBuilder());
		int i;

		while((i=popNW())!=-1)
			switch(i){
			case ':': {      //JUMP POINT
				CMarker cm=new CMarker();
				add(cm);
				jumps.put(parseName(),cm);
				continue;
			}

			case '+':
				if((i=pop())==-1) throw new ScriptParseException("Missing something at the end..");
				if(i=='='){
					add(CBasicOp.ADDEQ); add(parseValue());
				}
				else{
					add(CBasicOp.ADD); next(i); add(parseValue());
				}
				continue;

			case '-': {
				if((i=pop())==-1) throw new ScriptParseException("Missing something at the end..");
				if(Character.isDigit((char)i)){
					next(i); next('-'); add(getInt());
				}
				else{
					if((i=pop())==-1) throw new ScriptParseException("Missing something at the end..");
					if(i=='='){
						add(CBasicOp.SUBEQ); add(parseValue());
					}
					else{
						add(CBasicOp.SUB); next(i); add(parseValue());
					}
				} continue;
			}

			case '*':
				if((i=pop())==-1) throw new ScriptParseException("Missing something at the end..");
				if(i=='='){
					add(CBasicOp.MULEQ); add(parseValue());
				}
				else{
					add(CBasicOp.MUL); next(i); add(parseValue());
				}
				continue;

			case '/':
				if((i=pop())==-1) throw new ScriptParseException("Missing something at the end..");
				if(i=='='){
					add(CBasicOp.DIVEQ); add(parseValue());
				}
				else if(i=='/') skipInlineComment();
				else{
					add(CBasicOp.DIV); next(i); add(parseValue());
				}
				continue;

			case '%':
				if((i=pop())==-1) throw new ScriptParseException("Missing something at the end..");
				if(i=='='){
					add(CBasicOp.MODEQ); add(parseValue());
				}
				else{
					add(CBasicOp.MOD); next(i); add(parseValue());
				}
				continue;

			case '\"': add(getString()); continue;

			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case '0':
				next(i); add(getInt()); continue;

			case '=':
				if((i=pop())==-1) throw new ScriptParseException("Missing something at the end..");
				if(i=='='){
					add(CBasicOp.DUALEQUAL); add(parseValue());
				}
				//TODO			else{code.add(CBasicOp.EQ);next(i);add(parseValue());}
				continue;

			case '$':
				add(getCVarLink(parseName()));
				continue;

			case '.': {
				String cmd=parseName().toLowerCase();
				if(el.funcs.containsKey(cmd)) add(el.funcs.get(cmd));
				else{
					CVarFunction fun=new CVarFunction(cmd);
					add(fun);
					el.funcs.put(cmd,fun);
				}
				continue;
			}

			case ',': {add(CBasicOp.COMMA); continue;}

			case 's':
				if((i=pop())==-1) throw new ScriptParseException("Missing something at the end..");
				switch(i){
				case 'l':
					if((i=pop())==-1) throw new ScriptParseException("Missing something at the end..");
					if(i=='e'){
						if((i=pop())==-1) throw new ScriptParseException("Missing something at the end..");
						if(i=='e'){
							if((i=pop())==-1) throw new ScriptParseException("Missing something at the end..");
							if(i=='p'){
								add(CEnum.SLEEP);
								continue;
							}
						}
					}

				default: throw new ScriptParseException("Unknown Command: s.."+(char)i+"");
				}
				//TODO
			}
	}

	public CPP parseValue()throws IOException {
		int i;

		if((i=popNW())==-1) throw new ScriptParseException("Missing something at the end..");
		switch((char)i){
		case '\"': return getString();

		case '-':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
		case '0': {
			//	int k;
			//	if((k=popNW())==-1)throw new ScriptParseException("Missing something at the end..");
			//	next(k);
			next(i);
			return getInt();
		}

		//TODO (
		//TODO $
		case '$':
			return getCVarLink(parseName());

		default: throw new ScriptParseException("Expected a value or variable..");
		}
	}

	public String parseName()throws IOException {
		StringBuilder o=new StringBuilder();
		int           i;

		while((i=pop())!=-1){
			if(!Character.isAlphabetic((char)i)) return o.toString();

			o.append((char)i);
//			switch(i){
//				case'\\':
//					if((i=pop())==-1)return o.toString();
//					if(!Character.isAlphabetic((char)i))return o.toString();
//					o.append((char)i);
//				case'"':return o.toString();
//			}
		}
		return o.toString();
	}

	public void skipComment()throws IOException {
		int i;

		while((i=pop())!=-1)
			if(i=='*'){
				if((i=pop())==-1) throw new ScriptParseException("Comment at fileend is not closed..");
				if(i==(byte)'/') return; //comment ended
			}
		throw new ScriptParseException("Comment at fileend is not closed..");
	}

	public void skipInlineComment()throws IOException {
		int i;

		while((i=pop())!=-1)
			if(i=='\n') return;
	}

	public int intParser()throws IOException {
		StringBuilder o=new StringBuilder();
		int           i;

		skipW();
		while(true){
			if((i=pop())==-1) throw new ScriptParseException("Missing a number at the end..");
			if(Character.isDigit((char)i)){
				o.append((char)i); break;
			}
			if(i=='-') o.append('-');
			else throw new ScriptParseException("Number was Expected..");
		}
		while((i=pop())!=-1){
			if(Character.isDigit((char)i)) o.append((char)i);
			else{
				next(i); break;
			}
		}
		try{return Integer.parseInt(o.toString());}catch(NumberFormatException e){return 0;}
	}

	public SIntVar getInt()throws IOException {
		int     i=intParser();
		SIntVar v=el.ints.get(i);

		if(v!=null) return v;

		v=new SIntVar(i);
		el.ints.put(i,v);
		return v;
	}

	public SStrVar getString()throws IOException {
		return new SStrVar(parseString());
	}

	public String parseString()throws IOException {
		StringBuilder o=new StringBuilder();
		int           i;

		while((i=pop())!=-1){
			if(i=='\\'){
				if((i=pop())==-1) throw new ScriptParseException("Missing closing tag of a String..");
				switch(i){
				case 'n': o.append('\n'); break;

				case 't': o.append('\t'); break;

				default: o.append((char)i);
				}
			}
			else if(i=='"'){
				System.out.println("String: "+o.toString()); return o.toString();
			}
			o.append((char)i);
		}
		throw new ScriptParseException("Missing closing tag of a String..");
	}

	public static class ScriptParseException extends IOException {
		private static final long serialVersionUID=1081351362147409185L;
		public final String msg;
		public ScriptParseException(String msg){
			this.msg=msg;
		}
	}
	public boolean isSpace(int c){
		return c==' '||c=='\n'||c=='\t';
	}

	public class CBlockLoader {
		//TODO
	}
	public Script build(Extension e,ExtensionManager em){
		//close all brackets
		while(!closeBracket());
		SCPPArr a=cpp.peek().build(this,new Integer[0]);

		//TODO
		return new Script(buildJump,a,name,cmdJumpPoint);
		//return null;//CREATE ERROR TO FIND THIS AGAIn
//		HashMap<String,Integer>jump=new HashMap<>();
//		/**cleaned code*/
//		ArrayList<CPP>fc=new ArrayList<>();
//		/**used to keep track to which line it will be in the final script*/
//		int line=0;
//		for(CPP i:code){
//			if(i instanceof CMarker){
//				for(Entry<String,CMarker>a:jumps.entrySet())
//					if(a.getValue()==i)jump.put(a.getKey(),line);
//			}else{fc.add(i);line++;}
//		}
//		CPP[]cpp=new CPP[fc.size()];
//		System.out.println("Script Content: -----");
//		for(line=0;line<cpp.length;line++)System.out.println((cpp[line]=fc.get(line)).debug());
//		System.out.println("---------------------");
//		return new Script(buildJump,a,name,cmdJumpPoint);
	}
}
