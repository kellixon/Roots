package epicsquid.roots.ritual;

import epicsquid.mysticalworld.init.ModItems;
import epicsquid.roots.entity.ritual.EntityRitualStorm;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualStorm extends RitualBase {
  public RitualStorm(String name, int duration) {
    super(name, duration);
    addIngredients(
            new ItemStack(Blocks.WATERLILY),
            new ItemStack(ModItems.bark_oak),
            new ItemStack(Blocks.VINE),
            new ItemStack(ModItems.wildroot),
            new ItemStack(Items.BEETROOT_SEEDS)
    );
  }

  @Override
  public void doEffect(World world, BlockPos pos) {
    this.spawnEntity(world, pos, EntityRitualStorm.class);
  }
}