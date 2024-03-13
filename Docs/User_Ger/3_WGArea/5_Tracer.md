---
title: Tracer
slug: tracer
---

## Basics

Befehl         | Beschreibung
-------------- | ------------
`/trace start` | aufzeichnung beginnen
`/trace stop`  | aufzeichnung beenden
`/trace show`  | positionen anzeigen
`/trace hide`  | positionen verstecken
`/trace clear` | alle vorhandenen Punkt löschen
`/trace end`   | Kurz für: `stop`, `hide`, `clear`

## Modes

`/trace mode MODE`  
Hinweis: Ohne die richtigen [config-einstellung](#config) gehen bei Mode-Wechseln meist Details
verloren!

Mode           | Argumente | Beschreibung
-------------- | --------- | -------------------------------------------------------
OVerview       | v r       | Eine stark reduzierter Normal-Mode
NORMal         | v r       | Die Positionen von TNT zu den Ticks.
dot            | v r d     | Norm + Update Positionen (mit filtern)
alldot         | v r       | dot ohne jegliche filter (LAGGY!)
line           | v r l d   | dot + Linien zwischen den Update-Punkten (mit filtern)
allline        | v r l     | line ohne filter (SEHR LAGGY!)

alle Argumente sind optional und Zahlen!

Argument | Name          | Beschreibung
-------- | ------------- | ---------------------------------------------------------------------------
v        | View-distance | Die sichtweite (Positionen ausserhalb werden nicht angezeigt) (in Blöcken² (z.b. 2000))
r        | Refresh-Rate  | Wie oft die Positionen geupdated werden (in Gameticks (z.b. 40))
l        | Line-width    | Abstand zwischen Blöcken beim Line-Mode (in Blöcken (z.b. 1))
d        | reDuction     | Filter-Stärke (in Blöcken abstand (z.b. 0.2))

