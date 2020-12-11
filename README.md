# aws-parameter-store-poc

A simple POC of a Java Jetty Service using config stored in AWS Parameter Store.

The main goal is to use [AWS Parameter Store]() as a config service and a Java Webservice and don't use  spring-cloud-starter-aws-parameter-store-config as dependency.

## How-to setup

First of all, you need to create some parameters in AWS System Managers.

You can do it by AWS Console or AWS CLI, as described [here](https://docs.aws.amazon.com/systems-manager/latest/userguide/sysman-paramstore-su-create.html), but I think that the best is to use a Terraform Script that I created in [aws-parameter-store-poc-infra/terraform](aws-parameter-store-poc-infra/terraform) folder.

To use, you can simply execute the scripts as described below:

* Add your AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY in your enviroment:
```bash
export AWS_ACCESS_KEY_ID=your-aws-access-key-here
export AWS_SECRET_ACCESS_KEY=your-aws-secret-access-key-here
```

* To Create Parameters by Terraform
```bash
scripts/deploy-infra.sh path-to-tfstate environment region
```
Example:
```bash
scripts/deploy-infra.sh /tmp dev us-east-1
```

* To Remove Parameters by Terraform
```bash
scripts/destroy-infra.sh path-to-tfstate environment region
```
Example:
```bash
scripts/destroy-infra.sh /tmp dev us-east-1
```

## How-to Build And Run

* Add your AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY in your enviroment:
```bash
export AWS_ACCESS_KEY_ID=your-aws-access-key-here
export AWS_SECRET_ACCESS_KEY=your-aws-secret-access-key-here
export AWS_DEFAULT_REGION=your-aws-region-here
```

* Execute run.sh script
```bash
scripts/run.sh
```

Your project will listen requests at 8080 port. Now you can test access with the following HTTP requests:

### Request a parameter
http://localhost:8080/parameter/poc-parameter-store/dev/parameter1
```json
{
  "name": "/poc-parameter-store/dev/parameter1",
  "type": "String",
  "value": "parameter value 1"
}
```

http://localhost:8080/parameter/poc-parameter-store/dev/parameter2
```json
{
  "name": "/poc-parameter-store/dev/parameter2",
  "type": "StringList",
  "value": "String 1, String 2, String 3"
}
```

### Request a path's parameters list

http://localhost:8080/path/poc-parameter-store/dev
```json
[
  {
    "name": "/poc-parameter-store/dev/parameter1",
    "type": "String",
    "value": "parameter value 1"
  },
  {
    "name": "/poc-parameter-store/dev/parameter2",
    "type": "StringList",
    "value": "String 1, String 2, String 3"
  }
]
```
