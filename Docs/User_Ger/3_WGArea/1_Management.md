---
title: Management
slug: area/manage
---

## Vordefinierte Regelwerke

Name | Beschreibung
---- | ------------
t1   | [MyPlayPlanet t1][MPP t1]
t2   | [MyPlayPlanet t2][MPP t2]
t3   | [MyPlayPlanet t3][MPP t3]
wg   | [SteamWar WarGear][SW wg]
mwg  | [SteamWar MiniWarGear][SW mwg]
micro| [Discowueste Micro][micro]

## Erstellen

### Erstellen für ein vordefinirtes Regelwerk

`/area create REGELWERK NAME`

### Erstellen für andere Regelwerke

`/a create X Y Z EXT EXTFRONT EXTRA ENTFERNUNG NAME`

Name      | Erklärung
--------- | ---------
X         | X-Größe (Breite)
Y         | Y-Größe (Höhe)
Z         | Z-Größe (Tiefe)
EXT       | Ausfahrweite
EXTFRONT  | Ausfahrmaße nach vorne
EXTRA     | Extra größe (für sidestabs, backstabs, etc)
ENTFERNUNG| Abstand zwischen den WarGears
NAME      | Name des Bereiches

## (Ent-)Laden

Bereiche können entladen werden.
Das Entladen von vielen Bereichen hilft mit Performance,
allerdings verhalten sich entladene Bereiche wie ein ganz
normaler Abschnitt der Welt.

Entladen:     `/a unload NAME`  
Wieder laden: `/a load NAME`


[MPP t1]: https://myplaypla.net/rules/tier-1
[MPP t2]: https://myplaypla.net/rules/tier-2
[MPP t3]: https://myplaypla.net/rules/tier-3
[SW wg]: https://steamwar.de/spielmodi/wargear-regelwerk
[SW mwg]: https://steamwar.de/spielmodi/miniwargear-regelwerk
[micro]: https://www.youtube.com/watch?v=cCgh2YWWcNk
