package us.codecraft.tinyioc.beans.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * resource是Spring内部资源定位的接口
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
