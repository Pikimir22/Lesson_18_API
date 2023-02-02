package models;

public class LoginBodyModel {
    private String name, morpheus, job, leader;

    public String getMorpheus() {
        return morpheus;
    }

    public void setMorpheus(String morpheus) {
        this.morpheus = morpheus;
    }

    public String getJob() {
        return job;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public void setJob(String job) {
        this.job = job;
    }

//    @Override
//    public String toString() {
//        return "{\"name=\"" + name + '\'' +
//                ", morpheus='" + morpheus + '\'' +
//                ", job='" + job + '\'' +
//                ", leader='" + leader + '\'' +
//                '}';
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
