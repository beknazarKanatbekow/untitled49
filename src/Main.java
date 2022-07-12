import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence;

    public static int[] heroesHealth = {270, 260, 250, 250, 320, 250, 220, 240};
    public static int[] heroesDamage = {10, 20, 30, 0, 5, 15, 10, 15};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Golem", "Berserk", "Lucky", "Thor"};
    public static int roundNumber;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++;
        chooseDefence();
        bossHits();
        heroesHit();
        printStatistics();
        medic();
        Lucky();
        Berserk();
        Thour();
        Golem();
    }

    public static void chooseDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss chose: " + bossDefence);
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; //2,3,4,5,6,7,8,9,10
                    int critical_damage = coeff * heroesDamage[i];
                    if (bossHealth - critical_damage < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - critical_damage;
                    }
                    System.out.println("Critical damage: " + critical_damage + " [" + coeff + "]");
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        if (roundNumber == 0) {
            System.out.println("BEFORE GAME STARTED ______________");
        } else {
            System.out.println(roundNumber + " ROUND ______________");
        }
        System.out.println("Boss health: " + bossHealth + " [" + bossDamage + "]");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i]
                    + " [" + heroesDamage[i] + "]");
        }
    }
    public static void medic(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i]< 100 && heroesHealth[i]>0){
                if (heroesHealth[3] < 100 && heroesHealth[3] > 0){
                    heroesHealth[3] = heroesHealth[3] - bossDamage;
                }else {
                    Random random = new Random();
                    int coef = random.nextInt(10);
                    heroesHealth[i] = (heroesHealth[i] + coef) - bossDamage;
                }
            }
        }
    }
    public static void Golem(){
        int dmg = bossDamage /5;
        int howAliveHero = 0;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i == 4){
                continue;
            }else if (heroesHealth[i] > 0){
                howAliveHero++;
                heroesHealth[i] += dmg;
            }
            heroesHealth[4] -= dmg * howAliveHero;
            System.out.println("Golem took damage" + dmg * howAliveHero);
            break;
        }
    }
    public static void Berserk(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[6] > 0){
                heroesHealth[6] += bossDamage /5 * 2;
                bossHealth -= bossDamage /5 * 2;
                break;
            }
        }
    }
public static void Thour(){
        Random random = new Random();
        boolean choice = random.nextBoolean();
    for (int i = 0; i < heroesHealth.length; i++) {
        if (heroesHealth[7] > 0){
            if (choice){
                bossDamage = 0;
                System.out.println("Thor suppressed the boss" + choice);
                break;
            }
        }else {
            bossDamage = 50;
            break;
        }
    }
}
public static void Lucky(){
        Random random = new Random();
        boolean choose = random.nextBoolean();
        if (heroesHealth [5] > 0 && choose){
            heroesHealth[5] += bossDamage - bossDamage /5;
            System.out.println("Lucky dodged damage" + choose);
        }
}
            }

