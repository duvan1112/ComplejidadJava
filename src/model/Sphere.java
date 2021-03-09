package model;

/**
 * @author duvan
 */
public class Sphere {
    private int size; //radio
    private Location location;
    private int speed; // m/s unicamente en x

    public Sphere(int size, Location location, int speed) {
        this.size = size;
        this.location = location;
        this.speed = speed;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "size=" + size +
                ", location=" + location +
                ", speed=" + speed +
                '}';
    }

    public int getSize() {
        return size;
    }

    public int getSpeed() {
        return speed;
    }

    public Location getLocation() {
        return location;
    }
}
