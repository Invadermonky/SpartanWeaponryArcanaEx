package com.invadermonky.spartanweaponryarcanaex;

import com.invadermonky.spartanweaponryarcanaex.proxy.CommonProxy;
import com.invadermonky.spartanweaponryarcanaex.registry.ModEntitiesSE;
import com.invadermonky.spartanweaponryarcanaex.util.LogHelper;
import com.invadermonky.spartanweaponryarcanaex.util.libs.ModIds.ConstIds;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = SpartanWeaponryArcanaEx.MOD_ID,
        name = SpartanWeaponryArcanaEx.MOD_NAME,
        version = SpartanWeaponryArcanaEx.VERSION,
        acceptedMinecraftVersions = SpartanWeaponryArcanaEx.MC_VERSION,
        dependencies = SpartanWeaponryArcanaEx.DEPENDENCIES
)
public class SpartanWeaponryArcanaEx {
    public static final String MOD_ID = "spartanweaponryarcanaex";
    public static final String MOD_NAME = "Spartan Weaponry Arcana: Expanded";
    public static final String VERSION = "1.12.2-1.0.0";
    public static final String MC_VERSION = "[1.12.2]";
    public static final String DEPENDENCIES = "required-after:spartanweaponry@[1.6.0,)" +
            ";after:" + ConstIds.spartan_shields +
            ";after:" + ConstIds.baubles +
            ";after:" + ConstIds.astral_sorcery +
            ";after:" + ConstIds.bewitchment +
            ";after:" + ConstIds.blood_magic +
            ";after:" + ConstIds.natures_aura +
            ";after:" + ConstIds.roots
            ;

    public static final String ProxyClientClass = "com.invadermonky." + MOD_ID + ".proxy.ClientProxy";
    public static final String ProxyServerClass = "com.invadermonky." + MOD_ID + ".proxy.CommonProxy";

    @Mod.Instance(MOD_ID)
    public static SpartanWeaponryArcanaEx instance;

    @SidedProxy(clientSide = ProxyClientClass, serverSide = ProxyServerClass)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LogHelper.info("Starting " + MOD_NAME);
        proxy.preInit(event);
        ModEntitiesSE.registerEntities();
        LogHelper.debug("Finished preInit phase.");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        LogHelper.debug("Finished init phase.");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
        LogHelper.debug("Finished postInit phase.");
    }
}
