package jar;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.jar.Attributes;

public class JarClassLoader extends java.net.URLClassLoader{
    private URL url;

    public JarClassLoader(URL url) {
        super(new URL[] { url });
        this.url = url;
    }

    public String getMainClassName() throws IOException {
        URL u = new URL("jar", "", url + "!/");
        JarURLConnection uc = (JarURLConnection)u.openConnection();
        Attributes attr = uc.getMainAttributes();
        return attr != null ? attr.getValue(Attributes.Name.MAIN_CLASS) : null;
    }
    
}
