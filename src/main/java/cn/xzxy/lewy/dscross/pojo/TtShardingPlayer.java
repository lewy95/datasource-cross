package cn.xzxy.lewy.dscross.pojo;

public class TtShardingPlayer {
    private String playerId;

    private String name;

    private Integer age;

    private Integer number;

    private String nation;

    private String position;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId == null ? null : playerId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public TtShardingPlayer(String playerId, String name, Integer age, Integer number, String nation, String position) {
        this.playerId = playerId;
        this.name = name;
        this.age = age;
        this.number = number;
        this.nation = nation;
        this.position = position;
    }

}