#!/bin/bash

workload=./workload.txt

glowroot_jar_loc="/home/parsa/glowroot-0.13.6-dist/glowroot/glowroot.jar"
commons_io_mdos_jar_loc="./commons-io-1.2-SNAPSHOT.jar"

# execute commons-io-diff for each line in the workload
while IFS= read -r line
do
  echo "Parsing " ${line}
  java -javaagent:${glowroot_jar_loc} -jar ${commons_io_mdos_jar_loc} ${line}
done < ${workload}