package GTShop.Backend;

public class UserHistory {

    private Long id;
    private String rewardName;
    private Integer pointsSpent;
    private String status;

    public UserHistory(Long id, String rewardName, Integer pointsSpent, String status){
        this.id=id;
        this.rewardName=rewardName;
        this.pointsSpent=pointsSpent;
        this.status=status;
    }

    public Long getId() {
        return id;
    }

    public String getRewardName() {
        return rewardName;
    }

    public Integer getPointsSpent() {
        return pointsSpent;
    }

    public String getStatus() {
        return status;
    }

}
