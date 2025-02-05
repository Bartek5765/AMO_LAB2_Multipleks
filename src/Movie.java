public class Movie {
    private String title;
    private boolean is3D;
    public Movie(String title, boolean is3D) {
        this.title = title;
        this.is3D = is3D;
    }
    public String getTitle() {
        return title;
    }
    public boolean is3D() {
        return is3D;
    }

    @Override
    public String toString() {
        return title + (is3D ? " (3D)" : "");
    }
}