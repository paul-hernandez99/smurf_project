package org.example.smurf;

import jadex.bdiv3.annotation.*;
import jadex.bdiv3.runtime.IPlan;
import jadex.micro.annotation.Agent;

@Agent
@Plans({
        @Plan(trigger=@Trigger(goals=BlacksmithAgent.CreateToolsGoal.class), body=@Body(BlacksmithAgent.CreateToolsPlan.class)),
        @Plan(trigger=@Trigger(goals=BlacksmithAgent.TradeToolsGoal.class), body=@Body(BlacksmithAgent.TradeToolsPlan.class))
})
public class BlacksmithAgent {
    @Belief
    protected int toolInventory = 0;

    @Goal
    public class CreateToolsGoal {}

    @Goal
    public class TradeToolsGoal {}

    public class CreateToolsPlan {
        @PlanBody
        public void body(IPlan plan) {
            // Tool creation logic
        }
    }

    public class TradeToolsPlan {
        @PlanBody
        public void body(IPlan plan) {
            // Trading logic
        }
    }
}

