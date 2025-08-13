package com.invadermonky.spartanweaponryarcanaex.items.base;

import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.client.CreativeTabSE;
import com.invadermonky.spartanweaponryarcanaex.materials.util.WeaponPropertyWithCallbackSE;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import com.oblivioussp.spartanweaponry.item.ItemJavelin;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class ItemJavelinSE extends ItemJavelin {
    protected boolean doReequip = true;

    public ItemJavelinSE(String materialName, ToolMaterialEx material) {
        super("javelin_" + materialName, SpartanWeaponryArcanaEx.MOD_ID, material);
        this.setCreativeTab(CreativeTabSE.TAB_SE);
    }

    public Item setNoReequipAnimation() {
        this.doReequip = false;
		return this;
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return this.doReequip ? super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged) : oldStack.getItem() != newStack.getItem() || slotChanged || this.getCurrentAmmo(oldStack) != this.getCurrentAmmo(newStack);
    }

    public int getCurrentAmmo(ItemStack stack) {
        return stack.hasTagCompound() ? this.getMaxAmmo(stack) - stack.getTagCompound().getInteger(NBT_AMMO_USED) : 0;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        if(this.materialEx != null) {
            for (WeaponProperty property : this.materialEx.getAllWeaponProperties()) {
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
