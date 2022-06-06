---
title: Performance
slug: performance
---

## Performance monitoring

`/ctps`

---

## TPS Limiter

(never really finished and has some odd behaviors)

`/settps (tps)`

---

## Performance improvements

### Commands

Command       | Result
------------- | ------
`/lag`        | Remove Mobs\* in your current world
`/lag nether` | Remove Mobs\* in the World called `Nether`
`/lagall`     | Remove Mobs\* in all Worlds


\* Mobs means all "useless" mobs (zombies and items are examples for useless ones and armor-stands are an example for important ones)

### Item Manager

- Items only drop if a player is close by
- Items despawn faster

You can disable it in the [config](#config)

### Emergency lag removal

If the TPS is way below what the target be this attempts to save the server:
- delete TNT and items
- deactivate [TNT and Physics](#area/basics)
- hide [tracer](#tracer)

