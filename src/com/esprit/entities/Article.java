/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import java.util.Date;

/**
 *
 * @author alaagha
 */
public class Article {

    public Article() {
    }

    

    private int id;
    private String titre;
        private String tag;
    private String description;
    private String media;
    private String text;
    private String createdAt;
    private String like;
    private String commentaies;
    private int likenb;
    private int commentairesnb;
    private Commentaire commentaires;
    private boolean likedu;

    public boolean isLikedu() {
        return likedu;
    }

    public void setLikedu(boolean likedu) {
        this.likedu = likedu;
    }

    public Article(int id, String media, String text, String description, String titre, String tag, String createdAt, int likenb, int commentairesnb, Commentaire commentaires) {
        this.id = id;
        this.media = media;
        this.text = text;
        this.description = description;
        this.titre = titre;
        this.tag = tag;
        this.createdAt = createdAt;
        this.likenb = likenb;
        this.commentairesnb = commentairesnb;
        this.commentaires = commentaires;
    }

    public Article(int id, String titre, String tag, String description, String media, String text, String createdAt, int likenb, int commentairesnb, boolean likedu) {
        this.id = id;
        this.titre = titre;
        this.tag = tag;
        this.description = description;
        this.media = media;
        this.text = text;
        this.createdAt = createdAt;
        this.likenb = likenb;
        this.commentairesnb = commentairesnb;
        this.likedu = likedu;
    }

    public Article(int id, String media, String text, String description, String titre, String tag, String createdAt, String like, String commentaies, int likenb, int commentairesnb) {
        this.id = id;
        this.media = media;
        this.text = text;
        this.description = description;
        this.titre = titre;
        this.tag = tag;
        this.createdAt = createdAt;
        this.like = like;
        this.commentaies = commentaies;
        this.likenb = likenb;
        this.commentairesnb = commentairesnb;
    }

    public Article(String titre, String tag, String description, String media, String text) {
        this.titre = titre;
        this.tag = tag;
        this.description = description;
        this.media = media;
        this.text = text;
    }

    public int getLikenb() {
        return likenb;
    }

    public void setLikenb(int likenb) {
        this.likenb = likenb;
    }

    public int getCommentairesnb() {
        return commentairesnb;
    }

    public void setCommentairesnb(int commentairesnb) {
        this.commentairesnb = commentairesnb;
    }

    public Article(int id, String media, String text, String description, String titre, String tag, String createdAt) {
        this.id = id;
        this.media = media;
        this.text = text;
        this.description = description;
        this.titre = titre;
        this.tag = tag;
        this.createdAt = createdAt;
    }

    public Article(int id, String media, String text, String description, String titre, String tag, String createdAt, String like, String commentaies) {
        this.id = id;
        this.media = media;
        this.text = text;
        this.description = description;
        this.titre = titre;
        this.tag = tag;
        this.createdAt = createdAt;
        this.like = like;
        this.commentaies = commentaies;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getCommentaies() {
        return commentaies;
    }

    public void setCommentaies(String commentaies) {
        this.commentaies = commentaies;
    }

    
    
    public Article(String media, String text, String description, String titre, String tag, String createdAt) {
        this.media = media;
        this.text = text;
        this.description = description;
        this.titre = titre;
        this.tag = tag;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    public Commentaire getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Commentaire commentaires) {
        this.commentaires = commentaires;
    }

    @Override
    public String toString() {
        return "Article{" + "media=" + media + ", text=" + text + ", description=" + description + ", titre=" + titre + ", tag=" + tag + ", createdAt=" + createdAt + '}';
    }
    
}
