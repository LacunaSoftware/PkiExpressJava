package com.lacunasoftware.pkiexpress;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lacunasoftware.pkiexpress.SignatureAlgorithm.AlgorithmEnum;

public class SignatureAlgorithm {

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
	private AlgorithmEnum getEAlgorithmEnum() {
		return algorithm;
	}

	public String getName() {
		return algorithm.name();
	}

	public void setAlgorithm(AlgorithmEnum algorithm) {
		this.algorithm = algorithm;
	}
}
