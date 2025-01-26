port=8012

pid=$(netstat -nlp | grep :$port | awk '{print $7}' | awk -F"/" '{ print $1 }');


if [  -n  "$pid"  ];  then
    kill  -9  $pid;
fi

port1=8083

pid1=$(netstat -nlp | grep :$port1 | awk '{print $7}' | awk -F"/" '{ print $1 }');


if [  -n  "$pid1"  ];  then
    kill  -9  $pid1;
fi

port2=8100

pid2=$(netstat -nlp | grep :$port21 | awk '{print $7}' | awk -F"/" '{ print $1 }');


if [  -n  "$pid2"  ];  then
    kill  -9  $pid2;
fi
nohup java -jar /note/workspace/lp/bizService/target/bizservice-1-exec.jar &
nohup java -jar /note/workspace/lp/processService/target/processService-1.jar &
