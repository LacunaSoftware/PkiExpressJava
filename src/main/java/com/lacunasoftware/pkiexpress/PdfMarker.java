package com.lacunasoftware.pkiexpress;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PdfMarker extends PkiExpressOperator {

    private PadesMeasurementUnits measurementUnits;
    private PadesPageOptimization pageOptimization;
    private List<PdfMark> marks;
    private Path filePath;
    private Path outputFilePath;
    private boolean overwriteOriginalFile = false;


    public PdfMarker(PkiExpressConfig config) {
        super(config);
        this.marks = new ArrayList<PdfMark>();
        this.measurementUnits = PadesMeasurementUnits.Centimeters;
    }

    public PdfMarker() throws IOException {
        this(new PkiExpressConfig());
    }

    //region setFile
    public void setFile(InputStream stream) throws IOException {
        this.filePath = writeToTempFile(stream);
    }

    public void setFile(byte[] content) throws IOException {
        setFile(new ByteArrayInputStream(content, 0, content.length));
    }

    public void setFile(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException("The provided file to be signed was not found");
        }
        this.filePath = path;
    }

    public void setFile(String path) throws IOException {
        setFile(path != null ? Paths.get(path) : null);
    }
    //endregion

    public void setOutputFilePath(Path outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    public PadesMeasurementUnits getMeasurementUnits() {
        return measurementUnits;
    }
    public void setMeasurementUnits(PadesMeasurementUnits measurementUnits) {
        this.measurementUnits = measurementUnits;
    }

    public PadesPageOptimization getPageOptimization() {
        return pageOptimization;
    }
    public void setPageOptimization(PadesPageOptimization pageOptimization) {
        this.pageOptimization = pageOptimization;
    }

    public List<PdfMark> getMarks() {
        return marks;
    }
    public void setMarks(List<PdfMark> marks) {
        this.marks = marks;
    }
    public void addMark(PdfMark mark) {
        if (this.marks == null) {
            this.marks = new ArrayList<PdfMark>();
        }
        this.marks.add(mark);
    }

    public boolean getOverwriteOriginalFile() {
        return overwriteOriginalFile;
    }
    public void setOverwriteOriginalFile(boolean overwriteOriginalFile) {
        this.overwriteOriginalFile = overwriteOriginalFile;
    }

    public void apply() throws IOException {

        if (filePath == null) {
            throw new RuntimeException("The file to be marked was not set");
        }

        List<String> args = new ArrayList<String>();
        args.add(filePath.toString());

        // Generate changes file
        Path changesFilePath = generateChangesFile(marks, measurementUnits, pageOptimization);
        args.add(changesFilePath.toString());

        // Logic to overwrite original file or use the output file
        if (overwriteOriginalFile) {
            args.add("--overwrite");
        } else {
            args.add(outputFilePath.toString());
        }

        // This operation can only be used on versions greater than 1.3 of the PKI Express.
        versionManager.requireVersion(new Version("1.3"));

        // Invoke command
        invoke(CommandEnum.CommandEditPdf, args);
    }

    private Path generateChangesFile(
            List<PdfMark> marks,
            PadesMeasurementUnits measurementUnits,
            PadesPageOptimization pageOptimization
    ) throws IOException {

        // Generate model
        ChangesFileModel request = new ChangesFileModel();
        List<PdfMarkModel> marksModels = new ArrayList<PdfMarkModel>();
        for (PdfMark pm : marks) {
            marksModels.add(pm.toModel());
        }
        request.setMarks(marksModels);
        if (measurementUnits != null) {
            request.setMeasurementUnits(measurementUnits.toString());
        }
        if (pageOptimization != null) {
            request.setPageOptimization(pageOptimization.toModel());
        }

        // Store json file
        Path tempFilePath = createTempFile();
        OutputStream outputStream = new FileOutputStream(tempFilePath.toFile());
        new ObjectMapper().writeValue(outputStream, request);
        outputStream.close();

        return tempFilePath;
    }
}
