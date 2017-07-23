package com.globbypotato.rockhounding_surface.fluids;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

import com.globbypotato.rockhounding_surface.handler.Reference;
import com.globbypotato.rockhounding_surface.integration.SupportUtils;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

//Courtesy of Choonster, (MIT License) https://github.com/Choonster/TestMod3
public class ModFluids {
	/**
	 * The fluids registered by this mod. Includes fluids that were already registered by another mod.
	 */
	public static final Set<Fluid> FLUIDS = new HashSet<>();

	/**
	 * The fluid blocks from this mod only. Doesn't include blocks for fluids that were already registered by another mod.
	 */
	public static final Set<IFluidBlock> MOD_FLUID_BLOCKS = new HashSet<>();

	public static final Fluid ACIDIC_WATER = createFluid("acidic_water", true, SupportUtils.rhChemistryLoaded(), 0xFFD8FFFF,
			fluid -> fluid.setDensity(1000).setViscosity(1000).canBePlacedInWorld(),
			fluid -> new BlockFluidClassic(fluid, new MaterialLiquid(MapColor.SILVER)));
	public static final Fluid  AGING_BATH = createFluid("aging_bath", true, SupportUtils.rhChemistryLoaded(), 0xFF88AD8C,
			fluid -> fluid.setDensity(100).setViscosity(15000).canBePlacedInWorld(),
			fluid -> new BlockFluidClassic(fluid, new MaterialLiquid(MapColor.LIME)));
	public static final Fluid CASTING_BATH = createFluid("casting_bath", true, SupportUtils.rhChemistryLoaded(), 0xFFADA488,
			fluid -> fluid.setDensity(100).setViscosity(30000).canBePlacedInWorld(),
			fluid -> new BlockFluidClassic(fluid, new MaterialLiquid(MapColor.FOLIAGE)));

	/**
	 * Create a {@link Fluid} and its {@link IFluidBlock}, or use the existing ones if a fluid has already been registered with the same name.
	 */
	private static <T extends Block & IFluidBlock> Fluid createFluid(String name, boolean hasFlowIcon, boolean canCreate, int color, Consumer<Fluid> fluidPropertyApplier, Function<Fluid, T> blockFactory) {
		if(SupportUtils.rhChemistryLoaded()){
			final String texturePrefix = Reference.MODID + ":fluids/";
			final ResourceLocation still = new ResourceLocation(texturePrefix + "basefluid_still");
			final ResourceLocation flowing = hasFlowIcon ? new ResourceLocation(texturePrefix + "basefluid_flow") : still;

			Fluid fluid = new Fluid(name, still, flowing){
				@Override
				public int getColor() {
					return color;
				}
			};
			final boolean useOwnFluid = FluidRegistry.registerFluid(fluid);
	
			if (useOwnFluid) {
				fluidPropertyApplier.accept(fluid);
				MOD_FLUID_BLOCKS.add(blockFactory.apply(fluid));
			} else {
				fluid = FluidRegistry.getFluid(name);
			}
	
			FLUIDS.add(fluid);
			return fluid;
		}
		return null;
	}

	@Mod.EventBusSubscriber
	public static class RegistrationHandler {

		/**
		 * Register this mod's fluid {@link Block}s.
		 */
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			final IForgeRegistry<Block> registry = event.getRegistry();
			for (final IFluidBlock fluidBlock : MOD_FLUID_BLOCKS) {
				final Block block = (Block) fluidBlock;
				block.setRegistryName(Reference.MODID, "fluid." + fluidBlock.getFluid().getName());
				block.setUnlocalizedName(Reference.MODID + ":" + fluidBlock.getFluid().getUnlocalizedName());
				block.setCreativeTab(Reference.RockhoundingSurface);
				registry.register(block);
			}
		}

		/**
		 * Register this mod's fluid {@link ItemBlock}s.
		 */
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> registry = event.getRegistry();

			for (final IFluidBlock fluidBlock : MOD_FLUID_BLOCKS) {
				final Block block = (Block) fluidBlock;
				final ItemBlock itemBlock = new ItemBlock(block);
				itemBlock.setRegistryName(block.getRegistryName());
				registry.register(itemBlock);
			}
		}
	}

	public static void registerFluidContainers() {
		for (final Fluid fluid : FLUIDS) {
			if(fluid != null){
				registerBucket(fluid);
			}
		}
	}

	private static void registerBucket(Fluid fluid) {
		FluidRegistry.addBucketForFluid(fluid);
	}

}