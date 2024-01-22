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
  local arg=$2
  local value=$3
  echo "=> replace file: $fileName, key=$arg, value=$value"

  sed -i "s/${arg}/${value}/g" "${fileName}"
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
    docker ps -a
  elif [[ "$1" == "start" ]]; then
    docker start "$2"
  elif [[ "$1" == "stop" ]]; then
    docker stop "$2"
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