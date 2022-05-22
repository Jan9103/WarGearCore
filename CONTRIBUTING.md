# CONTRIBUTING
Feel free to create merge requests, but don't expect me to be fast with QA or accept everything.  
You might want to make sure something is actually wanted by creating a issue or asking first.

## Styleguide:
Java:
- Opening brackets do not deserve a own line
- Tabs are superior to spaces
- UNIX (`\n` and not `\r\n`) linebreaks (check your IDE)

Text (don't worry about it too much):
- Everything has to be available in English
- Roughly use the [Google Styleguide](https://developers.google.com/style) (or rather the Version implemented in `.vale_styles/WarGearCore`)
- Spelling (For some consistency)
	- Use `Stab`, not `Step`
	- CamelCase for WarGear specific words: `MyPlayPlanet`, `WarGear`, `SideStab`, etc
	- If words have no widely accepted translation use the German word: `MassivSchild`, `BreitenStreu`, etc
	- use the short form for `AK`

## Workflow
### Git
1. Get the newest version of the code
1. Create a feature-branch `git checkout -b YOUR_NAME/BUGID-FEATURE_NAME` (example: `jan9103/42-add_wgedit_code_docs`)
1. Write your Code
1. Test your Changes
1. Write a changelog entry
1. Rebase your code to the newest Version `git fetch && git rebase origin/master`
1. Commit and push `git commit -a && git push YOUR_BRANCH_NAME`
1. Create a merge request

## Setting up the environment:
note: jProject is my custom Build, Test, etc script, which i use for this and might upload someday.

### Eclipse
1. import it
1. select a JDK between 8 and 13 in your project properties
1. add Spigot 1.15 (not PaperMC, Glowstone, forge or something) to the dependencies
1. Set the project indent to tabs

### IntelliJ
TODO (i don't know)
- DISABLE THE AUTO TAB -> SPACES CONVERTER

### jProject.sh
adjust the `config.sh`:
- replace the `CLASSPATH` with a path to a `spigot1.15.jar` on your system
- replace the `jdk` with a path to a JDK (between java 8 and 13) directory on your system

## Building a jar:
### with Eclipse:
1. WarGearCore (Project in Package Explorer) > Export > as Jar (not runnable jar)
2. Exclude all extra files except for `plugin.yml`
3. Build

### with IntelliJ
TODO (i don't know)

### with jProject.sh
`jProject b`

