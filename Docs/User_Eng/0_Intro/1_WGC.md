---
title: WarGearCore
slug: wgc
---

## What is WGC

WGC is a Bukkit Plugin for WarGear Home- and Teamserver.

Due to the amount of Features the Plugin consists of multiple sections:

### WGCore

Everything which doesn't fit into the other sections.

Example features:
- [NightVision](#other)
- [Reloader](#shears)
- [Fernzünder](#shears)
- [Portable Cannon](#pc)
- [Lag reduction](#performance)

### WGArea

WGA manages the areas and adds functionality to it.

Example Features:
- [Freeze & TNT settings](#area/basics)
- [TNT Tracer](#tracer)
- Customizable [TestBlock](#area/tb)
- Automatic [Backups][]

### [WGEdit](#wge/basics)

The integrated WorldEdit.

WGE is primarily used by the Plugin itself and is not very user-friendly.

Using normal WorldEdit alongside it is heavily encouraged.

WGE has a few special features like:
- a [resizer](#wge/resize)
- a [DiffTool](#wge/diff)
- a version block rotation [fixer](#wge/fix)

### [WGFight](#fight/basics)

This manages the Fights.

WGF offers [Modifiers][] like:
- Rising Tides
- TNT Rain
- EnderGear (with an extension)

---

## Goals of this project

- Low set-up effort
- A good toolset
	- Include all the common tools
	- Include new ideas
	- Usage similar to what you're used to
- [Support for all Sizes and rules](#area/manage)
- More interesting fights with [Modifiers][]
- High server stability
	- no random crashes or world corruptions
	- [Backups][]
- Extendable (APIs) & Customizable (Example: [Chat][])


[Modifiers]: #fight/mods
[Chat]: #chat
[Backups]: #area/backup
