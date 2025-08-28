#!/bin/bash
# tools/setup-pre-commit-hook.sh

echo "Setting up Git hooks..."

# Get the project root directory (where this script's parent directory is)
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(dirname "$SCRIPT_DIR")"

# Change to project root
cd "$PROJECT_ROOT" || {
    echo "❌ Failed to change to project root directory"
    exit 1
}

# Create hooks directory if it doesn't exist
mkdir -p .git/hooks

# Copy pre-commit hook
cat > .git/hooks/pre-commit << 'EOF'
#!/bin/bash

echo "Running Spotless formatting..."

if ! ./gradlew spotlessApply; then
    echo "❌ Spotless formatting failed. Commit rejected."
    exit 1
fi

# Check if Spotless made any changes
if ! git diff --quiet --exit-code; then
    echo "ℹ️ Spotless made formatting changes. Staging changes..."
    # Stage all changes made by Spotless
    git add -A
fi

echo "✅ Code formatting is clean or has been fixed. Proceeding with commit..."
exit 0

EOF

# Make hook executable
chmod +x .git/hooks/pre-commit

echo "✅ Pre-commit hook installed successfully at $(pwd)/.git/hooks/pre-commit"
echo "The hook will now run './gradlew spotlessApply' before each push."
