#!/bin/bash

# Create new package directory
mkdir -p src/main/java/br/com/neurotech/neurocreditapi
mkdir -p src/test/java/br/com/neurotech/neurocreditapi

# Move all files from challenge to neurocreditapi
mv src/main/java/br/com/neurotech/challenge/* src/main/java/br/com/neurotech/neurocreditapi/
mv src/test/java/br/com/neurotech/challenge/* src/test/java/br/com/neurotech/neurocreditapi/

# Remove old package directory
rm -rf src/main/java/br/com/neurotech/challenge
rm -rf src/test/java/br/com/neurotech/challenge

# Update all package declarations and imports
find . -type f -name "*.java" -exec sed -i '' 's/br.com.neurotech.challenge/br.com.neurotech.neurocreditapi/g' {} + 