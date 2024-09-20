
provider "google" {
  project = var.project_id
  region  = var.region
  zone    = var.zone
}

terraform {
  backend "http" {}
}

resource "google_compute_instance" "default" {
  name         = var.service_name
  machine_type = "e2-medium"
  zone         = var.zone

  tags = ["softdev", "http-server", "https-server"]

  labels = {
    version = "${var.VERSION}"
  }

  boot_disk {
    initialize_params {
      image = "cos-cloud/cos-stable"
    }
  }

  network_interface {
    network = "default"

    access_config {
      // Ephemeral IP
    }
  }
    metadata = {
    "gce-container-declaration" = <<EOF
spec:
  containers:
    - name: gce-container
      image: ${var.IMAGE_NAME}
      env:
        - name: SPRING_DATASOURCE_URL
          value: "jdbc:postgresql://${var.DATABASE_URL}/${var.DATABASE_NAME}"
        - name: SPRING_DATASOURCE_USERNAME
          value: ${var.DATABASE_USERNAME}
        - name: SPRING_DATASOURCE_PASSWORD
          value: ${var.DATABASE_PASSWORD}
        - name: EUREKA_CLIENT_ENABLED
          value: "true"
        - name: EUREKA_CLIENT_SERVICEURL_DEFAULTZONE
          value: "http://${var.EUREKA_URL}"
        - name: SECURITY_JWT_SECRET_KEY
          value: ${var.JWT_SECRET}
        - name: SECURITY_JWT_EXPIRATION_TIME
          value: ${var.JWT_EXPIRATION_TIME}
        - name: SPRING_JPA_HIBERNATE_DDL-AUTO
          value: "update"
        - name: SPRING_JPA_SHOW_SQL
          value: "false"
        - name: SECURITY_JWT_ISSUER
          value: ${var.JWT_ISSUER}
        - name: SECURITY_JWT_ALLOW_ISSUER
          value: ${var.JWT_ALLOW_ISSUER}
      ports:
        - containerPort: 8080
          hostPort: 80
      securityContext:
        privileged: true
  restartPolicy: Always
EOF
  }

  service_account {
    scopes = ["cloud-platform"]
  }

}

output "ipInstance" {
  value = google_compute_instance.default.network_interface.0.access_config.0.nat_ip
}
