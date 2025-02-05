import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Tworzymy kino
        Cinema cinema1 = new Cinema("Multikino", "ul. Dobrego Pasterza 128");

        //Tworzymy salę
        List<String> room1Seats = Arrays.asList("H34", "H35", "H36", "H37", "H38");
        ScreeningRoom room1 = new ScreeningRoom("Sala 1", room1Seats);

        //Tworzymy filmy
        Movie movie1 = new Movie("Brutalist", false);
        Movie movie2 = new Movie("Sztuka pięknego życia", true);

        //Tworzymy seanse
        Screening screening1 = new Screening(movie1, LocalDateTime.now().plusDays(1).withHour(18).withMinute(0), room1, ScreeningType.STANDARD);
        Screening screening2 = new Screening(movie2, LocalDateTime.now().plusDays(2).withHour(20).withMinute(30), room1, ScreeningType.THREE_D);
        Screening screening3 = new Screening(movie1, LocalDateTime.now().plusDays(3).withHour(21).withMinute(0), room1, ScreeningType.VIP);

        //Dodajemy seanse do kina
        cinema1.addScreening(screening1);
        cinema1.addScreening(screening2);
        cinema1.addScreening(screening3);

        //Wyświetlamy repertuar
        cinema1.printProgramme();

        //Rezerwacja miejsc po id
        Screening firstScreening = cinema1.getScreenings()[0];
        firstScreening.reservePlaces("H34", "H35", "H36");

        //Rezerwacja przy użyciu Seat
        Seat seat1 = firstScreening.getSeat("H37");
        Seat seat2 = firstScreening.getSeat("H38");
        firstScreening.reservePlaces(seat1, seat2);

        //Rezerwacja dla klienta
        Customer customer = new Customer("Bartek Przęczek");
        firstScreening.reservePlaces(customer, "H34");
        screening2.reservePlaces(customer, "H34");

        //Zakup biletów
        Ticket ticket1 = firstScreening.purchaseTicket("H37");
        List<Ticket> tickets = screening2.purchaseTickets(customer, "H34", "H35");

        //Sprawdzenie biletów klienta
        customer.printTickets();

        //Wyszukiwanie filmu
        Movie foundMovie = cinema1.findMovie("Brutalist");
        if (foundMovie != null) {
            System.out.println("Znaleziono film: " + foundMovie);
        }
    }
}
