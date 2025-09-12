# Project Context: Android App Template

## 1. High-Level Overview

This project is a comprehensive Android application template. It's designed to
serve as a foundation for new Android projects, incorporating a wide range of
best practices and popular libraries. The goal is to provide a solid,
well-structured starting point that saves developers time and effort when
beginning a new app.

## 2. Architecture

- **Pattern:** The template follows the principles of Clean Architecture,
  promoting a separation of concerns and a more maintainable codebase. It
  utilizes a Model-View-ViewModel (MVVM) pattern for the presentation layer.
- **UI:** The user interface is built entirely with Jetpack Compose, Google's
  modern toolkit for building native Android UI.
- **Asynchronicity:** Kotlin Coroutines and Flow are used for managing
  background threads and handling asynchronous operations, ensuring a responsive
  and efficient application.
- **Dependency Injection:** Hilt is integrated for dependency injection,
  simplifying the management of dependencies and improving testability.

## 3. Key Libraries & Frameworks

This template integrates a curated list of libraries that are widely used and
respected in the Android development community. For a complete and detailed list
of libraries, please refer to the `docs/libraries.md` file. Some of the key
libraries include:

- **UI:** Jetpack Compose, Material 3
- **Architecture:** AAC ViewModel, Repository pattern
- **Concurrency:** Kotlin Coroutines
- **Dependency Injection:** Hilt
- **Networking:** Retrofit, OkHttp
- **Local Storage:** SQLDelight, DataStore
- **Image Loading:** Coil
- **Testing:** JUnit, Compose Screenshot Testing

HEllo!!!

## 4. Module Structure

The project is organized into modules based on features and layers, following
the principles of modularization. This structure helps in separating concerns,
improving build times, and enabling code reuse. The primary modules are:

- `:app`: The main application module, which brings together all the features
  and dependencies.
- `:core:*`: A set of core modules that provide shared functionality, such as
  data handling, database access, and design system components.
- `:feature:*`: Feature modules that encapsulate specific features of the
  application.

## 5. Coding Conventions

- The codebase adheres to the official Kotlin style guide.
- Static analysis tools like Detekt and Ktlint are used to enforce code quality
  and consistency.
- The project uses a custom convention plugin for Spotless to ensure consistent
  formatting.

## 6. Available Shell Scripts

You have access to the following shell scripts that you can execute to gather
more information about the codebase.

- `python3 .gemini/tools/file_reader.py <relative_file_path>`: Use this to read
  the full content of a specific file.
- `python3 .gemini/tools/file_writer.py <relative_file_path> <content>`: Use
  this to write content to a specific file.
- `python3 .gemini/tools/gradle_inspector.py <module_name>`: Use this to inspect
  the resolved dependencies for a given module.
- `python3 .gemini/tools/project_lister.py <module_name>`: Use this to
  understand the file structure of a specific module (e.g., 'app', 'core:data').
