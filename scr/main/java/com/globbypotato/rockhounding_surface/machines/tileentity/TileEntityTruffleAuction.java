package com.globbypotato.rockhounding_surface.machines.tileentity;

import java.util.List;
import java.util.Random;

import com.globbypotato.rockhounding_core.machines.tileentity.MachineStackHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.TileEntityMachineInv;
import com.globbypotato.rockhounding_core.machines.tileentity.WrappedItemHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.WrappedItemHandler.WriteMode;
import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.enums.EnumTruffles;
import com.globbypotato.rockhounding_surface.items.Truffles;
import com.globbypotato.rockhounding_surface.machines.gui.GuiTruffleAuction;

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

	public static final int TRUFFLE_SLOT = 0;
	public static final int TOOL_SLOT = 1;
	Random rand = new Random();
	private int truffleValue;
	private int auctionTime;
	private int bidCount;
	private int auctionMax = 1200;
	ItemStack lootStack;

	public static int totInput = 2;
	public static int totOutput = 1;

	public TileEntityTruffleAuction(){
		super(totInput, totOutput);

		input =  new MachineStackHandler(totInput,this){
			@Override
			public ItemStack insertItem(int slot, ItemStack insertingStack, boolean simulate){
				if(slot == TRUFFLE_SLOT && hasTruffles(insertingStack)){
					return super.insertItem(slot, insertingStack, simulate);
				}
				if(slot == TOOL_SLOT){
					return super.insertItem(slot, insertingStack, simulate);
				}
				return insertingStack;
			}
		};
		this.automationInput = new WrappedItemHandler(input, WriteMode.IN);
	}



	//----------------------- HANDLER -----------------------
	@Override
	public int getGUIHeight() {
		return GuiTruffleAuction.HEIGHT;
	}



	//----------------------- I/O -----------------------
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



	//----------------------- CUSTOM -----------------------
	private boolean hasTruffles(ItemStack insertingStack) {
		return insertingStack != null && insertingStack.getItem() instanceof Truffles;
	}

	private ItemStack truffle(){
		return hasTruffles(input.getStackInSlot(TRUFFLE_SLOT)) ? input.getStackInSlot(TRUFFLE_SLOT) : null;
	}
		
	private boolean isValidTruffle() {
		return truffle().getItem() == ModItems.truffles && truffle().getItemDamage() < EnumTruffles.size();
	}

	private int truffleTier() {
		return truffle().getItemDamage();
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



	//----------------------- PROCESS -----------------------
	@Override
	public void update() {
		if(!worldObj.isRemote){
			if(hasTruffles(input.getStackInSlot(TRUFFLE_SLOT))) {
				List villagerToBid = worldObj.getEntitiesWithinAABB(EntityVillager.class, new AxisAlignedBB(new BlockPos(pos.getX() - 10, pos.getY() - 4, pos.getZ() - 10), new BlockPos(pos.getX() + 10, pos.getY() + 4, pos.getZ() + 10)), EntitySelectors.IS_ALIVE);
                if(isValidTruffle()){
					if(this.auctionTime >= this.auctionMax){
						if(truffleValue < 1){truffleValue = 1;}
						if(bidCount > 0){
							handleLoot();
							handleSpawning();
							auctionTime = 0;
							truffleValue = 1;
				        	bidCount = 0;
						}
					}else{
						if(!villagerToBid.isEmpty() && villagerToBid.size() > 0) {
							for(int i = 0; i < villagerToBid.size(); i++) {
								EntityVillager villager = (EntityVillager)villagerToBid.get(i);
								if(!villager.isChild()){
									if(rand.nextInt(1000 - (truffleTier() * 150)) == 0){
										int bidValue = (2 + truffleTier()) - rand.nextInt(4 + (2 * truffleTier())) - crowdFactor(villagerToBid.size());
										truffleValue += bidValue;
										bidCount++;
										if(bidValue > 0){
							                worldObj.playSound((EntityPlayer)null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.AMBIENT, 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
										}
									}
								}
							}
						}
						auctionTime++;
                    }
				}
	        }else{
	        	truffleValue = 1;
	        	bidCount = 0;
				auctionTime = 0;
	        }
			this.markDirtyClient();
		}
	}

	private void handleSpawning() {
		EntityItem lootEntity = new EntityItem(worldObj, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5, lootStack);
		worldObj.spawnEntityInWorld(lootEntity);
		lootEntity.motionX = 0; lootEntity.motionY = 0; lootEntity.motionZ = 0;
		worldObj.playSound((EntityPlayer)null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.AMBIENT, 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
		input.decrementSlot(TRUFFLE_SLOT);
	}

	private void handleLoot() {
		if((truffleValue * 100 / bidCount) > 50 + (10 * truffleTier())){
			handleWishlist();
		}else{
			lootStack = new ItemStack(Items.GOLD_NUGGET, truffleValue + truffleTier());
		}
	}

	private void handleWishlist() {
		if(input.getStackInSlot(TOOL_SLOT) != null){
			lootStack = input.getStackInSlot(TOOL_SLOT).copy();
			lootStack.stackSize = 1;
		}else{
			lootStack = new ItemStack(Items.GOLD_NUGGET, truffleValue + truffleTier());
		}
	}

}