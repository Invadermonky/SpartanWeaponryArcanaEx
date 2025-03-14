package com.invadermonky.spartanweaponryarcanaex.items.sentient;

import WayofTime.bloodmagic.core.RegistrarBloodMagicItems;
import WayofTime.bloodmagic.soul.EnumDemonWillType;
import WayofTime.bloodmagic.soul.PlayerDemonWillHandler;
import WayofTime.bloodmagic.util.helper.TextHelper;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.client.CreativeTabSE;
import com.invadermonky.spartanweaponryarcanaex.items.base.ItemParryingDaggerSE;
import com.invadermonky.spartanweaponryarcanaex.materials.bloodmagic.ISpartanWillWeapon;
import com.invadermonky.spartanweaponryarcanaex.materials.bloodmagic.WeaponPropertySentient;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibNames;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class ItemSentientParryingDagger extends ItemParryingDaggerSE implements ISpartanWillWeapon {
    protected final double baseAttackDamage;
    protected final double damageMultiplier;
    protected final double baseAttackSpeed;

    public ItemSentientParryingDagger() {
        super(LibNames.sentient, WeaponPropertySentient.SENTIENT_MATERIAL_EX);
        this.setCreativeTab(CreativeTabSE.TAB_BLOOD_WEAPONRY);
        this.addPropertyOverride(new ResourceLocation(SpartanWeaponryArcanaEx.MOD_ID, "sentient"), (stack, worldIn, entityIn) -> this.getCurrentType(stack).ordinal());
        this.setNoReequipAnimation();
        this.damageMultiplier = ConfigHandler.damageMultiplierParryingDagger;
        this.baseAttackDamage = Math.max(0.5F, this.materialEx.getAttackDamage() * this.damageMultiplier + ConfigHandler.damageBaseParryingDagger - 1.0F);
        this.baseAttackSpeed = ConfigHandler.speedParryingDagger;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == RegistrarBloodMagicItems.ITEM_DEMON_CRYSTAL || super.getIsRepairable(toRepair, repair);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        this.recalculatePowers(player.getHeldItem(hand), world, player);
        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity targetEntity) {
        this.recalculatePowers(stack, player.world, player);
        double drain = this.getDrainOfActivatedWeapon(stack);
        if(drain > 0.0) {
            EnumDemonWillType type = this.getCurrentType(stack);
            double soulsRemaining = PlayerDemonWillHandler.getTotalDemonWill(type, player);
            if(drain > soulsRemaining) {
                return false;
            }
            PlayerDemonWillHandler.consumeDemonWill(type, player, drain);
        }
        return super.onLeftClickEntity(stack, player, targetEntity);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(stack.hasTagCompound()) {
            tooltip.add(TextHelper.localizeEffect("tooltip.bloodmagic.currentType." + this.getCurrentType(stack).getName().toLowerCase()));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = HashMultimap.create();
        if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.getBaseAttackDamage(), 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", this.getBaseAttackSpeed() - 4.0, 0));
        }

        return multimap;
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = HashMultimap.create();
        if (slot == EntityEquipmentSlot.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.getDamageOfActivatedWeapon(stack), 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", this.getAttackSpeedOfWeapon(stack) - 4.0, 0));
            multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(new UUID(0L, 31818145L), "Weapon modifier", this.getHealthBonusOfWeapon(stack), 0));
            multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(new UUID(0L, 4218052L), "Weapon modifier", this.getMovementSpeedOfWeapon(stack), 2));
        }

        return multimap;
    }

    @Override
    public void syncWeaponValues(ItemStack stack) {
        this.attackDamage = (float) this.getDamageOfActivatedWeapon(stack);
        this.attackSpeed = this.getAttackSpeedOfWeapon(stack);
    }

    @Override
    public double getBaseAttackDamage() {
        return this.baseAttackDamage;
    }

    @Override
    public double getDamageMultiplier() {
        return this.damageMultiplier;
    }

    @Override
    public double getBaseAttackSpeed() {
        return this.baseAttackSpeed;
    }

    @Override
    public double getBonusDamageMultiplier() {
        return MathHelper.clamp(this.getDamageMultiplier(), 0, 1.0);
    }
}
