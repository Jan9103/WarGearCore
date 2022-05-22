---
title: Testblock
slug: area/tb
---

Pasten: `/tb`

## Testblock modes

`/tb set MODE_NAME/MODE_ID [EINSTELLUNGEN]`

Id | Name       | Beschreibung
-- | ---------- | -----------------------------------
2  | simple     | Ein simpler Testblock im Area Theme
3  | schem      | Schematic basiert; Benötigt einen Namen als Argument (Wenn ein Testblock mit dem Namen existiert wird dieser verwendet, sonst wird der aktuelle inhalt des Testblock bereichs unter dem Namen gespeichert und verwendet).
4  | aimtrainer | Zielübungen für Schwenkstichs, etc - Optionale Argumente: 1. Größe und 2. Menge

## Schilde


Schilde können einmalig gepastet werden oder auch automatisch
bei jedem Pasten mit erneuert werden.

Schilde können aufgeräumt werden mit `/tb clean`.

.        | Befehl
-------- | ------
Einmalig | `/tb shield NAME` oder `/tb s NAME`
Anhängen | `/tb add NAME`
Abhängen | `/tb removeall`

Schilde (die großbuchstaben vom namen reichen):
- ArtiSchild [TIEFE]  - tiefe kann auch negativ seien
- Kiesschild
- wall - eine massieve wand auf den maximalen Ausfahrmaßen
- Massivschild - (Blickrichtung steuert die Seite)
- ArtoX - (Blickrichtung)
- ArtoX+ - (Blickrichtung) Artox mit Blocklager-Simulation

## XRay / Durchleuchten

Tausche Luft und alles andere jeweils aus (z.b. zu glas und glowstone) um den schaden besser zu inspizieren.

`/tb xray` oder `/tb xray MATERIAL MATERIAL`

## Score

`/tb score` gibt Statistiken zu dem Schaden an:

- Zerstörte Blöcke (Prozent und Zahl)
- Abdeckungs Score (wie viel könnte noch leben)
- Wassermenge verbleibend
- Ein Score

Es funktioniert am besten mit einem vollständigem (mode 2) Testblock.

Es ist primär dafür gedacht Schwenkstichs, Lupfs, etc simpel bewerten zu können und
grob zu vergleichen. Allerdings ist das Ergebnis sowohl dank der TNT-Physics als auch
aufgrund der Abdeckungs-Berechnungs-Methode nicht als zu akkurat.

