package br.com.selat.awsparameterstorepoc.contract.v1;

import br.com.selat.awsparameterstorepoc.contract.v1.output.HealthOutput;

import java.util.Optional;

public interface HealthService {

    Optional<HealthOutput> health();
}
