package com.invadermonky.spartanweaponryarcanaex.items.shields;

import WayofTime.bloodmagic.core.RegistrarBloodMagicItems;
import WayofTime.bloodmagic.core.data.Binding;
import WayofTime.bloodmagic.core.data.SoulTicket;
import WayofTime.bloodmagic.iface.IActivatable;
import WayofTime.bloodmagic.iface.IBindable;
import WayofTime.bloodmagic.util.Utils;
import WayofTime.bloodmagic.util.helper.NetworkHelper;
import WayofTime.bloodmagic.util.helper.TextHelper;
import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.client.CreativeTabSE;
import com.invadermonky.spartanweaponryarcanaex.util.StringHelper;
import com.oblivioussp.spartanshields.item.ItemShieldBasic;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBoundShield {
    protected final String unlocName;

    public ItemBoundShield(String unlocName) {
        this.unlocName = unlocName;
    }

    public ItemShield createShield() {
        class ItemBoundShieldBasic extends ItemShieldBasic implements IBindable, IActivatable {
            public ItemBoundShieldBasic() {
                super(unlocName, 3000, RegistrarBloodMagicItems.BOUND_TOOL_MATERIAL);
                this.setCreativeTab(CreativeTabSE.TAB_BLOOD_WEAPONRY);
                this.addPropertyOverride(new ResourceLocation(SpartanWeaponryArcanaEx.MOD_ID, "enabled"), (stack, worldIn, entityIn) -> this.getActivated(stack) ? 1 : 0);
            }

            @Override
            public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
                if(this.isInCreativeTab(tab)) {
                    items.add(Utils.setUnbreakable(new ItemStack(this)));
                }
            }

            @Override
            public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
                if(entity instanceof EntityLivingBase) {
                    this.setActivatedState(stack, stack == ((EntityLivingBase) entity).getHeldItemOffhand());
                }

                Binding binding = this.getBinding(stack);
                if (binding == null) {
                    this.setActivatedState(stack, false);
                } else {
                    if (entity instanceof EntityPlayer && this.getActivated(stack) && world.getTotalWorldTime() % 80L == 0L) {
                        NetworkHelper.getSoulNetwork(binding).syphonAndDamage((EntityPlayer)entity, SoulTicket.item(stack, world, entity, 20));
                    }

                }
            }

            @Override
            public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
                String desc = StringHelper.getTranslationKey(this.getRegistryName().getPath(), "tooltip", "desc");
                if(I18n.hasKey(desc)) {
                    tooltip.add(I18n.format(desc));
                }
                tooltip.add(TextHelper.localize("tooltip.bloodmagic." + (this.getActivated(stack) ? "activated" : "deactivated")));
                Binding binding = this.getBinding(stack);
                if(binding != null) {
                    tooltip.add(TextHelper.localizeEffect("tooltip.bloodmagic.currentOwner", binding.getOwnerName()));
                }
                super.addInformation(stack, worldIn, tooltip, flagIn);
            }

            @Override
            public void addEffectsTooltip(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
                tooltip.add(TextFormatting.DARK_AQUA + I18n.format(StringHelper.getTranslationKey("material_bonus", "tooltip")));
                tooltip.add(TextFormatting.GREEN + "- " + I18n.format(StringHelper.getTranslationKey("material_bound", "tooltip")));
                if(GuiScreen.isShiftKeyDown()) {
                    tooltip.add(TextFormatting.ITALIC+ "  " + I18n.format(StringHelper.getTranslationKey("material_bound_shield", "tooltip", "desc")));
                }
            }

            @Override
            public EnumAction getItemUseAction(ItemStack stack) {
                return ((IActivatable) stack.getItem()).getActivated(stack) ? super.getItemUseAction(stack) : EnumAction.NONE;
            }
        }

        return new ItemBoundShieldBasic();
    }
}
