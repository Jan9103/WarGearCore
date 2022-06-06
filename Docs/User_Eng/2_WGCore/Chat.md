---
title: Chat
slug: chat
---

WGC contains a chat system, which adds markdown, themes, etc.

## Change the look

`/settings`

If you play with a 1.16+ Minecraft client you can enable `RGB json`.

Feel free to suggest new ones in the Discord or create a pull request.

Inspiration / Credits for the chat themes:

Name           | Credit
-------------- | ------
Vanilla        | Bukkit
VaporWarfare   | SteamWar
YourGamingPlanet|MyPlayPlanet
Pastel         |
Yeti           |
XAMPP          | XAMPP Server Console
WTUtil         | WarTechUtil Plugin (WarTech Teamserver)
PHCore         | PhantomCore Plugin (Phantom Teamserver)
Interstellar   | AmirCore Plugin (Interstellar Teamserver)
Old WGC        |

---

## Markdown

### Bold

`normal **bold** normal`

### Multi line

`\n` Starts a new line.

### Links

**Option 1:** Just write the link

**Option 2:**

`[Wikipedia](https://wikipedia.org)` renders as `Wikipedia` and links to `https://wikipedia.org`

`Watch [WarTech's tutorial](https://www.youtube.com/watch?v=dQw4w9WgXcQ) an` is possible as well.

You can view where the link leads by hovering over it.

**Option 3:** `r/AdmiralsMemeKueche` and other subreddit names are clickable.

### Ping

`@Jan9103` pings `Jan9103`:
- Minecraft plays a sound (`/settings` allows volume adjustments)
- the text gets highlighted

### Suggest commands

Messages starting with `./` get recognized as suggestion:
- Players can click it to copy it into their input field
- Hovering over it reveals a command-help (if Bukkit works)

### Emojis

it you prefer sending ASCII emojis you can disable it in `/settings`

Input | Result
----- | ------
`o^`  | `♂`
`o+`  | `♀`
`ou`  | `☿`
`:(`  | `☹`
`:)`  | `☺`
`<3`  | `❤`
`x`   | `✖`
`TM`  | `™`

### Images

Syntax: `%!LINK`

example: `%!https://static.wikia.nocookie.net/minecraft_gamepedia/images/d/d2/Pufferfish_Artwork.png`

you can disable it in `/settings`

---

## Private Messages

`/tell` works as usual.

`/r` can is adjustable in `/settings`: You can respond to either the one you last messaged or the one you last received.

---

## Join Panel

The text-wall in your chat upon join containing info like online players.

You can change it in `/settings`

