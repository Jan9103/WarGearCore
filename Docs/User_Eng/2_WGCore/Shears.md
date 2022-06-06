---
title: Shears
slug: shears
---

## Bindings

Mouse | Sneak | Result
----- | ----- | ------
Left  | no    | Reload
Left  | yes   | Select prime target
Right | either| Prime

---

## Reload

1. Select a cannon with the [WGE selection](#wge/basics)
1. `/simplereload copy` or `/sr copy`
1. fire
1. `/sr paste` (or via the binding)

---

## Remote Detonator

You can select a target Block and later toggle it from anywhere on the map.

Due to weird Bukkit APIs observers seem to sometimes activate twice.

### Valid target blocks

- Buttons
- Levers
- (Trap-)Doors
- Fence gates
- Noteblock

### Multiple

You can have multiple remote detonators by renaming them (`/rename NAME`).
