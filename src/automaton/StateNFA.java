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
public class StateNFA extends State{
    ArrayList<Transition> transitions;
    
    StateNFA(String name){
        super(name);
        transitions = new ArrayList<>();
    }
    
    public void addTransition(Transition t){
        transitions.add(t);
    }
}
