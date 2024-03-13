SPIGOT_JAR ?= /media/w/dependencies/spigot-1.15.2.jar
WGC_JAR ?= /media/w/code/WarGearCore/WarGearCore.jar


.PHONY: all build compile jar clean

all: build

build: compile jar clean

compile:
	mkdir -p bin
	rm -r bin/* || true  # we dont want to include compiled versions of already deleted files in our jar
	cd src && javac -d ../bin "de/jan9103/wgc/advancedFight/P.java" -cp ".:$(SPIGOT_JAR):$(WGC_JAR)"
	cp src/plugin.yml bin/

jar:
	cd bin && jar -c -v -e "de.jan9103.wgc.advancedFight.P" -f '../AdvancedWGCFight.jar' ./*
