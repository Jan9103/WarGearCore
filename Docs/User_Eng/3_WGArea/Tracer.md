---
title: Tracer
slug: tracer
---

## Basics

Command        | Description
-------------- | ------------
`/trace start` | Start recording
`/trace stop`  | Stop recording
`/trace show`  | Show recording
`/trace hide`  | Hide recording
`/trace clear` | Clear recording
`/trace end`   | `stop`, `hide`, `clear`

## Modes

`/trace mode MODE`  
Note: Without the correct [config option](#config) enabled switching modes wont retain all the data.

Mode           | Arguments | Description
-------------- | --------- | -------------------------------------------------------
ExplosionPoint |           | A temporary marker at the location of the explosion.
trail          |           | A temporary trail behind the TNT
OVerview       | v r       | Heavily watered down version of the normal mode.
NORMal         | v r       | The position of each TNT during each tick.
dot            | v r d     | Normal + Update points (as dots)
alldot         | v r       | dot without filter (LAGGY!)
line           | v r l d   | dot, but with lines between the dots
allline        | v r l     | line without filter (VERRY LAGGY!)

All arguments are optional and numbers.

Argument | Name          | Description
-------- | ------------- | ---------------------------------------------------------------
v        | View-distance | Only TNT within this distance get rendered (Blocks² (ex: 2000))
r        | Refresh-Rate  | Time between redraws (in GameTicks (ex: 40))
l        | Line-width    | Distance between line mode blocks (ex: 1)
d        | reDuction     | Filter strength (ex: 0.2)

