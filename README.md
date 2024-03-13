# WarGearCore

A MCIV-WarGear Team- and Homeserver Bukkit-Plugin.

## Usage

- How to set up a Server: [German](https://html-preview.github.io/?url=https%3A%2F%2Fgithub.com%2FJan9103%2FWarGearCore%2Fblob%2Fmain%2FDocs%2Fuser_ger.html#setup)
- Documentation: [German](https://html-preview.github.io/?url=https%3A%2F%2Fgithub.com%2FJan9103%2FWarGearCore%2Fblob%2Fmain%2FDocs%2Fuser_ger.html)

### Getting a plugin JAR

#### Official Downloads

(none yet)

#### Building on linux

Depending on your distribution you might have to install the dependencies:
bash, curl, git, gnumake, openjdk17, zip, unzip

Open a terminal and run the following commands in it:

```sh
git clone https://github.com/jan9103/WarGearCore.git
cd WarGearCore
make all
```

Now you can find the jar file at `/home/YOUR_USERNAME/WarGearCore/build/WarGearCore.jar`

#### Building using a OCI-container-engine

(run in the WarGearCore code directory after downloading it)

[podman](https://podman.io/)
```bash
podman run -v .:/wgc --rm ghcr.io/nixos/nix env -C /wgc nix-shell --run "make all"
```

[docker](https://www.docker.com/)
```bash
docker run -v .:/wgc --rm ghcr.io/nixos/nix env -C /wgc nix-shell --run "make all"
```

#### Building on windows

Your options:
1. [use linux](https://en.wikipedia.org/wiki/Windows_Subsystem_for_Linux)  
2. [use linux](https://en.wikipedia.org/wiki/VMware_Workstation_Player)
3. [use linux](https://www.docker.com/)
4. reverse engineer spigot yourself
