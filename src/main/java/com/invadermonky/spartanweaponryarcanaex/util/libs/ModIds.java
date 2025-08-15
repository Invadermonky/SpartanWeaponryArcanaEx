package com.invadermonky.spartanweaponryarcanaex.util.libs;

import WayofTime.bloodmagic.BloodMagic;
import baubles.common.Baubles;
import com.bewitchment.Bewitchment;
import com.invadermonky.spartanweaponryarcanaex.util.ModHelper;
import com.oblivioussp.spartanshields.util.Reference;
import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import de.ellpeck.naturesaura.NaturesAura;
import epicsquid.roots.Roots;
import hellfirepvp.astralsorcery.AstralSorcery;

import javax.annotation.Nullable;

public enum ModIds {
    astral_sorcery(ConstIds.astral_sorcery),
    baubles(ConstIds.baubles),
    bewitchment(ConstIds.bewitchment),
    blood_magic(ConstIds.blood_magic),
    natures_aura(ConstIds.natures_aura),
    roots(ConstIds.roots),
    spartan_shields(ConstIds.spartan_shields),
    spartan_weaponry(ConstIds.spartan_weaponry);

    public final String modId;
    public final String version;
    public final boolean isLoaded;

    ModIds(String modId) {
        this(modId, null);
    }

    ModIds(String modId, @Nullable String version) {
        this.modId = modId;
        this.version = version;
        this.isLoaded = ModHelper.isModLoaded(modId, version);
    }

    ModIds(String modId, @Nullable String version, boolean isMinVersion, boolean isMaxVersion) {
        this.modId = modId;
        this.version = version;
        this.isLoaded = ModHelper.isModLoaded(modId, version, isMinVersion, isMaxVersion);
    }

    @Override
    public String toString() {
        return this.modId;
    }

    public static class ConstIds {
        public static final String astral_sorcery = AstralSorcery.MODID;
        public static final String baubles = Baubles.MODID;
        public static final String bewitchment = Bewitchment.MODID;
        public static final String blood_magic = BloodMagic.MODID;
        public static final String natures_aura = NaturesAura.MOD_ID;
        public static final String roots = Roots.MODID;
        public static final String spartan_shields = Reference.ModID;
        public static final String spartan_weaponry = SpartanWeaponryAPI.ModID;
    }
}
