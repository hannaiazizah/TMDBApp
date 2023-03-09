# Movie App
This is a mobile native application that uses the API from https://api.themoviedb.org to show movies.

## User Stories
- As a user, I want to see the list of official genres for movies.
- As a user, I want to discover movies by genre.
- As a user, I want to see the primary information about a movie when I click on it.
- As a user, I want to see reviews of a movie by other users.
- As a user, I want to see the youtube trailer of a movie.
- As a user, I want to have endless scrolling on the list of movies and users reviews.

## Features
### List of official genres
On the home screen of the application, users can see the list of official genres for movies. By clicking on a genre, users are taken to the screen that shows the list of movies in that genre.

### Discover movies by genre
On the genre screen, users can see a list of movies in that genre. Movies are displayed in a grid format with their title, rating, and poster image. Users can also scroll down to see more movies in that genre.

### Primary information about a movie
When users click on a movie from the list, they are taken to a screen that shows primary information about the movie, including its title, overview, and release date. Users can also see the youtube trailer of the movie from this screen.

### Users review for a movie
On the same screen that shows primary information about the movie, users can also see reviews of the movie by other users. Users can scroll down to see more reviews.

### Endless scrolling
Users can scroll down to see more movies or reviews on the list of movies and users reviews.

## Technologies
- Kotlin
- Jetpack Navigation
- Coroutine Flow
- Paging3

## Installation
- Clone the repository:
  ```bash
  git@github.com:hannaiazizah/TextRecognition.git
  ```
- Import the project into Android Studio.
- Run the application

## Credits
This application was developed by Hanna Izma Azizah. It uses the Themoviedb API to get data about movies.