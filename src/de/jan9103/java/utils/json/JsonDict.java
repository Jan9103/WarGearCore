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

import java.util.HashMap;
import java.util.Map;

public class JsonDict extends HashMap<String,Json>implements Json{
	private static final long serialVersionUID=-296447121930805753L;
	@Override public String encode(){
		StringBuilder b=new StringBuilder("{");
		for(Map.Entry<String,Json>i:this.entrySet())
			b.append('"').append(StringEscape.escape(i.getKey()))
					.append("\":").append(i.getValue().encode());
		return b.append('}').toString();
	}
	@Override public Json jq(String[]a,int i)throws JqException{
		if(i==a.length)return this;
		switch(Character.toLowerCase(a[i].charAt(0))){
			case'{':
				if(a[i].length()==2)return jqAll(a,i);
				return jqInd(a[i].substring(1,a[i].length()-2),a,i);
			case'*':return jqAll(a,i);
			default:return jqInd(a[i],a,i);
		}
	}
	private Json jqInd(String in,String[]a,int i)throws JqException{i++;return get(in).jq(a,i);}
	private Json jqAll(String[]a,int i)throws JqException{
		i++;
		JsonArray o=new JsonArray();
		for(Json j:this.values())o.add(j.jq(a,i));
		return o;
	}
}
