# project_lister.py
import os
import argparse

def list_files(startpath, module):
    """Lists the file structure for a given module path."""
    # Basic security: Prevent directory traversal
    if ".." in module:
        print("Error: Invalid module path.")
        return

    module_path = os.path.join(startpath, module)
    if not os.path.isdir(module_path):
        print(f"Error: Module '{module}' not found at '{module_path}'.")
        return

    for root, dirs, files in os.walk(module_path):
        # Prune common build/generated directories
        dirs[:] = [d for d in dirs if d not in ['build', '.gradle', 'generated']]
        level = root.replace(module_path, '').count(os.sep)
        indent = ' ' * 4 * level
        print(f'{indent}{os.path.basename(root)}/')
        subindent = ' ' * 4 * (level + 1)
        for f in files:
            print(f'{subindent}{f}')

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description="List project files for a specific module.")
    parser.add_argument("module", type=str, help="The module to inspect (e.g., 'app' or 'core/data').")
    args = parser.parse_args()
    # Assume the script is run from the project root
    list_files(os.getcwd(), args.module)
