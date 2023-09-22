package com.yc.bean;

public class Contributor {


    String login;
    int contributions;
    int id;
    String node_id;
    String avatar_url;
    String gravatar_id;
    String url;
    String html_url;
    String followers_url;

    @Override
    public String toString() {
        return "Contributor{" +
                "login='" + login + '\'' +
                ", contributions=" + contributions +
                ", id=" + id +
                ", node_id='" + node_id + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", gravatar_id='" + gravatar_id + '\'' +
                ", url='" + url + '\'' +
                ", html_url='" + html_url + '\'' +
                ", followers_url='" + followers_url + '\'' +
                '}';
    }
}
