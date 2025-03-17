package com.lacunasoftware.pkiexpress;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignatureAlgorithmIdentifier {

	public enum AlgorithmEnum {
		MD5WithRSA,
		SHA1WithRSA,
		SHA256WithRSA,
		SHA384WithRSA,
		SHA512WithRSA,
		SHA256WithECDsa,
		SHA384WithECDsa,
		SHA512WithECDsa
	}

	private AlgorithmEnum algorithm = null;

	@JsonProperty("algorithm")
	public AlgorithmEnum getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(AlgorithmEnum algorithm) {
		this.algorithm = algorithm;
	}
}
