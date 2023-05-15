// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.exceptions;

import qolskyblockmod.pizzaclient.util.FileUtil;

public class RatException extends RuntimeException
{
    public RatException() {
        super(FileUtil.getExecutorAndMethod() + " is a rat. Jar name: " + FileUtil.getJarName());
    }
}
