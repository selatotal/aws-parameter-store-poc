package br.com.selat.awsparameterstorepoc.impl.service;

import br.com.selat.awsparameterstorepoc.contract.v1.input.ParameterInput;
import br.com.selat.awsparameterstorepoc.contract.v1.output.ParameterOutput;
import br.com.selat.awsparameterstorepoc.contract.v1.ParameterService;
import br.com.selat.exceptions.InternalErrorException;
import br.com.selat.exceptions.ServiceValidationException;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.isEmpty;

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
    public Optional<ParameterOutput> save(ParameterInput parameterInput) {
        validateParameterInput(parameterInput);
        PutParameterRequest putParameterRequest = new PutParameterRequest()
                .withName(parameterInput.getName())
                .withType(parameterInput.getType())
                .withValue(parameterInput.getValue())
                .withOverwrite(Boolean.TRUE);
        try {
            awsSimpleSystemsManagement.putParameter(putParameterRequest);
            return get(parameterInput.getName());
        } catch (AWSSimpleSystemsManagementException e){
            throw new InternalErrorException(e);
        }
    }

    private void validateParameterInput(ParameterInput parameterInput){
        if (parameterInput == null){
            throw new ServiceValidationException("Invalid payload");
        }
        if (isEmpty(parameterInput.getName())){
            throw new ServiceValidationException("Invalid name");
        }
        if (!"String".equals(parameterInput.getType()) &&
            !"StringList".equals(parameterInput.getType()) &&
            !"SecureString".equals(parameterInput.getType())){
            throw new ServiceValidationException("Invalid type");
        }
        if (isEmpty(parameterInput.getValue())){
            throw new ServiceValidationException("Invalid value");
        }
    }
}
