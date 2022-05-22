---
title: Fight Modifikatoren
slug: fight/mods
---

Fight Modifikatoren (Mods) modifizieren wie der Fight abläuft oder Sachen sich
verhalten.  

## Bedienung

Auflisten der aktiven: `/fight MODifier list`  
Hinzufügen: `/fight mod add NAME [OPTIONEN]`  
Entfernen: `/fight mod remove NAME`  
Alle Entfernen: `/fight mod clear`

## Erweiterbarkeit

Es können mehr Mods von anderen Plugins über die API hinzugefügt werden.  
Um das Haupt-Plugin für Homeserver klein zu halten habe ich viele Mods
ausgelagert in das `AdvancedWGCFight` Plugin - allerdings gibt es villeicht
auch welche von der Community oder ihr schreibt euch selber welche.

## Vorhandene Mods

### In WGC

Mod        | Inspiration | Beschreibung
---------- | ----------- | ------------
Bomber     | Europsuchtis| Es fällt regelmäßig TNT vom Himmel
[Rising][] | GearForge   | Es steigt Wasser (oder was anderen) vom Boden (einfach mal `WarGear Rising Tides` googeln)

## Im AdvancedWGCFight Plugin

Mod          | Inspiration | Beschreibung
------------ | ----------- | ------------
[Endergear][]| MyPlayPlanet| Inventare, Positionen, etc werden regelmäßig getauscht
Explosivewaterremover|     | Bei der Wasserentfernung explodiert es noch einmal.
Glowingplayer|             | Alle Spieler haben den Glowing Effekt.
Glowingtnt   |             | Jedes tnt hat den Glowing Effekt.
Icestorm     |             | Es schneit Schneebälle und Eisblöcke.
NoGravTnt    |             | TNT hat keine Gravitation (auch beim zünden)
Randomizer   |             | Alle paar ticks passiert irgendwas (bislang nicht viele Events).
SackRace     |             | Sackhüpfen
Silvester    |             | Explosive Silvesterrakten (war mehr ein witz zu silvester denn was ernstes)
SpaceInvaders|             | Bomber mit einer schönen Animation und anderem Muster


[Endergear]: https://forum.myplayplanet.net/index.php?thread/3582-event-endergear-31-07-01-08
[Rising]: https://www.youtube.com/watch?v=x74tTuY-1iM
