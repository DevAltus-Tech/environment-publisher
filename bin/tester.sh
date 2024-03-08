#!/bin/bash


# This is a simple bash script to test setting env. variables from java

TARGET_DIR=./target

CP=""
fls=`find $TARGET_DIR -name "*.jar"`
for f in $fls
do
  CP=$CP:$f
done

# set some variables from java
echo "running java to set some variables"
cmd="java -Dlogback.configurationFile=./src/main/resources/logback.xml -cp $CP com.devaltus.clients.envpublisher.EntryPoint -c"
eval `$cmd`



export | grep -i test
echo "running java to get the variables"
command="java -Dlogback.configurationFile=./src/main/resources/logback.xml -cp $CP com.devaltus.clients.envpublisher.EntryPoint -r"
eval $command
