package DataAccessObject;

import java.sql.*;


public class Database {

    public Connection Conn() {
        final String driver = "org.mariadb.jdbc.Driver";
        final String DB_IP = "localhost";
        final String DB_PORT = "3306";
        final String DB_NAME = "game";
        final String DB_USER = "root";
        final String DB_PASS = "1234";
        final String DB_URL =
                "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;

        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            if (conn != null) {
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로드 실패");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }
        return conn;
    }


    public void user(String playerName) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = Conn();   // db 연결 메소드
        try {
            String sql = "select * from player where userName = ?";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, playerName);

        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
