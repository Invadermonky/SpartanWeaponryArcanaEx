package com.invadermonky.spartanweaponryarcanaex.materials.bewitchment;

import com.bewitchment.registry.ModObjects;
import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.materials.util.WeaponPropertyWithCallbackSE;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public class WeaponPropertyColdIron extends WeaponPropertyWithCallbackSE {
    public static final String COLD_IRON_MATERIAL = "material_cold_iron";
    public static final WeaponPropertyColdIron COLD_IRON_PROPERTY;
    public static final ToolMaterialEx COLD_IRON_MATERIAL_EX;

    public WeaponPropertyColdIron() {
        super(COLD_IRON_MATERIAL);
    }

    @Override
    public float modifyDamageDealt(ToolMaterialEx material, float baseDamage, DamageSource source, EntityLivingBase attacker, EntityLivingBase victim) {
        /* Bewitchment handles the increased damage internally so this is unneeded.
        float amount = BewitchmentAPI.COLD_IRON_WEAKNESS.get(victim);
        return amount > 1.0f ? baseDamage * amount : baseDamage;
         */
        return baseDamage;
    }
    static {
        COLD_IRON_PROPERTY = new WeaponPropertyColdIron();
        COLD_IRON_MATERIAL_EX = new ToolMaterialEx(
                COLD_IRON_MATERIAL,
                ModObjects.TOOL_COLD_IRON,
                "ingotColdIron",
                SpartanWeaponryArcanaEx.MOD_ID,
                ModObjects.TOOL_COLD_IRON.getAttackDamage(),
                COLD_IRON_PROPERTY
        );
    }
}
