import os
import sys
import argparse

def write_file(filepath, content, force=False):
    """Writes content to a specified file, with safeguards."""
    # Security: Prevent writing outside the current directory
    if ".." in filepath or not os.path.abspath(filepath).startswith(os.getcwd()):
        print(f"Error: Cannot write to '{filepath}'. Path is outside the project directory.")
        return

    # Safeguard: Check if file exists
    if os.path.exists(filepath) and not force:
        print(f"Error: File '{filepath}' already exists. Use the --force flag to overwrite.")
        return

    try:
        # Create directories if they don't exist
        os.makedirs(os.path.dirname(filepath), exist_ok=True)
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(content)
        print(f"Success: Content written to {filepath}")
    except Exception as e:
        print(f"An error occurred: {e}")

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description="Write content to a file.")
    parser.add_argument("filepath", type=str, help="The relative path to the file to be created/written.")
    parser.add_argument("--force", action="store_true", help="Overwrite the file if it already exists.")

    # Read content from stdin
    content_from_stdin = sys.stdin.read()

    args = parser.parse_args()
    write_file(args.filepath, content_from_stdin, args.force)
