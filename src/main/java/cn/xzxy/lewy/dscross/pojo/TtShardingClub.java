package cn.xzxy.lewy.dscross.pojo;

public class TtShardingClub {
    private String clubId;

    private String name;

    private String nation;

    private String city;

    private Integer championTime;

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId == null ? null : clubId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Integer getChampionTime() {
        return championTime;
    }

    public void setChampionTime(Integer championTime) {
        this.championTime = championTime;
    }

    public TtShardingClub(String clubId, String name, String nation, String city, Integer championTime) {
        this.clubId = clubId;
        this.name = name;
        this.nation = nation;
        this.city = city;
        this.championTime = championTime;
    }
}