package com.globbypotato.rockhounding_surface.handler;

import java.util.Random;

import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.enums.EnumTruffles;
import com.globbypotato.rockhounding_surface.integration.SupportUtils;
import com.globbypotato.rockhounding_surface.utils.BaseRecipes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLilyPad;
import net.minecraft.block.BlockMycelium;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GlobbyEventHandler {
	Random rand = new Random();
	ItemStack truffleStack;
	@SubscribeEvent
	public void onBlockBreak(HarvestDropsEvent event) {
		if(event.getState().getBlock() instanceof BlockLilyPad){
			if(rand.nextInt(10) == 0){
				if(!event.getWorld().isRemote){
                	EntityItem entGrub = new EntityItem(event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), BaseRecipes.teredo);
                	entGrub.motionY += rand.nextFloat() * 0.05F;
                	entGrub.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                	entGrub.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                	event.getWorld().spawnEntityInWorld(entGrub);
                	event.getDrops().clear();
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event){
		if(ModConfig.enableTruffles){
			if(event.getEntity() instanceof EntityPig){
				EntityPig thePig = (EntityPig)event.getEntity();
				if(!thePig.isChild()){
					BlockPos posPig = thePig.getPosition();
					World world = thePig.worldObj;
					if(!world.isRemote){
						Biome biome = world.getBiome(posPig);
						if(isValidBiome(biome)){
							BlockPos groundPos = new BlockPos(posPig.getX(), posPig.getY() - 1, posPig.getZ());
							Block groundBlock = world.getBlockState(groundPos).getBlock();
							if(isValidSoil(groundBlock)){
								int truffleChance = rand.nextInt(ModConfig.truffleRarity);
								if(truffleChance == 0){
									int truffleType = rand.nextInt(100);
									if(truffleType == 0){
						                truffleStack =  new ItemStack(ModItems.truffles,1,EnumTruffles.WHITE.ordinal());
									}else if(truffleType >= 1 && truffleType < 6){
										truffleStack =  new ItemStack(ModItems.truffles,1,EnumTruffles.GIANT.ordinal());
									}else if(truffleType >= 6 && truffleType < 16){
										truffleStack =  new ItemStack(ModItems.truffles,1,EnumTruffles.SUMMER.ordinal());
									}else if(truffleType >= 16 && truffleType < 46){
										truffleStack =  new ItemStack(ModItems.truffles,1,EnumTruffles.WINTER.ordinal());
									}else{
										truffleStack =  new ItemStack(ModItems.truffles,1,EnumTruffles.BLACK.ordinal());
									}
				                	EntityItem entTruffle = thePig.entityDropItem(truffleStack, 1.0F);
				                	entTruffle.motionY += rand.nextFloat() * 0.05F;
				                	entTruffle.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
				                    entTruffle.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
				                    world.playSound((EntityPlayer)null, posPig.getX(), posPig.getY(), posPig.getZ(), SoundEvents.ENTITY_PIG_AMBIENT, SoundCategory.AMBIENT, 1.5F, 0.7F);
									if(ModConfig.pigGriefing){
										world.destroyBlock(groundPos, true);
									}else{
										consumeGrass(world, groundPos);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private void consumeGrass(World world, BlockPos groundPos) {
		if(world.getBlockState(groundPos).getBlock() == Blocks.GRASS){
			world.setBlockState(groundPos, Blocks.DIRT.getDefaultState());
		}
		if(SupportUtils.bopLoaded()){
			ItemStack grassStack = world.getBlockState(groundPos).getBlock().getPickBlock(world.getBlockState(groundPos), null, world, groundPos, null);
			if(grassStack != null && grassStack.getItem() == Item.getItemFromBlock(SupportUtils.grass())){
				int grassmeta = grassStack.getItemDamage();
				IBlockState griefedState = SupportUtils.dirt().getStateForPlacement(world, groundPos, EnumFacing.UP, 0.0F, 0.0F, 0.0F, grassmeta-2, null, null);
				world.setBlockState(groundPos, griefedState);
			}
		}
	}

	private boolean isValidSoil(Block groundBlock) {
		return groundBlock instanceof BlockGrass || groundBlock instanceof BlockMycelium;
	}

	private boolean isValidBiome(Biome biome) {
		return BiomeDictionary.isBiomeOfType(biome, Type.FOREST) || BiomeDictionary.isBiomeOfType(biome, Type.CONIFEROUS) || BiomeDictionary.isBiomeOfType(biome, Type.MUSHROOM);
	}

}