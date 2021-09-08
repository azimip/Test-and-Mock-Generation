package org.carlspring.commons.io.diff;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class BinaryDiff {
    private byte[] bytes1;

    private byte[] bytes2;

    private long numberOfBytesToShowUponDifference = 100;

    public BinaryDiff(byte[] bytes1, byte[] bytes2) {
        this.bytes1 = bytes1;
        this.bytes2 = bytes2;
    }

    public boolean diff() {
        try (ByteArrayOutputStream baos1 = new ByteArrayOutputStream();ByteArrayOutputStream baos2 = new ByteArrayOutputStream();InputStream inputStream1 = new ByteArrayInputStream(bytes1);InputStream inputStream2 = new ByteArrayInputStream(bytes2)) {
            byte[] b1 = new byte[1];
            byte[] b2 = new byte[1];
            int bytesReadFromIS1;
            int bytesReadFromIS2;
            long totalReadFromIS1 = 0L;
            long totalReadFromIS2 = 0L;
            long differencePositionStart;
            while (((bytesReadFromIS1 = inputStream1.read(b1)) != (-1)) && ((bytesReadFromIS2 = inputStream2.read(b2)) != (-1))) {
                totalReadFromIS1 += bytesReadFromIS1;
                totalReadFromIS2 += bytesReadFromIS2;
                if (b1[0] != b2[0]) {
                    differencePositionStart = totalReadFromIS1;
                    baos1.write(b1);
                    baos1.flush();
                    baos2.write(b2);
                    baos2.flush();
                    totalReadFromIS1 = readExcerpt(inputStream1, totalReadFromIS1, b1, baos1);
                    totalReadFromIS2 = readExcerpt(inputStream2, totalReadFromIS2, b2, baos2);
                    streamsHaveDifferentLength(totalReadFromIS1, totalReadFromIS2);
                    return true;
                }
            } 
            if (streamsHaveDifferentLength(totalReadFromIS1, totalReadFromIS2)) {
                return true;
            }
        } catch (IOException e) {
        }
        return false;
    }

    private long readExcerpt(InputStream is, long totalReadFromIS, byte[] b, ByteArrayOutputStream baos) throws IOException {
        int bytesReadFromIS;
        long bytesShown1 = 0L;
        while (((bytesReadFromIS = is.read(b)) != (-1)) && (bytesShown1 < numberOfBytesToShowUponDifference)) {
            totalReadFromIS += bytesReadFromIS;
            baos.write(b);
            baos.flush();
            bytesShown1++;
        } 
        return totalReadFromIS;
    }

    private boolean streamsHaveDifferentLength(long totalReadFromIS1, long totalReadFromIS2) {
        if (totalReadFromIS1 != totalReadFromIS2) {
            if (totalReadFromIS1 == (-1)) {
            }
            if (totalReadFromIS2 == (-1)) {
            }
            return true;
        }
        return false;
    }

    public byte[] getBytes1() {
        return bytes1;
    }

    public void setBytes1(byte[] bytes1) {
        this.bytes1 = bytes1;
    }

    public byte[] getBytes2() {
        return bytes2;
    }

    public void setBytes2(byte[] bytes2) {
        this.bytes2 = bytes2;
    }

    public long getNumberOfBytesToShowUponDifference() {
        return numberOfBytesToShowUponDifference;
    }

    public void setNumberOfBytesToShowUponDifference(long numberOfBytesToShowUponDifference) {
        this.numberOfBytesToShowUponDifference = numberOfBytesToShowUponDifference;
    }

    public static void main(String[] args) {
        String s1 = args[0];
        byte[] b1 = s1.getBytes();
        String s2 = args[1];
        byte[] b2 = s2.getBytes();
        BinaryDiff bd = new BinaryDiff(b1, b2);
        System.out.println(s1);
        System.out.println(Arrays.toString(b1));
        System.out.println(s2);
        System.out.println(Arrays.toString(b2));
        System.out.println(bd.diff());
    }
}