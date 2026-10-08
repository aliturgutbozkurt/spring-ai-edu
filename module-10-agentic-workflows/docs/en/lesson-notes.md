---
marp: true
theme: gaia
_class: lead
paginate: true
backgroundColor: #0f172a
color: #f8fafc
---

# Spring AI: Module 10
## Agentic AI & Autonomous Multi-Agent Workflows
### Reasoning, Acting & Squad Delegation in Java

---

## 1. What Makes an LLM "Agentic"?

- Standard LLMs generate a single completion for a single prompt.
- **Agentic AI** autonomously:
  1. Breaks down an ambiguous objective into sub-goals.
  2. Executes tools in a feedback loop (Thought -> Action -> Observation).
  3. Adapts its plan based on tool results until the goal is solved.

---

## 2. The ReAct Pattern in Spring AI

```
[User Objective]
       │
 ┌─────▼─────────────────────────┐
 │ Thought: What is my next step?│
 │ Action: Call Tool X           │◄──┐
 │ Observation: Tool result      │   │ Iterative Loop
 └─────┬─────────────────────────┘   │ (Bounded by MaxIterations)
       │ Has Goal Been Met?          │
       ├─── NO ──────────────────────┘
       └─── YES ──► Synthesize Final Output
```

---

## 3. Multi-Agent Squads: Supervisor-Worker Architecture

```
                 [Supervisor Agent]
                 (Plans & Coordinates)
                 /        |        \
                /         |         \
               v          v          v
       [Search Agent] [Data Agent] [Writer Agent]
```

- Each worker agent has isolated prompt instructions, specific tools, and bounded execution scopes.

---

## 4. Operational Safety: Loop Detection & Human Approval

- Without safety bounds, agents can enter infinite reasoning loops.
- **Critical Controls**:
  - Maximum iteration cutoff (e.g. 5 steps).
  - Loop detection (flagging identical repeated actions).
  - Human-in-the-loop checkpoints before destructive operations (e.g. database updates, financial transfers).

---

## 5. Key Architecture Takeaways

1. Always set strict iteration and timeout boundaries on agent loops.
2. Delegate specialized tasks to focused subagents rather than building one monolithic prompt.
3. Gate sensitive external mutations behind human approval checks.
