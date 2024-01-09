package org.example.dtos;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("tasks")
public class    TaskDTO {
    @SuppressWarnings("unused")
    @Id
    private String id;
    private String name;

    private State state;
    private String obs;



    @SuppressWarnings("unused")
    public TaskDTO() { }

    @SuppressWarnings("unused")
    public TaskDTO(String name, String obs, State state) {
        this.name = name;
        this.obs = obs;
        this.state = state;
    }


    public enum State {
        COMPLETED,
        INCOMPLETE
    }
    @SuppressWarnings("unused")
    public void markAsCompleted(){
        state = State.COMPLETED;
    }
    @SuppressWarnings("unused")
    public  void markAsIncomplete(){
        state = State.INCOMPLETE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @SuppressWarnings("unused")
    public State getState() {
        return state;
    }
    @SuppressWarnings("unused")
    public void setState(State state) {
        this.state = state;
    }
    @SuppressWarnings("unused")
    public String getObs() {
        return obs;
    }
    @SuppressWarnings("unused")
    public void setObs(String obs) {
        this.obs = obs;
    }
}
