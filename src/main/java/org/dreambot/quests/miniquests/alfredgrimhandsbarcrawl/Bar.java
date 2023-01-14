package org.dreambot.quests.miniquests.alfredgrimhandsbarcrawl;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;

public enum Bar {
  DRAGON(new Area(2557, 3077, 2548, 3082, 0), "Bartender", 7),
  FORESTER(new Area(2689, 3488, 2700, 3498, 0), "Bartender", 9),
  JOLLY(new Area(3282, 3489, 3272, 3486, 0), "Bartender", 10),
  BLUE_MOON(new Area(3222, 3402, 3227, 3394, 0), "Bartender", 4),
  BLURBERRY(new Area(2480, 3490, 2484, 3486, 1), "Blurberry", 5),
  FLYING_HORSE(
      new Area(
          new Tile(2575, 3319, 0),
          new Tile(2572, 3320, 0),
          new Tile(2572, 3326, 0),
          new Tile(2577, 3326, 0)),
      "Bartender",
      8),
  DEADMAN(
      new Area(
          new Tile(2799, 3154, 0),
          new Tile(2793, 3154, 0),
          new Tile(2791, 3157, 0),
          new Tile(2791, 3169, 0),
          new Tile(2799, 3169, 0)),
      "Bartender",
      6),
  KARAMJA(new Area(2918, 3147, 2930, 3142, 0), "Zambo", 11),
  RUSTY(new Area(3044, 3259, 3055, 3255, 0), "Bartender", 13),
  RISING_SUN(
      new Area(
          new Tile(2957, 3378, 0),
          new Tile(2955, 3378, 0),
          new Tile(2955, 3375, 0),
          new Tile(2953, 3374, 0),
          new Tile(2953, 3366, 0),
          new Tile(2961, 3365, 0),
          new Tile(2960, 3369, 0),
          new Tile(2962, 3369, 0),
          new Tile(2960, 3375, 0),
          new Tile(2958, 3376, 0)),
      "Kaylee",
      12);

  private Area area;
  private String bartender;
  private int gc;
  private boolean visited;

  Bar(Area area, String bartender, int gc) {
    this.area = area;
    this.bartender = bartender;
    this.gc = gc;
    this.visited = false;
  }

  public Area getArea() {
    return this.area;
  }

  public String getBartender() {
    return this.bartender;
  }

  public int getGCIndex() {
    return this.gc;
  }

  public void setVisited(boolean visited) {
    this.visited = visited;
  }

  public boolean visited() {
    return this.visited;
  }
}
