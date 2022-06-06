---
title: WGE Resize
slug: wge/resize
---

Resizes building similar to how [GIMP][], [Krita][] and other image editors rescale images with a focus on WarGears (it contains optimisations for WGs such as seeing EndStone as worthless).

---

## Example use-cases

### Creating remakes of something for a different ruleset

It primarily helps with the design and scale on the inside.

It can serve as a template for scale of parts in all cases, but sometimes produces output, which requires almost no refinement.

### Upscale a planning model

If you build a WarGear according to the [Clay method][] you can upscale the model for easier orientation.

---

## Syntax

`/(resize X Y Z`  
`/(resize (WG/MWG/T1/T2/T3)`

(applies to your clipboard)

---

## Tricks and tips

- If you want more detail on a side try to expand it into that direction before re-copying and re-resizing
- It one half of the result looks good you might be able to flip it.
- The [Fix Command][] might be helpful for fixing fence connections, etc


[Fix Command]: #wge/fix
[GIMP]: https://www.gimp.org/
[Krita]: https://krita.org/en/
[Clay method]: https://www.youtube.com/watch?v=Yj2YEs7ZrpY
