import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private String name;
    private String address;
    private List<Screening> screenings;
    private List<Movie> movies;

    public Cinema(String name, String address) {
        this.name = name;
        this.address = address;
        this.screenings = new ArrayList<>();
        this.movies = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public void addScreening(Screening screening) {
        screenings.add(screening);
        if (!movies.contains(screening.getMovie())) {
            movies.add(screening.getMovie());
        }
    }

    public Screening[] getScreenings() {
        return screenings.toArray(new Screening[0]);
    }

    public void printProgramme() {
        System.out.println("Repertuar kina: " + name);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime weekLater = now.plusDays(7);
        for (Screening screening : screenings) {
            if (screening.getScreeningTime().isAfter(now) && screening.getScreeningTime().isBefore(weekLater)) {
                System.out.println(screening);
            }
        }
    }

    public Movie findMovie(String title) {
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(title.toLowerCase())) {
                return movie;
            }
        }
        System.out.println("Nie znaleziono filmu zawierającego: " + title);
        return null;
    }
}
