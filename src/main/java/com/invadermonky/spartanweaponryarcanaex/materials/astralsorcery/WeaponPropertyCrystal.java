package com.invadermonky.spartanweaponryarcanaex.materials.astralsorcery;

import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.materials.util.WeaponPropertyWithCallbackSE;
import com.invadermonky.spartanweaponryarcanaex.util.StringHelper;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibNames;
import com.oblivioussp.spartanweaponry.api.IWeaponCallback;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import hellfirepvp.astralsorcery.common.item.crystal.CrystalProperties;
import hellfirepvp.astralsorcery.common.item.crystal.ToolCrystalProperties;
import hellfirepvp.astralsorcery.common.item.tool.ItemCrystalSword;
import hellfirepvp.astralsorcery.common.registry.RegistryItems;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class WeaponPropertyCrystal extends WeaponPropertyWithCallbackSE implements IWeaponCallback {
    public static final WeaponPropertyCrystal CRYSTAL_PROPERTY;
    public static final ToolMaterialEx CRYSTAL_MATERIAL_EX;

    protected WeaponPropertyCrystal(String propType) {
        super(propType);
    }

    public WeaponPropertyCrystal() {
        this(LibNames.material_crystal);
    }

    @Override
    public void onCreateItem(ToolMaterialEx material, ItemStack stack) {
        CrystalProperties maxCelestial = CrystalProperties.getMaxCelestialProperties();
        ItemCrystalSword.setToolProperties(stack, ToolCrystalProperties.merge(maxCelestial, maxCelestial));
    }

    @Override
    public void onTooltip(ToolMaterialEx toolMaterialEx, ItemStack itemStack, World world, List<String> tooltip, ITooltipFlag iTooltipFlag) {
        tooltip.add(TextFormatting.DARK_AQUA + I18n.format(StringHelper.getTranslationKey("material_bonus", "tooltip")));
        tooltip.add(TextFormatting.GREEN + "- " + I18n.format(StringHelper.getTranslationKey("material_crystal", "tooltip")));
        if (GuiScreen.isShiftKeyDown()) {
            tooltip.add(TextFormatting.ITALIC + "  " + I18n.format(StringHelper.getTranslationKey("material_crystal", "tooltip", "desc")));
        }
    }

    static {
        CRYSTAL_PROPERTY = new WeaponPropertyCrystal();
        CRYSTAL_MATERIAL_EX = new ToolMaterialEx(
                LibNames.material_crystal,
                RegistryItems.crystalToolMaterial,
                "gemRockCrystal",
                SpartanWeaponryArcanaEx.MOD_ID,
                RegistryItems.crystalToolMaterial.getAttackDamage(),
                CRYSTAL_PROPERTY
        );
    }
}
