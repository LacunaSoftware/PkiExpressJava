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

    private Path licensePath;
    private Path pkiExpressHome;
    private Path tempFolder;
    private Path transferDataFolder;

    /**
     * Creates a new instance using the given license path.
     *
     * @param licensePath The path to the license file.
     * @throws IOException if the provided license path is invalid or the library failed to create a temporary folder.
     */
    public PkiExpressConfig(Path licensePath) throws IOException {
        this(licensePath, null);
    }

    public PkiExpressConfig(String licensePath) throws IOException {
        this(
            licensePath != null ? Paths.get(licensePath) : null
        );
    }

    /**
     * Creates a new instance using the given license path and PKI Express executable home.
     *
     * @param licensePath The path to the license file.
     * @param pkiExpressHome The path to the folder that contains the PKI Express executable.
     * @throws IOException if the provided license path is invalid or the library failed to create a temporary folder.
     */
    public PkiExpressConfig(Path licensePath, Path pkiExpressHome) throws IOException {
        this(licensePath, pkiExpressHome, null, null);
    }

    public PkiExpressConfig(String licensePath, String pkiExpressHome) throws IOException {
        this(
            licensePath != null ? Paths.get(licensePath) : null,
            pkiExpressHome != null ? Paths.get(pkiExpressHome) : null
        );
    }

    /**
     * Creates a new instance using the given license path, PKI Express executable home and temporary folder.
     *
     * @param licensePath The path to the license file.
     * @param pkiExpressHome The path to the folder that contains the PKI Express executable.
     * @param tempFolder The path to the custom folder that will store the temporary files. If this is not set, the
     *                   library will try to create a standard temporary folder.
     * @throws IOException if the provided license path is invalid or if the temporary folder is not set and the class
     * failed to create a new one.
     */
    public PkiExpressConfig(Path licensePath, Path pkiExpressHome, Path tempFolder) throws IOException {
        this(licensePath, pkiExpressHome, tempFolder, null);
    }

    public PkiExpressConfig(String licensePath, String pkiExpressHome, String tempFolder) throws IOException {
        this(
            licensePath != null ? Paths.get(licensePath) : null,
            pkiExpressHome != null ? Paths.get(pkiExpressHome) : null,
            tempFolder != null ? Paths.get(tempFolder) : null
        );
    }

    /**
     * Creates a new instance using the given license path, PKI Express executable home and temporary folder.
     *
     * @param licensePath The path to the license file.
     * @param pkiExpressHome The path to the folder that contains the PKI Express executable.
     * @param tempFolder The path to the custom folder that will store the temporary files. If this is not set, the
     *                   library will try to create a standard temporary folder.
     * @param transferFilesFolder The path to the custom folder that will store the transfer data file. If this field is
     *                           not set, it will try to use the tempFolder, if the later is not set, the library will
     *                           try to create a standard temporary folder.
     * @throws IOException if the provided license path is invalid or if the temporary folder is not set and the class
     * failed to create a new one.
     */
    public PkiExpressConfig(Path licensePath, Path pkiExpressHome, Path tempFolder, Path transferFilesFolder) throws IOException {

        if (!Files.exists(licensePath)) {
            throw new FileNotFoundException("The provided license was not found");
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

        this.licensePath = licensePath;
        this.pkiExpressHome = pkiExpressHome;
    }

    public PkiExpressConfig(String licensePath, String pkiExpressHome, String tempFolder, String transferFilesFolder) throws IOException {
        this(
            licensePath != null ? Paths.get(licensePath) : null,
            pkiExpressHome != null ? Paths.get(pkiExpressHome) : null,
            tempFolder != null ? Paths.get(tempFolder) : null,
            transferFilesFolder != null ? Paths.get(transferFilesFolder) : null
        );
    }

    /**
     * Retrieves the license path.
     *
     * @return The license path.
     */
    public Path getLicensePath() {
        return licensePath;
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
