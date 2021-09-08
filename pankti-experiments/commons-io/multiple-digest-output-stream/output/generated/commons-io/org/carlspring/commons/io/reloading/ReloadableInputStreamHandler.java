package org.carlspring.commons.io.reloading;
import java.io.IOException;
import java.io.InputStream;
public interface ReloadableInputStreamHandler extends Reloading {
    InputStream getInputStream() throws IOException;
}