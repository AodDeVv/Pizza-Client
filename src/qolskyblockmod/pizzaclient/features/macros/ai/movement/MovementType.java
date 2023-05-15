// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.ai.movement;

import qolskyblockmod.pizzaclient.PizzaClient;

public enum MovementType
{
    FORWARDS, 
    BACKWARDS, 
    LEFT, 
    RIGHT, 
    NULL;
    
    public MovementType left() {
        switch (this) {
            case FORWARDS: {
                return MovementType.LEFT;
            }
            case LEFT: {
                return MovementType.BACKWARDS;
            }
            case BACKWARDS: {
                return MovementType.RIGHT;
            }
            case RIGHT: {
                return MovementType.FORWARDS;
            }
            default: {
                return MovementType.NULL;
            }
        }
    }
    
    public MovementType right() {
        switch (this) {
            case FORWARDS: {
                return MovementType.RIGHT;
            }
            case RIGHT: {
                return MovementType.BACKWARDS;
            }
            case BACKWARDS: {
                return MovementType.LEFT;
            }
            case LEFT: {
                return MovementType.FORWARDS;
            }
            default: {
                return MovementType.NULL;
            }
        }
    }
    
    public MovementType opposite() {
        switch (this) {
            case FORWARDS: {
                return MovementType.BACKWARDS;
            }
            case BACKWARDS: {
                return MovementType.FORWARDS;
            }
            case LEFT: {
                return MovementType.RIGHT;
            }
            case RIGHT: {
                return MovementType.LEFT;
            }
            default: {
                return MovementType.NULL;
            }
        }
    }
    
    public static MovementType getCurrent() {
        if (PizzaClient.mc.field_71474_y.field_74351_w.func_151470_d()) {
            return MovementType.FORWARDS;
        }
        if (PizzaClient.mc.field_71474_y.field_74370_x.func_151470_d()) {
            return MovementType.LEFT;
        }
        if (PizzaClient.mc.field_71474_y.field_74366_z.func_151470_d()) {
            return MovementType.RIGHT;
        }
        if (PizzaClient.mc.field_71474_y.field_74368_y.func_151470_d()) {
            return MovementType.BACKWARDS;
        }
        return MovementType.NULL;
    }
}
