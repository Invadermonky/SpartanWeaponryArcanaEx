package com.invadermonky.spartanweaponryarcanaex.items.base;

import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.materials.util.WeaponPropertyWithCallbackSE;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import com.oblivioussp.spartanweaponry.item.ItemSpear;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class ItemSpearSE extends ItemSpear {
    protected boolean doReequip = true;

    public ItemSpearSE(String materialName, ToolMaterialEx material) {
        super("spear_" + materialName, SpartanWeaponryArcanaEx.MOD_ID, material);
    }

    public Item setNoReequipAnimation() {
        this.doReequip = false;
		return this;
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return this.doReequip ? super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged) : oldStack.getItem() != newStack.getItem() || slotChanged;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        if(this.properties != null) {
            for (WeaponProperty property : this.properties) {
                if (property instanceof WeaponPropertyWithCallbackSE) {
                    ICapabilityProvider capability = ((WeaponPropertyWithCallbackSE) property).initCapabilities(stack, nbt);
                    if(capability != null)
                        return capability;
                }
            }
        }
        return super.initCapabilities(stack, nbt);
    }
}
