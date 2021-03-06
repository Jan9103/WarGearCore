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
package de.jan9103.wargearcore.decentral.pkg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ByteArrWriter {
	private final ByteArrayOutputStream ba=new ByteArrayOutputStream();
	public ByteArrWriter(int pkgTypeId){
		ba.write((byte)pkgTypeId);
	}

	public ByteArrWriter a(String s)throws IOException {
		ba.write(i2ba(s.length()));
		ba.write(s.getBytes(StandardCharsets.UTF_8));
		return this;
	}

	public ByteArrWriter a(int a)throws IOException {
		ba.write(i2ba(a)); return this;
	}

	public ByteArrWriter a(byte a)throws IOException {
		ba.write(a); return this;
	}

	public byte[] get(){
		return ba.toByteArray();
	}

	private final byte[] i2ba(int value){
		return new byte[] {(byte)(value>>>24),(byte)(value>>>16),(byte)(value>>>8),(byte)value};
	}
}
