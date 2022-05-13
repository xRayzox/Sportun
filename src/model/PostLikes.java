/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author alaagha
 */
public class PostLikes {
    int id;
    int articleId;
    int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public PostLikes() {
    }

    public PostLikes(int id) {
        this.id = id;
    }

    public PostLikes(int articleId, int userId) {
        this.articleId = articleId;
        this.userId = userId;
    }
    
    
    
}
