package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DisplayData {
    public static void main(String[] args) {
        try {
            // 1. เชื่อมต่อฐานข้อมูล
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project93", "root", "Ss292546");

            // 2. สร้าง statement
            Statement stmt = conn.createStatement();

            // 3. สร้างและ execute query
            String query = "SELECT * FROM queuedata";
            ResultSet rs = stmt.executeQuery(query);

            // 4. แสดงข้อมูล
            while (rs.next()) {
            	String id = rs.getString("queueID");
                String name = rs.getString("name");
                int age = rs.getInt("numberOfPeople");
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }

            // 5. ปิดการเชื่อมต่อ
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
