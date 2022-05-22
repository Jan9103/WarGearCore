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

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;

import javax.imageio.ImageIO;

import org.bukkit.Material;

public class ColorConverter {
	/**
	 * Convert RGB to concrete (or rather bgr)
	 * DATA SET BASE: https://www.reddit.com/r/MinecraftCommands/comments/9tdvfc/almost_perfect_minecraft_rgb_colors_in_percent
	 * @author: Jan9103
	 */
	public static Material rgb2concrete(final int b,final int g,final int r){
		final int sum=b+r+g;

		if(sum<250) return Material.BLACK_CONCRETE;

		if(sum>650) return Material.WHITE_CONCRETE;

		if(b>100){
			if(sum<500){
				if(r>100) return Material.GRAY_CONCRETE;

				return b<165?Material.LIGHT_BLUE_CONCRETE:Material.BLUE_CONCRETE;
			}
			if(g<150) return Material.PURPLE_CONCRETE;

			if(r>170) return Material.LIGHT_GRAY_CONCRETE;

			return g>195?Material.LIME_CONCRETE:Material.LIGHT_BLUE_CONCRETE;
		}
		if(sum<390){
			if(g<90) return Material.RED_CONCRETE;

			return r<125?Material.GREEN_CONCRETE:Material.BROWN_CONCRETE;
		}
		if(g>200) return Material.YELLOW_CONCRETE;

		if(sum<460) return Material.PINK_CONCRETE;

		return r<230?Material.MAGENTA_CONCRETE:Material.ORANGE_CONCRETE;
	}

	/*public static String rgb2formatting(final int b,final int g,final int r){
	 *      final int sum=b+r+g;
	 *      if(sum<250)return"§0";
	 *      if(sum>650)return"§f";
	 *
	 *      return"§0";
	 * }*/
	/**
	 * requires at leat "[\"\""+s+"]" afterwards
	 */
	public static String img2jsonChat(BufferedImage i){
		boolean        alpha=i.getTransparency()==1;
		String         o="";
		int            width=i.getWidth(),height=i.getHeight();
		WritableRaster wr=i.getRaster();

		int[] a=new int[] {0,0,0,0};
		for(int x=0; x<height; x++){
			for(int y=0; y<width; y++){
				a=wr.getPixel(y,x,a);
				if(a[3]==0&&alpha) o+=",{\"text\":\"\u2581\"}";
				else o+=",{\"text\":\"\u2588\",\"color\":\"#"+rgba2hex(a)+"\"}";
			}
			o+=",{\"text\":\"\n\"}";
		}
		return o;
	}

	private static String rgba2hex(int[] a){
		String o="";
		String i=Integer.toHexString(a[0]);

		if(i.length()==1) o+="0";
		o+=i;
		i =Integer.toHexString(a[1]);
		if(i.length()==1) o+="0";
		o+=i;
		i =Integer.toHexString(a[2]);
		if(i.length()==1) o+="0";
		o+=i;
		return o;
	}

	/**
	 * @param a int[]{r,g,b,alpha}
	 * @return
	 */
	public static String rgba2chat(int[] a,boolean alpha){
		if(a[3]==0&&alpha) return "§0\u2581"; //low alpha

		return rgbToChat(a[0],a[1],a[2])+"\u2588";

		/*
		 * final int sum=a[0]+a[1]+a[2];
		 * if(sum<70)return"§0\u2588";//black
		 * if(sum>680)return"§f\u2588";//white
		 * int hexth=sum>>3,avg=sum/3,min=avg-hexth,max=avg+hexth;
		 *
		 * if(a[0]>min){
		 *      if(a[0]>max){//r>
		 *              if(a[2]>avg)return"§5\u2588";//magenta
		 *              if(a[1]>max)return"§e\u2588";//yellow
		 *              if(a[1]>avg)return"§6\u2588";//gold
		 *              if(sum<200)return"§4\u2588";//dRed
		 *              return"§c\u2588";//lRed
		 *      }
		 *      if(a[1]>min){
		 *              if(a[1]<max){//r=  g=
		 *                      if(a[2]>min&&a[2]<max)
		 *                              return sum<370?"§8\u2588":"§7\u2588";//dGray:lGray
		 *                      if(a[2]<min)return"§e\u2588";//yellow
		 *              }else return"§a\u2588";//lime
		 *      }else return"§d\u2588";//pink
		 * }
		 * //System.out.println(a[0]+" "+a[1]+" "+a[2]+" a "+avg+" mi "+min+" ma "+max+" su "+sum);
		 * if(a[1]>a[2])return sum>340?"§a\u2588":"§2\u2588";//lime, dGreen
		 * if(a[1]>avg)return sum>330?"§b\u2588":"§3\u2588";//cyan
		 * //if(sum>350)return"§9\u2588";//lCyan
		 * if(sum>450)return"§b\u2588";//light blue  old: a[2]<max
		 * if(sum>350)return"§9\u2588";
		 * return"§1\u2588";//dBlue
		 */
	}

	public static String get(BufferedImage i){
		return get(i,i.getTransparency()>1);
	}

	public static String get(BufferedImage i,boolean alpha){
		int            width=i.getWidth(),height=i.getHeight();
		String         o ="";
		WritableRaster wr=i.getRaster();

		int[] a=new int[] {0,0,0,0};
		for(int x=0; x<height; x++){
			if(x>0) o+="\n";
			for(int y=0; y<width; y++)
				o+=rgba2chat(wr.getPixel(y,x,a),alpha);
		}
		return o;
	}

	/**
	 * @param h 20=opened chat, 13=closed chat
	 */
	public static BufferedImage scaleAuto(String url,float h)throws IOException {
		BufferedImage   i  =ImageIO.read(new URL(url));
		float           fac=Float.min(35f/i.getWidth(),h/i.getHeight());
		AffineTransform at =new AffineTransform();

		at.scale(fac,fac);//2.0 , 2.0
		AffineTransformOp scaleOp=new AffineTransformOp(at,AffineTransformOp.TYPE_BICUBIC);
		BufferedImage     after  =new BufferedImage(Math.round(fac*i.getWidth()),Math.round(fac*i.getHeight()),BufferedImage.TYPE_INT_ARGB);

		after=scaleOp.filter(i,after);
		return after;
	}

	public static BufferedImage scaleAuto(BufferedImage i){//35w 20h
		float           fac=Float.min(35f/i.getWidth(),20f/i.getHeight());
		AffineTransform at =new AffineTransform();

		at.scale(fac,fac);//2.0 , 2.0
		AffineTransformOp scaleOp=new AffineTransformOp(at,AffineTransformOp.TYPE_BICUBIC);
		BufferedImage     after  =new BufferedImage(Math.round(fac*i.getWidth()),Math.round(fac*i.getHeight()),BufferedImage.TYPE_INT_ARGB);

		after=scaleOp.filter(i,after);
		return after;
	}

	private static String rgbToChat(int r,int g,int b){
		int         min=Integer.MAX_VALUE,m;
		ColMatchCol closest=null;

		for(ColMatchCol i:cols){
			m=i.offs(r,g,b);
			if(m<min){
				min=m; closest=i;
			}
		}
		if(closest==null) return "§r";

		return closest.nm;
	}

	private static HashSet<ColMatchCol>cols=new HashSet<>();
	public static void init(){
		cols.add(new ColMatchCol("§0",0,0,0));
		cols.add(new ColMatchCol("§1",0,0,170));
		cols.add(new ColMatchCol("§2",0,170,0));
		cols.add(new ColMatchCol("§3",0,170,170));
		cols.add(new ColMatchCol("§4",170,0,0));
		cols.add(new ColMatchCol("§5",170,0,170));
		cols.add(new ColMatchCol("§6",255,170,0));
		cols.add(new ColMatchCol("§7",170,170,170));
		cols.add(new ColMatchCol("§8",85,85,85));
		cols.add(new ColMatchCol("§9",85,85,255));
		cols.add(new ColMatchCol("§a",85,255,85));
		cols.add(new ColMatchCol("§b",85,255,255));
		cols.add(new ColMatchCol("§c",255,85,85));
		cols.add(new ColMatchCol("§d",255,85,255));
		cols.add(new ColMatchCol("§e",255,255,85));
		cols.add(new ColMatchCol("§f",255,255,255));
	}

	private static class ColMatchCol {
		public final String nm;
		private final short r,g,b;
		public ColMatchCol(String n,int r,int g,int b){
			nm=n; this.r=(short)r; this.g=(short)g; this.b=(short)b;
		}

		public int offs(int pixR,int pixG,int pixB){
			return (pixR-r)*(pixR-r)+(pixG-g)*(pixG-g)+(pixB-b)*(pixB-b);
		}
	}
}
