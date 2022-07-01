import DataAccessObject.*;
import Tables.Item;
import Tables.Monster;
import Tables.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayGround {

    //게임시작
    //1. 게임설정 / 2. 게임시작 / 3.정보 출력
    //캐릭터 정보 설정(저장)
    //몬스터 정보 입력(저장)
    //아이템 정보 입력(저장)

    //2. 정보가 있으면 시작
    //3. 정보 출력(DB 존재시)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Player player = new Player();
        Monster monster = new Monster();
        Item item = new Item();
        Trigger trig = new Trigger();

        MonsterManage mm = new MonsterManage();
        PlayerManage pm = new PlayerManage();
        ItemManage im = new ItemManage();
        Database db = new Database();

        System.out.println("게임 시작");
        System.out.println("1. 게임설정 / 2. 게임시작 / 3.정보 출력");
        int num = sc.nextInt();

        if (num == 1) {
            System.out.println("게임설정");
            System.out.println("1. 캐릭터 정보 입력");
            System.out.println("캐릭터 이름 : ");
            String userName = sc.next();
            System.out.println("캐릭터 체력: ");
            int userHp = sc.nextInt();
            System.out.println("캐릭터 공격력 : ");
            int atkDamage = sc.nextInt();
            player.setUserName(userName);
            player.setUserHp(userHp);
            player.setAtkDamage(atkDamage);
            pm.insertPlayer(player);
            pm.selectPlayer();

            System.out.println("2. 몬스터 정보 입력");
            System.out.println("몬스터 이름 : ");
            String mobName = sc.next();
            System.out.println("몬스터 체력 : ");
            int mobHp = sc.nextInt();
            System.out.println("몬스터 공격력");
            int mobDmg = sc.nextInt();
            monster.setMobName(mobName);
            monster.setMobHp(mobHp);
            monster.setMobDmg(mobDmg);
            mm.insertMob(monster);
            mm.selectMob();

            System.out.println("3.아이템 정보 입력:");
            System.out.println("아이템 이름 : ");
            String itemName = sc.next();
            System.out.println("아이템 공격력 :");
            int itemDmg = sc.nextInt();
            item.setItemName(itemName);
            item.setAtkDamage(itemDmg);
            im.insertItem(item);
            im.selectItem();

        } else if (num == 2) {
            ArrayList<Item> items = im.selectItem();
            if (items.isEmpty()) {
                System.out.println("오류");
            } else {
                System.out.println("게임시작성공");
                System.out.println("플레이 할 유저 선택 : ");
                String playerName = sc.next();
                db.user(playerName);
                System.out.println("장착할 장비 :");
                String weapon = sc.next();
                pm.updatePlayer(weapon);
                System.out.println("전투 시작");
                trig.attack(playerName);
                if (player.playerDie()) {
                    pm.playerDie(playerName);
                }
            }
        } else if (num == 3) {
            System.out.println("1. 캐릭터 정보/2. 몬스터 정보/3.아이템 정보");
            int select = sc.nextInt();
            if (select == 1) {
                pm.selectPlayer();
            } else if (select == 2) {
                mm.selectMob();
            } else if (select == 3) {
                im.selectItem();
            }
        } else {
            System.out.println("게임종료");
        }
    }

}

