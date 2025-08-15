package com.invadermonky.spartanweaponryarcanaex.materials.astralsorcery;

import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.util.PlayerHelper;
import com.invadermonky.spartanweaponryarcanaex.util.StringHelper;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibNames;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibTags;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.item.ItemThrowingWeapon;
import hellfirepvp.astralsorcery.common.item.tool.ChargedCrystalToolBase;
import hellfirepvp.astralsorcery.common.lib.ItemsAS;
import hellfirepvp.astralsorcery.common.registry.RegistryItems;
import hellfirepvp.astralsorcery.common.util.MiscUtils;
import hellfirepvp.astralsorcery.common.util.data.Vector3;
import hellfirepvp.astralsorcery.common.util.effect.CelestialStrike;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class WeaponPropertyInfusedCrystal extends WeaponPropertyCrystal {
    public static final WeaponPropertyInfusedCrystal INFUSED_CRYSTAL_PROPERTY;
    public static final ToolMaterialEx INFUSED_CRYSTAL_MATERIAL_EX;

    public WeaponPropertyInfusedCrystal() {
        super(LibNames.material_infused_crystal);
    }

    @Override
    public void onHitEntity(ToolMaterialEx material, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, Entity projectile) {
        if (attacker instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) attacker;
            if (!player.getEntityWorld().isRemote && player instanceof EntityPlayerMP) {
                EntityPlayerMP playerMp = (EntityPlayerMP) player;
                if (!MiscUtils.isPlayerFakeMP(playerMp) && !player.isSneaking() && !playerMp.getCooldownTracker().hasCooldown(ItemsAS.chargedCrystalSword)) {
                    CelestialStrike.play(player, player.getEntityWorld(), Vector3.atEntityCorner(target), Vector3.atEntityCenter(target));
                    stack.damageItem(1, player);
                    if (!ChargedCrystalToolBase.tryRevertMainHand(playerMp, stack)) {
                        playerMp.getCooldownTracker().setCooldown(ItemsAS.chargedCrystalSword, 80);
                        if (!(stack.getItem() instanceof ItemThrowingWeapon)) {
                            playerMp.getCooldownTracker().setCooldown(stack.getItem(), 80);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void applyAttributeToArrow(World world, EntityArrow entityArrow, EntityPlayer player, ItemStack bowStack) {
        if (entityArrow.pickupStatus != EntityArrow.PickupStatus.CREATIVE_ONLY || player.isCreative()) {
            entityArrow.getEntityData().setBoolean(LibTags.stellar_arrow, true);
            entityArrow.getEntityData().setString(LibTags.owner, PlayerHelper.getUUIDFromPlayer(player).toString());
        }
    }

    @Override
    public void onTooltip(ToolMaterialEx toolMaterialEx, ItemStack itemStack, World world, List<String> tooltip, ITooltipFlag iTooltipFlag) {
        super.onTooltip(toolMaterialEx, itemStack, world, tooltip, iTooltipFlag);
        tooltip.add(TextFormatting.GREEN + "- " + I18n.format(StringHelper.getTranslationKey("material_infused_crystal", "tooltip")));
        if (GuiScreen.isShiftKeyDown()) {
            tooltip.add(TextFormatting.ITALIC + "  " + I18n.format(StringHelper.getTranslationKey("material_infused_crystal_bow", "tooltip", "desc")));
        }
    }
    static {
        INFUSED_CRYSTAL_PROPERTY = new WeaponPropertyInfusedCrystal();
        INFUSED_CRYSTAL_MATERIAL_EX = new ToolMaterialEx(
                LibNames.material_infused_crystal,
                RegistryItems.crystalToolMaterial,
                "gemRockCrystal",
                SpartanWeaponryArcanaEx.MOD_ID,
                RegistryItems.crystalToolMaterial.getAttackDamage(),
                CRYSTAL_PROPERTY,
                INFUSED_CRYSTAL_PROPERTY
        );
    }
}
