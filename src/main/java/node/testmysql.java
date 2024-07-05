package node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

public class testmysql {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/devicedb?serverTimezone=GMT", "root", "884136445");

        String filename = "C:\\Users\\Administrator\\Desktop\\briefnot306.txt";

        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        System.out.println(br.readLine());
        System.out.println(br.readLine());
        while ((line = br.readLine()) != null) {
            String replace = line.replace("|", "");
            String deviceId = replace;
            insernotbrief306(deviceId,conn);
        }
        conn.close();
    }

    private static void insertbrief306(String deviceId, Connection conn) throws SQLException {
        String sql = "INSERT INTO brief306 (device_id) VALUES (?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, deviceId);
        stmt.executeUpdate();
        stmt.close();
    }
    private static void insernotbrief306(String deviceId, Connection conn) throws SQLException {
        String sql = "INSERT INTO briefnot306 (device_id) VALUES (?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, deviceId);
        stmt.executeUpdate();
        stmt.close();
    }


}
