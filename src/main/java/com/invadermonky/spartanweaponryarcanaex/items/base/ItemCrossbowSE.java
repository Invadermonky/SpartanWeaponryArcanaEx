package com.invadermonky.spartanweaponryarcanaex.items.base;

import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.client.CreativeTabSE;
import com.invadermonky.spartanweaponryarcanaex.materials.util.WeaponPropertyWithCallbackSE;
import com.invadermonky.spartanweaponryarcanaex.util.StringHelper;
import com.oblivioussp.spartanweaponry.api.IWeaponCallback;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityBolt;
import com.oblivioussp.spartanweaponry.item.ItemBolt;
import com.oblivioussp.spartanweaponry.item.ItemCrossbow;
import com.oblivioussp.spartanweaponry.util.Quaternion;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class ItemCrossbowSE extends ItemCrossbow {
    protected boolean doReequip = true;
    protected boolean hasCustomDisplayName = false;

    public ItemCrossbowSE(String materialName, ToolMaterialEx material) {
        super("crossbow_" + materialName, SpartanWeaponryArcanaEx.MOD_ID, material);
        this.setCreativeTab(CreativeTabSE.TAB_SE);
    }

    public ItemCrossbowSE(String materialName, ToolMaterialEx material, IWeaponCallback weaponCallback) {
        super("crossbow_" + materialName, SpartanWeaponryArcanaEx.MOD_ID, material, weaponCallback);
    }

    public ItemCrossbowSE setNoReequipAnimation() {
        this.doReequip = false;
        return this;
    }

    public ItemCrossbowSE setHasCustomDisplayName() {
        this.hasCustomDisplayName = true;
        return this;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return this.hasCustomDisplayName ? I18n.translateToLocalFormatted(StringHelper.getTranslationKey(this.getRegistryName().getPath(), "item", "name")) : super.getItemStackDisplayName(stack);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return this.doReequip ? super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged) : oldStack.getItem() != newStack.getItem() || slotChanged || this.isLoaded(oldStack) != this.isLoaded(newStack);
    }

    @Override
    protected void spawnProjectile(ItemStack crossbow, ItemBolt boltItem, ItemStack boltStack, World world, EntityPlayer player, boolean noPickup, float inaccuracyModifier, float projectileAngle) {
        EntityBolt bolt = boltItem.createBolt(world, boltStack, player);
        bolt.setIsCritical(true);
        Vec3d lookVec = player.getLook(1.0F);
        Vec3d vector = new Vec3d(lookVec.x, lookVec.y, lookVec.z);
        if (projectileAngle != 0.0F) {
            Vec3d playerUpVec = this.calculateEntityViewVectorSE(player.rotationPitch - 90.0F, player.rotationYaw);
            Quaternion quat = new Quaternion(playerUpVec, projectileAngle, true);
            vector = quat.transformVector(lookVec);
        }

        bolt.shoot(vector.x, vector.y, vector.z, this.getBoltSpeed() * 3.0F, inaccuracyModifier);
        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, crossbow);
        if (j > 0) {
            bolt.setDamage(bolt.getDamage() + (double)j * 0.5 + 0.5);
        }

        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, crossbow);
        if (k > 0) {
            bolt.setKnockbackStrength(k);
        }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, crossbow) > 0) {
            bolt.setFire(100);
        }

        if (noPickup || projectileAngle != 0.0F) {
            bolt.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
        }

        if(this.material != null) {
            for(WeaponProperty property : this.material.getAllWeaponProperties()) {
                if(property instanceof WeaponPropertyWithCallbackSE) {
                    ((WeaponPropertyWithCallbackSE) property).applyAttributeToArrow(world, bolt, player, crossbow);
                }
            }
        }
        world.spawnEntity(bolt);
    }

    public Vec3d calculateEntityViewVectorSE(float pitch, float yaw) {
        float degToRad = 0.017453292F;
        float yawCos = MathHelper.cos(-yaw * degToRad - 3.1415927F);
        float yawSin = MathHelper.sin(-yaw * degToRad - 3.1415927F);
        float pitchCos = -MathHelper.cos(-pitch * degToRad - 3.1415927F);
        float pitchSin = MathHelper.sin(-pitch * degToRad - 3.1415927F);
        return new Vec3d((yawSin * pitchCos), pitchSin, (yawCos * pitchCos));
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        if(this.material != null) {
            for (WeaponProperty property : this.material.getAllWeaponProperties()) {
                if (property instanceof WeaponPropertyWithCallbackSE) {
                    ICapabilityProvider capability = ((WeaponPropertyWithCallbackSE) property).initCapabilities(stack, nbt);
                    if(capability != null)
                        return capability;
                }
            }
        }
        return super.initCapabilities(stack, nbt);
    }
}
