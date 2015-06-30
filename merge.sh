#/bin/sh
mkdir -p merged
rm -f merged/*
for void in void/*.ttl ; do
  name=`basename $void`
  data=data/$name.gz
  merged=merged/$name  
  cp $void $merged
  echo >> $merged
  zcat $data >> $merged
  gzip $merged
done
