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
        rewardRepository.save(new Reward("r1", "Case în copac Gama SylvNest", "description1", "fullDescription1", 100, "/assets/images/Muntele-cu-premii/premiu1.jpg", "Muntele cu premii", true, 10));
        rewardRepository.save(new Reward("r2", "Mobilier Gama FloraForm", "description2", "fullDescription2", 200, "/assets/images/Muntele-cu-premii/premiu2.jpg", "Muntele cu premii", true, 10));
        rewardRepository.save(new Reward("r3", "Oglinda MirrorMind", "description3", "fullDescription3", 300, "/assets/images/Muntele-cu-premii/premiu3.jpg", "Muntele cu premii", true, 10));
        rewardRepository.save(new Reward("r4", "Bicicleta ChronoRider sau ChronoMix", "description4", "fullDescription4", 400, "/assets/images/Muntele-cu-premii/premiu4.jpg", "Muntele cu premii", true, 10));
        rewardRepository.save(new Reward("r5", "Scooter-ul TurmoVolt", "description5", "fullDescription5", 500, "/assets/images/Muntele-cu-premii/premiu5.jpg", "Muntele cu premii", true, 10));
        rewardRepository.save(new Reward("r6", "Flyboard-ul SkyRace", "description6", "fullDescription6", 600, "/assets/images/Muntele-cu-premii/premiu6.jpg", "Muntele cu premii", true, 10));
        rewardRepository.save(new Reward("r7", "Parașuta WaveCloud", "description7", "fullDescription7", 700, "/assets/images/Muntele-cu-premii/premiu7.jpg", "Muntele cu premii", true, 10));
        rewardRepository.save(new Reward("r8", "Caiacul Algomotion", "description8", "fullDescription8", 800, "/assets/images/Muntele-cu-premii/premiu8.jpg", "Muntele cu premii", true, 10));
        rewardRepository.save(new Reward("r9", "Placa de surf NauticCell", "description9", "fullDescription9", 900, "/assets/images/Muntele-cu-premii/premiu9.jpg", "Muntele cu premii", true, 10));
        rewardRepository.save(new Reward("r10", "Lanterna GeoRay sau MountainLight", "description10", "fullDescription10", 1000, "/assets/images/Muntele-cu-premii/premiu10.jpg", "Muntele cu premii", true, 10));
        rewardRepository.save(new Reward("r11", "Busola WildPass", "description11", "fullDescription11", 1100, "/assets/images/Muntele-cu-premii/premiu11.jpg", "Muntele cu premii", true, 10));
        rewardRepository.save(new Reward("r12", "Dispozitivul & Aplicația EchoPhonic", "description12", "fullDescription12", 1200, "/assets/images/Muntele-cu-premii/premiu12.jpg", "Muntele cu premii", true, 10));
    }
}
