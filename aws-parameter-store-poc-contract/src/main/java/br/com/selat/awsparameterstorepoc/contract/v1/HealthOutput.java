package br.com.selat.awsparameterstorepoc.contract.v1;

public class HealthOutput {

    private int status;

    public HealthOutput() {
    }

    public HealthOutput(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
