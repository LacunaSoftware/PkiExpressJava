package com.lacunasoftware.pkiexpress;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExtendXmlArchiving extends PkiExpressOperator {
    
    private String inputFile;
    private String outputFile;
    private String validationPolicy;
    private boolean overwrite;
    private TimestampAuthority timestampAuthority;

    public ExtendXmlArchiving(PkiExpressConfig config) {
        super(config);
    }
    
    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public String getValidationPolicy() {
        return validationPolicy;
    }

    public void setValidationPolicy(String validationPolicy) {
        this.validationPolicy = validationPolicy;
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }

    public TimestampAuthority getTimestampAuthority() {
        return timestampAuthority;
    }

    public void setTimestampAuthority(TimestampAuthority timestampAuthority) {
        this.timestampAuthority = timestampAuthority;
    }
    
    public ExtendXmlArchivingCommandResult extendArchiving() throws IOException {
        List<String> args = new ArrayList<>();
        args.add(this.inputFile);

        if (this.outputFile != null && !this.outputFile.isEmpty()) {
            args.add(this.outputFile);
        }

        if (this.validationPolicy != null && !this.validationPolicy.isEmpty()) {
            args.add("--policy");
            args.add(this.validationPolicy);
        }

        if (this.overwrite) {
            args.add("--overwrite");
        }

        if (this.timestampAuthority != null) {
            args.addAll(this.timestampAuthority.getCmdArguments());
        }
        
        OperatorResult result = invoke(CommandEnum.CommandExtendXmlArchiving, args);
        return parseOutput(result.getOutput()[0], ExtendXmlArchivingCommandResult.class);
    }
} 