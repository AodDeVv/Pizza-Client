// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.gui;

import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.core.config.Config;
import qolskyblockmod.pizzaclient.PizzaClient;
import java.util.Iterator;
import net.minecraft.client.gui.GuiButton;
import qolskyblockmod.pizzaclient.util.graphics.font.renderer.FontUtil;
import qolskyblockmod.pizzaclient.util.shader.shaders.TexturedChromaShader;
import qolskyblockmod.pizzaclient.util.shader.ChromaShader;
import org.lwjgl.opengl.GL11;
import qolskyblockmod.pizzaclient.util.shader.shaders.BlurShader;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import qolskyblockmod.pizzaclient.gui.components.BetterButton;
import net.minecraft.client.gui.GuiScreen;

public class StartGui extends GuiScreen
{
    public boolean func_73868_f() {
        return false;
    }
    
    public void func_73866_w_() {
        super.func_73866_w_();
        this.field_146292_n.add(new BetterButton(0, this.field_146294_l / 2 - 95, this.field_146295_m / 4 + 100, 190, 20, "Config"));
        this.field_146292_n.add(new BetterButton(1, this.field_146294_l / 2 - 95, this.field_146295_m / 4 + 125, 190, 20, "Edit Button Locations"));
        this.field_146292_n.add(new BetterButton(2, this.field_146294_l / 2 - 95, this.field_146295_m / 4 + 150, 190, 20, "Send Commands List"));
        this.field_146292_n.add(new BetterButton(3, this.field_146294_l / 2 - 95, this.field_146295_m / 4 + 175, 190, 20, "Join the discord!"));
    }
    
    public void func_73863_a(final int mouseX, final int mouseY, final float partialTicks) {
        PlayerUtil.walkInInventory();
        BlurShader.renderBlurryBackground();
        GL11.glScalef(10.0f, 10.0f, 0.0f);
        ChromaShader.setSize(90.0f);
        TexturedChromaShader.instance.applyShader();
        FontUtil.drawCenteredStringNoEvent("PizzaClient", (int)(this.field_146294_l / 2.0f / 10.0f), (int)((this.field_146295_m / 4.0f - 75.0f) / 10.0f), 65535);
        ChromaShader.endShaderResetSize();
        GL11.glScalef(0.1f, 0.1f, 0.0f);
        for (final GuiButton button : this.field_146292_n) {
            button.func_146112_a(this.field_146297_k, mouseX, mouseY);
        }
    }
    
    protected void func_146284_a(final GuiButton button) {
        switch (button.field_146127_k) {
            case 0: {
                this.field_146297_k.func_147108_a((GuiScreen)PizzaClient.config.gui());
                break;
            }
            case 1: {
                this.field_146297_k.func_147108_a((GuiScreen)new LocationEditGui());
                break;
            }
            case 2: {
                Config.sendCommandsList();
                this.field_146297_k.field_71439_g.func_71053_j();
                break;
            }
            case 3: {
                Utils.openUrl("https://discord.gg/NWeacCr3B8");
                break;
            }
        }
    }
}
