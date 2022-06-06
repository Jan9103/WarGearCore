---
title: Config
slug: config
---

**File**: `plugins/WarGearCore/wgc.cfg`

Its written in the [YAML][] Format.

## ads

Wenn diese einstllung aktiviert ist wird beim hochfahren die `plugins/WarGearCore/ads.txt` Datei
eingelesen und regelmäßig eine ihrer Zeilen in den Chat geschickt.

## advancedScoreboard

Das normale Scoreboard ist das Ergebniss einer Community-Umfrage, allerdings bevorzuge ich
es mehr infos zu haben, was hiermit ermöglicht wird.

## autoBugReport

Wenn ein Problem in dem Plugin auftritt können eine Menge an debuginformationen
gesammelt werden, welche helfen das Problem zu beheben.

Diese einstellung versucht automatisch fehler zu erkennen, daten zu sammeln und
sendet anschlißend eine zensierte version dieser Daten an den entwickler.

Auch wenn es zensiert ist ist diese einstellung standardmäßig aus, da datensammlung
nie opt-out seien sollte. Wenn dir das Projekt gefällt und du helfen willst es zu
verbessern aktiviere es gerne.

## betterTntKillTracker

Ein Experimentelles System, welches versucht redstone-kanonen verursachte
Kills nachzuvollziehen.

Da dies eine Menge an extra resourcen benötigt (für nedes redstones speichern
wer es aktiviert hat, etc) und noch nicht so ganz ausgereift ist empfiehlt sich
dieses feature nur wenn ihr eine Überflüssige ressourcen habt.

## command.wgcbug.activeScan

Ein experimentelles Feature, welches das Ergebniss von einem Funktionstest des
gesammten Plugins automatisch an Bug reports anhängt.

## customchat

Dies (de-)aktiviert das Custom-Chat Feature.

## customtab

(De-)aktiviere die Custom tabbar.

Vorallem da um kollisionen mit anderen Plugins zu vermeiden.

## decentral

Macht im augenblick noch garnichts.

Wurde bislang nur zum testen des decentral-WGC systems genutzt und
wird vermutlich auch wenn es fertig wird dafür genutzt, bis dahin
solltet ihr allerdings die einstellungen einfach so (aus) lassen.

## defence

(De-)aktivieren von WGCDefence Features.

## discord.wh

Dies ist eine Liste von [Discord webhooks][]

Name  | Funktion
----- | --------
notes | Wird für den `/dcn` Befehl genutzt
log   | Log nachrichten des Discord-Log features landen hier

## discoverytracker

Dies erlaubt WGCDefence frühzeitig zu warnen wenn jemand unbekanntes (bzw eine unbekannte ip)
den Server in ihre Serverliste aufnimmt.

Benötigt `serverListPlus: true`

## itemDropManager

(De-)aktiviert den Item-Drop manager.

Der itemDropManager verringert die serverauslastung durch die verminderung von Drops:
- items droppen nur wenn ein spiler nahe dran ist
- items despawnen schneller

## join.flag

Lege den Text für die Join-Flagge fest.  
Bespiel: `flag: WT`

## msg

Tausche namen/ texte aus:

Name             | Beschreibung
---------------- | ------------
prefix.1         | Erster teil von Plugin-Modul namen (default: WG)
prefix.2.core    | Zweiter teil vom WGCore subplugin namen
prefix.2.area    | .
prefix.2.defence | .
prefix.2.fight   | .
prefix.2.edit    | .
prefix.2.script  | .
area.sb.bu.status| Text im scoreboard für den Backup status (`'§6Auto-Bu: '`)
area.sb.bu.last  | .. Backup zeit
area.sb.freeze   | .. Freeze status
area.sb.off      | Text für off
area.sb.on       | .. on
area.sb.prefix.name | Area Namensprefix (gut für colorcodes)
area.sb.protection  | text für area-protect status
area.sb.tnt         | .. tnt schaden status
area.sb.trace.status| .. trace status
area.sb.waterremover| .. waterrmover status

## noDefaultScoreboard

Deatktiviert das Scoreboard ausserhalb von Bereichen.

Empfehlenswert wenn Minigame-Plugins aktiv sind.

## server.debug

Dies ist für Developer und ändert error-handling, permissions, etc.

Wenn du am Code arbeitest schalte es gerne an, aber sonst lass es
einfach aus.

## server.name

Dies ist der Name deines Servers.  
Dies wird für Join-Nachrichten, Logs, Scoreboards und ähnliches benutzt.

## serverlistplus

Diese Feature ändert das Aussehen des Servers in einer Serverliste:
- Das Bild ist ein zufälliges 64x64 png aus dem `plugins/WarGearCore/servericons` Ordner
- WGC-Defence kann warnen wenn neue Computer (ips) den Server in ihre Serverliste aufnehmen
  (discoverytracker option)

## tracer.noDataLossOnModeswitch

Im normalfall speichert der tracer nicht alle daten, sondern nur was für den
aktuellen mode benötigt wird. Somit verliert man infos wenn man den mode
nachträglich switcht.

Mit dieser Einstellung werden die Daten nicht reduziert und es ist möglich
nachträglich den detailgrad zu erhöhen.


[YAML]: https://de.wikipedia.org/wiki/YAML
[Discord webhooks]: https://support.discord.com/hc/de/articles/228383668-Einleitung-in-Webhooks
