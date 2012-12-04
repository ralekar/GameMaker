#!/bin/bash

#PIDFILE="/u/kspace/www/fluency/student-files/fall2012/a10/team-all/gameserverpid"
PIDFILE="/u/softeng/gameserverpid"
LOGFILE="/u/kspace/www/fluency/student-files/fall2012/a10/team-all/gameServer.log"
if [ -s $PIDFILE ] 
then
  PID=`cat $PIDFILE`
  kill -9 $PID
fi

# port_count will have the total number of process running using the port 8097
port_count=`ps aux | awk '/8097/ {print $2}' | wc -l`;

# Below while loop will keep terminating all the process running on port 8097, until there is only one process i.e awk which
# is being used to find the process running on 8097
while [ $port_count != 1 ]
do
	server_processid=`ps aux --sort=start_time | awk '/8097/ {print $2}' | grep -v awk | head -1`;
	kill -9 $server_processid;
	port_count=`ps aux --sort=start_time | awk '/8097/ {print $2}' | wc -l`;
done

# count will have the total number of cargo:run process running on server at the moment
count=`ps aux | awk '/cargo:run/ {print $2}' | wc -l`;

# Below while loop will keep terminating all cargo:run process currently running on server,
# until there is only one process i.e awk which is being used to find cargo:run process
while [ $count != 1 ]
do
        process_id=`ps aux --sort=lstart | awk '/cargo:run/ {print $2}' | tail -1`;
        kill -9 $process_id;
        count=`ps aux | awk '/cargo:run/ {print $2}' | wc -l`;
done

/u/kspace/local/maven/bin/mvn -f /u/kspace/www/fluency/student-files/fall2012/a10/team-all/server/pom.xml clean install > $LOGFILE 2>&1
/u/kspace/local/maven/bin/mvn -f /u/kspace/www/fluency/student-files/fall2012/a10/team-all/server/pom.xml cargo:run >> $LOGFILE 2>&1 &
pid=$!
echo $pid > $PIDFILE
