package ch.tkuhn.hashuri.file;

import ch.tkuhn.hashuri.HashUriModule;
import ch.tkuhn.hashuri.HashUriResource;

public class FileModule implements HashUriModule {

	public static final String ALGORITHM_ID = "FA";

	@Override
	public String getAlgorithmID() {
		return ALGORITHM_ID;
	}

	@Override
	public boolean hasCorrectHash(HashUriResource r) throws Exception {
		FileHasher hasher = new FileHasher();
		String h = hasher.makeHash(r.getInputStream());
		return r.getHash().equals(h);
	}

}
