package com.globbypotato.rockhounding_surface.items.io;

import com.globbypotato.rockhounding_core.items.BaseItem;
import com.globbypotato.rockhounding_surface.handler.Reference;

public class ItemIO extends BaseItem {

	public ItemIO(String name) {
		super(Reference.MODID, name);
		setCreativeTab(Reference.RockhoundingSurface);
	}
}