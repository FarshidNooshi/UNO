// In The Name Of GOD
import java.util.Random;

/**
 * this class creates random numbers and its most important method gives me randoms between 0 to mod - 1
 */
public class RandomGen {
    Random random;

    public RandomGen() {
        random = new Random(new Random().nextInt());
    }

    public int random(int mod) {
        return (random.nextInt() % mod + mod) % mod;
    }
}
