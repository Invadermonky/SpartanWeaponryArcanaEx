package com.invadermonky.spartanweaponryarcanaex.materials.bloodmagic;

import WayofTime.bloodmagic.core.RegistrarBloodMagicItems;
import WayofTime.bloodmagic.core.data.Binding;
import WayofTime.bloodmagic.core.data.SoulTicket;
import WayofTime.bloodmagic.util.Utils;
import WayofTime.bloodmagic.util.helper.NetworkHelper;
import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.materials.util.WeaponPropertyWithCallbackSE;
import com.invadermonky.spartanweaponryarcanaex.util.StringHelper;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibNames;
import com.oblivioussp.spartanweaponry.api.IWeaponCallback;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityBoltSpectral;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class WeaponPropertyBound extends WeaponPropertyWithCallbackSE implements IWeaponCallback {
    public static final WeaponPropertyBound BOUND_PROPERTY;
    public static final ToolMaterialEx BOUND_MATERIAL_EX;

    public WeaponPropertyBound() {
        super(LibNames.material_bound);
    }

    @Override
    public void onItemUpdate(ToolMaterialEx material, ItemStack stack, World world, EntityLivingBase entity, int itemSlot, boolean isSelected) {
        if (stack.getItem() instanceof ISpartanBoundWeapon) {
            Binding binding = ((ISpartanBoundWeapon) stack.getItem()).getBinding(stack);
            if (binding == null) {
                ((ISpartanBoundWeapon) stack.getItem()).setActivatedState(stack, false);
            } else {
                if (entity instanceof EntityPlayer && ((ISpartanBoundWeapon) stack.getItem()).getActivated(stack) && world.getTotalWorldTime() % 80L == 0L) {
                    NetworkHelper.getSoulNetwork(binding).syphonAndDamage((EntityPlayer) entity, SoulTicket.item(stack, world, entity, 20));
                }
            }
        }
    }

    @Override
    public void onCreateItem(ToolMaterialEx material, ItemStack stack) {
        Utils.setUnbreakable(stack);
    }

    @Override
    public void applyAttributeToArrow(World world, EntityArrow entityArrow, EntityPlayer player, ItemStack bowStack) {
        if (entityArrow instanceof EntitySpectralArrow || entityArrow instanceof EntityBoltSpectral) {
            entityArrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
        }
    }

    @Override
    public void onTooltip(ToolMaterialEx toolMaterialEx, ItemStack itemStack, World world, List<String> tooltip, ITooltipFlag iTooltipFlag) {
        tooltip.add(TextFormatting.DARK_AQUA + I18n.format(StringHelper.getTranslationKey("material_bonus", "tooltip")));
        tooltip.add(TextFormatting.GREEN + "- " + I18n.format(StringHelper.getTranslationKey("material_bound", "tooltip")));
        if (GuiScreen.isShiftKeyDown()) {
            tooltip.add(TextFormatting.ITALIC + "  " + I18n.format(StringHelper.getTranslationKey("material_bound_bow", "tooltip", "desc")));
        }
    }

    static {
        BOUND_PROPERTY = new WeaponPropertyBound();
        BOUND_MATERIAL_EX = new ToolMaterialEx(
                LibNames.material_bound,
                RegistrarBloodMagicItems.BOUND_TOOL_MATERIAL,
                "bindingReagent",
                SpartanWeaponryArcanaEx.MOD_ID,
                5.0f,
                BOUND_PROPERTY
        );
    }
}
