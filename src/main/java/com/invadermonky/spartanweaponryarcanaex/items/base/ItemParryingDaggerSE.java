package com.invadermonky.spartanweaponryarcanaex.items.base;

import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.client.CreativeTabSE;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.item.ItemParryingDagger;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemParryingDaggerSE extends ItemParryingDagger {
    protected boolean doReequip = true;

    public ItemParryingDaggerSE(String materialName, ToolMaterialEx material) {
        super("parrying_dagger_" + materialName, SpartanWeaponryArcanaEx.MOD_ID, material);
        this.setCreativeTab(CreativeTabSE.TAB_SE);
    }

    public Item setNoReequipAnimation() {
        this.doReequip = false;
		return this;
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return this.doReequip ? super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged) : oldStack.getItem() != newStack.getItem() || slotChanged;
    }
}
