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
public class UserTopic {

    private int userId;
    private String topId;
    private int num;
    private String title;

    /**
     * @return the topId
     */
    public String getTopId() {
        return topId;
    }

    /**
     * @param topId the topId to set
     */
    public void setTopId(String topId) {
        this.topId = topId;
    }

    /**
     * @return the num
     */
    public int getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "User_" + this.userId + ", " + this.title;
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
}
