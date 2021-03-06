package epicsquid.roots.ritual;

import epicsquid.mysticalworld.init.ModItems;
import epicsquid.roots.entity.ritual.EntityRitualRegrowth;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualRegrowth extends RitualBase {
  public RitualRegrowth(String name, int duration) {
    super(name, duration);
    addIngredients(
            new ItemStack(ModItems.terra_moss), 
            new ItemStack(ModItems.terra_spores), 
            new ItemStack(ModItems.bark_spruce),
            new ItemStack(Blocks.SAPLING), 
            new ItemStack(Blocks.SAPLING)
    );
  }

  @Override
  public void doEffect(World world, BlockPos pos) {
    this.spawnEntity(world, pos, EntityRitualRegrowth.class);
  }
}