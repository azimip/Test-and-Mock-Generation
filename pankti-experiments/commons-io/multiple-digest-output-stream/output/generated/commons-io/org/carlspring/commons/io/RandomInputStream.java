package org.carlspring.commons.io;
import java.io.InputStream;
import java.util.Random;
public class RandomInputStream extends InputStream {
    private long count;

    private long length;

    private Random random = new Random();

    public RandomInputStream(long length) {
        super();
        this.length = length;
    }

    public RandomInputStream(boolean randomSize, long sizeLimit) {
        super();
        if (randomSize) {
            this.length = getRandomSize(sizeLimit);
        }
    }

    @Override
    public int read() {
        if (count >= length) {
            return -1;
        }
        count++;
        return random.nextInt();
    }

    public long getRandomSize(long max) {
        return ((long) (random.nextDouble() * max));
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }
}