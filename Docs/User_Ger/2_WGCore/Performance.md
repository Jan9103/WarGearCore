---
title: Performance
slug: performance
---

## Performance beobachten

`/ctps`

---

## Performance verbesserung

WGC hat Lagreduzierungs-systeme eingebaut.

### Befehle

Befehl        | Effekt
------------- | ------
`/lag`        | Entferne Mobs\* in deiner Welt
`/lag nether` | Entferne Mobs\* in der Welt namens `Nether`
`/lagall`     | Entferne Mobs\* in allen Welten


\* Mobs beziht sich auf alle Entities, die als nicht wertvoll erachtet werden (Armorstands z.b. sind wichtig, aber items und Zombies nicht)

### Item Manager

Der Item Manager lässt Items schneller verschwinden und lässt nur Items in der nähe von Spielern droppen.

Dies kann in der [Config](#config) deaktiviert werden.

### Notfall Lag Entefernung

Der notfall lag entferner erkennt (manchmal) wenn der Server sich kurz vor dem Absturz befindet und leitet
gegenmaßnahmen ein:
- TNT entfernen
- TNT schaden berechnungen stoppen
- Physics deaktivieren
- Tacer hiden
- Gedropte Items entfernen

