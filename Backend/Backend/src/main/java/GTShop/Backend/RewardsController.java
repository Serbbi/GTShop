package GTShop.Backend;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class RewardsController {

    @Autowired
    private RewardRepository rewards;

    @GetMapping("api/rewards")
    public Map<String,List<Reward>> getRewards() {

        return Map.of("rewards",rewards.getAllRewards());
    }
    
}
