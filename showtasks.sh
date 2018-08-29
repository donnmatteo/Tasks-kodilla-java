#!/usr/bin/env bash

export RUNCRUD_HOME=/Users/Mateusz/Documents/Development/tasks

start_runcrud()
{
    $RUNCRUD_HOME/bin/runcrud.sh start
    end
}

stop_runcrud()
{
    $RUNCRUD_HOME/bin/runcrud.sh stop
}

end()
{
    echo "crud started"
}

fail()
{
    echo "There were errors"
}

if ./gradlew build; then
    open http://localhost:8080/crud/v1/task/getTasks
else
   stop_runcrud
   fail
fi

