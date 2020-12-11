resource "aws_ssm_parameter" "parameter1" {
  name = "/poc-parameter-store/${var.environment}/parameter1"
  description = "POC Parameter String Test"
  type = "String"
  value = "parameter value 1"
  overwrite = true

  tags = {
    Name  = var.component_name
    env   = var.environment
    layer = var.layer
  }
}

resource "aws_ssm_parameter" "parameter2" {
  name = "/poc-parameter-store/${var.environment}/parameter2"
  description = "POC Parameter String List Test"
  type = "StringList"
  value = "String 1, String 2, String 3"
  overwrite = true

  tags = {
    Name  = var.component_name
    env   = var.environment
    layer = var.layer
  }
}
