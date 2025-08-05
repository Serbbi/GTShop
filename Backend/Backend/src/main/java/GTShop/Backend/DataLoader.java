package GTShop.Backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RewardRepository rewardRepository;

    @Override
    public void run(String... args) throws Exception {
        rewardRepository.save(new Reward("r1", "Case în copac Gama SylvNest", "description1", "fullDescription1", 100, "images/img1.jpeg", "Muntele cu premii", true, 10));
        rewardRepository.save(new Reward("r2", "Mobilier Gama FloraForm", "description2", "fullDescription2", 200, "images/img2.jpeg", "Muntele cu premii", true, 10));
        rewardRepository.save(new Reward("r3", "Oglinda MirrorMind", "description3", "fullDescription3", 300, "images/img3.jpeg", "Muntele cu premii", true, 10));
        rewardRepository.save(new Reward("r4", "Bicicleta ChronoRider sau ChronoMix", "description4", "fullDescription4", 400, "images/img4.jpeg", "Muntele cu premii", true, 10));
        rewardRepository.save(new Reward("r5", "Scooter-ul TurmoVolt", "description5", "fullDescription5", 500, "images/img5.jpeg", "Muntele cu premii", true, 10));
        rewardRepository.save(new Reward("r6", "Flyboard-ul SkyRace", "description6", "fullDescription6", 600, "images/img6.jpeg", "Muntele cu premii", true, 10));
        rewardRepository.save(new Reward("r7", "Parașuta WaveCloud", "description7", "fullDescription7", 700, "images/img7.jpeg", "Muntele cu premii", true, 10));
        rewardRepository.save(new Reward("r8", "Caiacul Algomotion", "description8", "fullDescription8", 800, "images/img8.jpeg", "Muntele cu premii", true, 10));
        rewardRepository.save(new Reward("r9", "Placa de surf NauticCell", "description9", "fullDescription9", 900, "images/img9.jpeg", "Muntele cu premii", true, 10));
        rewardRepository.save(new Reward("r10", "Lanterna GeoRay sau MountainLight", "description10", "fullDescription10", 1000, "images/img10.jpeg", "ABCDEF", true, 10));
        rewardRepository.save(new Reward("r11", "Busola WildPass", "description11", "fullDescription11", 1100, "images/img11.jpeg", "Muntele cu premii", true, 10));
        rewardRepository.save(new Reward("r12", "Dispozitivul & Aplicația EchoPhonic", "description12", "fullDescription12", 1200, "images/img12.jpeg", "Muntele cu premii", true, 10));
    }
}
