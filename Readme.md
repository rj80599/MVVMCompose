# MVVM Project

This project demonstrates a basic implementation of the Model-View-ViewModel (MVVM) architectural pattern using Jetpack Compose.

**Key Features:**

* **Clean Architecture:** Adheres to the MVVM pattern for better separation of concerns.
* **Jetpack Compose:** Utilizes modern declarative UI with Jetpack Compose.
* **Hilt:** Implements dependency injection with Hilt for easier maintainability.
* **Coroutines:** Leverages Kotlin Coroutines for asynchronous operations.
* **StateFlow:** Employs StateFlow for efficient and reactive data flow.
* **Retrofit:** Integrates with Retrofit for network requests.
* **Compose Navigation:** Enables navigation between different screens within the application.
* **Kotlin DSL:** Configures the build process using Kotlin DSL for better readability and maintainability.

**Project Structure:**

* **ui:** Contains all the composable functions for UI elements.
* **viewmodel:** Holds the ViewModel classes responsible for managing UI state and data.
* **repository:** Houses the data access layer, interacting with the network or local data sources.
* **model:** Defines data models for the application.
* **retrofit:** Contains interfaces for interacting with the API.
* **di:** Contains modules for dependency injection using Hilt.

**Getting Started:**

1. **Clone the repository:**
   ```bash
   git clone https://github.com/rj80599/MVVMCompose.git
