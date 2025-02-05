import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Ticket> tickets;

    public Customer(String name) {
        this.name = name;
        this.tickets = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public List<Ticket> getTickets() {
        return tickets;
    }
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
    public void printTickets() {
        System.out.println("Bilety klienta " + name + ":");
        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
