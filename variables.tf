variable "DATABASE_URL" {
  description = "The URL of the database"
}

variable "DATABASE_USERNAME" {
  description = "The username of the database"
}

variable "DATABASE_PASSWORD" {
  description = "The password of the database"
}

variable "DATABASE_NAME" {
  description = "The name of the database"
}

variable "EUREKA_URL" {
  description = "The URL of the Eureka server"
}

variable "JWT_SECRET" {
  description = "The secret"
}

variable "JWT_EXPIRATION_TIME" {
  description = "The expiration time of the JWT token"
}

variable "VERSION" {
  description = "The version of the application"
}

variable "project_id" {}
variable "region" {}
variable "zone" {}
variable "IMAGE_NAME" {}
variable "JWT_ISSUER" {}
variable "JWT_ALLOW_ISSUER" {}

variable "service_name" {
  description = "The name of the Cloud Run service"
  default = "room-service"
  
}