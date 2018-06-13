package com.globbypotato.rockhounding_surface.handler;

import java.util.Random;

import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.blocks.io.GateIO;
import com.globbypotato.rockhounding_surface.blocks.io.LogIO;
import com.globbypotato.rockhounding_surface.enums.EnumTruffles;
import com.globbypotato.rockhounding_surface.integration.SupportUtils;
import com.globbypotato.rockhounding_surface.items.Truffles;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
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
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GlobbyEventHandler {

	Random rand = new Random();
	ItemStack truffleStack;



	@SubscribeEvent
    @SideOnly(Side.CLIENT)
	public void handleTooltip(ItemTooltipEvent event){
		ItemStack itemstack = event.getItemStack();
		if(itemstack != ItemStack.EMPTY){
			if(itemstack.getItem() instanceof Truffles){
				int chance = 0;
				if(itemstack.getItemDamage() ==  EnumTruffles.BLACK.ordinal()){chance = 54;}
				if(itemstack.getItemDamage() ==  EnumTruffles.WINTER.ordinal()){chance = 30;}
				if(itemstack.getItemDamage() ==  EnumTruffles.SUMMER.ordinal()){chance = 10;}
				if(itemstack.getItemDamage() ==  EnumTruffles.GIANT.ordinal()){chance = 5;}
				if(itemstack.getItemDamage() ==  EnumTruffles.WHITE.ordinal()){chance = 1;}
				event.getToolTip().add(TextFormatting.GRAY + "Rarity: " + TextFormatting.YELLOW + chance + "%");
			}
		}
	}



	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event){
		if(ModConfig.ENABLE_TRUFFLES){
			if(event.getEntity() instanceof EntityPig){
				EntityPig thePig = (EntityPig)event.getEntity();
				if(!thePig.isChild()){
					BlockPos posPig = thePig.getPosition();
					World world = thePig.world;
					if(!world.isRemote){
						Biome biome = world.getBiome(posPig);
						if(isValidBiome(biome)){
							BlockPos groundPos = new BlockPos(posPig.getX(), posPig.getY() - 1, posPig.getZ());
							Block groundBlock = world.getBlockState(groundPos).getBlock();
							if(isValidSoil(groundBlock)){
								int truffleChance = this.rand.nextInt(ModConfig.truffleRarity);
								if(truffleChance == 0){
									int truffleType = this.rand.nextInt(100);
									if(truffleType == 0){
						                this.truffleStack =  new ItemStack(ModItems.TRUFFLES, 1, EnumTruffles.WHITE.ordinal());
									}else if(truffleType >= 1 && truffleType < 6){
										this.truffleStack =  new ItemStack(ModItems.TRUFFLES, 1, EnumTruffles.GIANT.ordinal());
									}else if(truffleType >= 6 && truffleType < 16){
										this.truffleStack =  new ItemStack(ModItems.TRUFFLES, 1, EnumTruffles.SUMMER.ordinal());
									}else if(truffleType >= 16 && truffleType < 46){
										this.truffleStack =  new ItemStack(ModItems.TRUFFLES, 1, EnumTruffles.WINTER.ordinal());
									}else{
										this.truffleStack =  new ItemStack(ModItems.TRUFFLES, 1, EnumTruffles.BLACK.ordinal());
									}
				                	EntityItem entTruffle = thePig.entityDropItem(this.truffleStack, 1.0F);
				                	entTruffle.motionY += this.rand.nextFloat() * 0.05F;
				                	entTruffle.motionX += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F;
				                    entTruffle.motionZ += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F;
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



	@SubscribeEvent
	public void onBlockBurn(FurnaceFuelBurnTimeEvent event) {
		if(Block.getBlockFromItem(event.getItemStack().getItem()) instanceof LogIO ){
			event.setBurnTime(0);
		}else if(Block.getBlockFromItem(event.getItemStack().getItem()) instanceof GateIO ){
			event.setBurnTime(0);
		}
	}



	public static void consumeGrass(World world, BlockPos groundPos) {
		if(world.getBlockState(groundPos).getBlock() == Blocks.GRASS){
			world.setBlockState(groundPos, Blocks.DIRT.getDefaultState());
		}
		if(SupportUtils.bopLoaded()){
			ItemStack grassStack = world.getBlockState(groundPos).getBlock().getPickBlock(world.getBlockState(groundPos), null, world, groundPos, null);
			if(!grassStack.isEmpty() && grassStack.getItem() == Item.getItemFromBlock(SupportUtils.grass())){
				int grassmeta = grassStack.getItemDamage();
				IBlockState griefedState = SupportUtils.dirt().getStateForPlacement(world, groundPos, EnumFacing.UP, 0.0F, 0.0F, 0.0F, grassmeta-2, null, null);
				world.setBlockState(groundPos, griefedState);
			}
		}
	}

	public static boolean isValidSoil(Block groundBlock) {
		return groundBlock instanceof BlockGrass || groundBlock instanceof BlockMycelium;
	}

	public static boolean isValidBiome(Biome biome) {
		return BiomeDictionary.hasType(biome, Type.FOREST) || BiomeDictionary.hasType(biome, Type.CONIFEROUS) || BiomeDictionary.hasType(biome, Type.MUSHROOM);
	}

}