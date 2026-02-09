FROM mcr.microsoft.com/devcontainers/base:ubuntu

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y --no-install-recommends \
    curl zip unzip ca-certificates git wget jq \
    && rm -rf /var/lib/apt/lists/*

# Install SDKMAN
ENV SDKMAN_DIR=/usr/local/sdkman
RUN curl -s "https://get.sdkman.io" | bash

# Install Java, Gradle, Maven (all in one layer)
RUN bash -lc "source $SDKMAN_DIR/bin/sdkman-init.sh && \
    sdk install java 17.0.10-tem && \
    sdk install gradle 8.14 && \
    sdk install maven 3.9.9 && \
    sdk default java 17.0.10-tem && \
    sdk default gradle 8.14 && \
    sdk default maven 3.9.9"

# Ensure vscode user can read SDKMAN files (vscode exists in base image)
RUN chown -R 1000:1000 $SDKMAN_DIR || true

# Expose tools to PATH for non-interactive shells
ENV PATH="$SDKMAN_DIR/candidates/java/current/bin:\
$SDKMAN_DIR/candidates/gradle/current/bin:\
$SDKMAN_DIR/candidates/maven/current/bin:$PATH"
