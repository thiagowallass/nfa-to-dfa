/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automaton;

import java.util.ArrayList;

/**
 *
 * @author thiag
 */
public class State {
    String name;
    ArrayList<Transition> transitions;
    
    State(String name){
        
        this.name = name;
        transitions = new ArrayList<Transition>();
    
    }
    
    public void addTransition(Transition t){
        
        transitions.add(t);
    
    }
}
