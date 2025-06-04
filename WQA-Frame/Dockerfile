FROM openjdk:17-jdk-slim-buster

# Install dependencies for Chrome and troubleshooting tools
RUN apt-get update && \
    apt-get install -y wget gnupg ca-certificates fonts-liberation libappindicator3-1 libasound2 \
    libatk-bridge2.0-0 libatk1.0-0 libc6 libcairo2 libcups2 libdbus-1-3 libexpat1 libfontconfig1 \
    libgbm1 libgcc1 libglib2.0-0 libgtk-3-0 libnspr4 libnss3 libpango-1.0-0 libpangocairo-1.0-0 \
    libstdc++6 libx11-6 libx11-xcb1 libxcb1 libxcomposite1 libxcursor1 libxdamage1 libxext6 \
    libxfixes3 libxi6 libxrandr2 libxrender1 libxss1 libxtst6 lsb-release xdg-utils && \
    rm -rf /var/lib/apt/lists/*

# Download and install Chrome 131.0.6778.264
RUN wget -q https://dl.google.com/linux/chrome/deb/pool/main/g/google-chrome-stable/google-chrome-stable_131.0.6778.264-1_amd64.deb -O /tmp/chrome.deb && \
    apt-get update && \
    apt-get install -y /tmp/chrome.deb && \
    rm /tmp/chrome.deb && \
    rm -rf /var/lib/apt/lists/*


# Copy application JAR
COPY ./target/WQA-Framework-1.0-SNAPSHOT.jar /home/WQA-Framework-1.0-SNAPSHOT.jar

# Copy extension file
RUN mkdir -p /opt/extension && \
    chmod -R 755 /opt
COPY extension/ /opt/extension
RUN chmod 644 /opt/extension/adBlockUlt.crx


CMD ["java", "-jar", "/home/WQA-Framework-1.0-SNAPSHOT.jar"]
