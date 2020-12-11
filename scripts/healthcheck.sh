#!/bin/bash

service_url=localhost;
retries=0
sleeping_time=1
status_code=0
timeout=5
echo "Checking Healthcheck: http://$service_url/health"

while [ $retries -lt 5 ] && [ $status_code -ne 200 ]; do
    retries=$((retries+1));
    status_code=$(curl -s -o /dev/null -w "%{http_code}" --connect-timeout $timeout http://$service_url/health);
    echo "Healthcheck Status response: $status_code"
    sleeping_time=$((sleeping_time+sleeping_time));
    sleep $sleeping_time
done

if [ $status_code -eq 200 ]; then
    exit 0
else
    exit 1
fi
