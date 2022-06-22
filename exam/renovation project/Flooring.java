// Copyright 2022 Julius Schmidt
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//     http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

import java.util.Objects;

public class Flooring extends Material {
  private final static double limit = 0.02;
  private double widthOfFlooring;

  public Flooring(String name, double price, double width) {
    super(name, price);
    if (width <= 0)
      throw new IllegalArgumentException("width must be positive");
    this.widthOfFlooring = width;
  }

  public double getWidth() {
    return widthOfFlooring;
  }

  @Override
  public int getMaterialRequirements(Surface surface) {
    Objects.requireNonNull(surface, "surface must be non-null");
    return (int) Math.floor(surface.getArea() / widthOfFlooring + (1d - limit)); // add offset to next integer and round
                                                                                 // down
  }

}
