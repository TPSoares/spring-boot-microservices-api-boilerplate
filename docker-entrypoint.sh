#!/bin/sh
set -e
exec java $@ -jar /app.jar -Dfile.enconding=UTF-8