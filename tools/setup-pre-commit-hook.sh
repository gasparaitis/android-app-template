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

# Update SECURITY.md last updated date
if [ -f "SECURITY.md" ]; then
    echo "📄 Updating SECURITY.md date..."
    CURRENT_DATE=$(date '+%Y-%m-%d')
    sed -i.bak "s/Last updated: .*/Last updated: $CURRENT_DATE/" SECURITY.md
    rm -f SECURITY.md.bak 2>/dev/null  # Remove backup file if created

    # Check if SECURITY.md was modified
    if ! git diff --quiet --exit-code SECURITY.md; then
        echo "ℹ️ SECURITY.md date updated. Staging changes..."
        git add SECURITY.md
    fi
fi

echo "✅ Code formatting is clean or has been fixed. Proceeding with commit..."
exit 0

EOF

# Make hook executable
chmod +x .git/hooks/pre-commit

echo "✅ Pre-commit hook installed successfully at $(pwd)/.git/hooks/pre-commit"
echo "The hook will now run './gradlew spotlessApply' and update SECURITY.md date before each commit."
