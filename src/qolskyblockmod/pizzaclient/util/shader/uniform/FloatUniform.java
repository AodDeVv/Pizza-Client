// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.shader.uniform;

import qolskyblockmod.pizzaclient.util.shader.util.ShaderUtil;
import net.minecraft.client.renderer.OpenGlHelper;
import qolskyblockmod.pizzaclient.util.misc.runnables.FloatSupplier;

public class FloatUniform implements IUniform
{
    public final FloatSupplier supplier;
    public final int id;
    public float lastValue;
    
    public FloatUniform(final int program, final String name, final FloatSupplier supplier) {
        this.id = OpenGlHelper.func_153194_a(program, (CharSequence)name);
        this.supplier = supplier;
    }
    
    @Override
    public void update() {
        final float current = this.supplier.get();
        if (current != this.lastValue) {
            ShaderUtil.glUniform1f(this.id, current);
            this.lastValue = current;
        }
    }
}
