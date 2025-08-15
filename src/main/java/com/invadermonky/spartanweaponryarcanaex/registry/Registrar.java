package com.invadermonky.spartanweaponryarcanaex.registry;

import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.api.IModCompat;
import com.invadermonky.spartanweaponryarcanaex.compat.*;
import com.invadermonky.spartanweaponryarcanaex.util.libs.ModIds;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = SpartanWeaponryArcanaEx.MOD_ID)
public class Registrar {
    private static List<IModCompat> loadedCompat;

    public static void initializeModCompat() {
        if (ModIds.astral_sorcery.isLoaded)
            loadedCompat.add(new AstralSorceryCompat());
        if (ModIds.bewitchment.isLoaded)
            loadedCompat.add(new BewitchmentCompat());
        if (ModIds.blood_magic.isLoaded)
            loadedCompat.add(new BloodMagicCompat());
        if (ModIds.natures_aura.isLoaded)
            loadedCompat.add(new NaturesAuraCompat());
        if (ModIds.roots.isLoaded)
            loadedCompat.add(new RootsCompat());
    }

    public static List<IModCompat> getLoadedCompat() {
        if (loadedCompat == null) {
            loadedCompat = new ArrayList<>();
            initializeModCompat();
        }
        return loadedCompat;
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        getLoadedCompat().forEach(IModCompat::initializeWeapons);
        ModItemsSE.loadOrderedItemsToRegister();
        IForgeRegistry<Item> registry = event.getRegistry();
        ModItemsSE.getItemsToRegister().stream().filter(Objects::nonNull).forEach(registry::register);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ModItemsSE.getItemsToRegister().stream().filter(Objects::nonNull).forEach(item -> {
            ModelResourceLocation loc = new ModelResourceLocation(item.getRegistryName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(item, 0, loc);
        });
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        getLoadedCompat().forEach(modCompat -> modCompat.initializeRecipes(event.getRegistry()));
    }
}
