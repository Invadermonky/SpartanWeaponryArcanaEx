package com.invadermonky.spartanweaponryarcanaex.items.base;

import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.materials.util.WeaponPropertyWithCallbackSE;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import com.oblivioussp.spartanweaponry.item.ItemBattleaxe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBattleaxeSE extends ItemBattleaxe {
    protected boolean doReequip = true;
    
    public ItemBattleaxeSE(String materialName, ToolMaterialEx material) {
        super("battleaxe_" + materialName, SpartanWeaponryArcanaEx.MOD_ID, material);
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
