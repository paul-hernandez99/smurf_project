package org.example.smurf;

import jadex.bdiv3.annotation.*;
import jadex.bdiv3.runtime.IPlan;
import jadex.micro.annotation.Agent;

@Agent
@Plans({
        @Plan(trigger=@Trigger(goals=LeaderAgent.ResolveConflictGoal.class), body=@Body(LeaderAgent.ResolveConflictPlan.class))
})
public class LeaderAgent {
    @Belief
    protected int reputation = 1;

    @Goal
    public class ResolveConflictGoal {}

    public class ResolveConflictPlan {
        @PlanBody
        public void body(IPlan plan) {
            // Conflict resolution logic
        }
    }
}
