#!/usr/bin/bash

declare -A search_package_installed=(
  [arch]="pacman -Q"
  [manjaro]="pacman -Q"
  [ubuntu]="apt list --installed"
  [centos]="yum list --installed"
)

help() {
    echo "Usage: option [params...]"
}

replace() {
  local fileName=$1
  local key=$2
  local value=$3
  echo "=> replace file: $fileName, key=$key, value=$value"

  sed -i "s/${key}/${value}/g" "${fileName}"
}

open() {
    if [[ "$OSTYPE" == "linux-gnu"* ]]; then # Linux
      package_bin=$(which "$1")
      if [[ $package_bin != '' ]]; then
        $package_bin
        exit
      fi

      distro_name="$(cat '/etc/os-release'|grep -E '^ID='|awk -F'=' '{print $2}')"
      package=$(${search_package_installed[$distro_name]}|grep "$1" | awk -F' ' '{print $1}')
      if [[ $package != '' ]]; then
        $package
        exit
      fi

      echo "=> Cannot open package: $1"

    elif [[ "$OSTYPE" == "darwin"* ]]; then # Mac OS X
            echo "OS not supported"
    else
            echo "OS not supported"
    fi
}

container() {
  if [[ "$1" == "list" ]]; then
    docker ps -a --format '{{.Names}}'
  elif [[ "$1" == "start" ]]; then
    docker start "$2"
  elif [[ "$1" == "stop" ]]; then
    docker stop "$2"
  elif [[ "$1" == "status" ]]; then
    docker container inspect -f '{{.State.Status}}' "$2"
  elif [ "$1" == "search" ]; then
    docker ps -a --format '{{.Names}}' | grep "$2"
  else
    echo "command '$1' not support!"
  fi
}

action_name=$1
case $action_name in
replace)
  replace "$2" "$3" "$4"
  ;;
open)
  open "$2"
  ;;
container)
  container "$2" "$3"
  ;;
*)
help
exit 1
;;
esac