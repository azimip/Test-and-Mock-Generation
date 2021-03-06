package org.carlspring.commons.io;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.carlspring.commons.encryption.EncryptionAlgorithmsEnum;
import org.carlspring.commons.util.MessageDigestUtils;
import java.io.*;
public class MultipleDigestOutputStream extends FilterOutputStream {
    private static final String[] DEFAULT_ALGORITHMS = new String[]{ EncryptionAlgorithmsEnum.MD5.getAlgorithm(), EncryptionAlgorithmsEnum.SHA1.getAlgorithm(), EncryptionAlgorithmsEnum.SHA256.getAlgorithm(), EncryptionAlgorithmsEnum.SHA512.getAlgorithm() };

    private Map<String, MessageDigest> digests = new LinkedHashMap<>();

    private Map<String, String> hexDigests = new LinkedHashMap<>();

    private Path path;

    private boolean generateChecksumFiles;

    public MultipleDigestOutputStream(OutputStream os) throws NoSuchAlgorithmException {
        super(os);
        addAlgorithms(DEFAULT_ALGORITHMS);
    }

    public MultipleDigestOutputStream(OutputStream os, String[] algorithms) throws NoSuchAlgorithmException {
        super(os);
        addAlgorithms(algorithms);
    }

    public MultipleDigestOutputStream(File file, OutputStream os) throws NoSuchAlgorithmException {
        this(Paths.get(file.getAbsolutePath()), os, DEFAULT_ALGORITHMS, true);
    }

    public MultipleDigestOutputStream(Path path, OutputStream os) throws NoSuchAlgorithmException {
        this(path, os, DEFAULT_ALGORITHMS, true);
    }

    public MultipleDigestOutputStream(Path path, OutputStream os, String[] algorithms, boolean generateChecksumFiles) throws NoSuchAlgorithmException {
        super(os);
        this.path = path;
        this.generateChecksumFiles = generateChecksumFiles;
        addAlgorithms(algorithms);
    }

    private void addAlgorithms(String[] algorithms) throws NoSuchAlgorithmException {
        for (String algorithm : algorithms) {
            addAlgorithm(algorithm);
        }
    }

    public void addAlgorithm(String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digests.put(algorithm, digest);
    }

    public MessageDigest getMessageDigest(String algorithm) {
        return digests.get(algorithm);
    }

    public Map<String, MessageDigest> getDigests() {
        return digests;
    }

    public String getMessageDigestAsHexadecimalString(String algorithm) {
        if (hexDigests.containsKey(algorithm)) {
            return hexDigests.get(algorithm);
        } else {
            String hexDigest = MessageDigestUtils.convertToHexadecimalString(getMessageDigest(algorithm));
            hexDigests.put(algorithm, hexDigest);
            return hexDigest;
        }
    }

    public void setDigests(Map<String, MessageDigest> digests) {
        this.digests = digests;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
        for (Map.Entry entry : digests.entrySet()) {
            MessageDigest digest = ((MessageDigest) (entry.getValue()));
            digest.update(((byte) (b)));
        }
    }

    @Override
    public void write(byte[] b) throws IOException {
        out.write(b);
        for (Map.Entry entry : digests.entrySet()) {
            MessageDigest digest = ((MessageDigest) (entry.getValue()));
            digest.update(b);
        }
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        out.write(b, off, len);
        for (Map.Entry entry : digests.entrySet()) {
            MessageDigest digest = ((MessageDigest) (entry.getValue()));
            digest.update(b, off, len);
        }
    }

    @Override
    public void close() throws IOException {
        super.close();
        if (generateChecksumFiles) {
            writeChecksums();
        }
    }

    public void writeChecksums() throws IOException {
        for (Map.Entry entry : digests.entrySet()) {
            MessageDigest digest = ((MessageDigest) (entry.getValue()));
            String hexDigest = getMessageDigestAsHexadecimalString(digest.getAlgorithm());
            writeChecksum(path, digest.getAlgorithm(), hexDigest);
        }
    }

    private void writeChecksum(Path path, String algorithm, String hexDigest) throws IOException {
        final String filePathStr = path.toAbsolutePath().toString() + EncryptionAlgorithmsEnum.fromAlgorithm(algorithm).getExtension();
        try (FileWriter fw = new FileWriter(filePathStr)) {
            fw.write(hexDigest + "\n");
            fw.flush();
        }
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public boolean isGenerateChecksumFiles() {
        return generateChecksumFiles;
    }

    public void setGenerateChecksumFiles(boolean generateChecksumFiles) {
        this.generateChecksumFiles = generateChecksumFiles;
    }

    public static void main(String[] args) {
        byte[] bytes = args[0].getBytes();
        try (OutputStream out = new FileOutputStream(args[1])) {
            System.out.println("****GO****");
            System.out.println(args[0]);
            System.out.println(args[1]);
            MultipleDigestOutputStream mdos = new MultipleDigestOutputStream(out);
            mdos.write(bytes);
            mdos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}