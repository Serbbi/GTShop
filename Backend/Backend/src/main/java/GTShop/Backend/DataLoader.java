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
        rewardRepository.save(new Reward( "Boost XP Rush",
            "Vrei să urci rapid în clasament? Activează acest boost înainte de următorul Rush sau Workout și fiecare punct câștigat se va dubla automat. Ideal pentru săptămânile în care vrei să recuperezi sau să te distanțezi de ceilalți în clasament. Atenție: efectul se aplică o singură dată, așa că folosește-l cu grijă!",
             100, "images/img_bg1.png", "During", true, 10));
        rewardRepository.save(new Reward( "Life Saver Pass",
            "Ai pierdut un Rush sau nu ai trimis tema la timp? Fără stres. Acest pass îți salvează o viață și îți permite să rămâi în competiție fără penalizări. Este rar și valoros, așa că nu-l irosi pe situații minore. Cu cât îl păstrezi pentru un moment critic, cu atât îți poate schimba jocul.",
             200, "images/img_bg1.png", "During", true, 10));
        rewardRepository.save(new Reward("Time Extender",
            "Nu-ți ajung orele din zi? Activează acest item și primești timp suplimentar pentru a finaliza un Workout după deadline. Perfect pentru zilele aglomerate în care vrei să livrezi calitate, nu grabă. Disponibil în stoc extrem de limitat – rezervă-l pentru provocările cu adevărat importante.",
             300, "images/img_bg1.png", "During", true, 10));
        rewardRepository.save(new Reward( "Return Ticket 25",
            "Biletul tău de întoarcere în aventura Generația Tech, cu o reducere de 25% la următoarea ediție. Perfect pentru exploratorii care știu că misiunea nu se încheie niciodată.",
             400, "images/img_bg1.png", "After", true, 10));
        rewardRepository.save(new Reward( "Return Ticket 50",
            "Revenirea ta în universul digital e acum la jumătate de preț. 50% reducere la următoarea ediție – pentru eroii care au demonstrat că pot.",
             500, "images/img_bg1.png", "After", true, 10));
        rewardRepository.save(new Reward( "Tee of Triumph",
            "Tricoul oficial al campionilor Generația Tech. Moale, comod și marcat cu însemnele celor care au finalizat misiunea cu succes.",
             600, "images/img_bg1.png", "After", true, 10));
        rewardRepository.save(new Reward( "Badge of Bravery",
            "Insigna care vorbește pentru tine. Simbol al curajului, al implicării și al pasiunii tale pentru învățare. Poart-o și inspiră-i pe ceilalți.",
             700, "images/img_bg1.png", "After", true, 10));
        rewardRepository.save(new Reward( "Hoodie of Honor",
            "Hanoracul călduros al exploratorilor Generația Tech. Un trofeu purtabil care îți amintește că ai dus misiunea până la capăt și ai învins.",
       800, "images/img_bg1.png", "After", true, 10));
        rewardRepository.save(new Reward( "Digital Detox Kit",
            "Un pachet care-ți reamintește să iei o pauză reală după o sesiune intensivă: lumânare aromată, o mini-agendă pentru gânduri zen și un playlist exclusiv anti-stres. Pentru că și mintea ta are nevoie de upgrade.",
           900, "images/img_bg1.png", "After", true, 10));
        rewardRepository.save(new Reward( "Productivity Power-Up App",
            "O aplicație customizată, numai pentru absolvenți, cu tool-uri smart pentru organizare, notificări motivaționale și mini-challenge-uri zilnice ca să nu cazi în rutina de după curs.",
             1000, "images/img_bg1.png", "After", true, 10));
        rewardRepository.save(new Reward( "Focus Booster Headphones",
            "Căști wireless cu sunete ambientale pentru concentrare maximă și relaxare.",
            1100, "images/img_bg1.png", "After", true, 10));
        rewardRepository.save(new Reward( "Growth Seed Pack",
            "Un pachet cu semințe rare de plante care simbolizează creșterea personală. Cultivă-le în paralel cu evoluția ta — o metaforă vizuală care să-ți aducă motivare și răbdare.",
            1200, "images/img_bg1.png", "After", true, 10));
        rewardRepository.save(new Reward( "Shortcut Sensei",
            "Dispozitivul dedicat profesioniștilor care prioritizează eficiența, echipat cu butoane programabile personalizabile pentru fluxuri de lucru accelerate. Acest mouse redefinește rapiditatea prin integrarea controlului avansat într-un design compact și ergonomic, optim pentru multitasking la cel mai înalt nivel.",
           1300, "images/img_bg1.png", "After", true,10));
    }
}