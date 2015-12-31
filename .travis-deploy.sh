#!/bin/bash

if [ "$TRAVIS_REPO_SLUG" == "chkal/mvc-toolbox" ] &&
    [ "$TRAVIS_BRANCH" == "master" ] &&
    [ "$TRAVIS_PULL_REQUEST" == "false" ] &&
    [ "$TRAVIS_JDK_VERSION" == "oraclejdk8" ]; then

  echo "Starting snapshot deployment..."
  mvn -s .travis-settings.xml -DperformRelease -DskipTests -Dgpg.skip=true deploy
  echo "Snapshots deployed!"

else
  echo "Snapshots are not deployed!"
fi
