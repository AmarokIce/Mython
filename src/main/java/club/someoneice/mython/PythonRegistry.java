package club.someoneice.mython;

import cpw.mods.fml.common.Loader;
import org.python.core.Py;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

import javax.annotation.Nullable;
import java.io.*;
import java.util.Objects;
import java.util.Properties;

public class PythonRegistry {
    public static final PythonRegistry INSTANCE = new PythonRegistry();
    public final PythonInterpreter pythonProcessor;

    public void init() {

    }

    private PythonRegistry() {
        pythonInit();

        File fileDir = new File(Loader.instance().getConfigDir().getPath().replace("config", "mython"));
        if (!fileDir.exists() || !fileDir.isDirectory()) {
            boolean canCreateDir = fileDir.mkdirs();
            if (!canCreateDir) {
                throwDir();
            }
        }

        PySystemState sys = Py.getSystemState();
        sys.path.add(fileDir.getPath());
        pythonProcessor = new PythonInterpreter();

        checkTheFile(fileDir);
    }

    public void checkTheFile(File fileDir) {
        for (File file : Objects.requireNonNull(fileDir.listFiles())) {
            if (file.isFile()) {
                execFile(file);
            }
        }
    }

    public void execFile(File file) {
        if (file.getName().equals("__init__.py")) return;

        try {
            InputStream inputStream = fileAsStream(file);
            if (inputStream != null) {
                pythonProcessor.execfile(inputStream);
                inputStream.close();
                return;
            }

            MythonMain.LOG.error("An error in file {}", file.getName());
        } catch (IOException e) {
            MythonMain.LOG.error(e);
        }
    }

    @Nullable
    public InputStream fileAsStream(File file) {
        if (file.isFile() && file.exists()) {
            try {
                return new FileInputStream(file);
            } catch (FileNotFoundException e) {
                MythonMain.LOG.error(e);
            }
        }

        return null;
    }

    private void pythonInit() {
        Properties props = new Properties();
        props.put("python.console.encoding", "UTF-8");
        props.put("python.security.respectJavaAccessibility", "false");
        props.put("python.import.site", "false");
        Properties preprops = System.getProperties();
        PythonInterpreter.initialize(preprops, props, new String[0]);
    }

    private void throwDir()  {
        MythonMain.LOG.error(new IOException("Cannot create the dir mython on your game dir!"));
    }
}
