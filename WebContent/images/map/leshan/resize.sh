#!/usr/bin/bash

L5_WIDTH=10000
L1_WIDTH=1366
MAX_LEVEL=5
ORIGIN_FILE=origin.jpg
CONVERT=/usr/bin/convert
CROP_SH=crop.sh

for (( L=1 ; L<=$MAX_LEVEL; L++ )) ; do
    #WIDTH=`expr $L5_WIDTH - \( $L5_WIDTH - $L1_WIDTH \) \* \( $MAX_LEVEL - $L \) / \( $MAX_LEVEL - 1 \)`
    WIDTH=`bc <<< $L5_WIDTH/2^\($MAX_LEVEL-$L\)`
    mkdir -p L${L} 2>&1 > /dev/null
    LFILE=L${L}/origin.jpg
    if [[ $L -eq $MAX_LEVEL ]]; then
        cp $ORIGIN_FILE $LFILE
    else
        "$CONVERT" $ORIGIN_FILE -resize ${WIDTH}x${WIDTH}\> $LFILE
    fi

    cp $CROP_SH L${L}/
done

