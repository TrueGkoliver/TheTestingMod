package com.gkoliver.thetestmod.common.nonidealstructures;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.EndCityPieces;
import net.minecraft.world.gen.feature.structure.EndCityStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class FakeEndCityStructure {
   protected List<StructurePiece> components = Lists.newArrayList();
   protected MutableBoundingBox bounds;
   private int references;
   public FakeEndCityStructure() {
	   
   }
   public void generateEndCity(TemplateManager templateManager, BlockPos pos) {
        Rotation rotation = Rotation.randomRotation(new Random());
        EndCityPieces.startHouseTower(templateManager, pos, rotation, this.components, new Random());
        System.out.println(this.components.size());
        this.recalculateStructureSize();
        
     }
   
   public void synchronizeComponents(ISeedReader p_230366_1_, StructureManager p_230366_2_, ChunkGenerator p_230366_3_, Random p_230366_4_, MutableBoundingBox p_230366_5_, ChunkPos p_230366_6_) {
	      synchronized(this.components) {
	         if (!this.components.isEmpty()) {
	            MutableBoundingBox mutableboundingbox = (this.components.get(0)).getBoundingBox();
	            Vector3i vector3i = mutableboundingbox.func_215126_f();
	            BlockPos blockpos = new BlockPos(vector3i.getX(), mutableboundingbox.minY, vector3i.getZ());
	            Iterator<StructurePiece> iterator = this.components.iterator();

	            while(iterator.hasNext()) {
	               StructurePiece structurepiece = iterator.next();
	               if (structurepiece.getBoundingBox().intersectsWith(p_230366_5_) && !structurepiece.func_230383_a_(p_230366_1_, p_230366_2_, p_230366_3_, p_230366_4_, p_230366_5_, p_230366_6_, blockpos)) {
	                  iterator.remove();
	               }
	            }

	            this.recalculateStructureSize();
	         }
	      }
	   }
	protected void recalculateStructureSize() {
		this.bounds = MutableBoundingBox.getNewBoundingBox();

		for(StructurePiece structurepiece : this.components) {
			this.bounds.expandTo(structurepiece.getBoundingBox());
		}

	}
	
}
