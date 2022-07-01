package DataAccessObject;

import Tables.Item;

import java.sql.*;
import java.util.ArrayList;


public class ItemManage {

    Database db = new Database();
    public void insertItem(Item item) {

        String sql = "INSERT INTO `item` (`itemName`, `atkDamage`) VALUES (?, ?)";
        PreparedStatement pstmt = null;
        Connection conn = db.Conn();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, item.getItemName());
            pstmt.setInt(2, item.getAtkDamage());

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

    public ArrayList<Item> selectItem() {
        ArrayList<Item> list = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = db.Conn();  // db 연결 메소드

        try {
            String sql = "select * from item";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Item item = new Item();
                item.setItemName(rs.getString("itemName"));
                item.setAtkDamage(rs.getInt("atkDamage"));
                list.add(item);
                System.out.println("이름:" + item.getItemName() + "/데미지:" + item.getAtkDamage());
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

