import java.util.List;

public class ScreeningRoom {
    private String name;
    private List<String> seatIds;

    public ScreeningRoom(String name, List<String> seatIds) {
        this.name = name;
        this.seatIds = seatIds;
    }
    public String getName() {
        return name;
    }
    public List<String> getSeatIds() {
        return seatIds;
    }
}
