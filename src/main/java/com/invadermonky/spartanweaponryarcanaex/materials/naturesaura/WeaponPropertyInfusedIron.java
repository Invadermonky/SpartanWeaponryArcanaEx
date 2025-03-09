package com.invadermonky.spartanweaponryarcanaex.materials.naturesaura;

import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.materials.util.WeaponPropertyWithCallbackSE;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibNames;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import de.ellpeck.naturesaura.items.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class WeaponPropertyInfusedIron extends WeaponPropertyWithCallbackSE {
    public static final WeaponPropertyInfusedIron INFUSED_IRON_PROPERTY;
    public static final ToolMaterialEx INFUSED_IRON_MATERIAL_EX;

    static {
        INFUSED_IRON_PROPERTY = new WeaponPropertyInfusedIron();
        INFUSED_IRON_MATERIAL_EX = new ToolMaterialEx(
                LibNames.material_infused_iron,
                ModItems.TOOL_INFUSED,
                "ingotInfusedIron",
                SpartanWeaponryArcanaEx.MOD_ID,
                ModItems.TOOL_INFUSED.getAttackDamage(),
                WeaponPropertyAuraRepair.AURA_REPAIR_PROPERTY,
                INFUSED_IRON_PROPERTY
        );
    }

    public WeaponPropertyInfusedIron() {
        super(LibNames.material_infused_iron);
    }

    @Override
    public void onHitEntity(ToolMaterialEx material, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, Entity projectile) {
        target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 2));
    }
}
