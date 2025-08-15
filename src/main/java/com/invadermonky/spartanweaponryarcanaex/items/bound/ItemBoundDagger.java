package com.invadermonky.spartanweaponryarcanaex.items.bound;

import WayofTime.bloodmagic.core.data.Binding;
import WayofTime.bloodmagic.util.helper.TextHelper;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.items.base.ItemDaggerSE;
import com.invadermonky.spartanweaponryarcanaex.materials.bloodmagic.ISpartanBoundWeapon;
import com.invadermonky.spartanweaponryarcanaex.materials.bloodmagic.WeaponPropertyBound;
import com.invadermonky.spartanweaponryarcanaex.util.StringHelper;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibNames;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBoundDagger extends ItemDaggerSE implements ISpartanBoundWeapon {
    protected final double baseAttackDamage;
    protected final double baseAttackSpeed;
    protected float directAttackDamage;

    public ItemBoundDagger() {
        super(LibNames.bound, WeaponPropertyBound.BOUND_MATERIAL_EX);
        this.addPropertyOverride(new ResourceLocation(SpartanWeaponryArcanaEx.MOD_ID, "enabled"), (stack, worldIn, entityIn) -> this.getActivated(stack) ? 1 : 0);
        this.setNoReequipAnimation();
        this.baseAttackDamage = Math.max(0.5F, this.materialEx.getAttackDamage() * ConfigHandler.damageMultiplierDagger + ConfigHandler.damageBaseDagger - 1.0F);
        this.baseAttackSpeed = ConfigHandler.speedDagger;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        ItemStack stack = playerIn.getHeldItem(hand);
        if(playerIn.isSneaking()) {
            this.setActivatedState(stack, !this.getActivated(stack));
            return new ActionResult<>(EnumActionResult.SUCCESS, stack);
        }
        this.syncWeaponValues(stack);
        if(this.getActivated(stack)) {
            return super.onItemRightClick(worldIn, playerIn, hand);
        }
        return new ActionResult<>(EnumActionResult.PASS, stack);
    }

    @SideOnly(Side.CLIENT)
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
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot slot) {
        Multimap<String, AttributeModifier> multimap = HashMultimap.create();
        if(slot == EntityEquipmentSlot.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 2.0, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", this.getBaseAttackSpeed() - 4.0, 0));
        }
        return multimap;
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = HashMultimap.create();
        if(slot == EntityEquipmentSlot.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.getActivated(stack) ? this.getDirectAttackDamage() : 2.0, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", this.getBaseAttackSpeed() - 4.0, 0));
        }
        return multimap;
    }

    @Override
    public float getDirectAttackDamage() {
        return this.directAttackDamage;
    }

    @Override
    public void syncWeaponValues(ItemStack stack) {
        this.directAttackDamage = (float) this.getBaseAttackDamage();
        this.attackSpeed = this.getBaseAttackSpeed();
    }

    @Override
    public double getBaseAttackDamage() {
        return this.baseAttackDamage;
    }

    @Override
    public double getBaseAttackSpeed() {
        return this.baseAttackSpeed;
    }
}
