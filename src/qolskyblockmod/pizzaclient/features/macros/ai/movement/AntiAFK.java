// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.ai.movement;

import net.minecraft.util.Vec3;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.util.rotation.rotaters.Rotater;
import net.minecraft.world.World;
import qolskyblockmod.pizzaclient.util.VecUtil;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import net.minecraft.client.settings.KeyBinding;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.util.Utils;

public class AntiAFK
{
    private static long lastAfkTime;
    private static int nextDelay;
    public static int mode;
    
    public static void check() {
        if (AntiAFK.nextDelay == 0) {
            AntiAFK.nextDelay = 4000 + Utils.random.nextInt(3000);
            AntiAFK.mode = 0;
        }
        if (AntiAFK.lastAfkTime == 0L) {
            AntiAFK.lastAfkTime = System.currentTimeMillis();
        }
        if (System.currentTimeMillis() - AntiAFK.lastAfkTime >= AntiAFK.nextDelay) {
            AntiAFK.lastAfkTime = System.currentTimeMillis();
            switch (AntiAFK.mode) {
                case 0: {
                    AntiAFK.nextDelay = 300 + Utils.random.nextInt(100);
                    AntiAFK.mode = 1;
                    break;
                }
                case 1: {
                    KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74311_E.func_151463_i(), true);
                    AntiAFK.mode = 2;
                    AntiAFK.nextDelay = 300 + Utils.random.nextInt(125);
                    break;
                }
                case 2: {
                    KeyBinding.func_74510_a(PlayerUtil.getRandomMovement(), true);
                    AntiAFK.mode = 3;
                    AntiAFK.nextDelay = 200 + Utils.random.nextInt(100);
                    break;
                }
                case 3: {
                    PlayerUtil.moveOpposite();
                    AntiAFK.mode = 4;
                    break;
                }
                case 4: {
                    Movement.disableMovement();
                    AntiAFK.mode = 5;
                    AntiAFK.nextDelay = 450 + Utils.random.nextInt(150);
                    break;
                }
                case 5: {
                    KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74311_E.func_151463_i(), false);
                    AntiAFK.mode = 6;
                    AntiAFK.nextDelay = 300 + Utils.random.nextInt(100);
                    break;
                }
                case 6: {
                    final MovingObjectPosition rayTrace = VecUtil.rayTraceStopLiquid(100.0f);
                    if (rayTrace != null) {
                        final BlockPos pos = rayTrace.func_178782_a();
                        final Vec3 hit = VecUtil.getRandomHittableStopLiquid(pos, PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c().func_180646_a((World)PizzaClient.mc.field_71441_e, pos).func_72331_e(0.251, 0.251, 0.251));
                        if (hit != null) {
                            new Rotater(hit).setRotationAmount(12).rotate();
                        }
                        else {
                            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Failed to rotate to a random spot. Reason: found no hittable"));
                        }
                    }
                    else {
                        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Failed to rotate to a random spot. Reason: found no vec"));
                    }
                    AntiAFK.mode = 0;
                    AntiAFK.nextDelay = 4000 + Utils.random.nextInt(3000);
                    break;
                }
            }
        }
    }
    
    public static void reset() {
        AntiAFK.nextDelay = 4000 + Utils.random.nextInt(3000);
        AntiAFK.lastAfkTime = 0L;
        AntiAFK.mode = 0;
    }
    
    public static boolean isMoving() {
        return AntiAFK.mode >= 2 && AntiAFK.mode != 6;
    }
}
