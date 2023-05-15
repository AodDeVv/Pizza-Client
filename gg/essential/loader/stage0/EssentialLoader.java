// 
// Decompiled by Procyon v0.5.36
// 

package gg.essential.loader.stage0;

import org.apache.logging.log4j.LogManager;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.jar.JarInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import org.apache.logging.log4j.Logger;

class EssentialLoader
{
    static final String STAGE1_RESOURCE = "gg/essential/loader/stage0/stage1.jar";
    static final String STAGE1_PKG = "gg.essential.loader.stage1.";
    static final String STAGE1_PKG_PATH;
    static final Logger LOGGER;
    private final String variant;
    
    public EssentialLoader(final String variant) {
        this.variant = variant;
    }
    
    public Path loadStage1File(final Path gameDir) throws Exception {
        final Path dataDir = gameDir.resolve("essential").resolve("loader").resolve("stage0").resolve(this.variant);
        final Path stage1UpdateFile = dataDir.resolve("stage1.update.jar");
        final Path stage1File = dataDir.resolve("stage1.jar");
        final URL stage1Url = stage1File.toUri().toURL();
        if (!Files.exists(dataDir, new LinkOption[0])) {
            Files.createDirectories(dataDir, (FileAttribute<?>[])new FileAttribute[0]);
        }
        if (Files.exists(stage1UpdateFile, new LinkOption[0])) {
            EssentialLoader.LOGGER.info("Found update for stage1.");
            Files.deleteIfExists(stage1File);
            Files.move(stage1UpdateFile, stage1File, new CopyOption[0]);
        }
        URL latestUrl = null;
        int latestVersion = -1;
        if (Files.exists(stage1File, new LinkOption[0])) {
            latestVersion = getVersion(stage1Url);
            EssentialLoader.LOGGER.debug("Found stage1 version {}: {}", new Object[] { latestVersion, stage1Url });
        }
        final Enumeration<URL> resources = EssentialLoader.class.getClassLoader().getResources("gg/essential/loader/stage0/stage1.jar");
        if (!resources.hasMoreElements()) {
            EssentialLoader.LOGGER.warn("Found no embedded stage1 jar files.");
        }
        while (resources.hasMoreElements()) {
            final URL url = resources.nextElement();
            final int version = getVersion(url);
            EssentialLoader.LOGGER.debug("Found stage1 version {}: {}", new Object[] { version, url });
            if (version > latestVersion) {
                latestVersion = version;
                latestUrl = url;
            }
        }
        if (latestUrl != null) {
            EssentialLoader.LOGGER.info("Updating stage1 to version {} from {}", new Object[] { latestVersion, latestUrl });
            try (final InputStream in = latestUrl.openStream()) {
                Files.deleteIfExists(stage1File);
                Files.copy(in, stage1File, new CopyOption[0]);
            }
        }
        return stage1File;
    }
    
    private static int getVersion(final URL file) {
        try (final JarInputStream in = new JarInputStream(file.openStream(), false)) {
            final Manifest manifest = in.getManifest();
            final Attributes attributes = manifest.getMainAttributes();
            if (!EssentialLoader.STAGE1_PKG_PATH.equals(attributes.getValue("Name"))) {
                return -1;
            }
            return Integer.parseInt(attributes.getValue("Implementation-Version"));
        }
        catch (Exception e) {
            EssentialLoader.LOGGER.warn("Failed to read version from " + file, (Throwable)e);
            return -1;
        }
    }
    
    static {
        STAGE1_PKG_PATH = "gg.essential.loader.stage1.".replace('.', '/');
        LOGGER = LogManager.getLogger((Class)EssentialLoader.class);
    }
}
