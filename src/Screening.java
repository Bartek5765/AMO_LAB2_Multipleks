import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Screening {
    private Movie movie;
    private LocalDateTime screeningTime;
    private ScreeningRoom room;
    private ScreeningType type;
    private Map<String, Seat> seats;

    public Screening(Movie movie, LocalDateTime screeningTime, ScreeningRoom room, ScreeningType type) {
        this.movie = movie;
        this.screeningTime = screeningTime;
        this.room = room;
        this.type = type;
        this.seats = new HashMap<>();
        for (String seatId : room.getSeatIds()) {
            seats.put(seatId, new Seat(seatId));
        }
    }
    public Movie getMovie() {
        return movie;
    }
    public LocalDateTime getScreeningTime() {
        return screeningTime;
    }
    public ScreeningRoom getRoom() {
        return room;
    }
    public ScreeningType getType() {
        return type;
    }
    public Collection<Seat> getSeats() {
        return seats.values();
    }
    public Seat getSeat(String seatId) {
        return seats.get(seatId);
    }
    public void reservePlaces(String... seatIds) {
        for (String seatId : seatIds) {
            reserveSeat(null, seatId);
        }
    }
    public void reservePlaces(Customer customer, String... seatIds) {
        for (String seatId : seatIds) {
            reserveSeat(customer, seatId);
        }
    }
    public void reservePlaces(Seat... seatObjs) {
        for (Seat seat : seatObjs) {
            reserveSeat(null, seat.getId());
        }
    }
    private void reserveSeat(Customer customer, String seatId) {
        Seat seat = seats.get(seatId);
        if (seat == null) {
            System.out.println("Miejsce " + seatId + " nie istnieje.");
            return;
        }
        if (seat.getStatus() != SeatStatus.AVAILABLE) {
            System.out.println("Miejsce " + seatId + " nie jest dostępne do rezerwacji.");
            return;
        }
        seat.setStatus(SeatStatus.RESERVED);
        System.out.println("Miejsce " + seatId + " zostało zarezerwowane" +
                (customer != null ? " dla " + customer.getName() : "") + ".");
    }
    public Ticket purchaseTicket(String seatId) {
        return purchaseTicket(null, seatId);
    }
    public Ticket purchaseTicket(Customer customer, String seatId) {
        Seat seat = seats.get(seatId);
        if (seat == null) {
            System.out.println("Miejsce " + seatId + " nie istnieje.");
            return null;
        }
        if (seat.getStatus() == SeatStatus.SOLD) {
            System.out.println("Miejsce " + seatId + " zostało już sprzedane.");
            return null;
        }
        if (seat.getStatus() == SeatStatus.RESERVED && seat.getReservedBy() != null && customer != null && !seat.getReservedBy().equals(customer)) {
            System.out.println("Miejsce " + seatId + " jest zarezerwowane przez innego klienta.");
            return null;
        }
        seat.setStatus(SeatStatus.SOLD);
        seat.setReservedBy(customer);
        Ticket ticket = new Ticket(this, seat, customer);
        System.out.println("Bilet zakupiony na miejsce " + seatId +
        (customer != null ? " przez " + customer.getName() : "") + ".");
        if (customer != null) {
            customer.addTicket(ticket);
        }
        return ticket;
    }
    public List<Ticket> purchaseTickets(String... seatIds) {
        return purchaseTickets(null, seatIds);
    }
    public List<Ticket> purchaseTickets(Customer customer, String... seatIds) {
        List<Ticket> tickets = new ArrayList<>();
        for (String seatId : seatIds) {
            Ticket t = purchaseTicket(customer, seatId);
            if (t != null) {
                tickets.add(t);
            }
        }
        return tickets;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return movie.toString() + " o " + screeningTime.format(formatter) + " w sali " + room.getName() + " (" + type + ")";
    }
}
