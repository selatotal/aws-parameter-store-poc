package br.com.selat.awsparameterstorepoc.impl.service;

import br.com.selat.awsparameterstorepoc.contract.v1.HealthOutput;
import br.com.selat.awsparameterstorepoc.contract.v1.HealthService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HealthServiceImpl implements HealthService {

    @Override
    public Optional<HealthOutput> health() {
        return Optional.of(new HealthOutput(200));
    }
}
