package fa.nfa;

import fa.State;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents a single state for a Non-Deterministic
 * Finite Automata (NFA), and manages transition
 * sets to other states.
 *
 * @author Jayce Lowry
 */
public class NFAState extends State {
    private Map<Character, Set<NFAState>> transitions;

    /**
     * Creates a new NFAState
     *
     * @param name the name of this NFAState
     */
    public NFAState(String name) {
        super(name);
        transitions = new HashMap<>();
    }

    /**
     * Sets this NFAState to transition to other
     * states on a particular symbol. The transition
     * is not set if the set of states to transition
     * to is null.
     *
     * @param transitionSet the set of states to transition to.
     * @param onSymb the symbol to transition on.
     */
    public void setTransitions(Set<NFAState> transitionSet, char onSymb) {
        if (transitionSet == null) {
            return;
        }
        Set<NFAState> existingTransitions = transitions.get(onSymb);
        if (existingTransitions == null) {
            transitions.put(onSymb, transitionSet);
        } else {
            existingTransitions.addAll(transitionSet);
        }
    }

    /**
     * Gets the set of states this NFAState transitions
     * to on a particular symbol.
     *
     * @param onSymb the symbol this NFAState transitions on.
     * @return a set of NFAStates this NFAState transitions
     * to, or null if this NFAState doesn't have a transition
     * on the specified symbol.
     */
    public Set<NFAState> getTransitionSet(char onSymb) {
        return transitions.get(onSymb);
    }
}
