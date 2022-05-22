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

import java.util.Random;

import de.jan9103.wargearcore.util.Encryption;

public class EncryptionTests extends Test {
	@Override public void run(){
		Random r=new Random();

		o+=headerLine("ENCRYPTION TESTS",'_');
		for(int i=0; i<10; i++)
			enDecryptPW(randomString(r),randomString(r));
		o+=endLine();
	}

	private void enDecryptPW(String text,String pw){
		try{
			String en=Encryption.crypt(text,pw,true);
			String de=Encryption.crypt(en,pw,false);
			if(en==text){
				o+="\nDeEnCryptPW failed: Encrypted version matched Unencrypted version\n      "+text+"\n  --> "+en+"\n  --> "+de;
				test_fail++;
			}
			else if(de==text)
				test_success++;
			else{
				o+="\nDeEnCryptPW failed: Result dosnt match original\n      "+text+"\n  --> "+en+"\n  --> "+de;
				test_fail++;
			}
		}catch(Exception e){test_fail++; o+="\nDeEnCryptPW failed: "+e.getClass().getName()+"\n  "+e.getMessage();}
	}
}
