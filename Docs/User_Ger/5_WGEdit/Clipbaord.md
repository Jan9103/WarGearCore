---
title: WGE Clipboard
slug: wge/clipboard
---

`/(cb` oder `/(clipboard`

Befehl | Beschreibung
------ | ------------
size   | Größe des Clipboards (debug)
drain  | Entferne Wasser
os0    | Entferne das Paste Offset (debug)
unload | Uncache das Clipboard (debug)
d2     | (debug)

## Maskieren
Clipbaords können Maskiert werden um bestimmte Dinge nicht mitzupasten.

Befehl               | Effekt
-------------------- | -------
`/(cb mask stone`    | Tauscht stein durch Maske aus.
`/(cb invmask stone` | Tauscht alles ausser Stein durch Maske aus.
`/(cb unmask stone`  | Tauscht Maske durch Stein aus.

