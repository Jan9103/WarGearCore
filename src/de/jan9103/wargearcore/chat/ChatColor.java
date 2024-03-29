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
package de.jan9103.wargearcore.chat;

/** used to get colors for colorschemes.
 * Used ids:
 * 0 NORM
 * 1 HIGH
 * 2 WI
 * 3 ERROR
 * 4 CRITICAL
 * 5 WARN
 * 6 FRAME
 * 7 CHAT NORM
 * 8 CHAT HIGH
 * 9 CHAT PING
 * 10CHAT LINK
 */
public class ChatColor {
	/** convert a color enum to its byte id */
	public static byte coToBy(Co c){
		switch(c){
		case CHAT: return 7;

		case CHAT_HIGH: return 8;

		case CHAT_PING: return 9;

		case CHAT_LINK: return 10;

		case CRITICAL: return 4;

		case ERROR: return 3;

		case FRAME: return 6;

		case HIGH: return 1;

		case WARN: return 5;

		case WI: return 2;

		default: return 0;
		}
	}

	@Deprecated
	public static String[] a(ChatTheme thm){
		switch(thm){
		//								NORM HIGH	WI	ERROR CRIT	WARN	FRM	C-NM c-HIGH C-PING	C-Link  C-CMD
		case BUKKIT: return new String[] {"§f","§6","§c","§c","§c","§c","§8","§7","§f","§f§n","",""};

		case ISA:       return new String[] {"§b","§c","§c","§c","§c","§c","§0","§7","§8§l","§6§l","",""};

		case MPP:       return new String[] {"§7","§6","§c","§c","§c","§c","§0","§f","§f§l","§a§l","",""};

		case OLD:       return new String[] {"§3","§2§l","§6","§4","§4§l","§4","§0","§7","§8§L","§6§l","",""};

		case PASTELL: return new String[] {"§a","§b","§d","§d","§c§l","§c§l","§e","§e","§b","§d","",""};

		case PHC:       return new String[] {"§8","§4","§8","§8","§8§l","§4","§0","§7","§8§l","§6§l","",""};

		case SW:        return new String[] {"§7","§e","§8","§e","§e","§e","§0","§7","§f","§f§l","",""};

		case WTU:       return new String[] {"§a","§a§l","§c","§4","§4§l","§4","§0","§7","§8§l","§6§l","",""};

		case XAMPP: return new String[] {"§7","§9","§c","§c","§c","§c","§0","§7","§9","§9","",""};

		case YETI:      return new String[] {"§7","§b","§f","§3","§3§l","§3","§8","§7","§f","§f§l","",""};

		case APRIL_FOOLS: return new String[] {"§k","§k","§k","§k","§k","§k","","","","",""};

		default:        return new String[] {"","","","","","","","","","",""};
		}
	}

	/** Generate JSON */
	public static String[] b(ChatTheme thm){
		return b(thm,true);
	}

	/** Get JSON templates for chat messages.
	 * @param thm: the chat theme
	 * @param pre16: is this pre mc1.16? (true-color support)
	 */
	public static String[] b(ChatTheme thm,boolean pre16){
		switch(thm){
		/*
		 * 0 NORM
		 * 1 HIGH
		 * 2 WI
		 * 3 ERROR
		 * 4 CRITICAL
		 * 5 WARN
		 * 6 FRAME
		 * 7 CHAT NORM
		 * 8 CHAT HIGH
		 * 9 CHAT PING
		 * 10CHAT LINK
		 * 11CHAT COMMAND
		 */
		case BUKKIT: return new String[] {"",",\"color\":\"gold\"",",\"color\":\"red\"",",\"color\":\"red\"",",\"color\":\"red\"",",\"color\":\"red\"",",\"color\":\"dark_gray\"",",\"color\":\"gray\"","","§n",",\"color\":\"blue\",\"underlined\":true",",\"color\":\"green\",\"underlined\":true"};

		case ISA: return new String[] {",\"color\":\"aqua\"",",\"color\":\"red\"",",\"color\":\"red\"",",\"color\":\"red\"",",\"color\":\"red\"",",\"color\":\"red\"",",\"color\":\"black\"",",\"color\":\"gray\"",",\"color\":\"dark_gray\",\"bold\":true",",\"color\":\"gold\",\"bold\":true",",\"color\":\"blue\",\"underlined\":true",",\"color\":\"green\",\"underlined\":true"};

		case MPP: return new String[] {",\"color\":\"gray\"",",\"color\":\"gold\"",",\"color\":\"red\"",",\"color\":\"red\"",",\"color\":\"red\"",",\"color\":\"red\"",",\"color\":\"black\"","",",\"bold\":true",",\"color\":\"green\",\"bold\":true",",\"color\":\"blue\",\"underlined\":true",",\"color\":\"green\",\"underlined\":true"};

		case OLD: return new String[] {",\"color\":\"dark_aqua\"",",\"color\":\"green\",\"bold\":true",",\"color\":\"gold\"",",\"color\":\"dark_red\"",",\"color\":\"dark_red\",\"bold\":true",",\"color\":\"dark_red\"",",\"color\":\"black\"",",\"color\":\"gray\"",",\"color\":\"dark_gray\"",",\"color\":\"gold\",\"bold\":true",",\"color\":\"blue\",\"underlined\":true",",\"color\":\"green\",\"underlined\":true"};

		case PASTELL: return new String[] {",\"color\":\"green\"",",\"color\":\"aqua\"",",\"color\":\"light_purple\"",",\"color\":\"light_purple\"",",\"color\":\"red\",\"bold\":true",",\"color\":\"red\",\"bold\":true",",\"color\":\"yellow\"",",\"color\":\"yellow\"",",\"color\":\"aqua\"",",\"color\":\"light_purple\"",",\"color\":\"blue\",\"underlined\":true",",\"color\":\"green\",\"underlined\":true"};

		case PHC: return new String[] {",\"color\":\"dark_gray\"",",\"color\":\"dark_red\"",",\"color\":\"dark_gray\"",",\"color\":\"dark_gray\"",",\"color\":\"dark_gray\",\"bold\":true",",\"color\":\"dark_red\"",",\"color\":\"black\"",",\"color\":\"gray\"",",\"color\":\"dark_gray\",\"bold\":true",",\"color\":\"gold\",\"bold\":true",",\"color\":\"blue\",\"underlined\":true",",\"color\":\"green\",\"underlined\":true"};

		case SW: return new String[] {",\"color\":\"gray\"",",\"color\":\"yellow\"",",\"color\":\"dark_gray\"",",\"color\":\"yellow\"",",\"color\":\"yellow\"",",\"color\":\"yellow\"",",\"color\":\"black\"",",\"color\":\"gray\"","",",\"bold\":true",",\"color\":\"blue\",\"underlined\":true",",\"color\":\"green\",\"underlined\":true"};

		case WTU: return new String[] {",\"color\":\"green\"",",\"color\":\"green\",\"bold\":true",",\"color\":\"red\"",",\"color\":\"dark_red\"",",\"color\":\"dark_red\",\"bold\":true",",\"color\":\"dark_red\"",",\"color\":\"black\"",",\"color\":\"gray\"",",\"color\":\"dark_gray\",\"bold\":true",",\"color\":\"gold\",\"bold\":true",",\"color\":\"blue\",\"underlined\":true",",\"color\":\"green\",\"underlined\":true"};

		case XAMPP: return new String[] {",\"color\":\"gray\"",",\"color\":\"blue\"",",\"color\":\"red\"",",\"color\":\"red\"",",\"color\":\"red\"",",\"color\":\"red\"",",\"color\":\"black\"",",\"color\":\"gray\"",",\"color\":\"blue\"",",\"color\":\"blue\"",",\"color\":\"blue\",\"underlined\":true",",\"color\":\"green\",\"underlined\":true"};

		case YETI: return new String[] {",\"color\":\"gray\"",",\"color\":\"aqua\"","",",\"color\":\"dark_aqua\"",",\"color\":\"dark_aqua\",\"bold\":true",",\"color\":\"dark_aqua\"",",\"color\":\"dark_gray\"",",\"color\":\"gray\"","",",\"bold\":true",",\"color\":\"blue\",\"underlined\":true",",\"color\":\"green\",\"underlined\":true"};

		case APRIL_FOOLS: return new String[] {",\"obfuscated\":true",",\"obfuscated\":true",",\"obfuscated\":true",",\"obfuscated\":true",",\"obfuscated\":true",",\"obfuscated\":true","","","","",",\"color\":\"blue\",\"underlined\":true",",\"color\":\"green\",\"underlined\":true"};

		default: return new String[] {"","","","","","","","","","","",""};
		}
	}

	/**
	 * Generate a prefix for a plugin message.
	 * @param thm chat theme
	 * @param nm1 first part of the plugin name
	 * @param nm2 second part of the plugin name
	 */
	@Deprecated
	public static String a(ChatTheme thm,String nm1,String nm2){
		switch(thm){
		case PHC: return "§4"+nm1+"§8"+nm2+">> ";

		case WTU: return "§3"+nm1+"§8§l"+nm2+"\u00bb ";

		case PASTELL: return "§b[§a§n"+nm1+"§b§n"+nm2+"§b] ";

		case BUKKIT: return "";

		case SW: return "§e"+nm1+"§8"+nm2+"\u00bb ";

		case MPP: return "§7[§b"+nm1+nm2+"§7] ";

		case ISA: return "§3["+nm1+nm2+"] §b";

		case APRIL_FOOLS: return "§kAPRIL FOOLS";

		//TODO YETI
		case XAMPP: return "§0§l[§9"+nm1+nm2+"§0§l] ";

		default: return "["+nm1+nm2+"] ";
		}
	}

	//TODO
	/**
	 * Generate a prefix for a plugin message.
	 * @param thm chat theme
	 * @param nm1 first part of the plugin name
	 * @param nm2 second part of the plugin name
	 */
	public static String aJ(ChatTheme thm,String nm1,String nm2){
		switch(thm){
		case PHC:
		case WTU:
		case PASTELL:
		case BUKKIT: return "";

		case SW:
		case MPP:
		case ISA:
		case APRIL_FOOLS:
		//TODO YETI
		case XAMPP:
		default: return "["+nm1+nm2+"] ";
		}
	}

	@Deprecated
	public static String b(ChatTheme thm,String nm,String area,String col){
		switch(thm){
		case PASTELL: return "§a<§b"+col+nm+"§a> ";

		case BUKKIT: return "§f<"+col+nm+"§f> ";

		case SW: return "§f"+col+nm+"§7>> ";

		case MPP: return "§8[§7"+col+area+"§8] §a"+nm+"§7: ";

		case YETI: return "§7<"+col+"§l"+area+"§7>§b"+nm+"§7: ";

		default: return "§7("+area+")§3"+col+nm+"§3: ";
		}
	}

	/**
	 * Generate JSON.
	 */
	public static String bJ(ChatTheme thm,String nm,String nmc,String area,String col,boolean pre16,String areahover,String namehover){
		switch(thm){
		case PASTELL: return ",{\"text\":\"<\",\"color\":\"green\"},{\"text\":\""+nm+"\",\"color\":\"aqua\",\"hoverEvent\":{\"action:\":\"show_text\",\"value\":\""+namehover+"\"}},{\"text\":\"> \",\"color\":\"green\"}";

		case BUKKIT: return ",{\"text\":\"<\",\"color\":\"white\"},{\"text\":\""+nm+"\",\"color\":\""+(nmc==null?"white":nmc)+"\",\"hoverEvent\":{\"action:\":\"show_text\",\"value\":\""+namehover+"\"}},{\"text\":\"> \",\"color\":\"white\"}";

		case SW: return ",{\"text\":\""+nm+"\",\"color\":\""+(nmc==null?"white":nmc)+"\",\"hoverEvent\":{\"action:\":\"show_text\",\"value\":\""+namehover+"\"}},{\"text\":\">> \",\"color\":\"gray\"}";

		case MPP: return ",{\"text\":\"[\",\"color\":\"dark_gray\"},{\"text\":\""+area+"\",\"color\":\""+(col==null?"gray":col)+"\",\"hoverEvent\":{\"action:\":\"show_text\",\"value\":\""+areahover+"\"}},{\"text\":\"] \",\"color\":\"dark_gray\"},{\"text\":\""+nm+"\",\"color\":\""+(nmc==null?"green":nmc)+"\",\"hoverEvent\":{\"action:\":\"show_text\",\"value\":\""+namehover+"\"}},{\"text\":\": \",\"color\":\"gray\"}";

		case YETI: return ",{\"text\":\"<\",\"color\":\"gray\"},{\"text\":\""+area+"\",\"color\":\""+(col==null?"gray":col)+"\",\"bold\":true,\"hoverEvent\":{\"action:\":\"show_text\",\"value\":\""+areahover+"\"}},{\"text\":\"> \",\"color\":\"gray\"},{\"text\":\""+nm+"\",\"color\":\"aqua\",\"hoverEvent\":{\"action:\":\"show_text\",\"value\":\""+namehover+"\"}},{\"text\":\": \",\"color\":\"gray\"}";

		//				[								AREA																]								NAME										>>
		default: return ",{\"text\":\"[\",\"color\":\"gray\"},{\"text\":\""+area+"\",\"color\":\""+(col==null?"green":col)+"\",\"hoverEvent\":{\"action:\":\"show_text\",\"value\":\""+areahover+"\"}},{\"text\":\"] \",\"color\":\"gray\"},{\"text\":\""+nm+"\",\"color\":\""+(nmc==null?"green":nmc)+"\",\"hoverEvent\":{\"action:\":\"show_text\",\"value\":\""+namehover+"\"}},{\"text\":\">> \",\"color\":\"gray\"}";
		}
	}

	/**
	 * Get int id of chat theme
	 */
	public static int toInt(ChatTheme t){
		switch(t){
		case PHC: return 1;

		case WTU: return 2;

		case APRIL_FOOLS: return 4;

		case PASTELL: return 5;

		case BUKKIT: return 6;

		case MPP: return 7;

		case SW: return 8;

		case ISA: return 9;

		case YETI: return 10;

		case XAMPP: return 11;

		//12 egg
		//13 pride
		default: return 0;
		}
	}

	/**
	 * int id to enum
	 */
	public static ChatTheme fromInt(int i){
		switch(i){
		case 1: return ChatTheme.PHC;

		case 2: return ChatTheme.WTU;

		case 4: return ChatTheme.APRIL_FOOLS;

		case 5: return ChatTheme.PASTELL;

		case 6: return ChatTheme.BUKKIT;

		case 7: return ChatTheme.MPP;

		case 8: return ChatTheme.SW;

		case 9: return ChatTheme.ISA;

		case 10: return ChatTheme.YETI;

		case 11: return ChatTheme.XAMPP;

		//12 egg
		//13 pride
		default: return ChatTheme.OLD;
		}
	}

	@Deprecated
	public static String a(ChatTheme thm,Co c){
		switch(c){
		case ERROR:
			switch(thm){
			case PHC: return "§8";

			case WTU: return "§4";

			case PASTELL: return "§d";

			case BUKKIT: return "§c";

			case SW: return "§e";              //TODO check

			case MPP: return "§c";

			case ISA: return "§c";

			case YETI: return "§3";

			default: return "§4";              //dark red
			}

		case HIGH:
			switch(thm){
			case PHC: return "§4";

			case WTU: return "§a§l";

			case PASTELL: return "§b";

			case BUKKIT: return "§6";

			case SW: return "§e";

			case MPP: return "§6";

			case ISA: return "§l";

			case YETI: return "§b";

			case APRIL_FOOLS: return "§k";

			default: return "§2§l";              //dark green
			}

		case NORM:
			switch(thm){
			case PHC: return "§8";

			case WTU: return "§a";

			case PASTELL: return "§a";

			case BUKKIT: return "§f";

			case SW: return "§7";

			case MPP: return "§7";

			case ISA: return "§b";

			case YETI: return "§7";

			case APRIL_FOOLS: return "§k";

			default: return "§3";              //aqua
			}

		case WI:
			switch(thm){
			case PHC: return "§8";

			case WTU: return "§c";

			case PASTELL: return "§d";

			case BUKKIT: return "§c";

			case SW: return "§8";              //TODO check

			case MPP: return "§c";

			case ISA: return "§c";

			case YETI: return "§f";

			case APRIL_FOOLS: return "§k";

			default: return "§6";              //gold
			}

		case CRITICAL:
			switch(thm){
			case PHC: return "§8§l";

			case BUKKIT: return "§c";

			case PASTELL: return "§c§l";

			case SW: return "§e";              //TODO check

			case MPP: return "§c";

			case ISA: return "§c";

			case YETI: return "§3§l";

			default: return "§4§l";              //dark red
			}

		case WARN:
			switch(thm){
			case BUKKIT: return "§c";

			case PASTELL: return "§c§l";

			case SW: return "§e";              //TODO check

			case MPP: return "§c";

			case ISA: return "§c";

			case YETI: return "§3";

			default: return "§4";
			}

		case CHAT:
			switch(thm){
			case PASTELL: return "§e";

			case MPP: return "§f";

			default: return "§7";
			}

		case CHAT_HIGH:
			switch(thm){
			case PASTELL: return "§b";

			case BUKKIT: return "§f";

			case SW: return "§f";

			case MPP: return "§f§l";              //TODO check

			case YETI: return "§f";

			default: return "§8§l";
			}

		case CHAT_PING:
			switch(thm){
			case PASTELL: return "§d";

			case BUKKIT: return "§f§n";

			case SW: return "§f§l";

			case MPP: return "§a§l";              //TODO check

			case YETI: return "§f§l";

			default: return "§6§l";
			}

		case FRAME:
			switch(thm){
			case PASTELL: return "§e";

			case BUKKIT: return "§8";

			default: return "§0";
			}

		default: return "";
		}
	}
}
