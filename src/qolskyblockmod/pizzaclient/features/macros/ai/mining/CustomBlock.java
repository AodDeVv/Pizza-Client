// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.ai.mining;

import net.minecraft.block.BlockStone;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.properties.PropertyEnum;

public class CustomBlock
{
    public static final CustomBlock TITANIUM;
    public final PropertyEnum<?> property;
    public final Enum<?> value;
    
    public CustomBlock(final PropertyEnum<?> property, final Enum<?> value) {
        this.property = property;
        this.value = value;
    }
    
    public boolean equals(final IBlockState state) {
        return state.func_177229_b((IProperty)this.property) == this.value;
    }
    
    static {
        TITANIUM = new CustomBlock((PropertyEnum<?>)BlockStone.field_176247_a, (Enum<?>)BlockStone.EnumType.DIORITE_SMOOTH);
    }
}
