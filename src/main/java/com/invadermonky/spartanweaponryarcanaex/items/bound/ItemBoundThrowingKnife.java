package com.invadermonky.spartanweaponryarcanaex.items.bound;

import WayofTime.bloodmagic.core.data.Binding;
import WayofTime.bloodmagic.util.helper.TextHelper;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.items.base.ItemThrowingKnifeSE;
import com.invadermonky.spartanweaponryarcanaex.materials.bloodmagic.ISpartanBoundWeapon;
import com.invadermonky.spartanweaponryarcanaex.materials.bloodmagic.WeaponPropertyBound;
import com.invadermonky.spartanweaponryarcanaex.util.StringHelper;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibNames;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBoundThrowingKnife extends ItemThrowingKnifeSE implements ISpartanBoundWeapon {
    protected final double baseAttackDamage;
    protected final double baseAttackSpeed;
    protected float directAttackDamage;

    public ItemBoundThrowingKnife() {
        super(LibNames.bound, WeaponPropertyBound.BOUND_MATERIAL_EX);
        this.addPropertyOverride(new ResourceLocation(SpartanWeaponryArcanaEx.MOD_ID, "enabled"), (stack, worldIn, entityIn) -> this.getActivated(stack) ? 1 : 0);
        this.setNoReequipAnimation();
        this.baseAttackDamage = Math.max(0.5f, this.materialEx.getAttackDamage() * ConfigHandler.damageMultiplierThrowingKnife + ConfigHandler.damageBaseThrowingKnife - 1.0f);
        this.baseAttackSpeed = ConfigHandler.meleeSpeedThrowingKnife;
    }

    @Override
    public @NotNull ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        ItemStack stack = playerIn.getHeldItem(hand);
        if (playerIn.isSneaking()) {
            this.setActivatedState(stack, !this.getActivated(stack));
        }
        this.syncWeaponValues(stack);
        return this.getActivated(stack) ? super.onItemRightClick(worldIn, playerIn, hand) : new ActionResult<>(EnumActionResult.FAIL, stack);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        return true;
    }

    @Override
    public float getDirectAttackDamage() {
        return this.directAttackDamage;
    }

    @Override
    public @NotNull Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot slot) {
        Multimap<String, AttributeModifier> multimap = HashMultimap.create();
        if (slot == EntityEquipmentSlot.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 2.0, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", this.getBaseAttackSpeed() - 4.0, 0));
        }
        return multimap;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        super.hitEntity(stack, target, attacker);
        return true;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        if (this.getMaxItemUseDuration(stack) - entityLiving.getItemInUseCount() <= 2) {
            return;
        }
        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
    }

    @Override
    public @NotNull EnumAction getItemUseAction(ItemStack stack) {
        return this.getActivated(stack) ? super.getItemUseAction(stack) : EnumAction.NONE;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        String desc = StringHelper.getTranslationKey(this.getRegistryName().getPath(), "tooltip", "desc");
        if (I18n.hasKey(desc)) {
            tooltip.add(I18n.format(desc));
        }
        tooltip.add(TextHelper.localize("tooltip.bloodmagic." + (this.getActivated(stack) ? "activated" : "deactivated")));
        Binding binding = this.getBinding(stack);
        if (binding != null) {
            tooltip.add(TextHelper.localizeEffect("tooltip.bloodmagic.currentOwner", binding.getOwnerName()));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public @NotNull Multimap<String, AttributeModifier> getAttributeModifiers(@NotNull EntityEquipmentSlot slot, @NotNull ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = HashMultimap.create();
        if (slot == EntityEquipmentSlot.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.getActivated(stack) ? this.getDirectAttackDamage() : 2.0, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", this.getBaseAttackSpeed() - 4.0, 0));
        }
        return multimap;
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
