#!/bin/sh
# Lightweight Gradle wrapper bootstrap. Uses the pinned distribution in
# gradle/wrapper/gradle-wrapper.properties without requiring a committed wrapper JAR.
set -eu

APP_HOME=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
GRADLE_VERSION=8.14.3
GRADLE_USER_HOME=${GRADLE_USER_HOME:-"${HOME}/.gradle"}
DIST_ROOT="$GRADLE_USER_HOME/wrapper/dists/gradle-$GRADLE_VERSION-bin/straight-windcharges"
GRADLE_BIN="$DIST_ROOT/gradle-$GRADLE_VERSION/bin/gradle"

if [ ! -x "$GRADLE_BIN" ]; then
    mkdir -p "$DIST_ROOT"
    ZIP="$DIST_ROOT/gradle-$GRADLE_VERSION-bin.zip"
    URL="https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip"
    echo "Downloading Gradle $GRADLE_VERSION..." >&2
    if command -v curl >/dev/null 2>&1; then
        curl -fL --retry 3 "$URL" -o "$ZIP"
    elif command -v wget >/dev/null 2>&1; then
        wget -O "$ZIP" "$URL"
    else
        echo "Gradle is missing; install Gradle or curl/wget to bootstrap the wrapper." >&2
        exit 1
    fi
    if command -v unzip >/dev/null 2>&1; then
        unzip -q -o "$ZIP" -d "$DIST_ROOT"
    else
        echo "The unzip command is required to extract Gradle." >&2
        exit 1
    fi
fi

exec "$GRADLE_BIN" -p "$APP_HOME" "$@"
