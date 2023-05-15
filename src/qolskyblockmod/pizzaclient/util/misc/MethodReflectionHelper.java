// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.misc;

import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.PizzaClient;
import java.lang.reflect.Method;

public class MethodReflectionHelper
{
    private Method method;
    
    public MethodReflectionHelper(final Class<?> clazz, final String methodName, final String methodname2, final Class<?>... parameter) {
        try {
            try {
                this.method = clazz.getDeclaredMethod(methodName, parameter);
            }
            catch (NoSuchMethodException e2) {
                this.method = clazz.getDeclaredMethod(methodname2, parameter);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public void invoke(final Object instance) {
        try {
            this.method.setAccessible(true);
            this.method.invoke(instance, new Object[0]);
        }
        catch (Exception e) {
            e.printStackTrace();
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Failed to invoke the method " + this.method.getName() + ". See logs for more info."));
        }
    }
    
    public void invoke(final Object instance, final int amt) {
        try {
            this.method.setAccessible(true);
            for (int i = 0; i < amt; ++i) {
                this.method.invoke(instance, new Object[0]);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Failed to invoke the method " + this.method.getName() + ". See logs for more info."));
        }
    }
    
    public void invokeStatic() {
        try {
            this.method.setAccessible(true);
            this.method.invoke(null, new Object[0]);
        }
        catch (Exception e) {
            e.printStackTrace();
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Failed to invoke the method " + this.method.getName() + ". See logs for more info."));
        }
    }
    
    public void invokeStatic(final int amt) {
        try {
            this.method.setAccessible(true);
            for (int i = 0; i < amt; ++i) {
                this.method.invoke(null, new Object[0]);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Failed to invoke the method " + this.method.getName() + ". See logs for more info."));
        }
    }
    
    public void invokeMc() {
        try {
            this.method.setAccessible(true);
            this.method.invoke(PizzaClient.mc, new Object[0]);
        }
        catch (Exception e) {
            e.printStackTrace();
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Failed to invoke the method " + this.method.getName() + ". See logs for more info."));
        }
    }
    
    public void invokeMc(final int amt) {
        try {
            this.method.setAccessible(true);
            for (int i = 0; i < amt; ++i) {
                this.method.invoke(PizzaClient.mc, new Object[0]);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Failed to invoke the method " + this.method.getName() + ". See logs for more info."));
        }
    }
}
