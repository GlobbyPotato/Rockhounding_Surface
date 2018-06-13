package com.globbypotato.rockhounding_surface.machines.tileentity;

import java.util.List;

import com.globbypotato.rockhounding_core.machines.tileentity.MachineStackHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.TileEntityInv;
import com.globbypotato.rockhounding_core.machines.tileentity.WrappedItemHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.WrappedItemHandler.WriteMode;
import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.enums.EnumTruffles;
import com.globbypotato.rockhounding_surface.items.Truffles;
import com.globbypotato.rockhounding_surface.utils.BaseRecipes;

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

public class TileEntityTruffleAuction extends TileEntityInv {

	public static final int TRUFFLE_SLOT = 0;
	public static final int TOOL_SLOT = 1;
	private int truffleValue;
	private int auctionTime;
	private int bidCount;
	private int auctionMax = 1200;
	ItemStack lootStack;

	public static int inputSlots = 2;

	public TileEntityTruffleAuction(){
		super(inputSlots, 0, 0);

		this.input =  new MachineStackHandler(inputSlots,this){
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
		this.automationInput = new WrappedItemHandler(this.input, WriteMode.IN);
	}


	
//----------------------- HANDLER -----------------------
	@Override
	public int getGUIHeight() {
		return BaseRecipes.HEIGHT;
	}

	public String getName() {
		return "truffle_auction";
	}


	//----------------------- I/O -----------------------
	public ItemStack inputSlot(){
		return this.input.getStackInSlot(TRUFFLE_SLOT);
	}
	
	public ItemStack toolSlot(){
		return this.input.getStackInSlot(TOOL_SLOT);
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
	public boolean hasTruffles(ItemStack insertingStack) {
		return !insertingStack.isEmpty() && insertingStack.getItem() instanceof Truffles;
	}

	private ItemStack truffle(){
		return hasTruffles(inputSlot()) ? inputSlot() : ItemStack.EMPTY;
	}
		
	private boolean isValidTruffle() {
		return truffle().getItem() == ModItems.TRUFFLES && truffle().getItemDamage() < EnumTruffles.size();
	}

	private int truffleTier() {
		return truffle().getItemDamage();
	}

	private static int crowdFactor(int size) {
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
		if(!this.world.isRemote){
			if(hasTruffles(inputSlot())) {
				List villagerToBid = this.world.getEntitiesWithinAABB(EntityVillager.class, new AxisAlignedBB(new BlockPos(this.pos.getX() - 10, this.pos.getY() - 4, this.pos.getZ() - 10), new BlockPos(this.pos.getX() + 10, this.pos.getY() + 4, this.pos.getZ() + 10)), EntitySelectors.IS_ALIVE);
                if(isValidTruffle()){
					if(this.auctionTime >= this.auctionMax){
						if(this.truffleValue < 1){this.truffleValue = 1;}
						if(this.bidCount > 0){
							handleLoot();
							handleSpawning();
							this.auctionTime = 0;
							this.truffleValue = 1;
				        	this.bidCount = 0;
						}
					}else{
						if(!villagerToBid.isEmpty() && villagerToBid.size() > 0) {
							for(int i = 0; i < villagerToBid.size(); i++) {
								EntityVillager villager = (EntityVillager)villagerToBid.get(i);
								if(!villager.isChild()){
									if(this.rand.nextInt(1000 - (truffleTier() * 150)) == 0){
										int bidValue = (2 + truffleTier()) - this.rand.nextInt(4 + (2 * truffleTier())) - crowdFactor(villagerToBid.size());
										this.truffleValue += bidValue;
										this.bidCount++;
										if(bidValue > 0){
							                this.world.playSound((EntityPlayer)null, this.pos.getX(), this.pos.getY(), this.pos.getZ(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.AMBIENT, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
										}
									}
								}
							}
						}
						this.auctionTime++;
                    }
				}
	        }else{
	        	this.truffleValue = 1;
	        	this.bidCount = 0;
				this.auctionTime = 0;
	        }
			this.markDirtyClient();
		}
	}

	private void handleSpawning() {
		EntityItem lootEntity = new EntityItem(this.world, this.pos.getX() + 0.5, this.pos.getY() + 1.5, this.pos.getZ() + 0.5, this.lootStack);
		this.world.spawnEntity(lootEntity);
		lootEntity.motionX = 0; lootEntity.motionY = 0; lootEntity.motionZ = 0;
        this.world.playSound((EntityPlayer)null, this.pos.getX(), this.pos.getY(), this.pos.getZ(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.AMBIENT, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
		this.input.decrementSlot(TRUFFLE_SLOT);
	}

	private void handleLoot() {
		if((this.truffleValue * 100 / this.bidCount) > 50 + (10 * truffleTier())){
			handleWishlist();
		}else{
			this.lootStack = new ItemStack(Items.GOLD_NUGGET, this.truffleValue + truffleTier());
		}
	}

	private void handleWishlist() {
		if(!this.input.getStackInSlot(TOOL_SLOT).isEmpty()){
			this.lootStack = toolSlot().copy();
			this.lootStack.setCount(1);
		}else{
			this.lootStack = new ItemStack(Items.GOLD_NUGGET, this.truffleValue + truffleTier());
		}
	}

}