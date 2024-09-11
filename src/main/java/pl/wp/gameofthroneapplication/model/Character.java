package pl.wp.gameofthroneapplication.model;

import java.util.List;

public class Character {

    private String name;
    private String gender;
    private String culture;
    private List<String> titles;
    private List<String> aliases;
    private List<String> allegiances;
    private List<String> playedBy;
    private List<String> tvSeriesSeasons;
    private String mother;
    private String father;
    private String spouse;

    public Character(String name, String gender, String culture, List<String> titles, List<String> aliases, List<String> allegiances, List<String> playedBy, List<String> tvSeriesSeasons, String mother, String father, String spouse) {
        this.name = name;
        this.gender = gender;
        this.culture = culture;
        this.titles = titles;
        this.aliases = aliases;
        this.allegiances = allegiances;
        this.playedBy = playedBy;
        this.tvSeriesSeasons = tvSeriesSeasons;
        this.mother = mother;
        this.father = father;
        this.spouse = spouse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public List<String> getAllegiances() {
        return allegiances;
    }

    public void setAllegiances(List<String> allegiances) {
        this.allegiances = allegiances;
    }

    public List<String> getPlayedBy() {
        return playedBy;
    }

    public void setPlayedBy(List<String> playedBy) {
        this.playedBy = playedBy;
    }

    public List<String> getTvSeriesSeasons() {
        return tvSeriesSeasons;
    }

    public void setTvSeriesSeasons(List<String> tvSeriesSeasons) {
        this.tvSeriesSeasons = tvSeriesSeasons;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", culture='" + culture + '\'' +
                ", titles=" + titles +
                ", aliases=" + aliases +
                ", allegiances=" + allegiances +
                ", playedBy=" + playedBy +
                ", tvSeriesSeasons=" + tvSeriesSeasons +
                ", mother='" + mother + '\'' +
                ", father='" + father + '\'' +
                ", spouse='" + spouse + '\'' +
                '}';
    }
}