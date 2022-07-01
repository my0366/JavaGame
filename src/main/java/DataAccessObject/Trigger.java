package DataAccessObject;

import DataAccessObject.Database;
import Tables.Monster;
import Tables.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Trigger {

    Database db = new Database();
    Player pc = new Player();
    Monster monster = new Monster();
    PlayerManage pm = new PlayerManage();
    public void attack(String playerName) {

        String sql = "UPDATE player set userHp = ?, atkDamage = ? where userName = ?";
        PreparedStatement pstmt = null;
        Connection conn = db.Conn();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pc.getUserHp());
            pstmt.setInt(2, pc.getAtkDamage());
            pstmt.setString(3,playerName);
            int hp = pc.getUserHp();
            int mobDmg = monster.getMobDmg();
            hp = hp - mobDmg;
            System.out.println("남은 체력 : " + hp);
            pm.updatePlayer(playerName);

            int result = pstmt.executeUpdate();
            if (result == 1) {
                System.out.println("전투중");
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
