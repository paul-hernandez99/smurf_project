package org.example.smurf;

import jadex.base.IPlatformConfiguration;
import jadex.base.PlatformConfigurationHandler;
import jadex.base.Starter;

public class Main
{
    public static void main(String[] args)
    {
        IPlatformConfiguration config = PlatformConfigurationHandler.getDefaultNoGui();
        config.addComponent(FarmerAgentBDI.class);
        Starter.createPlatform(config).get();
    }
}
