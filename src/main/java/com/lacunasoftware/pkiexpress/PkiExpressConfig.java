package com.lacunasoftware.pkiexpress;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class used to config the library for the operations with PKI Express.
 */
public class PkiExpressConfig {

    private Path pkiExpressHome;
    private Path tempFolder;
    private Path transferDataFolder;

    /**
     * Creates a new instance.
     *
     * @throws IOException if the provided license path is invalid or the library failed to create a temporary folder.
     */
    public PkiExpressConfig() throws IOException {
        this((Path)null);
    }

    /**
     * Creates a new instance using the given PKI Express executable home.
     *
     * @param pkiExpressHome The path to the folder that contains the PKI Express executable.
     * @throws IOException if the provided license path is invalid or the library failed to create a temporary folder.
     */
    public PkiExpressConfig(Path pkiExpressHome) throws IOException {
        this(pkiExpressHome, null);
    }

    public PkiExpressConfig(String pkiExpressHome) throws IOException {
        this(
            pkiExpressHome != null ? Paths.get(pkiExpressHome) : null
        );
    }

    /**
     * Creates a new instance using the given PKI Express executable home and temporary folder.
     *
     * @param pkiExpressHome The path to the folder that contains the PKI Express executable.
     * @param tempFolder The path to the custom folder that will store the temporary files. If this is not set, the
     *                   library will try to create a standard temporary folder.
     * @throws IOException if the provided license path is invalid or the library failed to create a temporary folder.
     */
    public PkiExpressConfig(Path pkiExpressHome, Path tempFolder) throws IOException {
        this(pkiExpressHome, tempFolder, null);
    }

    public PkiExpressConfig(String pkiExpressHome, String tempFolder) throws IOException {
        this(
            pkiExpressHome != null ? Paths.get(pkiExpressHome) : null,
            tempFolder != null ? Paths.get(tempFolder) : null
        );
    }

    /**
     * Creates a new instance using the given PKI Express executable home, temporary folder, transfer files folder.
     *
     * @param pkiExpressHome The path to the folder that contains the PKI Express executable.
     * @param tempFolder The path to the custom folder that will store the temporary files. If this is not set, the
     *                   library will try to create a standard temporary folder.
     * @param transferFilesFolder The path to the custom folder that will store the transfer data file. If this field is
     *                           not set, it will try to use the tempFolder, if the later is not set, the library will
     *                           try to create a standard temporary folder.
     * @throws IOException if the provided license path is invalid or if the temporary folder is not set and the class
     * failed to create a new one.
     */
    public PkiExpressConfig(Path pkiExpressHome, Path tempFolder, Path transferFilesFolder) throws IOException {

        if (pkiExpressHome != null && pkiExpressHome.toString().endsWith(".config")) {
            throw new RuntimeException("Starting on version 1.1.0 of PKI Express, passing a licensing on the PKIExpressConfig constructor is not longer supported!");
        }

        if (tempFolder != null && Files.exists(tempFolder)) {
            this.tempFolder = tempFolder;
        } else {
            this.tempFolder = Paths.get(System.getProperty("java.io.tmpdir"));
        }

        if (transferFilesFolder != null && Files.exists(transferFilesFolder)) {
            this.transferDataFolder = transferFilesFolder;
        } else {
            this.transferDataFolder = this.tempFolder;
        }

        this.pkiExpressHome = pkiExpressHome;
    }

    public PkiExpressConfig(String pkiExpressHome, String tempFolder, String transferFilesFolder) throws IOException {
        this(
            pkiExpressHome != null ? Paths.get(pkiExpressHome) : null,
            tempFolder != null ? Paths.get(tempFolder) : null,
            transferFilesFolder != null ? Paths.get(transferFilesFolder) : null
        );
    }

    /**
     * Retrieves the PKI Express home.
     *
     * @return The PKI Express home.
     */
    public Path getPkiExpressHome() {
        return pkiExpressHome;
    }

    /**
     * Retrieves the folder path used to store the temporary files.
     *
     * @return The folder path for temporary files.
     */
    public Path getTempFolder() {
        return tempFolder;
    }

    /**
     * Retrieves the folder path used to store the transfer files persistent between steps.
     *
     * @return The folder path for the transfer files.
     */
    public Path getTransferDataFolder() {
        return transferDataFolder;
    }
}
