package com.invadermonky.spartanweaponryarcanaex.materials.bloodmagic;

import WayofTime.bloodmagic.iface.IMultiWillTool;
import WayofTime.bloodmagic.soul.EnumDemonWillType;
import WayofTime.bloodmagic.soul.PlayerDemonWillHandler;
import WayofTime.bloodmagic.util.helper.NBTHelper;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibTags;
import com.invadermonky.spartanweaponryarcanaex.util.libs.ModIds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;

import static WayofTime.bloodmagic.util.Constants.NBT.WILL_TYPE;
import static com.invadermonky.spartanweaponryarcanaex.util.libs.LibAttributes.Shields.*;

@Optional.Interface(modid = ModIds.ConstIds.blood_magic, iface = "WayofTime.bloodmagic.iface.IMultiWillTool", striprefs = true)
public interface ISpartanWillShield extends IMultiWillTool {
    default void recalculatePowers(ItemStack stack, World world, EntityPlayer player) {
        EnumDemonWillType type = PlayerDemonWillHandler.getLargestWillType(player);
        double soulsRemaining = PlayerDemonWillHandler.getTotalDemonWill(type, player);
        this.recalculatePowers(stack, type, soulsRemaining);
    }

    default void recalculatePowers(ItemStack stack, EnumDemonWillType type, double will) {
        this.setCurrentType(stack, will > 0.0 ? type : EnumDemonWillType.DEFAULT);
        int level = this.getLevel(stack, will);
        this.setCurrentWillBracket(stack, level);
        this.setBonusArmorOfShield(stack, this.getExtraArmor(type, level));
        this.setBonusArmorToughnessOfShield(stack, this.getExtraArmorToughness(type, level));
        this.setDamageOfActivatedShield(stack, this.getExtraDamage(type, level));
        this.setMovementSpeedOfShield(stack, this.getMovementSpeed(type, level));
    }

    @Optional.Method(modid = ModIds.ConstIds.blood_magic)
    @Override
    default EnumDemonWillType getCurrentType(ItemStack stack) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        return !tag.hasKey(WILL_TYPE) ? EnumDemonWillType.DEFAULT : EnumDemonWillType.valueOf(tag.getString(WILL_TYPE).toUpperCase());
    }

    default void setCurrentType(ItemStack stack, EnumDemonWillType type) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        tag.setString(WILL_TYPE, type.toString());
    }

    default int getLevel(ItemStack stack, double soulsRemaining) {
        int level = -1;
        for(int i = 0; i < soulBracket.length; i++) {
            if(soulsRemaining >= (double) soulBracket[i])
                level = i;
        }
        return level;
    }

    default double getExtraArmor(EnumDemonWillType type, int willBracket) {
        return willBracket != -1 ? defaultArmorAdded[willBracket] : 0;
    }

    default double getExtraArmorToughness(EnumDemonWillType type, int willBracket) {
        return willBracket != -1 && type == EnumDemonWillType.STEADFAST ? steadfastToughnessAdded[willBracket] : 0;
    }

    default double getMovementSpeed(EnumDemonWillType type, int willBracket) {
        return willBracket != -1 && type == EnumDemonWillType.VENGEFUL ? vengefulSpeedAdded[willBracket] : 0;
    }

    default double getExtraDamage(EnumDemonWillType type, int willBracket) {
        return willBracket != -1 && type == EnumDemonWillType.DESTRUCTIVE ? destructiveDamageAdded[willBracket] : 0;
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

    default double getBonusArmorOfShield(ItemStack stack) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        return tag.getDouble(LibTags.soul_shield_armor);
    }

    default void setBonusArmorOfShield(ItemStack stack, double armor) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        tag.setDouble(LibTags.soul_shield_armor, armor);
    }

    default double getBonusArmorToughnessOfShield(ItemStack stack) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        return tag.getDouble(LibTags.soul_shield_armor_toughness);
    }

    default void setBonusArmorToughnessOfShield(ItemStack stack, double toughness) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        tag.setDouble(LibTags.soul_shield_armor_toughness, toughness);
    }

    default double getDamageOfActivatedShield(ItemStack stack) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        return tag.getDouble(LibTags.soul_shield_damage);
    }

    default void setDamageOfActivatedShield(ItemStack stack, double damage) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        tag.setDouble(LibTags.soul_shield_damage, damage);
    }

    default double getMovementSpeedOfShield(ItemStack stack) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        return tag.getDouble(LibTags.soul_shield_speed);
    }

    default void setMovementSpeedOfShield(ItemStack stack, double speed) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        tag.setDouble(LibTags.soul_shield_speed, speed);
    }
}
