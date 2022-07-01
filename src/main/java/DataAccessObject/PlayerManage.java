package DataAccessObject;

import Tables.Item;
import Tables.Player;

import java.sql.*;
import java.util.ArrayList;

public class PlayerManage {
    Database db = new Database();
    Player pc = new Player();
    public void insertPlayer(Player player) {
        String sql = "INSERT INTO `player` (`userName`, `userHp`, `atkDamage`) VALUES (?, ?, ?)";
        PreparedStatement pstmt = null;
        Connection conn = db.Conn();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, player.getUserName());
            pstmt.setInt(2, player.getUserHp());
            pstmt.setInt(3, player.getAtkDamage());
            int result = pstmt.executeUpdate();
            if (result == 1) {
                System.out.println("데이터 삽입 성공!");
            }

        } catch (Exception e) {
            System.out.println("데이터 삽입 실패!");
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public ArrayList<Player> selectPlayer() {
        ArrayList<Player> list= new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = db.Conn();   // db 연결 메소드
        try {
            String sql = "select * from player";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Player player = new Player();
                player.setEquip(rs.getString("equip"));
                player.setUserName(rs.getString("userName"));
                player.setUserHp(rs.getInt("userHp"));
                player.setAtkDamage(rs.getInt("atkDamage"));
                list.add(player);

                System.out.println("이름:" + player.getUserName() + "/속성:" + player.getUserHp() + "/데미지:" + player.getAtkDamage() + "/무기" + player.getEquip());
            }
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
        return list;
    }

    public void updatePlayer(String playerName) {

        String sql = "UPDATE player set userHp = ?, equip = ?, atkDamage = ? where userName = ?";
        PreparedStatement pstmt = null;
        Connection conn = db.Conn();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pc.getUserHp());
            pstmt.setString(2, pc.getEquip());
            pstmt.setInt(3, pc.getAtkDamage());
            pstmt.setString(4,playerName);

            int result = pstmt.executeUpdate();



            if (result == 1) {
                System.out.println("데이터 삽입 성공!");
            }

        } catch (Exception e) {
            System.out.println("데이터 삽입 실패!");
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }


    public void playerDie(String playerName) {

        String sql = "DELETE from player where userName = ?";
        PreparedStatement pstmt = null;
        Connection conn = db.Conn();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,playerName);

            int result = pstmt.executeUpdate();

            if (result == 1) {
                System.out.println("데이터 삽입 성공!");
            }

        } catch (Exception e) {
            System.out.println("데이터 삽입 실패!");
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
