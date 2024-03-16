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
package de.jan9103.wargearcore.area;

import org.bukkit.Material;

/**
 * Area Theme.
 * This file could really use a remake ^^
 */
public class ATheme {
	/**
	 * Area Theme Enum
	 */
	public enum THM {
		SANDSTONE,
		GRAY,
		CONSTRUCT,
		WT,
		WINTER,
		WORLDS,
		ES,
		ARENA, //SPECIAL USE ONLY !!
		//elements -> blu ice, red fire, floor earth
		DPR,
	}
	// --------------------------------------- NEW --------------------------------------
	/**
	 * convert an theme id to an enum
	 * (ids are used in schems, area files, etc)
	 */
	public static THM thm(byte b){
		switch(b){
			case 1: return THM.GRAY;
			case 2: return THM.SANDSTONE;
			case 3: return THM.WT;
			case 4: return THM.CONSTRUCT;
			case 5: return THM.WINTER;
			case 6: return THM.WORLDS;
			case 7: return THM.ES;
			case 8: return THM.DPR;
			default: return THM.ARENA;
		}
	}

	/**
	 * convert an theme enum to an id
	 * (ids are used in schems, area files, etc)
	 */
	public static byte thm(THM t){
		switch(t){
			case GRAY: return 1;
			case SANDSTONE: return 2;
			case WT: return 3;
			case CONSTRUCT: return 4;
			case WINTER: return 5;
			case WORLDS: return 6;
			case ES: return 7;
			case DPR: return 8;
			default: return 0; //ARENA / not set
		}
	}

	public static Material get(THM a,ThemeMat m,boolean red){
		switch(m){
			case ENEMY_DARK: red=!red;
			case OWN_DARK:
				switch(a){
					case WORLDS: return red?Material.NETHER_BRICKS:Material.GREEN_TERRACOTTA;
					case WINTER: return red?Material.DIORITE:Material.WHITE_CONCRETE;
					case GRAY: return Material.GRAY_CONCRETE;
					case DPR: return red?Material.RED_NETHER_BRICKS:Material.LAPIS_BLOCK;
					default: return red?Material.RED_CONCRETE:Material.LIGHT_BLUE_CONCRETE;
				}

			case ENEMY_LIGHT: red=!red;
			case OWN_LIGHT:
				switch(a){
					case WORLDS: return red?Material.RED_NETHER_BRICKS:Material.LIME_TERRACOTTA;
					case WINTER: return Material.SMOOTH_QUARTZ;
					case DPR: return red?Material.NETHER_WART_BLOCK:Material.LIGHT_BLUE_CONCRETE;
					default: return red?Material.RED_TERRACOTTA:Material.BLUE_CONCRETE;
				}

			case ENEMY_ARENA: red=!red;
			case OWN_ARENA:
				switch(a){
					case SANDSTONE: return Material.SMOOTH_SANDSTONE;
					case WINTER: return Material.SNOW_BLOCK;
					case WORLDS: return red?Material.NETHERRACK:Material.GRASS_BLOCK;
					case WT: return Material.CYAN_TERRACOTTA;
					case ES: return Material.OBSIDIAN;
					case DPR: return Material.BLACK_CONCRETE;
					default: return Material.GRAY_CONCRETE;
				}

			case ENEMY_BANNER_DARK: red=!red;
			case OWN_BANNER_DARK:
				switch(a){
					case WINTER: return Material.LIGHT_BLUE_BANNER;
					case WORLDS: return red?Material.RED_BANNER:Material.GREEN_BANNER;
					default: return red?Material.RED_BANNER:Material.BLUE_BANNER;
				}

			case ENEMY_BANNER_LIGHT: red=!red;
			case OWN_BANNER_LIGHT:
				switch(a){
					case WINTER: return Material.WHITE_BANNER;
					case WORLDS: return red?Material.ORANGE_BANNER:Material.LIME_BANNER;
					case SANDSTONE:
					case CONSTRUCT: return red?Material.ORANGE_BANNER:Material.LIGHT_BLUE_BANNER;
					default: return red?Material.BROWN_BANNER:Material.LIGHT_BLUE_BANNER;
				}

			case ENEMY_CARPET_DARK: red=!red;
			case OWN_CARPET_DARK:
				switch(a){
					case WINTER: return Material.LIGHT_BLUE_CARPET;
					case WORLDS: return red?Material.RED_CARPET:Material.GREEN_CARPET;
					default: return red?Material.RED_CARPET:Material.BLUE_CARPET;
				}

			case ENEMY_CARPET_LIGHT: red=!red;
			case OWN_CARPET_LIGHT:
				switch(a){
					case WINTER: return Material.WHITE_CARPET;
					case WORLDS: return red?Material.ORANGE_CARPET:Material.LIME_CARPET;
					case SANDSTONE:
					case CONSTRUCT: return red?Material.ORANGE_CARPET:Material.LIGHT_BLUE_CARPET;
					default: return red?Material.BROWN_CARPET:Material.LIGHT_BLUE_CARPET;
				}

			case ENEMY_CONCRETE_DARK: red=!red;
			case OWN_CONCRETE_DARK:
				switch(a){
					case WINTER: return Material.LIGHT_BLUE_CONCRETE;
					case WORLDS: return red?Material.RED_CONCRETE:Material.GREEN_CONCRETE;
					default: return red?Material.RED_CONCRETE:Material.BLUE_CONCRETE;
				}

			case ENEMY_CONCRETE_LIGHT: red=!red;
			case OWN_CONCRETE_LIGHT:
				switch(a){
					case WINTER: return Material.WHITE_CONCRETE;
					case WORLDS: return red?Material.ORANGE_CONCRETE:Material.LIME_CONCRETE;
					case SANDSTONE:
					case CONSTRUCT: return red?Material.ORANGE_CONCRETE:Material.LIGHT_BLUE_CONCRETE;
					default: return red?Material.BROWN_CONCRETE:Material.LIGHT_BLUE_CONCRETE;
				}

			case ENEMY_GATE: red=!red;
			case OWN_GATE:
				switch(a){
					case WINTER: return Material.BIRCH_FENCE_GATE;
					case DPR: return Material.SPRUCE_FENCE_GATE;
					default: return red?Material.ACACIA_FENCE_GATE:Material.OAK_FENCE_GATE;
				}

			case ENEMY_GLASS_DARK: red=!red;
			case OWN_GLASS_DARK:
				switch(a){
					case WINTER: return Material.LIGHT_BLUE_STAINED_GLASS;
					case WORLDS: return red?Material.RED_STAINED_GLASS:Material.GREEN_STAINED_GLASS;
					default: return red?Material.RED_STAINED_GLASS:Material.BLUE_STAINED_GLASS;
				}

			case ENEMY_GLASS_LIGHT: red=!red;
			case OWN_GLASS_LIGHT:
				switch(a){
					case WINTER: return Material.WHITE_STAINED_GLASS;
					case WORLDS: return red?Material.ORANGE_STAINED_GLASS:Material.LIME_STAINED_GLASS;
					case SANDSTONE:
					case CONSTRUCT: return red?Material.ORANGE_STAINED_GLASS:Material.LIGHT_BLUE_STAINED_GLASS;
					default: return red?Material.BROWN_STAINED_GLASS:Material.LIGHT_BLUE_STAINED_GLASS;
				}

			case ENEMY_GLAZED_DARK: red=!red;
			case OWN_GLAZED_DARK:
				switch(a){
					case WINTER: return Material.LIGHT_BLUE_GLAZED_TERRACOTTA;
					case WORLDS: return red?Material.RED_GLAZED_TERRACOTTA:Material.GREEN_GLAZED_TERRACOTTA;
					default: return red?Material.RED_GLAZED_TERRACOTTA:Material.BLUE_GLAZED_TERRACOTTA;
				}

			case ENEMY_GLAZED_LIGHT: red=!red;
			case OWN_GLAZED_LIGHT:
				switch(a){
					case WINTER: return Material.WHITE_GLAZED_TERRACOTTA;
					case WORLDS: return red?Material.ORANGE_GLAZED_TERRACOTTA:Material.LIME_GLAZED_TERRACOTTA;
					case SANDSTONE:
					case CONSTRUCT: return red?Material.ORANGE_GLAZED_TERRACOTTA:Material.LIGHT_BLUE_GLAZED_TERRACOTTA;
					default: return red?Material.BROWN_GLAZED_TERRACOTTA:Material.LIGHT_BLUE_GLAZED_TERRACOTTA;
				}

			case ENEMY_MARKER: red=!red;
			case OWN_MARKER:
				switch(a){
					case DPR: return Material.CYAN_TERRACOTTA;
					default:
				}
				break;

			case ENEMY_PANE_DARK: red=!red;
			case OWN_PANE_DARK:
				switch(a){
					case WINTER: return Material.LIGHT_BLUE_STAINED_GLASS_PANE;
					case WORLDS: return red?Material.RED_STAINED_GLASS_PANE:Material.GREEN_STAINED_GLASS_PANE;
					default: return red?Material.RED_STAINED_GLASS_PANE:Material.BLUE_STAINED_GLASS_PANE;
				}

			case ENEMY_PANE_LIGHT: red=!red;
			case OWN_PANE_LIGHT:
				switch(a){
					case WINTER: return Material.WHITE_STAINED_GLASS_PANE;
					case WORLDS: return red?Material.ORANGE_STAINED_GLASS_PANE:Material.LIME_STAINED_GLASS_PANE;
					case SANDSTONE:
					case CONSTRUCT: return red?Material.ORANGE_STAINED_GLASS_PANE:Material.LIGHT_BLUE_STAINED_GLASS_PANE;
					default: return red?Material.BROWN_STAINED_GLASS_PANE:Material.LIGHT_BLUE_STAINED_GLASS_PANE;
				}

			case ENEMY_POWDER_DARK: red=!red;
			case OWN_POWDER_DARK:
				switch(a){
					case WINTER: return Material.LIGHT_BLUE_CONCRETE_POWDER;
					case WORLDS: return red?Material.RED_CONCRETE_POWDER:Material.GREEN_CONCRETE_POWDER;
					case DPR: return Material.GRAVEL;
					default: return red?Material.RED_CONCRETE_POWDER:Material.BLUE_CONCRETE_POWDER;
				}

			case ENEMY_POWDER_LIGHT: red=!red;
			case OWN_POWDER_LIGHT:
				switch(a){
					case WINTER: return Material.WHITE_CONCRETE_POWDER;
					case WORLDS: return red?Material.ORANGE_CONCRETE_POWDER:Material.LIME_CONCRETE_POWDER;
					case SANDSTONE:
					case CONSTRUCT: return red?Material.ORANGE_CONCRETE_POWDER:Material.LIGHT_BLUE_CONCRETE_POWDER;
					case DPR: return Material.GRAVEL;
					default: return red?Material.BROWN_CONCRETE_POWDER:Material.LIGHT_BLUE_CONCRETE_POWDER;
				}

			case ENEMY_SAND: red=!red;
			case OWN_SAND:
				switch(a){
					case WT:
					case GRAY: return Material.GRAVEL;
					case DPR: return Material.GRAVEL;
					default: return red?Material.RED_SAND:Material.SAND;
				}

			case ENEMY_SHULKER_DARK: red=!red;
			case OWN_SHULKER_DARK:
				switch(a){
					case WINTER: return Material.LIGHT_BLUE_SHULKER_BOX;
					case WORLDS: return red?Material.RED_SHULKER_BOX:Material.GREEN_SHULKER_BOX;
					default: return red?Material.RED_SHULKER_BOX:Material.BLUE_SHULKER_BOX;
				}

			case ENEMY_SHULKER_LIGHT: red=!red;
			case OWN_SHULKER_LIGHT:
				switch(a){
					case WINTER: return Material.WHITE_SHULKER_BOX;
					case WORLDS: return red?Material.ORANGE_SHULKER_BOX:Material.LIME_SHULKER_BOX;
					case SANDSTONE:
					case CONSTRUCT: return red?Material.ORANGE_SHULKER_BOX:Material.LIGHT_BLUE_SHULKER_BOX;
					default: return red?Material.BROWN_SHULKER_BOX:Material.LIGHT_BLUE_SHULKER_BOX;
				}

			case ENEMY_SLAB: red=!red;
			case OWN_SLAB:
				switch(a){
					case SANDSTONE: return Material.SMOOTH_SANDSTONE_SLAB;
					case WINTER: return Material.SMOOTH_QUARTZ_SLAB;
					case WORLDS: return red?Material.NETHER_BRICK_SLAB:Material.COBBLESTONE_SLAB;
					case WT: return Material.SMOOTH_STONE_SLAB;
					case DPR: return Material.RED_NETHER_BRICK_SLAB;
					default: return Material.STONE_BRICK_SLAB;
				}

			case ENEMY_SLAB_HIGH: red=!red;
			case OWN_SLAB_HIGH:
				switch(a){
					case SANDSTONE: return Material.SMOOTH_RED_SANDSTONE_SLAB;
					case WINTER: return Material.DARK_PRISMARINE_SLAB;
					case WORLDS: return red?Material.RED_NETHER_BRICK_SLAB:Material.PRISMARINE_BRICK_SLAB;
					case WT: return Material.STONE_BRICK_SLAB;
					case DPR: return Material.STONE_BRICK_SLAB;
					default: return Material.BRICK_SLAB;
				}

			case ENEMY_TERRA_DARK: red=!red;
			case OWN_TERRA_DARK:
				switch(a){
					case WINTER: return Material.LIGHT_BLUE_TERRACOTTA;
					case WORLDS: return red?Material.RED_TERRACOTTA:Material.GREEN_TERRACOTTA;
					default: return red?Material.RED_TERRACOTTA:Material.BLUE_TERRACOTTA;
				}

			case ENEMY_TERRA_LIGHT: red=!red;
			case OWN_TERRA_LIGHT:
				switch(a){
					case WINTER: return Material.WHITE_TERRACOTTA;
					case WORLDS: return red?Material.ORANGE_TERRACOTTA:Material.LIME_TERRACOTTA;
					case SANDSTONE:
					case CONSTRUCT: return red?Material.ORANGE_TERRACOTTA:Material.LIGHT_BLUE_TERRACOTTA;
					default: return red?Material.BROWN_TERRACOTTA:Material.LIGHT_BLUE_TERRACOTTA;
				}

			case ENEMY_THIN_SHIELD: red=!red;
			case OWN_THIN_SHIELD:
				switch(a){
					case SANDSTONE: return Material.SMOOTH_SANDSTONE;
					case WINTER: return Material.PACKED_ICE;
					case WORLDS: return red?Material.NETHER_WART_BLOCK:Material.OAK_WOOD;
					case DPR: return Material.END_STONE;
					default: return Material.GRAY_STAINED_GLASS;
				}

			default:
				switch(a){
					case CONSTRUCT: return Material.YELLOW_CONCRETE;
					case SANDSTONE: return Material.BIRCH_WOOD;
					case WINTER: return Material.PACKED_ICE;
					case WORLDS: return Material.OBSIDIAN;
					case WT: return Material.SMOOTH_STONE;
					case ES: return Material.BEDROCK;
					case DPR: return Material.COAL_BLOCK;
					default: return Material.BLACK_CONCRETE;
				}
		}
		return Material.STONE;
	}
}
