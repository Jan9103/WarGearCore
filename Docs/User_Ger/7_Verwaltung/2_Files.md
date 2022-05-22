---
title: Dateien
slug: files
---

WGC erstellt eine menge an Ordnern und Dateien in `plugins/WarGearCore`:

**Bitte bearbeite keiner dieser Dateien während das Plugin aktiv ist!**

## areas

Hier werde areas gespeichert.

Die `.area` Dateien sind im eifache YAML Dateien, allrdings sind sie
nicht zum manuellen bearbeiten gedacht.

Wenn du dateien hier löschst lösche auch `plugins/WarGearCore/cache.yml`.

## backup

in diesem Ordner befinden sich Ordner, deren namen Unix-Timestamps repräsentieren.

Innerhalb dieser Ordner befinden sich schem dateien mit namen von Berichen.

## extensions

Dies war der Ordner für WGC-Extension Ordner/Dateien und ist im Augenblick ungenutzt.

## inventories

Dieser ordner enthält gespicherte Fight-Inventare.

## langs

Dieser ordner hatte früher sprachpakete enthalten und sollte eigentlich nicht mehr auftauchen.

## schem

Hier speichert WGEdit die Nutzergemachten schmatics.

Untenordner:
- P (Public)
- Tb (Testblöcke)
- \[UUID\] (Spielerspezifische)

Diese unterordner enthalten unterordner entsprechend der schem kategorisierung.

## servericons

Hier kannst du 64x64px pngs ablegen, welche wenn in der [Config][] `serverListPlus` aktiv ist
als servericons benutzt werden.

(Dateinamen sind egal)

## todo

Todolisten

## user

yaml dateien mit nutzereinstellungen.

hier kann root aktiviert werden.

## ads.txt

bekutzt von dem ads-feature

## cache.yml

speichert welche Bereiche vor dem herunterfahren geladen waren, etc.

## wgc.cfg

siehe [Config][]


[Config]: #config
