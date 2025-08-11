package com.invadermonky.spartanweaponryarcanaex.items.base;

import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.materials.util.WeaponPropertyWithCallbackSE;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import com.oblivioussp.spartanweaponry.item.ItemBoomerang;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemBoomerangSE extends ItemBoomerang {
    protected boolean doReequip = true;

    public ItemBoomerangSE(String materialName, ToolMaterialEx material) {
        super("boomerang_" + materialName, SpartanWeaponryArcanaEx.MOD_ID, material);
    }

    public Item setNoReequipAnimation() {
        this.doReequip = false;
        return this;
    }

    @Override
    public boolean shouldCauseReequipAnimation(@Nonnull ItemStack oldStack, @Nonnull ItemStack newStack, boolean slotChanged) {
        return this.doReequip ? super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged) : oldStack.getItem() != newStack.getItem() || slotChanged || this.getCurrentAmmo(oldStack) != this.getCurrentAmmo(newStack);
    }

    public int getCurrentAmmo(@Nonnull ItemStack stack) {
        return stack.hasTagCompound() ? this.getMaxAmmo(stack) - stack.getTagCompound().getInteger(NBT_AMMO_USED) : 0;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable NBTTagCompound nbt) {
        final List<WeaponProperty> prs = getAllWeaponProperties();
        if (prs != null) {
            for (int i = 0, size = prs.size(); i < size; i++) {
                WeaponProperty property = prs.get(i);
                if (property instanceof WeaponPropertyWithCallbackSE) {
                    ICapabilityProvider capability = ((WeaponPropertyWithCallbackSE) property).initCapabilities(stack, nbt);
                    if (capability != null) return capability;
                }
            }
        }
        return super.initCapabilities(stack, nbt);
    }
}
