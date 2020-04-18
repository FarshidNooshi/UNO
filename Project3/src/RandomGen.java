// In The Name Of GOD
import java.util.Random;

public class RandomGen {
    Random random;

    public RandomGen() {
        random = new Random(new Random().nextInt());
    }

    public int random(int mod) {
        return random.nextInt() % mod;
    }
}
