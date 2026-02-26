#!/bin/bash
set -e

# Check if PKIE is already installed
if [ -f /usr/local/bin/pkie ]; then
    echo "PKIE is already installed, skipping setup..."
    exit 0
fi

# Download and install PKIE
wget -q $PKIE_DOWNLOAD_URL
sudo mkdir -p /usr/share/pkie
sudo tar xzf pkie-$PKIE_VERSION.tar.gz -C /usr/share/pkie
sudo chmod +x /usr/share/pkie/pkie
sudo ln -sf /usr/share/pkie/pkie /usr/local/bin/pkie
sudo mkdir -p /var/log/pkie
sudo chmod 777 /var/log/pkie
sudo pkie config --set logDir=/var/log/pkie

# Activate license if available
LICENSE_FILE="/workspaces/PkiExpressJava/.env/LacunaPkiLicense.config"
if [ -f "$LICENSE_FILE" ]; then
    echo "Activating PKIE license..."
    sudo pkie activate "$LICENSE_FILE"
else
    echo "Warning: License file not found at $LICENSE_FILE. PKIE will run in trial mode."
    echo "Please add LacunaPkiLicense.config to the .env/ directory to activate the license."
fi

rm pkie-$PKIE_VERSION.tar.gz
rm -f pkie-$PKIE_VERSION.tar.gz.1

# In order to activate the trust service certificate, you need to have a valid client_id and client_secret.
# You can do this by going to https://demos.lacunasoftware.com/pt/tsp-app-registration and registering a new application.

# Activate trust service certificate if available
TRUST_SERVICE_CERT_FILE="/workspaces/PkiExpressJava/.env/trustServiceCert.json"
if [ -f "$TRUST_SERVICE_CERT_FILE" ]; then
    echo "Reading client_id and client_secret from trustServiceCert.json..."
    CLIENT_ID=$(jq -r '.client_id' "$TRUST_SERVICE_CERT_FILE")
    CLIENT_SECRET=$(jq -r '.client_secret' "$TRUST_SERVICE_CERT_FILE")
    SERVICE_NAME="safeID"
    ENDPOINT=$(jq -r '.endpoint' "$TRUST_SERVICE_CERT_FILE")
    echo "Activating trust service certificate..."
    sudo pkie config --set trustServices:$SERVICE_NAME:endpoint=$ENDPOINT
    sudo pkie config --set trustServices:$SERVICE_NAME:clientId=$CLIENT_ID
    sudo pkie config --set trustServices:$SERVICE_NAME:clientSecret=$CLIENT_SECRET
else
    echo "Warning: Trust service certificate file not found at $TRUST_SERVICE_CERT_FILE. Trust service will not be activated."
    echo "Please add trustServiceCert.json to the .env/ directory to activate the trust service certificate."
    echo "You can do this by going to https://demos.lacunasoftware.com/pt/tsp-app-registration and registering a new application."
fi