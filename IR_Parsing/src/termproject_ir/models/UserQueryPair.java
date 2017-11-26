/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termproject_ir.models;

/**
 *
 * @author Tamer
 */
public class UserQueryPair {

    private int UserID;
    private int TopicID;

    public UserQueryPair(int userId, int topicId) {
        this.UserID = userId;
        this.TopicID = topicId;
    }

    /**
     * @return the UserID
     */
    public int getUserID() {
        return UserID;
    }

    /**
     * @param UserID the UserID to set
     */
    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    /**
     * @return the TopicID
     */
    public int getTopicID() {
        return TopicID;
    }

    /**
     * @param TopicID the TopicID to set
     */
    public void setTopicID(int TopicID) {
        this.TopicID = TopicID;
    }
}
