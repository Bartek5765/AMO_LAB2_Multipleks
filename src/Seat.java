public class Seat {
    private String id;
    private SeatStatus status;
    private Customer reservedBy;

    public Seat(String id) {
        this.id = id;
        this.status = SeatStatus.AVAILABLE;
    }
    public String getId() {
        return id;
    }
    public SeatStatus getStatus() {
        return status;
    }
    public void setStatus(SeatStatus status) {
        this.status = status;
    }
    public Customer getReservedBy() {
        return reservedBy;
    }
    public void setReservedBy(Customer reservedBy) {
        this.reservedBy = reservedBy;
    }

    @Override
    public String toString() {
        return "Seat " + id + " [" + status + "]";
    }
}