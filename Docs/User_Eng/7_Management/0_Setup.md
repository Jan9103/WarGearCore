---
title: Setup
slug: setup
---

Prefix | Target Operating system
------ | -----------
Widows:| Windows 10/11 Desktops
Debian:| Debian based Linux Distros (Ubuntu, Pop, Mint, Sparky, etc)
Linux: | Other Linux Systems
Other: | Everything unlisted

`Texte wie dieser sind jetweder terminalbefehle, datinamen oder texte zum kopieren`
(und du kannst [Kopieren][] statt abzutippen!).  
Bitte das Terminal bis zum ende offen lassen, da die Befehle aufeinander aufbauen!

Wie man ein Terminal öffnet:
[Windows](https://www.wikihow.com/Open-Terminal-in-Windows)
[Linux (Gnome)](https://www.wikihow.com/Open-a-Terminal-Window-in-Ubuntu)

Ein Texteditor für das Linux Terminal: [Nano][] (<f3>: speichern, <f2> schließen, `nano datei.txt`: datei öffnen)

## Minecraft Server Einrichten

### Java installieren

Debian: `sudo apt-get install default-jre`  
[Other][Java Install Guide]

### Bukkit downloaden

Zuerst musst du dich entscheiden welche Serversoftware du nutzen möchtest.

Wenn du WarGears für einen spezifischen Server bauen willst nutze am besten die
gleiche wie der Server.

**Der Server muss für Minecraft Version 15 sein**

Name       | Server      | Anmerkung
---------- | ----------- | ---------
[Spigot][] | [SteamWar][]|
[PaperMc][]| [MPP][]     | PaperMc ist bedeuted performanter als Spigot.

Linux:
1. erstelle dir einene Ordner für den Server: `mkdir minecraft-server`
2. gehe in den Ordner: `cd minecraft-server`
3. Downloade einen Server:
	- Spigot: `curl https://cdn.getbukkit.org/spigot/spigot-1.15.2.jar -o spigot.jar`
	- PaperMc: `curl https://papermc.io/api/v2/projects/paper/versions/1.15.2/builds/393/downloads/paper-1.15.2-393.jar -o paper.jar`
4. Akzeptiere die [Minecraft EULA][]: `echo 'eula=true' > eula.txt`

Windows:
1. Öffne den explorer
1. [Aktiviere Dateiendungen][]
1. Suche dir eine schöne Stelle und erstelle einen Ordner
1. Öffne den Ordner
1. Downloade einen Server und lege die Jar Datei in den Ordner
	- [Spigot][]
	- [PaperMc][]
1. Akzeptiere die [Minecraft EULA][]:
	1. Rechtsclick > Neue Datei > Textdokument
	1. Bennene die Datei `Eula.txt`
	1. Öffne die Datei
	1. Schreibe `eula=true` in die Datei
	1. Speicher die datei (strg & s gleichzeitig drücken)
	1. Schliße den Texteditor

### Eine Server-Start Datei erstellen

Linux:
1. Schreibe ein Script: `echo -e '#!/bin/bash\nRAM=1\njava -Xms${RAM}g -Xmx${RAM}g -DIReallyKnowWhatIAmDoingISwear -jar *.jar nogui' > start.sh`
1. Mache es ausführbar: `chmod +x start.sh`

Windows:
1. Rechtsclick > Neue Datei > Textdokument
1. Bennene die Datei `start.bat`
1. Öffne die Datei
1. Schreibe den Nachfolgenden text ion die Datei: `java -Xms1g -Xmx1g -DIReallyKnowWhatIAmDoingISwear -jar server.jar` (statt `java.jar` den namen der datei)
1. Speicher die datei (strg & s gleichzeitig drücken)
1. Schliße den Texteditor

### Generieren der Bukkit Dateien

1. Starte den Server:
	- Windows: doppelclick auf `start.bat`
	- Linux: `./start.sh`
1. Warte bis der Server fertig ist
1. Schreibe `op USERNAME` um dir für später rechte zu geben
1. Jetzt ist ein guter Zeitpunkt um eine [Whitelist einzurichten][Whitelist] (die Befehle ohne `/` ausführen)
1. Mob spawnen deaktivieren: `gamerule doMobSpawning false`
1. Schreibe `stop` um den server herunterzufahren

### Simple Server einstellungen

#### Bowfights ermöglichen

Die `spigot.yml` bearbeiten und `players` als unterpuket von `entity-tracking-range`
auf mindestenst `150` setzen.

#### Offline Modus

**Hinweis**: Dies wird benötigt um ohne internetverbindung auf einen lokalen server zu gehen,
allerdings sollte ein server mit dieser einstllung normalerweise nicht öffentlich
erreichbar seien, da sich jeder als admin anmelden kann ohne das password zu kennen.

Tausche `online-mode=true` in der `server.properties` durch `online-mode=false` aus.

### Plugins installieren

Du kannst einfach die `.jar` Dateien für 1.15 **denen du vertraust** in dem `plugins` Ordner ablegen.

Beliebte ergänzende Plugins:
- [FAWE][] oder [WE][]

## Bedienung

### Starten

Linux: einfach wieder mit `cd DATEIPFAD` in den ordner gehen und mit `./start.sh` starten

Windows: Den ordner öffnen und `start.bat` doppelclicken

### Beitreten

Homeserver: Mit der korrekten Minecraft-Version der server-ip `localhost` joinen.

Server: Finde die öffentliche ip heraus..

### Stoppen

`stop` in das terminal eingeben



[Spigot]: https://getbukkit.org/download/spigot
[PaperMc]: https://papermc.io/legacy
[SteamWar]: https://steamwar.de/
[MPP]: https://myplaypla.net/
[Java Install Guide]: https://www.java.com/de/download/manual.jsp
[Minecraft EULA]: https://www.minecraft.net/en-us/eula
[Kopieren]: https://praxistipps.chip.de/tastenkombination-kopieren-und-einfuegen_13193
[Aktiviere Dateiendungen]: https://www.giga.de/tipp/windows-10-dateiendungen-anzeigen-so-gehts/
[Nano]: https://wiki.ubuntuusers.de/Nano/
[Whitelist]: https://minecraft.fandom.com/de/wiki/Befehl/whitelist
