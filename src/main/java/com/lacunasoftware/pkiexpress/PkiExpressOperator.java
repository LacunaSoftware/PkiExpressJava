package com.lacunasoftware.pkiexpress;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


abstract class PkiExpressOperator {

    private List<Path> tempFiles;
    private Map<String, Path> fileReferences;

    protected PkiExpressConfig config;
    protected List<Path> trustedRoots;

    public Boolean trustLacunaTestRoot;


    protected PkiExpressOperator(PkiExpressConfig config) {
        this.config = config;
        this.trustedRoots = new ArrayList<>();
        this.tempFiles = new ArrayList<>();
        this.fileReferences = new HashMap<>();
        this.trustLacunaTestRoot = true;
    }

    protected OperatorResult invoke(CommandEnum command, List<String> args) throws IOException {

        if (config.getLicensePath() == null) {
            throw new RuntimeException("The license's path was not set");
        }

        // Add PKI Express invocation arguments
        List<String> cmdArgs = new ArrayList<>();
        cmdArgs.addAll(getPkiExpressInvocation());

        // Add PKI Express command
        cmdArgs.add(command.getValue());

        // Add PKI Express arguments
        cmdArgs.addAll(args);

        // Add the license path
        cmdArgs.add("-lf");
        cmdArgs.add(config.getLicensePath().toString());

        // Add file references if added
        if (fileReferences != null && !fileReferences.isEmpty()) {

            for (String key : fileReferences.keySet()) {
                cmdArgs.add("-fr");
                cmdArgs.add(String.format("%s=%s", key, fileReferences.get(key)));
            }
        }

        // Add trusted roots if added
        if (trustedRoots != null && !trustedRoots.isEmpty()) {

            for (Path trustedRoot : trustedRoots) {
                cmdArgs.add("-tr");
                cmdArgs.add(trustedRoot.toString());
            }
        }

        // Add trust Lacuna test root if set
        if (trustLacunaTestRoot) {
            cmdArgs.add("-tt");
        }

        // Process command arguments
        String[] argArr = new String[cmdArgs.size()];
        cmdArgs.toArray(argArr);

        // Execute the command
        Process proc = Runtime.getRuntime().exec(argArr);

        // Read response
        InputStreamReader reader = new InputStreamReader(proc.getInputStream(), "UTF-8");
        StringBuilder sb = new StringBuilder();
        char[] buf = new char[1024];
        while (reader.read(buf, 0, buf.length) != -1) {
            sb.append(buf);
        }
        return new OperatorResult(proc.exitValue(), sb.toString());
    }

    protected List<String> getPkiExpressInvocation() {

        String os = null;

        // Identify OS
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            os = "linux";
        } else if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            os = "win";
        } else {
            throw new RuntimeException("Unsupported OS: " + System.getProperty("os.name").toLowerCase());
        }

        // Verify if the PKI Express home is set on configuration
        Path home = config.getPkiExpressHome();
        if (home != null) {

            if (os.equals("linux")) {
                if (!Files.exists(home.resolve("pkie.dll"))) {
                    throw new RuntimeException("The file pkie.dll could not be found on directory " + home.toString());
                }
            } else {
                if (!Files.exists(home.resolve("pkie.exe"))) {
                    throw new RuntimeException("The file pkie.exe could not be found on directory " + home.toString());
                }
            }

        } else {

            if (os.equals("linux")) {

                if (Files.exists(Paths.get("/usr/local/share/pkie/pkie.dll"))) {
                    home = Paths.get("/usr/local/share/pkie");
                } else if (Files.exists(Paths.get("/usr/share/pkie/pkie.dll"))) {
                    home = Paths.get("/usr/local/pkie");
                }

            } else {

                if (Files.exists(Paths.get(System.getenv("ProgramFiles(x86)")).resolve("Lacuna Software\\PKI Express\\pkie.exe"))) {
                    home = Paths.get(System.getenv("ProgramFiles(x86)")).resolve("Lacuna Software\\PKI Express");
                } else if (Files.exists(Paths.get(System.getenv("LOCALAPPDATA")).resolve("Lacuna Software\\PKI Express\\pkie.exe"))) {
                    home = Paths.get(System.getenv("LOCALAPPDATA")).resolve("Lacuna Software\\PKI Express");
                }

            }

            if (home == null) {
                throw new RuntimeException("Could not determine the installation folder of PKI Express. If you installed PKI Express on a custom folder, make sure you are specifying it on the PkiExpressConfig object.");
            }

        }

        List<String> invocArgs = new ArrayList<>();
        if (os.equals("linux")) {
            invocArgs.add("dotnet");
            invocArgs.add(home.resolve("pkie.dll").toString());
        } else {
            invocArgs.add(home.resolve("pkie.exe").toString());
        }

        return invocArgs;
    }

    protected Path createTempFile() throws IOException {
        Path tempFile = Files.createTempFile(config.getTempFolder(), "pkie", "");
        tempFiles.add(tempFile);
        return tempFile;
    }

    protected String getTransferFileName() {
        byte[] bytes = new byte[16];
        new Random().nextBytes(bytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    @Override
    public void finalize() {
        for (Path tempFile : tempFiles) {
            try {
                Files.delete(tempFile);
            } catch (Exception ex) {
                // TODO: log
            }
        }
    }

    public void addFileReference(String alias, Path path) {
        if (!Files.exists(path)) {
            throw new RuntimeException("The provided file path was not found");
        }

        fileReferences.put(alias, path);
    }

    public void addTrustedRoot(Path path) {
        if (!Files.exists(path)) {
            throw new RuntimeException("The provided trusted root was not found");
        }

        trustedRoots.add(path);
    }
}
