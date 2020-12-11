package br.com.selat.awsparameterstorepoc.contract.v1;

import br.com.selat.awsparameterstorepoc.contract.v1.input.ParameterInput;
import br.com.selat.awsparameterstorepoc.contract.v1.output.ParameterOutput;

import java.util.List;
import java.util.Optional;

public interface ParameterService {

    Optional<ParameterOutput> get(String parameterName);
    List<ParameterOutput> getPath(String path);
    Optional<ParameterOutput> save(ParameterInput parameterInput);
}
