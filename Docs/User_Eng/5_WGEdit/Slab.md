---
title: WGE Slab
slug: wge/slab
---

Syntax: `/(slab MATERIAL`  
Example: `/(slab glass`

Places slabs (or whatever) on Redstone going up.
But dosn't take Pistons or muzzles into consideration.

Solution to a few issues if you just want to prevent wrong placed EndStone:
- Leaves / Cobwebs:
	- Ignored by Pistons
	- Low TNT resistance
- Cave-Air
	- Acts the same as air, but `//rep air endstone` dosn't affect it
