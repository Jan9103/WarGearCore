---
title: WGE Diff
slug: wge/diff
---

[Mask][] the unchanged things between 2 Clipboards.
It is basically a [File Diff][] in 3 dimensions.

Syntax: `/(diff`

Creates a diff between your Clipboard(+) and a [Schem][](-)

This is part of WGGit (WIP)

---

## Example use-cases

### Working at one project with multiple people at once

1. Create duplicates of your current State (1 more than needed)
1. Work on the duplicates (maybe 1x inside Design, 1x outside Design and 2x Cannons)
1. Duplicate the unchanged (lets call the new one bob)
1. Starting with the least important create a diff between a changed one and the original and paste it over bob.

---

## See also

- [Mask][]: Exclude blocks from a paste


[Mask]: #wge/clipboard
[Schem]: #wge/schem
[File Diff]: https://linuxhandbook.com/diff-command/
