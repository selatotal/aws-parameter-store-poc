package br.com.selat.awsparameterstorepoc.contract.v1;

import java.util.Optional;

public interface HealthService {

    Optional<HealthOutput> health();
}
