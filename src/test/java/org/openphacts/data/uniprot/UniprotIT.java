
package org.openphacts.data.uniprot;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.junit.Test;

public class UniprotIT {

	Model model = ModelFactory.createDefaultModel();
	Property seeAlso = model.createProperty("http://www.w3.org/2000/01/rdf-schema#seeAlso");

	@Test
	public void wormbaseSeeAlso() throws Exception {
		String filename = "/data/ops-uniprot-linksets/uniprot_wormbase.ttl.gz";
		URL wormbase = getClass().getResource(filename);
		assertNotNull("Can't find " + filename);
		
		try (InputStream in = new GZIPInputStream(wormbase.openStream())) {
			RDFDataMgr.read(model, in, wormbase.toExternalForm(), Lang.TURTLE);
		}
		
		assertTrue(wormbase + " should contain rdfs:seeAlso statements", 
				model.contains((Resource)null, seeAlso, (RDFNode)null));
		
	}
}
