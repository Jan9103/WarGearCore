# Java Project Config File
name='WarGearCore'
AUTHOR='Jan9103'
FORMAT=eclipse

# classpath should contain a colon(:) seperated of dependencies
# (not the project itself)
#  example: "/home/user/Downloads/spigot1.15.2.jar:/home/usr/java/Project1/src"
CLASSPATH="/media/w/dependencies/spigot-1.15.2.jar"

main='de.jan9103.wargearcore.WGC'

# specific JDK file-path (has to contain bin/javac, bin/jar and bin/java)
#jdk='/usr/lib/jvm/java-11-openjdk-amd64'

# exlude files (regex)
#exclude='
#	de.jan9103.Test
#	de.jan9103.java.utils.json
#'

AUTO_LINT=true

#### docs ####
doc_paths="de.jan9103.wargearcore"
doc_public_only=true

#### always enable arguments ####
#clean=true
#forcerecompile=true

#### Hooks ####
hook_post_clean() {
	true
}

hook_pre_jar() {
	true
}

hook_post_jar() {
	true
}
