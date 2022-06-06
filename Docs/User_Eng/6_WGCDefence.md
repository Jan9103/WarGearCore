---
title: WGCDefence
slug: defence
---

WGC contains a small defence system against invaders, impersonation, etc.

---

## AuthMe

Asks for a Password on login to prevent identity fraud on cracked/ offline servers.

It has multiple modes of operation:
- off
- once per IP
- every login

---

## Discovery Tracker

You can enable it in the [config][].

Sends a message to the configured discord whenever a new IP pings the server (adds it to the serverlist).

---

## Root permission

You can give a player the root permission by editing their [config][] file while they're offline.

What it does:
- prevent kicks (force-kick still works)
- permit the use of special commands

---

## Force-kick

`/wgc crash NAME` crashes Minecraft

---

## List IPs used to login to your account

`/wgc defence ips`


[config]: #config
