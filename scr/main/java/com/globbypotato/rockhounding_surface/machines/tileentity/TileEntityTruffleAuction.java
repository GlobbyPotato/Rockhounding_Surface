package com.globbypotato.rockhounding_surface.machines.tileentity;

import java.util.List;
import java.util.Random;

import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.enums.EnumTruffles;
import com.globbypotato.rockhounding_surface.machines.gui.GuiTruffleAuction;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class TileEntityTruffleAuction extends TileEntityMachineInv {

	public static final int TOOL_SLOT = 0;
	Random rand = new Random();
	private int truffleValue;
	private int auctionTime;
	private int bidCount;
	private int auctionMax = 1200;
	ItemStack lootStack;

	public TileEntityTruffleAuction(){
		super(1,0);

		input =  new MachineStackHandler(INPUT_SLOTS,this){
			@Override
			public ItemStack insertItem(int slot, ItemStack insertingStack, boolean simulate){
				if(slot == TOOL_SLOT){
					return super.insertItem(slot, insertingStack, simulate);
				}
				return insertingStack;
			}
		};
		this.automationInput = new WrappedItemHandler(input, WrappedItemHandler.WriteMode.IN_OUT);
	}

	@Override
	public int getGUIHeight() {
		return GuiTruffleAuction.HEIGHT;
	}

	@Override
	public void update() {
		if(!worldObj.isRemote){
			List truffleToSell = worldObj.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), new BlockPos(pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1)));
			List villagerToBid = worldObj.getEntitiesWithinAABB(EntityVillager.class, new AxisAlignedBB(new BlockPos(pos.getX() - 10, pos.getY() - 4, pos.getZ() - 10), new BlockPos(pos.getX() + 10, pos.getY() + 4, pos.getZ() + 10)), EntitySelectors.IS_ALIVE);
			if(truffleToSell != null && !truffleToSell.isEmpty()) {
				for (Object stack : truffleToSell) {
	                if(stack != null && stack instanceof EntityItem) {
	                    EntityItem truffle = (EntityItem)stack;
	                    if(isValidTruffle(truffle)) {
	                        if(truffle.getEntityItem().stackSize >= 1) {
								if(this.auctionTime >= this.auctionMax){
									if(truffleValue < 1){truffleValue = 1;}
									if(bidCount > 0){
										handleLoot(truffle);
										handleSpawning(truffle);
										auctionTime = 0;
										truffleValue = 1;
							        	bidCount = 0;
									}
								}else{
									if(!villagerToBid.isEmpty() && villagerToBid.size() > 0) {
										for(int i = 0; i < villagerToBid.size(); i++) {
											EntityVillager villager = (EntityVillager)villagerToBid.get(i);
											if(!villager.isChild()){
												if(rand.nextInt(1000 - (truffleTier(truffle) * 150)) == 0){
													int bidValue = (2 + truffleTier(truffle)) - rand.nextInt(4 + (2 * truffleTier(truffle))) - crowdFactor(villagerToBid.size());
													truffleValue += bidValue;
													bidCount++;
													if(bidValue > 0){
										                worldObj.playSound((EntityPlayer)null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_EXPERIENCE_ORB_TOUCH, SoundCategory.AMBIENT, 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
													}
												}
											}
										}
									}
									auctionTime++;
								}
	                        }
	                    }
	                }
	            }
	        }else{
	        	truffleValue = 1;
	        	bidCount = 0;
				auctionTime = 0;
	        }
		}
	}

	private void handleSpawning(EntityItem truffle) {
		EntityItem lootEntity = new EntityItem(worldObj, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5, lootStack);
		if (lootEntity != null) {
			worldObj.spawnEntityInWorld(lootEntity);
			lootEntity.motionX = 0; lootEntity.motionY = 0; lootEntity.motionZ = 0;
            worldObj.playSound((EntityPlayer)null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.AMBIENT, 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
		}
		if(truffle.getEntityItem().stackSize >= 1){ 
			truffle.getEntityItem().stackSize--; 
		}else{ 
			truffle.setDead(); 
		}
	}

	private void handleLoot(EntityItem truffle) {
		if((truffleValue * 100 / bidCount) > 50 + (10 * truffleTier(truffle))){
			handleWishlist(truffle);
		}else{
			lootStack = new ItemStack(Items.GOLD_NUGGET, truffleValue + truffleTier(truffle));
		}
	}

	private void handleWishlist(EntityItem truffle) {
		if(input.getStackInSlot(TOOL_SLOT) != null){
			lootStack = input.getStackInSlot(TOOL_SLOT).copy();
			lootStack.stackSize = 1;
		}else{
			lootStack = new ItemStack(Items.GOLD_NUGGET, truffleValue + truffleTier(truffle));
		}
	}

	private boolean isValidTruffle(EntityItem truffle) {
		return truffle.getEntityItem().getItem() == ModItems.truffles && truffle.getEntityItem().getItemDamage() < EnumTruffles.size();
	}

	private int truffleTier(EntityItem truffle) {
		return truffle.getEntityItem().getItemDamage();
	}

	private int crowdFactor(int size) {
		if(size <= 30){
			return 3 - (size/10);
		}else if(size > 30 && size <= 60){
			return 0;
		}else{
			return -1;	
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		this.bidCount = compound.getInteger("BidCount");
		this.truffleValue = compound.getInteger("TruffleValue");
		this.auctionTime = compound.getInteger("AuctionTime");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound){
		super.writeToNBT(compound);
		compound.setInteger("BidCount", this.bidCount);
		compound.setInteger("TruffleValue", this.truffleValue);
		compound.setInteger("AuctionTime", this.auctionTime);
		return compound;
	}

}