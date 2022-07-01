package DataAccessObject;

import Tables.Monster;

import java.sql.*;
import java.util.ArrayList;

public class MonsterManage {
    Database db = new Database();
    public void insertMob(Monster monster) {
        String sql = "INSERT INTO `monster` (`mobName`, `mobHp`, `atkDamage`) VALUES (?, ?, ?)";
        PreparedStatement pstmt = null;
        Connection conn = db.Conn();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, monster.getMobName());
            pstmt.setInt(2, monster.getMobHp());
            pstmt.setInt(3, monster.getMobDmg());

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

    public ArrayList<Monster> selectMob() {
        ArrayList<Monster> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = db.Conn();  // db 연결 메소드

        try {
            String sql = "select * from monster";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Monster monster = new Monster();
                monster.setMobName(rs.getString("mobName"));
                monster.setMobHp(rs.getInt("mobHp"));
                monster.setMobDmg(rs.getInt("atkDamage"));
                list.add(monster);
                System.out.println("이름:" + monster.getMobName() + "/체력:" + monster.getMobHp() + "/데미지:" + monster.getMobDmg());
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

}
