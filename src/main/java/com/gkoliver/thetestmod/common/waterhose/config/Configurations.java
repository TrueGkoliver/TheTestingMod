package com.gkoliver.thetestmod.common.waterhose.config;

import java.util.HashMap;

public class Configurations {
	public static HashMap<Integer, IWaterHoseType> CONFIGS = new HashMap<Integer, IWaterHoseType>();
	public static IWaterHoseType getConfig(int id) {
		return CONFIGS.get(id);
	}

	public static final IWaterHoseType CONFIG_DEFAULT = new DefaultConfiguration();

}
