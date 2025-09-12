# file_reader.py
import argparse
import os

def read_file_content(filepath):
    """Reads and prints the content of a specified file."""
    # Basic security: Prevent reading files outside the project directory
    if ".." in filepath or not os.path.abspath(filepath).startswith(os.getcwd()):
        print("Error: Access denied. Cannot read files outside the project directory.")
        return

    try:
        with open(filepath, 'r', encoding='utf-8') as f:
            print(f.read())
    except FileNotFoundError:
        print(f"Error: File not found at '{filepath}'.")
    except Exception as e:
        print(f"An error occurred: {e}")

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description="Read the content of a file.")
    parser.add_argument("filepath", type=str, help="The relative path to the file.")
    args = parser.parse_args()
    read_file_content(args.filepath)
