#!/bin/bash

echo "=============================="
echo " Running OpenCart Automation "
echo "=============================="

cd "$(dirname "$0")"

mvn clean test

echo "=============================="
echo " Execution Finished "
echo "=============================="

read -p "Press ENTER to close..."



