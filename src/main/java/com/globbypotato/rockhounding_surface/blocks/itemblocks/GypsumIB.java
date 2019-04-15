package com.globbypotato.rockhounding_surface.blocks.itemblocks;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.handler.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GypsumIB extends ItemBlock {

    public GypsumIB(Block block) {
        super(block);
	}

	@Override
	public boolean onEntityItemUpdate(EntityItem entityItem) {
		if(ModConfig.droppedGypsum){
			World world = entityItem.world;
			if(entityItem.getItem().getItem() == Item.getItemFromBlock(ModBlocks.WHITE_SAND)){
				if (!world.isRemote && entityItem.isInsideOfMaterial(Material.WATER)) {
					if(world.rand.nextInt(8) == 0){
						BlockPos pos = entityItem.getPosition();
						world.playSound(null, pos, SoundEvents.BLOCK_SNOW_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
						ItemStack mutationDrop = new ItemStack(ModBlocks.GYPSUM,1,0);
						EntityItem mutation = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), mutationDrop);
						mutation.setPosition(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
						mutation.motionX = 0; mutation.motionY = 0; mutation.motionZ = 0;
						world.spawnEntity(mutation);		
						entityItem.getItem().shrink(1);
						int polSize = entityItem.getItem().getCount();
						if(polSize <= 0){
							entityItem.setDead();
						}
					}
				}
			}
		}
		return super.onEntityItemUpdate(entityItem);
	}

}