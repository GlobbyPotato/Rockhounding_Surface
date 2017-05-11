package com.globbypotato.rockhounding_surface.blocks;

import com.globbypotato.rockhounding_surface.handler.Reference;

import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BaseGate extends BlockFenceGate {

	public BaseGate(String name, Material material, EnumType plank, SoundType soundtype, float hardness, float resistance) {
		super(plank);
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this).setRegistryName(name));
		setCreativeTab(Reference.RockhoundingSurface);
		setHardness(hardness); setResistance(resistance); setSoundType(soundtype);
        this.setDefaultState(this.blockState.getBaseState().withProperty(OPEN, Boolean.valueOf(false)).withProperty(POWERED, Boolean.valueOf(false)).withProperty(IN_WALL, Boolean.valueOf(false)));
	}

    @Override
    public BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {FACING, OPEN, POWERED, IN_WALL});
    }
}