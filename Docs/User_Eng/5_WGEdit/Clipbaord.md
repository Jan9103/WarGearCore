---
title: WGE Clipboard
slug: wge/clipboard
---

## Command

`/(cb` or `/(clipboard`

Sub-Command | Description
----------- | ------------
size        | Debug:
drain       | Remove Water
os0         | Debug: Remove the paste offset
unload      | Debug: Unload the cache Clipboard
d2          | Debug:

---

## Masking

Masked blocks in a clipboard aren't pasted -> existing blocks in the world wont change.

Command              | Result
-------------------- | -------
`/(cb mask stone`    | masks all stone blocks.
`/(cb invmask stone` | masks everything except stone.
`/(cb unmask stone`  | replaces all masked things with stone.

---

## See also

- [Diff](#wge/diff): Mask the unchanged things between 2 Clipboards
- [Resize](#wge/resize): Resize the contents of a clipboard
- [Schem](#wge/schem): save clipboards
