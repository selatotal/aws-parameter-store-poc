package br.com.selat.awsparameterstorepoc.contract.v1;

public class ParameterOutput {

    private String name;
    private String value;

    public ParameterOutput() {
    }

    public ParameterOutput(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
