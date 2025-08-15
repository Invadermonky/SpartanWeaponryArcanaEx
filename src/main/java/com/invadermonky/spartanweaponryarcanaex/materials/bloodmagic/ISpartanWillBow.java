package com.invadermonky.spartanweaponryarcanaex.materials.bloodmagic;

import WayofTime.bloodmagic.entity.mob.EntitySentientSpecter;
import WayofTime.bloodmagic.iface.IMultiWillTool;
import WayofTime.bloodmagic.iface.ISentientTool;
import WayofTime.bloodmagic.soul.EnumDemonWillType;
import WayofTime.bloodmagic.soul.PlayerDemonWillHandler;
import WayofTime.bloodmagic.util.helper.NBTHelper;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibTags;
import com.invadermonky.spartanweaponryarcanaex.util.libs.ModIds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;

import java.util.Locale;

import static WayofTime.bloodmagic.util.Constants.NBT.WILL_TYPE;
import static com.invadermonky.spartanweaponryarcanaex.util.libs.LibAttributes.Ranged.soulBracket;
import static com.invadermonky.spartanweaponryarcanaex.util.libs.LibAttributes.Ranged.velocityAdded;

@Optional.Interface(modid = ModIds.ConstIds.blood_magic, iface = "WayofTime.bloodmagic.iface.IMultiWillTool", striprefs = true)
@Optional.Interface(modid = ModIds.ConstIds.blood_magic, iface = "WayofTime.bloodmagic.iface.ISentientTool", striprefs = true)
public interface ISpartanWillBow extends IMultiWillTool, ISentientTool {
    default void recalculatePowers(ItemStack stack, World world, EntityPlayer player) {
        EnumDemonWillType type = PlayerDemonWillHandler.getLargestWillType(player);
        double soulsRemaining = PlayerDemonWillHandler.getTotalDemonWill(type, player);
        this.recalculatePowers(stack, type, soulsRemaining);
    }

    default void recalculatePowers(ItemStack stack, EnumDemonWillType type, double will) {
        this.setCurrentType(stack, type);
        this.setCurrentWillBracket(stack, this.getLevel(will));
    }

    default int getLevel(double souls) {
        int lvl = -1;
        for (int i = 0; i < soulBracket.length; i++) {
            if (souls >= soulBracket[i])
                lvl = i;
        }
        return lvl;
    }

    @Optional.Method(modid = ModIds.ConstIds.blood_magic)
    @Override
    default EnumDemonWillType getCurrentType(ItemStack stack) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        return !tag.hasKey(WILL_TYPE) ? EnumDemonWillType.DEFAULT : EnumDemonWillType.valueOf(tag.getString(WILL_TYPE).toUpperCase(Locale.ENGLISH));
    }

    default void setCurrentType(ItemStack stack, EnumDemonWillType type) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        tag.setString(WILL_TYPE, type.toString());
    }

    default int getCurrentWillBracket(ItemStack stack) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        return tag.getInteger(LibTags.will_bracket);
    }

    default void setCurrentWillBracket(ItemStack stack, int willBracket) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        tag.setInteger(LibTags.will_bracket, willBracket);
    }

    default double getArrowVelocityModifier(EnumDemonWillType type, int willBracket) {
        return willBracket > -1 && type == EnumDemonWillType.VENGEFUL ? velocityAdded[willBracket] : 0.0;
    }

    default double getDrawSpeedModifier(EnumDemonWillType type, int willBracket) {
        return willBracket > -1 && type == EnumDemonWillType.VENGEFUL ? 0 : 0.0;
    }

    default void applySentientArrowAttributes(ItemStack bowStack, EntityPlayer player, EntityArrow entityArrow) {
        if (bowStack.getItem() instanceof ISpartanWillBow) {
            ISpartanWillBow willWeapon = (ISpartanWillBow) bowStack.getItem();
            EnumDemonWillType type = willWeapon.getCurrentType(bowStack);
            int willBracket = willWeapon.getCurrentWillBracket(bowStack);
            NBTTagCompound sentientTag = new NBTTagCompound();
            sentientTag.setInteger(LibTags.will_type, type.ordinal());
            sentientTag.setInteger(LibTags.will_bracket, willBracket);
            entityArrow.getEntityData().setTag(LibTags.sentient_attributes, sentientTag);
        }
    }

    @Optional.Method(modid = ModIds.ConstIds.blood_magic)
    @Override
    default boolean spawnSentientEntityOnDrop(ItemStack droppedStack, EntityPlayer player) {
        World world = player.getEntityWorld();
        if (!world.isRemote) {
            this.recalculatePowers(droppedStack, world, player);
            EnumDemonWillType type = this.getCurrentType(droppedStack);
            double soulsRemaining = PlayerDemonWillHandler.getTotalDemonWill(type, player);
            if (soulsRemaining < 1024.0) {
                return false;
            } else {
                PlayerDemonWillHandler.consumeDemonWill(type, player, 100.0);
                EntitySentientSpecter specterEntity = new EntitySentientSpecter(world);
                specterEntity.setPosition(player.posX, player.posY, player.posZ);
                world.spawnEntity(specterEntity);
                specterEntity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, droppedStack.copy());
                specterEntity.setType(this.getCurrentType(droppedStack));
                specterEntity.setOwner(player);
                specterEntity.setTamed(true);
                return true;
            }
        } else {
            return false;
        }
    }
}
