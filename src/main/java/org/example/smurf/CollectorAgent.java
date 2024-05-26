package org.example.smurf;

import jadex.bdiv3.annotation.*;
import jadex.bdiv3.runtime.IPlan;
import jadex.micro.annotation.Agent;

@Agent
@Plans({
        @Plan(trigger=@Trigger(goals=CollectorAgent.GatherResourcesGoal.class), body=@Body(CollectorAgent.GatherResourcesPlan.class)),
        @Plan(trigger=@Trigger(goals=CollectorAgent.TradeResourcesGoal.class), body=@Body(CollectorAgent.TradeResourcesPlan.class))
})
public class CollectorAgent {
    @Belief
    protected int woodInventory = 0;

    @Belief
    protected int waterInventory = 0;

    @Goal
    public class GatherResourcesGoal {}

    @Goal
    public class TradeResourcesGoal {}

    public class GatherResourcesPlan {
        @PlanBody
        public void body(IPlan plan) {
            // Resource gathering logic
        }
    }

    public class TradeResourcesPlan {
        @PlanBody
        public void body(IPlan plan) {
            // Trading logic
        }
    }
}
