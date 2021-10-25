/*
 * Copyright 2020 Jonathan Chang, Chun-yien <ccy@musicapoetica.org>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cctcc.art.c1635.demo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.function.Predicate.not;
import java.awt.Rectangle;
import processing.core.PApplet;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class Main extends PApplet {

  int size = 700;
  int margin = 50;
  Rectangle inner_rect, outer_rect;
  List<DemoAnt> ants;
  boolean status;

  @Override
  public void settings() {

    size(size, size);
  }

  @Override
  public void setup() {

    colorMode(RGB);
    this.outer_rect = new Rectangle(margin, margin, size - margin * 2, size - margin * 2);
    this.inner_rect = new Rectangle(margin * 2, margin * 2, size - margin * 4, size - margin * 4);
    this.ants = Stream.generate(() -> new DemoAnt(size))
            .filter(not(this::inSquare))
            .limit(50000)
            .collect(Collectors.toList());
    noFill();
  }

  @Override
  public void draw() {

    background(0);
    if (status == false) return;
    stroke(50);
    rect(outer_rect.x, outer_rect.y, outer_rect.width, outer_rect.height);
    rect(inner_rect.x, inner_rect.y, inner_rect.width, inner_rect.height);
    ants.stream()
            .peek(ant -> stroke(ant.color.getRGB()))
            .peek(ant -> point(ant.p.x, ant.p.y))
            .forEach(DemoAnt::move);
  }

  public boolean inSquare(DemoAnt ant) {

    return inner_rect.contains(ant.p) || !outer_rect.contains(ant.p);
  }

  @Override
  public void keyPressed() {

    if (keyCode == ENTER) status = true;
  }

  public static void main(String[] args) {

    System.setProperty("sun.java2d.uiScale", "1.0");
    PApplet.main(Main.class.getName());
  }
}
