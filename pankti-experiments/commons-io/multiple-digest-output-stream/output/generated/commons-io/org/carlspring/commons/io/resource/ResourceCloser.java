package org.carlspring.commons.io.resource;
import java.io.Closeable;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.Logger;
public class ResourceCloser {
    private ResourceCloser() {
    }

    public static void close(Closeable resource, Logger logger) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                if (logger != null) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }
}