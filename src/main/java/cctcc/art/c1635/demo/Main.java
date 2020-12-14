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

import java.awt.Rectangle;
import java.util.List;
import static java.util.function.Predicate.not;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import processing.core.PApplet;
import processing.core.PVector;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class Main extends PApplet {

   int size = 700;
   int margin = 50;
   Rectangle inner_rect, outer_rect;
   List<DemoAnt> ants;

   @Override
   public void settings() {

      size(size, size);
   }

   @Override
   public void setup() {

      this.outer_rect = new Rectangle(margin, margin, size - margin * 2, size - margin * 2);
      this.inner_rect = new Rectangle(margin * 2, margin * 2, size - margin * 4, size - margin * 4);
      this.ants = Stream.generate(() -> new PVector(random(size), random(size)))
              .filter(not(this::inSquare))
              .limit(1500)
              .map(DemoAnt::new)
              .collect(Collectors.toList());
      noFill();
   }

   @Override
   public void draw() {

      background(0);
      stroke(50);
      rect(outer_rect.x, outer_rect.y, outer_rect.width, outer_rect.height);
      rect(inner_rect.x, inner_rect.y, inner_rect.width, inner_rect.height);
      stroke(255);
      ants.stream()
              .peek(ant -> point(ant.p().x, ant.p().y))
              .forEach(DemoAnt::move);
   }

   public boolean inSquare(PVector v) {

      return inner_rect.contains(v.x, v.y) || !outer_rect.contains(v.x, v.y);
   }

   public static void main(String[] args) {

      System.setProperty("sun.java2d.uiScale", "1.0");
      PApplet.main(Main.class.getName());
   }
}
