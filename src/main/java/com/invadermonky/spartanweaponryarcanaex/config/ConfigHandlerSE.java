package com.invadermonky.spartanweaponryarcanaex.config;

import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import net.minecraftforge.common.config.Config;

@Config(modid = SpartanWeaponryArcanaEx.MOD_ID)
public class ConfigHandlerSE {
    public static AstralSorceryConfigSE astral_sorcery = new AstralSorceryConfigSE();
    public static BewitchmentConfigSE bewitchment = new BewitchmentConfigSE();
    public static BloodMagicConfigSE blood_magic = new BloodMagicConfigSE();
    public static NaturesAuraConfigSE natures_aura = new NaturesAuraConfigSE();
    public static RootsConfigSE roots = new RootsConfigSE();


    public static class AstralSorceryConfigSE {
        @Config.RequiresMcRestart
        @Config.Comment("Enables the Astral Sorcery crystal and infused crystal Spartan Weaponry variants.")
        public boolean enableCrystalWeapons = true;
    }

    public static class BewitchmentConfigSE {
        @Config.RequiresMcRestart
        @Config.Comment("Enables the Bewitchment cold iron Spartan Weapon variants.")
        public boolean enableColdIronWeapons = true;

        @Config.RequiresMcRestart
        @Config.Comment("Changes the boring crafting table recipes into Frostfire item conversion recipes.")
        public boolean useFrostfireRecipes = true;
    }

    public static class BloodMagicConfigSE {
        @Config.RequiresMcRestart
        @Config.Comment("Enables the Blood Magic Bound Spartan Weaponry variants.")
        public boolean enableBoundWeapons = true;

        @Config.RequiresMcRestart
        @Config.Comment("Enables the Blood Magic sentient Spartan Weaponry variants.")
        public boolean enableSentientWeapons = true;
    }

    public static class NaturesAuraConfigSE {
        @Config.RequiresMcRestart
        @Config.Comment("Enables the Nature's Aura infused iron (Botanist's) Spartan Weapon variants.")
        public boolean enableInfusedIronWeapons = true;

        @Config.RequiresMcRestart
        @Config.Comment("Changes the boring crafting table recipes into Natural Altar infusion recipes.")
        public boolean useNaturalAltarRecipes = true;
    }

    public static class RootsConfigSE {
        @Config.RequiresMcRestart
        @Config.Comment("Enables the Roots living Spartan Weapon variants.")
        public boolean enableLivingWeapons = true;

        @Config.RequiresMcRestart
        @Config.Comment("Enables the Roots terrastone Spartan Weapon variants.")
        public boolean enableTerrastoneWeapons = true;

        /*
        @Config.RequiresMcRestart
        @Config.Comment("Enables the Roots runed Spartan Weapon variants.")
        public boolean enableRunedWeapons = true;
        */

        @Config.RequiresMcRestart
        @Config.Comment("Disables the wildwood bow and crossbow.")
        public boolean enableWildwoodBows = true;

        @Config.RequiresMcRestart
        @Config.Comment("Changes the Terrastone weapon recipes to use living weapons instead of wood, providing a more linear weapon upgrade\n" +
                "progression path. This option does nothing if living weapons are disabled.")
        public boolean useAlternateRecipes = true;
    }
}
