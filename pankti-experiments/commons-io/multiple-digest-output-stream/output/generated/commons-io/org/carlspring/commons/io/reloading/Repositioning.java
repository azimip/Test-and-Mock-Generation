package org.carlspring.commons.io.reloading;
import java.io.IOException;
public interface Repositioning {
    void reposition() throws IOException;

    void reposition(long skipBytes);

    boolean hasMoreByteRanges();
}