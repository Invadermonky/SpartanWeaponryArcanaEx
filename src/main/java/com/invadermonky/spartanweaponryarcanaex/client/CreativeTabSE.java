package com.invadermonky.spartanweaponryarcanaex.client;

import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.items.base.*;
import com.invadermonky.spartanweaponryarcanaex.registry.ModItemsSE;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CreativeTabSE {
    public static final CreativeTabs TAB_SE = new CreativeTabs(SpartanWeaponryArcanaEx.MOD_ID) {
        @Override
        public @NotNull ItemStack createIcon() {
            Item icon = getVanillaItem();
            return new ItemStack(icon != null ? icon : Items.AIR);
        }
    };

    private static Item getVanillaItem() {
        if (!ConfigHandler.disableLongsword) {
            return ModItemsSE.getItemsToRegister().stream().filter(item -> item instanceof ItemLongswordSE).findFirst().orElse(getFallback());
        } else if (!ConfigHandler.disableGreatsword) {
            return ModItemsSE.getItemsToRegister().stream().filter(item -> item instanceof ItemGreatswordSE).findFirst().orElse(getFallback());
        } else if (!ConfigHandler.disableKatana) {
            return ModItemsSE.getItemsToRegister().stream().filter(item -> item instanceof ItemKatanaSE).findFirst().orElse(getFallback());
        } else if (!ConfigHandler.disableScythe) {
            return ModItemsSE.getItemsToRegister().stream().filter(item -> item instanceof ItemScytheSE).findFirst().orElse(getFallback());
        } else if (!ConfigHandler.disableSaber) {
            return ModItemsSE.getItemsToRegister().stream().filter(item -> item instanceof ItemSaberSE).findFirst().orElse(getFallback());
        } else if (!ConfigHandler.disableRapier) {
            return ModItemsSE.getItemsToRegister().stream().filter(item -> item instanceof ItemRapierSE).findFirst().orElse(getFallback());
        } else if (!ConfigHandler.disableHammer) {
            return ModItemsSE.getItemsToRegister().stream().filter(item -> item instanceof ItemHammerSE).findFirst().orElse(getFallback());
        } else if (!ConfigHandler.disableWarhammer) {
            return ModItemsSE.getItemsToRegister().stream().filter(item -> item instanceof ItemWarhammerSE).findFirst().orElse(getFallback());
        } else if (!ConfigHandler.disableSpear) {
            return ModItemsSE.getItemsToRegister().stream().filter(item -> item instanceof ItemSpearSE).findFirst().orElse(getFallback());
        } else if (!ConfigHandler.disableHalberd) {
            return ModItemsSE.getItemsToRegister().stream().filter(item -> item instanceof ItemHalberdSE).findFirst().orElse(getFallback());
        } else if (!ConfigHandler.disablePike) {
            return ModItemsSE.getItemsToRegister().stream().filter(item -> item instanceof ItemPikeSE).findFirst().orElse(getFallback());
        } else if (!ConfigHandler.disableLance) {
            return ModItemsSE.getItemsToRegister().stream().filter(item -> item instanceof ItemLanceSE).findFirst().orElse(getFallback());
        } else if (!ConfigHandler.disableLongbow) {
            return ModItemsSE.getItemsToRegister().stream().filter(item -> item instanceof ItemLongbowSE).findFirst().orElse(getFallback());
        } else if (!ConfigHandler.disableCrossbow) {
            return ModItemsSE.getItemsToRegister().stream().filter(item -> item instanceof ItemCrossbowSE).findFirst().orElse(getFallback());
        } else if (!ConfigHandler.disableThrowingKnife) {
            return ModItemsSE.getItemsToRegister().stream().filter(item -> item instanceof ItemThrowingKnifeSE).findFirst().orElse(getFallback());
        } else if (!ConfigHandler.disableThrowingAxe) {
            return ModItemsSE.getItemsToRegister().stream().filter(item -> item instanceof ItemThrowingAxeSE).findFirst().orElse(getFallback());
        } else if (!ConfigHandler.disableJavelin) {
            return ModItemsSE.getItemsToRegister().stream().filter(item -> item instanceof ItemJavelinSE).findFirst().orElse(getFallback());
        } else if (!ConfigHandler.disableDagger) {
            return ModItemsSE.getItemsToRegister().stream().filter(item -> item instanceof ItemDaggerSE).findFirst().orElse(getFallback());
        } else {
            return getFallback();
        }
    }

    private static Item getFallback() {
        return Items.DIAMOND_SWORD;
    }
}
