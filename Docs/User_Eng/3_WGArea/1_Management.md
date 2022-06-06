---
title: Management
slug: area/manage
---

## Predefined Rule sets

Name | Description
---- | ------------
t1   | [MyPlayPlanet t1][MPP t1]
t2   | [MyPlayPlanet t2][MPP t2]
t3   | [MyPlayPlanet t3][MPP t3]
wg   | [SteamWar WarGear][SW wg]
mwg  | [SteamWar MiniWarGear][SW mwg]
micro| [Discowueste Micro][micro]

## Creation

### Creation with a predefined rule set

`/area create REGELWERK NAME`

### Creation with a custom rule set

`/a create X Y Z EXT EXTFRONT EXTRA DISTANCE NAME`

Name     | Description
-------- | -----------
X        | X-Size (width)
Y        | Y-Size (hight)
Z        | Z-Size (depth)
EXT      | Expansion distance
EXTFRONT | Expansion distance at the front
EXTRA    | Additional space (for projectiles to pass)
DISTANCE | Distance in between
NAME     |

## (Un-)load

You can unload areas in order to save resources.

`/a unload NAME`

`/a load NAME` or `/a list`

[MPP t1]: https://myplaypla.net/rules/tier-1
[MPP t2]: https://myplaypla.net/rules/tier-2
[MPP t3]: https://myplaypla.net/rules/tier-3
[SW wg]: https://steamwar.de/spielmodi/wargear-regelwerk
[SW mwg]: https://steamwar.de/spielmodi/miniwargear-regelwerk
[micro]: https://www.youtube.com/watch?v=cCgh2YWWcNk
