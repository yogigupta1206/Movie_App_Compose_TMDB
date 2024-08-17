# Atlys Android Assignment: Movie App

## Introduction

Welcome! This project showcases a movie app built as part of the Atlys Android Take-Home assignment. The app demonstrates essential functionalities using recommended Android development practices.

## Features

- Movie List Screen: Displays a dynamic list of trending movies fetched from the TMDB API.
- Search Functionality: Enables users to conveniently search for movies within the list.
- Movie Detail Screen: Provides detailed information about a selected movie, including title, overview, poster image, and more (depending on TMDB API data availability).
- Loading States: Displays a progress indicator while data is being fetched.
- Empty States: Informs the user when no search results are found or when the API returns an empty list.
- Error States: Handles network errors gracefully and displays an appropriate error message.

## Technical Stack

Programming Language: Kotlin
UI Framework: Jetpack Compose
Navigation: Compose Navigation
API: TMDB API (my free API key embedded)
Network Calls: Retrofit
Image Loading: Coil 
##Getting Started

### Clone the Repository:

```console
git clone https://github.com/yogigupta1206/Movie_App_Compose_TMDB.git
```

### Run the App:

- Open the project in Android Studio.
- Ensure you have the necessary dependencies set up (Retrofit, Glide, etc.).
- Run the app on an emulator or physical device.

## Demo
A video demonstrating the app's functionality is included in the repository (refer to the assets directory or a link provided).

[![YouTube](http://i.ytimg.com/vi/kGHCIV32i-k/hqdefault.jpg)](https://www.youtube.com/watch?v=kGHCIV32i-k)


### The app was developed with the following criteria in mind:

- Adherence to Android development best practices
- Clean, readable, and maintainable code
- Complete implementation of all features
- Clear commit history demonstrating development progress

### Potential Improvements Can Be Done:

- Maintaining String values in the strings.xml file
- Dimen can be maintained in the same way.
- Styles can be created instead of setting everything in the Text compose
- Pull to refresh can be introduced.
- Diff Util can be added
- Debounce in search can be used.
