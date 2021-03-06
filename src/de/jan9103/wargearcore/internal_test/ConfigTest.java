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
package de.jan9103.wargearcore.internal_test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import de.jan9103.wargearcore.C;

public class ConfigTest extends Test {
	private C c            =new C(null,new YamlConfiguration());
	private Material[] mats=Material.values();
	@Override public void run(){
		Random r=new Random();

		o+=headerLine("CONFIG TESTS",'_');

		longList(0,r);
		longList(1,r);
		for(int i=0; i<10; i++) longList(r);
		c.s("bool",false);
		if(c.gb("bool",true)){
			test_fail++; o+="\nBoolean-Parse Failed: false -> true (or default)";
		}
		else test_success++;
		stringList(0,r);
		stringList(1,r);
		for(int i=0; i<10; i++) stringList(r);
		materialList(0,r);
		materialList(1,r);
		for(int i=0; i<10; i++) materialList(r);
		//TODO add more

		o+=endLine();
	}

	private void stringList(Random r){
		stringList(r.nextInt(60)+1,r);
	}

	private void stringList(int size,Random r){
		List<String>l=new ArrayList<>();

		while(size-->0) l.add(randomString(r));
		String expected=listToString(l),name="sl"+r.nextInt();

		c.s(name,l);
		List<String>ret=c.gsl(name);

		if(ret==null){
			o+="\nStringList-Parse Failed: null returned::\n"+expected; test_fail++; return;
		}
		String got=listToString(ret);

		if(got.equalsIgnoreCase(expected)){
			test_success++; return;
		}
		o+="\nStringList-Parse Failed: Wrong Value returned::"
		    +"\n  "+expected
		    +"\n  -->"
		    +"\n  "+got;
	}

	private void materialList(Random r){
		materialList(r.nextInt(60)+1,r);
	}

	private void materialList(int size,Random r){
		List<Material>l=new ArrayList<>();

		while(size-->0) l.add(mats[r.nextInt(mats.length-1)]);
		String expected=listToString(l),name="ml"+r.nextInt();

		c.sml(name,l);
		List<Material>ret=c.gml(name,null);

		if(ret==null){
			o+="\nStringList-Parse Failed: null (default) returned::\n"+expected; test_fail++; return;
		}
		String got=listToString(ret);

		if(got.equalsIgnoreCase(expected)){
			test_success++; return;
		}
		o+="\nStringList-Parse Failed: Wrong Value returned::"
		    +"\n  "+expected
		    +"\n  -->"
		    +"\n  "+got;
	}

	private void longList(Random r){
		longList(r.nextInt(60)+1,r);
	}

	private void longList(int a,Random r){
		List<Long>l=new ArrayList<>();

		while(a-->0)
			l.add(r.nextLong());
		String name="LLT"+r.nextLong();
		String exp =listToString(l);

		c.sll(name,l);
		List<Long>k=c.gll(name,null);

		if(k==null){
			o+="\nLongList-Parse Failed: Default-Value returned::"
			    +"\n  "+exp;
			test_fail++;
			return;
		}
		if(!listToString(k).equalsIgnoreCase(exp)){
			o+="\nLongList-Parse Failed: Wrong Value returned::"
			    +"\n  "+exp
			    +"\n  -->"
			    +"\n  "+listToString(k);
			test_fail++;
			return;
		}
		test_success++;
	}
}
