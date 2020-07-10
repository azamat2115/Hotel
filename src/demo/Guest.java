package demo;

import java.sql.Date;

public class Guest {
    private String firstName;
    private String lastName;
    private Date from;
    private Date to;
    private int roomNumber;

    public String getFirstName() {
        return firstName;
    }

    public Guest setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Guest setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Date getFrom() {
        return from;
    }

    public Guest setFrom(Date from) {
        this.from = from;
        return this;
    }

    public Date getTo() {
        return to;
    }

    public Guest setTo(Date to) {
        this.to = to;
        return this;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public Guest setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", roomNumber=" + roomNumber +
                '}';
    }
}
