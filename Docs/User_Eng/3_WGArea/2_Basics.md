---
title: Basics
slug: area/basics
---

command: `/area` or `/a`

## TNT damage control

You can toggle TNT damage for an area with `/a tnt`.

With `/a protect` you can toggle an additional protection for
the building side of the area, which informs you whenever it
prevented damage.

`/worldtnt` toggles TNT outside of areas.

## Freeze

You can pause physics (freeze) an area with `/a freeze`

`/worldfreeze` toggles TNT outside of the areas.

## Creation / Repair of an Arena

Link: [How to create a Area](#area/manage)

Command    | creates
---------- | -------
`/a floor` | Generates a floor.
`/a frame` | Creates a frame to mark the max size on the building side.
`/a reset` | Clean the space and generate a new frame, etc (creates a [Backup](#area/backup))
`/tb`      | Generates a [TestBlock](#area/tb)
`/a clean` | removes blocks outside of the building and extension space

## Themes

You can adjust the design of an area with themes.

List Themes: `/a theme`.

Change Theme: `/a theme NAME` (and regenerate the floor, etc to view it).

Feel free to suggest themes in the [Discord][].

[Discord]: https://discord.gg/ddB2MDK
