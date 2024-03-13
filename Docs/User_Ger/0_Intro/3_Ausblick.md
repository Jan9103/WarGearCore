---
title: Ausblick
slug: future
---

Ich (Jan9103) arbeite nicht besonders Konsistent oder viel an WGC.

Da ich inzwischen Vollzeit arbeite habe ich allgemein auch nicht
mehr so viel Zeit wie in der vergangenheit.

Es werden villeicht noch Dinge von mir kommen (vorallem wenn ich mal
wieder an WarGears interresiert bin), aber erwartet keine Konsistenz
oder großen Features.

Ich hatte noch eine Menge an ideen gehabt und vileicht setze ich mir irgendwann
mal wieder an eine. Hier wären ein paar dieser (übergroßen) Ideen:

---

## Chaträume und Integration

Ein großer und ein Fight-Teamchat sind gut und schön, aber wie wäre es mit
noch mehr und einfach zu nutzender aufteilung?
Und wenn wir schon dabei sind Cross-Platform Chaträumen?

### Cross-Platform

Hierfür wird eine API eingebaut werden und dann via addons (andere Plugins die WGCs
APIs nutzen) verbunden.

Ideen:
- [Discord][]
- [Matrix][]
- [Revolt][]
- [Rocket.Chat][]

---

## Git für WarGears

Mögliche Ergebnisse:
- Rückgängig machen von Fehlern / Experimenten
- Aufteilen der Arbeit ohne sich in die Quere zu kommen oder auf dem gleichem Server zu seien
	- Design und Kanonen / mehrere Kanonen / ..
---

## Decentral WGC

Simplere Idee:
- Geteilte Chat-Channels zwischen WGC (und anderen) Servern haben
- Einstellungen zwischen Servern Synchronisieren

Erweiterte Ideen:
- Bungeecord ähnlich Spieler für Fights verleihen
- Dezentrale Fights
	- Techklau schutz indem jeder server nur sein WarGear kennt
	- Cross-Version (1.15 vs 1.18) Fights
- [Teletype][] (ein WarGear von meheren Servern aus bauen)

### Mögliche Ergebnisse

Eine Entlastung der großen Server:
- Bauserver könnten ausgelagert werden, da es keinene Nachteil mehr hätte

---

## Twitch Integration (Addon)

Fight Mods:
- Viewer steuern WarGear
- Viewer steuern Events (Voting/ Redeems)

Chat integration

---

## Package Manager

Manuell Dateien finden, runterladen, installieren und dann debuggen macht nicht viel Spaß. Das ist allerdings die aktuelle richtung mit immer mehr Extension / Addon / Mod / .. optionen von WGC.  
Wie wäre es wenn man einfach ähnlich zu Linux soetwas im Chat machen kann:

```text
> /packages search worldedit
WorldEdit: Faster building with Commands.
FAWE: a faster, but buggier version of WorldEdit
WorldEditPlus: adds a bunch of shortcuts like //p //floppy //90

> /packages install WorldEditPlus
Installing WorldEditPlus..
WorldEditPlus installed and Enabled.

> /packages update
Updates running..
Downloaded FAWE v12345.
Already up to date: WorldEditPlus.
Updates downloaded - Restarting Updated Plugins..
FAWE restarted.
Updates done.

> /packages update full
Updates running..
Downloaded WGC v12345
Downloaded Spigot 1.15.2 v1832
Restarting Server to install Spigot Updates..
```



[Git]: https://de.wikipedia.org/wiki/Git
[Teletype]: https://teletype.atom.io/
[Rocket.Chat]: https://rocket.chat/
[Matrix]: https://matrix.org/
[Revolt]: https://revolt.chat/
[Discord]: https://discord.com/
