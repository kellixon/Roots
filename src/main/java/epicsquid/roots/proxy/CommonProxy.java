package epicsquid.roots.proxy;

import epicsquid.roots.effect.EffectManager;
import epicsquid.roots.init.HerbRegistry;
import epicsquid.roots.init.ModItems;
import epicsquid.roots.recipe.recipes.RunicCarvingRecipes;
import epicsquid.roots.ritual.RitualRegistry;
import epicsquid.roots.rune.RuneRegistry;
import epicsquid.roots.spell.SpellRegistry;
import epicsquid.roots.util.OfferingUtil;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
  public void preInit(FMLPreInitializationEvent event) {
    ModItems.registerOredict();
  }

  public void init(FMLInitializationEvent event) {
    HerbRegistry.init();
    RitualRegistry.init();
    SpellRegistry.init();
    OfferingUtil.init();
    EffectManager.init();
    RuneRegistry.init();
    RunicCarvingRecipes.initRecipes();
  }

  public void postInit(FMLPostInitializationEvent event) {

  }
}
