package br.com.selat.awsparameterstorepoc.impl.service;

import br.com.selat.awsparameterstorepoc.contract.v1.ParameterOutput;
import br.com.selat.awsparameterstorepoc.contract.v1.ParameterService;
import br.com.selat.exceptions.InternalErrorException;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParameterServiceImpl implements ParameterService {

    private final Logger logger = LoggerFactory.getLogger(ParameterServiceImpl.class);
    private final AWSSimpleSystemsManagement awsSimpleSystemsManagement;

    @Autowired
    public ParameterServiceImpl(AWSSimpleSystemsManagement awsSimpleSystemsManagement) {
        this.awsSimpleSystemsManagement = awsSimpleSystemsManagement;
    }

    @Override
    public Optional<ParameterOutput> get(String parameterName) {
        GetParameterRequest getParameterRequest = new GetParameterRequest().withName(parameterName);
        try {
            final GetParameterResult result = awsSimpleSystemsManagement.getParameter(getParameterRequest);
            return Optional.of(new ParameterOutput(parameterName, result.getParameter().getType(), result.getParameter().getValue()));
        } catch (ParameterNotFoundException e){
            logger.error("Parameter {} not found", parameterName);
            return Optional.empty();
        } catch (AWSSimpleSystemsManagementException e){
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<ParameterOutput> getPath(String path) {
        GetParametersByPathRequest getParameterRequest = new GetParametersByPathRequest().withPath(path);
        try {
            final GetParametersByPathResult result = awsSimpleSystemsManagement.getParametersByPath(getParameterRequest);
            return result.getParameters().stream().map(r -> new ParameterOutput(r.getName(), r.getType(), r.getValue())).collect(Collectors.toList());
        } catch (AWSSimpleSystemsManagementException e){
            throw new InternalErrorException(e);
        }
    }

    @Override
    public Optional<ParameterOutput> save(String parameterName, String parameterValue) {
        return Optional.empty();
    }
}
