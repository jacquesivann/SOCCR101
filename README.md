SOCCR101
========

# TODO SDK
{PROJECT FOLDER}/local.properties
Change th sdk.dir to the dir where your sdk is located

# APK
apk location: {PROJECT FOLDER}/app/build/outputs/apk

# SQlite DB
DataBase Browser for SQLite: http://sqlitebrowser.org/
Location: {PROJECT FOLDER}/app/assets/data
Reminder: When changing of filename of database, change the database name based on its filename @ code DatabaseHandler.java#L14
If database file not found it will throw an exception IOException, resulting to error of the app.
@ Org table, for column 'LOGO' is not used. leave as is, or remove. doesn't matter

# Organization logo
location: {PROJECT FOLDER}/app/assets/logo
size: 144x144 px, to prevent OutOfMemory Exception
format: PNG,
filename should be the same with the record id in the database
If logo is not available the app will use the {PROJECT FOLDER}/app/assets/missing.png

# Gradle
Choosing an changing of grable check {PROJECT FOLDER}/gradle/wrapper/gradle-wrapper.properties
Change the distributionUrl to the gradle distributionUrl you want to use in building & etc of the app
Change the gradle-wrapper.jar to the desired gradle which location in the lib forder of the gradle distribution
