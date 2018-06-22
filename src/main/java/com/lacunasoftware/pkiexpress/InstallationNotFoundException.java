package com.lacunasoftware.pkiexpress;

import java.io.IOException;

public class InstallationNotFoundException extends IOException {

    public InstallationNotFoundException(String message) {
        this(message, null);
    }

    public InstallationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
