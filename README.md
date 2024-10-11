# TestAssignment for Android Developer Job

# Test Submitted by Mohsin Ikram

# Project Description
This app is a simple MVVM application developed in Kotlin using Kotlin, designed to facilitate user authentication and display medicine information. The following core features have been implemented:

# ore Features
a. User Authentication

A simple login screen allows users to log in without requiring any validation. Users can enter their username/email, and upon clicking the login button, they will be directed to the home screen.
# b. Greeting Message

After logging in, the app greets the user based on the time of day. A text box displays a personalized greeting message, along with the username/email entered during login. The greeting updates dynamically, reflecting "Good Morning," "Good Afternoon," or "Good Evening" based on the current hour.
# c. Medicine Display

On the home screen, the app fetches and displays a list of medicines, showcasing their name, dose, and strength. This data is retrieved from a JSON API (mocked using Mocky.io) and is presented in a scrollable lazy list, where each medicine is represented as a card. Tapping on a card navigates to a detailed view showing the same information.
# d. Room Database Integration

The app utilizes Room Database to locally store medicine data. This allows the application to cache information and reduces the need for repeated API calls. The data is saved and retrieved seamlessly using a Room DAO.
# f. Unit Testing

To ensure the robustness of the application, at least three unit tests have been implemented. These tests cover key use cases such as verifying data retrieval from the Room database and validating the correctness of greeting messages based on the time of day.

# MVVM App Architecture talks

Build a MVVM Android app architecture - Google I/O'19
Project Characteristics
This project makes use of the following tools and solutions:

100% Kotlin

Modern architecture (Clean Architecture, Model-View-ViewModel)

Dependency Injection

Testing (Unit)

Tech-stack

Jetpack
Lifecycle - perform an action when lifecycle state changes
ViewModel - store and manage UI-related data in a lifecycle conscious way
Data Binding - declaratively bind UI components in layouts to data sources.
Room - persistence
Architecture

Clean Architecture (at module level)
MVVM (presentation layer)
Android Architecture components
(ViewModel, LiveData)
Gradle

Gradle Kotlin DSL
Plugins (SafeArgs)
Architecture
Clean Architecture is the "core architecture" of this application. The main purpose of this approach is to achieve a separation of concerns which Clean architecture helps with and in making the code loosely coupled. This approach results in a more testable and flexible code.

Presentation: Layer with the Android Framework, the MVVM pattern and the DI module. Depends on domain to access the use cases and on DI, to inject dependencies. This is the layer closest to what the user sees on the screen.
MVVM: The Model View ViewModel pattern helps with the separation of concerns, dividing the user interface with the logic behind. It combines very well with Android Architecture Components like LiveData and DataBinding.
Domain: This is the core layer of the application with the business logic. It contains the use cases, in charge of calling the correct repository or data member.The domain layer is independent of any other layers.
Data: Layer with the responsibility of managing the application data and exposes these data sources as repositories to the domain layer. Typical responsibilities of this layer is to retrieve data from the internet and optionally cache this data locally.
Dependency Injection with Hilt
Dependency injection is closely related to two SOLID concepts: dependency inversion, which states that high level modules should not depend on low level modules, both should depend on abstractions; and single responsibility principle, which states that every class or module is responsible for just a single piece of functionality.


