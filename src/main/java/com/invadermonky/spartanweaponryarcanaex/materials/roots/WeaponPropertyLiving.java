package com.invadermonky.spartanweaponryarcanaex.materials.roots;

import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.materials.util.WeaponPropertyWithCallbackSE;
import com.invadermonky.spartanweaponryarcanaex.util.StringHelper;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibNames;
import com.oblivioussp.spartanweaponry.api.IWeaponCallback;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import epicsquid.roots.item.materials.Materials;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class WeaponPropertyLiving extends WeaponPropertyWithCallbackSE implements IWeaponCallback {
    public static final WeaponPropertyLiving LIVING_PROPERTY;
    public static final WeaponPropertyLiving TERRASTONE_PROPERTY;
    public static final WeaponPropertyLiving RUNED_PROPERTY;
    public static final WeaponPropertyLiving WILDWOOD_PROPERTY;

    public static final ToolMaterialEx LIVING_MATERIAL_EX;
    public static final ToolMaterialEx TERRASTONE_MATERIAL_EX;
    public static final ToolMaterialEx RUNED_MATERIAL_EX;
    public static final ToolMaterialEx WILDWOOD_MATERIAL_EX;

    static {
        LIVING_PROPERTY = new WeaponPropertyLiving(LibNames.material_living, 40);
        LIVING_MATERIAL_EX = new ToolMaterialEx(
                LibNames.material_living,
                Materials.LIVING,
                "rootsBark",
                SpartanWeaponryArcanaEx.MOD_ID,
                Materials.LIVING.getAttackDamage(),
                LIVING_PROPERTY

        );

        TERRASTONE_PROPERTY = new WeaponPropertyLiving(LibNames.material_terrastone, 20);
        TERRASTONE_MATERIAL_EX = new ToolMaterialEx(
                LibNames.material_terrastone,
                Materials.TERRASTONE,
                "mossyCobblestone",
                SpartanWeaponryArcanaEx.MOD_ID,
                Materials.TERRASTONE.getAttackDamage(),
                TERRASTONE_PROPERTY
        );

        RUNED_PROPERTY = new WeaponPropertyLiving(LibNames.material_runed, 90);
        RUNED_MATERIAL_EX = new ToolMaterialEx(
                LibNames.material_runed,
                Materials.RUNIC,
                "runedObsidian",
                SpartanWeaponryArcanaEx.MOD_ID,
                Materials.RUNIC.getAttackDamage(),
                RUNED_PROPERTY
        );

        WILDWOOD_PROPERTY = new WeaponPropertyLiving(LibNames.material_wildwood, 120);
        WILDWOOD_MATERIAL_EX = new ToolMaterialEx(
                LibNames.material_wildwood,
                ConfigHandler.woodenCrossbowOnly ? Materials.LIVING : Materials.TERRASTONE,
                "rootsBarkWildwood",
                SpartanWeaponryArcanaEx.MOD_ID,
                (ConfigHandler.woodenCrossbowOnly ? WeaponPropertyLiving.LIVING_MATERIAL_EX : WeaponPropertyLiving.TERRASTONE_MATERIAL_EX).getAttackDamage(),
                WILDWOOD_PROPERTY
        );

    }

    private final int repairChance;

    public WeaponPropertyLiving(String propType, int repairChance) {
        super(propType);
        this.repairChance = Math.max(1, repairChance);
    }

    @Override
    public void onItemUpdate(ToolMaterialEx material, ItemStack stack, World world, EntityLivingBase entity, int itemSlot, boolean isSelected) {
        if(!world.isRemote && entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            if(player.isHandActive() && isSelected) {
                return;
            }

            if (stack.getItemDamage() > 0 && world.rand.nextInt(this.repairChance) == 0) {
                stack.setItemDamage(stack.getItemDamage() - 1);
            }
        }
    }

    @Override
    public void onTooltip(ToolMaterialEx toolMaterialEx, ItemStack itemStack, World world, List<String> tooltip, ITooltipFlag iTooltipFlag) {
        tooltip.add(TextFormatting.DARK_AQUA + I18n.format(StringHelper.getTranslationKey("material_bonus", "tooltip")));
        tooltip.add(TextFormatting.GREEN + "- " + I18n.format(StringHelper.getTranslationKey(LibNames.material_wildwood, "tooltip")));
        if(GuiScreen.isShiftKeyDown()) {
            tooltip.add(TextFormatting.ITALIC+ "  " + I18n.format(StringHelper.getTranslationKey(LibNames.material_wildwood, "tooltip", "desc")));
        }
    }
}
