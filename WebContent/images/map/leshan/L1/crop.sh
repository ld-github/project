#!/usr/bin/bash
ORIGIN_FILE=origin.jpg
CONVERT=/usr/bin/convert
IDENTIFY=/usr/bin/identify


TILE_W=200
TILE_H=200

ORIGIN_W=`"$IDENTIFY" -format %[fx:w] $ORIGIN_FILE`
ORIGIN_H=`"$IDENTIFY" -format %[fx:h] $ORIGIN_FILE`

ROW_COUNT=`expr $ORIGIN_H / $TILE_H`
COL_COUNT=`expr $ORIGIN_W / $TILE_W`

if [[ `expr $ORIGIN_H % $TILE_H` -ne 0 ]] ; then
    ROW_COUNT=`expr $ROW_COUNT \+ 1`
fi
if [[ `expr $ORIGIN_W % $TILE_W` -ne 0 ]] ; then
    COL_COUNT=`expr $COL_COUNT \+ 1`
fi

echo $ROW_COUNT x $COL_COUNT

ROW_RANGE=5
ROW_START=0
ROW_END=$ROW_COUNT

if [[ "$1" != "" ]] ; then
    ROW_START=`expr $1 \* $ROW_RANGE`
    ROW_END=`expr $ROW_START \+ $ROW_RANGE - 1`
fi

echo From row $ROW_START to $ROW_END

for (( R=$ROW_START ; R<$ROW_END ; R++ )); do
    for (( C=0 ; C<$COL_COUNT ; C++ )); do
        TNAME=`printf R%03dC%03d $R $C`
        Y=`expr $R \* $TILE_H`
        X=`expr $C \* $TILE_W`
        "$CONVERT" $ORIGIN_FILE -crop ${TILE_W}x${TILE_H}\+${X}\+${Y} ${TNAME}.jpg
    done
done
