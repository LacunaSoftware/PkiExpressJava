package com.lacunasoftware.pkiexpress;


class OperatorResult {

    private int response;
    private String[] output;


    OperatorResult(int response, String[] output) {
        this.response = response;
        this.output = output;
    }

    public int getResponse() {
        return response;
    }

    public String[] getOutput() {
        return output;
    }
}
