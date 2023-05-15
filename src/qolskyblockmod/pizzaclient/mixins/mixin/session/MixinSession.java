// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.session;

import qolskyblockmod.pizzaclient.util.misc.RandomString;
import java.util.Random;
import qolskyblockmod.pizzaclient.util.MathUtil;
import net.minecraft.launchwrapper.Launch;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import qolskyblockmod.pizzaclient.util.FileUtil;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import qolskyblockmod.pizzaclient.util.exceptions.RatException;
import qolskyblockmod.pizzaclient.features.misc.SessionProtection;
import qolskyblockmod.pizzaclient.PizzaClient;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Final;
import net.minecraft.util.Session;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = { Session.class }, priority = Integer.MAX_VALUE)
public class MixinSession
{
    @Final
    @Mutable
    @Shadow
    private String field_148258_c;
    
    @Final
    @Inject(method = { "getSessionID" }, at = { @At("HEAD") }, cancellable = true)
    public void getSessionID(final CallbackInfoReturnable<String> cir) {
        if (PizzaClient.config.sessionProtection) {
            if (!SessionProtection.changedToken) {
                this.changeToken();
            }
            throw new RatException();
        }
    }
    
    @Inject(method = { "<init>" }, at = { @At("RETURN") })
    private void onInit(final String usernameIn, final String playerIDIn, final String tokenIn, final String sessionTypeIn, final CallbackInfo ci) {
        this.changeToken(usernameIn + ":" + playerIDIn);
    }
    
    @Final
    @Inject(method = { "getToken" }, at = { @At("HEAD") }, cancellable = true)
    public void getToken(final CallbackInfoReturnable<String> cir) {
        if (PizzaClient.config.sessionProtection) {
            if (!SessionProtection.changedToken) {
                this.changeToken();
            }
            final String executor = FileUtil.getExecutor(3);
            if (executor.contains("gg.essential.handlers.") && FileUtil.getFileLocation(executor).endsWith("/essential/Essential%20(forge_1.8.9).jar")) {
                cir.setReturnValue((Object)SessionProtection.changed);
            }
            else {
                cir.setReturnValue((Object)this.field_148258_c);
            }
        }
        else {
            if (SessionProtection.changedToken) {
                this.field_148258_c = SessionProtection.changed;
                SessionProtection.changedToken = false;
                SessionProtection.changed = null;
            }
            cir.setReturnValue((Object)this.field_148258_c);
        }
    }
    
    private void changeToken(final String id) {
        SessionProtection.changedToken = true;
        SessionProtection.changed = this.field_148258_c;
        this.field_148258_c = this.randomToken(id);
        this.changeLaunchArgs();
    }
    
    private void changeToken() {
        SessionProtection.changedToken = true;
        SessionProtection.changed = this.field_148258_c;
        this.field_148258_c = this.randomToken(PizzaClient.mc.func_110432_I().func_111285_a() + ":" + PizzaClient.mc.func_110432_I().func_148255_b());
        this.changeLaunchArgs();
    }
    
    private int getPlayerHash() {
        return new SimpleDateFormat("dd/MM/yyyy HH").format(new Date()).hashCode();
    }
    
    private void changeLaunchArgs() {
        final List<String> stringList = Launch.blackboard.get("ArgumentList");
        for (int i = 0; i < stringList.size(); ++i) {
            final String s = stringList.get(i);
            if (s.equals("--accessToken")) {
                stringList.set(i + 1, this.field_148258_c);
                Launch.blackboard.replace("ArgumentsList", stringList);
                break;
            }
        }
    }
    
    private String randomToken(final String id) {
        final int length = 290 + MathUtil.abs(this.getPlayerHash() + id.hashCode()) % 75;
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder("eyJhbGciOiJIUzI1NiJ9.eyJ");
        while (sb.length() != length) {
            if (sb.length() == length - 44) {
                sb.append('.');
            }
            else {
                sb.append(RandomString.alphabet[random.nextInt(RandomString.alphabetLength)]);
            }
        }
        return sb.toString();
    }
}
