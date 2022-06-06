---
title: Fight Modifiers
slug: fight/mods
---

Fight Modifiers (Mods) modify how a fight works by adding, changing, or removing features.

## Usage

List active mods: `/fight MODifier list`  
Add a mod: `/fight mod add NAME [OPTIONEN]`  
Remove: `/fight mod remove NAME`  
Remove all: `/fight mod clear`

## Expandability

There is an API, which lets you add you'r own mods (and share them with others).

In order to keep the main plugin smaller there are only 2 in it.
The `AdvancedWGCFight` plugin is a good option if you want more Mods.

## Existing Mods

### In WGC

Mod        | Inspiration | Description
---------- | ----------- | ------------
Bomber     | EuropSuchtis| TNT drops from the sky in regular intervals.
[Rising][] | GearForge   | Water slowly raises up and ruins the lower decks.

### In AdvancedWGCFight

Mod          | Inspiration | Description
------------ | ----------- | ------------
[EnderGear][]| MyPlayPlanet| Inventories, Positions, etc get swapped around.
ExplosiveWaterRemover|     |
GlowingPlayer|             | All players have the glowing potion effect.
GlowingTnt   |             | Every TNT has the glow effect
IceStorm     |             | Snowballs and ice-blocks drop from the sky.
NoGravTnt    |             | TNT has no gravity.
Randomizer   |             | Every few ticks something happens
SackRace     |             | You can only move while jumping
Silvester    |             | Explosive Fireworks (more visual than game affecting)
SpaceInvaders|             | The destroyable SpaceInvader Aliens drop bombs.


[EnderGear]: https://forum.myplayplanet.net/index.php?thread/3582-event-endergear-31-07-01-08
[Rising]: https://www.youtube.com/watch?v=x74tTuY-1iM
