package org.example.smurf;

import jadex.bdiv3.BDIAgentFactory;
import jadex.bdiv3.annotation.*;
import jadex.bdiv3.runtime.IPlan;
import jadex.micro.annotation.*;

@Agent(type=BDIAgentFactory.TYPE)
public class FarmerAgentBDI {

    //@AgentFeature
    //protected IBDIAgentFeature bdi;

    @Belief
    private int foodInventory;
    @Belief(dynamic=true)
    protected boolean lowFoodInventory = foodInventory < 20;

    @Belief
    private int waterInventory;
    @Belief(dynamic=true)
    protected boolean lowWaterInventory = waterInventory < 20;

    @Belief
    private int cropsState;
    @Belief(dynamic=true)
    protected boolean cropsReady = cropsState == 5;

    private void printStatus () {
        System.out.println("\nFood Inventory: "+foodInventory);
        System.out.println("Water Inventory: "+waterInventory);
        System.out.println("Crops State: "+cropsState+"\n");
    }

    @AgentCreated
    public void init()
    {
        foodInventory = 0;
        waterInventory = 100;
        cropsState = 0;

        printStatus();
    }

    @Plan(trigger=@Trigger(factchanged="lowFoodInventory"))
    public class PlantCropsPlan {
        @PlanAPI
        protected IPlan plan;

        @PlanContextCondition(beliefs="waterInventory")
        public boolean checkCondition()
        {
            return waterInventory>=50;
        }

        @PlanBody
        public void body() {
            printStatus();
            while(cropsState<5) {
                System.out.println("Planting crops...");
                plan.waitFor(20000).get();
                waterInventory -= 10;
                cropsState += 1;
                printStatus();
            }
        }

        @PlanPassed
        public void passed()
        {
            System.out.println("Planting finished successfully.");
        }

        @PlanAborted
        public void aborted()
        {
            System.out.println("Planting aborted.");
        }

        @PlanFailed
        public void failed(Exception e)
        {
            System.out.println("Planting failed: "+e);
        }
    }

    @Plan(trigger=@Trigger(factchanged="cropsReady"))
    public class HarvestCropsPlan {

        @PlanAPI
        protected IPlan plan;

        @PlanBody
        public void body() {
            printStatus();
            System.out.println("Harvesting crops...");
            plan.waitFor(10000).get();
            foodInventory += 20;
            cropsState = 0;
            printStatus();
        }
        @PlanPassed
        public void passed()
        {
            System.out.println("Harvesting finished successfully.");
        }

        @PlanAborted
        public void aborted()
        {
            System.out.println("Harvesting aborted.");
        }

        @PlanFailed
        public void failed(Exception e)
        {
            System.out.println("Harvesting failed: "+e);
        }
    }

    @Plan(trigger=@Trigger(factchanged="lowWaterInventory"))
    public class TradeFoodForWaterPlan {

        @PlanAPI
        protected IPlan plan;

        @PlanContextCondition(beliefs="foodInventory")
        public boolean checkCondition()
        {
            return foodInventory>=25;
        }

        @PlanBody
        public void body() {
            System.out.println("Trading food for water...");
            foodInventory -= 25;
            waterInventory += 25;
        }
        @PlanPassed
        public void passed()
        {
            System.out.println("Trading finished successfully.");
        }

        @PlanAborted
        public void aborted()
        {
            System.out.println("Trading aborted.");
        }

        @PlanFailed
        public void failed(Exception e)
        {
            System.out.println("Trading failed: "+e);
        }
    }
}
