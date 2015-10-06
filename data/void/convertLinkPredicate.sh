
for file in *.ttl
do
	sed 's/rdfs:seeAlso/<http:\/\/semanticscience.org\/resource\/SIO_010043>/g' < $file > $file.tmp
	sed 's/skos:exactMatch/rdfs:seeAlso/g' < $file.tmp > $file
	rm $file.tmp
done

