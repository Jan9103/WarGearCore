# Jan Java Utils
## Json
A full Json en-/decoder.  
currently unable to handle unicode escape codes.

## SimpJson
**Depends:** StringEscape.  
SimpJson is lighter than Json, bu cant handle arrays/dicts/etc.

## JsonDumpClass
Dump a random class into json format.

## Curl
a very simple web-request sender.  
primarly meant for REST-api requests.

## Browser
a more sofisticated web-request sender.  
capable of passing some ddos-protections, managing cookies, navigating logins, etc.

## JAHmSF (Java Automated HashMap Safe-File)
Auto-save `HashMap<String, ExampleDataClass>` to a single string.  
Only generic elements (strings, bytes, etc and not arrays, objects, etc) will be saved.

## JHmDcSF (Java-HashMap DataClass Safe-File)
a less automated predecessor of JAHmSF, which is capable of saving more complex
classes at the cost of manually defining how to safe and load them.

## CacHeMap
a HashMap, with a automatically managed limited size.

## StringEscape
escape or unescape String with backslashes.  
used for example for JSON.

## cmd (WIP)
a command interpreter inspired by bash and brigadier.