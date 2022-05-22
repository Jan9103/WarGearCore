---
title: Portable Kanone
slug: pc
---

Die glitzernde Melonen-Scheibe ist eine Portable TNT kanone (linksclick zum schießen).

Mit `/pc MODE MODE-ARGS` kann sie eingestellt werden

## Nutzen

- Auszutesten wie viel ein WarGear aushält
- In einem Youtube video zeigen warum es im Zeitalter von SideStabs eine schlechte Idee
  ist 8 Kanonen nebeneinander zu plazieren.
- Stundenlang testblöcke beschießen anstatt produktiv zu seien
- Konzepte / delays zu testen (..)

## Modes

Mode         | Argument 1| Argument 2      | Argument 3                | Argument 4      | Argument 5
------------ | --------- | --------------- | ------------------------- | --------------- | ----------
stich / s    | tnt menge | delay           | geschwindigkeit (<3 = arti, <5 = shotgun)
shotgun / sg | tnt menge | delay           | geschwindigkeit           | Streuung (1 ist sehr weit - 0.2 ist eine breitenstreu - 0.02 ist eine shotgun)
static / stc | tnt menge | minimales delay | maximale delay abweichung | geschwindigkeit | Streuung

Beispiele:

Name  | Command
----- | -------
Stich | `/pc s 100 0 100`
Lupf  | `/pc stc 45 5 10 5 0.05`
Streu | `/pc sg 50 5 4 0.2`

