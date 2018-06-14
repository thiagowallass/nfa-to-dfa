/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automaton;

/**
 *
 * @author thiag
 */
public class DFA extends Automaton{
    
    StateDFA initial;
    State last;
    
    DFA(){
        super();
    }
    
    void addInitialState(StateDFA state){
        initial = state;
        addState(state);
    }
    
    @Override
    void addLastState(State state){
        last = state;
        addState(state);
    }
    
    @Override
    public String toString(){
        String back = "";
        for(Object object: programFunction){
            StateDFA state = (StateDFA) object;
            back += state.name + "\n"; //+ " " + state.transition.letter + " " + state.transition.next.name + "\n";
        }
        return back;
    }
}
