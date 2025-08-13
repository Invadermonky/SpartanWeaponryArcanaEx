package com.invadermonky.spartanweaponryarcanaex.items.bound;

import WayofTime.bloodmagic.core.data.Binding;
import WayofTime.bloodmagic.core.data.SoulTicket;
import WayofTime.bloodmagic.iface.IBindable;
import WayofTime.bloodmagic.util.helper.NetworkHelper;
import WayofTime.bloodmagic.util.helper.TextHelper;
import com.invadermonky.spartanweaponryarcanaex.items.base.ItemCrossbowSE;
import com.invadermonky.spartanweaponryarcanaex.materials.bloodmagic.WeaponPropertyBound;
import com.invadermonky.spartanweaponryarcanaex.util.StringHelper;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibNames;
import com.invadermonky.spartanweaponryarcanaex.util.libs.ModIds;
import com.oblivioussp.spartanweaponry.init.ItemRegistrySW;
import com.oblivioussp.spartanweaponry.item.ItemBolt;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;

import javax.annotation.Nullable;
import java.util.List;

@Optional.Interface(modid = ModIds.ConstIds.blood_magic, iface = "WayofTime.bloodmagic.iface.IBindable", striprefs = true)
public class ItemBoundCrossbow extends ItemCrossbowSE implements IBindable {
    public static int lpDrainPerShot = 150;

    public ItemBoundCrossbow() {
        super(LibNames.bound, WeaponPropertyBound.BOUND_MATERIAL_EX, WeaponPropertyBound.BOUND_PROPERTY);
        this.setNoReequipAnimation();
        this.setHasCustomDisplayName();
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment != Enchantments.INFINITY || super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
        if(entityLiving instanceof EntityPlayer && !worldIn.isRemote) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            Binding binding = this.getBinding(stack);
            if (binding != null) {
                NetworkHelper.getSoulNetwork(binding).syphonAndDamage(player, SoulTicket.item(stack, player.world, player, lpDrainPerShot));
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        String desc = StringHelper.getTranslationKey(this.getRegistryName().getPath(), "tooltip", "desc");
        if(I18n.hasKey(desc)) {
            tooltip.add(I18n.format(desc));
        }
        Binding binding = this.getBinding(stack);
        if(binding != null) {
            tooltip.add(TextHelper.localizeEffect("tooltip.bloodmagic.currentOwner", binding.getOwnerName()));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    protected ItemStack findAmmo(EntityPlayer player) {
        if (this.isBolt(player.getHeldItem(EnumHand.OFF_HAND))) {
            return player.getHeldItem(EnumHand.OFF_HAND);
        } else if (this.isBolt(player.getHeldItem(EnumHand.MAIN_HAND))) {
            return player.getHeldItem(EnumHand.MAIN_HAND);
        }

        ItemStack bowStack;
        if(player.getHeldItemMainhand().getItem() == this) {
            bowStack = player.getHeldItemMainhand();
        } else if(player.getHeldItemOffhand().getItem() == this) {
            bowStack = player.getHeldItemOffhand();
        } else {
            return ItemStack.EMPTY;
        }
        return this.getBinding(bowStack) != null ? new ItemStack(ItemRegistrySW.boltSpectral) : ItemStack.EMPTY;
    }

    @Override
    protected void spawnProjectile(ItemStack crossbow, ItemBolt boltItem, ItemStack boltStack, World world, EntityPlayer player, boolean noPickup, float inaccuracyModifier, float projectileAngle) {
        if (!this.isBolt(player.getHeldItem(EnumHand.OFF_HAND)) && !this.isBolt(player.getHeldItem(EnumHand.MAIN_HAND))) {
            super.spawnProjectile(crossbow, boltItem, boltStack, world, player, true, inaccuracyModifier, projectileAngle);
        }
        super.spawnProjectile(crossbow, boltItem, boltStack, world, player, noPickup, inaccuracyModifier, projectileAngle);
    }
}
