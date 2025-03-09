package com.invadermonky.spartanweaponryarcanaex.client;

import WayofTime.bloodmagic.core.RegistrarBloodMagicItems;
import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static com.invadermonky.spartanweaponryarcanaex.registry.ModItemsSE.*;

public class CreativeTabSE {
    public static final CreativeTabs TAB_BLOOD_WEAPONRY = new CreativeTabs(SpartanWeaponryArcanaEx.MOD_ID) {
        @Override
        public ItemStack createIcon() {
            Item icon = getVanillaItem();
            return new ItemStack(icon != null ? icon : RegistrarBloodMagicItems.SENTIENT_SWORD);
        }
    };

    private static Item getVanillaItem() {
        if (!ConfigHandler.disableLongsword) {
            return sentient_longsword;
        } else if (!ConfigHandler.disableGreatsword) {
            return sentient_greatsword;
        } else if (!ConfigHandler.disableKatana) {
            return sentient_katana;
        } else if (!ConfigHandler.disableScythe) {
            return sentient_scythe;
        } else if (!ConfigHandler.disableSaber) {
            return sentient_saber;
        } else if (!ConfigHandler.disableRapier) {
            return sentient_rapier;
        } else if (!ConfigHandler.disableHammer) {
            return sentient_hammer;
        } else if (!ConfigHandler.disableWarhammer) {
            return sentient_warhammer;
        } else if (!ConfigHandler.disableSpear) {
            return sentient_spear;
        } else if (!ConfigHandler.disableHalberd) {
            return sentient_halberd;
        } else if (!ConfigHandler.disablePike) {
            return sentient_pike;
        } else if (!ConfigHandler.disableLance) {
            return sentient_lance;
        } else if (!ConfigHandler.disableLongbow) {
            return sentient_longbow;
        } else if (!ConfigHandler.disableCrossbow) {
            return sentient_crossbow;
        } else if (!ConfigHandler.disableThrowingKnife) {
            return sentient_throwing_knife;
        } else if (!ConfigHandler.disableThrowingAxe) {
            return sentient_throwing_axe;
        } else if (!ConfigHandler.disableJavelin) {
            return sentient_javelin;
        } else if (!ConfigHandler.disableDagger) {
            return sentient_dagger;
        } else {
            return RegistrarBloodMagicItems.SENTIENT_SWORD;
        }
    }
}
