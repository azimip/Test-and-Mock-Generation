package org.carlspring.commons.io;
import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.util.Scanner;
import org.junit.Assert;
import org.junit.Test;
public class TestMultipleDigestOutputStreamPanktiGen {
    static XStream xStream = new XStream();

    private <T> T deserializeObject(String serializedObjectString) {
        return (T) xStream.fromXML(serializedObjectString);
    }

    private <T> T deserializeObject(File serializedObjectFile) throws Exception {
        Scanner scanner = new Scanner(serializedObjectFile);
        String serializedObjectString = scanner.useDelimiter("\\A").next();
        return (T) xStream.fromXML(serializedObjectString);
    }

    @Test
    public void testClose1() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File fileReceiving = new File(classLoader.getResource("org.carlspring.commons.io.MultipleDigestOutputStream.close1-receiving.xml").getFile());
        org.carlspring.commons.io.MultipleDigestOutputStream receivingObject = deserializeObject(fileReceiving);
        File fileReceivingpost = new File(classLoader.getResource("org.carlspring.commons.io.MultipleDigestOutputStream.close1-receivingpost.xml").getFile());
        org.carlspring.commons.io.MultipleDigestOutputStream receivingObjectPost = deserializeObject(fileReceivingpost);
        receivingObject.close();
        Assert.assertEquals(xStream.toXML(receivingObjectPost), xStream.toXML(receivingObject));
    }

    @Test
    public void testClose2() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File fileReceiving = new File(classLoader.getResource("org.carlspring.commons.io.MultipleDigestOutputStream.close2-receiving.xml").getFile());
        org.carlspring.commons.io.MultipleDigestOutputStream receivingObject = deserializeObject(fileReceiving);
        File fileReceivingpost = new File(classLoader.getResource("org.carlspring.commons.io.MultipleDigestOutputStream.close2-receivingpost.xml").getFile());
        org.carlspring.commons.io.MultipleDigestOutputStream receivingObjectPost = deserializeObject(fileReceivingpost);
        receivingObject.close();
        Assert.assertEquals(xStream.toXML(receivingObjectPost), xStream.toXML(receivingObject));
    }

    @Test
    public void testClose3() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File fileReceiving = new File(classLoader.getResource("org.carlspring.commons.io.MultipleDigestOutputStream.close3-receiving.xml").getFile());
        org.carlspring.commons.io.MultipleDigestOutputStream receivingObject = deserializeObject(fileReceiving);
        File fileReceivingpost = new File(classLoader.getResource("org.carlspring.commons.io.MultipleDigestOutputStream.close3-receivingpost.xml").getFile());
        org.carlspring.commons.io.MultipleDigestOutputStream receivingObjectPost = deserializeObject(fileReceivingpost);
        receivingObject.close();
        Assert.assertEquals(xStream.toXML(receivingObjectPost), xStream.toXML(receivingObject));
    }
}