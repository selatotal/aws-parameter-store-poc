package br.com.selat.awsparameterstorepoc.impl.rest.awsssmconfig;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.util.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class AWSSSMConfig {

    private final String awsRegion = Optional.ofNullable(System.getenv("AWS_DEFAULT_REGION")).orElse("us-east-1");
    private final String awsAccessSecret = Optional.ofNullable(System.getenv("AWS_SECRET_ACCESS_KEY")).orElse("");
    private final String awsAccessKey = Optional.ofNullable(System.getenv("AWS_ACCESS_KEY_ID")).orElse("");

    @Bean
    public AWSSimpleSystemsManagement awsSimpleSystemsManagement(){

        if (StringUtils.isNullOrEmpty(awsAccessKey)) {
            InstanceProfileCredentialsProvider instanceProfileCredentialsProvider = InstanceProfileCredentialsProvider.getInstance();
            instanceProfileCredentialsProvider.refresh();
            return AWSSimpleSystemsManagementClientBuilder.standard()
                    .withCredentials( instanceProfileCredentialsProvider )
                    .withRegion(Regions.fromName(awsRegion))
                    .build();
        }

        return AWSSimpleSystemsManagementClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsAccessSecret)))
                .withRegion(Regions.fromName(awsRegion))
                .build();
    }
}
