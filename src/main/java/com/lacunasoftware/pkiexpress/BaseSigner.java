package com.lacunasoftware.pkiexpress;

import java.io.IOException;
import java.util.List;

class BaseSigner extends PkiExpressOperator {

    BaseSigner(PkiExpressConfig config) {
        super(config);
    }

    BaseSigner() throws IOException {
        this(new PkiExpressConfig());
    }

    protected void verifyAndAddCommonOptions(List<String> args) {

        if (StandardSignaturePolicies.requireTimestamp(signaturePolicy) && timestampAuthority == null) {
            throw new RuntimeException("The provided policy requires a timestamp authority and none was provided");
        }

        // Set signature policy.
        if (signaturePolicy != null) {
            args.add("--policy");
            args.add(signaturePolicy.getValue());

            // This operation evolved after version 1.5 to other signature policies.
            if (signaturePolicy != StandardSignaturePolicies.XmlDSigBasic &&
                    signaturePolicy != StandardSignaturePolicies.NFePadraoNacional) {

                // This operation can only be used on versions greater than 1.5 of the PKI Express.
                versionManager.requireVersion(new Version("1.5"));
            }

            if (signaturePolicy == StandardSignaturePolicies.CodWithSHA1 ||
                    signaturePolicy == StandardSignaturePolicies.CodWithSHA256) {
                // This policy can only be used on version greater than 1.6 of the PKI Express.
                versionManager.requireVersion(new Version("1.6"));
            }
        }


        // Add timestamp authority.
        if (timestampAuthority != null) {
            args.addAll(timestampAuthority.getCmdArguments());

            // This operation can only be used on versions greater than 1.5 of the PKI Express.
            versionManager.requireVersion(new Version("1.5"));
        }

    }
}
