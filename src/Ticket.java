import java.time.LocalDateTime;

public class Ticket {
    private Screening screening;
    private Seat seat;
    private Customer customer;
    private LocalDateTime purchaseTime;

    public Ticket(Screening screening, Seat seat, Customer customer) {
        this.screening = screening;
        this.seat = seat;
        this.customer = customer;
        this.purchaseTime = LocalDateTime.now();
    }
    public Screening getScreening() {
        return screening;
    }
    public Seat getSeat() {
        return seat;
    }
    public Customer getCustomer() {
        return customer;
    }
    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    @Override
    public String toString() {
        return  "Bilet na \"" + screening.getMovie().getTitle() + "\" (" + screening.getScreeningTime() + "), miejsce: " + seat.getId() +
                (customer != null ? ", klient: " + customer.getName() : "");
    }

}
