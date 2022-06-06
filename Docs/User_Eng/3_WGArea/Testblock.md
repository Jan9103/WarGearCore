---
title: TestBlock
slug: area/tb
---

Paste: `/tb`

## TestBlock modes

`/tb set MODE_NAME/MODE_ID [ARGS]`

Id | Name       | Description
-- | ---------- | -----------------------------------
2  | simple     |
3  | schem      | Pastes a Schematic; Requires a name as Argument (if a TB with that name exists it is used - otherwise the current area content is used and saved under the name)
4  | aimtrainer | Optional Arguments: 1. Size 2. Amount

## Shield

Remove: `/tb clean`.

.            | Command
------------ | ------
Paste once   | `/tb shield NAME` or `/tb s NAME`
Attach to TB | `/tb add NAME`
Detach       | `/tb removeall`

Shield       | Short | Args  | Notes
------------ | ----- | ----- | -----
ArtiSchild   | AS    | DEPTH | the depth can be negative
KiesSchild   | K     |       |
wall         | -     |       | A wall surrounding everything
MassivSchild | M     | \*    |
Artox        | AX    | \*    |
Artox+       | AX+   | \*    | Artox with a hole behind it.

\* Your view direction determines on which side its generated.

## XRay

Replace Air with Glowstone (or another block of your choice) and
everything else with glass (or another block of your choice).

`/tb xray` or `/tb xray MATERIAL MATERIAL`

## Score

`/tb score` outputs statistics for the current damage:

- Destroyed blocks (percent and count)
- Coverage score (chance of something having survived)
- Water-count
- A score (all the ones above in a single number)

For a comparable result use TestBlock type 2.
