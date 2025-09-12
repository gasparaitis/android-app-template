# gradle_inspector.py
import subprocess
import argparse

def get_dependencies(module):
    """Runs the Gradle 'dependencies' task for a specific module."""
    if ":" in module:
        task = f"{module}:dependencies"
    else:
        task = f":{module}:dependencies"

    command = ["./gradlew", task]
    try:
        result = subprocess.run(command, capture_output=True, text=True, check=True)
        # Filter for compile classpath dependencies for brevity
        lines = result.stdout.splitlines()
        filtered_lines = [line for line in lines if "CompileClasspath" in line or "+---" in line or "\---" in line]
        print("\n".join(filtered_lines[-50:])) # Return last 50 lines to not overwhelm the context
    except subprocess.CalledProcessError as e:
        print(f"Error running Gradle task: {e}")
        print(e.stderr)

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description="Inspect Gradle dependencies for a module.")
    parser.add_argument("module", type=str, help="The name of the module (e.g., 'app').")
    args = parser.parse_args()
    get_dependencies(args.module)
