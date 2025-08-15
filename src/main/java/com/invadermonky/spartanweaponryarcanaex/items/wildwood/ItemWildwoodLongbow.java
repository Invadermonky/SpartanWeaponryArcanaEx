package com.invadermonky.spartanweaponryarcanaex.items.wildwood;

import com.invadermonky.spartanweaponryarcanaex.items.base.ItemLongbowSE;
import com.invadermonky.spartanweaponryarcanaex.materials.roots.WeaponPropertyLiving;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibNames;
import epicsquid.roots.init.ModItems;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IRarity;
import org.jetbrains.annotations.NotNull;

public class ItemWildwoodLongbow extends ItemLongbowSE {

    public ItemWildwoodLongbow() {
        super(
                LibNames.wildwood,
                WeaponPropertyLiving.WILDWOOD_MATERIAL_EX,
                WeaponPropertyLiving.WILDWOOD_PROPERTY
        );
        this.setNoReequipAnimation();
    }

    @Override
    public @NotNull String getItemStackDisplayName(ItemStack stack) {
        return super.getItemStackDisplayName(stack);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return toRepair.getItem() == this && repair.getItem() == ModItems.bark_wildwood;
    }

    @Override
    public @NotNull IRarity getForgeRarity(@NotNull ItemStack stack) {
        return EnumRarity.RARE;
    }
}
