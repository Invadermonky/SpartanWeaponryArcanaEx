package com.invadermonky.spartanweaponryarcanaex.util.factories;

import com.google.gson.JsonObject;
import com.invadermonky.spartanweaponryarcanaex.config.ConfigHandlerSE;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;

import java.util.function.BooleanSupplier;

public class ConditionFactoryConfiguration implements IConditionFactory {
    @Override
    public BooleanSupplier parse(JsonContext context, JsonObject json) {
        String key = "config";
        boolean result = true;
        if(json.has(key)) {
            String configKey = json.get(key).getAsString();
            switch (configKey) {
                case "frostfire":
                    result = !ConfigHandlerSE.bewitchment.useFrostfireRecipes;
                    break;
                case "natural_altar":
                    result = !ConfigHandlerSE.natures_aura.useNaturalAltarRecipes;
                    break;
            }
        }
        boolean finalResult = result;
        return () -> finalResult;
    }
}
