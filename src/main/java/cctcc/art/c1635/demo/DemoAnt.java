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

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class DemoAnt {

  Point p;
  Color color;

  static final Random R = new Random();

  public DemoAnt(int size) {

    this.p = new Point(R.nextInt(size), R.nextInt(size));
    this.color = new Color(R.nextInt());
  }

  public void move() {

    p.x += R.nextInt(3) - 1;
    p.y += R.nextInt(3) - 1;
  }
}
