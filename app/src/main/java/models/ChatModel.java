package models;

public class ChatModel {

    private String id;
    private String name;
    private String state;

    public ChatModel(String id, String name/*, String state*/) {
        this.id = id;
        this.name = name;
//        this.state = state;
    }
/*
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
