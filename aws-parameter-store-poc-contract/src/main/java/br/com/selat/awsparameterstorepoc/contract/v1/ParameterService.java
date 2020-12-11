package br.com.selat.awsparameterstorepoc.contract.v1;

public interface ParameterService {

    ParameterOutput get(String parameterName);
    ParameterOutput save(String parameterName, String parameterValue);
}
