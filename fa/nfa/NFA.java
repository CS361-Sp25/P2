package fa.nfa;

import fa.State;

import java.util.Set;

/**
 * TODO Docs
 */
public class NFA implements NFAInterface {

    @Override
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        return Set.of();
    }

    @Override
    public Set<NFAState> eClosure(NFAState s) {
        return Set.of();
    }

    @Override
    public int maxCopies(String s) {
        return 0;
    }

    @Override
    public boolean addTransition(String fromState, Set<String> toStates, char onSymb) {
        return false;
    }

    @Override
    public boolean isDFA() {
        return false;
    }

    @Override
    public boolean addState(String name) {
        return false;
    }

    @Override
    public boolean setFinal(String name) {
        return false;
    }

    @Override
    public boolean setStart(String name) {
        return false;
    }

    @Override
    public void addSigma(char symbol) {

    }

    @Override
    public boolean accepts(String s) {
        return false;
    }

    @Override
    public Set<Character> getSigma() {
        return Set.of();
    }

    @Override
    public NFAState getState(String name) {
        return null;
    }

    @Override
    public boolean isFinal(String name) {
        return false;
    }

    @Override
    public boolean isStart(String name) {
        return false;
    }
}
