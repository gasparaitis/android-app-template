# Project Context: MyAndroidApp

## 1. High-Level Overview

This is a native Android application for social media aggregation. The user can
connect different social media accounts and view a unified feed. The app is
written entirely in Kotlin.

## 2. Architecture

- **Pattern:** Model-View-ViewModel (MVVM) with a Repository layer.
- **UI:** Jetpack Compose exclusively. We do not use XML layouts.
- **Asynchronicity:** Kotlin Coroutines and Flow are used for all asynchronous
  operations.
- **Dependency Injection:** Hilt is used for dependency injection throughout the
  app. ViewModels are injected using `@HiltViewModel`.

## 3. Key Libraries & Frameworks

- **Jetpack Compose:** For the entire UI layer.
- **Compose Navigation:** For screen transitions.
- **ViewModel:** For UI-related business logic and state management.
- **Hilt:** For dependency injection.
- **Room:** For local database persistence.
- **Retrofit & OkHttp:** For networking with our REST API.
- **Coil:** For image loading in Composables.
- **JUnit 5 & MockK:** For unit testing.
- **Espresso & Compose Test Rules:** For UI testing.

## 4. Module Structure

- `:app`: The main application module, contains UI and feature logic.
- `:core:data`: Contains repositories, data sources, and network models (DTOs).
- `:core:database`: Contains Room database definitions and DAOs.
- `:core:ui`: Contains shared, reusable Jetpack Compose components.

## 5. Coding Conventions

- Follow the official Kotlin style guide.
- ViewModel state is exposed via a `StateFlow<UiState>`.
- Use sealed classes for representing UI state (e.g., `Loading`, `Success`,
  `Error`).
- All Composable functions that emit UI should be placed in files ending with
  `Screen.kt` or `Views.kt`.
- All public functions and classes must have KDoc documentation.

## 6. Available Shell Scripts

You have access to the following shell scripts that you can execute to gather
more information about the codebase.

- `python3 .gemini/tools/project_lister.py <module_name>`: Use this to
  understand the file structure of a specific module (e.g., 'app', 'core:data').
- `python3 .gemini/tools/file_reader.py <relative_file_path>`: Use this to read
  the full content of a specific file.
- `python3 .gemini/tools/gradle_inspector.py <module_name>`: Use this to inspect
  the resolved dependencies for a given module.

## 7. API Specification

The REST API contract is defined in the OpenAPI v3 spec. You can consult it for
endpoint paths, request bodies, and response DTOs.

- **Tool Command to Read:**
  `python .gemini/tools/file_reader.py .gemini/api_schema.json`
