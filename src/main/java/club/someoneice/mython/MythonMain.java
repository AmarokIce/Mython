package club.someoneice.mython;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@Mod(modid = MythonMain.MODID)
public class MythonMain {
    public static final String MODID = "mython";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) throws IOException {
        PythonRegistry.INSTANCE.init();
    }
}
