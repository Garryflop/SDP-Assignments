package Assignment5.com.cinema.ticketbook.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a movie screening in the cinema.
 * Immutable class following Clean Code principles.
 */
public class Movie {
    private final String movieId;
    private final String title;
    private final String genre;
    private final int durationMinutes;
    private final LocalDateTime showTime;
    private final int hallNumber;

    public Movie(String movieId, String title, String genre, int durationMinutes, 
                 LocalDateTime showTime, int hallNumber) {
        validateMovieData(movieId, title, durationMinutes, showTime, hallNumber);
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
        this.showTime = showTime;
        this.hallNumber = hallNumber;
    }

    private void validateMovieData(String movieId, String title, int durationMinutes,
                                   LocalDateTime showTime, int hallNumber) {
        if (movieId == null || movieId.trim().isEmpty()) {
            throw new IllegalArgumentException("Movie ID cannot be null or empty");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (durationMinutes <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }
        if (showTime == null) {
            throw new IllegalArgumentException("Show time cannot be null");
        }
        if (hallNumber <= 0) {
            throw new IllegalArgumentException("Hall number must be positive");
        }
    }

    public String getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public int getHallNumber() {
        return hallNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(movieId, movie.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId='" + movieId + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", durationMinutes=" + durationMinutes +
                ", showTime=" + showTime +
                ", hallNumber=" + hallNumber +
                '}';
    }
}
