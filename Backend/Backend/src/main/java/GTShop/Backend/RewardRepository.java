package GTShop.Backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RewardRepository {

    private List<Reward> rewards=new ArrayList<>();

    public RewardRepository(){
        rewards.add(new Reward("id1", "name1", "description1", "fullDescription1",100, "image1", "category", true, 0));
        rewards.add(new Reward("id2", "name2", "description2", "fullDescription2",200, "image2", "category", true, 0));
        rewards.add(new Reward("id3", "name3", "description3", "fullDescription3",300, "image3", "category", true, 0));
        rewards.add(new Reward("id4", "name4", "description4", "fullDescription4",400, "image4", "category", true, 0));
        rewards.add(new Reward("id5", "name5", "description5", "fullDescription5",500, "image5", "category", true, 0));
    }

    public List<Reward> getAllRewards(){
        return rewards;
    }

    public Reward findById(String id){
        for(var r:rewards){
            if(r.getId().equals(id)){
                return r;
            }
        }
        return null;
    }
}
