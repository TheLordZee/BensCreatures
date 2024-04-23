package com.zee.benscreatures.setup;

import com.zee.benscreatures.entities.sandElemental.SandElemental;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.zee.benscreatures.BensCreatures.MODID;

public class Registration {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);
    public static void init(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        ENTITIES.register(bus);
    }
    public static final BlockBehaviour.Properties ORE_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).strength(8f).requiresCorrectToolForDrops();
    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ModSetup.ITEM_GROUP);

    public static final RegistryObject<Block> ANCIENT_STEEL_ORE = BLOCKS.register("ancient_steel_ore", () -> new Block(ORE_PROPERTIES));
    public static final RegistryObject<Block> ANCIENT_STEEL_BLOCK = BLOCKS.register("ancient_steel_block", () -> new Block(ORE_PROPERTIES));


    public static final RegistryObject<Item> ANCIENT_STEEL_ORE_ITEM = fromBlock(ANCIENT_STEEL_ORE);
    public static final RegistryObject<Item> ANCIENT_STEEL_BLOCK_ITEM = fromBlock(ANCIENT_STEEL_BLOCK);

    public static final RegistryObject<Item> ANCIENT_STEEL_NUGGET = ITEMS.register("ancient_steel_nugget", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> ANCIENT_STEEL_INGOT = ITEMS.register("ancient_steel_ingot", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> ANCIENT_STEEL_CHUNK = ITEMS.register("ancient_steel_chunk", () -> new Item(ITEM_PROPERTIES));

    public static final RegistryObject<EntityType<SandElemental>> SAND_ELEMENTAL = ENTITIES.register("sand_elemental", () -> EntityType.Builder.of(SandElemental::new, MobCategory.MONSTER)
            .sized(0.6f, 1.95f)
            .clientTrackingRange(8)
            .setShouldReceiveVelocityUpdates(false)
            .build("sand_elemental"));
    public static final RegistryObject<Item> SAND_ELEMENTAL_EGG = ITEMS.register("sand_elemental", () -> new ForgeSpawnEggItem(SAND_ELEMENTAL, 0xff0000, 0x00ff00, ITEM_PROPERTIES));

    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block){
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
    }
}
