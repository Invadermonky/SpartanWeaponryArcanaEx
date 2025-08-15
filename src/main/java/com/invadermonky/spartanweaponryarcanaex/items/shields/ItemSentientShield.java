package com.invadermonky.spartanweaponryarcanaex.items.shields;

import WayofTime.bloodmagic.core.RegistrarBloodMagicItems;
import WayofTime.bloodmagic.soul.EnumDemonWillType;
import WayofTime.bloodmagic.util.helper.TextHelper;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.client.CreativeTabSE;
import com.invadermonky.spartanweaponryarcanaex.materials.bloodmagic.ISpartanWillShield;
import com.invadermonky.spartanweaponryarcanaex.util.StringHelper;
import com.oblivioussp.spartanshields.item.ItemShieldBasic;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class ItemSentientShield {
    protected final String unlocName;

    public ItemSentientShield(String unlocName) {
        this.unlocName = unlocName;
    }

    public ItemShield createShield() {
        class ItemSentientShieldBasic extends ItemShieldBasic implements ISpartanWillShield {
            public ItemSentientShieldBasic() {
                super(unlocName, 600, RegistrarBloodMagicItems.SOUL_TOOL_MATERIAL);
                this.setCreativeTab(CreativeTabSE.TAB_SE);
                this.addPropertyOverride(new ResourceLocation(SpartanWeaponryArcanaEx.MOD_ID, "sentient"), (stack, worldIn, entityIn) -> this.getCurrentType(stack).ordinal());
            }

            @Override
            public void onUpdate(@NotNull ItemStack stack, @NotNull World worldIn, @NotNull Entity entityIn, int itemSlot, boolean isSelected) {
                if (entityIn instanceof EntityLivingBase && ItemStack.areItemStacksEqual(((EntityLivingBase) entityIn).getHeldItemOffhand(), stack)) {
                    EnumDemonWillType type = this.getCurrentType(stack);
                    if (type == EnumDemonWillType.CORROSIVE) {
                        if (((EntityLivingBase) entityIn).isPotionActive(MobEffects.POISON)) {
                            ((EntityLivingBase) entityIn).removePotionEffect(MobEffects.POISON);
                        }
                        if (((EntityLivingBase) entityIn).isPotionActive(MobEffects.WITHER)) {
                            ((EntityLivingBase) entityIn).removePotionEffect(MobEffects.WITHER);
                        }
                    }
                }
                super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
            }

            @Override
            public @NotNull Multimap<String, AttributeModifier> getAttributeModifiers(@NotNull EntityEquipmentSlot slot, @NotNull ItemStack stack) {
                Multimap<String, AttributeModifier> multimap = HashMultimap.create();
                if (slot == EntityEquipmentSlot.OFFHAND) {
                    //  Default: bonus armor based on will (any)
                    multimap.put(SharedMonsterAttributes.ARMOR.getName(), new AttributeModifier(UUID.fromString("f9c58d75-afcf-43ce-956e-4fb794356ec2"), "Armor modifier", this.getBonusArmorOfShield(stack), 0));
                    //  Corrosive: Poison immunity (done)
                    //  Destructive: bonus damage
                    multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(new UUID(0L, 96721L), "Armor modifier", this.getDamageOfActivatedShield(stack), 0));            //  Steadfast: bonus health
                    multimap.put(SharedMonsterAttributes.ARMOR_TOUGHNESS.getName(), new AttributeModifier(UUID.fromString("2ea81026-04ac-4258-885f-b0e6a2cdc3b8"), "Armor modifier", this.getBonusArmorToughnessOfShield(stack), 0));
                    //  Vengeful: bonus speed
                    multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(new UUID(0L, 94021L), "Armor modifier", this.getMovementSpeedOfShield(stack), 2));
                }
                return multimap;
            }

            @Override
            public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
                return oldStack.getItem() != newStack.getItem() || slotChanged;
            }

            @Override
            public @NotNull ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull EntityPlayer playerIn, @NotNull EnumHand handIn) {
                this.recalculatePowers(playerIn.getHeldItem(handIn), worldIn, playerIn);
                return super.onItemRightClick(worldIn, playerIn, handIn);
            }

            @Override
            public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
                if (stack.hasTagCompound()) {
                    tooltip.add(TextHelper.localizeEffect("tooltip.bloodmagic.currentType." + this.getCurrentType(stack).getName().toLowerCase()));
                }
                super.addInformation(stack, worldIn, tooltip, flagIn);
            }

            @Override
            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == RegistrarBloodMagicItems.ITEM_DEMON_CRYSTAL || super.getIsRepairable(toRepair, repair);
            }

            @Override
            public void addEffectsTooltip(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
                tooltip.add(TextFormatting.DARK_AQUA + I18n.format(StringHelper.getTranslationKey("material_bonus", "tooltip")));
                tooltip.add(TextFormatting.GREEN + "- " + I18n.format(StringHelper.getTranslationKey("material_sentient", "tooltip")));
                if (GuiScreen.isShiftKeyDown()) {
                    tooltip.add(TextFormatting.ITALIC + "  " + I18n.format(StringHelper.getTranslationKey("material_sentient", "tooltip", "desc")));
                }
            }
        }
        return new ItemSentientShieldBasic();
    }

}
