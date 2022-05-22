---
title: Basics
slug: area/basics
---

`/area` bzw die kurzform `/a` wird genutzt.

## Tnt schadens steuerung

Es gibt eine Berichsweise tnt schadenseinstellung,
die mit `/a tnt` gesteuert wird.

Zudem gibt es `/a protect`, welches ein zusätzlicher schutz ist,
der auch wenn `tnt` aktiv ist tnt schaden auf der bauseite verhindert
und stattdessen eine Warnung ausgibt.

## Freeze

Freeze kann mit `/a freeze` gesteuert werden.

Freeze beinflusst:
- Neu plazierter sand fällt nicht runter
- Vorhandene Blöcke erhalten keine Blockupdates
	- Sand fällt nicht wenn man ihn updated
	- Wasser fließt nicht wenn nebenstehende blöcke abgebaut werden
	- redstone geht nicht
	- uvm

## Aufbauen / Reparieren der arena

Wenn `worldtnt` aus ist sollte die arena nicht beschädigt werden.

Wenn ihr eine eigene Arena verwendet müsst ihr sie manuell reparieren.

Eingebaute presets können generiert werden mit:
- `/a floor`: erzeuge einen Boden
- `/a frame`: erzeuge einen Baurahmen
- `/a reset`: setze einene Berich zurück ([Backup](#area/backup))
- `/tb`: Generiere einene Testblock ([mehr Infos](#area/tb))
- `/a clean`: entfernt Blöcke ausserhalb der Ausfahrmaße (herumgeflogener Sand, etc)

## Themes

Man kann das Theme von Bereichen ändern, was die Farbpalette der von Plugin plazierten
Blöcke und Arena-Theme-Blöcken in WGE-Schematics beeinflusst.

Auflisten der verfügbaren Themes: `/a theme`.

Theme ändern: `/a theme NAME`.

Wenn dir kein Theme gefällt und du eine gute Idee hast kannst du es gerne im Discord
teilen und wenn es gut aussieht ist es vielleicht in der nächsten Version enthalten.

