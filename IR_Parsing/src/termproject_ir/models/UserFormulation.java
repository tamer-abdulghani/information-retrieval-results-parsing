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
public class UserFormulation {

    private int userId;
    private ArrayList<UserTopic> topics;

    public UserFormulation() {
        topics = new ArrayList<>();
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User_" + this.getUserId();
    }

    public String toString(int topicId) {
        String topicTitle = getTopicTitleById(topicId);
        return "User_" + this.getUserId() + ": " + topicTitle;
    }

    private String getTopicTitleById(int topicId) {
        for (UserTopic t : this.getTopics()) {
            if (t.getNum() == topicId) {
                return t.getTitle();
            }
        }
        return "";
    }

    /**
     * @return the topics
     */
    public ArrayList<UserTopic> getTopics() {
        return topics;
    }

    /**
     * @param topics the topics to set
     */
    public void setTopics(ArrayList<UserTopic> topics) {
        this.topics = topics;
    }

}
