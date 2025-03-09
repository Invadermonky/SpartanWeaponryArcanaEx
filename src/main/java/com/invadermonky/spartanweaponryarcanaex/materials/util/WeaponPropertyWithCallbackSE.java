package com.invadermonky.spartanweaponryarcanaex.materials.util;

import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyWithCallback;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public abstract class WeaponPropertyWithCallbackSE extends WeaponPropertyWithCallback {


    public WeaponPropertyWithCallbackSE(String propType, int propLevel, float propMagnitude) {
        super(propType, SpartanWeaponryArcanaEx.MOD_ID, propLevel, propMagnitude);
    }

    public WeaponPropertyWithCallbackSE(String propType, int propLevel) {
        super(propType, SpartanWeaponryArcanaEx.MOD_ID, propLevel);
    }

    public WeaponPropertyWithCallbackSE(String propType, float propMagnitude) {
        super(propType, SpartanWeaponryArcanaEx.MOD_ID, propMagnitude);
    }

    public WeaponPropertyWithCallbackSE(String propType) {
        super(propType, SpartanWeaponryArcanaEx.MOD_ID);
    }

    @Nullable
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return null;
    }

    /**
     * Used to set any additional data to the arrow entity prior to it being spawned into the world. You can adjust the velocity
     * or add NBT data here.
     *
     * @param world The world the arrow is being spawned in.
     * @param entityArrow The arrow entity being spawned.
     * @param player The player firing the bow.
     * @param bowStack The bow firing the arrow.
     */
    public void applyAttributeToArrow(World world, EntityArrow entityArrow, EntityPlayer player, ItemStack bowStack) {}
}
