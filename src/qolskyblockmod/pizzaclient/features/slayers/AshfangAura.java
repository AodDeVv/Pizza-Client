// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.slayers;

import java.util.Iterator;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.util.rotation.fake.FakeRotater;
import qolskyblockmod.pizzaclient.util.rotation.Rotation;
import net.minecraft.util.Vec3;
import net.minecraft.util.StringUtils;
import net.minecraft.entity.item.EntityArmorStand;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.entity.Entity;

public class AshfangAura
{
    public static boolean onRenderOrb(final Entity entityToInteract) {
        Vec3 ashfang = null;
        for (final Entity entity : PizzaClient.mc.field_71441_e.field_72996_f) {
            if (entity instanceof EntityArmorStand && entity.func_145818_k_() && StringUtils.func_76338_a(entity.func_95999_t()).contains("Lv200] Ashfang")) {
                ashfang = new Vec3(entity.field_70165_t, entity.field_70163_u - 0.5, entity.field_70161_v);
                break;
            }
        }
        if (ashfang != null) {
            FakeRotater.clickEntity(Rotation.getRotation(entityToInteract.func_174824_e(1.0f), ashfang), entityToInteract);
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.SUCCESS_MESSAGE + "Clicking Blazing Soul!"));
            return true;
        }
        return false;
    }
}
