//input stack: the item to compost
//type: the type of composting (-1:custom, 0:seed, 1:plants, 2:saplings, 3:vegetation, 4:food, 5:fossil)
//value: the custom composting value if type is -1
mods.rockhounding_surface.CompostBin.add(<minecraft:dye:0>, 1, 0);


//input = itemstack representing the item to be processed
//solute = itemstack representing the contaminating ingredient
//solvent = the fluidstack representing the solvent in which to perform the process.
//output = itemstack representing the final object
mods.rockhounding_surface.WoodIncubator.add(<minecraft:slime_ball>, <minecraft:dye:7>, <liquid:water>*1000, <minecraft:slime>);

//output = the itemstack to remove
mods.rockhounding_surface.WoodIncubator.remove(<rockhounding_surface:bogLogs:2>);