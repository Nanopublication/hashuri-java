package ch.tkuhn.hashuri.rdf;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;

import org.openrdf.model.BNode;
import org.openrdf.model.Literal;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;

import ch.tkuhn.hashuri.HashUriUtils;

public class RdfHasher {

	private static boolean DEBUG = false;

	private String hash = null;

	public RdfHasher() {
	}

	public RdfHasher(String hash) {
		this.hash = hash;
	}

	public String makeHash(List<Statement> statements) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException ex) {}
		Collections.sort(statements, new StatementComparator(hash));
		if (DEBUG) System.err.println("----------");
		for (Statement st : statements) {
			if (DEBUG) System.err.print(valueToString(st.getContext()));
			md.update(valueToString(st.getContext()).getBytes());
			if (DEBUG) System.err.print(valueToString(st.getSubject()));
			md.update(valueToString(st.getSubject()).getBytes());
			if (DEBUG) System.err.print(valueToString(st.getPredicate()));
			md.update(valueToString(st.getPredicate()).getBytes());
			if (DEBUG) System.err.print(valueToString(st.getObject()));
			md.update(valueToString(st.getObject()).getBytes());
		}
		if (DEBUG) System.err.println("----------");
		return RdfModule.ALGORITHM_ID + HashUriUtils.getBase64(md.digest());
	}

	private String valueToString(Value v) {
		if (v instanceof BNode) {
			throw new RuntimeException("Unexpected blank node encountered");
		} else if (v instanceof URI) {
			return RdfUtils.normalize((URI) v, hash) + "\n";
		} else if (v instanceof Literal) {
			Literal l = (Literal) v;
			if (l.getDatatype() != null) {
				return "^" + l.getDatatype().stringValue() + " " + escapeString(l.stringValue()) + "\n";
			} else if (l.getLanguage() != null) {
				return "@" + l.getLanguage() + " " + escapeString(l.stringValue()) + "\n";
			} else {
				return "#" + escapeString(l.stringValue()) + "\n";
			}
		} else if (v == null) {
			return "\n";
		} else {
			throw new RuntimeException("Unknown element");
		}
	}

	private static final String escapeString(String s) {
		return s.replaceAll("\\\\", "\\\\\\\\").replaceAll("\\\n", "\\\\n");
	}

}
