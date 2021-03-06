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
package de.jan9103.wargearcore.util;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JSONObject { //TODO delee
	private final HashMap<String,Object>map=new HashMap<>();
	public void put(String key,Object value){
		if(value!=null) map.put(key,value);
	}

	@Override  public String toString(){
		final StringBuilder builder=new StringBuilder();
		final Set<Map.Entry<String,Object> >entrySet=map.entrySet();

		builder.append("{");
		int i=0;

		for(final Map.Entry<String,Object>entry:entrySet){
			final Object val=entry.getValue();
			builder.append(quote(entry.getKey())).append(":");
			if(val instanceof String) builder.append(quote(String.valueOf(val)));
			else if(val instanceof Integer) builder.append(Integer.valueOf(String.valueOf(val)));
			else if(val instanceof Boolean) builder.append(val);
			else if(val instanceof JSONObject) builder.append(val.toString());
			else if(val.getClass().isArray()){
				builder.append("[");
				final int len=Array.getLength(val);
				for(int j=0; j<len; j++) builder.append(Array.get(val,j).toString()).append(j!=len-1?",":"");
				builder.append("]");
			}
			builder.append(++i==entrySet.size()?"}":",");
		}
		return builder.toString();
	}

	private String quote(String string){
		return "\""+string+"\"";
	}
}
