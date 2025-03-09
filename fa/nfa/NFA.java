package fa.nfa;

import fa.State;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * TODO Docs
 *
 * @author Jayce Lowry
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
        return Set.of();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<NFAState> eClosure(NFAState s) {
        return Set.of();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int maxCopies(String s) {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addTransition(String fromState, Set<String> toStates, char onSymb) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDFA() {
        return false;
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
