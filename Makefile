EMPTY :=
SPACE := $(EMPTY) $(EMPTY)

java_files := $(shell find src -type f -name *.java)
class_files := $(patsubst src/%.java,build/wgc/%.class,$(java_files))

awgcf_java_files := $(shell find awgcf/src -type f -name *.java)
awgcf_class_files := $(patsubst awgcf/src/%.java,build/awgcf/%.class,$(awgcf_java_files))

deps := build/spigot/spigot.jar build/spigot/spigot-api.jar build/spigot/authlib.jar build/spigot/bungeecord-chat.jar build/spigot/gson.jar build/spigot/guava.jar
jdeps := $(subst $(SPACE),:,$(deps))

all: wgc awgcf
wgc: build/WarGearCore.jar
awgcf: build/AdvancedWarGearCoreFight.jar

clean:
	rm -r build/wgc build/awgcf build/WarGearCore.jar build/AdvancedWarGearCoreFight.jar

update:
	$(MAKE) -B build/spigot/server.jar

build/WarGearCore.jar: $(class_files) build/wgc/META-INF/MANIFEST.MF build/wgc/plugin.yml
	cd build/wgc && zip -r9 WarGearCore.jar de/ plugin.yml META-INF
	mv build/wgc/WarGearCore.jar $@

build/AdvancedWarGearCoreFight.jar: $(awgcf_class_files) build/awgcf/META-INF/MANIFEST.MF build/awgcf/plugin.yml
	cd build/awgcf && zip -r9 tmp.jar de/ plugin.yml META-INF
	mv build/awgcf/tmp.jar $@

build/wgc/%.class: src/%.java $(deps)
	javac -d build/wgc/ -s src -encoding UTF-8 -cp "$(jdeps):src" $<

build/awgcf/%.class: awgcf/src/%.java $(deps) build/WarGearCore.jar
	javac -d build/awgcf/ -s awgcf/src -encoding UTF-8 -cp "$(jdeps):build/WarGearCore.jar:awgcf/src" $<

build/spigot/BuildTools.jar:
	mkdir -p build/spigot
	curl -o $@ "https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar"

build/spigot/server.jar: build/spigot/BuildTools.jar
	mkdir -p build/spigot/cache
	cd build/spigot/cache && java -jar ../BuildTools.jar --output-dir .. --nogui --final-name server.jar --rev 1.20.4

build/spigot/spigot.jar: build/spigot/server.jar
	unzip -p $< "META-INF/versions/spigot-*.jar" > $@

build/spigot/spigot-api.jar: build/spigot/server.jar
	unzip -p $< "META-INF/libraries/spigot-api-*.jar" > $@

build/spigot/gson.jar: build/spigot/server.jar
	unzip -p $< "META-INF/libraries/gson-*.jar" > $@

build/spigot/authlib.jar: build/spigot/server.jar
	unzip -p $< "META-INF/libraries/authlib-*.jar" > $@

build/spigot/guava.jar: build/spigot/server.jar
	unzip -p $< "META-INF/libraries/guava-*.jar" > $@

build/spigot/bungeecord-chat.jar: build/spigot/server.jar
	unzip -p $< "META-INF/libraries/bungeecord-chat-*.jar" > $@

build/%/MANIFEST.MF:
	mkdir -p "$$(dirname "$@")"
	echo -e "Manifest-Version: 1.0\nBuilt-By: $$USER\nSealed: false" > $@

build/wgc/plugin.yml: plugin.yml
	mkdir -p build/wgc
	cp -f $< $@

build/awgcf/plugin.yml: awgcf/src/plugin.yml
	mkdir -p build/awgcf
	cp -f $< $@

.PHONY: all wgc awgcf clean update
