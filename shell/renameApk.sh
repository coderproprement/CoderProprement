#!/usr/bin/env bash
branch=$(git symbolic-ref --short HEAD)
newName="$branch.apk"
newName=`echo $newName | sed 's/\//_/g'`
filePath="../app/build/outputs/apk/release"
apkDefaultName="app-release-unsigned.apk"
cd $filePath
if [ -f "$apkDefaultName" ]
then
	mv $apkDefaultName $newName
else
	echo "Debug apk not found. Aborting moving process."
fi
