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
package de.jan9103.java.utils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * a simple http(s) get request sender.
 * primarly meant for rest-apis
 * use Browser if you need more options
 */
public class Curl{
	public static final String USERAGENT="Mozilla/5.0 (Windows Astley 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.246";
	public static String getHttp(String url)throws IOException{
		URL u=new URL(url);
		HttpURLConnection c=(HttpURLConnection)u.openConnection();
		c.setConnectTimeout(5000);
		c.setReadTimeout(5000);
		c.setRequestMethod("GET");
		c.setRequestProperty("DNT","1");
		c.setRequestProperty("user-agent",USERAGENT);
		c.connect();
		BufferedReader br=new BufferedReader(new InputStreamReader(c.getInputStream()));
		String o="",i;
		while((i=br.readLine())!=null)o+=i+"\n";
		c.disconnect();
		return o;
	}
	public static String getHttps(String url)throws IOException{return getHttps(url,new String[][]{});}
	public static String getHttps(String url,String[][]params)throws IOException{
		URL u=new URL(url);
		HttpsURLConnection c=(HttpsURLConnection)u.openConnection();
		c.setConnectTimeout(5000);
		c.setReadTimeout(5000);
		for(String[]i:params){
			c.addRequestProperty(i[0],i[1]);
			System.out.println(i[0]+": "+i[1]);
		}
		c.setRequestProperty("user-agent",USERAGENT);
		c.setRequestProperty("DNT","1");
		c.setRequestMethod("GET");
		c.connect();
		BufferedReader br=new BufferedReader(new InputStreamReader(c.getInputStream()));
		String o="",i;
		while((i=br.readLine())!=null)o+=i+"\n";
		c.disconnect();
		return o;
	}
	/**
	 * @param url
	 * @param params
	 * @param content_type application/json
	 * @param content
	 * @return
	 * @throws IOException
	 */
	public static String httpsPost(String url,String[][]params,String content_type,String content)throws IOException{
		URL u=new URL(url);
		HttpsURLConnection c=(HttpsURLConnection)u.openConnection();
		c.setConnectTimeout(5000);
		c.setReadTimeout(5000);
		c.setRequestProperty("Content-Type",content_type);
		for(String[]i:params){
			c.addRequestProperty(i[0],i[1]);
			System.out.println(i[0]+": "+i[1]);
		}
		c.setRequestProperty("user-agent",USERAGENT);
		c.setRequestProperty("DNT","1");
		c.setRequestMethod("POST");
		c.setDoOutput(true);
		c.connect();
		final OutputStream str=c.getOutputStream();
		str.write(content.getBytes());
		str.flush();
		str.close();
		BufferedReader br=new BufferedReader(new InputStreamReader(c.getInputStream()));
		String o="",i;
		while((i=br.readLine())!=null)o+=i+"\n";
		c.disconnect();
		return o;
	}
}