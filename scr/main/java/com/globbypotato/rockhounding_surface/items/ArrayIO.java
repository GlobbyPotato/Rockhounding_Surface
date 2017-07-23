package com.globbypotato.rockhounding_surface.items;

import com.globbypotato.rockhounding_core.items.BaseArray;
import com.globbypotato.rockhounding_surface.handler.Reference;

public class ArrayIO extends BaseArray {

	public ArrayIO(String name, String[] array) {
		super(name, array);
		setCreativeTab(Reference.RockhoundingSurface);
	}
}