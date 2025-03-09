package com.invadermonky.spartanweaponryarcanaex.materials.bloodmagic;

import WayofTime.bloodmagic.iface.IActivatable;
import WayofTime.bloodmagic.iface.IBindable;
import com.invadermonky.spartanweaponryarcanaex.util.libs.ModIds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional;

@Optional.Interface(modid = ModIds.ConstIds.blood_magic, iface = "WayofTime.bloodmagic.iface.IActivatable", striprefs = true)
@Optional.Interface(modid = ModIds.ConstIds.blood_magic, iface = "WayofTime.bloodmagic.iface.IBindable", striprefs = true)
public interface ISpartanBoundWeapon extends IBindable, IActivatable {
    void syncWeaponValues(ItemStack stack);
    double getBaseAttackDamage();
    double getBaseAttackSpeed();
}
