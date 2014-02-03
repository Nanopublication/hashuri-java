package ch.tkuhn.hashuri.rdf;

import java.util.List;

import ch.tkuhn.nanopub.Nanopub;
import ch.tkuhn.nanopub.NanopubUtils;
import org.openrdf.model.Statement;

import ch.tkuhn.hashuri.HashUriUtils;

/**
 * @author Tobias Kuhn
 * @author Rajaram
 * @author Mark Thompson
 * @author Eelke van der Horst
 */

public class CheckNanopub {
	
	public static boolean isValid(Nanopub nanopub) {
		String hash = HashUriUtils.getHashUriDataPart(nanopub.getUri().toString());
		if (hash == null) return false;
		List<Statement> statements = NanopubUtils.getStatements(nanopub);
		statements = RdfPreprocessor.run(statements, hash);
		String h = RdfHasher.makeHash(statements);
		return h.equals(hash);
	}

}
