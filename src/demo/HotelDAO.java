package demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.*;
import java.util.*;

public class HotelDAO {
    private JDBCConnection connection = new JDBCConnection();
    private Map<String, String> map = new HashMap<>();

    public String insert(Guest guest) throws Exception {
        Connection con = connection.getConnection();
        String sql = "select * from guests where room_number =" + guest.getRoomNumber();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            map.put("status", "error");
            map.put("message", "The room is occupied");
            String json = new ObjectMapper().writeValueAsString(map);
            return json;
        } else {
            String sql1 = "insert into guests(first_name, last_name, date_from, date_to, " +
                    "room_number) values(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql1);
            ps.setString(1, guest.getFirstName());
            ps.setString(2, guest.getLastName());
            ps.setDate(3, guest.getFrom());
            ps.setDate(4, guest.getTo());
            ps.setInt(5, guest.getRoomNumber());
            ps.executeUpdate();
            map.put("status", "ok");
            map.put("message", "guest is inserted");
            String json = new ObjectMapper().writeValueAsString(map);
            return json;
        }
    }


    public String getGuest(String name) throws Exception {
        List<Guest> guests = new ArrayList<>();
        Connection con = connection.getConnection();
        String sql = "select * from guests where first_name = \'" + name + "\'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Guest guest = new Guest();
            guest.setFirstName(rs.getString(1));
            guest.setLastName(rs.getString(2));
            guest.setFrom(rs.getDate(3));
            guest.setTo(rs.getDate(4));
            guest.setRoomNumber(rs.getInt(5));
            guests.add(guest);
        }
        String json = new ObjectMapper().writeValueAsString(guests);
        return json;
    }

    public String deleteGuest(int roomNumber) throws Exception {
        Connection con = connection.getConnection();
        String sql = "delete from guests where room_number = " + roomNumber;
        PreparedStatement ps = con.prepareStatement(sql);
        ps.executeUpdate();
        map.put("status", " delete");
        map.put("message", "The room is empty");
        return new ObjectMapper().writeValueAsString(map);
    }
}
