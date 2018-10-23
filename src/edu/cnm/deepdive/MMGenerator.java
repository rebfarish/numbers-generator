package edu.cnm.deepdive;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class MMGenerator implements Generator {

  private static final int POOL_UPPER_LIMIT = 70;
  private static final int BONUS_UPPER_LIMIT = 25;
  private static final int POOL_PICK_SIZE = 5;

  private Random rng;
  private int[] pool;

public MMGenerator(Random rng){
  this.rng = rng;
  pool = IntStream.rangeClosed(1, POOL_UPPER_LIMIT).toArray();

}

  @Override
  public int[] generate() {
    int[] pick = new int[POOL_PICK_SIZE + 1];
    for (int target = pool.length - 1; target >= pool.length - POOL_PICK_SIZE; target--){
      int source = rng.nextInt(target + 1);
      int temp = pool[target];
      pool[target] = pool[source];
      pool[source] = temp;
      pick[pool.length - 1 - target] = pool[target];
    }
    pick[POOL_PICK_SIZE] = 1 + rng.nextInt(BONUS_UPPER_LIMIT);
    Arrays.sort(pick, 0, POOL_PICK_SIZE);
    return pick;
  }

}
