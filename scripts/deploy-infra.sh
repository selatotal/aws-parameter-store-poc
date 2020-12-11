#!/bin/bash

name=aws-parameter-store-poc-infra
state_base_dir=$1
environment=$2
region=$3
TERRAFORM_STATE_DIR=$state_base_dir/$name/$env/$region/terraform.tfstate

cd aws-parameter-store-poc-infra/terraform

terraform init

terraform apply \
-state=$TERRAFORM_STATE_DIR \
-auto-approve \
-var "environment=$environment" \
-var "component_name=$name" \
-var "region=$region"