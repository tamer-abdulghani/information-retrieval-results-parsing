/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termproject_ir.models;

import java.util.ArrayList;

/**
 *
 * @author Tamer
 */
public class Topic {

    private int number;
    private TopicType type;
    private String query;
    private String description;
    private ArrayList<Subtopic> subtopics;

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return the type
     */
    public TopicType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(TopicType type) {
        this.type = type;
    }

    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the subtopics
     */
    public ArrayList<Subtopic> getSubtopics() {
        return subtopics;
    }

    /**
     * @param subtopics the subtopics to set
     */
    public void setSubtopics(ArrayList<Subtopic> subtopics) {
        this.subtopics = subtopics;
    }

    @Override
    public String toString() {
        return "Id:" + this.number + "," + "Topic/Query: " + this.query;
    }

}
