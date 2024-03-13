---
title: WGCDefence
slug: defence
---

WGC hat schutzsysteme / verteidigungs-systeme für Teamserver eingebaut.

## AuthMe

Fragt beim Einloggen nach einem eingerichteten Password um Identitätsfälschung auf
cracked / offline servern zu erschweren.

Dieses AuthMe hat mehrere Operations-modi:
- off
- once per ip
- every login

## Discovery Tracker

Kann in der [Config](#config) aktiviert werden.

Sendet wenn eine neue IP den Server in die Serverliste aufnimmt eine Nachricht in den
eingerichteten Discord Channel.

## Root permission

Spieler können die `root` permission über [config Dateien](#files) erhalten und sind somit geschützt
gegenüber kicks, etc.

## Liste deine IPs auf

Dies kann gut sein um festzustellen ob jemand anderes sich als dich ausgegeben hat.

`/wgc defence ips` (die liste ist nicht zwingen vollständig!)

