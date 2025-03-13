package fa.nfa;

import fa.State;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

/**
 * TODO Docs
 *
 * @author Jayce Lowry and Chase Stombaugh
 */
public class NFA implements NFAInterface {
    private Set<NFAState> states;
    private Set<Character> alphabet;
    private NFAState startState;
    private Set<NFAState> finalStates;

    /**
     * TODO Docs
     */
    public NFA() {
        this.alphabet = new LinkedHashSet<>();
        this.states = new LinkedHashSet<>();
        this.startState = null;
        this.finalStates = new LinkedHashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        if (from == null || !states.contains(from)) {
            return Set.of();
        }
        return from.getTransitionSet(onSymb) != null ? from.getTransitionSet(onSymb) : Set.of();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<NFAState> eClosure(NFAState s) {
        Set<NFAState> closure = new LinkedHashSet<>();
        Stack<NFAState> stack = new Stack<>();
        stack.push(s);

        while (!stack.isEmpty()) {
            NFAState current = stack.pop();
            if (closure.add(current)) { // Add state if not already visited
                Set<NFAState> epsilonTransitions = current.getTransitionSet('e');
                if (epsilonTransitions != null) {
                    stack.addAll(epsilonTransitions);
                }
            }
        }
        return closure;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int maxCopies(String s) {
        if (startState == null) return 0;

        Set<NFAState> currentStates = eClosure(startState);
        int maxCopies = currentStates.size();

        for (char symbol : s.toCharArray()) {
            Set<NFAState> nextStates = new LinkedHashSet<>();
            for (NFAState state : currentStates) {
                Set<NFAState> transitions = state.getTransitionSet(symbol);
                if (transitions != null) {
                    for (NFAState t : transitions) {
                        nextStates.addAll(eClosure(t));
                    }
                }
            }
            currentStates = nextStates;
            maxCopies = Math.max(maxCopies, currentStates.size());
        }

        return maxCopies;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addTransition(String fromState, Set<String> toStates, char onSymb) {
        NFAState from = getState(fromState);
        if (from == null || !alphabet.contains(onSymb) && onSymb != 'e') {
            return false; // state is invalid or the symbol isn't in the alphabet
        }

        Set<NFAState> toStateObjects = new LinkedHashSet<>();
        for (String name : toStates) {
            NFAState state = getState(name);
            if (state == null) {
                return false; // one of the toStates doesn't exist
            }
            toStateObjects.add(state);   
        }
        
        from.setTransitions(toStateObjects, onSymb);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDFA() {
        for (NFAState state : states) {
            if (state.getTransitionSet('e') != null) {
                return false; // epsilon transitions
            }
            for (char symbol : alphabet) {
                Set<NFAState> transitions = state.getTransitionSet(symbol);
                if (transitions != null && transitions.size() > 1) {
                    return false; // More than one transition per symbol
                }
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addState(String name) {
        if (name == null || getState(name) != null) {
            return false;
        }
        NFAState newState = new NFAState(name);
        states.add(newState);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean setFinal(String name) {
        NFAState state = getState(name);
        if (state == null) {
            return false;
        }
        finalStates.add(state);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean setStart(String name) {
        NFAState state = getState(name);
        if (state == null) {
            return false;
        }
        startState = state;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSigma(char symbol) {
        if (symbol == 'e') { // Reserve 'e' for epsilon
            return;
        }
        alphabet.add(symbol);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean accepts(String s) {
        if (startState == null) return false;

        Set<NFAState> currentStates = eClosure(startState);

        for (char symbol : s.toCharArray()) {
            Set<NFAState> nextStates = new LinkedHashSet<>();
            for (NFAState state : currentStates) {
                Set<NFAState> transitions = state.getTransitionSet(symbol);
                if (transitions != null) {
                    for (NFAState t : transitions) {
                        nextStates.addAll(eClosure(t));
                    }
                }
            }
            currentStates = nextStates;
        }

        for (NFAState state : currentStates) {
            if (finalStates.contains(state)) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Character> getSigma() {
        return new LinkedHashSet<>(alphabet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NFAState getState(String name) {
        for (NFAState state : states) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFinal(String name) {
        return finalStates.contains(getState(name));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isStart(String name) {
        return startState != null && startState.getName().equals(name);
    }
}
