package com.lacunasoftware.pkiexpress;

import java.util.ArrayList;
import java.util.List;

public class TimestampAuthority {

    private String url;
    private String token;
    private String sslThumbprint;
    private String basicAuth;
    private TsaAuthenticationType authType;

    TimestampAuthority(String url) {
        this.url = url;
    }

    public void setOAuthTokenAuthentication(String token) {
        this.token = token;
        this.authType = TsaAuthenticationType.OauthToken;
    }

    public void setBasicAuthentication(String username, String password) {
        this.basicAuth = String.format("%s:%s", username, password);
        this.authType = TsaAuthenticationType.BasicAuth;
    }

    public void setSSLThumbprint(String sslThumbprint) {
        this.sslThumbprint = sslThumbprint;
        this.authType = TsaAuthenticationType.SSL;
    }

    public String getUrl() {
        return url;
    }

    public String getToken() {
        return token;
    }

    public String getSslThumbprint() {
        return sslThumbprint;
    }

    public String getBasicAuth() {
        return basicAuth;
    }

    public TsaAuthenticationType getAuthType() {
        return authType;
    }

    List<String> getCmdArguments() {

        List<String> args = new ArrayList<String>();
        args.add("--tsa-url");
        args.add(url);

        switch (authType) {
            case BasicAuth:
                args.add("--tsa-basic-auth");
                args.add(basicAuth);
                break;
            case SSL:
                args.add("--tsa-ssl-thumbprint");
                args.add(sslThumbprint);
                break;
            case OauthToken:
                args.add("--tsa-token");
                args.add(token);
                break;
            default:
                throw new RuntimeException("Unknown authentication type of the timestamp authority");
        }

        return args;
    }
}
