package Tables;

public class Player {

    private String userName;
    private int userHp;
    private int atkDamage;

    public String getEquip() {
        return equip;
    }

    public void setEquip(String equip) {
        this.equip = equip;
    }

    private String equip;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserHp() {
        return userHp;
    }

    public void setUserHp(int userHp) {
        this.userHp = userHp;
    }

    public int getAtkDamage() {
        return atkDamage;
    }

    public void setAtkDamage(int atkDamage) {
        this.atkDamage = atkDamage;
    }

    public boolean playerDie() {
        if (userHp <= 0) {
            System.out.println("사망");
            return true;
        } else {
            System.out.println("진행");
            return false;
        }
    }
}
