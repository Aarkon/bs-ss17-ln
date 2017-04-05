#!/bin/bash 
# Renames every file in the current directory to the filename + a given string.
# Florian Nehmer
# 05.04.2017

# ------------------------------------------------------------
# This function shows the help text for this bash script
usage() { 
echo "
$0 [<text>]
Renames every file in the current directory to the filename + a given string.
OPTIONS: 
   -h: Show this help
"
}

# This funtion is renaming the files in the current directory.
rename() {
    for file in * ; 
    do
        mv $file $file$str
    done
}

# ---------------------- main --------------------------------
# check parameters 
if [ $# -gt 1 ]; then
    usage
    exit 1
fi

case $1 in
    "-h")
        usage
        exit 0
        ;;
    *)
        str=$1
        rename
        exit 0
        ;; 
esac

echo "Unknown Error!"

exit 0

# ---------------------- end ---------------------------------