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
package de.jan9103.java.utils.json;

import de.jan9103.java.utils.StringEscape;

/**
 * the parser only parses:
 * - int, long, double (all floats as double; else int; or if to big for int: long)
 * - string
 * - boolean
 * you can input for the encoder:
 * - String, Character
 * - Integer, Byte, Short, Long, Double, Float
 * - Boolean
 */
public class JsonValue implements Json{
	public JsonValue(Object o){value=o;}
	public Object value=null;
	public String encode(){
		if(value==null)return"null";
		if(value instanceof String)return '"'+StringEscape.escape((String)value)+'"';
		if(value instanceof Character)return '"'+StringEscape.escape(""+value);
		if(value instanceof Integer||value instanceof Byte||value instanceof Short||value instanceof Long||
				value instanceof Boolean||value instanceof Double||value instanceof Float)
			return value+"";
		throw new IllegalArgumentException();
	}
	@Override public Json jq(String[] q,int ind) throws JqException{return this;}
	public String asStr()throws ClassCastException{return(String)value;}
	public int asInt()throws ClassCastException{return(int)value;}
	public Long asLong()throws ClassCastException{return(Long)value;}
	public double asDouble()throws ClassCastException{return(double)value;}
	public boolean asBool()throws ClassCastException{return(boolean)value;}
}
