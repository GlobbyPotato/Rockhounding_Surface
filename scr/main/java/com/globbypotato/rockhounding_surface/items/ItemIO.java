package com.globbypotato.rockhounding_surface.items;

import com.globbypotato.rockhounding_core.items.BaseArray;
import com.globbypotato.rockhounding_core.items.BaseItem;
import com.globbypotato.rockhounding_surface.handler.Reference;

public class ItemIO extends BaseItem {

	public ItemIO(String name) {
		super(name);
		setCreativeTab(Reference.RockhoundingSurface);
	}
}