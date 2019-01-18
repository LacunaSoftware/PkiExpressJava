package com.lacunasoftware.pkiexpress;


public class SignatureAlgorithmIdentifier {
	public enum AlgorithmEnum {
		MD5WithRSA, SHA1WithRSA, SHA256WithRSA, SHA384WithRSA, SHA512WithRSA,
	}


	private AlgorithmEnum algorithm = null;


	public AlgorithmEnum getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(AlgorithmEnum algorithm) {
		this.algorithm = algorithm;
	}
}
