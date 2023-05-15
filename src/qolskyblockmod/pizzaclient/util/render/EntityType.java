// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.render;

import net.minecraft.entity.EntityLivingBase;

public class EntityType
{
    public final EntityLivingBase entityToRender;
    public final int type;
    
    public EntityType(final EntityLivingBase entity, final int type) {
        this.entityToRender = entity;
        this.type = type;
    }
    
    public boolean isDead() {
        return this.entityToRender.field_70128_L || this.entityToRender.func_110138_aP() <= 0.0f;
    }
}
