package demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class HotelServlet extends HttpServlet {
    private HotelDAO hotelDAO = new HotelDAO();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        BufferedReader reader = req.getReader();
        String s = "";
        while (reader.ready()) {
            s = s + reader.readLine();
        }
        try {
            Guest guest = objectMapper.readValue(s, Guest.class);
            out.println(hotelDAO.insert(guest));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PrintWriter out = resp.getWriter();
            String name = req.getParameter("firstName");
            out.println(hotelDAO.getGuest(name));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PrintWriter out = resp.getWriter();
            int roomNumber = Integer.parseInt(req.getParameter("roomNumber"));
            out.println(hotelDAO.deleteGuest(roomNumber));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
