package com.lacunasoftware.pkiexpress;


public class VersionManager {
	private Version minVersion = new Version("0.0");


	public void requireVersion(Version candidate) {
		if (candidate.compareTo(this.minVersion) >= 0) {
			this.minVersion = candidate;
		}
	}

	public boolean requireMinVersionFlag() {
		return this.minVersion.compareTo(new Version("1.3")) >= 0;
	}

	public Version getMinVersion() {
		return this.minVersion;
	}
}
