// 
// Decompiled by Procyon v0.5.36
// 

package gg.essential.loader.stage0;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.util.List;
import net.minecraft.launchwrapper.LaunchClassLoader;
import java.net.URL;
import java.nio.file.Path;
import java.io.File;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.ITweaker;

public class EssentialSetupTweaker implements ITweaker
{
    private static final String STAGE1_CLS = "gg.essential.loader.stage1.EssentialSetupTweaker";
    private final EssentialLoader loader;
    private final ITweaker stage1;
    
    public EssentialSetupTweaker() {
        this.loader = new EssentialLoader("launchwrapper");
        try {
            this.stage1 = this.loadStage1((ITweaker)this);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private ITweaker loadStage1(final ITweaker stage0) throws Exception {
        if (Launch.minecraftHome == null) {
            Launch.minecraftHome = new File(".");
        }
        final Path stage1File = this.loader.loadStage1File(Launch.minecraftHome.toPath());
        final URL stage1Url = stage1File.toUri().toURL();
        final LaunchClassLoader classLoader = Launch.classLoader;
        classLoader.addURL(stage1Url);
        classLoader.addClassLoaderExclusion("gg.essential.loader.stage1.");
        addUrlHack(classLoader.getClass().getClassLoader(), stage1Url);
        return (ITweaker)Class.forName("gg.essential.loader.stage1.EssentialSetupTweaker", true, (ClassLoader)classLoader).getConstructor(ITweaker.class).newInstance(stage0);
    }
    
    public void acceptOptions(final List<String> args, final File gameDir, final File assetsDir, final String profile) {
        this.stage1.acceptOptions((List)args, gameDir, assetsDir, profile);
    }
    
    public void injectIntoClassLoader(final LaunchClassLoader classLoader) {
        this.stage1.injectIntoClassLoader(classLoader);
    }
    
    public String getLaunchTarget() {
        return this.stage1.getLaunchTarget();
    }
    
    public String[] getLaunchArguments() {
        return this.stage1.getLaunchArguments();
    }
    
    private static void addUrlHack(final ClassLoader loader, final URL url) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final ClassLoader classLoader = Launch.classLoader.getClass().getClassLoader();
        final Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        method.setAccessible(true);
        method.invoke(classLoader, url);
    }
}
