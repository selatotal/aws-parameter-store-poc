package br.com.selat.awsparameterstorepoc.contract.v1;

import java.util.List;
import java.util.Optional;

public interface ParameterService {

    Optional<ParameterOutput> get(String parameterName);
    List<ParameterOutput> getPath(String path);
    Optional<ParameterOutput> save(String parameterName, String parameterValue);
}
