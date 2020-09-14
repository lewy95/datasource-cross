package cn.xzxy.lewy.dscross.pojo;

public class TtSharding {
    private Integer id;

    private String phone;

    private String backOne;

    private String backTwo;

    private String backThree;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getBackOne() {
        return backOne;
    }

    public void setBackOne(String backOne) {
        this.backOne = backOne == null ? null : backOne.trim();
    }

    public String getBackTwo() {
        return backTwo;
    }

    public void setBackTwo(String backTwo) {
        this.backTwo = backTwo == null ? null : backTwo.trim();
    }

    public String getBackThree() {
        return backThree;
    }

    public void setBackThree(String backThree) {
        this.backThree = backThree == null ? null : backThree.trim();
    }

    public TtSharding(Integer id, String phone, String backOne, String backTwo, String backThree) {
        this.id = id;
        this.phone = phone;
        this.backOne = backOne;
        this.backTwo = backTwo;
        this.backThree = backThree;
    }
}